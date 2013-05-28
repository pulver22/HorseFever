package horsefever;
import java.util.ArrayList;

import org.junit.*;

import View.TextView;
import adapter.Adapter;
import static org.junit.Assert.*;


public class BetManagerTest {
	public Partita p = new Partita(2);
	public Adapter a = new Adapter();
	public TextView v = new TextView();
	public String[] ordineArrivo = {"Nero","Blu","Rosso","Verde","Giallo","Bianco"};
	public Lavagna lavagna = new Lavagna(p);
	private String[][] quotazioni = new String[6][2];
	
	
	@Before
	public void setUP(){
		
		p.setAdapter(a);
		a.addView(v);
		quotazioni[0][1]="5";
		quotazioni[1][1]="6";
		quotazioni[2][1]="2";
		quotazioni[3][1]="4";
		quotazioni[4][1]="7";
		quotazioni[5][1]="3";
		lavagna.setQuotazioni(quotazioni);
	}
	
	/*
	@Test
	public void aggiungi(){
		
		Personaggio personaggio = new Personaggio("Paolo",200,"Nero");
		Giocatore g1 = new Giocatore(personaggio,"nero",p);
		Scommessa scom = new Scommessa(g1,3,100,'P');
		BetManager bM = new BetManager(p);
		bM.AggiungiScommessa(scom);
		assertEquals(bM.getbManager().get(0).getScommettitore(),scom.getScommettitore());
		assertEquals(bM.getbManager().get(0).getCorsia(),scom.getCorsia());
		assertEquals(bM.getbManager().get(0).getImporto(),scom.getImporto());
		assertEquals(bM.getbManager().get(0).getTipoScomessa(),scom.getTipoScomessa());
		
	}*/

	@Test
	public void paga(){
		Personaggio personaggio = new Personaggio("Paolo",200,"Nero");
		Giocatore g1 = new Giocatore(personaggio,"Nero",p);
		ArrayList<Giocatore> g = new ArrayList<Giocatore>();
		g.add(g1);
		
		BetManager bM = new BetManager(p);
		Scommessa scom = new Scommessa(g1,0,100,'V');
		bM.AggiungiScommessa(scom);
		
		int aspettativa = 100*5 +200;
		bM.Pagamenti(ordineArrivo, quotazioni, g);
		assertEquals(aspettativa,bM.getbManager().get(0).getScommettitore().getDenari());
	}
	
	
}
