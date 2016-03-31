package ev3dev.hardware.motors;

import java.io.IOException;

import ev3dev.exception.InvalidMotorException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;

public class MediumMotor extends Motor {
	
	private static final String driver = "lego-ev3-m-motor";

	public MediumMotor(Device device) throws InvalidPortException, IOException, InvalidMotorException {
		super(device);
		/*//TODO Probably this is incorrect. The LegoPort returns its driver name, not motor's.
		String drivername = device.getPort().getDriverName();
		if (!drivername.equals(driver)){
			throw new InvalidMotorException("It is not a MediumMotor (" + driver + "): " + drivername);
		}
		*/
	}

}
