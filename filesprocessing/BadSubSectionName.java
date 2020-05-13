package filesprocessing;

/**
 * a class that creates a exception in case the FILTER or the ORDER in each sections
 * is not wrriten as demand
 */
public class BadSubSectionName extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "Bad subsection name, should be FILTER or ORDER";
    public BadSubSectionName(){
        super(WARNING_MASSAGE);
    }
}
