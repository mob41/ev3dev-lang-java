package ev3dev.hardware;

import java.io.IOException;

import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Sysclass;

public class Device {
	
	private Thread thread;
	private CheckDevice runnable;
	private String address;
	private LegoPort port;
	private boolean connected = false;
	
	public Device(String address){
		//TODO the available devices should be enumerated until a suitable device is found
	}
	
	public Device(String classname, String address){
		//TODO find device according some requirements
	}

	public Device(LegoPort port) throws IOException{
		this.port = port;
		address = port.getAddress();
		runnable = new CheckDevice();
		thread = new Thread(runnable);
		thread.setName("EV3PingDevice-" + address + "-" + thread.getId());
		thread.start();
	}
	
	public boolean isConnected(){
		return connected;
	}
	
	public LegoPort getPort(){
		return port;
	}
}

class CheckDevice implements Runnable{

	public void run() {
		
		
	}
	
}