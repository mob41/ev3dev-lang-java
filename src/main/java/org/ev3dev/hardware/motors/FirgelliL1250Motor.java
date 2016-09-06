package org.ev3dev.hardware.motors;

import java.io.IOException;

import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;

public class FirgelliL1250Motor extends Motor {
	
	/**
	 * The Sysfs class's <code>count_per_m</code> property name
	 */
	public static final String SYSFS_PROPERTY_COUNT_PER_M = "count_per_m";
	
	/**
	 * The Sysfs class's <code>full_travel_count</code> property name
	 */
	public static final String SYSFS_PROPERTY_FULL_TRAVEL_COUNT = "full_travel_count";
	
	/**
	 * This Sysfs's class name prefix (e.g. <code>/sys/class/lego-sensor/sensor0</code>, and <code>sensor</code> is the class name prefix without the [N] value.)
	 */
	public static final String LINEAR_MOTOR_CLASS_NAME_PREFIX = "linear";
	
	/**
	 * The driver name for the L12 EV3 50mm by Actuonix
	 */
	public static final String DRIVER_NAME_50MM = "act-l12-ev3-50";

	public FirgelliL1250Motor(int portField) throws InvalidPortException, IOException {
		this(new LegoPort(portField));
	}

	public FirgelliL1250Motor(LegoPort port) throws InvalidPortException, IOException {
		super(port, LINEAR_MOTOR_CLASS_NAME_PREFIX);
		if (!port.getDriverName().equals(DRIVER_NAME_50MM)){
			throw new InvalidPortException("The port does not have a Firgelli L12 50 Motor driver.");
		}
	}
	
	/**
	 * Do not use this on Firgelli L12 50/100 Motors (Linear motors).<br>
	 * <code>-1</code> will be returned instead, use <code>getCountPerMetre()</code>
	 */
	@Override
	public int getCountPerRot() throws IOException{
		return -1;
	}
	
	/**
	 * Returns the number of tacho counts in one meter of travel of the motor. 
	 * Tacho counts are used by the position and speed attributes, so you can
	 *  use this value to convert from distance to tacho counts. (linear motors only)
	 * @return Counts per metre
	 * @throws IOException If I/O goes wrong
	 */
	public int getCountPerMetre() throws IOException{
		if (!this.isConnected()){
			return -1;
		}
		String countpermetre = this.getAttribute(SYSFS_PROPERTY_COUNT_PER_M);
		return Integer.parseInt(countpermetre);
	}
	
	/**
	 * Returns the number of tacho counts in the full travel of the motor. 
	 * When combined with the count_per_m attribute, you can use this value
	 *  to calculate the maximum travel distance of the motor. 
	 *  (linear motors only)
	 * @return Full Travel Count
	 * @throws IOException
	 */
	public int getFullTravelCount() throws IOException{
		if (!this.isConnected()){
			return -1;
		}
		String str = this.getAttribute(SYSFS_PROPERTY_FULL_TRAVEL_COUNT);
		return Integer.parseInt(str);
	}

}
