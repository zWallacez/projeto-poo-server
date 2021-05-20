import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static final int PORT = 4000;//Porta para conexao
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private Scanner sc;
	private BufferedWriter output;
	
	public ChatClient() {
		sc = new Scanner(System.in);
	}
	
	//Conecta o cliente com o servidor
	public void start() {
		try(Socket clientSocket = new Socket(SERVER_ADDRESS,PORT)){
			/*
			 * Stream outputStream para enviar uma mensagem para o server
			 * A função write() envia apenas bytes, por isso o OutputStreamWriter
			 * O OutputStreamWriter é uma stream de caracteres para stream de bytes.
			 * O BufferedWriter cria um buffer para stream output
			 * */
			OutputStream outputStream = clientSocket.getOutputStream();
			output = new BufferedWriter(new OutputStreamWriter(outputStream));
		
			try {
				messageLoop();
			}
			catch(IOException e) {
				e.getMessage();
			}
		}
		catch(IOException b) {
			System.out.println(b.getMessage());
		}
	}
	
	//Captura uma entrada do cliente
	private void messageLoop() throws IOException {
		
		String msg;
		do {
			System.out.println("Enter yours message: ");
			msg = sc.nextLine();
			output.write(msg);
			output.newLine();
			output.flush();//Garante o envio da mensagem
		}while(!msg.equalsIgnoreCase("exit"));

	}
	
	public static void main(String[] args) {
		
		ChatClient client = new ChatClient();
		client.start();
		
	}
	
}
