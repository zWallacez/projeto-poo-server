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

	public String proccessImgs(String msg) {
		String result = msg;
		if (result.contains("/")) {

			result = result.replaceAll("/amigo", FIGURINHAS.AMIGO);
			result = result.replaceAll("/cobrar", FIGURINHAS.COBRAR);
			result = result.replaceAll("/vaidaroque", FIGURINHAS.DOWNLOAD);
			result = result.replaceAll("/ead", FIGURINHAS.EAD);
			result = result.replaceAll("/nentendi", FIGURINHAS.NENTENDI);
			result = result.replaceAll("/pfvr", FIGURINHAS.PFVR);
			result = result.replaceAll("/picapau", FIGURINHAS.PICAPAU);
			result = result.replaceAll("/sad", FIGURINHAS.SAD);
			result = result.replaceAll("/surpreso", FIGURINHAS.SURPRESA);
			result = result.replaceAll("/blz", FIGURINHAS.OK);
			result = result.replaceAll("\\:\\)", FIGURINHAS.EMOJI_HAPPY);
			result = result.replaceAll("\\:\\(", FIGURINHAS.EMOJI_SAD);
			result = result.replaceAll("\\:\\|", FIGURINHAS.EMOJI_NEUTRO);

			result = result.replaceAll("\\(y\\)", FIGURINHAS.EMOJI_UP);

		}
		if (result.contains(("class='fig'"))) {
			result = "<div width='100px' height='100px'>" + result + "</div>";
		}
		return result;
	}

	private void proccessMessages() {
		Scanner sc = new Scanner(this.client);
		while (sc.hasNextLine()) {
			String msg = sc.nextLine();

			if (msg.contains("[SERVER]")) {
				if (msg.equals(SERVER_INFO.COMMAND_WHO_ONLINE.get())) {
					printStream.println("[SERVER]WHO_ONLINE:" + socketServer.userConnected.toString());
				} else if (msg.contains(SERVER_INFO.COMMAND_ADD_USER.get())) {
					String splitted[] = msg.split(":");
					socketServer.userConnected.add(new String(splitted[1]));
					printStream.println("[SERVER]WHO_ONLINE:" + socketServer.userConnected.toString());
					
				} else if (msg.contains(SERVER_INFO.COMMAND_REMOVE_USER.get())) {
					String splitted[] = msg.split(":");
					
					while (socketServer.userConnected.contains(new String(splitted[1]))) {
						socketServer.userConnected.remove(new String(splitted[1]));
					}
					
					printStream.println("[SERVER]WHO_ONLINE:" + socketServer.userConnected.toString());
					

				}

			} else {
				msg = proccessImgs(msg);
				socketServer.distribuiMensagem(msg);
			}

		}
	}

	@Override
	public void run() {
		this.proccessMessages();

	}

}
