# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)

An ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

## Library

This library currently legacy supports ev3dev kernel version 15, but does not support new drivers listed since kernel version 11, and optional drivers in [http://www.ev3dev.org/docs/sensors/](http://www.ev3dev.org/docs/sensors/). 

Still in heavy development, see issue [#15](https://github.com/mob41/ev3dev-lang-java/issues/15) for more details or tracking development stage.

## Build your own

You can build your own library with some steps.

1. Ensure you have Maven installed in your system

2. Clone this repository

    ```git clone https://github.com/mob41/ev3dev-lang-java.git```

3. Switch to the repository directory

    ```cd ev3dev-lang-java```

4. Build!

    ```maven package```

5. Switch to the ```target``` directory

    ```cd target```

6. There should have ```ev3dev-lang-java-x.x.x-x.jar``` and ```ev3dev-lang-java-x.x.x-x-jar-with-dependencies.jar``` in the directory.