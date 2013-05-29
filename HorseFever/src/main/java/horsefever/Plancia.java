package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

import eventi.HorseFeverEvent;
import eventi.eventoCorsa;
import eventi.eventoEffettoAvvenuto;
import eventi.eventoQuotazioni;
import eventi.eventoTrucca;

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
	private Partita partita;
	private HorseFeverEvent e;
	
	public Plancia(Lavagna lavagna, Partita p){
		this.partita=p;
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
	public void TruccaCorsia(Azione carta,int numCorsia, String nomeGioc){
		corsieTruccate[numCorsia].add(carta);
		partita.notifyObserver(new eventoTrucca(nomeGioc,numCorsia));
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
		boolean positive=false;
		boolean negative=false;
		if(azioni.size()>0)
		for (int i=0; i<azioni.size();i++){
			a=(Azione) azioni.get(i);
			if (a.getColore().equals("Grigio") && a.getTipoEffetto().equals("Azione")){
				if (a.getValoreEffetto().charAt(8)=='p') {
					positive=true;
					azioni.remove(i);
					i--;
					partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
				}
				if (a.getValoreEffetto().charAt(8)=='n') {
					negative=true;
					azioni.remove(i);
					i--;
					partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));	
				}
			}
			
		}
		if (positive || negative){
			if (negative && !positive){
				for (int j=0;j<azioni.size();j++){
					a=(Azione) azioni.get(j);
					if (a.getColore().equals("Rosso")){
						azioni.remove(j);
						j--;
					}
				}
			
			} else if (!negative && positive){
				for (int j=0; j<azioni.size();j++){
					a=(Azione) azioni.get(j);
					if (a.getColore().equals("Verde")){
						azioni.remove(j);
						j--;
					}
				}
			} else {
				for (int j=0; j<azioni.size();j++){
					azioni.remove(0);
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
			if (a.getTipoEffetto().equals("Partenza")) {
				if (a.getValoreEffetto().charAt(0)=='='){
					cavallo.setEffettoPartenza(a.getValoreEffetto());
				} else {
					cavallo.setEffettoPartenza2(a.getValoreEffetto());
				}
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
			}
			if (a.getTipoEffetto().equals("Sprint")) {
				if (a.getLettera()=='C'){
					cavallo.setEffettoSprint(a.getValoreEffetto());
				} else {
					cavallo.setEffettoSprint2(a.getValoreEffetto());
				}
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
			}
			if (a.getTipoEffetto().equals("Fotofinish")) {
				cavallo.setEffettoFotofinish(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
			}
			if (a.getTipoEffetto().equals("Traguardo")) {
				cavallo.setEffettoTraguardo(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
			}
			if (a.getTipoEffetto().equals("Ultimo") || a.getTipoEffetto().equals("Primo")) {
				cavallo.setEffettoUltimoPrimo(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
			}
			if (a.getTipoEffetto().equals("Quotazione")) {
				cavallo.setEffettoQuotazione(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString()));
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
		if (azioni.size()>1){
			//int[] flag=new int[azioni.size()];
			for (int i=0; i<azioni.size()-1; i++){
				for (int j=1; j<azioni.size(); j++){
					Azione a1= (Azione)azioni.get(i);
					Azione a2= (Azione)azioni.get(j);
					if (a1.getLettera()==a2.getLettera()){
						azioni.remove(j);
						azioni.remove(i);
					}
				}
			}
		}
	}
	
	/**
	 * @author Niccolo
	 * A partire dall'array di valori della carta Movimento, rende l'array dei movimenti teorici dei cavalli 
	 * in base alle quotazioni
	 * @param array dei valori della carta Movimento
	 * @return array dei movimenti teorici dei cavalli
	 * */
	public int[] calcolaIncrementiDaMov(int[] valoriMov){
		int[] incrementi = new int[6];
		for (int i=0; i<6;i++){
			incrementi[i]=valoriMov[lavagna.getRigaMovimento(i)];
		}
		return incrementi;
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
	public void muovi(){
		Cavallo c;
		int[] dadiSprint=sprint();
		
		Movimento m=(Movimento)partita.getMazzoMovimento().pesca();
		int[] valoriMov=m.getArrayMovimenti();
		
		int[] movTeorici=calcolaIncrementiDaMov(valoriMov);
		
		if (partenza){
			partenza(movTeorici);
			partenza=false;
		}else{
			for(int i=0; i<6; i++){
				if (cavalli[i]!=null){
					if (cavalli[i].getEffettoUltimoPrimo()!=null){
						int[] primiPari=getCavalliPrimiPari();
						int[] ultimiPari=getCavalliUltimiPari();
						if (primiPari[i]==1) cavalli[i].aggiornaPosizionePrimoUltimo(movTeorici[i], true, false);
						else if (ultimiPari[i]==1) cavalli[i].aggiornaPosizionePrimoUltimo(movTeorici[i], false, true);
						else cavalli[i].aggiornaPosizionePrimoUltimo(movTeorici[i], false, false);
					} else {
					cavalli[i].aggiornaPosizione(movTeorici[i]);
					}
					if (dadiSprint[i]==1) cavalli[i].aggiornaPosizioneSprint();
				}
			
			}
			partita.notifyObserver(new eventoCorsa(getPosizioniCavalli(), valoriMov, dadiSprint));
			gestioneArrivi();
			
		}
	}
	
	/**
	 * @author Niccolo
	 * Gestisce arrivi e fotofinish del round di corsa attuale
	 * */
	public void gestioneArrivi(){
		inserisciArrivati();
		if (almenoDueCavalliPari())fotoFinish();
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
		if (esisteAltroArrivato()) inserisciArrivati();
	}
	
	public boolean esisteAltroArrivato(){
		int flagArrivo=0;
		for (int i=0; i<6;i++){
			if (!arrivati[i]){
				if (cavalli[i].oltreTraguardo()){
					for (int k=i+1;k<6;k++){
						if (!arrivati[k]){
							if (cavalli[i].getPosizione()>cavalli[k].getPosizione()) flagArrivo+=1;
							else flagArrivo-=1;
						} else flagArrivo+=1;//Per ogni cavallo fuori gara o con posizione inferiore
					}						 //aumenta il flag, altrimenti lo diminuisce
					if (flagArrivo==(5-i)) { //se alla fine il flag è pari ai cavalli dopo quello in analisi
						return true;
					}
					flagArrivo=0;
				}
			}
		}
		return false;
	}
	
	public boolean almenoDueCavalliPari(){
		for (int i=0; i<6; i++){
			if (!arrivati[i]){
				if (cavalli[i].oltreTraguardo()){
					for (int j=i+1;j<6;j++){
						if (!arrivati[j]){
							if (cavalli[j].oltreTraguardo()){
								if (cavalli[i].getPosizione()==cavalli[j].getPosizione())return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * @author Niccolo
	 * Esegue il controllo di fotofinish sui vari cavalli nel round di corsa corrente
	 * */
	public void fotoFinish(){
		int[] flagFotofinish=new int[6];
		char[] effettiCarte;
		boolean esisteVincente=false;
		boolean esistePerdente=false;
		ArrayList<Cavallo> ordineCavalli=new ArrayList<Cavallo>();
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
							if (fotofinish.get(n).getEffettoFotofinish()!=null){
									if(fotofinish.get(n).getEffettoFotofinish().charAt(1)=='1'){
										ordineCavalli.add(fotofinish.get(n)); //Mette in primo posto 
										fotofinish.remove(n);//il cavallo con l'effetto fotofinish vincente
										esisteVincente=true;
								}
							}
						for (int m=0; m<fotofinish.size();m++){//Ricerca di cavallo con effetto perdente
							if (fotofinish.get(m).getEffettoFotofinish()!=null){
									if(fotofinish.get(m).getEffettoFotofinish().charAt(1)=='0'){
										ordineCavalli.add(fotofinish.get(m));
										fotofinish.remove(m);
										esistePerdente=true;
									}
							}
						}
						
						if (fotofinish.size()>1)sortPerQuotazioneDecrescente(fotofinish);//Sort dei cavalli restanti in fotofinish
						
						for (int l=0; l<fotofinish.size();l++){//I restanti li inserisce coma capita tra il primo e l'ultimo
															//(In ordine di quotazione, inserisce man mano
															//cavalli con quotazione maggiore (1:2>1:3) davanti agli altri
															
							if (esisteVincente) {
								ordineCavalli.add(1, fotofinish.get(l));
								fotofinish.remove(l);
							}else if (esistePerdente){
								ordineCavalli.add(0, fotofinish.get(l));
								fotofinish.remove(l);
							} else {
								ordineCavalli.add(fotofinish.get(l));
								fotofinish.remove(l);
							}
						}
						for (int a=0; a<ordineCavalli.size(); a++){ //Inserimento ordinato nei cavalliArrivati
							cavalliArrivati.add(ordineCavalli.get(a));//di quelli risultanti il fotofinish
							ordineCavalli.remove(a);
						}
						}
					}
					almenoUnCavalloPari=false;
				}
			}
		}
	}
	
	/**
	 * @author Niccolo
	 * Sostanzialmente un bubblesort dell'ArrayList di Cavalli in base alla loro quotazione (decrescente
	 * nel senso da "7" a "2"). 
	 * @param l'ArrayList di Cavalli da ordinare
	 * */
	public void sortPerQuotazioneDecrescente(ArrayList<Cavallo> cavalli){
		for (int j=0; j<fotofinish.size()-1;j++){
			for (int i=1;i<fotofinish.size();i++){
				if (cavalli.get(i).getQuotazione()<cavalli.get(j).getQuotazione()){
					Cavallo ci=cavalli.get(i);
					Cavallo cj=cavalli.get(j);
					cavalli.set(i, cj);
					cavalli.set(j, ci);
				}
			}
		}
	}
	
	/**
	 * Resetta la Plancia alla situazione precedente la corsa.
	 * Rimuove tutti gli elementi da fotofinish cavalliArrivati e corsieTruccate, e resetta i cavalli.
	 * */
	public void reset(){
		for (int i=0; i<6;i++){
			cavalli[i].reset();
			if (corsieTruccate[i].size()!=0){
				for (int j=0; j<corsieTruccate[i].size(); j++){
					corsieTruccate[i].remove(0);
				}
			}
		}
		if (fotofinish.size()!=0){
			for (int k=0; k<fotofinish.size();k++){
				fotofinish.remove(0);
			}
		}
		if (cavalliArrivati.size()!=0){
			for (int l=0; l<cavalliArrivati.size();l++){
				cavalliArrivati.remove(0);
			}
		}
		partenza=true;
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
			if (min>cavalli[i].getPosizione()) min=cavalli[i].getPosizione();
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
		for (int i=0; i<cavalliArrivati.size();i++){
			if(cavalliArrivati.get(i)!=null)
			colori[i]=cavalliArrivati.get(i).getColore();
		}
		return colori;
	}
	
	/**
	 * @author Niccolo
	 * Ritorna true se tutti i cavalli sono arrivati e false in caso contrario
	 * */
	public boolean tuttiArrivati(){
		for (int i=0; i<6;i++){
			if (arrivati[i]==false) return false;
		}
		return true;
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
	

	

}
