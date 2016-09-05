package org.ev3dev.io;

/**
 * Application-specified System Defaults
 * @author Anthony
 *
 */
public class Def { 	//Partial: Move properties constants to classes
	
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
