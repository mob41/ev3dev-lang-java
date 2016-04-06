package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class UltrasonicSensor extends Sensor {
	
	public boolean autoSwitchMode = true;

	public UltrasonicSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		String driverName = this.getDriverName();
		if (!driverName.equals(Def.ULTRASONIC_SENSOR_DRIVER_NAME_EV3) && 
				!driverName.equals(Def.ULTRASONIC_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidSensorException("Can't create a UltrasonicSensor instance if it is not a ultrasonic sensor! Yours: " + driverName);
		}
	}

	public float getDistanceCentimeters() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_ULTRASONIC_SENSOR_CM_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_ULTRASONIC_SENSOR_CM_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_ULTRASONIC_SENSOR_CM_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_ULTRASONIC_SENSOR_CM_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public float getDistanceInches() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_ULTRASONIC_SENSOR_IN_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_ULTRASONIC_SENSOR_IN_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_ULTRASONIC_SENSOR_IN_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_ULTRASONIC_SENSOR_IN_VALUE_INDEX);
		return Float.parseFloat(str);
	}
	
	public boolean isOtherSensorPresent() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_ULTRASONIC_SENSOR_OTHER_PRESENT_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_ULTRASONIC_SENSOR_OTHER_PRESENT_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_ULTRASONIC_SENSOR_OTHER_PRESENT_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_ULTRASONIC_SENSOR_OTHER_PRESENT_VALUE_INDEX);
		return str.equals("1");
	}
	
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}

	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}
}
