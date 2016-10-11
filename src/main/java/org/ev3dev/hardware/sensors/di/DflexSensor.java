package org.ev3dev.hardware.sensors.di;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.generic.NXTAnalogSensor;

public class DflexSensor extends NXTAnalogSensor {
	
	/**
	 * <code>FLEX</code> Mode - Flex
	 */
	public static final String MODE_FLEX = "FLEX";
	
	/**
	 * <code>FLEX</code> Mode - Flex Sysfs value index
	 */
	public static final int MODE_FLEX_VALUE_INDEX = 0;
	
	/**
	 * dFlex Sensor driver name
	 */
	public static final String DRIVER_NAME = "di-dflex";

	/**
	 * Creates a DflexSensor instance.
	 * @param port The LegoPort instance
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public DflexSensor(LegoPort port) throws EV3LibraryException {
		super(port, DRIVER_NAME);
	}
	
	/**
	 * Flex
	 * @return an integer from 0-100
	 */
	public int getFlex(){
		return Integer.parseInt(this.getAttribute("value" + MODE_FLEX_VALUE_INDEX));
	}

}
