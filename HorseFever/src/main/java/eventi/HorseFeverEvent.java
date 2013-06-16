package eventi;

import java.io.Serializable;

public abstract class HorseFeverEvent implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 119661390107983124L;

	/**
	 * Restituisce la rappresentazione in stringa dell'evento.
	 * @return la stringa che rappresenta l'evento.
	 * */
	public abstract String rappresentazione();

}
