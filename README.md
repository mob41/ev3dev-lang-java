# ev3dev-lang-java [![Build Status](https://travis-ci.org/mob41/ev3dev-lang-java.svg?branch=master)](https://travis-ci.org/mob41/ev3dev-lang-java) [![unstable](http://badges.github.io/stability-badges/dist/unstable.svg)](https://github.com/mob41/ev3dev-lang-java/releases/tag/unstable-0.0.1-SNAPSHOT-B98)
A ev3dev unified language binding for Java, that followed with the [language wrapper specification](http://ev3dev-lang.readthedocs.org/en/latest/spec.html).

#### Duplication
You might saw another language binding for Java from https://github.com/ev3dev-lang-java/ev3dev-lang-java by @jabrena<br>
This is not a duplication. See this: https://github.com/ev3dev/ev3dev-lang/issues/154#issuecomment-203562758<br>
@jabrena 's library is a port from ```lsJOS``` codes to ```ev3dev```.

## Library version
Building on ```unstable``` releases. Version: ```0.0.2-SNAPSHOT```<br>
Target Kernel Version: ```v3.16.7-ckt26-10-ev3dev-ev3``` (Current) (v10)

```0.0.2-SNAPSHOT``` Changelog:
- Update for Kernel Version 10
- Removed speed_regulation
- Removed encoder_polarity
- Renamed stop_command to stop_action
- Motor will not stop until write stop to command
- Added max_speed
- Added subclass linear

## Downloads 
```ev3dev-lang-java``` is now in ```unstable``` stage. Nothing is "confirmed" as stable at all, except Motors, Touch/Color/Infrared Sensors, Buttons, LED. All components are successfully developed, but some of them aren't tested.

[[Latest Release (Development)]](https://github.com/mob41/ev3dev-lang-java/releases/latest)<br>
[[Unstable Release (Usable)]](https://github.com/mob41/ev3dev-lang-java/releases/tag/unstable-0.0.1-SNAPSHOT-B98)<br>
[Stable Release (Smooth)]<br>

<b>Others:</b><br>
[[Java API Document (JavaDoc)]](https://mob41.github.io/ev3dev-lang-java/javadoc)<br>
[[API Tutorials / Wiki, Help]](https://github.com/mob41/ev3dev-lang-java/wiki)<br>

<b>Problem reporting:</b><br>
[[API Issue Reporting]](https://github.com/mob41/ev3dev-lang-java/issues)<br>
[[Official Issue Reporting]](https://github.com/ev3dev/ev3dev-lang/issues)<br>

Please post issues so that I can fix it immediately, thank you!

## Tutorials
Check out the [wiki](https://github.com/mob41/ev3dev-lang-java/wiki) now!

## Stage
- [x] Development
- [x] JavaDoc
- [x] Unstable Release
- [ ] Autogen Templates
- [ ] Debugging
- [ ] Stable Release!
