package ev3dev.hardware.motors;

import java.io.IOException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;
import ev3dev.io.Sysclass;

public class DCMotor extends Device{

	private LegoPort port;

	private String address;
	
	/***
	 * Creates a new DC motor object.
	 * @param device A device object connecting a motor
	 * @throws InvalidPortException If the LegoPort isn't a OUTPUT, invalid or a tacho-motor.
	 * @throws IOException If the LegoPort specified goes wrong
	 */
	public DCMotor(LegoPort port) throws InvalidPortException, IOException{
		super(port, Def.DC_MOTOR_CLASS_NAME, Def.SUB_MOTOR_CLASS_NAME);
		this.port = port;
		address = port.getAddress();
		
		//Verify is the LegoPort connecting a motor / is a output
		if (!address.contains("out")){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a output.");
		} else if (!port.getStatus().equals(Def.DC_MOTOR_CLASS_NAME)){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a motor (" + port.getStatus() + ")");
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
	 */
	public void sendCommand(String command) throws IOException{
		this.setAttribute(Def.PROPERTY_COMMAND, command);
	}
	
	/***
	 * Cause the motor to run until another command is sent
	 * @throws IOException
	 */
	public void runForever() throws IOException{
		sendCommand(Def.COMMAND_RUN_FOREVER);
	}
	
	/***
	 * Run the motor for the amount of time specified in <b>time_sp</b>
	 *  and then stop the motor using the command specified by
	 *  <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void runTimed() throws IOException{
		sendCommand(Def.COMMAND_RUN_TIMED);
	}
	
	public void stop() throws IOException{
		sendCommand(Def.COMMAND_STOP);
	}
	
	public String[] getCommands() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_COMMANDS);
		return Sysclass.separateSpace(str);
	}
	
	public String getDriverName() throws IOException{
		return this.getAttribute(Def.PROPERTY_DRIVER_NAME);
	}
	
	public int getDutyCycle() throws IOException{
		String dutycycle = this.getAttribute(Def.PROPERTY_DUTY_CYCLE);
		return Integer.parseInt(dutycycle);
	}
	
	public int getDutyCycleSP() throws IOException{
		String dutycyclesp = this.getAttribute(Def.PROPERTY_DUTY_CYCLE_SP);
		return Integer.parseInt(dutycyclesp);
	}
	
	public void setDutyCycleSP(int sp) throws IOException{
		this.setAttribute(Def.PROPERTY_DUTY_CYCLE_SP, Integer.toString(sp));
	}
	
	public String getPolarity() throws IOException{
		return this.getAttribute(Def.PROPERTY_POLARITY);
	}
	
	public void setPolarity(String polarity) throws IOException{
		this.setAttribute(Def.PROPERTY_POLARITY, polarity);
	}
	
	public int getRamp_Up_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_RAMP_UP_SP);
		return Integer.parseInt(str);
	}
	
	public void setRamp_Up_SP(int ramp_up_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_RAMP_UP_SP, Integer.toString(ramp_up_sp));
	}
	
	public int getRamp_Down_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_RAMP_DOWN_SP);
		return Integer.parseInt(str);
	}
	
	public void setRamp_Down_SP(int ramp_down_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_RAMP_DOWN_SP, Integer.toString(ramp_down_sp));
	}
	
	public String getStateViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_STATE);
	}
	
	public String[] getState() throws IOException{
		String str = getStateViaString();
		return Sysclass.separateSpace(str);
	}
	
	public String getStopCommand() throws IOException{
		return this.getAttribute(Def.PROPERTY_STOP_COMMAND);
	}
	
	public void setStopCommand(String stop_command) throws IOException{
		this.setAttribute(Def.PROPERTY_STOP_COMMAND, stop_command);
	}
	
	public String getStopCommandsViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_STOP_COMMANDS);
	}
	
	public String[] getStopCommands() throws IOException{
		String str = getStopCommandsViaString();
		return Sysclass.separateSpace(str);
	}
	
	public int getTime_SP() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_TIME_SP);
		return Integer.parseInt(str);
	}
	
	public void setTime_SP(int time_sp) throws IOException{
		this.setAttribute(Def.PROPERTY_TIME_SP, Integer.toString(time_sp));
	}
}
