package provaFinaleIS;
import java.util.ArrayList;
import java.util.Arrays;

public class Partita {

	private ArrayList<Giocatore> giocatori;
	private int numturni=2;
	private Mazzo mazzoAzione=new Mazzo("MazzoAzione");
	private Mazzo mazzoPersonaggio=new Mazzo("MazzoPersonaggio");;
	private Mazzo mazzoMovimento;
	private Mazzo mazzoProprietario=new Mazzo("MazzoProprietario");
	private Lavagna lavagna;
	private Plancia plancia;
	private Turno turno;
	
	public Partita(int numgiocatori){
		if(numgiocatori==4) numturni=4;
		else if (numgiocatori==5) numturni=5;
		else numturni=6;
		giocatori=new ArrayList<Giocatore>(numgiocatori);
		
	}
	
}
