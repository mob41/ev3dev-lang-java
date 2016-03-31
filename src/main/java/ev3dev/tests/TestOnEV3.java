package ev3dev.tests;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.hardware.Device;
import ev3dev.hardware.motors.Motor;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.hardware.ports.MotorPort;
import ev3dev.hardware.ports.SensorPort;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		//LegoPort port = new LegoPort(42);
		SensorPort port2 = new SensorPort(6);
	}

}
