package filesprocessing;

/**
 * a class that creates an exception in case a negative number is given when is required to
 * get a positive one
 */
public class NegativeNumber extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "positive number expected";
    public NegativeNumber() {
        super(WARNING_MASSAGE);
    }
}
