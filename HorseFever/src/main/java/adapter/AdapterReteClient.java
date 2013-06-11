package adapter;

import horsefever.Azione;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterReteClient implements Adapter{

	//private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;
    private Socket socket=null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
 
    public void connetti(String serverIP) {
        try {
            socket = new Socket(serverIP, SERVER_PORT);
            System.out.println("Connected to server!");
        } catch (Exception ex) {
            System.out.println("Error connecting to server: " + ex.getMessage());
            throw new RuntimeException();
        }
    }
    
    public void start(){
    	//ObjectInputStream in = null;
        //ObjectOutputStream out = null;
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
		
		String[] scelta = new String[2];
		
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

}
