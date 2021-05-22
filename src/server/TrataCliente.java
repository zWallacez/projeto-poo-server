package server;

import java.io.InputStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

    InputStream client;
    SocketServer socketServer;

    public TrataCliente(InputStream client, SocketServer socketServer) {
        this.client = client;
        this.socketServer = socketServer;
    }
    
    @Override
    public void run() {
        Scanner sc = new Scanner(this.client);
        while(sc.hasNextLine()){
            socketServer.distribuiMensagem(sc.nextLine());
            //System.out.println(host+": "+scan.nextLine());
        }
        
    }

}
