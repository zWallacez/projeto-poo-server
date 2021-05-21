import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static final int PORT = 4000;//Porta para conexao
	private static final String SERVER_ADDRESS = "127.0.0.1";
	private Scanner sc;
	private PrintWriter output;
	
	public ChatClient() {
		sc = new Scanner(System.in);
	}
	
	//Conecta o cliente com o servidor
	public void start() {
		try(Socket clientSocket = new Socket(SERVER_ADDRESS,PORT)){
			/*
			 * Stream outputStream para enviar uma mensagem para o server
			 * */
			OutputStream outputStream = clientSocket.getOutputStream();
			output = new PrintWriter(outputStream,true);
		
			messageLoop();
		}
		catch(IOException b) {
			System.out.println(b.getMessage());
		}
	}
	
	//Captura uma entrada do cliente
	private void messageLoop(){
		
		String msg;
		while(true) {
			System.out.println("Enter yours message: ");
			msg = sc.nextLine();
			if(!msg.equalsIgnoreCase("exit")) {
				output.println(msg);
			}
			else {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		
		ChatClient client = new ChatClient();
		client.start();
		
	}
	
}
