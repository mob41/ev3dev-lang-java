# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)
A ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

Really, really, I don't know why I programmed too fast. I must missed something. This version of API is currently unstable, but it is able to use.<br>
I can't test some classes as I don't have those modules, such as DCMotor, ServoMotor, LED, I2CSensor.

Please post issues so that I can fix it immediately, thank you!

## TODO
- The auto-reconnect is not implemented (Base Device Class). Though, the code is still working. But it limited you to connect that port without plugging out until the application stops. Sorry for inconvenient.

## Tutorial
1. To have to create a port for your own Motor/Sensor.
```java
//Support all ports 1-4, A-D (Motors and sensors)
LegoPort legoport = new LegoPort(LegoPort.PORT_1);

//Support only motor ports: A-D or InvalidPortException will be thrown
MotorPort motorport = new MotorPort(MotorPort.PORT_A);

//Support only sensor ports: 1-4 or InvalidPortException will be thrown
SensorPort sensorport = new SensorPort(SensorPort.PORT_2);
```

2. Before creating a motor, you have to create a device first.
```java
Device device = new Device(legoport); //Specifying your LegoPort
```

3. In this case, we plugged in a LargeMotor into ``PORT_A``. There're different types of motor.
```java
//Support all types of motor excepting DCMotor and ServoMotor.
Motor motor = new Motor(device); //You have to include your port here.
```

4. You can run your robot now! (Probably not this :()
```java
motor.setDutyCycle_SP(60);
motor.runForever();
```

5. To make use of the touch sensor:
```java
Device tsd = new Device(sensorport);
TouchSensor ts = new TouchSensor(tsd);
while (true) {
	if (ts.isPressed()){
		motor.runForever();
	} else {
		motor.stop();
	}
}
```

6. To make use of the color sensor:
```java
Device csd = new Device(sensorport);
ColorSensor cs = mew ColorSensor(csd);
cs.setMode(PropertyDefaults.PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE);
while (true) {
	if (cs.getReflectedLightIntensity() < 25){
		motor.runForever()
	} else {
		motor.stop();
	}
}
```

### Stage
- [ ] Device class
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

### Duplication
You might saw another language binding for Java from [here](https://github.com/ev3dev-lang-java/ev3dev-lang-java).<br>
This is not a duplication. See Issue [#154](https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203562758)<br>
<br>
Probably, he is using a wrong name for his project. As [suggested](https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203538860) by @dlech, he should use another name like ev3dev-lejos-compat.
