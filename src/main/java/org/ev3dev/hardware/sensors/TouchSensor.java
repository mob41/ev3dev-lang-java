package org.ev3dev.hardware.sensors;

import java.io.IOException;

import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;

public class TouchSensor extends Sensor {
	
	/**
	 * Sysfs class TouchSensor required mode
	 */
	public static final String SYSFS_REQUIRED_MODE = "TOUCH";
	
	/**
	 * Sysfs class TouchSensor Value Index
	 */
	public static final int SYSFS_VALUE_INDEX = 0;
	
	/**
	 * This device's default driver name (EV3 Touch Sensor)
	 */
	public static final String DRIVER_NAME_EV3 = "lego-ev3-touch";
	
	/**
	 * This device's default driver name (NXT Touch Sensor)
	 */
	public static final String DRIVER_NAME_NXT = "lego-nxt-touch"; 
	
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
		if (!this.getDriverName().equals(DRIVER_NAME_EV3) &&
				!this.getDriverName().equals(DRIVER_NAME_NXT)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that isn't a touch sensor!");
		}
		port.getAddress();
		if (!this.getMode().equals(SYSFS_REQUIRED_MODE)){
			throw new InvalidSensorException("Can't create a TouchSensor instance that does not support: " + SYSFS_REQUIRED_MODE);
		}
	}
	
	/**
	 * A boolean indicating whether the current touch sensor is being pressed.
	 * @return The touch sensor is pressed or not.
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public boolean isPressed() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_VALUE_INDEX);
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
