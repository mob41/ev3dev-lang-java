package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class TouchSensor extends Sensor {
	
	private String address;
	
	public TouchSensor(LegoPort port) throws IOException, InvalidPortException {
		super(port);
		if (!this.getDriverName().equals(PropertyDefaults.TOUCH_SENSOR_DRIVER_NAME_EV3) &&
				!this.getDriverName().equals(PropertyDefaults.TOUCH_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidPortException("Can't create a TouchSensor instance that isn't a touch sensor!");
		}
		address = port.getAddress();
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE)){
			throw new InvalidPortException("Can't create a TouchSensor instance that does not support: " + PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE);
		}
	}
	
	public boolean isPressed() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE)){
			System.err.println("ERROR: You are not using a required mode (" + PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE + " for TOUCH SENSOR: " + this.getMode());
			return false;
		}
		String str = this.getAttribute("value" + PropertyDefaults.PROPERTY_TOUCH_VALUE_INDEX);
		return str.equals("1");
	}

}
