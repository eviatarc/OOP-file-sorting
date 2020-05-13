package filesprocessing;

import java.io.File;
import java.util.ArrayList;

/**
 * class that create a filter from three types, all in one because almost similar:
 * writable,executable,hidden
 */
class FilterWritableOrExecutableOrHidden extends FilterTypes {

    private static final String FILTER_WRITABLE = "writable";
    private static final String FILTER_EXECUTABLE = "executable";
    private static final String FILTER_HIDDEN = "hidden";

    private String FilterRequired;

    /**
     * constructor
     * @param cuttedStringToArray - - the array of the filter argumants
     * @throws Type1Exceptions - exception of type1
     */
    FilterWritableOrExecutableOrHidden(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size() == 2) {
            FilterRequired = cuttedStringToArray.get(0);
                if (cuttedStringToArray.get(1).equals(YES_SUFFIX)) {
                    noSuffix = false;
                }else if (cuttedStringToArray.get(1).equals(NO_SUFFIX)){
                    noSuffix = true;
                }
            }else{
                throw new Type1Exceptions(new BadBooleanParameters());
            }

        }


    @Override
    public boolean specificCondition(File file) {
        boolean result = false;
        if (FilterRequired.equals(FILTER_WRITABLE)){
            if(file.canWrite()){
                result = true;
            }
        }
        if (FilterRequired.equals(FILTER_EXECUTABLE)){
            if(file.canExecute()){
                result = true;
            }
        }
        if (FilterRequired.equals(FILTER_HIDDEN)){
            if(file.isHidden()){
                result = true;
            }
        }

        return changeToNot(result,notSuffix);
    }

}
