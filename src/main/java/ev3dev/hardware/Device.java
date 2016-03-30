package ev3dev.hardware;

public abstract class Device {
	
	private LegoPort port;
	
	public Device(){
		//TODO the available devices should be enumerated until a suitable device is found
	}

	public Device(LegoPort port){
		this.port = port;
	}
	
	public boolean isConnected(){
		//TODO I don't understand around the Device class... :(
		return true;
	}
}
