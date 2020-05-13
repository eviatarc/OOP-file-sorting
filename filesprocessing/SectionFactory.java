package filesprocessing;

import java.io.*;
import java.util.ArrayList;
import stringManipulations.*;

class SectionFactory {
    //    private static final Order ABS_ORDER = new Order(Order.ABS, false);
//    private static final Order ABS_REVERSED_ORDER = new Order(Order.ABS, true);
    private static final int FILTER_INDEX = 0;
    private static final int ORDER_INDEX = 1;
    private static final int FILTER_LINE_INDEX = 2;
    private static final int ORDER_LINE_INDEX = 3;
    private static final String FILTER_APEARED = "FILTER";
    private static final String ORDER_APEARED = "ORDER";
    private static final char SAPERATOR_BY_HASHTAG = '#';


    //       filter types constant names          //
    static final String FILTER_GREATER_THAN = "greater_than";
    static final String FILTER_BETWEEN = "between";
    static final String FILTER_SMALLER_THAN = "smaller_than";
    static final String FILTER_FILE = "file";
    static final String FILTER_CONTAINS = "contains";
    static final String FILTER_PREFIX = "prefix";
    static final String FILTER_SUFFIX = "suffix";
    static final String FILTER_WRITABLE = "writable";
    static final String FILTER_EXECUTABLE = "executable";
    static final String FILTER_HIDDEN = "hidden";
    static final String FILTER_ALL = "all";
    static final String DO_DEFUALT = "do the defualt";


    /**
     * a function that reads the file that recieved (that represensented as a path)
     * and extract the data that is relevant for creating 4-cells that each one holds data to create
     * a single section.
     * this function throws exceptions from type2 when needed relative to the command file content
     *
     * @param commandsPath - the path where the wanted command file located
     * @return - array list that contain 4-cells that holds data in this order:
     * the filter type
     * the order type
     * the line where the filter type has been read from
     * the line where the order type has been read from
     * @throws Type2Exceptions
     */
    private static ArrayList<String[]> tupleListCreator(String commandsPath) throws Type2Exceptions {
        ArrayList<String[]> sections;
        try {
            File commandsFile = new File(commandsPath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(commandsFile));
            sections = new ArrayList<>();
            String[] tuple_filter_order = new String[4];
            String tempString = null;
            String lastString = null;
            String lastLastString = null;
            boolean flagLookingForOrderType = false;
            int line = 0;
            while ((tempString = bufferedReader.readLine()) != null) {
                line++;
                if (flagLookingForOrderType) {
                    if (!tempString.equals(FILTER_APEARED)) {
                        tuple_filter_order[ORDER_INDEX] = tempString;
                        lastString = null;
                    } else {
                        tuple_filter_order[ORDER_INDEX] = null;

                        lastString = tempString;
                    }
                    tuple_filter_order[ORDER_LINE_INDEX] = Integer.toString(line);
                    sections.add(tuple_filter_order);
                    tuple_filter_order = new String[4];
                    lastLastString = null;
                    flagLookingForOrderType = false;
//                    lines[ORDER_LINE_INDEX] = line;
                } else {
                    if (lastLastString == null) {
                        if (lastString != null) {
                            if (lastString.equals(FILTER_APEARED)) {
                                tuple_filter_order[FILTER_INDEX] = tempString;
                                tuple_filter_order[FILTER_LINE_INDEX] = Integer.toString(line);

                            } else {
                                throw new Type2Exceptions(new FilterSubSectionMissing());
                            }
                        } else {
                            if (!tempString.equals(FILTER_APEARED)) {
                                throw new Type2Exceptions(new FilterSubSectionMissing());
                            }
                        }
                    } else {
                        if (lastLastString.equals(FILTER_APEARED) && !tempString.equals(ORDER_APEARED)) {
                            throw new Type2Exceptions(new BadSubSectionName());
                        }

                        if (lastLastString.equals(FILTER_APEARED) && tempString.equals(ORDER_APEARED)) {
                            flagLookingForOrderType = true;
                        }
                    }
                    lastLastString = lastString;
                    lastString = tempString;
                }


            }
            if ((lastLastString != null && lastLastString.equals(FILTER_APEARED)) || ((lastString != null)
                    && !lastString.equals(ORDER_APEARED))) {
                throw new Type2Exceptions(new BadSubSectionName());
            }

            sections.add(tuple_filter_order);
        } catch (IOException e) {
            throw new Type2Exceptions(e);
        }

        return sections;
    }


    /**
     * a function that uses many others functions to create the array list of sections,
     * its uses other functions to create Order and Filter object and by using tham it creates
     * a single section, and continue untill made all required section
     * @param sourceDir - string that holds the source dir paht
     * @param commands - string that holds the command file path
     * @return array list of section objects that are ready to by printed
     * @throws Type2Exceptions
     * @throws Type1Exceptions
     */
    static ArrayList<Section> factoryOfSections(String sourceDir, String commands) throws Type2Exceptions,
            Type1Exceptions {
        ArrayList<String[]> listOfSectionsRequired = tupleListCreator(commands);
        ArrayList<Section> theSectionsList = new ArrayList<>();

        FilterTypes singleFilter;

        Order singleOrder;

        for (int i = 0; i < listOfSectionsRequired.size(); i++) {
            try {
                if (listOfSectionsRequired.get(i)[FILTER_INDEX] == null) {
                    break;
                }
                singleFilter = createTheWantedFilter(listOfSectionsRequired.get(i)[FILTER_INDEX]);
            } catch (Type1Exceptions e) {
                System.err.println(e.getMessage() + listOfSectionsRequired.get(i)[FILTER_LINE_INDEX]);
                singleFilter = new FilterAll();
            }

            try {
                singleOrder = new Order((listOfSectionsRequired.get(i)[ORDER_INDEX]), false);
            } catch (Type1Exceptions e) {
                System.err.println(e.getMessage() + listOfSectionsRequired.get(i)[ORDER_LINE_INDEX]);
                singleOrder = new Order(Order.ABS, false);
            }

            Section tempHandleSection = new Section(new File(sourceDir), singleFilter, singleOrder);
            theSectionsList.add(tempHandleSection);
        }
        return theSectionsList;
    }

    /**
     * function that is a factory for filters
     * @param theAskedFilter - the string that hols inside all the filter parameters
     * @return filter object as required in the command file, if the recieved data is'nt fit
     * this function will throw exception
     * @throws Type1Exceptions
     */
    private static FilterTypes createTheWantedFilter(String theAskedFilter) throws Type1Exceptions {
        FilterTypes currentFilter;
        ArrayList<String> cuttedStringToArray = Split.tearTheStringApart(theAskedFilter,
                SAPERATOR_BY_HASHTAG);
        String theFilterType = cuttedStringToArray.get(0);
        switch (theFilterType) {

            case (FILTER_GREATER_THAN):
                currentFilter = new FilterGreatherOrSmaller(cuttedStringToArray);
                break;

            case (FILTER_BETWEEN):
                currentFilter = new FilterBetween(cuttedStringToArray);
                break;

            case (FILTER_SMALLER_THAN):
                currentFilter = new FilterGreatherOrSmaller(cuttedStringToArray);
                break;

            case (FILTER_FILE):
                currentFilter = new FilterFile(cuttedStringToArray);
                break;

            case (FILTER_CONTAINS):
                currentFilter = new FilterContains(cuttedStringToArray);
                break;

            case (FILTER_PREFIX):
                currentFilter = new FilterPrefixSuffix(cuttedStringToArray);
                break;

            case (FILTER_SUFFIX):
                currentFilter = new FilterPrefixSuffix(cuttedStringToArray);
                break;

            case (FILTER_WRITABLE):
                currentFilter = new FilterWritableOrExecutableOrHidden(cuttedStringToArray);
                break;

            case (FILTER_EXECUTABLE):
                currentFilter = new FilterWritableOrExecutableOrHidden(cuttedStringToArray);
                break;

            case (FILTER_HIDDEN):
                currentFilter = new FilterWritableOrExecutableOrHidden(cuttedStringToArray);
                break;

            case (FILTER_ALL):
                currentFilter = new FilterAll();
                break;

            default:
                throw new Type1Exceptions(new NoSuchFilter());
        }
        return currentFilter;
    }
}