# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java)

An ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

## Library

This library currently legacy supports ev3dev kernel version 15, but does not support new drivers listed since kernel version 11, and optional drivers in [http://www.ev3dev.org/docs/sensors/](http://www.ev3dev.org/docs/sensors/). 

Still in heavy development, see issue [#15](https://github.com/mob41/ev3dev-lang-java/issues/15) for more details or tracking development stage.

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