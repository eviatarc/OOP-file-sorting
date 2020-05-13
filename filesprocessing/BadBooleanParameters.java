package filesprocessing;

/**
 * class that create exception in the case the boolean argumant is different than expected
 */
public class BadBooleanParameters extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "bad boolean parameters. should be YES or NO ";
    public BadBooleanParameters() {
        super(WARNING_MASSAGE);
    }
}
