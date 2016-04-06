package ev3dev.exception;

/***
 * This exception is thrown if:<br>
 * <br>
 * The specified button does not exist, or you are not using the <b>int</b> fields.
 * @author Anthony
 *
 */
public class InvalidButtonException extends InvalidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidButtonException(){
		super();
	}
	
	public InvalidButtonException(String message){
		super(message);
	}
	
	public InvalidButtonException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidButtonException(Throwable cause){
		super(cause);
	}
}
