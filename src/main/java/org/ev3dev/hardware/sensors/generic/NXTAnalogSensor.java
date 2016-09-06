package org.ev3dev.hardware.sensors.generic;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.Sensor;

public class NXTAnalogSensor extends Sensor {

	/**
	 * The NXT Analog sensor driver name
	 */
	public static final String DRIVER_NAME = "nxt-analog";
	
	public NXTAnalogSensor(LegoPort port) throws EV3LibraryException, InvalidPortException {
		super(port);
		String drivername = port.getDriverName();
		if (!drivername.equals(DRIVER_NAME)){
			throw new EV3LibraryException("The port is not connected to a NXT analog sensor: " + drivername);
		}
	}

}
