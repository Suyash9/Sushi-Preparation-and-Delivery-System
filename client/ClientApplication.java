import client.*;

public class ClientApplication {
	
	public static void main(String[] args) {
		initialise();
		launchGUI(new ClientLink());
	}
    
	public static ClientLink initialise() {
		return new ClientLink();
	}
	 
	public static void launchGUI(ClientLink client) {
		ClientWindow clientWindow = new ClientWindow(client);
	}
}
