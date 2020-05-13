package filesprocessing;

import java.io.*;

class Section {

    FilterTypes theChosenFilter;
    Order theChosenOrder;
//    protected ArrayList<File> passedFiles;
    File[] passedFiles;
    Order absFilter;


    /**
     * constructor for the Section class
     * @param sourceDir - the source directory as a path
     * @param theGivenFilter - filter object that is given by the section factory
     * @param theGivenOrder - order object given by the section factory
     */
    Section(File sourceDir ,FilterTypes theGivenFilter, Order theGivenOrder) throws Type1Exceptions {
        this.theChosenFilter = theGivenFilter;
        this.theChosenOrder = theGivenOrder;
        this.passedFiles = sourceDir.listFiles(theGivenFilter);
        absFilter = new Order(Order.ABS,false);
        orderThis();
    }

    /**
     * uses the order ibject to order the files
     */
    private void orderThis(){
        absFilter.orderByMe(passedFiles);
        theChosenOrder.orderByMe(passedFiles);
    }

    public String toString(){
        String result = "";
        for (int i=0;i<passedFiles.length;i++){
            result += passedFiles[i].getName()+"\n";
        }
        return result;
    }

}
