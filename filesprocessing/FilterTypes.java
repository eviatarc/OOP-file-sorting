package filesprocessing;

import java.io.*;


abstract class FilterTypes implements FileFilter {

    static final String YES_SUFFIX = "YES";
    static final String NO_SUFFIX = "NO";
    static final String NOT_STRING = "NOT";
    static final int MULTIPLY_TO_KBYES=1024;
    boolean noSuffix;
    boolean notSuffix;


    @Override
    public boolean accept(File file){
        if(file.isDirectory()){
            return false;
        }
        return specificCondition(file);
    }


    /**
     * an abstract function that for each filter type will suit its condition ad later will be
     * used in the accept function
     * @param file - the file this filter will check
     * @return true if the file matches the conditions, otherwise will return false
     */
    protected abstract boolean specificCondition(File file);

    /**
     * @param isNot - holds true if the filter need to return the opposite answer
     * @param answer - the anser the filter return for his condition
     * @return the opposite of the answer if isNot is truw
     */
    boolean changeToNot(boolean isNot, boolean answer){
        if(isNot){
            return !answer;
        }
        return answer;
    }


}
