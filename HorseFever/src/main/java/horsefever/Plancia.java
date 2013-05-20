package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

public class Plancia {
	
	boolean[] arrivati = new boolean[6];
	ArrayList<String> ordineArrivo = new ArrayList<String>(6);
	int[] posizione = new int[6];
	private String colori[] = {"Nero","Blu","Verde","Rosso","Giallo","Bianco"}; 
	Cavallo[] cavalli = new Cavallo[6];
	ArrayList[] corsieTruccate = new ArrayList[6];
	private boolean partenza=true;
	private ArrayList<Cavallo> fotofinish=new ArrayList<Cavallo>();
	private ArrayList<Cavallo> cavalliArrivati=new ArrayList<Cavallo>();
	private Lavagna lavagna;
	
	public Plancia(Lavagna lavagna){
		this.lavagna=lavagna;
		for (int i=0;i<6; i++){
			corsieTruccate[i]=new ArrayList<Azione>();		
			posizione[i]=0;
			cavalli[i]=new Cavallo(colori[i]);
			cavalli[i].setQuotazione(lavagna.getQuotazioneDaColoreIniziale(colori[i]));
		}
	}
	
	/**
	 * Aggiunge la carta azione su quella determinata corsia
	 * @param carta
	 * @param numCorsia
	 */
	public void TruccaCorsia(Azione carta,int numCorsia){
		corsieTruccate[numCorsia-1].add(carta);
	}

	/**
	 * @author Niccolo
	 * Applica gli effetti di tutte le carte Azioni su tutti i Cavalli
	 * */
	public void applicaAzioni(){
		for (int i=0; i<6;i++){
			controllaAzioniDiRimozione(corsieTruccate[i]);
			eliminaEffettiOpposti(corsieTruccate[i]);
			assegnaEffettiAlCavallo(corsieTruccate[i],cavalli[i]);
		}
	}
	
	/**
	 * @author Niccolo
	 * Controlla e applica effetti delle carte Grigie che rimuovono tutte le carte Verdi o Rosse
	 * @param l'ArrayList dell carte Azione su cui fare il controllo
	 * */
	public void controllaAzioniDiRimozione(ArrayList azioni){
		Azione a;
		char carteDaRimuovere='0';
		for (int i=0; i<azioni.size();i++){
			a=(Azione) azioni.get(i);
			if (a.getColore()=="Grigio" && a.getTipoEffetto()=="Azione"){
				if (a.getValoreEffetto().charAt(8)=='p') carteDaRimuovere='P';
				else carteDaRimuovere='N';
			}
		}
		if (carteDaRimuovere!='0'){
			if (carteDaRimuovere=='N'){
				for (int j=0;j<azioni.size();j++){
					a=(Azione) azioni.get(j);
					if (a.getColore()=="Rosso"){
						azioni.remove(j);
					}
				}
			} else {
				for (int j=0; j<azioni.size();j++){
					a=(Azione) azioni.get(j);
					if (a.getColore()=="Verde"){
						azioni.remove(j);
					}
				}
			}
		}
	}

	/**
	 * @author Niccolo
	 * Assegna agli attributi di Cavallo i valori delle Carte Azioni presenti su di esso
	 * @param ArrayList delle Carte Azione assegnate al cavallo
	 * @param Il cavallo su cui assegnare gli effetti
	 * */
	public void assegnaEffettiAlCavallo(ArrayList azioni, Cavallo cavallo){
		for (int i=0; i<azioni.size();i++){
			Azione a= (Azione)azioni.get(i);
			if (a.getTipoEffetto()=="Partenza") cavallo.setEffettoPartenza(a.getValoreEffetto());
			if (a.getTipoEffetto()=="Sprint") cavallo.setEffettoSprint(a.getValoreEffetto());
			if (a.getTipoEffetto()=="Fotofinish") cavallo.setEffettoFotofinish(a.getValoreEffetto());
			if (a.getTipoEffetto()=="Traguardo") cavallo.setEffettoTraguardo(a.getValoreEffetto());
			if (a.getTipoEffetto()=="Ultimo" || a.getTipoEffetto()=="Primo") cavallo.setEffettoUltimoPrimo(a.getValoreEffetto());
			if (a.getTipoEffetto()=="Quotazione") {
				cavallo.setEffettoQuotazione(a.getValoreEffetto());
				lavagna.setQuotazioneAlCavallo(cavallo.getColore(), a.getValoreEffetto());
			}
		}
	}
	
	/**
	 * @author Niccolo
	 * Verifica se in un ArrayList di carte Azione, ce ne sono alcune che si annullano
	 * @param l'arrayList delle carte Azione da controllare
	 * */
	public void eliminaEffettiOpposti(ArrayList azioni){
		for (int i=0; i<azioni.size()-1; i++){
			for (int j=1; j<azioni.size(); j++){
				Azione a1= (Azione)azioni.get(i);
				Azione a2= (Azione)azioni.get(j);
				if (a1.getTipoEffetto().equals(a2.getTipoEffetto())){
					if (a1.getValoreEffetto().charAt(0)==a2.getValoreEffetto().charAt(0)||
							(a1.getValoreEffetto().charAt(0)=='+' && a2.getValoreEffetto().charAt(0)=='-')||
							a1.getValoreEffetto().charAt(0)=='-' && a2.getValoreEffetto().charAt(0)=='+'){
						azioni.remove(i);
						azioni.remove(j);
					}
				}
			}
		}
	}
	
	/**
	 * @author Niccolo
	 * Esegue i movimenti dei cavalli alla partenza
	 * @param l'array dei movimenti teorici dei cavalli
	 * */
	public void partenza(int[] movimenti){
		for (int i=0; i<6;i++){
			cavalli[i].aggiornaPosizionePartenza(movimenti[i]);
		}
	}
	
	/**
	 * @author Niccolo
	 * Esegue i movimenti dei cavalli successivi alla partenza
	 * @param l'array dei movimenti teorici dei cavalli
	 * */
	public void muovi(int[] movimenti){
		Cavallo c;
		int[] dadiSprint=sprint();
		
		for(int i=0; i<6; i++){
			if (cavalli[i]!=null){
				if (cavalli[i].getEffettoUltimoPrimo()!=null){
					int[] primiPari=getCavalliPrimiPari(),ultimiPari=getCavalliUltimiPari();
					if (primiPari[i]==1) cavalli[i].aggiornaPosizionePrimoUltimo(movimenti[i], true);
					else if (ultimiPari[i]==1) cavalli[i].aggiornaPosizionePrimoUltimo(movimenti[i], false);
				}
				cavalli[i].aggiornaPosizione(movimenti[i]);
				if (dadiSprint[i]==1) cavalli[i].aggiornaPosizioneSprint();
			}
		}
		gestioneArrivi();
		
	}
	
	/**
	 * @author Niccolo
	 * Gestisce arrivi e fotofinish del round di corsa attuale
	 * */
	public void gestioneArrivi(){
		inserisciArrivati();
		fotoFinish();
	}
	
	/**
	 * @author Niccolo
	 * Controlla se un cavallo è arrivati prima di tutti gli altri e in tal caso lo inserisce in arrivi
	 * rimuovendo il suo riferimento dall'array dei cavalli
	 * */
	public void inserisciArrivati(){
		int flagArrivo=0;
		for (int j=0;j<6;j++){
			if (!arrivati[j]){
				if (cavalli[j].oltreTraguardo()){
					for (int k=j+1;k<6;k++){
						if (!arrivati[k]){
							if (cavalli[j].getPosizione()>cavalli[k].getPosizione()) flagArrivo+=1;
							else flagArrivo-=1;
						} else flagArrivo+=1;//Per ogni cavallo fuori gara o con posizione inferiore
					}						 //aumenta il flag, altrimenti lo diminuisce
					if (flagArrivo==(5-j)) { //se alla fine il flag è pari ai cavalli dopo quello in analisi
						cavalliArrivati.add(cavalli[j]); //vuole dire che è davanti a tutti e quindi primo
						arrivati[j]=true;
						//cavalli[j]=null;
					}
					flagArrivo=0;
				}
			}
		}
	}
	/**
	 * @author Niccolo
	 * Esegue il controllo di fotofinish sui vari cavalli nel round di corsa corrente
	 * */
	public void fotoFinish(){
		int[] flagFotofinish=new int[6];
		char[] effettiCarte;
		ArrayList<Cavallo> ordineCavalli=new ArrayList();
		boolean almenoUnCavalloPari=false;
		for (int i=0; i<6; i++){
			if (!arrivati[i]){
				if (cavalli[i].oltreTraguardo()){ //Per ogni cavallo ancora in gara che ha superato il traguardo
					flagFotofinish[i]=1; 
					arrivati[i]=true;
					for (int j=i+1;j<6;j++){
						if (!arrivati[j]){
							if (cavalli[i].getPosizione()==cavalli[j].getPosizione()){
								flagFotofinish[j]=1; //Tiene conto dell'indice di cavalli eventualmente pari a
													 //a quello in analisi
								almenoUnCavalloPari=true;
								arrivati[j]=true;
							}
						}
					}
					if (almenoUnCavalloPari){ //Se è stato trovato almeno un cavallo paria quello in analisi
						for (int k=0;k<6;k++){
							if (flagFotofinish[k]==1) {
								fotofinish.add(cavalli[k]);
								arrivati[k]=true;
								//cavalli[k]=null;
								flagFotofinish[k]=0;// Inserisce nell'ArrayList fotofinish i cavalli da controllare
							}
						}
						
						for (int n=0; n<fotofinish.size(); n++){ //Ricerca di cavallo con effetto vincente
							if (fotofinish.get(n).getEffettoFotofinish().charAt(1)=='1'){
								ordineCavalli.add(fotofinish.get(n)); //Mette in primo posto 
								fotofinish.remove(n);				//il cavallo con l'effetto fotofinish vincente
							}
						for (int m=0; m<fotofinish.size();m++){//Ricerca di cavallo con effetto perdente
							if (fotofinish.get(m).getEffettoFotofinish().charAt(1)=='0'){
								ordineCavalli.add(fotofinish.get(m));
								fotofinish.remove(m);
							}
						}
						
						for (int l=0; l<fotofinish.size();l++){//I restanti li inserisce coma capita tra il primo e l'ultimo
							ordineCavalli.add(1, fotofinish.get(l));
						}
						for (int a=0; a<ordineCavalli.size(); a++){ //Inserimento ordinato nei cavalliArrivati
							cavalliArrivati.add(ordineCavalli.get(a));//di quelli risultanti il fotofinish
						}
						}
					}
				}
			}
		}
	}
	
	/**
	 * @author Niccolo
	 * Metodo di supporto, ritorna i cavalli in prima posizione
	 * @return un array di int con 1 se il cavallo corrispondente è primo (eventualmente parimerito con altri)
	 * 0 se invece non lo è. 
	 * */
	public int[] getCavalliPrimiPari(){
		int[] primiPari=new int[6];
		int max=getMax();
		for (int i=0;i<6;i++){
			if (cavalli[i].getPosizione()==max){
				primiPari[i]=1;
			} else primiPari[i]=0;
		}
		return primiPari;
	}
	
	/**
	 * @author Niccolo
	 * Metodo di supporto, ritorna i cavalli in ultima posizione
	 * @return un array di int con 1 se il cavallo corrispondente è ultimo (eventualmente parimerito con altri)
	 * 0 se invece non lo è. 
	 * */
	public int[] getCavalliUltimiPari(){
		int[] ultimiPari=new int[6];
		int min=getMin();
		for (int i=0;i<6;i++){
			if (cavalli[i].getPosizione()==min){
				ultimiPari[i]=1;
			} else ultimiPari[i]=0;
		}
		return ultimiPari;
	}
	
	/**
	 * @author Niccolo
	 * Metodo di supporto, ritorna la posizione massima tra quelle attuali dei cavalli
	 * */
	public int getMax(){
		int max=0;
		for (int i=0;i<6;i++){
			if (max<cavalli[i].getPosizione()) max=cavalli[i].getPosizione();
			else max=max;
		}
		return max;
	}
	
	/**
	 * @author Niccolo
	 * Metodo di supporto, ritorna la posizione minima tra quelle attuali dei cavalli
	 * */
	public int getMin(){
		int min=0;
		for (int i=0;i<6;i++){
			if (min<cavalli[i].getPosizione()) min=cavalli[i].getPosizione();
			else min=min;
		}
		return min;
	}
	
	/**
	 * @author Niccolo
	 * Esegue lo pseudo lancio dei dadi sprint
	 * @return un array di 6 elementi. Ciascuno a 0 se non è uscito con nessun lancio, 1 se è uscito 
	 * */
	public int[] sprint(){
		int c;
		int movimentiSprint[]=new int[6];
		for (int i=0; i<6; i++){
			movimentiSprint[i]=0;
		}
		for (int j=0; j<2; j++){
			
			c=(int)(Math.random()*6);
			if(movimentiSprint[c]!=1) movimentiSprint[c]=1;
		
		}
		return movimentiSprint;
		
	}
	
	/**
	 * @author Niccolo
	 * Ritorna l'Array di String dei colori dei cavalli nell'ordine d'arrivo
	 * @return I colori dei cavalli in ordine d'arrivo
	 * */
	public String[] getColoriArrivi(){
		String colori[]=new String[6];
		for (int i=0; i<6;i++){
			if(cavalliArrivati!=null)
			colori[i]=cavalliArrivati.get(i).getColore();
		}
		return colori;
	}
	
	public Cavallo getCavalloArrivatoInPos(int posizione){
		return cavalliArrivati.get(posizione);
	}
	
	/**
	 * @author Niccolo
	 * Ritorna un array di interi corrispondenti alle posizioni dei vari cavalli
	 * @return l'array delle posizioni
	 * */
	public int[] getPosizioniCavalli(){
		int[] posizioni=new int[6];
		for (int i=0; i<6; i++){
			posizioni[i]=cavalli[i].getPosizione();
		}
		return posizioni;
	}
	
	/**
	 * @author Niccolo
	 * Ritorna le carte azioni presenti su una data corsia
	 * @return l'ArrayList delle azioni presenti su di esso.
	 * */
	public ArrayList getAzioniSuCorsia(int corsia){
		return corsieTruccate[corsia];
	}
	
	public void setPosizioniCavalli(int[] posizioneFissa){
		for (int i=0;i<6;i++){
			cavalli[i].setPosizione(posizioneFissa[i]);
		}
	}
	
	/*--------------------------------------------------------------------------------*/
	/**
	 * Posizione
	 * */
	
	/**
	 * Se il cavallo non è ancora arrivato al traguardo, ne incrementa la posizione, 
	 * altrimenti non fa nulla. Se ci sono due cavalli con la posizione successiva al
	 * traguardo, viene analizzata la situazione di fotofinish.
	 * Successivamente viene verificato che se c'è un solo cavallo ad avere superato il
	 * traguardo,viene registrato il suo arrivo.
	 * @param movimento
	 */
	public void AggiornaPosizione(int[] movimento){
		
		for(int i=0; i<6;i++){
			if(posizione[i] > 13) ;
			else posizione[i] += movimento[i];
		}
		for(int i=0;i<6;i++){
			for(int j=1; j<7;j++){
				if (posizione[i] == posizione[j]) Fotofinish(posizione);
			}
		}
		for(int i=0;i<6;i++){
			if (posizione[i] >= 13 & arrivati[i] == false) {
				ordineArrivo.add("" +i);
				arrivati[i] = true;
			}
		}
	}

	
	/**
	 * Prendendo l'ordine dei cavalli, se i due cavalli oltre il traguardo sono arrivati al
	 * turno precedente, non si fa nulla. Altrimenti si verifica quale ha percorso più spazio
	 * e si registra che lui è arrivato prima dell'altro. 
	 * @param posizione
	 */
	public void Fotofinish (int[] posizione){
		for(int i=0;i<5;i++){
			for(int j=1;j<6;j++){
				if (arrivati[i] == false & arrivati[j] == false) {
					if (posizione[i] >= posizione[j] & posizione[i] > 13
							& posizione[j] > 13) {
						ordineArrivo.add("" + i);
						ordineArrivo.add("" + j);
						arrivati[i] = true;
						arrivati[j] = true;
					} else if (posizione[i] < posizione[j] & posizione[i] > 13
							& posizione[j] > 13) {
						ordineArrivo.add("" + j);
						ordineArrivo.add("" + i);
						arrivati[i] = true;
						arrivati[j] = true;
					}
				}
			}
		}
		
	}
	
	public int[] getPosizione() {
		return posizione;
	}
    
	public String[] getOrdineArrivo(){
		String[] ordineDefinitivo = new String[6];
		for(int i=0;i<ordineArrivo.size();i++){
			 ordineDefinitivo[i] = ordineArrivo.get(i);
		}
		return (ordineDefinitivo);
	}
	

}
