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

	@Override
	public void run() {
		Scanner sc = new Scanner(this.client);
		while (sc.hasNextLine()) {
			String msg = sc.nextLine();
			System.out.println("SERVER: " + msg);
			if (msg.contains("[SERVER]")) {
				if(msg.equals("[SERVER]WHO_ONLINE")) {

					socketServer.sendInfo(this.printStream);	
				}else if(msg.contains("[SERVER]ADD_USER")) {
					String splitted[] = msg.split(":");
					socketServer.userConnected.add(new String(splitted[1]));
					System.out.println("Adicionado: " + splitted[1]);
				}else if(msg.contains("[SERVER]REMOVE_USER")) {
					String splitted[] = msg.split(":");
					socketServer.userConnected.remove(splitted[1]);
				}
				
			

				
			} else {
				socketServer.distribuiMensagem(msg);
			}

			// System.out.println(host+": "+scan.nextLine());
		}

	}

}
