package org.ev3dev.hardware.lcd;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.ev3dev.exception.EV3LibraryException;

/**
 * Provides an interface to draw to the EV3's LCD
 * @author Anthony
 *
 */
public class LCD {
	
	public static final String FB_PATH = "/dev/fb0";
	
	//This should not be hard-coded, however just for testing
	public static final int SCREEN_WIDTH = 178;
	
	public static final int SCREEN_HEIGHT = 128;

	public LCD() {
		
	}
	
	/**
	 * Draws a byte array into the EV3 framebuffer
	 * @param data Byte array to be drawn (128 (height) * 178 / 8 (length) = 3072 bytes)
	 * @throws EV3LibraryException
	 */
	public void draw(byte[] data) throws EV3LibraryException{
		File file = new File(FB_PATH);
		if (!file.exists()){
			throw new EV3LibraryException("The framebuffer device does not exist! Are you using a EV3?");
		}
		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
			out.write(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new EV3LibraryException("Unable to draw the LCD", e);
		}
	}

}
