package org.ev3dev.hardware;

import java.io.IOException;

import org.ev3dev.hardware.ports.LegoPort;
import org.ev3dev.io.Sysfs;

/**
 * This is the base class that handles control tasks for a single port or index. The class must chose one device out of the available ports to control. Given an IO port (in the constructor), an implementation should:<br>
<br>
- If the specified port is blank or unspecified/undefined/null, the available devices should be enumerated until a suitable device is found. Any device is suitable when it¡¦s type is known to be compatible with the controlling class, and it meets any other requirements specified by the caller.<br>
<br>
- If the specified port name is not blank, the available devices should be enumerated until a device is found that is plugged in to the specified port. The supplied port name should be compared directly to the value from the file, so that advanced port strings will match, such as in1:mux3.<br>
<br>
- If an error occurs after the initial connection, an exception should be thrown by the binding informing the caller of what went wrong. Unless the error is fatal to the application, no other actions should be taken.<br>
<br>
 * @author Anthony
 *
 */
public abstract class Device {
	
	private String className;
	
	private String classNamePrefix = null;
	
	private String address;
	
	private String classFullName = null;
	
	private LegoPort port;
	
	private boolean connected = false;
	
	/***
	 * Generic way to create a device
	 * @param className The Sysfs Class name
	 */
	public Device(String className){
		this.port = null;
		this.className = className;
	}

	/**
	 * Create a new device with a <b>LegoPort</b>, <b>ClassName</b>, <b>classNamePrefix</b>
	 * @param port A LegoPort delared before.
	 * @param className Sysfs class name
	 * @param classNamePrefix The filename inside the "Sysfs class" (I called it sub-class)
	 * @throws IOException If I/O goes wrong
	 */
	public Device(LegoPort port, String className, String classNamePrefix) throws IOException{
		this.port = port;
		this.className = className;
		this.classNamePrefix = classNamePrefix;
		try {
			address = port.getAddress();
		} catch (IOException e){
			System.err.println(className + "-" + this.hashCode() + ": The lego-port system class wasn't found.");
			throw new IOException("Cannot access to the target address. Are you using a EV3?", e);
		}
		
		connected = checkIsConnected();
		if (!connected){
			System.out.println(className + "-" + this.hashCode() + ": No port connected. Searching until port \"" + address + "\" connected...");
		}
		while (!connected){
			connected = checkIsConnected();
		}
		System.out.println(className + "-" + this.hashCode() + ": Connected to " + address);
	}
	
	public abstract String getAddress() throws IOException;
	
	public abstract String getDriverName() throws IOException;
	
	/**
	 * Set the Sysfs class name (location) of this Device
	 * @param className The Sysfs class name located in <b>/sys/class</b>
	 */
	public void setClassName(String className){
		this.className = className;
	}
	
	/**
	 * Set the filename prefix inside the Sysfs class (prefix (e.g. motor)) of this Device
	 * @param classNamePrefix The filename inside the Sysfs class (e.g. "/sys/class/motor/motor0" <b>motor</b> is a prefix)
	 */
	public void setClassNamePrefix(String classNamePrefix){
		this.classNamePrefix = classNamePrefix;
	}
	
	/**
	 * If a valid device is found while enumerating the ports, the <b>connected</b> variable is set to <b>true</b> (by default, it should be <b>false</b>). If <b>connected</b> is <b>false</b> when an attempt is made to read from or write to a property file, an error should be thrown (except while in the consructor).
	 * @return Whether the device is ready.
	 */
	public boolean isConnected(){
		return connected;
	}
	
	/**
	 * Returns the LegoPort connected with this Device
	 * @return LegoPort object
	 */
	public LegoPort getPort(){
		return port;
	}
	
	/**
	 * Get the filename prefix inside the Sysfs class (prefix (e.g. motor)) of this Device
	 * @return The filename inside the Sysfs class (e.g. "/sys/class/motor/motor0" <b>motor</b> is a prefix)
	 */
	public String getClassNamePrefix(){
		return classNamePrefix;
	}
	
	/**
	 * Get the full filename (not prefix) inside the Sysfs class of this Device. This must be already searched by the <code>checkIsConnected()</code> method
	 * @return The filename inside the Sysfs class (e.g. "/sys/class/motor/motor0" <b>motor0</b> is the full name)
	 */
	public String getClassFullName(){
		return classNamePrefix;
	}
	
	/***
	 * Reads the property specified.
	 * @param property The property name
	 * @return The value of the property
	 */
	public final String getAttribute(String property){
		try {
			String str = Sysfs.getAttribute(className, classFullName, property);
			connected = true;
			return str;
		} catch (IOException e){
			e.printStackTrace();
			connected = false;
			return null;
		}
	}
	
	/***
	 * Writes the property specified.
	 * @param property The property name
	 * @param new_value The new value of the property
	 * @return Boolean whether the attribute was successfully written
	 */
	public final boolean setAttribute(String property, String new_value){
		try {
			Sysfs.setAttribute(className, classFullName, property, new_value);
			connected = true;
		} catch (IOException e){
			e.printStackTrace();
			connected = false;
		}
		return connected;
	}
	
	private boolean checkIsConnected(){
		try {
			classFullName = Sysfs.searchClassFullName(className, classNamePrefix, address);
		} catch (Exception ignore){
			classFullName = null;
			return false;
		}
		return classFullName != null;
	}
}