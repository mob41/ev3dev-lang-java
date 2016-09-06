package org.ev3dev.hardware.sensors;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;

/**
 * LEGO EV3 gyro sensor.
 * @author Anthony
 *
 */
public class GyroSensor extends Sensor {
	
	/**
	 * Gyro angle required Sysfs mode
	 */
	public static final String SYSFS_ANGLE_MODE = "GYRO-ANG";
	
	/**
	 * Gyro angle Sysfs value index
	 */
	public static final int SYSFS_ANGLE_VALUE_INDEX = 0;
	
	/**
	 * Gyro rate required Sysfs mode
	 */
	public static final String SYSFS_RATE_MODE = "GYRO-RATE";
	
	/**
	 * Gyro angle Sysfs value index
	 */
	public static final int SYSFS_RATE_VALUE_INDEX = 0;
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "lego-ev3-gyro";
	
	private boolean autoSwitchMode = true;

	/**
	 * Creates a new GyroSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a GyroSensor
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public GyroSensor(LegoPort port) throws EV3LibraryException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a GyroSensor instance if port isn't connected to a GyroSensor!");
		}
	}
	
	/**
	 * The number of degrees that the sensor has been rotated since it was put into this mode.
	 * @return The number of degrees
	 * @throws EV3LibraryException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getAngle() throws InvalidModeException, EV3LibraryException{
		if (!this.getMode().equals(SYSFS_ANGLE_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_ANGLE_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_ANGLE_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_ANGLE_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * The rate at which the sensor is rotating, in degrees/second.
	 * @return The rate at which the sensor is rotating
	 * @throws EV3LibraryException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getRate() throws InvalidModeException, EV3LibraryException{
		if (!this.getMode().equals(SYSFS_RATE_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_RATE_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_RATE_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_RATE_VALUE_INDEX);
		return Integer.parseInt(str);
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
