import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static final int PORT = 4000;//Porta para conexao
	private BufferedReader input;
	private String msg;
	
	/*
	 * Implementa o socket do servidor.
	 * Se o objeto for realmente criado, significa que PORT (constante) estava 
	 * fechada e foi aberta.
	 * Obs.: Ocorre um erro se a porta já está em uso.
	 * */
	public void start() {
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
				
				/*
				 * 
				 * */
				System.out.println(ipClient(clientSocket));
				InputStream inputStream = clientSocket.getInputStream();
				input = new BufferedReader(new InputStreamReader(inputStream));
				msg = input.readLine();
				System.out.println(ipClient(clientSocket) + " " + msg);
			}
			catch(IOException c) {
				System.out.println("ERRO: " + c.getMessage());
				break;
			}
		}
	}
	
	public String ipClient(Socket clientSocket) {
		return clientSocket.getInetAddress().getHostAddress();
	}
	
	public static void main(String[] args) {
		
		ChatServer server = new ChatServer();
		server.start();
		
		
	}
}
