package server;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SocketServer {

	private String ip;

	ServerSocket serverSocket;
	Socket client;
	Scanner sc;// Ler do console
	List<PrintStream> outputClients;
	ArrayList<String> userConnected = new ArrayList<>();;

	public SocketServer() {

		outputClients = new ArrayList<>();
	}

	public void execute(ServerSocket server) throws IOException {

		System.out.println("Server iniciado");
		this.serverSocket = server;
		while (true) {

			// Aceitando conexão cliente
			client = serverSocket.accept();

			ip = ipClient(client);
			System.out.println("Connection by: " + ip);

			// adicionar saida do cliente a lista de clientes
			PrintStream ps = new PrintStream(client.getOutputStream());
			outputClients.add(ps);

			// tratamento de clientes em uma nova Thread
			TrataCliente thread = new TrataCliente(client.getInputStream(), this, ps);

			new Thread(thread).start();

		}

	}

	public String ipClient(Socket client) {
		return client.getInetAddress().getHostAddress();
	}

	public void sendInfo(PrintStream output) {

		for (PrintStream cli : outputClients) {
			if (output == cli) {
				output.println("[SERVER]WHO_ONLINE:"+userConnected.toString());
			}
		}

	}

	public void distribuiMensagem(String msg) {

		for (PrintStream cli : outputClients) {
			cli.println(msg);
			System.out.println(msg);
		}
	}

	private void secureClose(final Closeable resource) {
		try {
			if (resource != null) {
				resource.close();
			}
		} catch (IOException ex) {
			System.out.println("Erro = " + ex.getMessage());
		}

	}

	public void encerrar() {
		secureClose(serverSocket);
		secureClose(client);
		secureClose(sc);
		System.exit(2);
	}

}
