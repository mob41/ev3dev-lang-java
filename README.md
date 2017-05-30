# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java) [![CodeFactor](https://www.codefactor.io/repository/github/mob41/ev3dev-lang-java/badge)](https://www.codefactor.io/repository/github/mob41/ev3dev-lang-java)

>This project isn't dead. Just "not very active" in development. It works at all!

An ev3dev unified language binding for Java, not to be confused with [jabrena/ev3dev-lang-java](https://github.com/ev3dev-lang-java/ev3dev-lang-java/) which interfaces LeJOS. It follows with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

Library tutorials and document can be found [here](https://mob41.github.io/ev3dev-lang-java).

## Library

This library supports ev3dev kernel version 20 (latest kernel):

- ```v4.4.61-20-ev3dev-ev3``` for EV3
- ```v4.4.61-ti-rt-r98-20-ev3dev-bb.org``` for BeagleBone
- ```v4.4.61-20-ev3dev-rpi``` for Raspberry Pi 0/1
- ```v4.4.61-20-ev3dev-rpi2``` for Raspberry Pi 2/3

All the drivers and functions listed in the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html) are all supported, but without confirming the stability of those devices.

Other motors listed in http://www.ev3dev.org/docs/motors are all supported. ```DCMotor``` handles ```rcx-motor``` motors.

Official LEGO sensors are all supported. But (some) other sensors listed in http://www.ev3dev.org/docs/sensors are currently not implemented.

## Release

This library currently **does not have any** releases, but snapshots (nightly builds) instead.

You can download the latest snapshot from the [OSSRH repository](https://oss.sonatype.org/content/groups/public/org/ev3dev/ev3dev-lang-java/) directly or via Maven:

1. Add the Sonatype snapshot repository.

    ```xml
    <repositories>
    	<repository>
       		<id>oss-sonatype</id>
        	<name>oss-sonatype</name>
        	<url>https://oss.sonatype.org/content/repositories/snapshots/</url>
        	<snapshots>
         		<enabled>true</enabled>
        	</snapshots>
    	</repository>
	</repositories>
    ```
    
2. Add the dependency.

	```xml
	<dependency>
		<groupId>org.ev3dev</groupId>
   		<artifactId>ev3dev-lang-java</artifactId>
   		<version>1.0.0-SNAPSHOT</version>
   	</dependency>
	```

## Build your own

>TODO: Modify ```pom.xml``` to build/package ev3dev-lang-java jars (it is removed since moving to Maven Central)

## License

[tl;dr](https://tldrlegal.com/license/mit-license) This project is licensed under the MIT License.