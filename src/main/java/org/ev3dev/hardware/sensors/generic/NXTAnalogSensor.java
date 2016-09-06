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
	 * <code>ANALOG-0</code> Mode - Raw analog value
	 */
	public static final String MODE_ANALOG_0 = "ANALOG-0";
	
	/**
	 * <code>ANALOG-1</code> Mode - Raw analog value, Pin 5 high
	 */
	public static final String MODE_ANALOG_1 = "ANALOG-1";
	
	/**
	 * The Sysfs value index
	 */
	public static final int VALUE_INDEX = 0;
	
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
	
	/**
	 * Set mode as <code>ANALOG-0</code> Mode - Raw analog value
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public void setModeAnalog0() throws EV3LibraryException{
		setMode(MODE_ANALOG_0);
	}
	
	/**
	 * Set mode as <code>ANALOG-1</code> Mode - Raw analog value, Pin 5 high
	 * @throws EV3LibraryException If I/O goes wrong
	 */
	public void setModeAnalog1() throws EV3LibraryException{
		setMode(MODE_ANALOG_1);
	}
	
	/**
	 * Returns the raw analog voltage / value (0-5000).<br>
	 * Both mode uses the same value index (value0)<br>
	 * <br>
	 * This function does not calculate decimal places.
	 * @throws EV3LibraryException If I/O goes wrong
	 * @return The voltage
	 */
	public int getRawValue() throws EV3LibraryException{
		String str = getAttribute("value" + VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	/**
	 * Returns the raw analog voltage / value (0-5000), and with decimal places<br>
	 * Both mode uses the same value index (value0)<br>
	 * @throws EV3LibraryException If I/O goes wrong
	 * @return The voltage
	 */
	public float getValue() throws EV3LibraryException{
		float out = getRawValue();
		
		int dec = getDecimals();
		for (int i = 0; i <= dec; i++){
			out /= 10;
		}
		
		return out;
	}
	
	//TODO Decimal places http://www.ev3dev.org/docs/sensors/generic-nxt-analog-sensor/

}
