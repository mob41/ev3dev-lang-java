package org.ev3dev.exception;

/***
 * This exception is thrown if:<br>
 * <br>
 * 1. The specified port does not contain a sensor.<br>
 * 2. That sensor was invalid for the specified type.<br>
 * 3. Could not read the address of the sensor.
 * @author Anthony
 *
 */
public class InvalidSensorException extends InvalidException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidSensorException(){
		super();
	}
	
	public InvalidSensorException(String message){
		super(message);
	}
	
	public InvalidSensorException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InvalidSensorException(Throwable cause){
		super(cause);
	}
}
