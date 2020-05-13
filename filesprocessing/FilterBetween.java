package filesprocessing;

import java.io.*;
import java.util.ArrayList;

/**
 * a class that creates the between filter
 */
class FilterBetween extends FilterTypes {

    private double lowerBound;
    private double upperBound;

    /**
     * constructor
     * @param cuttedStringToArray - - the array of the filter argumants
     * @throws Type1Exceptions - exception of type1
     */
    FilterBetween(ArrayList<String> cuttedStringToArray) throws Type1Exceptions {
        if (cuttedStringToArray.size()>=3) {
            lowerBound = Double.parseDouble(cuttedStringToArray.get(1));
            upperBound = Double.parseDouble(cuttedStringToArray.get(2));

            if ((lowerBound >= 0 && upperBound>=0)){
                if (((lowerBound < upperBound))) {
                    if (cuttedStringToArray.size() > 3){
                        if (cuttedStringToArray.get(3).equals(NOT_STRING)) {
                            notSuffix = true;
                        } else {
                            throw new Type1Exceptions(new BadBooleanParameters());
                        }
                    }
                }else{
                    throw new Type1Exceptions(new WrongOrderOfParameters());
                }
            }else {
                throw new Type1Exceptions(new NegativeNumber());
            }


        }
    }


    @Override
    protected boolean specificCondition(File file) {
        boolean result;

        if (((file.length())>(lowerBound*MULTIPLY_TO_KBYES))&&(file.length()<upperBound*MULTIPLY_TO_KBYES)){
            result = true;

        }else{
            result =false;
        }

        return changeToNot(result,notSuffix);

    }
}
