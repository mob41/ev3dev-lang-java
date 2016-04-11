package org.ev3dev.hardware.motors;

import java.io.IOException;

import org.ev3dev.exception.InvalidMotorException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Def;

public class MediumMotor extends Motor {

	public MediumMotor(LegoPort port) throws InvalidPortException, IOException, InvalidMotorException {
		super(port);
		String drivername = this.getDriverName();
		if (!drivername.equals(Def.MEDIUM_MOTOR_DRIVER_NAME)){
			throw new InvalidMotorException("It is not a MediumMotor (" + Def.MEDIUM_MOTOR_DRIVER_NAME + "): " + drivername);
		}
	}

}
