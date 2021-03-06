package View;

import java.util.ArrayList;

import eventi.*;

import horsefever.*;

public interface View {
	
	//chiamati dal controller
	
	/**
	 * Chiede all'utente su quale corsia vuole scommettere,quanto, e se piazzato o vincente
	 */
	String[] chiediScommessa();
	
	/**
	 * Se il giocatore vuole fare una seconda scommessa,viene lanciato il metodo per scommettere.
	 * Altrimenti setta il paramentro vincente/piazzato a valore nullo (N)
	 */
	String[] chiediSecondaScommessa();
	
	/**
	 * 
	 * @param num  carta azione in mano
	 * @param nome carta azione in mano 
	 * @param effetto carta azione
	 * @param valoreEffetto della carta azione
	 * @return
	 */
	String[] chiediTrucca(ArrayList<Azione> carteAzione);

	/**
	 * Stampa a video una stringa generica
	 * @param messaggio 
	 */
	void stampaMessaggio(String messaggio);
	
	
	
	/**
	 * Metodo per bloccare la logica in attesa di un comando dell'utente
	 * @param messaggio da stampare 
	 * */
	void prosegui(String messaggio);
	
	
	
	//chiamati dal model
	/**
	 * Metodo per gestire le notifiche in arrivo dal model
	 * @param l'evento da gestire
	 * */
	void notify(HorseFeverEvent e);


}
