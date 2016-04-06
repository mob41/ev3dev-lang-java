package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class LightSensor extends Sensor {
	
	public boolean autoSwitchMode = true;

	public LightSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if(!this.getDriverName().equals(Def.LIGHT_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a LightSensor instance if the port isn't connected to a light sensor!");
		}
	}
	
	public float getReflectedLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_LIGHT_SENSOR_REFLECTED_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_LIGHT_SENSOR_REFLECTED_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_LIGHT_SENSOR_REFLECTED_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_LIGHT_SENSOR_REFLECTED_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public float getAmbientLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_LIGHT_SENSOR_AMBIENT_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_LIGHT_SENSOR_AMBIENT_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_LIGHT_SENSOR_AMBIENT_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_LIGHT_SENSOR_AMBIENT_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}

	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}

}
