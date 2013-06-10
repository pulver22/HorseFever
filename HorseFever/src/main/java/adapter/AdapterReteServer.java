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
        		System.out.println("Connection created, client IP" + socket.getInetAddress());
        	} catch (IOException ex) {
        		System.out.println("Error occured while accepting the socket");
        		return;
        	}
        }
        /*
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        while (true) {
            try {
                if (in == null) {
                    in = new ObjectInputStream(socket.getInputStream());
                }
                
                //Message message = (Message) in.readObject();
                System.out.println("Client said: " + message.getMessage());
 
                //Send a reply to the client
                if (out == null) {
                    out = new ObjectOutputStream(socket.getOutputStream());
                }
                out.writeObject(new Message(&quot;Message recieved: &quot; + message.getMessage()));
                out.flush();
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }
		*/
    }
	
	private ArrayList<View> viewRegistrate=new ArrayList<View>();

	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	public String[] chiediScommessa(int indice) {
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediScommessa";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public String[] chiediSecondaScommessa(int indice){
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediSecondaScommessa";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String[] mess = new String[2];
		String[] valori = new String[2];
		mess[0]="chiediTrucca";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            out.writeObject(carteAzione);
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public void stampaMessaggio(String messaggio,int indice){
		String[] mess = new String[2];
		mess[0]="stampaMessaggio";
		mess[1]=String.valueOf(messaggio);
		Socket socket=clients.get(indice).getClientSocket();
        ObjectOutputStream out = null;
		try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento scrivendolo in un outPutStream
	 */
	public void notify(HorseFeverEvent e){
		for (AdapterClientHandler ach: clients){
			Socket socket=ach.getClientSocket();
	        ObjectOutputStream out = null;
			try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(e);
                out.flush();
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
		}
	}
	
	
	/**
	 * Aggiunge una view, quindi registra un giocatore
	 */
	public void addView(View v){
		viewRegistrate.add(v);
	}
	
	/**
	 * Rimuove una view, quindi elimina un giocatore dal gioco
	 */
	public void removeView(int i){
		clients.remove(i);
	}

	/**
	 * Permette di avanzare alla fase successiva del turno, e quindi del gioco
	 */
	@Override
	public void prosegui(String messaggio, int indice) {
		String[] mess = new String[2];
		mess[0]="prosegui";
		mess[1]=String.valueOf(messaggio);
		Socket socket=clients.get(0).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
	}

}
