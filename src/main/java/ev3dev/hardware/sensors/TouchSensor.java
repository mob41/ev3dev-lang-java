package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class TouchSensor extends Sensor {
	
	public TouchSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.TOUCH_SENSOR_DRIVER_NAME_EV3) &&
				!this.getDriverName().equals(Def.TOUCH_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that isn't a touch sensor!");
		}
		port.getAddress();
		if (!this.getMode().equals(Def.PROPERTY_TOUCH_REQUIRED_MODE)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that does not support: " + Def.PROPERTY_TOUCH_REQUIRED_MODE);
		}
	}
	
	public boolean isPressed() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_TOUCH_REQUIRED_MODE)){
			System.err.println("ERROR: You are not using a required mode (" + Def.PROPERTY_TOUCH_REQUIRED_MODE + " for TOUCH SENSOR: " + this.getMode());
			return false;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_TOUCH_VALUE_INDEX);
		return str.equals("1");
	}

}
