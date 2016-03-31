package ev3dev.tests;

import ev3dev.hardware.Device;
import ev3dev.hardware.motors.MediumMotor;
import ev3dev.hardware.motors.Motor;
import ev3dev.hardware.ports.LegoPort;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		LegoPort port = new LegoPort(LegoPort.PORT_A);
		Device device = new Device(port);
		MediumMotor motor = new MediumMotor(device);
		motor.setDutyCycleSP(50);
		motor.runForever();
		Thread.sleep(5000);
		motor.stop();
		
	}

}
