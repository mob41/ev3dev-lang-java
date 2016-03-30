package ev3dev.hardware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

import ev3dev.EV3;
import ev3dev.exception.InvalidPortException;

public class Motor {
	
	private static final String SYSTEM_CLASS_NAME = "tacho-motor";
	private LegoPort port;
	private String address;
	
	/***
	 * Creates a new motor object.
	 * @param port Specify a LegoPort
	 * @throws InvalidPortException If the LegoPort isn't a OUTPUT, invalid or a tacho-motor.
	 */
	public Motor(LegoPort port) throws InvalidPortException{
		this.port = port;
		address = port.getAddress();
		
		//Verify is the LegoPort connecting a motor / is a output
		if (!address.contains("out")){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a output.");
		} else if (port.getStatus() != "tacho-motor"){
			throw new InvalidPortException("The specified port (" + port.getAddress() + ") isn't a motor (" + port.getStatus() + ")");
		}
		
	}
	
	/***
	 * Get the address of this motor.
	 * @return LegoPort address described in String
	 * @throws IOException If the motor doesn't exist or IO ERROR
	 */
	public String getAddress() throws IOException{
		return EV3.read(SYSTEM_CLASS_NAME, "tacho-motor", "");
	}
	
	private static int getMotorNumber(String address){
		int motors = EV3.getNumbersOfSubClass("motor");
		if (motors == -1){
			return -1;
		}
		for (int i = 0; i < motors; i++){
			try {
				if (EV3.read("tacho-motor", "motor" + i, "address").equals(address)){
					return i;
				} 
			} catch (IOException ignore){}
		}
		return -1;
	}
}
