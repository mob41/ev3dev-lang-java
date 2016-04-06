package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class I2CSensor extends Sensor {

	public I2CSensor(LegoPort port) throws InvalidPortException, InvalidSensorException, IOException {
		super(port);
		if (!this.getDriverName().equals(Def.I2CSENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("The specified port is not a I2C sensor.");
		}
	}
	
	public String getFirmwareVersion() throws IOException{
		return this.getAttribute(Def.PROPERTY_FIRMWARE_VERSION);
	}
	
	public int getPollMs() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_POLL_MS);
		return Integer.parseInt(str);
	}
	
	public void setPollMs(int ms) throws IOException{
		this.setAttribute(Def.PROPERTY_POLL_MS, Integer.toString(ms));
	}
}
