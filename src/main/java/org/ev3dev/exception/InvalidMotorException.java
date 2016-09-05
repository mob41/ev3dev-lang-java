package org.ev3dev.exception;

/***
 * This exception is thrown if:<br>
 * <b>LargeMotor</b> selected port's driver isn't LargeMotor's driver.<br>
 * <b>MediumMotor</b> selected port's driver isn't MediumMotor's driver.
 * @author Anthony
 *
 */
public class InvalidMotorException extends EV3LibraryException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMotorException(){
		super();
	}
	
	public InvalidMotorException(String message){
		super(message);
	}
	
	public InvalidMotorException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidMotorException(Throwable cause){
		super(cause);
	}
}
