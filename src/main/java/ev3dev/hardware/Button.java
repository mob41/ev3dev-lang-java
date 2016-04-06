package ev3dev.hardware;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import ev3dev.exception.InvalidButtonException;
import ev3dev.io.Def;

/***
 * Provides a generic button reading mechanism that can be adapted to platform specific implementations.
 *  Each platform¡¦s specific button capabilites are enumerated in the ¡¥platforms¡¦ section of this specification.
 * @author Anthony
 *
 */
public class Button {
	
	public static final int UP = Def.PROPERTY_EV3_BUTTON_UP;
	
	public static final int DOWN = Def.PROPERTY_EV3_BUTTON_DOWN;
	
	public static final int LEFT = Def.PROPERTY_EV3_BUTTON_LEFT;
	
	public static final int RIGHT = Def.PROPERTY_EV3_BUTTON_RIGHT;
	
	public static final int ENTER = Def.PROPERTY_EV3_BUTTON_ENTER;
	
	public static final int BACKSPACE = Def.PROPERTY_EV3_BUTTON_BACKSPACE;
	
	private int button;
	
	public Button(int button) throws InvalidButtonException{
		if (button != UP && button != DOWN && button != LEFT &&
				button != RIGHT && button != ENTER && button != ENTER &&
				button != BACKSPACE){
			throw new InvalidButtonException("The button that you specified does not exist. Better use the integer fields like Button.UP");
		}
		this.button = button;
	}
	
	public boolean isPressed(){
		try {
			DataInputStream in = new DataInputStream(new FileInputStream(Def.PROPERTY_EV3_BUTTON_SYSTEM_EVENT_PATH));
			byte[] val = new byte[16];
			in.readFully(val);
			in.close();
			return test_bit(button, val);
		} catch (IOException e){
			System.err.println("### ERROR MESSAGE ###\nError: Unexpected error! Report an issue to \"mob41/ev3dev-lang-java\" now, with logs!\n === STACK TRACE ===");
			e.printStackTrace();
			System.err.println("=== END STACK TRACE ===\nError: Unexpected error! Report an issue to \"mob41/ev3dev-lang-java\" now, with logs!\n ### END MESSAGE ###");
			return false;
		}
	}
	
	public static boolean test_bit(int bit, byte[] bytes){
	    return ((bytes[bit / 8] & (1 << (bit % 8))) == 1 ? false : true);
	}
}
