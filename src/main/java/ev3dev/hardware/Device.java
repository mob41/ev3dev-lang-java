package ev3dev.hardware;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.AccessControlException;

import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.PropertyDefaults;
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
	
	private int interval = 0;
	
	private boolean connected = false;
	
	public Device(String devicename){
		//Generic
	}

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
	
	/***
	 * Reads the property of the class specified.
	 * @param class_name The class name
	 * @param property The property name of the class.
	 * @return The value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public String getProperty(String class_name, String property) throws FileNotFoundException, IOException, AccessControlException{
		if (!connected){
			return null;
		}
		return Sysclass.getProperty(class_name, property);
	}
	
	/***
	 * Reads the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @return The value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public String getProperty(String class_name, String subclass, String property) throws FileNotFoundException, IOException, AccessControlException{
		return getProperty(class_name, subclass + "/" + property);
	}
	
	/***
	 * Writes the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public void setProperty(String class_name, String subclass, String property, String new_value) throws FileNotFoundException, AccessControlException{
		setProperty(class_name, subclass + "/" + property, new_value);
	}
	
	/***
	 * Writes the property of the class specified.
	 * @param class_name The class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 * @throws FileNotFoundException If the specified class isn't exist.
	 * @throws IOException If the API couldn't read the class's property
	 * @throws AccessControlException If you are trying to write to a read-only property, read from a write-only property
	 */
	public void setProperty(String class_name, String property, String new_value) throws FileNotFoundException, AccessControlException{
		if (!connected){
			return;
		}
		Sysclass.setProperty(class_name, property, new_value);
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