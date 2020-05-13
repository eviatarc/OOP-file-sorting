package filesprocessing;

/**
 * a class that creates an exception in case the order types that was asked not
 * much any existing filter types
 */
public class NoSuchOrder extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "no such order";
    public NoSuchOrder() {
        super(WARNING_MASSAGE);
    }
}
