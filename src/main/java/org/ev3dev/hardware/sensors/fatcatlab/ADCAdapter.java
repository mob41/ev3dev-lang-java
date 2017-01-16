package org.ev3dev.hardware.sensors.fatcatlab;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.exception.InvalidModeException;
import org.ev3dev.exception.InvalidSensorException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.Sensor;

public class ADCAdapter extends Sensor {
	
	/**
	 * Channel 1 voltage Mode
	 */
	public static final String MODE_CH1_VOLTAGE = "CH1-VOLTAGE";
	
	/**
	 * Channel 1 voltage mode Sysfs value index
	 */
	public static final int INDEX_MODE_CH1_VOLTAGE = 0;
	
	/**
	 * Channel 2 voltage Mode
	 */
	public static final String MODE_CH2_VOLTAGE = "CH2-VOLTAGE";
	
	/**
	 * Channel 2 voltage mode Sysfs value index
	 */
	public static final int INDEX_MODE_CH2_VOLTAGE = 0;
	
	/**
	 * All channels voltage Mode
	 */
	public static final String MODE_ALL_VOLTAGE = "VOLTAGE";
	
	/**
	 * All channels mode channel 1 voltage Sysfs value index
	 */
	public static final int INDEX_MODE_ALL_CH1_VOLTAGE = 0;
	
	/**
	 * All channels mode channel 2 voltage Sysfs value index
	 */
	public static final int INDEX_MODE_ALL_CH2_VOLTAGE = 1;
	
	/**
	 * The property "valueX" prefix
	 */
	public static final String PROPERTY_PREFIX = "value";
	
	/**
	 * This device's default driver name
	 */
	public static final String DRIVER_NAME = "fcl-adc";

	/**
	 * Creates a new ADCAdapter instance
	 * @param port LegoPort
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public ADCAdapter(LegoPort port) throws EV3LibraryException {
		super(port);
		if(!this.getDriverName().equals(DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a ADCAdapter instance if the port isn't connected to a ADCAdapter sensor!");
		}
	}
	
	/**
	 * Set the device mode as Channel 1 Voltage Mode
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public void setModeAsCh1() throws EV3LibraryException{
		setMode(MODE_CH1_VOLTAGE);
	}
	
	/**
	 * Set the device mode as Channel 2 Voltage Mode
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public void setModeAsCh2() throws EV3LibraryException{
		setMode(MODE_CH2_VOLTAGE);
	}
	
	/**
	 * Set the device mode as All Channels Voltage Mode
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public void setModeAsAllCh() throws EV3LibraryException{
		setMode(MODE_ALL_VOLTAGE);
	}
	
	/**
	 * Gets the voltage in millivolts from channel 1. The device mode must be <code>CH1-VOLTAGE</code> or <code>VOLTAGE</code>. Otherwise, a <code>InvalidModeException</code> will be thrown.
	 * @return millivolts in Integer
	 * @throws EV3LibraryException If the mode is invalid or I/O goes wrong
	 */
	public int getCh1Volt() throws EV3LibraryException{
		String mode = getAttribute(SYSFS_PROPERTY_MODE);
		if (mode.equals(MODE_CH1_VOLTAGE) || mode.equals(MODE_ALL_VOLTAGE)){
			String str = getAttribute(PROPERTY_PREFIX + INDEX_MODE_CH1_VOLTAGE);
			return Integer.parseInt(str);
		} else {
			throw new InvalidModeException("The Channel 1 voltage property cannot be accessed if the mode is not \"" + MODE_CH1_VOLTAGE + "\" or \"" + MODE_ALL_VOLTAGE + "\"");
		}
	}
	
	/**
	 * Gets the voltage in millivolts from channel 2. The device mode must be <code>CH2-VOLTAGE</code> or <code>VOLTAGE</code>. Otherwise, a <code>InvalidModeException</code> will be thrown.
	 * @return millivolts in Integer
	 * @throws EV3LibraryException If the mode is invalid or I/O goes wrong
	 */
	public int getCh2Volt() throws EV3LibraryException{
		String mode = getAttribute(SYSFS_PROPERTY_MODE);
		if (mode.equals(MODE_CH2_VOLTAGE) || mode.equals(MODE_ALL_VOLTAGE)){
			String str = getAttribute(PROPERTY_PREFIX + (mode.equals(MODE_ALL_VOLTAGE) ? INDEX_MODE_ALL_CH2_VOLTAGE : INDEX_MODE_CH1_VOLTAGE));
			return Integer.parseInt(str);
		} else {
			throw new InvalidModeException("The Channel 2 voltage property cannot be accessed if the mode is not \"" + MODE_CH2_VOLTAGE + "\" or \"" + MODE_ALL_VOLTAGE + "\"");
		}
	}

}
