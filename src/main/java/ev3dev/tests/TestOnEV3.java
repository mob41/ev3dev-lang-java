package ev3dev.tests;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.motors.Motor;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.hardware.ports.MotorPort;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		LegoPort port = new LegoPort(LegoPort.PORT_A);
		Motor motor = new Motor(port);
		motor.setDutyCycleSP(50);
		motor.runForever();
		Thread.sleep(5000);
		motor.stop();
	}

}
