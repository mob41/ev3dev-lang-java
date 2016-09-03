package org.ev3dev.hardware.sensors;

import java.io.IOException;

import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Def;

/**
 * LEGO EV3 infrared sensor.
 * @author Anthony
 *
 */
public class InfraredSensor extends Sensor {
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "lego-ev3-ir";

	private boolean autoSwitchMode = true;
	
	/**
	 * Creates a new InfraredSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a InfraredSensor
	 * @throws IOException If I/O goes wrong
	 */
	public InfraredSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a InfraredSensor instance if the port isn't connected a infrared sensor!");
		}
	}

	/**
	 * A measurement of the distance between the sensor and the remote, as a percentage. 100% is approximately 70cm/27in.
	 * @return A measurement of the distance
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
	public boolean getAutoSwitchMode(){
		return autoSwitchMode;
	}
}
