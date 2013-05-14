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

	public void AggiornaPosizione(int incremento,int cavallo){
		posizione[cavallo] += incremento;
		System.out.println(Arrays.toString(posizione));
	}

	public int[] getPosizione() {
		return posizione;
	}
    
	public void setPosizioneDado(int i){
		
		posizione[i]++;
	}

}
