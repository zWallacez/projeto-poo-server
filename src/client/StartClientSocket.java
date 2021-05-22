package client;

import java.io.IOException;
import java.net.Socket;

public class StartClientSocket {

	public static final int PORT = 4000;//Porta para conexao
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private static Socket clientSocket;
	private static SocketClient soc= new SocketClient();
	
	public static void connectClient() {
		try {
			//Conecta o cliente com o servidor
			clientSocket = new Socket(SERVER_ADDRESS,PORT);
			
			soc.execute(clientSocket);
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public static void main(String[] args) {
		
		connectClient();

	}

}
