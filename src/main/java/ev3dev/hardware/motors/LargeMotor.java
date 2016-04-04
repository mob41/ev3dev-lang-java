package ev3dev.hardware.motors;

import java.io.IOException;

import ev3dev.exception.InvalidMotorException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;

public class LargeMotor extends Motor {
	
	private static final String driver = "lego-ev3-l-motor";

	public LargeMotor(LegoPort port) throws InvalidPortException, IOException, InvalidMotorException {
		super(port);
		/*
		String drivername = this.getDriverName();
		if (!drivername.equals(PropertyDefaults.LARGE_MOTOR_DRIVER_NAME)){
			throw new InvalidMotorException("It is not a LargeMotor (" + PropertyDefaults.LARGE_MOTOR_DRIVER_NAME + "): " + drivername);
		}
		*/
	}

}
