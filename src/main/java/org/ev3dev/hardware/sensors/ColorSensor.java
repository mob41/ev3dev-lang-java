package org.ev3dev.hardware.sensors;

import java.io.IOException;

import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;

/**
 * LEGO EV3 color sensor.
 * @author Anthony
 *
 */
public class ColorSensor extends Sensor {
	
	/**
	 * Reflected Light Intensity required Sysfs mode
	 */
	public static final String SYSFS_REFLECTED_LIGHT_INTENSITY_MODE = "COL-REFLECT";
	
	/**
	 * Reflected Light Intensity Sysfs value index
	 */
	public static final int SYSFS_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX = 0;
	
	/**
	 * Ambient Light Intensity required Sysfs mode
	 */
	public static final String SYSFS_AMBIENT_LIGHT_INTENSITY_MODE = "COL-AMBIENT";
	
	/**
	 * Ambient Light Intensity Sysfs value index
	 */
	public static final int SYSFS_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX = 0;
	
	/**
	 * Color required Sysfs mode
	 */
	public static final String SYSFS_COLOR_MODE = "COL-COLOR";
	
	/**
	 * Color Sysfs value index
	 */
	public static final int SYSFS_COLOR_VALUE_INDEX = 0;
	
	/**
	 * RGB required Sysfs mode
	 */
	public static final String SYSFS_RGB_MODE = "RGB-RAW";
	
	/**
	 * RGB Red Sysfs value index
	 */
	public static final int SYSFS_RGB_R_VALUE_INDEX = 0;
	
	/**
	 * RGB Green Sysfs value index
	 */
	public static final int SYSFS_RGB_G_VALUE_INDEX = 1;
	
	/**
	 * RGB Blue Sysfs value index
	 */
	public static final int SYSFS_RGB_B_VALUE_INDEX = 2;
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "lego-ev3-color";
	
	private boolean autoSwitchMode = true;
	
	/**
	 * Creates a new ColorSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a ColorSensor
	 * @throws IOException If I/O goes wrong
	 */
	public ColorSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!DRIVER_NAME.equals(this.getDriverName())){
			throw new InvalidSensorException("The specified device is not a color sensor.");
		}
		port.getAddress();
	}
	
	/**
	 * Reflected light intensity as a percentage. Light on sensor is red.
	 * @return Reflected Light Intensity in percentage
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getReflectedLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_REFLECTED_LIGHT_INTENSITY_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_REFLECTED_LIGHT_INTENSITY_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_REFLECTED_LIGHT_INTENSITY_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Ambient light intensity. Light on sensor is dimly lit blue.
	 * @return Ambient light intensity in percentage
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getAmbientLightIntensity() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_AMBIENT_LIGHT_INTENSITY_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_AMBIENT_LIGHT_INTENSITY_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_AMBIENT_LIGHT_INTENSITY_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Color detected by the sensor, categorized by overall value. <br>
	 * - 0: No color<br>
	 * - 1: Black<br>
	 * - 2: Blue<br>
	 * - 3: Green<br>
	 * - 4: Yellow<br>
	 * - 5: Red<br>
	 * - 6: White<br>
	 * - 7: Brown
	 * @return Color value
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getColor() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_COLOR_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_COLOR_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_COLOR_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_COLOR_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Red component of the detected color, in the range 0-1020
	 * @return RGB Red component
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getRGB_Red() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_RGB_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_RGB_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_RGB_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_RGB_R_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Green component of the detected color, in the range 0-1020
	 * @return Green Red component
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getRGB_Green() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_RGB_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_RGB_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_RGB_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_RGB_G_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Blue component of the detected color, in the range 0-1020
	 * @return Blue Red component
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidModeException The mode selected wasn't valid, or <b>Auto Switch Mode</b> has disabled.
	 */
	public int getRGB_Blue() throws IOException, InvalidModeException{
		if (!this.getMode().equals(SYSFS_RGB_MODE)){
			if (autoSwitchMode){
				this.setMode(SYSFS_RGB_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + SYSFS_RGB_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + SYSFS_RGB_B_VALUE_INDEX);
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
