package adapter;

import horsefever.Azione;

import java.util.ArrayList;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterRete implements Adapter{
	
	/**
	 * Chiede ad ogni giocatore registrato di fare una scommessa
	 */
	@Override
	public String[] chiediScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Chiedi ad ogni giocatore registrato di effettuare la seconda scommessa
	 */
	@Override
	public String[] chiediSecondaScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Chiedi ad ogni giocatore registrato di truccare la corsa
	 */
	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Stampa a video di ogni giocatore registrato un messaggio
	 */
	@Override
	public void stampaMessaggio(String messaggio, int indice) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica ad ogni giocatore registrato un evento
	 */
	@Override
	public void notify(HorseFeverEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Aggiunge una view, quindi registra un giocatore
	 */
	@Override
	public void addView(View v) {
		// TODO Auto-generated method stub
		
	}
	
	/**
    * Rimuove una view, quindi elimina un giocatore dal gioco
	 */
	@Override
	public void removeView(int i) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Permette di avanzare alla fase successiva del turno, e quindi del gioco
	 */
	@Override
	public void prosegui(String messaggio, int indice) {
		// TODO Auto-generated method stub
		
	}

}
