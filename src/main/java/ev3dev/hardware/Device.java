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
	
	private boolean connected = false;

	public Device(LegoPort port, String className, String subClassName) throws IOException{
		this.port = port;
		this.className = className;
		this.subClassName = subClassName;
		System.out.println(className + "-" + this.hashCode() + ": Searching until a port connected...");
		while (!connected){
			check();
		}
		address = port.getAddress();
		System.out.println(className + "-" + this.hashCode() + ": Connected to " + address);
	}
	
	public boolean isConnected(){
		connected = check();
		return connected;
	}
	
	public LegoPort getPort(){
		return port;
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
	public String getAttribute(String class_name, String property){
		try {
			String str = Sysclass.getAttribute(class_name, property);
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
	public String getAttribute(String class_name, String subclass, String property){
		return getAttribute(class_name, subclass + "/" + property);
	}
	
	/***
	 * Writes the property of the class & subclass specified.
	 * @param class_name The class name.
	 * @param subclass The Sub-class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 */
	public boolean setAttribute(String class_name, String subclass, String property, String new_value){
		return setAttribute(class_name, subclass + "/" + property, new_value);
	}
	
	/***
	 * Writes the property of the class specified.
	 * @param class_name The class name.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 */
	public boolean setAttribute(String class_name, String property, String new_value){
		try {
			Sysclass.setAttribute(class_name, property, new_value);
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