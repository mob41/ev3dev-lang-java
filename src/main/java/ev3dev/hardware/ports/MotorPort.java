package ev3dev.hardware.ports;

import ev3dev.exception.InvalidPortException;

/***
 * MotorPort class for fetching data from a specified motor port.<br>
 * <br>
 * You can use a LegoPort to use all the ports.
 * @author Anthony
 *
 */
public class MotorPort extends LegoPort {
	
	public static final int MOTOR_A = 4;
	public static final int MOTOR_B = 5;
	public static final int MOTOR_C = 6;
	public static final int MOTOR_D = 7;

	/***
	 * Creates a new motor port object.<br>
	 * <br>
	 * <b>NOTE:</b> MotorPort only accepts motors ports. Use LegoPort for all ports.
	 * @param port A final field from the <b>MotorPort</b> class. e.g.: <b>MotorPort.MOTOR_A</b>
	 * @throws InvalidPortException If the specified port does not exist
	 */
	public MotorPort(int port) throws InvalidPortException {
		super(port);
		if (port < 0){
			throw new InvalidPortException("The port you specified was invaild: 0 > " + port);
		} else if (port < MOTOR_A){
			throw new InvalidPortException("The port you specified isn't a motor: " + MOTOR_A + " > " + port);
		} else if (port > MOTOR_D){
			throw new InvalidPortException("The port you specified was invaild: " + MOTOR_D + " > " + port);
		}
	}

}
