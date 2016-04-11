package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class TouchSensor extends Sensor {
	
	public boolean autoSwitchMode = true;
	
	/**
	 * Creates a new TouchSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a TouchSensor
	 * @throws IOException If I/O goes wrong
	 */
	public TouchSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.TOUCH_SENSOR_DRIVER_NAME_EV3) &&
				!this.getDriverName().equals(Def.TOUCH_SENSOR_DRIVER_NAME_NXT)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that isn't a touch sensor!");
		}
		port.getAddress();
		if (!this.getMode().equals(Def.PROPERTY_TOUCH_REQUIRED_MODE)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that does not support: " + Def.PROPERTY_TOUCH_REQUIRED_MODE);
		}
	}
	
	/**
	 * A boolean indicating whether the current touch sensor is being pressed.
	 * @return The touch sensor is pressed or not.
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public boolean isPressed() throws IOException, InvalidModeException{
		if (!this.getMode().equals(Def.PROPERTY_TOUCH_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_TOUCH_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_TOUCH_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_TOUCH_VALUE_INDEX);
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
