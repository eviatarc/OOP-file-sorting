package filesprocessing;

/**
 * wrapper class the wrap each exception that is from Type1 as instructed ,
 * this exception is just a warnning and dont stop or crash the program during its running.
 */
public class Type1Exceptions extends Exception {
    public static final long serialVersionUID = 1L;
    private static final String WARNING_MASSAGE = "Warning in line ";
//    public Type1Exceptions(int line){
//        super(WARNING_MASSAGE+Integer.toString(line));
//    }
    public Type1Exceptions(Throwable cause){
        super(WARNING_MASSAGE, cause);
    }

}
