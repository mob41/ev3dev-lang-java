package ev3dev.hardware;

import java.io.File;
import java.io.IOException;

import ev3dev.exception.InvalidLEDException;
import ev3dev.io.Def;
import ev3dev.io.Sysclass;

/***
 * Any device controlled by the generic LED driver. 
 * See <a href="https://www.kernel.org/doc/Documentation/leds/leds-class.txt">
 * https://www.kernel.org/doc/Documentation/leds/leds-class.txt</a> 
 * for more details.
 * @author Anthony
 *
 */
public class LED extends Device{
	
	public static final int LEFT = 0;
	
	public static final int RIGHT = 1;
	
	public static final int GREEN = 0;
	
	public static final int RED = 1;
	
	public LED(int leftRightField, int colorField) throws InvalidLEDException{
		super(Def.LED_CLASS_NAME);
		if (leftRightField != 0 && leftRightField != 1){
			throw new InvalidLEDException("You are not specifying a EV3_LEFT_LED or EV3_RIGHT_LED field!");
		} else if (colorField != 0 && colorField != 1){
			throw new InvalidLEDException("You are not specifying a EV3_LED_GREEN or EV3_LED_RED field!");
		}
		String direction = leftRightField == 0 ? "left" : "right";
		String color = colorField == 0 ? "green" : "red";
		
		this.setSubClassName("ev3:" + direction + ":" + color + ":ev3dev");
	}
	
	public LED(String ledName) throws InvalidLEDException{
		super(Def.LED_CLASS_NAME);
		File file = new File(Def.SYSTEM_CLASS_PATH + Def.LED_CLASS_NAME + "/" + ledName);
		if (!file.exists()){
			throw new InvalidLEDException("The specified LED does not exist");
		}
		this.setSubClassName(ledName);
	}
	
	public int getMaxBrightness() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_MAX_BRIGHTNESS);
		return Integer.parseInt(str);
	}
	
	public int getBrightness() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_BRIGHTNESS);
		return Integer.parseInt(str);
	}
	
	public void setBrightness(int brightness) throws IOException{
		this.setAttribute(Def.PROPERTY_BRIGHTNESS, Integer.toString(brightness));
	}
	
	public String getTriggersViaString() throws IOException{
		return this.getAttribute(Def.PROPERTY_TRIGGER);
	}
	
	public String[] getTriggers() throws IOException{
		String str = getTriggersViaString();
		return Sysclass.separateSpace(str);
	}
	
	public String getTrigger() throws IOException{
		return this.getAttribute(Def.PROPERTY_TRIGGER);
	}
	
	public void setTrigger(String selector) throws IOException{
		this.setAttribute(Def.PROPERTY_TRIGGER, selector);
	}
	
	public int getDelay_On() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_DELAY_ON);
		return Integer.parseInt(str);
	}
	
	public int getDelay_Off() throws IOException{
		String str = this.getAttribute(Def.PROPERTY_DELAY_OFF);
		return Integer.parseInt(str);
	}
	
	public void setDelay_On(int delay_on) throws IOException{
		this.setAttribute(Def.PROPERTY_DELAY_ON, Integer.toString(delay_on));
	}
	
	public void setDelay_Off(int delay_off) throws IOException{
		this.setAttribute(Def.PROPERTY_DELAY_OFF, Integer.toString(delay_off));
	}
}