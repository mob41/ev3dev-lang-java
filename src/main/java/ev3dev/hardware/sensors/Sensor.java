package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class Sensor extends Device{

	private LegoPort port;
	
	public Sensor(LegoPort port) throws IOException, InvalidPortException{
		super(port, PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME);
		this.port = port;
	}
	
	public String getAddress() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the sensor controller.
	 * @param command Command that suits for the sensor driver
	 */
	public void sendCommand(String command) throws IOException{
		this.setAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_COMMAND, command);
	}
	
	public String getCommandsViaString() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_COMMANDS);
	}
	
	public String[] getCommands() throws IOException{
		String str = getCommandsViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getDecimals() throws IOException{
		String str = this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_DECIMALS);
		return Integer.parseInt(str);
	}
	
	public String getDriverName() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_DRIVER_NAME);
	}
	
	public String getMode() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_MODE);
	}
	
	public void setMode(String mode) throws IOException{
		this.setAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_MODE, mode);
	}
	
	public String getModesViaString() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_MODES);
	}
	
	public String[] getModes() throws IOException{
		String str = getModesViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getNumValues() throws IOException{
		String str = this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_NUM_VALUES);
		return Integer.parseInt(str);
	}
	
	public String getUnits() throws IOException{
		return this.getAttribute(PropertyDefaults.SENSOR_CLASS_NAME, this.getSubClassName(), PropertyDefaults.PROPERTY_UNITS);
	}
	
}
