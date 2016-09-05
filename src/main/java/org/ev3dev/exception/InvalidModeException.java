package org.ev3dev.exception;

/***
 * This exception is thrown if your device is not using a correct mode for requested function.
 * @author Anthony
 *
 */
public class InvalidModeException extends EV3LibraryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidModeException(){
		super();
	}
	
	public InvalidModeException(String message){
		super(message);
	}
	
	public InvalidModeException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidModeException(Throwable cause){
		super(cause);
	}
}
