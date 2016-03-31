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
	}
	
	/***
	 * Get the address of this motor.
	 * @return LegoPort address described in String
	 * @throws IOException If the motor doesn't exist or IO ERROR
	 */
	public String getAddress() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "address");
	}
	
	/***
	 * Generic method to send commands to the motor controller.
	 * @param command Command that suits for the motor driver
	 */
	public void sendCommand(String command) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "command", command);
	}
	
	/***
	 * Cause the motor to run until another command is sent
	 * @throws IOException
	 */
	public void runForever() throws IOException{
		sendCommand("run-forever");
	}
	
	public void runToAbsPos() throws IOException{
		sendCommand("run-to-abs-pos");
	}
	
	public void runToRelPos() throws IOException{
		sendCommand("run-to-rel-pos");
	}
	
	public void runTimed() throws IOException{
		sendCommand("run-timed");
	}
	
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
		String countperrot = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "count_per_rot");
		return Integer.parseInt(countperrot);
	}
	
	public String getDriverName() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "driver_name");
	}
	
	public int getDutyCycle() throws IOException{
		String dutycycle = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "duty_cycle");
		return Integer.parseInt(dutycycle);
	}
	
	public int getDutyCycleSP() throws IOException{
		String dutycyclesp = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "duty_cycle_sp");
		return Integer.parseInt(dutycyclesp);
	}
	
	public void setDutyCycleSP(int sp) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "duty_cycle_sp", Integer.toString(sp));
	}
	
	public String getEncoderPolarity() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "encoder_polarity");
	}
	
	public void setEncoderPolarity(String encoder_polarity) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "encoder_polarity", encoder_polarity);
	}
	
	public String getPolarity() throws IOException{
		return Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "polarity");
	}
	
	public void setPolarity(String polarity) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "polarity", polarity);
	}
	
	public int getPosition() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "position_p");
		return Integer.parseInt(str);
	}
	
	public void setPosition(int position) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "position", Integer.toString(position));
	}
	
	public int getPosition_P() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "hold_pid/Kp");
		return Integer.parseInt(str);
	}
	
	public int getPosition_I() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "hold_pid/Ki");
		return Integer.parseInt(str);
	}
	
	public int getPosition_D() throws IOException{
		String str = Sysclass.getProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "hold_pid/Kd");
		return Integer.parseInt(str);
	}
	
	public void setPosition_P(int position_p) throws IOException{
		Sysclass.setProperty(SYSTEM_CLASS_NAME, "motor" + motornum, "hold_pid/Kp", Integer.toString(position_p));
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
