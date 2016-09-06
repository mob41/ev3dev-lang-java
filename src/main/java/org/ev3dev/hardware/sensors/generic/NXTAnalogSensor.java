package org.ev3dev.hardware.sensors.generic;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.Sensor;

/**
 * Generic NXT Analog Sensor driver
 * @author Anthony
 *
 */
public class NXTAnalogSensor extends Sensor {

	/**
	 * The NXT Analog sensor driver name
	 */
	public static final String DRIVER_NAME = "nxt-analog";
	
	/**
	 * Creates a new NXT analog sensor
	 * @param port The LegoPort instance
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public NXTAnalogSensor(LegoPort port) throws EV3LibraryException{
		super(port);
		String drivername = port.getDriverName();
		if (!drivername.equals(DRIVER_NAME)){
			throw new EV3LibraryException("The port is not connected to a NXT analog sensor: " + drivername);
		}
	}
	
	

}
