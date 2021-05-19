import java.io.IOException;
import java.net.Socket;

public class ChatClient {

	private static final String SERVER_ADDRESS = "127.0.0.1";
	
	public void start() {
		try(Socket clientSocket = new Socket(SERVER_ADDRESS,ChatServer.PORT)){
			
		}
		catch(IOException b) {
			System.out.println(b.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		ChatClient client = new ChatClient();
		client.start();
		
	}
	
}
