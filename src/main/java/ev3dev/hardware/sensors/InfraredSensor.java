package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class InfraredSensor extends Sensor {

	private boolean autoSwitchMode = true;
	
	public InfraredSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.INFRARED_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a InfraredSensor instance if the port isn't connected a infrared sensor!");
		}
	}

	public int getProximity() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_INFRARED_SENSOR_PROXIMITY_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_INFRARED_SENSOR_PROXIMITY_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_INFRARED_SENSOR_PROXIMITY_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_INFRARED_SENSOR_PROXIMITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}
	
	public boolean getAutoSwitchMode(){
		return autoSwitchMode;
	}
}
