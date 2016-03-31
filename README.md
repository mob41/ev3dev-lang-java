# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)
A ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

Really, really, I don't know why I programmed too fast. I must missed something. This version of API is currently unstable, but it is able to use.<br>
I can't test some classes as I don't have those modules, such as DCMotor, ServoMotor, LED, I2CSensor.<br>
Please post issues so that I can fix it immediately, thank you!

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
