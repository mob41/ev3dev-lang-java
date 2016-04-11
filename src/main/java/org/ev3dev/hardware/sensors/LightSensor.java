package org.ev3dev.hardware.sensors;

import java.io.IOException;

import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Def;

/**
 * LEGO NXT Light Sensor
 * @author Anthony
 *
 */
public class LightSensor extends Sensor {
	
	public boolean autoSwitchMode = true;

	/**
	 * Creates a new LightSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a LightSensor
	 * @throws IOException If I/O goes wrong
	 */
	public LightSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if(!this.getDriverName().equals(Def.LIGHT_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a LightSensor instance if the port isn't connected to a light sensor!");
		}
	}
	
	/**
	 * A measurement of the reflected light intensity, as a percentage.
	 * @return A measurement of the reflected light intensity
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
	
	/**
	 * A measurement of the ambient light intensity, as a percentage.
	 * @return A measurement of the ambient light intensity
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
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
