import server.*;

public class ServerApplication {
	
	public static void main(String[] args) {
		initialise();
		launchGUI(new ServerLink());
	}
    
	public static ServerLink initialise() {
		return new ServerLink();
	}
	 
	public static void launchGUI(ServerLink server) {
		ServerWindow serverWindow = new ServerWindow(server);
	}
}
