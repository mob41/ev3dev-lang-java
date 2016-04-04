package ev3dev.hardware;

import java.io.IOException;

import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Sysclass;

public class Device {
	
	private String className;
	
	private String subClassName;
	
	private String driverName;
	
	private String address;
	
	private String hardwareName = null;
	
	private LegoPort port;
	
	private Thread thread;
	
	private Check runnable;
	
	private int interval = 100;
	
	private boolean connected = false;

	public Device(LegoPort port, String className, String subClassName) throws IOException{
		this.port = port;
		this.className = className;
		this.subClassName = subClassName;
		address = port.getAddress();
		runnable = new Check();
		thread = new Thread(runnable);
		thread.setName("ev3dev-pingDevice-" + className + "-" + thread.getId());
		thread.start();
		while (!connected){
			check();
		}
	}
	
	public boolean isConnected(){
		return connected;
	}
	
	public LegoPort getPort(){
		return port;
	}
	
	public int getCheckInterval(){
		return interval;
	}
	
	public void setCheckInterval(int interval){
		this.interval = interval;
	}
	
	public String getSubClassName(){
		return hardwareName;
	}
	
	public void startInterval(){
		if (thread != null && runnable != null){
			thread.start();
		}
	}
	
	public void skipInterval(){
		synchronized(thread){
			thread.notify();
		}
	}
	
	public void stopInterval(){
		if (runnable != null){
			runnable.stop();
		}
	}
	
	private void check(){
		try {
			hardwareName = Sysclass.getHardwareName(className, subClassName, address);
		} catch (Exception ignore){
			connected = false;
			hardwareName = null;
			return;
		}
		connected = hardwareName != null;
	}
	
	class Check implements Runnable{

		public boolean running = false;
		
		@Override
		public void run() {
			if (!running){
				running = true;
				while(running){
					check();
					try {
						synchronized(thread){
							thread.wait(interval);
						}
					} catch (InterruptedException ignore) {}
				}
			}
			
		}
		
		public void stop(){
			if (running){
				synchronized(thread){
					thread.notify();
				}
				running = false;
			}
		}
		
	}
}