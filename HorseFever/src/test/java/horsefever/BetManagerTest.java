package horsefever;
import java.util.ArrayList;

import org.junit.*;

import View.TextView;
import adapter.Adapter;
import static org.junit.Assert.*;


public class BetManagerTest {
	public Partita p = new Partita(6);
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
		Personaggio p1 = new Personaggio("Paolo",200,"3");
		Personaggio p2 = new Personaggio("Luca",200,"5");
		Personaggio p3 = new Personaggio("Giovanni",200,"3");
		Personaggio p4 = new Personaggio("Stefano",200,"5");
		Personaggio p5 = new Personaggio("Aldo",200,"3");
		Personaggio p6 = new Personaggio("Enrico",200,"5");
		Giocatore g1 = new Giocatore(p1,p1.getQuotScuderia(),p);
		Giocatore g2 = new Giocatore(p2,p2.getQuotScuderia(),p);
		Giocatore g3 = new Giocatore(p3,p3.getQuotScuderia(),p);
		Giocatore g4 = new Giocatore(p4,p4.getQuotScuderia(),p);
		Giocatore g5 = new Giocatore(p5,p5.getQuotScuderia(),p);
		Giocatore g6 = new Giocatore(p6,p6.getQuotScuderia(),p);
		
		g1.setPV(5);
		g2.setPV(5);
		g3.setPV(5);
		g4.setPV(5);
		g5.setPV(5);
		g6.setPV(5);
		
		ArrayList<Giocatore> g = new ArrayList<Giocatore>();
		g.add(g1);
		g.add(g2);
		g.add(g3);
		g.add(g4);
		g.add(g5);
		g.add(g6);
		
		BetManager bM = new BetManager(p);
		Scommessa scom1 = new Scommessa(g1,0,100,'V');
		Scommessa scom2 = new Scommessa(g2,0,100,'V');
		Scommessa scom3 = new Scommessa(g3,0,100,'P');
		Scommessa scom4 = new Scommessa(g4,0,100,'P');
		Scommessa scom5 = new Scommessa(g5,4,100,'V');
		Scommessa scom6 = new Scommessa(g6,4,100,'V');
		
		bM.AggiungiScommessa(scom1);
		bM.AggiungiScommessa(scom2);
		bM.AggiungiScommessa(scom3);
		bM.AggiungiScommessa(scom4);
		bM.AggiungiScommessa(scom5);
		bM.AggiungiScommessa(scom6);
		
		int aspettativaDenari1 = 100*5 +200;
		int aspettativaPV1 = 5+3;
		int aspettativaDenari2 = 100*5 +200 +600;
		int aspettativaPV2 = 5+3;
		int aspettativaDenari3 = 100*2 +200;
		int aspettativaPV3 = 5+1;
		int aspettativaDenari4 = 100*2 +200 +600;
		int aspettativaPV4 = 5+1;
		int aspettativaDenari5 = 200;
		int aspettativaPV5 = 5;
		int aspettativaDenari6 = 200 +600;
		int aspettativaPV6 = 5;
		
		bM.Pagamenti(ordineArrivo, quotazioni, g);
		
		assertEquals(aspettativaDenari1,bM.getbManager().get(0).getScommettitore().getDenari());
		assertEquals(aspettativaPV1,bM.getbManager().get(0).getScommettitore().getPV());
		//assertEquals(aspettativaDenari2,bM.getbManager().get(1).getScommettitore().getDenari());
		assertEquals(aspettativaPV2,bM.getbManager().get(1).getScommettitore().getPV());
		assertEquals(aspettativaDenari3,bM.getbManager().get(2).getScommettitore().getDenari());
		assertEquals(aspettativaPV3,bM.getbManager().get(2).getScommettitore().getPV());
		//assertEquals(aspettativaDenari4,bM.getbManager().get(3).getScommettitore().getDenari());
		assertEquals(aspettativaPV4,bM.getbManager().get(3).getScommettitore().getPV());
		assertEquals(aspettativaDenari5,bM.getbManager().get(4).getScommettitore().getDenari());
		assertEquals(aspettativaPV5,bM.getbManager().get(4).getScommettitore().getPV());
		//assertEquals(aspettativaDenari6,bM.getbManager().get(5).getScommettitore().getDenari());
		assertEquals(aspettativaPV6,bM.getbManager().get(5).getScommettitore().getPV());
	}
	
	
}
