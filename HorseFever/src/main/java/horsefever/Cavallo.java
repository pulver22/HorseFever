package horsefever;

public class Cavallo {
	
	private int posizione=0;
	private String colore;
	private int quotazione;
	private String effettoPartenza=null;
	private String effettoPartenza2=null;
	private String effettoSprint=null;
	private String effettoSprint2=null;
	private String effettoUltimoPrimo=null;
	private String effettoFotofinish=null;
	private String effettoTraguardo=null;
	private final int SPRINT=1;
	private final int TRAGUARDO=12;
	
	/**
	 * Setta il colore del cavallo
	 * @param colore
	 */
	public Cavallo(String colore){
		this.colore=String.valueOf(colore);
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti alla partenza
	 * @param L'incremento teorico senza effetti delle carte Azione
	 * */
	public void aggiornaPosizionePartenza(int incremento){
		if (effettoPartenza!=null || effettoPartenza2!=null){
			int incr1,incr2;
			if (effettoPartenza!=null && effettoPartenza2!=null){ //Se entrambi tipi di effetti != NULL
				incr1=Integer.parseInt(""+effettoPartenza.charAt(1));
				incr2=Integer.parseInt(""+effettoPartenza2.charAt(1));
				if (effettoPartenza.equals("=4") && effettoPartenza2.equals("-1")){
					posizione=posizione+incr1-incr2;
				} else if (effettoPartenza.equals("=4") && effettoPartenza2.equals("+1")){
					posizione=posizione+incr1+incr2;
				} else if (effettoPartenza.equals("=0") && effettoPartenza2.equals("-1")){
					posizione=posizione+incr1;
				} else if (effettoPartenza.equals("=0") && effettoPartenza2.equals("+1")){
					posizione=posizione+incr1+incr2;
				}
			} else if (effettoPartenza!=null){//Se solo quello con lettera A è !=null
				incr1=Integer.parseInt(""+effettoPartenza.charAt(1));
				if (effettoPartenza.equals("=4")){
					posizione=posizione+incr1;
				} else if (effettoPartenza.equals("=0")){
					posizione=posizione+incr1;
				}
			} else if (effettoPartenza2!=null){//Se, per esclusione, solo quello con lettera B è !=null
				incr2=Integer.parseInt(""+effettoPartenza2.charAt(1));
				if (effettoPartenza2.equals("-1")){
					if (incremento-incr2>=0){ 
						posizione=incremento-incr2; 
					} else{ 
						  posizione=posizione+0;
					}
				} else if (effettoPartenza2.equals("+1")){
					posizione=incremento+incr2;
				}
			}
		} else {
			posizione+=incremento;
		}
	}
	/**
	 * Aggiorna la posizione del cavallo tenendo conto degli effetti di Primo/Ultimo. 
	 * In caso non ve ne siano, chiama il metodo di default aggiornaPosizione (che esegue il
	 * controllo solo sul traguardo).
	 * @param l'incremento teorico che dovrebbe eseguire il cavallo
	 * @param booleano a true nel caso il cavallo sia primo, false altrimenti
	 * @param booleano a true nel caso il cavallo sia ultimo, false altrimenti
	 * @author Niccolo
	 * */
	public void aggiornaPosizionePrimoUltimo(int incremento,boolean primo, boolean ultimo){
		
		if (effettoUltimoPrimo!=null){
			int incr=Integer.parseInt(""+effettoUltimoPrimo.charAt(1));
			if (primo && incr==0){
				posizione=posizione+incr;
			}else if (primo && incr==4){
				posizione=posizione+incremento;
			} else if (ultimo && incr==4){
				posizione+=incr;
			} else if (ultimo && incr==0){
				posizione=posizione+incremento;
			} else if (!ultimo && !primo){
				aggiornaPosizione(incremento); //Se non ha effetti primoUltimo, o li ha senza essere
			}								   //nè primo nè ultimo, delega l'aggiornamento al 
		} else {							   //metodo di default di aggiornamentoPosizione
			aggiornaPosizione(incremento);
		}
	}
	
	/**
	 * Aggiorna la posizione del cavallo applicando effetti allo sprint.
	 * Essendo lo sprint teorico sempre =1 non necessita di parametri, l'invocazione del metodo
	 * implica già che il cavallo abbia ottenuto uno sprint teorico dai dadi
	 * */
	public void aggiornaPosizioneSprint(){
		/*
		 * effettoSprint: +1  o =0  Cioè l'effetto Sprint con lettera C
		 * effettoSprint2:=2  o -1  Cioè l'effetto Sprint con lettera D
		 * 
		 * */
		if (effettoSprint!=null || effettoSprint2!=null){
			int incr1,incr2;
			if (effettoSprint!=null && effettoSprint2!=null){ //Se entrambi tipi di effetti != NULL
				incr1=Integer.parseInt(""+effettoSprint.charAt(1));
				incr2=Integer.parseInt(""+effettoSprint2.charAt(1));
				if (effettoSprint.equals("=0")){
					posizione=posizione+incr1;
				} else if (effettoSprint.equals("+1") && effettoSprint2.equals("-1")){
					posizione=posizione+SPRINT;
				} else if (effettoSprint.equals("+1") && effettoSprint2.equals("=2")){
					posizione=posizione+incr1+incr2;
				}
			} else if (effettoSprint!=null){//Se solo quello con lettera C è !=null
				incr1=Integer.parseInt(""+effettoSprint.charAt(1));
				if (effettoSprint.equals("=0")){
					posizione=posizione+incr1;
				} else if (effettoSprint.equals("+1")){
					posizione=posizione+SPRINT+incr1;
				}
			} else if (effettoSprint2!=null){//Se solo quello con lettera D è !=null
				incr2=Integer.parseInt(""+effettoSprint2.charAt(1));
				if (effettoSprint2.equals("-1")){
					posizione=posizione+SPRINT-incr2;
				} else if (effettoSprint2.equals("=2")){
					posizione=posizione+incr2;
				}
			}
		} else { //Se entrambi =NULL e quindi non ha effetti sprint di nessun tipo
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
		if (posizione>=TRAGUARDO){
			if (effettoTraguardo!=null){
				incr=Integer.parseInt(""+effettoTraguardo.charAt(1));
				if (effettoTraguardo.charAt(0)=='='){
					posizione=TRAGUARDO; //il "si ferma immediatamente" nelle regole l'ho interpretato come "si ferma SUL traguardo"
								  //anche perchè altrimenti un cavallo in generale si fermerebbe già, una volta superato il traguardo
				} else if (effettoTraguardo.charAt(0)=='+'){
					posizione+=incr;
				}
			}
		}
	}
	/**
	 * Funzione che controlla che il cavallo sia oltre il traguardo.
	 * @return booleano a true se il cavallo ha superato il traguardo, false altrimenti
	 * @author Niccolo
	 * */
	public boolean oltreTraguardo(){
		if (posizione>=TRAGUARDO){ return true; }
		else{ return false; }
	}
	/**
	 * Resetta il cavallo alle condizioni precedenti la corsa. 
	 * Mantiene sostanzialmente solo la quotazione del cavallo (che deve rimanere nei turni a seguire
	 * per i controlli di fotofinish).
	 * */
	public void reset(){
		posizione=0;
		effettoPartenza=null;
		effettoPartenza2=null;
		effettoSprint=null;
		effettoSprint2=null;
		effettoUltimoPrimo=null;
		effettoFotofinish=null;
		effettoTraguardo=null;
	}
	
	public int getPosizione() {
		return posizione;
	}
	
	public void setPosizione(int posizione) {
		this.posizione = Integer.valueOf(posizione);
	}
	
	public String getColore() {
		return colore;
	}

	public String getEffettoPartenza() {
		return effettoPartenza;
	}
	
	public void setEffettoPartenza(String effettoPartenza) {
		this.effettoPartenza = String.valueOf(effettoPartenza);
	}

	public void setEffettoSprint(String effettoSprint) {
		this.effettoSprint = String.valueOf(effettoSprint);
	}
	
	public String getEffettoUltimoPrimo() {
		return effettoUltimoPrimo;
	}

	public void setEffettoUltimoPrimo(String effettoUltimoPrimo) {
		this.effettoUltimoPrimo = String.valueOf(effettoUltimoPrimo);
	}

	public String getEffettoFotofinish() {
		return effettoFotofinish;
	}
	
	public void setEffettoFotofinish(String effettoFotofinish) {
		this.effettoFotofinish = String.valueOf(effettoFotofinish);
	}

	public void setEffettoTraguardo(String effettoTraguardo) {
		this.effettoTraguardo = String.valueOf(effettoTraguardo);
	}

	public int getQuotazione() {
		return quotazione;
	}
	
	public void setQuotazione(int quotazione) {
		this.quotazione = Integer.valueOf(quotazione);
	}

	/**
	 * Rispetto ai normali getter/setter degli effetti sul cavallo, questo esegue anche
	 * immediatamente la modifica dell'attributo del cavallo, facendo anche un controllo
	 * che la quotazione non assuma valori non validi (<2 o >7)
	 * @param stringa dell'effetto quotazione letto dalla carta
	 * */
	public void setEffettoQuotazione(String effettoQuotazione) {
		if (effettoQuotazione.charAt(0)=='+'){
			quotazione-=2;
			if (quotazione<=2){ quotazione=2; }
		} else {
			quotazione+=2;
			if (quotazione>=7){ quotazione=7; }
		}
	}
	
	public String toString(){
		return colore+" con quotazione "+quotazione+" e posizione "+posizione;
	}
	
	public String getEffettoPartenza2() {
		return effettoPartenza2;
	}
	
	public void setEffettoPartenza2(String effettoPartenza2) {
		this.effettoPartenza2 = String.valueOf(effettoPartenza2);
	}

	public void setEffettoSprint2(String effettoSprint2) {
		this.effettoSprint2 = String.valueOf(effettoSprint2);
	}
	
	
}
