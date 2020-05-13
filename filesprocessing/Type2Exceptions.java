package filesprocessing;

/**
 * wrapper class the wrap each exception that is from Type2 as instructed ,
 * this exception is stop/crash the program during its running.
 */
public class Type2Exceptions extends Exception {
    public static final long serialVersionUID = 1L;
    public Type2Exceptions(Throwable cause){
        super("ERROR: "+cause.getMessage()+"\n", cause);
    }
}
