import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static final int PORT = 4000;//Porta para conexao
	
	public void start() 
	{//Implementa o socket do servidor
		try(ServerSocket serverSocket = new ServerSocket(PORT)){
			System.out.println("SUCCESS");
			connectionLoop(serverSocket);
		}
		catch(IOException a) {
			System.out.println(a.getMessage());
		}
	}
	
	private void connectionLoop(ServerSocket serverSocket) {
		while(true) {
			try(Socket clientSocket = serverSocket.accept()){
				System.out.println("Connection by: " +
						clientSocket.getRemoteSocketAddress());
			}
			catch(IOException c) {
				System.out.println("ERRO: " + c.getMessage());
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		
		ChatServer server = new ChatServer();
		server.start();
		
	}
}
