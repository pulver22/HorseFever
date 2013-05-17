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
	private ArrayList<Cavallo> fotofinish;
	
	public Plancia(){
		for (int i=0;i<6; i++){
			corsieTruccate[i]=new ArrayList<Azione>();		
			posizione[i]=0;
			cavalli[i]=new Cavallo(colori[i]);
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
			
			eliminaEffettiOpposti(corsieTruccate[i]);
			assegnaEffettiAlCavallo(corsieTruccate[i],cavalli[i]);
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
				if (a1.getTipoEffetto()==a1.getTipoEffetto()){
					if (a1.getValoreEffetto().charAt(0)==a2.getValoreEffetto().charAt(0)){
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
		
		Cavallo c;
		
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
		/*VA ANCORA CONSIDERATO IL FOTOFINISH, E EFFETTI CARTE AZIONE "GRIGIE"*/
		Cavallo c;
		int[] dadiSprint=sprint();
		
		for(int i=0; i<6; i++){
			cavalli[i].aggiornaPosizione(movimenti[i]);
			if (dadiSprint[i]==1) cavalli[i].aggiornaPosizioneSprint();
		}
		
		
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
