package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class SoundSensor extends Sensor {
	
	public boolean autoSwitchMode = true;

	public SoundSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if(!this.getDriverName().equals(Def.SOUND_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a SoundSensor instance if the port isn't connected to a sound sensor!");
		}
	}
	
	public float getSoundPressure() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public float getSoundPressureLow() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}

	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}

}
