package adapter;

import horsefever.Azione;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterReteClient implements Adapter{

	private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;
 
    public static void main(String[] args) {
 
        Scanner scanner = new Scanner(System.in);   //to read text from the console
        Socket socket = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connected to server!");
        } catch (Exception ex) {
            System.out.println("Error connecting to server: " + ex.getMessage());
        }
 
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        while (true) {
            try {
                if (out == null) {
                    out = new ObjectOutputStream(socket.getOutputStream());
                }
 
                //read a string
                System.out.println("Enter a message: ");
                String str = scanner.next();
 
                //send it to server
                out.writeObject(new Object()/*Message(str)*/);
                out.flush();
 
                //get the reply from the server
                if (in == null) {
                    in = new ObjectInputStream(socket.getInputStream());
                }
                //Message message = (Message) in.readObject();
                System.out.println("Server said: "/* + message.getMessage()*/);
 
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
	
	@Override
	public String[] chiediScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] chiediSecondaScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stampaMessaggio(String messaggio, int indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(HorseFeverEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prosegui(String messaggio, int indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addView(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeView(int i) {
		// TODO Auto-generated method stub
		
	}

}
