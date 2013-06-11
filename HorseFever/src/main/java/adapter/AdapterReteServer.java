package adapter;

import horsefever.Azione;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterReteServer implements Adapter{

	public static final int SERVER_PORT = 5000;
	
	private int numeroClientAttesi;
	private ArrayList<AdapterClientHandler> clients=new ArrayList<AdapterClientHandler>();
	
	/**
	 * Registra il numero di giocatori su cui stare in attesa
	 * @param numeroClientAttesi
	 * @param v
	 */
	public AdapterReteServer(int numeroClientAttesi, View v){
		this.numeroClientAttesi=numeroClientAttesi;
		viewRegistrate.add(v);
	}
	
	/**
	 * Crea il server socket e aspetta che tutti gli utenti che devono partecipare al gioco
	 * si connettano
	 */
	public void startServer() {
        ServerSocket serverSocket = null;
        try {
            //Crea un nuovo server socket con la porta di default
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("[Server] Aperta la porta.");
        } catch (IOException ex) {
            System.out.println("Errore durante la creazione del server socket "+ex.getMessage());
            return;
        }
        System.out.println("[Server] In attesa dei Clients");
        Socket socket = null;
        ClientThread c=new ClientThread("localhost",viewRegistrate.get(0));
		c.start();
        while(clients.size()<this.numeroClientAttesi){
        	try {
        		//Aspetta fino all'instaurazione del server socket e l'aggiunta di giocatori
        		socket = serverSocket.accept();
        		clients.add(new AdapterClientHandler(socket));
        		System.out.println("[Server] Connessione instaurata, client IP" + socket.getInetAddress());
        	} catch (IOException ex) {
        		System.out.println("Errore durante l'accettazione del server socket");
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
		
		String valori[]=clients.get(indice).chiediScommessa(indice);
		/*
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediScommessa";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
            out = clients.get(indice).getOut();
            out.writeObject(mess);
            out.flush();
            in = clients.get(indice).getIn();
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        */
		return valori;
		
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public String[] chiediSecondaScommessa(int indice){
		
		String valori[]=clients.get(indice).chiediSecondaScommessa(indice);
		/*
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediSecondaScommessa";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
			out = clients.get(indice).getOut();
            out.writeObject(mess);
            out.flush();
            in = clients.get(indice).getIn();
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		*/
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String valori[]=clients.get(indice).chiediTrucca(carteAzione,indice);
		/*
		String[] mess = new String[2];
		String[] valori = new String[2];
		mess[0]="chiediTrucca";
		mess[1]="";
		Socket socket=clients.get(indice).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
			out = clients.get(indice).getOut();
            out.writeObject(mess);
            out.flush();
            out.writeObject(carteAzione);
            out.flush();
            in = clients.get(indice).getIn();
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        */
		return valori;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public void stampaMessaggio(String messaggio,int indice){
		clients.get(indice).stampaMessaggio(messaggio,indice);
		/*
		String[] mess = new String[2];
		mess[0]="stampaMessaggio";
		mess[1]=String.valueOf(messaggio);
		Socket socket=clients.get(indice).getClientSocket();
        ObjectOutputStream out = null;
		try {
			out = clients.get(indice).getOut();
            out.writeObject(mess);
            out.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        */
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento scrivendolo in un outPutStream
	 */
	public void notify(HorseFeverEvent e){
		for (AdapterClientHandler ach: clients){
			ach.notify(e);
			/*
			Socket socket=ach.getClientSocket();
	        ObjectOutputStream out = null;
			try {
				out = ach.getOut();
                out.writeObject(e);
                out.flush();
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
            */
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
		clients.get(indice).prosegui(messaggio,indice);
		if (messaggio.equals("Fine del turno")){
			clients.add(clients.get(0)); //Riordina clients secondo ordine del primo giocatore
			clients.remove(0);
		}
		/*
		String[] mess = new String[2];
		mess[0]="prosegui";
		mess[1]=String.valueOf(messaggio);
		Socket socket=clients.get(0).getClientSocket();
		ObjectInputStream in = null;
        ObjectOutputStream out = null;
		try {
			out = clients.get(indice).getOut();
            out.writeObject(mess);
            out.flush();
            in = clients.get(indice).getIn();
            in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        */
	}

	@Override
	public void connetti(String serverIP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}
