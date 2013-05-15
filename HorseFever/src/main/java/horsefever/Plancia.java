package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

public class Plancia {
	
	boolean[] arrivati = new boolean[6];
	ArrayList<String> ordineArrivo = new ArrayList<String>(6);
	int[] posizione = new int[6];
	ArrayList[] corsieTruccate = new ArrayList[6];
	
	public Plancia(){
		for (int i=0;i<6; i++){
			corsieTruccate[i]=new ArrayList<Azione>();		
			posizione[i]=0;
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
