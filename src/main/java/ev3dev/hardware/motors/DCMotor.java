package ev3dev.hardware.motors;

import java.io.IOException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Sysclass;

public class DCMotor {
	
	private static final String SYSTEM_CLASS_NAME = "dc-motor";
	
	private static final String PROPERTY_ADDRESS = "address";
	
	private static final String PROPERTY_COMMAND = "command";
	
	private static final String COMMAND_RUN_FOREVER = "run-forever";
	
	//TODO Change all the command & properties into fields

	private LegoPort port;

	private String address;
	
	private int motornum;
	
	private String MOTOR_STR = "motor";
	
	/***
	 * Creates a new DC motor object.
	 * @param device A device object connecting a motor
	 * @throws InvalidPortException If the LegoPort isn't a OUTPUT, invalid or a tacho-motor.
	 * @throws IOException If the LegoPort specified goes wrong
	 */
	public DCMotor(Device device) throws InvalidPortException, IOException{
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
	
	public String[] getCommands() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "commands");
		return Sysclass.separateSpace(str);
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
	
	public String getPolarity() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "polarity");
	}
	
	public void setPolarity(String polarity) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, MOTOR_STR, "polarity", polarity);
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
