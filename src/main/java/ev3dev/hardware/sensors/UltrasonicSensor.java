package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class UltrasonicSensor extends Sensor {
	
	public boolean autoSwitchMode = true;

	/**
	 * Creates a new UltrasonicSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a UltrasonicSensor
	 * @throws IOException If I/O goes wrong
	 */
	public UltrasonicSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		String driverName = this.getDriverName();
		if (!driverName.equals(Def.ULTRASONIC_SENSOR_DRIVER_NAME_EV3) && 
				!driverName.equals(Def.ULTRASONIC_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidSensorException("Can't create a UltrasonicSensor instance if it is not a ultrasonic sensor! Yours: " + driverName);
		}
	}

	/**
	 * Measurement of the distance detected by the sensor, in centimeters.
	 * @return The distance in centimeters
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
	
	/**
	 * Measurement of the distance detected by the sensor, in inches.
	 * @return The distance in inches.
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
	
	/**
	 * Value indicating whether another ultrasonic sensor could be heard nearby.
	 * @return Boolean
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
	
	/**
	 * Set Auto Switch Mode to be enabled or disabled.<br>
	 * (Default: enabled)
	 * @param autoswitch A Boolean
	 */
	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}

	/**
	 * Get whether Auto Switch Mode is enabled or disabled.
	 * @return A Boolean
	 */
	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}
}
