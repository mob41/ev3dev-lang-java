package ev3dev.hardware.motors;

import java.io.IOException;

import ev3dev.exception.InvalidMotorException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class MediumMotor extends Motor {

	public MediumMotor(LegoPort port) throws InvalidPortException, IOException, InvalidMotorException {
		super(port);
		String drivername = this.getDriverName();
		if (!drivername.equals(Def.MEDIUM_MOTOR_DRIVER_NAME)){
			throw new InvalidMotorException("It is not a MediumMotor (" + Def.MEDIUM_MOTOR_DRIVER_NAME + "): " + drivername);
		}
	}

}
