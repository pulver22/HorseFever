package provaFinaleIS;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Azione> carteprova=new ArrayList<Azione>(2);
		
		Giocatore prova=new Giocatore();
		Mazzo MazzoAzione=new Mazzo("MazzoAzione");
		MazzoAzione.mischia();
		carteprova.add((Azione) MazzoAzione.pesca());
		carteprova.add((Azione) MazzoAzione.pesca());
		prova.setCarteAzione(carteprova);
		
		prova.setPV(100);
        prova.setDenari(5000000);
        prova.scommetti(1);
        prova.trucca();
        prova.trucca();
	}

}
