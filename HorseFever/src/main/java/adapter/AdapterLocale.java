package adapter;

import horsefever.Azione;

import java.util.ArrayList;
import View.*;
import eventi.HorseFeverEvent;

public class AdapterLocale implements Adapter {

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

	@Override
	public void connetti(String serverIP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

}
