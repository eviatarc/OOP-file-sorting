package filesprocessing;

/**
 * a class that creates an exception in case the filter types that was asked not
 * much any existing filter types
 */
public class NoSuchFilter extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "no such filter";
    public NoSuchFilter() {
        super(WARNING_MASSAGE);
    }

}
