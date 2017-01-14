package org.ev3dev.hardware;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.ev3dev.hardware.VirtualLCD;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VLCDFrame extends JFrame {

	private JPanel contentPane;
	private VirtualLCD lcd;
	private Thread thread;
	private JLabel lblImg;

	/**
	 * Create the frame.
	 */
	public VLCDFrame(VirtualLCD vlcd) {
		setTitle("ev3dev-lang-java VirtualLCD");
		this.lcd = vlcd;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 321);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblImg = new JLabel("");
		lblImg.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblImg, BorderLayout.CENTER);
		
		thread = new Thread(new Runnable(){

			public void run() {
				lblImg.setIcon(new ImageIcon(lcd.getImage()));
			}
			
		});
		thread.start();
	}

}
