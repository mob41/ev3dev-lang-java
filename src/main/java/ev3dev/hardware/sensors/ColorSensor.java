package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;
import ev3dev.io.Sysclass;

public class ColorSensor extends Sensor {
	
	private Device device;
	
	private String address;

	public ColorSensor(LegoPort port) throws IOException, InvalidPortException {
		super(port);
		if (!this.getDriverName().equals(Def.COLOR_SENSOR_DRIVER_NAME)){
			throw new InvalidPortException("The specified device is not a color sensor. (Future plan: Check device until a suitable device detected)");
		}
		address = device.getPort().getAddress();
	}
	
	public int getReflectedLightIntensity() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getAmbientLightIntensity() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getColor() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_COLOR_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Red() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_R_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Green() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_G_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Blue() throws IOException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE)){
			System.err.println("Error: You are not using a correct mode.");
			return -1;
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_B_VALUE_INDEX);
		return Integer.parseInt(str);
	}

}
