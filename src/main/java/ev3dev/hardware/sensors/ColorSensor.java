package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class ColorSensor extends Sensor {
	
	private Device device;
	
	private boolean autoSwitchMode = true;
	
	public ColorSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.COLOR_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("The specified device is not a color sensor.");
		}
		device.getPort().getAddress();
	}
	
	public int getReflectedLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getAmbientLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getColor() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_COLOR_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Red() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_R_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Green() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_G_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRGB_Blue() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_COLOR_SENSOR_RGB_B_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}

	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}

}
