# ev3dev-lang-java
A ev3dev unified language binding for Java.

A unified language binding that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

### Stage
- [x] Device class
- [x] Motor class ([http://www.ev3dev.org/docs/drivers/tacho-motor-class/](http://www.ev3dev.org/docs/drivers/tacho-motor-class/))
- [ ] Large-motor class (inherits from Motor)
- [ ] Medium-motor class (inherits from Motor)
- [ ] Generic DC Motor class ([http://www.ev3dev.org/docs/drivers/dc-motor-class/](http://www.ev3dev.org/docs/drivers/dc-motor-class/))
- [ ] Servo Motor class ([http://www.ev3dev.org/docs/drivers/servo-motor-class/](http://www.ev3dev.org/docs/drivers/servo-motor-class/))
- [ ] Generic LED class ([https://www.kernel.org/doc/Documentation/leds/leds-class.txt](https://www.kernel.org/doc/Documentation/leds/leds-class.txt))
- [ ] Generic button class
- [ ] Sensor class ([http://www.ev3dev.org/docs/drivers/lego-sensor-class/](http://www.ev3dev.org/docs/drivers/lego-sensor-class/))
- [ ] Generic I2C sensor
- [ ] Power supply
- [x] Lego Port
- [x] Sensor Port (Unnecessary) (inherits from LegoPort)
- [x] Motor Port (Unnecessary) (inherits from LegoPort)

### Duplication
You might saw another language binding for Java from here.<br>
This is not a duplication. See Issue [#154](https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203562758)<br>
<br>
Probably, he is using a wrong name for his project. As [suggested](https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203538860) by @dlech, he should use another name like ev3dev-lejos-compat.

## Example
In this moment of development stage, this language binding can do:
<pre>
public static void main(String[] args) throws Exception {
		LegoPort port = new LegoPort(LegoPort.PORT_A);
		Device device = new Device(port);
		Motor motor = new Motor(device);
		motor.setDutyCycleSP(50);
		motor.runForever();
		Thread.sleep(5000);
		motor.stop();
	}
</pre>