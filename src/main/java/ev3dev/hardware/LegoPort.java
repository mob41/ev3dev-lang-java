package ev3dev.hardware;

import ev3dev.EV3;

public class LegoPort {
	
	private static String SYSTEM_CLASS_PATH = "/sys/class/lego-port";

	private int port = 0;
	
	private String address;
	
	private String driver_name;
	
	private String[] modes;
	
	private String mode;
	
	private String set_device;
	
	private String status;
	
	public LegoPort(int port){
		this.port = port;
	}
	
	public final String getAddress(){
		String address = null;
		try { //TODO I shouldn't do like this. Do Error handling.
			address = EV3.read("lego-port", "address");
		} catch (Exception e){
			e.printStackTrace();
		}
		return address;
	}
}
