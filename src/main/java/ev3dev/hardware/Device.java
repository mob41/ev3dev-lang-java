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
		connected = check();
	}
	
	public boolean isConnected(){
		connected = check();
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
	 */
	public String getProperty(String class_name, String property){
		try {
			String str = Sysclass.getProperty(class_name, property);
			connected = true;
			return str;
		} catch (IOException e){
			connected = false;
			return null;
		}
	}
	
	/***
	 * Reads the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @return The value of the property
	 */
	public String getProperty(String class_name, String subclass, String property){
		return getProperty(class_name, subclass + "/" + property);
	}
	
	/***
	 * Writes the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 */
	public boolean setProperty(String class_name, String subclass, String property, String new_value){
		return setProperty(class_name, subclass + "/" + property, new_value);
	}
	
	/***
	 * Writes the property of the class specified.
	 * @param class_name The class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 */
	public boolean setProperty(String class_name, String property, String new_value){
		try {
			Sysclass.setProperty(class_name, property, new_value);
			connected = true;
		} catch (IOException e){
			connected = false;
		}
		return connected;
	}
	
	private boolean check(){
		try {
			hardwareName = Sysclass.getHardwareName(className, subClassName, address);
		} catch (Exception ignore){
			connected = false;
			hardwareName = null;
			return false;
		}
		return hardwareName != null;
	}
}