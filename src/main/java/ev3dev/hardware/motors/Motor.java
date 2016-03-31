package ev3dev.hardware.motors;

import java.io.IOException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Sysclass;

public class Motor {
	
	private static final String SYSTEM_CLASS_NAME = "tacho-motor";

	private LegoPort port;

	private String address;
	
	private int motornum;
	
	private String MOTOR_STR = "motor";
	
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
		} else if (!port.getStatus().equals(SYSTEM_CLASS_NAME)){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a motor (" + port.getStatus() + ")");
		}
		motornum = getMotorNumber(address);
		if (motornum == -1){
			throw new InvalidPortException("The motor does not exist. (Future plan: Wait until a suitable device)");
			//TODO This should wait until a suitable device detected.
		}
		MOTOR_STR = "motor" + motornum;
	}
	
	/***
	 * Get the address of this motor.
	 * @return LegoPort address described in String
	 * @throws IOException If the motor doesn't exist or IO ERROR
	 */
	public String getAddress() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "address");
	}
	
	/***
	 * Generic method to send commands to the motor controller.
	 * @param command Command that suits for the motor driver
	 */
	public void sendCommand(String command) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "command", command);
	}
	
	/***
	 * Cause the motor to run until another command is sent
	 * @throws IOException
	 */
	public void runForever() throws IOException{
		sendCommand("run-forever");
	}
	
	/***
	 * Run to an absolute position specified by <b>position_sp</b>
	 *  and then stop using the command specified in <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void runToAbsPos() throws IOException{
		sendCommand("run-to-abs-pos");
	}
	
	/***
	 * Run to a position relative to the current position value.
	 * The new position will be <b>current position</b> + <b>position_sp</b>.
	 * When the new position is reached, the motor will stop
	 *  using the command specified by <b>stop_command</b>.
	 * @throws IOException If I/O goes wrong.
	 */
	public void runToRelPos() throws IOException{
		sendCommand("run-to-rel-pos");
	}
	
	/***
	 * Run the motor for the amount of time specified in <b>time_sp</b>
	 *  and then stop the motor using the command specified by
	 *  <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void runTimed() throws IOException{
		sendCommand("run-timed");
	}
	
	/***
	 * Run the motor at the duty cycle specified by <b>duty_cycle_sp</b>.
	 *  Unlike other run commands, changing <b>duty_cycle_sp</b> while
	 *   running will take effect immediately
	 * @throws IOException If I/O goes wrong
	 */
	public void runDirect() throws IOException{
		sendCommand("run-direct");
	}
	
	public void stop() throws IOException{
		sendCommand("stop");
	}
	
	public void reset() throws IOException{
		sendCommand("reset");
	}
	
	public int getCountPerRot() throws IOException{
		String countperrot = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "count_per_rot");
		return Integer.parseInt(countperrot);
	}
	
	public String getDriverName() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "driver_name");
	}
	
	public int getDutyCycle() throws IOException{
		String dutycycle = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "duty_cycle");
		return Integer.parseInt(dutycycle);
	}
	
	public int getDutyCycleSP() throws IOException{
		String dutycyclesp = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "duty_cycle_sp");
		return Integer.parseInt(dutycyclesp);
	}
	
	public void setDutyCycleSP(int sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "duty_cycle_sp", Integer.toString(sp));
	}
	
	public String getEncoderPolarity() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "encoder_polarity");
	}
	
	public void setEncoderPolarity(String encoder_polarity) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "encoder_polarity", encoder_polarity);
	}
	
	public String getPolarity() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "polarity");
	}
	
	public void setPolarity(String polarity) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "polarity", polarity);
	}
	
	public int getPosition() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "position_p");
		return Integer.parseInt(str);
	}
	
	public void setPosition(int position) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "position", Integer.toString(position));
	}
	
	public int getPosition_P() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Kp");
		return Integer.parseInt(str);
	}
	
	public int getPosition_I() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Ki");
		return Integer.parseInt(str);
	}
	
	public int getPosition_D() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Kd");
		return Integer.parseInt(str);
	}
	
	public void setPosition_P(int position_p) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Kp", Integer.toString(position_p));
	}
	
	public void setPosition_I(int position_i) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Ki", Integer.toString(position_i));
	}
	
	public void setPosition_D(int position_d) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "hold_pid/Kd", Integer.toString(position_d));
	}
	
	public void setPosition_SP(int position_sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "position_sp", Integer.toString(position_sp));
	}
	
	public int getSpeed() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed");
		return Integer.parseInt(str);
	}
	
	public int getSpeed_SP() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed");
		return Integer.parseInt(str);
	}
	
	public void setSpeed_SP(int speed_sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_sp", Integer.toString(speed_sp));
	}
	
	public int getRamp_Up_SP() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "ramp_up_sp");
		return Integer.parseInt(str);
	}
	
	public void setRamp_Up_SP(int ramp_up_sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "ramp_up_sp", Integer.toString(ramp_up_sp));
	}
	
	public int getRamp_Down_SP() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "ramp_down_sp");
		return Integer.parseInt(str);
	}
	
	public void setRamp_Down_SP(int ramp_down_sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "ramp_down_sp", Integer.toString(ramp_down_sp));
	}
	
	public String getSpeedRegulationEnabled() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_regulation_enabled");
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
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_regulation_enabled", str);
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
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Kp");
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_P(int p) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Kp", Integer.toString(p));
	}
	
	public int getSpeedRegulation_I() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Ki");
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_I(int i) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Ki", Integer.toString(i));
	}
	
	public int getSpeedRegulation_D() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Kd");
		return Integer.parseInt(str);
	}
	
	public void setSpeedRegulation_D(int d) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "speed_pid/Kd", Integer.toString(d));
	}
	
	public String getStateViaString() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "state");
	}
	
	public String[] getState() throws IOException{
		String str = getStateViaString();
		return Sysclass.separateSpace(str);
	}
	
	public String getStopCommand() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "stop_command");
	}
	
	public void setStopCommand(String stop_command) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "stop_command", stop_command);
	}
	
	public String getStopCommandsViaString() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "stop_commands");
	}
	
	public String[] getStopCommands() throws IOException{
		String str = getStopCommandsViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getTime_SP() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "time_sp");
		return Integer.parseInt(str);
	}
	
	public void setTime_SP(int time_sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "time_sp", Integer.toString(time_sp));
	}
	
	private static int getMotorNumber(String address){
		int motors = Sysclass.getNumbersOfSubClass(SYSTEM_CLASS_NAME);
		System.out.println("All motors: " + motors);
		if (motors == -1){
			return -1;
		}
		for (int i = 0; i < motors; i++){
			try {
				if (Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + i, "address").equals(address)){
					return i;
				} 
			} catch (IOException ignore){}
		}
		return -1;
	}
}
