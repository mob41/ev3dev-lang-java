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
	
	private int sensornum;
	
	private String SENSOR_STR = "sensor";

	public I2CSensor(Device device) throws IOException, InvalidPortException {
		super(device);
		if (!this.getDriverName().equals(PropertyDefaults.I2CSENSOR_DRIVER_NAME)){
			throw new InvalidPortException("The specified device is not a I2C sensor. (Future plan: Check device until a suitable device detected)");
		}
		this.device = device;
		sensornum = getSensorNumber(device.getPort().getAddress());
		if (sensornum == -1){
			throw new InvalidPortException("The specified device does not exist.");
		}
		SENSOR_STR = "sensor" + sensornum;
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
	
	private static int getSensorNumber(String address){
		int sensors = Sysclass.getNumbersOfSubClass(PropertyDefaults.SENSOR_CLASS_NAME);
		System.out.println("All sensors: " + sensors);
		if (sensors == -1){
			return -1;
		}
		for (int i = 0; i < sensors; i++){
			try {
				if (Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, "sensor" + i, PropertyDefaults.PROPERTY_ADDRESS).equals(address)){
					return i;
				} 
			} catch (IOException ignore){}
		}
		return -1;
	}
}
