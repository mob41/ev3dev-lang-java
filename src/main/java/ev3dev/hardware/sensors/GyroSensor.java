package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

/**
 * LEGO EV3 gyro sensor.
 * @author Anthony
 *
 */
public class GyroSensor extends Sensor {
	
	private boolean autoSwitchMode = true;

	/**
	 * Creates a new GyroSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a GyroSensor
	 * @throws IOException If I/O goes wrong
	 */
	public GyroSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.GYRO_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a GyroSensor instance if port isn't connected to a GyroSensor!");
		}
	}
	
	/**
	 * The number of degrees that the sensor has been rotated since it was put into this mode.
	 * @return The number of degrees
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getAngle() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_GYRO_SENSOR_ANGLE_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * The rate at which the sensor is rotating, in degrees/second.
	 * @return The rate at which the sensor is rotating
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getRate() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_GYRO_SENSOR_RATE_VALUE_INDEX);
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
