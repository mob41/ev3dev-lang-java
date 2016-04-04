package ev3dev.hardware.sensors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class I2CSensor extends Sensor {

	public I2CSensor(LegoPort port) throws IOException, InvalidPortException {
		super(port);
		if (!this.getDriverName().equals(PropertyDefaults.I2CSENSOR_DRIVER_NAME)){
			throw new InvalidPortException("The specified device is not a I2C sensor. (Future plan: Check device until a suitable device detected)");
		}
	}
	
	public String getFirmwareVersion() throws IOException{
		return this.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_FIRMWARE_VERSION);
	}
	
	public int getPollMs() throws IOException{
		String str = this.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_POLL_MS);
		return Integer.parseInt(str);
	}
	
	public void setPollMs(int ms) throws IOException{
		this.setProperty(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_POLL_MS, Integer.toString(ms));
	}
}
