package org.ev3dev.hardware;

import java.io.IOException;

import org.ev3dev.io.Def;
import org.ev3dev.io.Sysfs;

/***
 * A generic interface to read data from the system¡¦s power_supply class. Uses the built-in legoev3-battery if none is specified.
 * @author Anthony
 *
 */
public class PowerSupply{
	
	/***
	 * The measured current that the battery is supplying (in microamps)
	 * @return Measured Current
	 * @throws IOException If I/O goes wrong
	 */
	public static int getMeasuredCurrent() throws IOException{
		String str = Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_MEASURED_CURRENT);
		return Integer.parseInt(str);
	}
	
	/***
	 * The measured voltage that the battery is supplying (in microvolts)
	 * @return Measured Voltage
	 * @throws IOException If I/O goes wrong
	 */
	public static int getMeasuredVoltage() throws IOException{
		String str = Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_MEASURED_VOLTAGE);
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the maximum voltage
	 * @return Maximum Voltage
	 * @throws IOException If I/O goes wrong
	 */
	public static int getMaxVoltage() throws IOException{
		String str = Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_MAX_VOLTAGE);
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the minimum voltage
	 * @return Minimum Voltage
	 * @throws IOException If I/O goes wrong
	 */
	public static int getMinVoltage() throws IOException{
		String str = Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_MIN_VOLTAGE);
		return Integer.parseInt(str);
	}
	
	/***
	 * Get the technology of this power supply
	 * @return String
	 * @throws IOException If I/O goes wrong
	 */
	public static String getTechnology() throws IOException{
		return Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_TECHNOLOGY);
	}
	
	/***
	 * Get the type of this power supply
	 * @return String
	 * @throws IOException If I/O goes wrong
	 */
	public static String getType() throws IOException{
		return Sysfs.getAttribute(Def.POWER_SUPPLY_CLASS_NAME, Def.PROPERTY_TYPE);
	}
}
