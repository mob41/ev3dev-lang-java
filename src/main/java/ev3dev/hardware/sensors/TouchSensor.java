package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class TouchSensor extends Sensor {

	private Device device;
	
	private String address;
	
	private int sensornum;
	
	private String SENSOR_STR = "sensor";
	
	public TouchSensor(Device device) throws IOException, InvalidPortException {
		super(device);
		address = device.getPort().getAddress();
		sensornum = Sysclass.getHardwareIndex(PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME, address);
		if (sensornum == -1){
			throw new InvalidPortException("The specified port does not connect a Touch Sensor.");
		}
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE)){
			throw new InvalidPortException("The specified port's device does not support the required mode: " + PropertyDefaults.PROPERTY_TOUCH_REQUIRED_MODE);
		}
		this.device = device;
		SENSOR_STR = "sensor" + sensornum;
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
