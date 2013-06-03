package View;

import java.util.ArrayList;

import eventi.*;

import horsefever.*;

public interface View {
	
	//chiamati dal controller
	
	/**
	 * Chiede all'utente su quale corsia vuole scommettere,quanto, e se piazzato o vincente
	 */
	public String[] chiediScommessa();
	
	/**
	 * Se il giocatore vuole fare una seconda scommessa,viene lanciato il metodo per scommettere.
	 * Altrimenti setta il paramentro vincente/piazzato a valore nullo (N)
	 */
	public String[] chiediSecondaScommessa();
	
	/**
	 * 
	 * @param num  carta azione in mano
	 * @param nome carta azione in mano 
	 * @param effetto carta azione
	 * @param valoreEffetto della carta azione
	 * @return
	 */
	public String[] chiediTrucca(ArrayList<Azione> carteAzione);

	/**
	 * Stampa a video una stringa generica
	 * @param messaggio 
	 */
	public void stampaMessaggio(String messaggio);
	
	
	
	
	public void prosegui(String messaggio);
	
	
	
	//chiamati dal model
	public void notify(HorseFeverEvent e);


}
