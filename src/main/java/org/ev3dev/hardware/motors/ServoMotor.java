package org.ev3dev.hardware.motors;

import java.io.IOException;

import org.ev3dev.exception.InvalidMotorException;
import org.ev3dev.exception.InvalidPortException;
import org.ev3dev.hardware.Device;
import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Def;
import org.ev3dev.io.Sysfs;

/**
 * The servo motor class provides a uniform interface for using hobby type servo motors.
 * @author Anthony
 *
 */
public class ServoMotor extends Device{
	
	/**
	 * This Sysfs's class name (e.g. <code>/sys/class/lego-sensor</code>, and <code>lego-sensor</code> is the class name)
	 */
	public static final String SERVO_MOTOR_CLASS_NAME = "servo-motor";
	
	/**
	 * This Sysfs's class name prefix (e.g. <code>/sys/class/lego-sensor/sensor0</code>, and <code>sensor</code> is the class name prefix without the [N] value.)
	 */
	public static final String SERVO_MOTOR_CLASS_NAME_PREFIX = "motor";

	private String address;
	
	/***
	 * Creates a new motor object.
	 * @param port LegoPort
	 * @throws InvalidPortException If the LegoPort isn't a OUTPUT, invalid or a tacho-motor.
	 * @throws IOException If the LegoPort specified goes wrong
	 * @throws InvalidMotorException The specified motor wasn't a motor
	 */
	public ServoMotor(LegoPort port) throws InvalidPortException, InvalidMotorException, IOException{
		super(port, SERVO_MOTOR_CLASS_NAME, SERVO_MOTOR_CLASS_NAME_PREFIX);
		address = port.getAddress();
		
		//Verify is the LegoPort connecting a motor / is a output
		if (!address.contains("out")){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a output.");
		} else if (!port.getStatus().equals(SERVO_MOTOR_CLASS_NAME)){
			throw new InvalidMotorException("The specified port (" + port.getAddress() + ") isn't a motor (" + port.getStatus() + ")");
		}
	}
	
	/***
	 * Get the address of this motor.
	 * @return LegoPort address described in String
	 * @throws IOException If the motor doesn't exist or IO ERROR
	 */
	public String getAddress() throws IOException{
		return this.getAttribute(Def.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the motor controller.
	 * @param command Command that suits for the motor driver
	 * @throws IOException If I/O goes wrong
	 */
	public void sendCommand(String command) throws IOException{
		this.setAttribute(Def.PROPERTY_COMMAND, command);
	}
	
	/***
	 * Setting to run will cause the servo to be driven to the <b>position_sp</b> set in the <b>position_sp</b> attribute. 
	 * @throws IOException If I/O goes wrong
	 */
	public void run() throws IOException{
		sendCommand(Def.COMMAND_RUN);
	}
	
	/***
	 * Run to an absolute position specified by <b>position_sp</b>
	 *  and then stop using the command specified in <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void Float() throws IOException{
		sendCommand(Def.COMMAND_FLOAT);
	}
	
	/**
	 * Returns the name of the driver that provides this tacho motor device.
	 * @return The name of the driver
	 * @throws IOException If I/O goes wrong
	 */
	public String getDriverName() throws IOException{
		return this.getAttribute(Def.PROPERTY_DRIVER_NAME);
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the maximum (clockwise) position_sp. Default value is 2400.
	 *  Valid values are 2300 to 2700. You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @return The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public int getMaxPulse_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_MAX_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the maximum (clockwise) position_sp. Default value is 2400.
	 *  Valid values are 2300 to 2700. You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @param max_pulse_sp The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public void setMaxPulse_SP(int max_pulse_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_MAX_PULSE_SP, Integer.toString(max_pulse_sp));
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the mid position_sp.
	 *  Default value is 1500. Valid values are 1300 to 1700. For example, on a 180 degree servo, this would be 90 degrees.
	 *   On continuous rotation servo, this is the ．neutral・ position_sp where the motor does not turn.
	 *  You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @return The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public int getMidPulse_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_MID_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the mid position_sp.
	 *  Default value is 1500. Valid values are 1300 to 1700. For example, on a 180 degree servo, this would be 90 degrees.
	 *   On continuous rotation servo, this is the ．neutral・ position_sp where the motor does not turn.
	 *  You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @param mid_pulse_sp The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public void setMidPulse_SP(int mid_pulse_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_MID_PULSE_SP, Integer.toString(mid_pulse_sp));
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the
	 *  minimum (counter-clockwise) position_sp. Default value is 600. Valid values are 300 to 700.
	 *  You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @return The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public int getMinPulse_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_MIN_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	/**
	 * Used to set the pulse size in milliseconds for the signal that tells the servo to drive to the
	 *  minimum (counter-clockwise) position_sp. Default value is 600. Valid values are 300 to 700.
	 *  You must write to the position_sp attribute for changes to this attribute to take effect.
	 * @param min_pulse_sp The pulse size in milliseconds
	 * @throws IOException If I/O goes wrong
	 */
	public void setMinPulse_SP(int min_pulse_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_MIN_PULSE_SP, Integer.toString(min_pulse_sp));
	}
	
	/**
	 * Sets the polarity of the servo. Valid values are normal and inversed. Setting the value to inversed will cause the position_sp value to be inversed.
	 *  i.e -100 will correspond to max_pulse_sp, and 100 will correspond to min_pulse_sp.
	 * @return The polarity of the servo
	 * @throws IOException If I/O goes wrong
	 */
	public String getPolarity() throws IOException{
		return this.getAttribute(Def.PROPERTY_POLARITY);
	}
	
	/**
	 * Sets the polarity of the servo. Valid values are normal and inversed. Setting the value to inversed will cause the position_sp value to be inversed.
	 *  i.e -100 will correspond to max_pulse_sp, and 100 will correspond to min_pulse_sp.
	 * @param polarity The polarity of the servo
	 * @throws IOException If I/O goes wrong
	 */
	public void setPolarity(String polarity) throws IOException{
		this.setAttribute(Def.PROPERTY_POLARITY, polarity);
	}
	
	/**
	 * Reading returns the current position_sp of the servo. Writing instructs the servo to move to the specified position_sp.
	 *  Units are percent. Valid values are -100 to 100 (-100% to 100%)
	 *  where -100 corresponds to min_pulse_sp, 0 corresponds to mid_pulse_sp and 100 corresponds to max_pulse_sp.
	 * @return The current position_sp of the servo
	 * @throws IOException If I/O goes wrong
	 */
	public int getPosition_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_POSITION_SP);
		return Integer.parseInt(str);
	}

	/**
	 * Reading returns the current position_sp of the servo. Writing instructs the servo to move to the specified position_sp.
	 *  Units are percent. Valid values are -100 to 100 (-100% to 100%)
	 *  where -100 corresponds to min_pulse_sp, 0 corresponds to mid_pulse_sp and 100 corresponds to max_pulse_sp.
	 * @param position_sp The current position_sp of the servo
	 * @throws IOException If I/O goes wrong
	 */
	public void setPosition_SP(int position_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_POSITION_SP, Integer.toString(position_sp));
	}
	
	/**
	 * Sets the rate_sp at which the servo travels from 0 to 100.0% (half of the full range of the servo).
	 *  Units are in milliseconds. Example: Setting the rate_sp to 1000 means that it will take a 180 degree
	 *   servo 2 second to move from 0 to 180 degrees. Note: Some servo controllers may not support this
	 *    in which case reading and writing will fail with -EOPNOTSUPP.
	 *  In continuous rotation servos, this value will affect the rate_sp at which the speed ramps up or down.
	 * @param rate_sp The rate_sp value
	 * @throws IOException If I/O goes wrong
	 */
	public void setRate_SP(int rate_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_RATE_SP, Integer.toString(rate_sp));
	}
	
	/**
	 * <b>This function returns a string that is likely a "spaced-array".</b><br>
	 * <b>Use this function to directly to return a String array:</b>
	 * <pre>
	 * getState()
	 * </pre>
	 * Reading returns a list of state flags. Possible flags are running, ramping holding and stalled.
	 * @return A list of state flags. String spaced-array
	 * @throws IOException If I/O goes wrong
	 */
	public String getStateViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_STATE);
	}

	/**
	 * Reading returns a list of state flags. Possible flags are running, ramping holding and stalled.
	 * @return A list(String array) of state flags.
	 * @throws IOException If I/O goes wrong
	 */
	public String[] getState() throws IOException{
		String str = getStateViaString();
		return Sysfs.separateSpace(str);
	}
}
