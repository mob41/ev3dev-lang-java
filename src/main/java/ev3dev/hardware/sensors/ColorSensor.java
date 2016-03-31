package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class ColorSensor extends Sensor {
	
	private Device device;
	
	private String address;
	
	private String SENSOR_STR = null;

	public ColorSensor(Device device) throws IOException, InvalidPortException {
		super(device);
		if (!this.getDriverName().equals(PropertyDefaults.COLOR_SENSOR_DRIVER_NAME)){
			throw new InvalidPortException("The specified device is not a color sensor. (Future plan: Check device until a suitable device detected)");
		}
		address = device.getPort().getAddress();
		SENSOR_STR = Sysclass.getHardwareName(PropertyDefaults.SENSOR_CLASS_NAME, PropertyDefaults.SUB_SENSOR_CLASS_NAME, address);
	}
	
	public int getReflectedLightIntensity() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getAmbientLightIntensity() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getColor() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_COLOR_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Red() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_R_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Green() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_G_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Blue() throws IOException{
		if (!this.getMode().equals(PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = Sysclass.getProperty(PropertyDefaults.SENSOR_CLASS_NAME, SENSOR_STR, "value" + PropertyDefaults.PROPERTY_COLOR_SENSOR_RGB_B_VALUE_INDEX);
		return Integer.parseInt(str);
	}

}
