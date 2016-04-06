package ev3dev.exception;

/***
 * This exception is thrown if something was invalid.
 * @author Anthony
 *
 */
public class InvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidException(){
		super();
	}
	
	public InvalidException(String message){
		super(message);
	}
	
	public InvalidException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidException(Throwable cause){
		super(cause);
	}
}
