package filesprocessing;

import java.io.*;
import java.util.ArrayList;

public class DirectoryProcessor {
    private static final int COMMANDS_INDEX = 1;
    private static final int SOURCE_DIR_INDEX = 0;
    private static final int NUMBER_OF_ARGS = 2;

    public static void main(String[] args) throws Type1Exceptions {
        ArrayList<Section> toPrintListOfSections;
        try {
            checkValidArgs(args);
            toPrintListOfSections = SectionFactory.factoryOfSections(args[SOURCE_DIR_INDEX],
                    args[COMMANDS_INDEX]);
            for(int i=0;i<toPrintListOfSections.size();i++){
                System.out.println(toPrintListOfSections.get(i));
            }

        } catch (Type2Exceptions exceptions){
            System.err.println(exceptions.getMessage());
            System.exit(1);
        }
    }

    private static void checkValidArgs(String[] args) throws Type2Exceptions{
        if (args.length!=NUMBER_OF_ARGS || !(new File(args[SOURCE_DIR_INDEX]).isDirectory()||!
                (new File(args[COMMANDS_INDEX]).isFile()))){
            throw new Type2Exceptions(new InvalidUsage());
        }
    }
}

