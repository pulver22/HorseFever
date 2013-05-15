package horsefever;
import java.util.ArrayList;


public class BetManager {
	
	private ArrayList<Scommessa> bManager = new ArrayList<Scommessa>();

	public void AggiungiScommessa(int giocatore, int corsia, double quota,char tipo){
		bManager.add(new Scommessa(giocatore,corsia,quota,tipo));
	}
}
