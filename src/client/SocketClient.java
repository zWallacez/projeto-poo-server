package client;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    private Socket socketClient;
    private PrintStream output;
    private Scanner sc;
    private Receiver r;

    public void execute(Socket client){

    	this.socketClient = client;
        String str;
        System.out.println("Connected");

        //Thread recebe mensagem do servidor
        try {
			r = new Receiver(socketClient.getInputStream());
		} catch (IOException e) {
			e.getMessage();
		}
        new Thread(r).start();

        //Enviar Texto via PrintStream no outputStram do cliente
        sc = new Scanner(System.in);
        try {
			output = new PrintStream(socketClient.getOutputStream());
		} catch (IOException e) {
			e.getMessage();
		}
        
        //Recebe input do console
        while (sc.hasNextLine()) {
        	
            str = sc.nextLine();
            if (str.equalsIgnoreCase("sair") || str.equalsIgnoreCase("exit")) {
                encerrar();
            } 
            else {
                output.println(ipClient(socketClient) + ": " + str);
            }
        }
    }

    private void secureClose(final Closeable resource) {

        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void encerrar() {
        secureClose(output);
        secureClose(socketClient);
        secureClose(sc);
        System.exit(2);
    }
    
    public String ipClient(Socket client) {
    	return client.getInetAddress().getHostAddress();
    }
    
}
