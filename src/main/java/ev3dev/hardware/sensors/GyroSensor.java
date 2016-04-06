package ev3dev.hardware.sensors;

import java.io.IOException;

import ev3dev.exception.InvalidModeException;
import ev3dev.exception.InvalidPortException;
import ev3dev.exception.InvalidSensorException;
import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Def;

public class GyroSensor extends Sensor {
	
	private boolean autoSwitchMode = true;

	public GyroSensor(LegoPort port) throws IOException, InvalidPortException, InvalidSensorException {
		super(port);
		if (!this.getDriverName().equals(Def.GYRO_SENSOR_DRIVER_NAME)){
			throw new InvalidSensorException("Can't create a GyroSensor instance if port isn't connected to a GyroSensor!");
		}
	}
	
	public int getAngle() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_GYRO_SENSOR_ANGLE_VALUE_INDEX);
		return Integer.parseInt(str);
	}
	
	public int getRate() throws InvalidModeException, IOException{
		if (!this.getMode().equals(Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE)){
			if (autoSwitchMode){
				this.setMode(Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE);
			} else {
				throw new InvalidModeException("[Auto-switch is off] You are not using a correct mode(" + Def.PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE + ")! Yours: " + this.getMode());
			}
		}
		String str = this.getAttribute("value" + Def.PROPERTY_GYRO_SENSOR_RATE_VALUE_INDEX);
		return Integer.parseInt(str);
	}

	public void setAutoSwitchMode(boolean autoswitch){
		this.autoSwitchMode = autoswitch;
	}
	
	public boolean isAutoSwitchMode(){
		return autoSwitchMode;
	}

}
