package ev3dev.exception;

/***
 * This exception is thrown if:<br>
 * <b>LegoPort</b> port is lower than 0 or higher than 7<br>
 * <b>TachoMotor</b> port number does not found.
 * @author Anthony
 *
 */
public class InvalidPortException extends InvalidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPortException(){
		super();
	}
	
	public InvalidPortException(String message){
		super(message);
	}
	
	public InvalidPortException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidPortException(Throwable cause){
		super(cause);
	}
}
