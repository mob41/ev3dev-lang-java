# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)

An ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

If you are finding a document or a tutorial for a specific version, come to https://mob41.github.io/ev3dev-lang-java

## Library

This library supports ev3dev kernel version 15:

- ```v4.4.19-15-ev3dev-ev3``` for EV3
- ```v4.4.19-ti-rt-r41-15-ev3dev-bb.org``` for BeagleBone
- ```v4.4.19-15-ev3dev-rpi``` for Raspberry Pi 0/1
- ```v4.4.19-15-ev3dev-rpi2``` for Raspberry Pi 2/3

All the drivers and functions listed in the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html) are all supported, but without confirming the stability of those devices.

Other motors listed in http://www.ev3dev.org/docs/motors are also supported. ```DCMotor``` handles ```rcx-motor``` motors.

Other sensors listed in http://www.ev3dev.org/docs/sensors are still in heavy development.

Still in heavy development, please don't expect all things to be working, see issue [#15](https://github.com/mob41/ev3dev-lang-java/issues/15) for more details or tracking development stage.

## Release

This library currently **does not have any** releases, but snapshots (nightly builds) instead.

You can download the latest snapshot from the [OSSRH repository](https://oss.sonatype.org/content/groups/public/org/ev3dev/ev3dev-lang-java/) directly or via Maven:

1. Add the Sonatype snapshot repository.

    ```
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

	```
	<dependency>
		<groupId>org.ev3dev</groupId>
   		<artifactId>ev3dev-lang-java</artifactId>
   		<version>1.0.0-SNAPSHOT</version>
   	</dependency>
	```

## Build your own

You can build your own library with some steps.

1. Ensure you have Maven installed in your system

2. Clone this repository

    >git clone https://github.com/mob41/ev3dev-lang-java.git

3. Switch to the repository directory

    >cd ev3dev-lang-java

4. Build!

    >maven package

5. Switch to the ```target``` directory

    >cd target

6. There should have ```ev3dev-lang-java-x.x.x-x.jar``` and ```ev3dev-lang-java-x.x.x-x-jar-with-dependencies.jar``` in the directory.

## License

This project is based on the MIT License

>MIT License
>
>Copyright (c) 2016 Anthony Law
>
>Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
>
>The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
>
>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.