package horsefever;

import static org.junit.Assert.*;

import org.junit.*;

import View.TextView;
import adapter.Adapter;

public class TestPlancia {

	Partita partita;
	Plancia plancia;
	Lavagna lavagna;
	Adapter adapter;
	TextView textview;
	Azione azione1;
	Azione azione2;
	Azione azione3;
	Azione rpos;
	Azione rneg;
	Giocatore giocatore;
	Personaggio personaggio;
	String nome;
	
	@Before
	public void setup(){
		partita = new Partita(2);
		adapter = new Adapter();
		textview = new TextView();
		adapter.addView(textview);
		partita.setAdapter(adapter);
		lavagna=new Lavagna(partita);
		plancia=new Plancia(lavagna,partita);
		azione1=new Azione("Magna Velocitas","Verde","Partenza","=4",'A');
		azione2=new Azione("Globulus Obscurus","Rosso","Partenza","=0",'A');
		azione3=new Azione("Fortuna Malevola","Verde","Partenza","+1",'B');
		rpos=new Azione("Rochelle Recherche","Grigio","Azione","Rimuovi_positive",'Z');
		rneg=new Azione("Fritz Finden","Grigio","Azione","Rimuovi_negative",'X');
		personaggio = new Personaggio("Cranio Mercanti", 3400, "2");
		giocatore = new Giocatore(personaggio, "Nero",partita);
		nome=giocatore.getNome();
		
	}
	
	@Test
	public void casoTestRimozioneOpposti(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.TruccaCorsia(azione2, 1, nome);
		plancia.eliminaEffettiOpposti(plancia.getAzioniSuCorsia(1));
		assertEquals(1,plancia.getAzioniSuCorsia(1).size());
		Azione a=(Azione)plancia.getAzioniSuCorsia(1).get(0);
		assertEquals("Fortuna Malevola",a.getNome());
	}
	
	@Test
	public void casoTestAzioniRimozioneDoppio(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.TruccaCorsia(azione2, 1, nome);
		plancia.TruccaCorsia(rneg, 1, nome);
		plancia.TruccaCorsia(rpos, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1));
		for (int j=0;j<plancia.getAzioniSuCorsia(1).size();j++){
			Azione az=(Azione)plancia.getAzioniSuCorsia(1).get(j);
			System.out.println(az.toString());
		}
		assertEquals(0,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAzioniRimozioneRosse(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.TruccaCorsia(azione2, 1, nome);
		plancia.TruccaCorsia(rneg, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1));
		assertEquals(2,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAzioniRimozioneVerdi(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.TruccaCorsia(azione2, 1, nome);
		plancia.TruccaCorsia(rpos, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1));
		assertEquals(1,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestNOAzioniRimozione(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.TruccaCorsia(azione2, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1));
		assertEquals(3,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAssegnaEffettiCavallo(){
		plancia.TruccaCorsia(azione1, 1, nome);
		plancia.TruccaCorsia(azione3, 1, nome);
		plancia.assegnaEffettiAlCavallo(plancia.getAzioniSuCorsia(1), plancia.getCavalloAt(1));
		assertEquals("=4",plancia.getCavalloAt(1).getEffettoPartenza());
		assertEquals("+1",plancia.getCavalloAt(1).getEffettoPartenza2());
	}
	
	@Test
	public void casoTestFotofinishNoEffettiSingolaParita(){
		int[] pos={10,13,13,13,10,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(4);
		plancia.fotoFinish();
		assertEquals("Verde",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(2).getColore());
	}
	
	@Test
	public void casoTestFotofinishNoEffettiMoltepliciParita(){
		int[] pos={10,13,13,14,14,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(5);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(4);
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(3);
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(2);
		plancia.fotoFinish();
		assertEquals("Verde",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Giallo",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(3).getColore());
	}
	/*
	@Test
	public void casoTestFotofinishEffettoVincente(){
		int[] pos={10,13,13,13,13,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		plancia.getCavalloAt(3).setEffettoFotofinish("=1");
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(4);
		plancia.fotoFinish();
		for (int i =0; i<plancia.getCavalliArrivati().size();i++){
			System.out.println("Cavallo in posizione "+(i+1)+" "+plancia.getCavalliArrivati().get(i).getColore());
		}
		assertEquals("Rosso",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Verde",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Giallo",plancia.getCavalliArrivati().get(3).getColore());
	}
	*/
	
	@Test
	public void casoTestInserisciArrivati(){
		int[] pos={10,13,15,14,10,10};
		plancia.setPosizioniCavalli(pos);
		plancia.inserisciArrivati();
		assertEquals("Verde",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(2).getColore());
	}
}
