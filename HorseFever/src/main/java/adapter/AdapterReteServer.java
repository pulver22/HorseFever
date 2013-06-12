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
	 * si connettano. Ingloba anche la creazione del client connesso in locale al server stesso
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
    }
	
	private ArrayList<View> viewRegistrate=new ArrayList<View>();

	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	public String[] chiediScommessa(int indice) {
		
		String valori[]=clients.get(indice).chiediScommessa(indice);
		return valori;
		
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public String[] chiediSecondaScommessa(int indice){
		
		String valori[]=clients.get(indice).chiediSecondaScommessa(indice);
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String valori[]=clients.get(indice).chiediTrucca(carteAzione,indice);
		return valori;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public void stampaMessaggio(String messaggio,int indice){
		clients.get(indice).stampaMessaggio(messaggio,indice);
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento scrivendolo in un outPutStream
	 */
	public void notify(HorseFeverEvent e){
		for (AdapterClientHandler ach: clients){
			ach.notify(e);
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
	}
	
	@Override
	public void connetti(String serverIP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metodo che nella GUI permette di evidenziare i dati corrispondenti a quello che è il giocatore
	 * */
	@Override
	public void evidenziaGiocatore(String nomeGiocatore, int indice) {
		clients.get(indice).evidenziaGiocatore(nomeGiocatore);
	}

}
