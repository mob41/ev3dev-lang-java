package ev3dev.io;

public class PropertyDefaults {
	
	//Class names
	
	public static final String MOTOR_CLASS_NAME = "tacho-motor";
	
	public static final String DC_MOTOR_CLASS_NAME = "dc-motor";
	
	public static final String SERVO_MOTOR_CLASS_NAME = "servo-motor";
	
	public static final String LED_CLASS_NAME = "leds";
	
	public static final String SENSOR_CLASS_NAME = "lego-sensor";
	
	public static final String SUB_SENSOR_CLASS_NAME = "sensor";
	
	public static final String SUB_MOTOR_CLASS_NAME = "motor";
	
	public static final String POWER_SUPPLY_CLASS_NAME = "power_supply";
	
	//Driver names
	
	public static final String I2CSENSOR_DRIVER_NAME = "nxt-i2c-sensor";
	
	public static final String TOUCH_SENSOR_DRIVER_NAME_EV3 = "lego-ev3-touch";
	
	public static final String TOUCH_SENSOR_DRIVER_NAME_NXT = "lego-nxt-touch";
	
	public static final String COLOR_SENSOR_DRIVER_NAME = "lego-ev3-color";
	
	//Properties defaults
	
	public static final String PROPERTY_ADDRESS = "address";
	
	public static final String PROPERTY_COMMAND = "command";
	
	public static final String PROPERTY_COMMANDS = "commands";
	
	public static final String PROPERTY_COUNT_PER_ROT = "count_per_rot";
	
	public static final String PROPERTY_DRIVER_NAME = "driver_name";
	
	public static final String PROPERTY_DUTY_CYCLE = "duty_cycle";
	
	public static final String PROPERTY_DUTY_CYCLE_SP = "duty_cycle_sp";
	
	public static final String PROPERTY_ENCODER_POLARITY = "encoder_polarity";
	
	public static final String PROPERTY_POLARITY = "polarity";
	
	public static final String PROPERTY_POSITION = "position";
	
	public static final String PROPERTY_POSITION_P = "hold_pid/Kp";
	
	public static final String PROPERTY_POSITION_I = "hold_pid/Ki";
	
	public static final String PROPERTY_POSITION_D = "hold_pid/Kd";
	
	public static final String PROPERTY_POSITION_SP = "position_sp";
	
	public static final String PROPERTY_SPEED = "speed";
	
	public static final String PROPERTY_SPEED_SP = "speed_sp";
	
	public static final String PROPERTY_RAMP_UP_SP = "ramp_up_sp";
	
	public static final String PROPERTY_RAMP_DOWN_SP = "ramp_down_sp";
	
	public static final String PROPERTY_SPEED_REGULATION_ENABLED = "speed_regulation_enabled";
	
	public static final String PROPERTY_SPEED_REGULATION_P = "speed_pid/Kp";
	
	public static final String PROPERTY_SPEED_REGULATION_I = "speed_pid/Ki";
	
	public static final String PROPERTY_SPEED_REGULATION_D = "speed_pid/Kd";
	
	public static final String PROPERTY_STATE = "state";
	
	public static final String PROPERTY_STOP_COMMAND = "stop_command";
	
	public static final String PROPERTY_STOP_COMMANDS = "stop_commands";
	
	public static final String PROPERTY_TIME_SP = "time_sp";
	
	public static final String PROPERTY_MAX_PULSE_SP = "max_pulse_sp";
	
	public static final String PROPERTY_MID_PULSE_SP = "mid_pulse_sp";
	
	public static final String PROPERTY_MIN_PULSE_SP = "min_pulse_sp";
	
	public static final String PROPERTY_RATE_SP = "rate_sp";
	
	//LED Properties defaults
	
	public static final String PROPERTY_MAX_BRIGHTNESS = "max_brightness";
	
	public static final String PROPERTY_BRIGHTNESS = "brightness";
	
	public static final String PROPERTY_TRIGGERS = "triggers";
	
	public static final String PROPERTY_TRIGGER = "trigger";
	
	public static final String PROPERTY_DELAY_ON = "delay_on";
	
	public static final String PROPERTY_DELAY_OFF = "delay_off";
	
	//Sensor Properties defaults
	
	public static final String PROPERTY_DECIMALS = "decimals";
	
	public static final String PROPERTY_MODE = "mode";
	
	public static final String PROPERTY_MODES = "modes";
	
	public static final String PROPERTY_NUM_VALUES = "num_values";
	
	public static final String PROPERTY_UNITS = "units";
	
	//I2C Sensor properties
	
	public static final String PROPERTY_FIRMWARE_VERSION = "fw_version";
	
	public static final String PROPERTY_POLL_MS = "poll_ms";
	
	//Touch Sensor properties
	
	public static final String PROPERTY_TOUCH_REQUIRED_MODE = "TOUCH";
	
	public static final int PROPERTY_TOUCH_VALUE_INDEX = 0;
	
	//Color Sensor properties
	
	public static final String PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY = "reflected_light_intensity";
	
	public static final String PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_REQUIRED_MODE = "COL-REFLECT";
	
	public static final int PROPERTY_COLOR_SENSOR_REFLECTED_LIGHT_INTENSITY_VALUE_INDEX = 0;
	
	public static final String PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY = "ambient_light_intensity";
	
	public static final String PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_REQUIRED_MODE = "AMBIENT";
	
	public static final int PROPERTY_COLOR_SENSOR_AMBIENT_LIGHT_INTENSITY_VALUE_INDEX = 0;
	
	public static final String PROPERTY_COLOR_SENSOR_COLOR = "color";
	
	public static final String PROPERTY_COLOR_SENSOR_COLOR_REQUIRED_MODE = "COL-COLOR";
	
	public static final int PROPERTY_COLOR_SENSOR_COLOR_VALUE_INDEX = 0;
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_R = "red";
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_R_REQUIRED_MODE = "RGB-RAW";
	
	public static final int PROPERTY_COLOR_SENSOR_RGB_R_VALUE_INDEX = 0;
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_G = "green";
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_G_REQUIRED_MODE = "RGB-RAW";
	
	public static final int PROPERTY_COLOR_SENSOR_RGB_G_VALUE_INDEX = 1;
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_B = "blue";
	
	public static final String PROPERTY_COLOR_SENSOR_RGB_B_REQUIRED_MODE = "RGB-RAW";
	
	public static final int PROPERTY_COLOR_SENSOR_RGB_B_VALUE_INDEX = 2;
	
	//Power supply properties
	
	public static final String PROPERTY_MEASURED_CURRENT = "measured_current";
	
	public static final String PROPERTY_MEASURED_VOLTAGE = "measured_voltage";
	
	public static final String PROPERTY_MAX_VOLTAGE = "max_voltage";
	
	public static final String PROPERTY_MIN_VOLTAGE = "min_voltage";
	
	public static final String PROPERTY_TECHNOLOGY = "technology";
	
	public static final String PROPERTY_TYPE = "type";
	
	//Commands defaults
	
	public static final String COMMAND_RUN_FOREVER = "run-forever";
	
	public static final String COMMAND_RUN_TO_ABS_POS = "run-to-abs-pos";
	
	public static final String COMMAND_RUN_TO_REL_POS = "run-to-rel-pos";
	
	public static final String COMMAND_RUN_TIMED = "run-timed";
	
	public static final String COMMAND_RUN_DIRECT = "run-direct";
	
	public static final String COMMAND_STOP = "stop";
	
	public static final String COMMAND_RESET = "reset";

	public static final String COMMAND_RUN = "run";

	public static final String COMMAND_FLOAT = "float";
	
}
