package ev3dev.hardware.motors;

import java.io.IOException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class Motor {

	private LegoPort port;

	private String address;
	
	private String MOTOR_STR = null;
	
	/***
	 * Creates a new motor object.
	 * @param device A device object connecting a motor
	 * @throws InvalidPortException If the LegoPort isn't a OUTPUT, invalid or a tacho-motor.
	 * @throws IOException If the LegoPort specified goes wrong
	 */
	public Motor(Device device) throws InvalidPortException, IOException{
		this.port = device.getPort();
		address = port.getAddress();
		
		//Verify is the LegoPort connecting a motor / is a output
		if (!address.contains("out")){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a output.");
		} else if (!port.getStatus().equals(PropertyDefaults.MOTOR_CLASS_NAME)){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a motor (" + port.getStatus() + ")");
		}
		MOTOR_STR = Sysclass.getHardwareName(PropertyDefaults.MOTOR_CLASS_NAME, PropertyDefaults.SUB_MOTOR_CLASS_NAME, address);
		if (MOTOR_STR == null){
			throw new InvalidPortException("The motor does not exist. (Future plan: Wait until a suitable device)");
			//TODO This should wait until a suitable device detected.
		}
	}
	
	/***
	 * Get the address of this motor.
	 * @return LegoPort address described in String
	 * @throws IOException If the motor doesn't exist or IO ERROR
	 */
	public String getAddress() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the motor controller.
	 * @param command Command that suits for the motor driver
	 */
	public void sendCommand(String command) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_COMMAND, command);
	}
	
	/***
	 * Cause the motor to run until another command is sent
	 * @throws IOException
	 */
	public void runForever() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN_FOREVER);
	}
	
	/***
	 * Run to an absolute position specified by <b>position_sp</b>
	 *  and then stop using the command specified in <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void runToAbsPos() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN_TO_ABS_POS);
	}
	
	/***
	 * Run to a position relative to the current position value.
	 * The new position will be <b>current position</b> + <b>position_sp</b>.
	 * When the new position is reached, the motor will stop
	 *  using the command specified by <b>stop_command</b>.
	 * @throws IOException If I/O goes wrong.
	 */
	public void runToRelPos() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN_TO_REL_POS);
	}
	
	/***
	 * Run the motor for the amount of time specified in <b>time_sp</b>
	 *  and then stop the motor using the command specified by
	 *  <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void runTimed() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN_TIMED);
	}
	
	/***
	 * Run the motor at the duty cycle specified by <b>duty_cycle_sp</b>.
	 *  Unlike other run commands, changing <b>duty_cycle_sp</b> while
	 *   running will take effect immediately
	 * @throws IOException If I/O goes wrong
	 */
	public void runDirect() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN_DIRECT);
	}
	
	public void stop() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_STOP);
	}
	
	public void reset() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RESET);
	}
	
	public String[] getCommands() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_COMMANDS);
		return Sysclass.separateSpace(str);
	}
	
	public int getCountPerRot() throws IOException{
		String countperrot = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_COUNT_PER_ROT);
		return Integer.parseInt(countperrot);
	}
	
	public String getDriverName() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_DRIVER_NAME);
	}
	
	public int getDutyCycle() throws IOException{
		String dutycycle = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_DUTY_CYCLE);
		return Integer.parseInt(dutycycle);
	}
	
	public int getDutyCycleSP() throws IOException{
		String dutycyclesp = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_DUTY_CYCLE_SP);
		return Integer.parseInt(dutycyclesp);
	}
	
	public void setDutyCycleSP(int sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_DUTY_CYCLE_SP, Integer.toString(sp));
	}
	
	public String getEncoderPolarity() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_ENCODER_POLARITY);
	}
	
	public void setEncoderPolarity(String encoder_polarity) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_ENCODER_POLARITY, encoder_polarity);
	}
	
	public String getPolarity() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POLARITY);
	}
	
	public void setPolarity(String polarity) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POLARITY, polarity);
	}
	
	public int getPosition() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, "position_p");
		return Integer.parseInt(str);
	}
	
	public void setPosition(int position) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION, Integer.toString(position));
	}
	
	public int getPosition_P() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_P);
		return Integer.parseInt(str);
	}
	
	public int getPosition_I() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_I);
		return Integer.parseInt(str);
	}
	
	public int getPosition_D() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_D);
		return Integer.parseInt(str);
	}
	
	public void setPosition_P(int position_p) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_P, Integer.toString(position_p));
	}
	
	public void setPosition_I(int position_i) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_I, Integer.toString(position_i));
	}
	
	public void setPosition_D(int position_d) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_D, Integer.toString(position_d));
	}
	
	public int getPosition_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_SP);
		return Integer.parseInt(str);
	}

	public void setPosition_SP(int position_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_SP, Integer.toString(position_sp));
	}
	
	public int getSpeed() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED);
		return Integer.parseInt(str);
	}
	
	public int getSpeed_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED);
		return Integer.parseInt(str);
	}
	
	public void setSpeed_SP(int speed_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_SP, Integer.toString(speed_sp));
	}
	
	public int getRamp_Up_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_RAMP_UP_SP);
		return Integer.parseInt(str);
	}
	
	public void setRamp_Up_SP(int ramp_up_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_RAMP_UP_SP, Integer.toString(ramp_up_sp));
	}
	
	public int getRamp_Down_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_RAMP_DOWN_SP);
		return Integer.parseInt(str);
	}
	
	public void setRamp_Down_SP(int ramp_down_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_RAMP_DOWN_SP, Integer.toString(ramp_down_sp));
	}
	
	public String getSpeedRegulationEnabled() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_ENABLED);
		return str;
	}
	
	public boolean isSpeedRegulationEnabled() throws IOException{
		String str = getSpeedRegulationEnabled();
		if (str.equals("on")){
			return true;
		} else if (str.equals("off")){
			return false;
		} else {
			return false;
		}
	}
	
	public void setSpeedRegulationEnabled(boolean enabled) throws IOException{
		String str = enabled ? "on" : "off";
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_ENABLED, str);
	}
	
	public void setSpeedRegulationEnabled(String onoff) throws IOException{
		boolean enabled = false;
		if (onoff.equals("on")){
			enabled = true;
		} else if (onoff.equals("off")){
			enabled = false;
		} else {
			enabled = false;
		}
		setSpeedRegulationEnabled(enabled);
	}
	
	public int getSpeedRegulation_P() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_P);
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_P(int p) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_P, Integer.toString(p));
	}
	
	public int getSpeedRegulation_I() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_I);
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_I(int i) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_I, Integer.toString(i));
	}
	
	public int getSpeedRegulation_D() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_D);
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_D(int d) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_SPEED_REGULATION_D, Integer.toString(d));
	}
	
	public String getStateViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_STATE);
	}
	
	public String[] getState() throws IOException{
		String str = getStateViaString();
		return Sysclass.separateSpace(str);
	}
	
	public String getStopCommand() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_STOP_COMMAND);
	}
	
	public void setStopCommand(String stop_command) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_STOP_COMMAND, stop_command);
	}
	
	public String getStopCommandsViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_STOP_COMMANDS);
	}
	
	public String[] getStopCommands() throws IOException{
		String str = getStopCommandsViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getTime_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_TIME_SP);
		return Integer.parseInt(str);
	}
	
	public void setTime_SP(int time_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_TIME_SP, Integer.toString(time_sp));
	}
	
}
