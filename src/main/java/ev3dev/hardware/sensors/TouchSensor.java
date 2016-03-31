package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class TouchSensor extends Sensor {

	private Device device;
	
	private String address;
	
	private String SENSOR_STR = null;
	
	public TouchSensor(Device device) throws IOException, InvalidPortException {
		super(device);
		if (!this.getDriverName().equals(PropertyDefaults.TOUCH_SENSOR_DRIVER_NAME_EV3) &&
				!this.getDriverName().equals(PropertyDefaults.TOUCH_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidPortException("The specified device is not a touch sensor. (Future plan: Check device until a suitable device detected)");
		}
		address = device.getPort().getAddress();
		SENSOR_STR = Sysclass.getHardwareName(PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME, address);
		if (SENSOR_STR == null){
			throw new InvalidPortException("The specified port does not connect a Touch Sensor.");
		}
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE)){
			throw new InvalidPortException("The specified port's device does not support the required mode: " + PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE);
		}
		this.device = device;
	}
	
	public boolean isPressed() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE)){
			System.err.println("ERROR: You are not using a required mode (" + PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE + " for TOUCH SENSOR: " + this.getMode());
			return false;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_TOUCH_VALUE_INDEX);
		return str.equals("1");
	}

}
