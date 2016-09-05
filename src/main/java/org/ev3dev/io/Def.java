package org.ev3dev.io;

/**
 * Application-specified System Defaults
 * @author Anthony
 *
 */
public class Def { 	//Partial: Move properties constants to classes
	
	//System class path REMOVAL DONE!
	
	//Class names REMOVAL DONE!
	
	//Driver names REMOVAL DONE!
	
	//Motor Properties defaults REMOVAL DONE!
	
	//LED Properties defaults REMOVAL DONE!
	
	//Sensor Properties defaults REMOVAL DONE!
	
	//I2C Sensor properties REMOVAL DONE!
	
	//Touch Sensor properties REMOVAL DONE!
	
	//Color Sensor properties REMOVAL DONE!
	
	//Ultrasonic sensor properties REMOVAL DONE!
	
	//Gyro sensor properties
	
	public static final String PROPERTY_GYRO_SENSOR_ANGLE_REQUIRED_MODE = "GYRO-ANG";
	
	public static final int PROPERTY_GYRO_SENSOR_ANGLE_VALUE_INDEX = 0;
	
	public static final String PROPERTY_GYRO_SENSOR_RATE_REQUIRED_MODE = "GYRO-RATE";
	
	public static final int PROPERTY_GYRO_SENSOR_RATE_VALUE_INDEX = 0;
	
	//Infrared Sensor properties
	
	public static final String PROPERTY_INFRARED_SENSOR_PROXIMITY_REQUIRED_MODE = "IR-PROX";
	
	public static final int PROPERTY_INFRARED_SENSOR_PROXIMITY_VALUE_INDEX = 0;
	
	//Sound Sensor properties
	
	public static final String PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_REQUIRED_MODE = "DB";

	public static final int PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_VALUE_INDEX = 0;

	public static final String PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_REQUIRED_MODE = "DBA";
	
	public static final int PROPERTY_SOUND_SENSOR_SOUND_PRESSURE_LOW_VALUE_INDEX = 0;
	
	//Light Sensor properties

	public static final String PROPERTY_LIGHT_SENSOR_REFLECTED_REQUIRED_MODE = "REFLECT";

	public static final int PROPERTY_LIGHT_SENSOR_REFLECTED_VALUE_INDEX = 0;
	
	public static final String PROPERTY_LIGHT_SENSOR_AMBIENT_REQUIRED_MODE = "AMBIENT";

	public static final int PROPERTY_LIGHT_SENSOR_AMBIENT_VALUE_INDEX = 0;
	
	//Power supply properties
	
	public static final String PROPERTY_MEASURED_CURRENT = "measured_current";
	
	public static final String PROPERTY_MEASURED_VOLTAGE = "measured_voltage";
	
	public static final String PROPERTY_MAX_VOLTAGE = "max_voltage";
	
	public static final String PROPERTY_MIN_VOLTAGE = "min_voltage";
	
	public static final String PROPERTY_TECHNOLOGY = "technology";
	
	public static final String PROPERTY_TYPE = "type";
	
	//Buttons properties
	
	public static final String PROPERTY_EV3_BUTTON_SYSTEM_EVENT_PATH = "/dev/input/by-path/platform-gpio-keys.0-event";
	
	public static final int PROPERTY_EV3_BUTTON_UP = 103;
	
	public static final int PROPERTY_EV3_BUTTON_DOWN = 108;
	
	public static final int PROPERTY_EV3_BUTTON_LEFT = 105;
	
	public static final int PROPERTY_EV3_BUTTON_RIGHT = 106;
	
	public static final int PROPERTY_EV3_BUTTON_ENTER = 28;
	
	public static final int PROPERTY_EV3_BUTTON_BACKSPACE = 14;
	
	//Commands defaults REMOVAL DONE
	
}
