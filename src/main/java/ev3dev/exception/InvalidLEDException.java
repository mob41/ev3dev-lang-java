package ev3dev.exception;

/***
 * This exception is thrown if:<br>
 * <b>LED</b> specified LED does not exist.
 * @author Anthony
 *
 */
public class InvalidLEDException extends InvalidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidLEDException(){
		super();
	}
	
	public InvalidLEDException(String message){
		super(message);
	}
	
	public InvalidLEDException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidLEDException(Throwable cause){
		super(cause);
	}
}
