package org.ev3dev.hardware.motors;

import java.io.IOException;

import org.ev3dev.exception.InvalidMotorException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;

public class NXTMotor extends Motor {
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "lego-nxt-motor";

	public NXTMotor(int portField) throws InvalidPortException, IOException {
		this(new LegoPort(portField));
	}

	public NXTMotor(LegoPort port) throws InvalidPortException, IOException {
		super(port);
		String drivername = port.getDriverName();
		if (!drivername.equals(DRIVER_NAME)){
			throw new InvalidMotorException("The port is not connected to a Lego NXT Motor (" + DRIVER_NAME + "): " + drivername);
		}
	}

}
