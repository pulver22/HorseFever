package adapter;

import horsefever.Azione;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import eventi.HorseFeverEvent;

public class AdapterClientHandler{

	private Socket clientSocket;
	private ObjectOutputStream out=null;
	private ObjectInputStream in=null;
	
	public static final Logger log = Logger.getLogger(AdapterReteServer.class.getName());
	
	/**
	 * Costruttore dell'handler
	 * @param Il socket relativo al client che deve gestire l'handler
	 */
	public AdapterClientHandler(Socket s){
		this.clientSocket=s;
	}

	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	public synchronized String[] chiediScommessa(int indice) throws IOException{
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediScommessa";
		mess[1]="";
		
			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}
				
            out.writeObject(mess);
            out.flush();
            if (in==null){
            	in = new ObjectInputStream(clientSocket.getInputStream());
            }	
            try {
				valori=(String[])in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, e.getMessage(), e);
			}
        
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public synchronized String[] chiediSecondaScommessa(int indice) throws IOException{
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediSecondaScommessa";
		mess[1]="";

			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
            out.writeObject(mess);
            out.flush();
            if (in==null){
            	in = new ObjectInputStream(clientSocket.getInputStream());
            }	
            try {
				valori=(String[])in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, e.getMessage(), e);
			}

		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public synchronized String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) throws IOException{
		
		String[] mess = new String[2];
		String[] valori = new String[2];
		mess[0]="chiediTrucca";
		mess[1]="";

			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
            out.writeObject(mess);
            out.flush();
            out.writeObject(carteAzione.clone());
            out.flush();
            if (in==null){
            	in = new ObjectInputStream(clientSocket.getInputStream());
            }
            try {
				valori=(String[])in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, e.getMessage(), e);
			}

		return valori;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public synchronized void stampaMessaggio(String messaggio,int indice) throws IOException{
		String[] mess = new String[2];
		mess[0]="stampaMessaggio";
		mess[1]=String.valueOf(messaggio);

			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
            out.writeObject(mess);
            out.flush();
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento scrivendolo in un outPutStream
	 */
	public synchronized void notify(HorseFeverEvent e) throws IOException{


			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
			out.writeObject(e);
			out.flush();

		
	}
	

	/**
	 * Permette di avanzare alla fase successiva del turno, e quindi del gioco
	 */
	public synchronized void prosegui(String messaggio, int indice) throws IOException{
		String[] mess = new String[2];
		mess[0]="prosegui";
		mess[1]=String.valueOf(messaggio);

			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
            out.writeObject(mess);
            out.flush();
            if (in==null){
            	in = new ObjectInputStream(clientSocket.getInputStream());
            }	
            try {
				in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, e.getMessage(), e);
			}
	}

	/**
	 * Invia al client il comando di evidenziare il giocatore corrispondente
	 * */
	public synchronized void evidenziaGiocatore(String nomeGiocatore) throws IOException{
		String[] mess = new String[2];
		mess[0]="evidenziaGiocatore";
		mess[1]=String.valueOf(nomeGiocatore);

			if (out==null){
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}	
            out.writeObject(mess);
            out.flush();

	}
	
}
