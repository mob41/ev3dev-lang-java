package ev3dev.hardware;

import java.io.File;
import java.io.IOException;

import ev3dev.exception.InvalidLEDException;
import ev3dev.io.PropertyDefaults;
import ev3dev.io.Sysclass;

/***
 * Any device controlled by the generic LED driver. 
 * See <a href="https://www.kernel.org/doc/Documentation/leds/leds-class.txt">
 * https://www.kernel.org/doc/Documentation/leds/leds-class.txt</a> 
 * for more details.
 * @author Anthony
 *
 */
public class LED {
	
	private String devicename;
	
	public LED(String devicename) throws InvalidLEDException{
		File file = new File(Sysclass.SYSTEM_CLASS_PATH + PropertyDefaults.LED_CLASS_NAME + "/" + devicename);
		if (!file.exists()){
			throw new InvalidLEDException("The specified LED does not exist");
		}
		this.devicename = devicename;
	}
	
	public int getMaxBrightness() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_MAX_BRIGHTNESS);
		return Integer.parseInt(str);
	}
	
	public int getBrightness() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_BRIGHTNESS);
		return Integer.parseInt(str);
	}
	
	public void setBrightness(int brightness) throws IOException{
		Sysclass.setProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_BRIGHTNESS, Integer.toString(brightness));
	}
	
	public String getTriggersViaString() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_TRIGGERS);
	}
	
	public String[] getTriggers() throws IOException{
		String str = getTriggersViaString();
		return Sysclass.separateSpace(str);
	}
	
	public String getTrigger() throws IOException{
		return Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_TRIGGER);
	}
	
	public void setTrigger(String selector) throws IOException{
		Sysclass.setProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_TRIGGER, selector);
	}
	
	public int getDelay_On() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_DELAY_ON);
		return Integer.parseInt(str);
	}
	
	public int getDelay_Out() throws IOException{
		String str = Sysclass.getProperty(PropertyDefaults.LED_CLASS_NAME, devicename, PropertyDefaults.PROPERTY_DELAY_OFF);
		return Integer.parseInt(str);
	}
}