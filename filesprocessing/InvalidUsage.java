package filesprocessing;

/**
 * a class that creates exception in case the argumants given to the program are not feet the
 * order of inputs as required
 */
public class InvalidUsage extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "invalid parameters. the first parameter should be Source" +
            " Directory and the second Commands File";
    public InvalidUsage(){
        super(WARNING_MASSAGE);
    }
}
