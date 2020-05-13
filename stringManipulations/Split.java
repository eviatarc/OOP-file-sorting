package stringManipulations;

import java.util.ArrayList;

/**
 * class that hold a static method that manipulate strings
 */
public class Split {

    /**
     * a static method that by a given string and a char it splits the strings when the
     * saperator is the given character and insert all of the parts into an array
     * @param givenStringToAnalyze - the string that is needed to cut to pieces into the array
     * @param saperator - the char/sign that the string needed to be cutted by
     * @return array list the holds the cutted array
     */
    static public ArrayList<String> tearTheStringApart(String givenStringToAnalyze , char saperator){
        char saperetBy = saperator;
        ArrayList<String> resultArray = new ArrayList<>();
        int[] towIndexToHoldForFinish = new int[2];
        for (int i=0,j=0; i<givenStringToAnalyze.length();i++){
            char tempChar = givenStringToAnalyze.charAt(i);
            if (tempChar== saperetBy){
                resultArray.add(givenStringToAnalyze.substring(j,i));
                i++;
                j=i;
            }
            towIndexToHoldForFinish[0] = j;
            towIndexToHoldForFinish[1]= i;
        }
        towIndexToHoldForFinish[1]+=1;
        // adding the final string in the given one to the result array - because its running in an inner
        // scope it required to use them out side in a special way
        resultArray.add(givenStringToAnalyze.substring(towIndexToHoldForFinish[0],
                towIndexToHoldForFinish[1]));
        return resultArray;
    }
}
