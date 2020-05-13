package filesprocessing;

import java.io.File;
import java.util.ArrayList;

class FilterPrefixSuffix extends FilterTypes {


    private String theWantedVal = null;
    private int numberOfLetters = 0;
    private int lengthOfName = 0;
    private boolean isItPrefix;
    private static final String FILTER_PREFIX = "prefix";
    private static final String FILTER_SUFFIX = "suffix";


    /**
     * constructor
     * @param cuttedStringToArray - - the array of the filter argumants
     * @throws Type1Exceptions - exception of type1
     */
    FilterPrefixSuffix(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size() >= 2) {
            if(cuttedStringToArray.get(0).equals(FILTER_PREFIX)){
                isItPrefix = true;
            }
            theWantedVal = cuttedStringToArray.get(1);
            numberOfLetters = theWantedVal.length();

                if (cuttedStringToArray.size() > 2 && cuttedStringToArray.get(2).equals(NOT_STRING)) {
                    notSuffix = true;
                } else {
                    throw new Type1Exceptions(new BadBooleanParameters());
                }
            }
        }


    @Override
    protected boolean specificCondition(File file) {
        boolean result;
        lengthOfName = file.getName().length();
        String fileName = file.getName();
        String specialCutOfName = prefixOrSuffix(fileName);
        if (theWantedVal.equals(specialCutOfName)){
            result = true;
        }else {
            result = false;
        }

        return changeToNot(result,notSuffix);
    }

    private String prefixOrSuffix(String fileName){
        String subtractedString;
        if (isItPrefix){
            subtractedString = fileName.substring(0,numberOfLetters);
        }else{
            subtractedString = fileName.substring(lengthOfName-numberOfLetters,lengthOfName);
        }
        return subtractedString;
    }
}
