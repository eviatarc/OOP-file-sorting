package filesprocessing;

import java.io.File;
import java.util.ArrayList;

/**
 * a class that create the file filter
 */
public class FilterFile extends FilterTypes {

    public static final String NOT_STRING = "NOT";
    private String valueString = "";
    private boolean notSuffix;

    /**
     * constructor
     * @param cuttedStringToArray - the array of the filter argumants
     * @throws Type1Exceptions - exceptionn of type1
     */
    FilterFile(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size()>=2){
            valueString = cuttedStringToArray.get(1);
            if (cuttedStringToArray.size() > 2){
                if (cuttedStringToArray.get(2).equals(NOT_STRING)) {
                    System.out.println(cuttedStringToArray);
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

        if (file.getName().equals(valueString)){
            result = true;

        }else{
            result =false;
        }

        return changeToNot(result,notSuffix);

    }
}
