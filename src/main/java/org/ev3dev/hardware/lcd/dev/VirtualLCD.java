package org.ev3dev.hardware.lcd.dev;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.ev3dev.hardware.lcd.LCD;

/**
 * Provides an interface for developers to emulate the LCD in ev3
 * @author Anthony
 *
 */
public class VirtualLCD extends LCD{

	private BufferedImage image;
	
	private byte[] lastbuf = null;
	
	/**
	 * Creates a new virtual LCD instance
	 */
	public VirtualLCD(){
		//image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		try {
			image = ImageIO.read(VirtualLCD.class.getResource("/org/ev3dev/hardware/defaultvirtuallcd.fw.png"));
		} catch (IOException e) {
		}
	}
	
	/**
	 * Returns the image drawn
	 * @return BufferedImage
	 */
	public BufferedImage getImage(){
		return image;
	}
	
	/**
	 * Starts up an UI showing the virtual LCD
	 */
	public void showVLCD(){
		VLCDFrame frame = new VLCDFrame(this);
		frame.setVisible(true);
		frame.setTitle(this.hashCode() + " - VirtualLCD");
	}
	
	/**
	 * This function overrides the original draw() function to draw directly into a BufferedImage
	 */
	@Override
	public void draw(byte[] data){
		if (data == null){
			System.out.println("Null data");
			return;
		}
		Graphics g = image.getGraphics();
		
		int bitPos;
		for (int i = 0; i < 128; i++){
			bitPos = 0;
			for (int j = 0; j < 178; j++){
				if (bitPos > 7){
					bitPos = 0;
				}
				
				//System.out.println("(" + j + ", " + i + ")");
				byte bit = (byte) ((data[i * 24 + j / 8] >> bitPos) & 1);
				
				if (bit != 0x00){
					System.out.println("Draw: 0x" + Integer.toHexString(bit));
				}
				
				if ((bit & 0xff) == 0){
					//System.out.println("Black");
					g.setColor(Color.BLACK);
					g.drawLine(j, i, j, i);
				} else {
					//System.out.println("White");
					g.setColor(Color.WHITE);
					g.drawLine(j, i, j, i);
				}
				
				bitPos++;
			}
		}
		g.dispose();
	}
}
