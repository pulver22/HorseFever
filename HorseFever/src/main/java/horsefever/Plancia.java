package horsefever;

import java.util.ArrayList;
import java.util.Arrays;

public class Plancia {
	
	int[] posizione = new int[6];
	ArrayList[] corsieTruccate = new ArrayList[6];
	
	public Plancia(){
		for (int i=0;i<6; i++){
			corsieTruccate[i]=new ArrayList<Azione>();		
			posizione[i]=0;
		}
	}
	
	public void TruccaCorsia(Azione carta,int numCorsia){
		corsieTruccate[numCorsia].add(carta);
	}

	public boolean AggiornaPosizione(int incremento,int cavallo){
		posizione[cavallo] += incremento;
		System.out.println(Arrays.toString(posizione));
		if(posizione[cavallo] > 13) return false;
		return true;
	}

	public int[] getPosizione() {
		return posizione;
	}
    
	

}
