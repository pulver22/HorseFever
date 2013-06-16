package eventi;

public class eventoArrivi extends HorseFeverEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7147089307672438729L;
	private int posArrivo;
	private String cavallo;
	
	/**
	 * Inizializza l'evento arrivi.
	 * @param il colore dle cavallo arrivato
	 * @param la posizione alla quale è arrivato
	 * */
	public eventoArrivi(String cavallo, int posArrivo){
		this.posArrivo=posArrivo;
		this.cavallo=String.valueOf(cavallo);
	}
	/***/
	@Override
	public String rappresentazione() {
		return "Il Cavallo "+cavallo+" è arrivato "+posArrivo;
	}
	/***/
	public int getPosArrivo() {
		return posArrivo;
	}
	/***/
	public String getCavallo() {
		return cavallo;
	}

	
}
