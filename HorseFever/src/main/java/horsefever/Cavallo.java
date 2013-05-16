package horsefever;

public class Cavallo {
	
	private int posizione=0;
	private String colore;
	private String effettoPartenza;
	private String effettoSprint;
	private String effettoUltimoPrimo;
	private String effettoFotofinish;
	private String effettoTraguardo;
	
	public Cavallo(String colore){
		this.colore=colore;
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti alla partenza
	 * @param L'incremento teorico senza effetti delle carte Azione
	 * */
	public void aggiornaPosizionePartenza(int incremento){
		
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti allo sprint.
	 * Essendo lo sprint teorico sempre =1 non necessita di parametri, l'invocazione del metodo
	 * implica già che il cavallo abbia ottenuto uno sprint teorico dia dadi
	 * */
	public void aggiornaPosizioneSprint(){
		
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando gli effetti sul movimento generico (non alla partenza)
	 * @param L'incremento teorico senza effetti delle carte Azione
	 * @param Booleano che indica se vadano applicati gli effetti primoUltimo (qualora presenti)
	 * @param Booleano che indica se vadano applicati gli effetti al Traguardo
	 * */
	public void aggiornaPosizione(int incremento, boolean primoUltimo, boolean traguardo){
		
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
