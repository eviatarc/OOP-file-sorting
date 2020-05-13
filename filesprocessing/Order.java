package filesprocessing;

import java.io.*;
import java.util.*;
import java.lang.*;
import stringManipulations.*;

/**
 * class of orders
 */
public class Order {

    private final String orderType;
    private final boolean isReverse;
    private  Comparator<File> sortKey;

    static final String ABS = "abs";
    static final String TYPE = "type";
    static final String SIZE = "size";

    private static final char SAPERATOR_BY_DOT = '.';


    /**
     * constructoor for an order
     * @param orderType - the type of order that is required
     * @param isReverse - is the wanted order is the reversed order type
     * @throws Type1Exceptions
     */
    Order(String orderType, boolean isReverse) throws Type1Exceptions {
//        this.fileArray = fileArray;
        this.orderType = orderType;
        this.isReverse = isReverse;
        setSortKey();
    }

    /**
     * a function that order the given files
     * @param fileArray the array that is needed to order
     */
    void orderByMe(File[] fileArray){
        if (isReverse){
            Arrays.sort(fileArray,sortKey.reversed());
        } else {
            Arrays.sort(fileArray,sortKey);
        }



    }

    /**
     * factory of order by types
     * @throws Type1Exceptions - type1 exception
     */
    private void setSortKey() throws Type1Exceptions {
        switch (orderType){
            case(ABS):
                sortKey = (file1, file2) -> file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
                break;
            case (TYPE):
                sortKey = (file1,file2) -> getTheTypeOfFile(file1).compareTo(getTheTypeOfFile(file2));
                break;
            case (SIZE):
                sortKey = Comparator.comparing(File::length);
                break;
            default:
                throw new Type1Exceptions(new NoSuchOrder());
        }
    }

    /**
     * function that uses the split to analize the type (the suffix) of a given file
     * @param file the file need to get its type
     * @return the last cell of the given string after cutted into an array - the last string is the suffix
     */
    private String getTheTypeOfFile(File file){
        ArrayList<String> theCuttedStringInArray = Split.tearTheStringApart(file.getName(),SAPERATOR_BY_DOT);
        int lengthOfArray = theCuttedStringInArray.size();
        if(lengthOfArray==1){
            return "";
        }
        return theCuttedStringInArray.get(lengthOfArray-1);
    }

    }


