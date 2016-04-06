package ev3dev.tests;

import java.util.Arrays;

import ev3dev.hardware.Button;
import ev3dev.hardware.LED;

public class TestOnEV3 {

	public static void main(String[] args) throws Exception {
		Button but = new Button(Button.UP);
		while (true){
			if (but.isPressed()){
				LED led = new LED(LED.LEFT, LED.GREEN);
				led.setBrightness(255);
				System.out.println(Arrays.deepToString(led.getTriggers()));
				for (int i = 255; i > 0; i--){
					led.setBrightness(i);
				}
			}
		}
	}

}
