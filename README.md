# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java) [![unstable](http://badges.github.io/stability-badges/dist/unstable.svg)](https://github.com/mob41/ev3dev-lang-java/releases/tag/unstable-0.0.1-SNAPSHOT-B98)
A ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

#### Duplication
You might saw another language binding for Java from https://github.com/ev3dev-lang-java/ev3dev-lang-java by @jabrena<br>
This is not a duplication. See this: https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203562758<br>
@jabrena 's library is a port from ```lsJOS``` codes to ```ev3dev```.

## Downloads 
```ev3dev-lang-java``` is now in ```unstable``` stage. Nothing is "confirmed" as stable at all. All components are successfully developed, but some of them aren't tested.

[Unstable Release (0.0.1-SNAPSHOT Build 65)](https://github.com/mob41/ev3dev-lang-java/releases/tag/unstable-0.0.1-SNAPSHOT-B65)<br>
[JavaDoc Web](https://mob41.github.io/ev3dev-lang-java/)<br>
[Wiki / Help](https://github.com/mob41/ev3dev-lang-java/wiki)<br>
<br>
Please post issues so that I can fix it immediately, thank you!

## Tutorial
Check out the [wiki](https://github.com/mob41/ev3dev-lang-java/wiki) now!

## Stage
- [x] Development
	- [x] Device class
	- [x] Motor class ([http://www.ev3dev.org/docs/drivers/tacho-motor-class/](http://www.ev3dev.org/docs/drivers/tacho-motor-class/))
	- [x] Large-motor class (inherits from Motor)
	- [x] Medium-motor class (inherits from Motor)
	- [x] Generic DC Motor class ([http://www.ev3dev.org/docs/drivers/dc-motor-class/](http://www.ev3dev.org/docs/drivers/dc-motor-class/))
	- [x] Servo Motor class ([http://www.ev3dev.org/docs/drivers/servo-motor-class/](http://www.ev3dev.org/docs/drivers/servo-motor-class/))
	- [x] Generic LED class ([https://www.kernel.org/doc/Documentation/leds/leds-class.txt](https://www.kernel.org/doc/Documentation/leds/leds-class.txt))
	- [x] Generic button class
	- [x] Sensor class ([http://www.ev3dev.org/docs/drivers/lego-sensor-class/](http://www.ev3dev.org/docs/drivers/lego-sensor-class/))
	- [x] Generic I2C sensor
	- [x] Touch Sensor
	- [x] Color Sensor
	- [x] Ultrasonic Sensor
	- [x] Gyro Sensor
	- [x] Infrared Sensor
	- [x] Sound Sensor
	- [x] Light Sensor
	- [x] Power supply
	- [x] Lego Port
	- [x] Sensor Port (Unnecessary) (inherits from LegoPort)
	- [x] Motor Port (Unnecessary) (inherits from LegoPort)
- [x] JavaDoc
- [x] Unstable Release
- [ ] Autogen Templates
- [ ] Debugging
- [ ] Stable Release!
