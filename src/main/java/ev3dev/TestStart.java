package ev3dev;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.LegoPort;

public class TestStart {

	public static void main(String[] args) throws InvalidPortException {
		LegoPort port;
		for (int i = 0; i <= 7; i++){
			port = new LegoPort(i);
			System.out.println(
					"\nAddress: " + port.getAddress() +
					"\nDriver Nm: " + port.getDriverName() +
					"\nMode: " + port.getMode() +
					"\nStatus: " + port.getStatus()
					);
		}
	}

}
