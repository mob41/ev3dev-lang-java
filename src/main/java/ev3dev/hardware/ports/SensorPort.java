package ev3dev.hardware.ports;

import ev3dev.exception.InvalidPortException;

/***
 * SensorPort class for getting data from a specified sensor port.<br>
 * <br>
 * You can use a LegoPort to use all the ports.
 * @author Anthony
 *
 */
public class SensorPort extends LegoPort{
	
	public static final int SENSOR_1 = 0;
	public static final int SENSOR_2 = 1;
	public static final int SENSOR_3 = 2;
	public static final int SENSOR_4 = 3;

	/***
	 * Creates a new sensor port object.<br>
	 * <br>
	 * <b>NOTE:</b> SensorPort only accepts sensor ports. Use LegoPort for all ports.
	 * @param port A final field from the <b>SensorPort</b> class. e.g.: <b>SensorPort.SENSOR_1</b>
	 * @throws InvalidPortException If the specified port does not exist
	 */
	public SensorPort(int port) throws InvalidPortException {
		super(port);
		if (port < 0){
			throw new InvalidPortException("The port you specified was invaild: 0 > " + port);
		} else if (port > SENSOR_4){
			throw new InvalidPortException("The port you specified isn't a sensor: " + SENSOR_3 + " > " + port);
		}
	}
	
}
