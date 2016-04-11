package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

/**
 * A generic interface to control I2C-type EV3 sensors.
 * @author Anthony
 *
 */
public class I2CSensor extends Sensor {

	/**
	 * Creates a new I2CSensor instance.
	 * @param port LegoPort
	 * @throws InvalidPortException If the specified port wasn't valid
	 * @throws InvalidSensorException If the specified sensor wasn't a I2CSensor
	 * @throws IOException If I/O goes wrong
	 */
	public I2CSensor(LegoPort port) throws InvalidPortException, InvalidSensorException, IOException {
		super(port);
		if (!this.getDriverName().equals(Def.I2CSENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("The specified port is not a I2C sensor.");
		}
	}
	
	/**
	 * Returns the firmware version of the sensor if available. Currently only I2C/NXT sensors support this.
	 * @return The firmware version
	 * @throws IOException If I/O goes wrong
	 */
	public String getFirmwareVersion() throws IOException{
		return this.getAttribute(Def.PROPERTY_FIRMWARE_VERSION);
	}
	
	/**
	 * Returns the polling period of the sensor in milliseconds. Writing sets the polling period. Setting to 0 disables polling. 
	 * Minimum value is hard coded as 50 msec. Returns -EOPNOTSUPP if changing polling is not supported.
	 *  Currently only I2C/NXT sensors support changing the polling period.
	 * @return The polling period in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public int getPollMs() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_POLL_MS);
		return Integer.parseInt(str);
	}
	
	/**
	 * Sets the polling period of the sensor in milliseconds. Writing sets the polling period. Setting to 0 disables polling. 
	 * Minimum value is hard coded as 50 msec. Returns -EOPNOTSUPP if changing polling is not supported.
	 *  Currently only I2C/NXT sensors support changing the polling period.
	 * @param ms The polling period in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public void setPollMs(int ms) throws IOException{
		this.setAttribute(Def.PROPERTY_POLL_MS, Integer.toString(ms));
	}
}
