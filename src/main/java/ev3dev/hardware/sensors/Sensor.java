package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class Sensor {

	private Device device;
	
	private String SENSOR_STR = "sensor";
	
	private int sensornum;
	
	public Sensor(Device device) throws IOException, InvalidPortException{
		this.device = device;
		sensornum = Sysclass.getHardwareIndex(PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME, device.getPort().getAddress());
		if (sensornum == -1){
			throw new InvalidPortException("The sensor does not exist. (Future plan: Wait until a suitable device)");
		}
		SENSOR_STR = "sensor" + sensornum;
	}
	
	public String getAddress() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the sensor controller.
	 * @param command Command that suits for the sensor driver
	 */
	public void sendCommand(String command) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_COMMAND, command);
	}
	
	public String getCommandsViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_COMMANDS);
	}
	
	public String[] getCommands() throws IOException{
		String str = getCommandsViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getDecimals() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_DECIMALS);
		return Integer.parseInt(str);
	}
	
	public String getDriverName() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_DRIVER_NAME);
	}
	
	public String getMode() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_MODE);
	}
	
	public void setMode(String mode) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_MODE, mode);
	}
	
	public String getModesViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_MODES);
	}
	
	public String[] getModes() throws IOException{
		String str = getModesViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getNumValues() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_NUM_VALUES);
		return Integer.parseInt(str);
	}
	
	public String getUnits() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, PropertyDefaults.PROPERTY_UNITS);
	}
	
}
