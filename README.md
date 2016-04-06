# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)
A ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

Please post issues so that I can fix it immediately, thank you!

#### Duplication
You might saw another language binding for Java from https://github.com/ev3dev-lang-java/ev3dev-lang-java by @jabrena<br>
This is not a duplication. See this: https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203562758<br>
@jabrena 's library is a port from ```LeJOS``` codes to ```ev3dev```.

## Tutorial
As ```ev3dev-lang-java``` is still in development stage. There are no Jar(s) releases. But if you want to still use it, you can follow these steps:

1. Download the source and put it into your IDE or project.

	i. Clone via command-line:

	```
	git clone https://github.com/mob41/ev3dev-lang-java.git ev3dev-lang-java
	```
	
	ii. Or via GitHub [[Download ZIP]](https://github.com/mob41/ev3dev-lang-java/archive/master.zip)
2. Add this source to your IDE or change your classpath
3. Import the libraries

	```java
	//It is not recommended to import all libraries.
	import ev3dev.* 
	```
	
4. You have to create a port for your own Motor/Sensor.

	```java
	//Support all ports 1-4, A-D (Motors and sensors)
	LegoPort legoport = new LegoPort(LegoPort.PORT_1);

	//Support only motor ports: A-D or InvalidPortException will be thrown
	MotorPort motorport = new MotorPort(MotorPort.PORT_A);
	
	//Support only sensor ports: 1-4 or InvalidPortException will be thrown
	SensorPort sensorport = new SensorPort(SensorPort.PORT_2);
	```
	
5. In this case, we plugged in a LargeMotor into ```PORT_A```.

	```java
	//Support all types of motor excepting DCMotor and ServoMotor.
	Motor motor = new Motor(port); //You have to include your port here.
	
	//Alternative types of motors
	LargeMotor lm = new LargeMotor(altport1);
	MediumMotor mm = new MediumMotor(altport2);
	DCMotor dcm = new DCMotor(altport3);
	ServoMotor serm = new ServoMotor(altport4);
	
	//Make sure that all the ports are connecting to a correct type of motor.
	//If isn't, a InvalidPortException will be thrown.
	```
	
6. You can simply run your motor through these code:

	```java
	try {
		//Set the Motor DutyCycle speed to 60
		motor.setDutyCycle_SP(60);
		//Run the Motor forever
		motor.runForever();
		
		//The code is similar to other types of motors.
	catch (Exception e){
		//A Error will be thrown if the Motor is disconnected / Invalid.
		e.printStackTrace();
	}
	```
	
7. You can also control the motor via a touch sensor:

	```java
	//Create a new TouchSensor instance with a altport5 LegoPort
	TouchSensor ts = new TouchSensor(altport5);
	hile (true) {
		if (ts.isPressed()){
			motor.runForever();
		} else {
			motor.stop();
		}
	}
	```
	
8. Control it via a color sensor:

	```java
	//Create a new ColorSensor instance with a altport6 LegoPort
	ColorSensor cs = new ColorSensor(altport6);
	cs.setMode(PropertyDefaults.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE);
	while (true) {
		if (cs.getReflectedLightIntensity() < 25){
			motor.runForever()
		} else {
			motor.stop();
		}
	}
	```

## Stage
- [ ] Development
	- [x] Device class
	- [x] Motor class ([http://www.ev3dev.org/docs/drivers/tacho-motor-class/](http://www.ev3dev.org/docs/drivers/tacho-motor-class/))
	- [x] Large-motor class (inherits from Motor)
	- [x] Medium-motor class (inherits from Motor)
	- [x] Generic DC Motor class ([http://www.ev3dev.org/docs/drivers/dc-motor-class/](http://www.ev3dev.org/docs/drivers/dc-motor-class/))
	- [x] Servo Motor class ([http://www.ev3dev.org/docs/drivers/servo-motor-class/](http://www.ev3dev.org/docs/drivers/servo-motor-class/))
	- [x] Generic LED class ([https://www.kernel.org/doc/Documentation/leds/leds-class.txt](https://www.kernel.org/doc/Documentation/leds/leds-class.txt))
	- [ ] Generic button class
	- [x] Sensor class ([http://www.ev3dev.org/docs/drivers/lego-sensor-class/](http://www.ev3dev.org/docs/drivers/lego-sensor-class/))
	- [x] Generic I2C sensor
	- [x] Touch Sensor
	- [x] Color Sensor
	- [ ] Ultrasonic Sensor
	- [ ] Gyro Sensor
	- [ ] Infrared Sensor
	- [ ] Sound Sensor
	- [ ] Light Sensor
	- [x] Power supply
	- [x] Lego Port
	- [x] Sensor Port (Unnecessary) (inherits from LegoPort)
	- [x] Motor Port (Unnecessary) (inherits from LegoPort)
- [ ] Autogen Templates
- [ ] Debugging
- [ ] Release!
