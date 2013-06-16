package eventi;

public class eventoEffettoAvvenuto extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2425905078412852498L;
	private String cartaAttivata;
	private int corsia;
	
	/**
	 * Inizializza l'evento effetto avvenuto
	 * @param la stringa che rappresenta la carta attivata
	 * @param la corsia su cui si è attivata la carta
	 * */
	public eventoEffettoAvvenuto(String cartaAttivata, int corsia){
		this.cartaAttivata=cartaAttivata;
		this.corsia=corsia;
	}
	/***/
	@Override
	public String rappresentazione() {
		return "Ha avuto effetto la carta "+cartaAttivata+" sulla corsia "+corsia;
	}
	/***/
	public String getCartaAttivata() {
		return cartaAttivata;
	}
	/***/
	public int getCorsia() {
		return corsia;
	}

}
