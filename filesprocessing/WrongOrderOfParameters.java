package filesprocessing;

/**
 * a class that creates exception in case the argumants given to the between filter are
 * not feet the order of inputs as required.
 */
class WrongOrderOfParameters extends Exception {
    private static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "parameters should be ordered from smaller to bigger";
    public WrongOrderOfParameters() {
        super(WARNING_MASSAGE);
    }
}
