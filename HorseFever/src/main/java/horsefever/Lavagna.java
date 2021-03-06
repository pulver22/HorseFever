package horsefever;

import java.util.ArrayList;

import eventi.eventoQuotazioni;

public class Lavagna {

	private static final int NUM_CORSIE=6;
	private static final int QUOT_MIN=7;
	private static final int QUOT_MAX=2;
	
	private String[][] quotazioni;
	private Partita partita;
	private Plancia plancia;
	private String[] colori={"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	private boolean debug=false;
	
	/**
	 * Il costruttore di Lavagna. Inizializza la prima colonna di quotazioni alle Stringhe dei colori corrispondenti alle scuderie
	 * secondo l'ordine convenzionale:
	 * 0 = Nero
	 * 1 = Blu
	 * 2 = Verde
	 * 3 = Rosso
	 * 4 = Giallo
	 * 5 = Bianco
	 * Successivamente, ai fini della preparazione di inizio gioco, assegna casualmente una quotazione differente a ciascuna scuderia 
	 * per ottenere le quotazioni iniziali.
	 * */
	public Lavagna(Partita p){
		this.partita=p;
		inizializzaQuot();
	}

	/**
	 * Inizializza TabellaQuotazioni con valori iniziali casuali (o fissi se in caso di debug)
	 * */
	public void inizializzaQuot(){
		int temp;
		quotazioni=new String[NUM_CORSIE][2];
		ArrayList<Integer> init = new ArrayList<Integer>(NUM_CORSIE);
		for (int i=QUOT_MAX; i<(QUOT_MIN+1) ;i++){
			init.add(i);
		}
		for (int j=0; j<NUM_CORSIE; j++){
			if (debug){//Quotazioni iniziali in caso di Debug o Test
				quotazioni[j][1]=Integer.toString(j+2);
			}else{ //Quotazioni iniziali in caso di partita ordinaria
				temp=(int) (Math.random()*init.size());
				quotazioni[j][1]=init.get(temp).toString();
				init.remove(temp);
			}
			quotazioni[j][0]=String.valueOf(colori[j]);
		}

		partita.notifyObserver(new eventoQuotazioni(quotazioni));
	}
	
	/**
	 * Ricalcola le quotazioni delle relative scuderie, in base all'ordine d'arrivo dei corrispondenti cavalli
	 * @param Ordine d'arrivo dei cavalli, contraddistinti dal colore che li rappresenta
	 * */
	public void ricalcolaQuotazioni(String[] ordineArrivo){
		String [] arrivi=ordineArrivo;
		for (int i=0; i<NUM_CORSIE; i++){
			for (int j=0; j<NUM_CORSIE; j++){   //Ricerca in quotazioni cavallo==cavallo in arrivi
				if (arrivi[i].equals(quotazioni[j][0])){
					int posCavallo=i+1; //Poichè l'indice i è di 1 inferiore alla posizione reale
					int quotCavallo=Integer.parseInt(quotazioni[j][1]);
					/*Confronto tra quotazioni e posizione perchè 
					 Riga cavallo=Posizione cavallo
					 Riga Scuderia= quotazione scuderia -1 ( 1:2 = > 2 = > 2-1=1 riga della scuderia a quotazione 1:2)
					 confrontando così le righe di scuderia e cavallo stabilisco se far salire
					 o diminuire la quotazione di quel cavallo*/
					if (posCavallo>(quotCavallo-1)){
						if (quotCavallo<QUOT_MIN){
							quotCavallo++;
							
						}
					} else if (posCavallo<(quotCavallo-1)){
						if (quotCavallo>QUOT_MAX){
							quotCavallo--;
						}
					} else {
						quotCavallo=quotCavallo+0;
					}
					quotazioni[j][1]=Integer.toString(quotCavallo); //Aggiorna nuova quotazione in tabella quotazioni
					
				}
			}
		}
		for (int i=0; i<NUM_CORSIE;i++){ //Aggiorna ai cavalli in plancia la loro nuova quotazione
			plancia.getCavalloAt(i).setQuotazione(Integer.parseInt(quotazioni[i][1]));
		}
		partita.notifyObserver(new eventoQuotazioni(quotazioni));
	}
	/**
	 *Dato come parametro il numero della corsia del cavallo, rende la riga della carta movimento da cui leggere il valore
	 *in base alla quotazione del cavallo in quella corsia
	 *@param il numero della corsia del cavallo di cui si vuole sapere la riga del movimento
	 *@return L'indice della riga della carta movimento corrispondente al movimento relativo di quel cavallo
	 */
	public int getRigaMovimento(int numCorsiaCavallo){
		/*
		 * Come per il ricalcolo delle quotazioni, essendo:
		 * indice riga carta movimento [0,...,5] = (quotazione[2,...,7] -2)[0,...,5]
		 * sarà:
		 **/
		return (Integer.parseInt(quotazioni[numCorsiaCavallo][1]) -2);
	}
	/**
	 * Per ottenere il colore corrispondente alla scuderia che è alla data quotazione. Serve solo per la preparazione iniziale, quando ogni 
	 * quotazione ha assegnata una ed una sola scuderia distinta, altrimenti potrebbe ritornare NULL
	 * @param La stringa corrispondente alla quotazione (se "1:2" va passato "2" )di cui si vuole sapere a quale scuderia è assegnata
	 * @return La stringa corrispondente al colore della scuderia cui è assegnata quella quotazione
	 * @author Niccolo
	 * */
	public String getScuderiaInit(String quotazione){
		String scuderia=null;
		
		for (int j=0; j<NUM_CORSIE;j++){
			if (quotazione.equals(quotazioni[j][1])) {
				scuderia=quotazioni[j][0];
			}
		}
		return scuderia;
	}

	//Metodi Getter e Setter
	/***/
	public String[][] getQuotazioni() {
		String[][] quot=new String[NUM_CORSIE][2];
		for (int i=0;i<NUM_CORSIE;i++){
			quot[i][0]=String.valueOf(quotazioni[i][0]);
			quot[i][1]=String.valueOf(quotazioni[i][1]);
		}
		return quot;
	}
	/***/
	public void setQuotazioni(String[][] quotazioni) {
		this.quotazioni = quotazioni.clone();
	}
	/**
	 * Dato il colore della scuderia corrispondente, ritorna la quotazione di quel cavallo. 
	 * Metodo usabile solo a inizio partita per settare quotazioni iniziali cavalli
	 * @param il colore del cavallo
	 * @param la quotazione del cavallo con quel colore
	 * @author Niccolo
	 * */
	public int getQuotazioneDaColoreIniziale(String colore){
		int quot=0;
		for (int i=0; i<NUM_CORSIE;i++){
			if (quotazioni[i][0].equals(colore)) {
				quot=Integer.parseInt(quotazioni[i][1]);
			}
		}
		return quot;
	}
	
	/**
	 * Aggiorna la variazione della quotazione di un determinato cavallo
	 * @param colore
	 * @param variazione
	 */
	public void setQuotazioneAlCavallo(String colore, String variazione){
		int quot;
		int incremento=2;
		for (int i=0; i<NUM_CORSIE;i++){
			if (colore.equals(quotazioni[i][0])){
				quot=Integer.parseInt(quotazioni[i][1]);
				if (variazione.charAt(0)=='+'){
					quot-=incremento;
					if (quot<=QUOT_MAX){ quot=QUOT_MAX; }
				} else {
					quot+=incremento;
					if (quot>=QUOT_MIN){ quot=QUOT_MIN; }
				}
				quotazioni[i][1]=Integer.toString(quot);
			}
		}
		partita.notifyObserver(new eventoQuotazioni(quotazioni));
	}
	/***/
	public void setPlancia(Plancia plancia) {
		this.plancia = plancia;
	}
	
	/**
	 * Setta lo stato di Debug. In tale caso ricalcola anche quotazioni iniziali convenzionali
	 * */
	public void setDebug(boolean debug){
		this.debug=debug;
		inizializzaQuot();
	}
}
