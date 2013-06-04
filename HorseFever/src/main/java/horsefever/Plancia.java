package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

import eventi.HorseFeverEvent;
import eventi.eventoArrivi;
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
			
		partenza=true;
	}
	
	/**
	 * Aggiunge la carta azione su quella determinata corsia
	 * @param carta
	 * @param numCorsia
	 * @author Niccolo
	 */
	public void TruccaCorsia(Azione carta,int numCorsia, String nomeGioc){
		corsieTruccate[numCorsia].add(carta);
		partita.notifyObserver(new eventoTrucca(nomeGioc,numCorsia));
	}

	/**
	 * Applica gli effetti di tutte le carte Azioni su tutti i Cavalli
	 * @author Niccolo
	 * */
	public void applicaAzioni(){
		for (int i=0; i<6;i++){
			controllaAzioniDiRimozione(corsieTruccate[i],i);
			eliminaEffettiOpposti(corsieTruccate[i]);
			assegnaEffettiAlCavallo(corsieTruccate[i],cavalli[i],i);
		}
	}
	
	/**
	 * Controlla e applica effetti delle carte Grigie che rimuovono tutte le carte Verdi o Rosse
	 * @param l'ArrayList dell carte Azione su cui fare il controllo
	 * @author Niccolo
	 * */
	public void controllaAzioniDiRimozione(ArrayList azioni,int corsia){
		Azione a;
		boolean positive=false;
		boolean negative=false;
		if(azioni.size()>0)
		for (int i=0; i<azioni.size();i++){
			a=(Azione) azioni.get(i);
			if (a.getColore().equals("Grigio") && a.getTipoEffetto().equals("Azione")){
				if (a.getValoreEffetto().equals("Rimuovi_positive")) {
					positive=true;
					azioni.remove(i);
					i--;
					partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
				}
				if (a.getValoreEffetto().equals("Rimuovi_negative")) {
					negative=true;
					azioni.remove(i);
					i--;
					partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));	
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
			} else if (negative && positive){
				while(azioni.size()>0){
					azioni.remove(0);
				}
			}
		}
	}

	/**
	 * Assegna agli attributi di Cavallo i valori delle Carte Azioni presenti su di esso
	 * @param ArrayList delle Carte Azione assegnate al cavallo
	 * @param Il cavallo su cui assegnare gli effetti
	 * @author Niccolo
	 * */
	public void assegnaEffettiAlCavallo(ArrayList azioni, Cavallo cavallo,int corsia){
		for (int i=0; i<azioni.size();i++){
			Azione a= (Azione)azioni.get(i);
			if (a.getTipoEffetto().equals("Partenza")) {
				if (a.getValoreEffetto().charAt(0)=='='){
					cavallo.setEffettoPartenza(a.getValoreEffetto());
				} else {
					cavallo.setEffettoPartenza2(a.getValoreEffetto());
				}
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
			}
			if (a.getTipoEffetto().equals("Sprint")) {
				if (a.getLettera()=='C'){
					cavallo.setEffettoSprint(a.getValoreEffetto());
				} else {
					cavallo.setEffettoSprint2(a.getValoreEffetto());
				}
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
			}
			if (a.getTipoEffetto().equals("Fotofinish")) {
				cavallo.setEffettoFotofinish(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
			}
			if (a.getTipoEffetto().equals("Traguardo")) {
				cavallo.setEffettoTraguardo(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
			}
			if (a.getTipoEffetto().equals("Ultimo") || a.getTipoEffetto().equals("Primo")) {
				cavallo.setEffettoUltimoPrimo(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
			}
			if (a.getTipoEffetto().equals("Quotazione")) {
				cavallo.setEffettoQuotazione(a.getValoreEffetto());
				partita.notifyObserver(new eventoEffettoAvvenuto(a.toString(),corsia+1));
				lavagna.setQuotazioneAlCavallo(cavallo.getColore(), a.getValoreEffetto());
				
			}
		}
	}
	
	/**
	 * Verifica se in un ArrayList di carte Azione, ce ne sono alcune che si annullano
	 * @param l'arrayList delle carte Azione da controllare
	 * @author Niccolo
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
	 * A partire dall'array di valori della carta Movimento, rende l'array dei movimenti teorici dei cavalli 
	 * in base alle quotazioni
	 * @param array dei valori della carta Movimento
	 * @return array dei movimenti teorici dei cavalli
	 * @author Niccolo
	 * */
	public int[] calcolaIncrementiDaMov(int[] valoriMov){
		int[] incrementi = new int[6];
		for (int i=0; i<6;i++){
			incrementi[i]=valoriMov[lavagna.getRigaMovimento(i)];
		}
		return incrementi;
	}
	
	/**
	 * Esegue i movimenti dei cavalli alla partenza
	 * @param l'array dei movimenti teorici dei cavalli
	 * @author Niccolo
	 * */
	public void partenza(int[] movimenti){
		for (int i=0; i<6;i++){
			cavalli[i].aggiornaPosizionePartenza(movimenti[i]);
		}
	}
	
	/**
	 * Esegue i movimenti dei cavalli
	 * @author Niccolo
	 * */
	public void muovi(){
		Cavallo c;
		int[] dadiSprint=sprint();
		
		Movimento m=(Movimento)partita.getMazzoMovimento().pesca();
		int[] valoriMov=m.getArrayMovimenti();
		String immagineMov=m.getNomefile();
		
		int[] movTeorici=calcolaIncrementiDaMov(valoriMov);
		
		if (partenza){
			partenza(movTeorici);
			partenza=false;
			for (int i=0;i<6;i++)
				if (dadiSprint[i]==1) cavalli[i].aggiornaPosizioneSprint();
			partita.notifyObserver(new eventoCorsa(getPosizioniCavalli(), valoriMov, dadiSprint,immagineMov));
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
			partita.notifyObserver(new eventoCorsa(getPosizioniCavalli(), valoriMov, dadiSprint,immagineMov));
			gestioneArrivi();
			
		}
	}
	
	/**
	 * Gestisce arrivi e fotofinish del round di corsa attuale
	 * @author Niccolo
	 * */
	public void gestioneArrivi(){
		inserisciArrivati();
		if (almenoDueCavalliPari())fotoFinish2();
	}
	
	/**
	 * Controlla se un cavallo è arrivati prima di tutti gli altri e in tal caso lo inserisce in arrivi
	 * rimuovendo il suo riferimento dall'array dei cavalli
	 * @author Niccolo
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
						partita.notifyObserver(new eventoArrivi(cavalli[j].getColore(),cavalliArrivati.size()));
						arrivati[j]=true;
						break;
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
	 * Esegue il controllo di fotofinish sui vari cavalli nel round di corsa corrente
	 * @author Niccolo
	 * */
	public void fotoFinish2(){
		
		int flag[]=getCavalliPariMax();//Trova indici cavalli pari e in posizione massima
		
		ArrayList<Cavallo> ordineCavalli=new ArrayList<Cavallo>();
		boolean esisteVincente=false,esistePerdente=false;
		for (int i=0; i<6;i++){ //Per cavalli di cui si è memorizzato l'indice, si aggiungono a Fotofinish e si segnano arrivati
			if (flag[i]==1){
				fotofinish.add(cavalli[i]);
				arrivati[i]=true;
			}
		}
		
		for (int j=0;j<fotofinish.size();j++){//Ricerca Cavallo con effetto Vincente
			if (fotofinish.get(j).getEffettoFotofinish()!=null){
				if (fotofinish.get(j).getEffettoFotofinish().equals("=1")){
					ordineCavalli.add(fotofinish.get(j));
					fotofinish.remove(j);
					j--;
					esisteVincente=true;
				}
			}
		}
		for (int k=0;k<fotofinish.size();k++){//Ricerca Cavallo con effetto perdente
			if (fotofinish.get(k).getEffettoFotofinish()!=null){
				if (fotofinish.get(k).getEffettoFotofinish().equals("=0")){
					ordineCavalli.add(fotofinish.get(k));
					fotofinish.remove(k);
					k--;
					esistePerdente=true;
				}
			}
		}
		
		if (fotofinish.size()>1) this.sortPerQuotazioneDecrescente(); //Ordinamento dei restanti
		
		while(fotofinish.size()>0){//Inserimento dei restanti nelle posizioni adeguate
			if (esisteVincente){
				ordineCavalli.add(1,fotofinish.get(0));
				fotofinish.remove(0);
			} else if (esistePerdente && !esisteVincente){
				ordineCavalli.add(0,fotofinish.get(0));
				fotofinish.remove(0);
			} else if (!esistePerdente && !esisteVincente){
				ordineCavalli.add(0, fotofinish.get(0));
				fotofinish.remove(0);
			}
		}
		
		while(ordineCavalli.size()>0){//Inserimento finale in cavalliArrivati
			cavalliArrivati.add(ordineCavalli.get(0));
			partita.notifyObserver(new eventoArrivi(ordineCavalli.get(0).getColore(),cavalliArrivati.size()));
			ordineCavalli.remove(0);
		}
		
		esisteVincente=false;
		esistePerdente=false;
		if (almenoDueCavalliPari()) fotoFinish2();
	}
	
	/**
	 * Restituisce un array con 1 agli indici dei cavalli pari nella posizione massima non ancora arrivati, 0 altrove 
	 * @author Niccolo
	 * */
	public int[] getCavalliPariMax(){
		int[] flag=new int[6];
		int max=getMaxPosPari();
		for (int i=0;i<6;i++){
			if (cavalli[i].getPosizione()==max && !arrivati[i]) flag[i]=1;
		}
		return flag;
	}
	
	/**
	 * Controlla, tra i cavalli arrivati ora al traguardo, la posizione di quali sono arrivati pari a una posizione maggiore, 0 altrimenti
	 * @return la posizione dei cavalli pari maggiormente oltre il traguardo 
	 * @author Niccolo
	 * */
	public int getMaxPosPari(){
		int max=0;
		for (int i=0; i<6;i++){
			if (!arrivati[i]){
				if (cavalli[i].oltreTraguardo()){
					for (int j=i+1;j<6;j++){
						if (!arrivati[j]){
							if (cavalli[j].getPosizione()==cavalli[i].getPosizione()){
								if (cavalli[j].getPosizione()>=max) max=cavalli[j].getPosizione();
							}
						}
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * Sostanzialmente un bubblesort dell'ArrayList Fotofinish di Cavalli in base alla loro quotazione (decrescente
	 * nel senso numerico, da "7" a "2", non nel senso dalla migliore (2) alla peggiore (7)). 
	 * @author Niccolo
	 * */
	public void sortPerQuotazioneDecrescente(){
		
		for(int i = 0; i < fotofinish.size(); i++) {
            boolean flag = false;
            for(int j = 0; j < fotofinish.size()-1; j++) {

                //Se l' elemento j e maggiore del successivo allora
                //scambiamo i valori
                if(fotofinish.get(j).getQuotazione()<fotofinish.get(j+1).getQuotazione()) {
                    Cavallo k = fotofinish.get(j);
                    fotofinish.set(j, fotofinish.get(j+1));
                    fotofinish.set(j+1, k);
                    flag=true; //Lo setto a true per indicare che é avvenuto uno scambio
                }
                

            }

            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
                             //non ci sono stati scambi, quindi il metodo può terminare
                             //poiché l' array risulta ordinato
        }
	}
	
	/**
	 * Resetta la Plancia alla situazione precedente la corsa.
	 * Rimuove tutti gli elementi da fotofinish cavalliArrivati e corsieTruccate, e resetta i cavalli.
	 * @author Niccolo
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
	 * Metodo di supporto, ritorna i cavalli in prima posizione
	 * @return un array di int con 1 se il cavallo corrispondente è primo (eventualmente parimerito con altri)
	 * 0 se invece non lo è. 
	 * @author Niccolo
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
	 * Metodo di supporto, ritorna i cavalli in ultima posizione
	 * @return un array di int con 1 se il cavallo corrispondente è ultimo (eventualmente parimerito con altri)
	 * 0 se invece non lo è. 
	 * @author Niccolo
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
	 * Metodo di supporto, ritorna la posizione massima tra quelle attuali dei cavalli
	 * @return la posizione massima tra le attuali dei cavalli
	 * @author Niccolo
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
	 * Metodo di supporto, ritorna la posizione minima tra quelle attuali dei cavalli
	 * @return la posizione minima tra quelle attuali dei Cavalli
	 * @author Niccolo
	 * */
	public int getMin(){
		int min=cavalli[0].getPosizione();
		for (int i=0;i<6;i++){
			if (min>cavalli[i].getPosizione()) min=cavalli[i].getPosizione();
			else min=min;
		}
		return min;
	}
	
	/**
	 * Esegue lo pseudo lancio dei dadi sprint
	 * @return un array di 6 elementi. Ciascuno a 0 se non è uscito con nessun lancio, 1 se è uscito 
	 * @author Niccolo
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
	 * Ritorna l'Array di String dei colori dei cavalli nell'ordine d'arrivo
	 * @return I colori dei cavalli in ordine d'arrivo
	 * @author Niccolo
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
	 * Ritorna true se tutti i cavalli sono arrivati e false in caso contrario
	 * @return true tutti i cavalli sono arrivati, false altrimenti
	 * @author Niccolo
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
	
	public Cavallo getCavalloAt(int i){
		return cavalli[i];
	}
	
	public ArrayList<Cavallo> getCavalliArrivati(){
		return cavalliArrivati;
	}
	
	public ArrayList<Cavallo> getFotofinish(){
		return fotofinish;
	}
	
}
