package ev3dev.hardware.motors;

import java.io.IOException;

import ev3dev.exception.InvalidMotorException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;

public class LargeMotor extends Motor {
	
	private static final String driver = "lego-ev3-l-motor";

	public LargeMotor(Device device) throws InvalidPortException, IOException, InvalidMotorException {
		super(device);
		String drivername = device.getPort().getDriverName();
		if (!drivername.equals(driver)){
			throw new InvalidMotorException("It is not a LargeMotor (" + driver + "): " + drivername);
		}
	}

}
