package filesprocessing;

import java.io.*;
import java.util.ArrayList;

import static filesprocessing.SectionFactory.FILTER_GREATER_THAN;

/**
 * a class that create greater than and smaller than filteres
 */
class FilterGreatherOrSmaller extends FilterTypes {

    private double threshold;
    private boolean isItGreater;
    private double fileSize;

    /**
     * constructor
     * @param cuttedStringToArray - - the array of the filter argumants
     * @throws Type1Exceptions - exception of type1
     */
    FilterGreatherOrSmaller(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size() >= 2) {
            if(cuttedStringToArray.get(0).equals(FILTER_GREATER_THAN)){
                isItGreater = true;
            }
            threshold = Double.parseDouble(cuttedStringToArray.get(1));
            if (threshold >= 0) {

                if (cuttedStringToArray.size() > 2 && cuttedStringToArray.get(2).equals(NOT_STRING)) {
                    notSuffix = true;
                }
            } else {
                throw new Type1Exceptions(new NegativeNumber());
            }
        }
    }


    @Override
    protected boolean specificCondition(File file) {

        boolean result;
        fileSize = file.length();
        result = greaterOrSmaller();

        return changeToNot(result,notSuffix);
    }

    /**
     * @return true if the file size is greater than the given theshold if freater than is
     * chosed, analogy if smaller than is chosed, false otherwise
     */
    private boolean greaterOrSmaller(){
        String subtractedString;
        if (isItGreater && (fileSize)>(threshold*MULTIPLY_TO_KBYES)){
            return true;
        }
        if (!(isItGreater) && (fileSize)<(threshold*MULTIPLY_TO_KBYES)){
            return true;
        }
        return false;
    }
}
