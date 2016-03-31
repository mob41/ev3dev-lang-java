package ev3dev.tests;

import ev3dev.hardware.Device;
import ev3dev.hardware.motors.Motor;
import ev3dev.hardware.ports.MotorPort;
import ev3dev.hardware.ports.SensorPort;
import ev3dev.hardware.sensors.TouchSensor;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		MotorPort motorport = new MotorPort(MotorPort.MOTOR_A);
		SensorPort sensorport = new SensorPort(SensorPort.PORT_1);
		Device motord = new Device(motorport);
		System.out.println(motord.getPort().getAddress());
		Motor motor = new Motor(motord);
		Device tsd = new Device(sensorport);
		TouchSensor ts = new TouchSensor(tsd);
		motor.setDutyCycleSP(100);
		while (true){
			try {
				if (ts.isPressed()){
					System.out.println("Pressed");
					motor.runForever();
				} else {
					motor.stop();
				}
			} catch (Exception e){
				System.out.println("!! Motor or sensor is disconnected.");
				motor = new Motor(motord);
				ts = new TouchSensor(tsd);
			}
		}
	}

}
