package org.ev3dev.hardware.lcd.dev;

import java.awt.image.BufferedImage;

import org.ev3dev.hardware.lcd.LCDGraphics;

/**
 * This is a fake implementation of the VirtualLCD class
 * that this implementation will override the getImage()
 * method from VirtualLCD to provide the BufferedImage
 * from LCDGraphics's getImage() method directly.<br>
 * <br>
 * This class is intent for development purpose. The
 * black and white colors are inverted from the
 * method in LCDGraphics
 * @author Anthony
 *
 */
public class FakeVirtualLCD extends VirtualLCD {

	private LCDGraphics g;
	
	public FakeVirtualLCD(LCDGraphics g) {
		this.g = g;
	}
	
	@Override
	public void showVLCD(){
		VLCDFrame frame = new VLCDFrame(this);
		frame.setVisible(true);
		frame.setTitle(this.hashCode() + " - FakeVirtualLCD");
	}
	
	/**
	 * Returns the BufferedImage from LCDGraphics' getImage() method
	 */
	@Override
	public BufferedImage getImage(){
		return g.getImage();
	}
	
	/**
	 * Calling this method will have no effect.
	 */
	@Override
	public void draw(byte[] data){
		return;
	}

}
