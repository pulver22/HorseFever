package adapter;

import horsefever.Azione;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import View.GUIView;
import View.View;
import eventi.HorseFeverEvent;

public class AdapterReteClient implements Adapter{

    private static final int SERVER_PORT = 5000;
    private Socket socket=null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
 
    /**
     * Dopo aver ricevuto in ingresso l'indirizzo IP del server,collega il client al server attraverso una porta
     * di default
     */
    public void connetti(String serverIP) {
        try {
            socket = new Socket(serverIP, SERVER_PORT);
            System.out.println("[Client] Connesso al Server!");
        } catch (Exception ex) {
            System.out.println("Error connecting to server: " + ex.getMessage());
            throw new RuntimeException();
        }
    }
    
    /**
     * Rappresenta il main vero e proprio del client, chiedi la prima e seconda scommessa, il truccare la corsa
     * e il prosegui alla fase successiva
     */
    public void start(){
        while (true) {
            try {
            	
            	if (in == null) {
                    in = new ObjectInputStream(socket.getInputStream());
                }
            	Object obj=in.readObject();
            	
            	if (obj instanceof HorseFeverEvent){
            		notify((HorseFeverEvent) obj);
            	}
            	
            	if (obj instanceof String[]){
            		String[] mess= (String[])obj;
            		if (mess[0].equals("chiediScommessa")){
            			try {
            				String[] valori=chiediScommessa(0);
            				if (out==null){
                        		out = new ObjectOutputStream(socket.getOutputStream());
                        	}
            	            out.writeObject(valori);
            	            out.flush();
            	 
            	        } catch (Exception ex) {
            	    
            	            System.out.println("Error: " + ex);
            	            throw new RuntimeException();
            	        }
            		}
            		if (mess[0].equals("chiediSecondaScommessa")){
            			try {
            				String[] valori=chiediSecondaScommessa(0);
            				if (out==null){
                        		out = new ObjectOutputStream(socket.getOutputStream());
                        	}
            	            out.writeObject(valori);
            	            out.flush();
            	 
            	        } catch (Exception ex) {
            	            System.out.println("Error: " + ex);
            	            throw new RuntimeException();
            	        }
            		}
            		if (mess[0].equals("chiediTrucca")){
            			try {
            				@SuppressWarnings("unchecked")
							ArrayList<Azione> carteAzione=(ArrayList<Azione>)in.readObject();
            				String[] valori=chiediTrucca(carteAzione,0);
            				if (out==null){
                        		out = new ObjectOutputStream(socket.getOutputStream());
                        	}
            	            out.writeObject(valori);
            	            out.flush();
            	 
            	        } catch (Exception ex) {
            	            System.out.println("Error: " + ex);
            	            throw new RuntimeException();
            	        }
            		}
            		if (mess[0].equals("stampaMessaggio")){
            			stampaMessaggio(mess[1],0);
            		}
            		if (mess[0].equals("prosegui")){
            			prosegui(mess[1],0);
            			if (out==null){
                    		out = new ObjectOutputStream(socket.getOutputStream());
                    	}
            			out.writeObject("Fatto");
            			out.flush();
            		}
            		if (mess[0].equals("evidenziaGiocatore")){
            			evidenziaGiocatore(mess[1],0);
            		}
            	}
            	
            } catch (Exception ex) {
                System.out.println("Error: " + ex);
                throw new RuntimeException();
            }
        }
    }
	
    private ArrayList<View> viewRegistrate=new ArrayList<View>();

	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	public String[] chiediScommessa(int indice) {
		
		String[] valori;
		
		valori=viewRegistrate.get(0).chiediScommessa();
		
		
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	public String[] chiediSecondaScommessa(int indice){
		
        String[] valori;
        
        valori=viewRegistrate.get(0).chiediSecondaScommessa();
		
		return valori;
	}
	
	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String[] scelta;
		
		scelta=viewRegistrate.get(0).chiediTrucca(carteAzione);
		
		return scelta;
	}
	
	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	public void stampaMessaggio(String messaggio,int indice){
		
		viewRegistrate.get(0).stampaMessaggio(messaggio);
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento
	 */
	public void notify(HorseFeverEvent e){
		
		for (View v: viewRegistrate){
			v.notify(e);
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
		viewRegistrate.remove(i);
	}

	/**
	 * Permette di avanzare alla fase successiva del turno, e quindi del gioco
	 */
	@Override
	public void prosegui(String messaggio, int indice) {
		viewRegistrate.get(0).prosegui(messaggio);
	}

	@Override
	public void startServer() {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Metodo che nelle GUI chiama l'evidenziazione del giocatore corrispondente a questo client
	 * */
	@Override
	public void evidenziaGiocatore(String nomeGiocatore, int indice) {
		if (viewRegistrate.get(0) instanceof GUIView){
			((GUIView)viewRegistrate.get(0)).getBoard().setGiocatoreEvidenziato(nomeGiocatore);
		}
	}

}
