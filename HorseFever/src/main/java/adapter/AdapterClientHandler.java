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

public class AdapterClientHandler{

	Socket clientSocket;
	ObjectOutputStream out=null;
	ObjectInputStream in=null;
	
	/**
	 * Associa un determinato socket a questo client
	 * @param s
	 */
	public AdapterClientHandler(Socket s){
		this.clientSocket=s;
	}

	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	public synchronized String[] chiediScommessa(int indice) {
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediScommessa";
		mess[1]="";
		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            if (in==null)
            	in = new ObjectInputStream(clientSocket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public synchronized String[] chiediSecondaScommessa(int indice){
		
		String[] mess = new String[2];
		String[] valori = new String[3];
		mess[0]="chiediSecondaScommessa";
		mess[1]="";
		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            if (in==null)
            	in = new ObjectInputStream(clientSocket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public synchronized String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String[] mess = new String[2];
		String[] valori = new String[2];
		mess[0]="chiediTrucca";
		mess[1]="";
		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            out.writeObject(carteAzione.clone());
            out.flush();
            if (in==null)
            	in = new ObjectInputStream(clientSocket.getInputStream());
            valori=(String[])in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
		return valori;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public synchronized void stampaMessaggio(String messaggio,int indice){
		String[] mess = new String[2];
		mess[0]="stampaMessaggio";
		mess[1]=String.valueOf(messaggio);
		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mess);
            out.flush();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento scrivendolo in un outPutStream
	 */
	public synchronized void notify(HorseFeverEvent e){

		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			out.writeObject(e);
			out.flush();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
		
	}
	

	/**
	 * Permette di avanzare alla fase successiva del turno, e quindi del gioco
	 */
	
	public synchronized void prosegui(String messaggio, int indice) {
		String[] mess = new String[2];
		mess[0]="prosegui";
		mess[1]=String.valueOf(messaggio);
		try {
			if (out==null)
				out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mess);
            out.flush();
            if (in==null)
            	in = new ObjectInputStream(clientSocket.getInputStream());
            in.readObject();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
	}
	
	/**
	 * Restituisce il metodo in uscita
	 * @return
	 */
	public ObjectOutputStream getOut() {
		return out;
	}

	/**
	 * Restituisce il metodo in ingresso
	 * @return
	 */
	public ObjectInputStream getIn() {
		return in;
	}

	/**
	 * Restituisce il socket del client
	 * @return
	 */
	public Socket getClientSocket() {
		return clientSocket;
	}
	
	
	
}
