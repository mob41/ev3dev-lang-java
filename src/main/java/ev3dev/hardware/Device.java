package ev3dev.hardware;

import java.io.IOException;

import ev3dev.hardware.ports.LegoPort;
import ev3dev.io.Sysclass;

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
public class Device {
	
	private String className;
	
	private String subClassName = null;
	
	private String address;
	
	private String hardwareName = null;
	
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
	 * Create a new device with a <b>LegoPort</b>, <b>ClassName</b>, <b>SubClassName</b>
	 * @param port A LegoPort delared before.
	 * @param className Sysfs class name
	 * @param subClassName The filename inside the "Sysfs class" (I called it sub-class)
	 * @throws IOException If I/O goes wrong
	 */
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
	
	/**
	 * Set the Sysfs class name(location) of this Device
	 * @param className A Sysfs class name located in <b>/sys/class</b>
	 */
	public void setClassName(String className){
		this.className = className;
	}
	
	/**
	 * Set the filename inside the Sysfs class (sub-class) of this Device
	 * @param subClassName A filename inside the Sysfs class (e.g. "/sys/class/motor/motor0" <b>motor0</b> is sub-class name)
	 */
	public void setSubClassName(String subClassName){
		this.subClassName = subClassName;
	}
	
	/**
	 * If a valid device is found while enumerating the ports, the <b>connected</b> variable is set to <b>true</b> (by default, it should be <b>false</b>). If <b>connected</b> is <b>false</b> when an attempt is made to read from or write to a property file, an error should be thrown (except while in the consructor).
	 * @return Whether the device is ready.
	 */
	public boolean isConnected(){
		connected = check();
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
	 * Get the sub-class name of this Device
	 * @return A filename inside the Sysfs class (e.g. "/sys/class/motor/motor0" <b>motor0</b> is sub-class name)
	 */
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