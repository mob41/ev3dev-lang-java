package org.ev3dev.exception;

/***
 * This exception is thrown if something was invalid.
 * @author Anthony
 *
 */
public class EV3LibraryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EV3LibraryException(){
		super();
	}
	
	public EV3LibraryException(String message){
		super(message);
	}
	
	public EV3LibraryException(String message, Throwable cause){
		super(message, cause);
	}
	
	public EV3LibraryException(Throwable cause){
		super(cause);
	}
}
