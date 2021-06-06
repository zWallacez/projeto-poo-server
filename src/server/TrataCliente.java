package server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	InputStream client;
	SocketServer socketServer;
	OutputStream outputStream;
	PrintStream printStream;

	public TrataCliente(InputStream client, SocketServer socketServer, PrintStream printStream) {
		this.client = client;
		this.socketServer = socketServer;
		this.printStream = printStream;
	}

	private void proccessMessages() {
		Scanner sc = new Scanner(this.client);
		while (sc.hasNextLine()) {
			String msg = sc.nextLine();

			if (msg.contains("[SERVER]")) {
				if (msg.equals(SERVER_INFO.COMMAND_WHO_ONLINE.get())) {
					socketServer.sendInfoToUser(this.printStream);
				} else if (msg.contains(SERVER_INFO.COMMAND_ADD_USER.get())) {
					String splitted[] = msg.split(":");
					socketServer.userConnected.add(new String(splitted[1]));
				} else if (msg.contains(SERVER_INFO.COMMAND_REMOVE_USER.get())) {
					String splitted[] = msg.split(":");
					socketServer.userConnected.remove(new String(splitted[1]));
					
				}

			} else {
				socketServer.distribuiMensagem(msg);
			}

		}
	}

	@Override
	public void run() {
		this.proccessMessages();

	}

}
