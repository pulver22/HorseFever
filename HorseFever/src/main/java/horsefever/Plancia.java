package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

public class Plancia {
	
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
			if(posizione[i] > 13) return ;
			else posizione[i] += movimento[i];
			}
	}

	public void Fotofinish (int cavallo1,int cavallo2){
		if (posizione[cavallo1] >= posizione[cavallo2])
			ordineArrivo.add(""+cavallo1);
			ordineArrivo.add(""+cavallo2);
		if (posizione[cavallo1] < posizione[cavallo2])
			ordineArrivo.add(""+cavallo2);
			ordineArrivo.add(""+cavallo1);
	}
	
	public int[] getPosizione() {
		return posizione;
	}
    
	

}
