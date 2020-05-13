package filesprocessing;

import java.io.File;
import java.util.ArrayList;

/**
 * class that creates the contains filter
 */
class FilterContains extends FilterTypes {

    private String theWantedValue;

    /**
     * constructor
     * @param cuttedStringToArray - - the array of the filter argumants
     * @throws Type1Exceptions - exception of type1
     */
    FilterContains(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size() >= 2) {
            theWantedValue = cuttedStringToArray.get(1);
            if (cuttedStringToArray.size() > 2){
                if (cuttedStringToArray.get(2).equals(NOT_STRING)) {
                    notSuffix = true;
                } else {
                    throw new Type1Exceptions(new BadBooleanParameters());
                }
            }
        }
    }

    @Override
    protected boolean specificCondition(File file) {
        boolean result;
        if (file.getName().contains(theWantedValue)){
            result = true;
        }else{
            result =false;
        }

        return changeToNot(result,notSuffix);

    }
}
