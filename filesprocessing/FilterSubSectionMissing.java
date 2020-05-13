package filesprocessing;

/**
 * a class that creates an exception in case a sub-section in the command file is missing
 */
public class FilterSubSectionMissing extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "FILTER sub-section missing";
    public FilterSubSectionMissing(){
        super(WARNING_MASSAGE);
    }
}
