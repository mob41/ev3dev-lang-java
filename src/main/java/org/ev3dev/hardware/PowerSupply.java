package org.ev3dev.hardware;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.io.Sysfs;

/***
 * A generic interface to read data from the system¡¦s power_supply class. Uses the built-in legoev3-battery if none is specified.
 * @author Anthony
 *
 */
public class PowerSupply{
	
	/**
	 * The Sysfs class's <code>measured_current</code> property name
	 */
	public static final String SYSFS_MEASURED_CURRENT = "measured_current";
	
	/**
	 * The Sysfs class's <code>measured_voltage</code> property name
	 */
	public static final String SYSFS_MEASURED_VOLTAGE = "measured_voltage";
	
	/**
	 * The Sysfs class's <code>max_voltage</code> property name
	 */
	public static final String SYSFS_MAX_VOLTAGE = "max_voltage";
	
	/**
	 * The Sysfs class's <code>min_voltage</code> property name
	 */
	public static final String SYSFS_MIN_VOLTAGE = "min_voltage";
	
	/**
	 * The Sysfs class's <code>technology</code> property name
	 */
	public static final String SYSFS_TECHNOLOGY = "technology";
	
	/**
	 * The Sysfs class's <code>type</code> property name
	 */
	public static final String SYSFS_TYPE = "type";
	
	/**
	 * This Sysfs's class name (e.g. <code>/sys/class/lego-sensor</code>, and <code>lego-sensor</code> is the class name)
	 */
	public static final String POWER_SUPPLY_CLASS_NAME = "power_supply";
	
	/***
	 * The measured current that the battery is supplying (in microamps)
	 * @return Measured Current
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static int getMeasuredCurrent() throws EV3LibraryException{
		String str;
		try {
			str = Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_MEASURED_CURRENT);
		} catch (IOException e) {
			throw new EV3LibraryException("Get measured current attribute failed", e);
		}
		return Integer.parseInt(str);
	}
	
	/***
	 * The measured voltage that the battery is supplying (in microvolts)
	 * @return Measured Voltage
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static int getMeasuredVoltage() throws EV3LibraryException{
		String str;
		try {
			str = Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_MEASURED_VOLTAGE);
		} catch (IOException e) {
			throw new EV3LibraryException("Get measured voltage attribute failed", e);
		}
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the maximum voltage
	 * @return Maximum Voltage
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static int getMaxVoltage() throws EV3LibraryException{
		String str;
		try {
			str = Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_MAX_VOLTAGE);
		} catch (IOException e) {
			throw new EV3LibraryException("Get max voltage attribute failed", e);
		}
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the minimum voltage
	 * @return Minimum Voltage
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static int getMinVoltage() throws EV3LibraryException{
		String str;
		try {
			str = Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_MIN_VOLTAGE);
		} catch (IOException e) {
			throw new EV3LibraryException("Get min voltage attribute failed", e);
		}
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the technology of this power supply
	 * @return String
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static String getTechnology() throws EV3LibraryException{
		try {
			return Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_TECHNOLOGY);
		} catch (IOException e) {
			throw new EV3LibraryException("Get technology attribute failed", e);
		}
	}
	
	/***
	 * Get the type of this power supply
	 * @return String
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public static String getType() throws EV3LibraryException{
		try {
			return Sysfs.getAttribute(POWER_SUPPLY_CLASS_NAME, SYSFS_TYPE);
		} catch (IOException e) {
			throw new EV3LibraryException("Get type attribute failed", e);
		}
	}
}
