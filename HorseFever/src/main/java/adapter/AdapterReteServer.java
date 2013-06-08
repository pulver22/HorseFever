package adapter;

import horsefever.Azione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterReteServer implements Adapter{

	public static final int SERVER_PORT = 5000;
	
	private int numeroClientAttesi;
	private ArrayList<AdapterClientHandler> clients=new ArrayList<AdapterClientHandler>();
	
	public AdapterReteServer(int numeroClientAttesi){
		this.numeroClientAttesi=numeroClientAttesi;
	}
	
	public void startServer() {
        ServerSocket serverSocket = null;
        try {
            //Creates a new server socket with the given port number
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Error occured while creating the server socket");
            return;
        }
 
        Socket socket = null;
        while(clients.size()<this.numeroClientAttesi){
        	try {
        		//Waits untill a connection is made, and returns that socket
        		socket = serverSocket.accept();
        		clients.add(new AdapterClientHandler(socket, serverSocket));
        	} catch (IOException ex) {
        		System.out.println("Error occured while accepting the socket");
        		return;
        	}
        }
        //Now we have established the a connection with the client
        System.out.println("Connection created, client IP" + socket.getInetAddress());
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        while (true) {
            try {
                if (in == null) {
                    in = new ObjectInputStream(socket.getInputStream());
                }
                
                //Message message = (Message) in.readObject();
                System.out.println("Client said: "/* + message.getMessage()*/);
 
                //Send a reply to the client
                if (out == null) {
                    out = new ObjectOutputStream(socket.getOutputStream());
                }
                out.writeObject(new Object()/*new Message(&quot;Message recieved: &quot; + message.getMessage())*/);
                out.flush();
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
