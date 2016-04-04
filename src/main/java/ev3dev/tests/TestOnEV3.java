package ev3dev.tests;

import ev3dev.hardware.Device;
import ev3dev.hardware.motors.Motor;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.hardware.ports.MotorPort;
import ev3dev.hardware.ports.SensorPort;
import ev3dev.hardware.sensors.TouchSensor;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		LegoPort port = new LegoPort(LegoPort.PORT_A);
		Motor motor = new Motor(port);
		while (true){
			System.out.println(motor.isConnected());
			if (motor.isConnected()){
				motor.setDutyCycleSP(25);
				motor.runForever();
			}
		}
	}

}
