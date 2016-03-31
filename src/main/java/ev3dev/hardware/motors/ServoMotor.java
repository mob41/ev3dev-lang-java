package ev3dev.hardware.motors;

import java.io.IOException;
import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

public class ServoMotor {

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
	public ServoMotor(Device device) throws InvalidPortException, IOException{
		this.port = device.getPort();
		address = port.getAddress();
		
		//Verify is the LegoPort connecting a motor / is a output
		if (!address.contains("out")){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a output.");
		} else if (!port.getStatus().equals(PropertyDefaults.SERVO_MOTOR_CLASS_NAME)){
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
		return Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_ADDRESS);
	}
	
	/***
	 * Generic method to send commands to the motor controller.
	 * @param command Command that suits for the motor driver
	 */
	public void sendCommand(String command) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_COMMAND, command);
	}
	
	/***
	 * Setting to run will cause the servo to be driven to the <b>position_sp</b> set in the <b>position_sp</b> attribute. 
	 * @throws IOException
	 */
	public void run() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_RUN);
	}
	
	/***
	 * Run to an absolute position specified by <b>position_sp</b>
	 *  and then stop using the command specified in <b>stop_command</b>
	 * @throws IOException If I/O goes wrong
	 */
	public void Float() throws IOException{
		sendCommand(PropertyDefaults.COMMAND_FLOAT);
	}
	
	public String getDriverName() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_DRIVER_NAME);
	}
	
	public int getMaxPulse_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MAX_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	public void setMaxPulse_SP(int max_pulse_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MAX_PULSE_SP, Integer.toString(max_pulse_sp));
	}
	
	public int getMidPulse_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MID_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	public void setMidPulse_SP(int mid_pulse_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MID_PULSE_SP, Integer.toString(mid_pulse_sp));
	}
	
	public int getMinPulse_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MIN_PULSE_SP);
		return Integer.parseInt(str);
	}
	
	public void setMinPulse_SP(int min_pulse_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_MIN_PULSE_SP, Integer.toString(min_pulse_sp));
	}
	
	public String getPolarity() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POLARITY);
	}
	
	public void setPolarity(String polarity) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POLARITY, polarity);
	}
	
	public int getPosition_SP() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_SP);
		return Integer.parseInt(str);
	}

	public void setPosition_SP(int position_sp) throws IOException{
		Sysclass.setProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_POSITION_SP, Integer.toString(position_sp));
	}
	
	public String getStateViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.DC_MOTOR_CLASS_NAME, MOTOR_STR, PropertyDefaults.PROPERTY_STATE);
	}

	public String[] getState() throws IOException{
		String str = getStateViaString();
		return Sysclass.separateSpace(str);
	}
	
	private static int getMotorNumber(String address){
		int motors = Sysclass.getNumbersOfSubClass(PropertyDefaults.SERVO_MOTOR_CLASS_NAME);
		System.out.println("All motors: " + motors);
		if (motors == -1){
			return -1;
		}
		for (int i = 0; i < motors; i++){
			try {
				if (Sysclass.getProperty(PropertyDefaults.SERVO_MOTOR_CLASS_NAME, "motor" + i, PropertyDefaults.PROPERTY_ADDRESS).equals(address)){
					return i;
				} 
			} catch (IOException ignore){}
		}
		return -1;
	}
}
