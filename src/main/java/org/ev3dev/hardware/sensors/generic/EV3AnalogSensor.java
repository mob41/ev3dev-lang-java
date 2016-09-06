package org.ev3dev.hardware.sensors.generic;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.Sensor;

/**
 * Generic NXT Analog Sensor driver
 * @author Anthony
 *
 */
public class EV3AnalogSensor extends Sensor {

	/**
	 * The EV3 Analog sensor driver name
	 */
	public static final String DRIVER_NAME = "ev3-analog";
	
	public EV3AnalogSensor(LegoPort port, String typeId) throws EV3LibraryException, InvalidPortException {
		super(port);
		String drivername = port.getDriverName();
		if (!drivername.equals(DRIVER_NAME + "-" + typeId)){
			throw new EV3LibraryException("The port is not connected to a EV3 analog sensor with type id \"" + typeId + "\": " + drivername);
		}
	}

}
