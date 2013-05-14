package horsefever;
import java.util.ArrayList;
import java.util.Arrays;

public class Partita {

	private ArrayList<Giocatore> giocatori;
	private int numturni=2;
	private int numgiocatori;
	private Mazzo mazzoAzione=new Mazzo("MazzoAzione");
	private Mazzo mazzoPersonaggio=new Mazzo("MazzoPersonaggio");;
	private Mazzo mazzoMovimento;
	private Mazzo mazzoProprietario=new Mazzo("MazzoProprietario");
	private Lavagna lavagna;
	private Plancia plancia;
	private Turno turno;
	
	public Partita(int numgiocatori){
		this.numgiocatori=numgiocatori;
		if(numgiocatori==4) numturni=4;
		else if (numgiocatori==5) numturni=5;
		else numturni=6;
		giocatori=new ArrayList<Giocatore>(numgiocatori);
		
	}
	
	public void preparazione(){
		for (int i=0; i<numgiocatori; i++){
			Carta p=mazzoPersonaggio.pesca();
			giocatori.add(new Giocatore(p));
		}
		
	}
	
}
