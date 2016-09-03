package org.ev3dev.hardware.sensors;

import java.io.IOException;

import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.Device;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Def;
import org.ev3dev.io.Sysfs;

/**
 * The sensor class provides a uniform interface for using most of the sensors available for the EV3.
 *  The various underlying device drivers will create a lego-sensor device for interacting with the sensors.<br>
 *  <br>
 *  Sensors are primarily controlled by setting the mode and monitored by reading the value[N] attributes.
 *  Values can be converted to floating point if needed by value[N] / 10.0 ^ decimals.<br>
 *  <br>
 *  Since the name of the sensor[N] device node does not correspond to the port that a sensor is plugged in
 *  to, you must look at the address attribute if you need to know which port a sensor is plugged in to.
 *  However, if you don¡¦t have more than one sensor of each type, you can just look for a matching driver_name.
 *  Then it will not matter which port a sensor is plugged in to - your program will still work.
 * @author Anthony
 *
 */
public class Sensor extends Device{
	
	/**
	 * This Sysfs's class name (e.g. <code>/sys/class/lego-sensor</code>, and <code>lego-sensor</code> is the class name)
	 */
	public static final String CLASS_NAME = "lego-sensor";
	
	/**
	 * This Sysfs's class name prefix (e.g. <code>/sys/class/lego-sensor/sensor0</code>, and <code>sensor</code> is the class name prefix without the [N] value.)
	 */
	public static final String CLASS_NAME_PREFIX = "sensor";

	/**
	 * Creates a new Sensor instance using a LegoPort
	 * @param port LegoPort
	 * @throws IOException If I/O goes wrong
	 * @throws InvalidPortException If the specified LegoPort was invalid
	 */
	public Sensor(LegoPort port) throws IOException, InvalidPortException{
		super(port, CLASS_NAME, CLASS_NAME_PREFIX);
	}
	
	/**
	 * Returns the name of the port that the sensor is connected to, e.g. ev3:in1.
	 *  I2C sensors also include the I2C address (decimal), e.g. ev3:in1:i2c8.
	 * @return A sensor address
	 * @throws IOException If I/O goes wrong
	 */
	public String getAddress() throws IOException{
		return this.getAttribute(Def.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the sensor controller.
	 * @param command Command that suits for the sensor driver
	 * @throws IOException If I/O goes wrong
	 */
	public void sendCommand(String command) throws IOException{
		this.setAttribute(Def.PROPERTY_COMMAND, command);
	}
	
	/**
	 * <b>This function returns a String instead of a String Array</b><br>
	 * <b>Use this function to return a String Array directly:</b>
	 * <pre>
	 * getCommands()
	 * </pre>
	 * Returns a list of the valid commands for the sensor. Returns -EOPNOTSUPP if no commands are supported.
	 * @return A list of valid commands
	 * @throws IOException If I/O goes wrong
	 */
	public String getCommandsViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_COMMANDS);
	}
	
	/**
	 * Returns a list of the valid commands for the sensor. Returns -EOPNOTSUPP if no commands are supported.
	 * @return A list of valid commands
	 * @throws IOException If I/O goes wrong
	 */
	public String[] getCommands() throws IOException{
		String str = getCommandsViaString();
		return Sysfs.separateSpace(str);
	}
	
	/**
	 * Returns the number of decimal places for the values in the value[N] attributes of the current mode.
	 * @return The number of decimal places
	 * @throws IOException If I/O goes wrong
	 */
	public int getDecimals() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_DECIMALS);
		return Integer.parseInt(str);
	}
	
	/**
	 * Returns the name of the sensor device/driver. See the list of [supported sensors] for a complete list of drivers.
	 * @return The name of the sensor device or driver
	 * @throws IOException If I/O goes wrong
	 */
	public String getDriverName() throws IOException{
		return this.getAttribute(Def.PROPERTY_DRIVER_NAME);
	}
	
	/**
	 * Returns the current mode. Writing one of the values returned by modes sets the sensor to that mode.
	 * @return The current mode
	 * @throws IOException If I/O goes wrong
	 */
	public String getMode() throws IOException{
		return this.getAttribute(Def.PROPERTY_MODE);
	}
	
	/**
	 * Sets the current mode. Writing one of the values returned by modes sets the sensor to that mode.
	 * @param mode The mode listed using <code>getModes()</code>
	 * @throws IOException If I/O goes wrong
	 */
	public void setMode(String mode) throws IOException{
		this.setAttribute(Def.PROPERTY_MODE, mode);
	}
	
	/**
	 * <b>This function returns a String instead of a array.</b><br>
	 * <b>Use this function to return a String Array directly:</b>
	 * <pre>
	 * getModes()
	 * </pre>
	 * Returns a list of the valid modes for the sensor.
	 * @return A list of valid modes for the sensor
	 * @throws IOException If I/O goes wrong
	 */
	public String getModesViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_MODES);
	}
	
	/**
	 * Returns a list of the valid modes for the sensor.
	 * @return A list of valid modes for the sensor
	 * @throws IOException If I/O goes wrong
	 */
	public String[] getModes() throws IOException{
		String str = getModesViaString();
		return Sysfs.separateSpace(str);
	}
	
	/**
	 * Returns the number of value[N] attributes that will return a valid value for the current mode.
	 * @return The number if value[N] attributes
	 * @throws IOException If I/O goes wrong
	 */
	public int getNumValues() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_NUM_VALUES);
		return Integer.parseInt(str);
	}
	
	/**
	 * Returns the units of the measured value for the current mode. May return empty string
	 * @return The units of measured value
	 * @throws IOException If I/O goes wrong
	 */
	public String getUnits() throws IOException{
		return this.getAttribute(Def.PROPERTY_UNITS);
	}
	
}
