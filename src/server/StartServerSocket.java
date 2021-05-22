package server;

import java.io.IOException;
import java.net.ServerSocket;

public class StartServerSocket {

	public static final int PORT = 4000;
	public static SocketServer ss = new SocketServer();
	public static ServerSocket serverSocket;
	
	public static void connectServer() {

		try{
			//Abrindo a Porta Servidor
			serverSocket = new ServerSocket(PORT);
			
			ss.execute(serverSocket);
		} catch (IOException ioe) {
			System.err.println("Erro: " + ioe.getMessage());
		}
	}

	public static void main(String[] args) {

		connectServer();

	}

}