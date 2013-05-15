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
	
	public void TruccaCorsia(Azione carta,int numCorsia){
		corsieTruccate[numCorsia-1].add(carta);
	}

	public void AggiornaPosizione(int[] movimento){
		for(int i=0; i<6;i++){
			if(posizione[i] > 13) ;
			else posizione[i] += movimento[i];
		}
		
		Fotofinish(posizione);
	}
//Attenzione al fatto che un solo giocatore sia arrivato oltre il traguardo!!
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
