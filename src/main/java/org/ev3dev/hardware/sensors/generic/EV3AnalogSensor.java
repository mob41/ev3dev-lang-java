package org.ev3dev.hardware.sensors.generic;

import org.ev3dev.exception.EV3LibraryException;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.hardware.sensors.Sensor;

/**
 * Generic EV3 Analog Sensor driver
 * @author Anthony
 *
 */
public class EV3AnalogSensor extends Sensor {

	/**
	 * The EV3 Analog sensor driver name
	 */
	public static final String DRIVER_NAME = "ev3-analog";
	
	/**
	 * Creates a new EV3 analog sensor
	 * @param port The LegoPort instance
	 * @param typeId The sensor type ID, see <a href="http://www.ev3dev.org/docs/sensors">here</a> for more details.
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public EV3AnalogSensor(LegoPort port, String typeId) throws EV3LibraryException{
		super(port);
		String drivername = port.getDriverName();
		if (!drivername.equals(DRIVER_NAME + "-" + typeId)){
			throw new EV3LibraryException("The port is not connected to a EV3 analog sensor with type id \"" + typeId + "\": " + drivername);
		}
	}

}
