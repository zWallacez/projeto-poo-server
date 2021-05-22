package client;

import java.io.InputStream;
import java.util.Scanner;

public class Receiver implements Runnable{

	private Scanner sc;
	InputStream input;

    public Receiver(InputStream input) {
        this.input = input;
    }
	
	@Override
	public void run() {
		
		//Recebe Mesagens do servidor e imprime na tela
        sc = new Scanner(this.input);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
		
	}

}
