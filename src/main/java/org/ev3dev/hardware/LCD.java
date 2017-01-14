package org.ev3dev.hardware;

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
	public static final int SCREEN_WIDTH = 128;
	
	public static final int SCREEN_HEIGHT = 178;

	public LCD() {
		
	}
	
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
