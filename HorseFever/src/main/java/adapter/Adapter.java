package adapter;

import horsefever.Azione;

import java.util.ArrayList;

import View.View;

import eventi.HorseFeverEvent;

public interface Adapter {

	/**Metodo per la richiesta di una scommessa a un giocatore
	 * @param l'indice del giocatore a cui chiederla
	 * @return l'array di String con i parametri inseriti da utente
	 * */
	String[] chiediScommessa(int indice);
	
	/**Metodo per la richiesta di una seconda scommessa a un giocatore
	 * @param l'indice del giocatore a cui chiederla
	 * @return l'array di String con i parametri inseriti da utente
	 * */
	String[] chiediSecondaScommessa(int indice);
	
	/**Metodo per la richiesta di truccare la corsa a un giocatore
	 * @param l'indice del giocatore a cui chiederla
	 * @return l'array di String con i parametri inseriti da utente
	 * */
	String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice);
	
	/**Metodo per la stampa di un generico messaggio all'utente
	 * @param il messaggio da stampare
	 * @param l'indice del giocatore al quale inviarlo
	 * */
	void stampaMessaggio(String messaggio,int indice);
	
	/**Metodo per la notifica di un evento a un Giocatore
	 * @param l'event da notificare
	 * */
	void notify(HorseFeverEvent e);
	
	/**Metodo per fermare la logica tra una fase e l'altra fino a comando dell'utente
	 * @param messaggio da stampare
	 * @param l'indice del giocatore a cui chiederla
	 * */
	void prosegui(String messaggio, int indice);
	
	/**Metodo per registrare una vista all'adapter
	 * @param la View da registrare
	 * */
	void addView(View v);
	
	/**Metodo per la rimozione di una view registrata all'adapter
	 * @param l'indice della view da rimuovere
	 * */
	void removeView(int i);
	
	/**Metodo per la rete, che avvia il server
	 * */
	void startServer();
	
	/**Metodo per la rete, che connette il client al server
	 * @param l'ip del server
	 * */
	void connetti(String serverIP);
	
	/**Metodo per la rete, avvia il client
	 * */
	void start();
	
	/**Metodo per evidenziare, in rete, il nome del giocatore che rappresenta il client
	 * */
	void evidenziaGiocatore(String nomeGiocatore, int indice);
	
}
