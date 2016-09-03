package org.ev3dev.hardware.motors;

import java.io.IOException;

import org.ev3dev.exception.InvalidMotorException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;

public class LargeMotor extends Motor {
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "lego-ev3-l-motor";
	
	public LargeMotor(LegoPort port) throws InvalidPortException, IOException, InvalidMotorException {
		super(port);
		String drivername = this.getDriverName();
		if (!drivername.equals(DRIVER_NAME)){
			throw new InvalidMotorException("It is not a LargeMotor (" + DRIVER_NAME + "): " + drivername);
		}
	}

}
