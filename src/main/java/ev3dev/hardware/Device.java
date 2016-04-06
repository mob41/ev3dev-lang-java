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
import ev3dev.io.Def;
import ev3dev.io.Sysclass;

public class Device {
	
	private String className;
	
	private String subClassName = null;
	
	private String address;
	
	private String hardwareName = null;
	
	private LegoPort port;
	
	private boolean connected = false;
	
	/***
	 * Generic way to create a device
	 * @param devicename
	 */
	public Device(String className){
		this.port = null;
		this.className = className;
	}

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
	
	public void setClassName(String className){
		this.className = className;
	}
	
	public void setSubClassName(String subClassName){
		this.subClassName = subClassName;
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
	 * @param property The property name of the class.
	 * @return The value of the property
	 */
	public String getAttribute(String property){
		try {
			String str = Sysclass.getAttribute(className, subClassName, property);
			connected = true;
			return str;
		} catch (IOException e){
			connected = false;
			return null;
		}
	}
	
	/***
	 * Writes the property of the class specified.
	 * @param property The property name of the class
	 * @param new_value The new value of the property
	 */
	public boolean setAttribute(String property, String new_value){
		try {
			Sysclass.setAttribute(className, subClassName, property, new_value);
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