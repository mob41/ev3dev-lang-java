package ev3dev.hardware.sensors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class I2CSensor extends Sensor {
	
	private Device device;
	
	private String SENSOR_STR = null;

	public I2CSensor(Device device) throws IOException, InvalidPortException {
		super(device);
		if (!this.getDriverName().equals(PropertyDefaults.I2CSENSOR_DRIVER_NAME)){
			throw new InvalidPortException("The specified device is not a I2C sensor. (Future plan: Check device until a suitable device detected)");
		}
		this.device = device;
		SENSOR_STR = Sysclass.getHardwareName(PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME, device.getPort().getAddress());
		if (SENSOR_STR == null){
			throw new InvalidPortException("The specified device does not exist.");
		}
	}
	
	public String getFirmwareVersion() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_FIRMWARE_VERSION);
	}
	
	public int getPollMs() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_POLL_MS);
		return Integer.parseInt(str);
	}
	
	public void setPollMs(int ms) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_POLL_MS, Integer.toString(ms));
	}
}
