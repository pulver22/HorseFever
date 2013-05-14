package horsefever;

import java.util.ArrayList;

public class Plancia {
	
	char[] posizione = new char[6];
	ArrayList[] corsieTruccate = new ArrayList[6];
	
	public Plancia(){
		for (int i=0;i<=6; i++){
			corsieTruccate[i]=new ArrayList<Azione>();
		}
	}
	
	public void TruccaCorsia(Azione carta,int numCorsia){
		corsieTruccate[numCorsia].add(carta);
	}

	public void AggiornaPosizione(int incremento,int cavallo){
		posizione[cavallo] += incremento;
	}

}
