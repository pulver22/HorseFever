package horsefever;

public class Cavallo {
	
	private int posizione=0;
	private String colore;
	private String effettoPartenza;
	private String effettoSprint;
	private String effettoUltimoPrimo;
	private String effettoFotofinish;
	private String effettoTraguardo;
	private final int SPRINT=1;
	
	public Cavallo(String colore){
		this.colore=colore;
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti alla partenza
	 * @param L'incremento teorico senza effetti delle carte Azione
	 * */
	public void aggiornaPosizionePartenza(int incremento){
		int incr;
		if (effettoPartenza!=null){
			incr=Integer.parseInt(""+effettoPartenza.charAt(1));
			if (effettoPartenza.charAt(0)=='='){
				posizione+=incr;
			} else if (effettoPartenza.charAt(0)=='+'){
				posizione=posizione+incr+incremento;
			} else if (effettoPartenza.charAt(0)=='-'){
				posizione=posizione+incremento-incr;
			}
		} else {
			posizione+=incremento;
		}
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti allo sprint.
	 * Essendo lo sprint teorico sempre =1 non necessita di parametri, l'invocazione del metodo
	 * implica già che il cavallo abbia ottenuto uno sprint teorico dia dadi
	 * */
	public void aggiornaPosizioneSprint(){
		int incr;
		if (effettoSprint!=null){
			incr=Integer.parseInt(""+effettoSprint.charAt(1));
			if (effettoSprint.charAt(0)=='='){
				posizione+=incr;
			} else if (effettoSprint.charAt(0)=='+'){
				posizione=posizione+SPRINT+incr;
			} else if (effettoSprint.charAt(0)=='-'){
				posizione=posizione+SPRINT-incr;
			}
		} else {
			posizione+=SPRINT;
		}
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando gli effetti sul movimento generico (non alla partenza) e eventuali effetti Traguardo
	 * @param L'incremento teorico senza effetti delle carte Azione
	 * */
	public void aggiornaPosizione(int incremento){
		int incr;
		posizione+=incremento;
		if (posizione>=13){
			if (effettoTraguardo!=null){
				incr=Integer.parseInt(""+effettoTraguardo.charAt(1));
				if (effettoTraguardo.charAt(0)=='='){
					posizione=13; //il "si ferma immediatamente" nelle regole l'ho interpretato come "si ferma SUL traguardo"
								  //anche perchè altrimenti un cavallo in generale si fermerebbe già, una volta superato il traguardo
				} else if (effettoTraguardo.charAt(0)=='+'){
					posizione+=incr;
				}
			}
		}
	}
	
	public void resetPosizione(){
		posizione=0;
	}
	
	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getEffettoPartenza() {
		return effettoPartenza;
	}

	public void setEffettoPartenza(String effettoPartenza) {
		this.effettoPartenza = effettoPartenza;
	}

	public String getEffettoSprint() {
		return effettoSprint;
	}

	public void setEffettoSprint(String effettoSprint) {
		this.effettoSprint = effettoSprint;
	}

	public String getEffettoUltimoPrimo() {
		return effettoUltimoPrimo;
	}

	public void setEffettoUltimoPrimo(String effettoUltimoPrimo) {
		this.effettoUltimoPrimo = effettoUltimoPrimo;
	}

	public String getEffettoFotofinish() {
		return effettoFotofinish;
	}

	public void setEffettoFotofinish(String effettoFotofinish) {
		this.effettoFotofinish = effettoFotofinish;
	}

	public String getEffettoTraguardo() {
		return effettoTraguardo;
	}

	public void setEffettoTraguardo(String effettoTraguardo) {
		this.effettoTraguardo = effettoTraguardo;
	}
	
	
}
