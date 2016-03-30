package ev3dev.hardware.ports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.AccessControlException;

import ev3dev.exception.InvalidPortException;
import ev3dev.io.Sysclass;

/***
 * LegoPort class for fetching data from a specified port.<br>
 * <br>
 * You can also use a <b>MotorPort</b> or a <b>SensorPort</b> for your device.
 * @author Anthony
 *
 */
public class LegoPort {
	
	private int port = 0;
	
	public static final int PORT_1 = 0;
	
	public static final int PORT_2 = 1;
	
	public static final int PORT_3 = 2;
	
	public static final int PORT_4 = 3;
	
	public static final int PORT_A = 4;
	
	public static final int PORT_B = 5;
	
	public static final int PORT_C = 6;
	
	public static final int PORT_D = 7;
	
	public LegoPort(int port) throws InvalidPortException{
		if (port < 0){
			throw new InvalidPortException("Port is lower than 0, Port: " + port);
		} else if (port > 7){
			throw new InvalidPortException("Port is higher than 7, Port: " + port);
		}
		this.port = port;
	}
	
	public String getAddress(){
		String address = null;
		try { //TODO I shouldn't do like this. Do Error handling.
			address = Sysclass.getProperty("lego-port", "port" + port, "address");
		} catch (Exception e){
			e.printStackTrace();
		}
		return address;
	}
	
	public String getDriverName(){
		String drivername = null;
		try {
			drivername = Sysclass.getProperty("lego-port", "port" + port, "driver_name");
		} catch (Exception e){
			e.printStackTrace();
		}
		return drivername;
	}
	
	public String[] getModes(){
		String[] modes = null;
		//TODO This
		return modes;
	}
	
	public String getMode(){
		String mode = null;
		try {
			mode = Sysclass.getProperty("lego-port", "port" + port, "mode");
		} catch (Exception e){
			e.printStackTrace();
		}
		return mode;
	}
	
	public void setMode(String mode){
		try {
			Sysclass.setProperty("lego-port", "port" + port, "mode", mode);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void setDevice(String driver){
		try {
			Sysclass.setProperty("lego-port", "port" + port, "set_device", driver);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public String getStatus(){
		String status = null;
		try {
			status = Sysclass.getProperty("lego-port", "port" + port, "status");
		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}
}
