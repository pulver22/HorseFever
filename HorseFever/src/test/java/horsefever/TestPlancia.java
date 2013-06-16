package horsefever;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import View.TextView;
import adapter.Adapter;
import adapter.AdapterLocale;

public class TestPlancia {

	Partita partita;
	Plancia plancia;
	Lavagna lavagna;
	Adapter adapter;
	TextView textview;
	Azione azione1;
	Azione azione2;
	Azione azione3;
	Azione sprintC;
	Azione rpos;
	Azione rneg;
	Giocatore giocatore;
	Personaggio personaggio;
	String nome;
	
	@Before
	public void setup(){
		
		adapter = new AdapterLocale();
		textview = new TextView();
		adapter.addView(textview);
		partita = new Partita(2,adapter);
		lavagna=new Lavagna(partita);
		plancia=new Plancia(lavagna,partita);
		azione1=new Azione("Magna Velocitas","Verde","Partenza","=4",'A');
		azione2=new Azione("Globulus Obscurus","Rosso","Partenza","=0",'A');
		azione3=new Azione("Fortuna Malevola","Verde","Partenza","+1",'B');
		sprintC=new Azione("Flagellum Fulguris","Verde","Sprint","+1",'C');
		rpos=new Azione("Rochelle Recherche","Grigio","Azione","Rimuovi_positive",'Z');
		rneg=new Azione("Fritz Finden","Grigio","Azione","Rimuovi_negative",'X');
		personaggio = new Personaggio("Cranio Mercanti", 3400, "2");
		giocatore = new Giocatore(personaggio, "Nero",partita);
		nome=giocatore.getNome();
		
	}
	
	@Test
	public void casoTestRimozioneOpposti(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.eliminaEffettiOpposti(plancia.getAzioniSuCorsia(1));
		assertEquals(1,plancia.getAzioniSuCorsia(1).size());
		Azione a=(Azione)plancia.getAzioniSuCorsia(1).get(0);
		assertEquals("Fortuna Malevola",a.getNome());
	}
	
	@Test
	public void casoTestAzioniRimozioneDoppio(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.truccaCorsia(rneg, 1, nome);
		plancia.truccaCorsia(rpos, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1),0);
		for (int j=0;j<plancia.getAzioniSuCorsia(1).size();j++){
			Azione az=(Azione)plancia.getAzioniSuCorsia(1).get(j);
			System.out.println(az.toString());
		}
		assertEquals(0,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAzioniRimozioneRosse(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.truccaCorsia(rneg, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1),0);
		assertEquals(2,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAzioniRimozioneVerdi(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.truccaCorsia(rpos, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1),0);
		assertEquals(1,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestNOAzioniRimozione(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.controllaAzioniDiRimozione(plancia.getAzioniSuCorsia(1),0);
		assertEquals(3,plancia.getAzioniSuCorsia(1).size());
	}
	
	@Test
	public void casoTestAssegnaEffettiCavallo(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(sprintC, 1, nome);
		plancia.assegnaEffettiAlCavallo(plancia.getAzioniSuCorsia(1), plancia.getCavalloAt(1),0);
		assertEquals("=4",plancia.getCavalloAt(1).getEffettoPartenza());
		assertEquals("+1",plancia.getCavalloAt(1).getEffettoPartenza2());
		assertEquals("+1",plancia.getCavalloAt(1).getEffettoSprint());
	}
	
	@Test
	public void casoTestApplicaAzioni(){
		plancia.truccaCorsia(azione1, 1, nome);
		plancia.truccaCorsia(azione3, 1, nome);
		plancia.truccaCorsia(azione2, 1, nome);
		plancia.truccaCorsia(rneg, 1, nome);
		plancia.truccaCorsia(rpos, 1, nome);
		plancia.applicaAzioni();
		assertEquals(0,plancia.getAzioniSuCorsia(1).size());
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
		plancia.fotoFinish2();
		assertEquals("Verde",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(2).getColore());
	}
	
	@Test
	public void casoTestFotofinishNoEffettiMoltepliciParita(){
		int[] pos={10,13,13,14,14,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(4);
		plancia.fotoFinish2();
		assertEquals("Giallo",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Verde",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(3).getColore());
	}
	
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
		plancia.fotoFinish2();
		assertEquals("Rosso",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Verde",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Giallo",plancia.getCavalliArrivati().get(3).getColore());
	}
	
	@Test
	public void casoTestFotofinishEffettoPerdente(){
		int[] pos={10,13,13,13,13,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		plancia.getCavalloAt(2).setEffettoFotofinish("=0");
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(4);
		plancia.fotoFinish2();
		assertEquals("Blu",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Giallo",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Verde",plancia.getCavalliArrivati().get(3).getColore());
	}
	
	@Test
	public void casoTestFotofinishEntrambiEffetti(){
		int[] pos={10,13,13,13,13,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		plancia.getCavalloAt(2).setEffettoFotofinish("=0");
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		plancia.getCavalloAt(3).setEffettoFotofinish("=1");
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(4);
		plancia.fotoFinish2();
		assertEquals("Rosso",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Giallo",plancia.getCavalliArrivati().get(2).getColore());
		assertEquals("Verde",plancia.getCavalliArrivati().get(3).getColore());
	}
	
	@Test
	public void casoTestInserisciArrivati(){
		int[] pos={10,13,15,14,10,10};
		plancia.setPosizioniCavalli(pos);
		plancia.inserisciArrivati();
		assertEquals("Verde",plancia.getCavalliArrivati().get(0).getColore());
		assertEquals("Rosso",plancia.getCavalliArrivati().get(1).getColore());
		assertEquals("Blu",plancia.getCavalliArrivati().get(2).getColore());
	}
	
	@Test
	public void casoTestgetMaxPari(){
		int[] pos={10,13,13,14,14,10};
		plancia.setPosizioniCavalli(pos);
		int max=plancia.getMaxPosPari();
		assertEquals(14,max);
	}
	
	@Test
	public void casoTestGetCavalliMaxPari(){
		int[] pos={10,13,13,14,14,10};
		plancia.setPosizioniCavalli(pos);

		int[] flag=plancia.getCavalliPariMax();
		assertEquals(flag[0],0);
		assertEquals(flag[1],0);
		assertEquals(flag[2],0);
		assertEquals(flag[3],1);
		assertEquals(flag[4],1);
		assertEquals(flag[5],0);
	}
	
	@Test
	public void casoTestSortQuotazioneDecrescente(){
		int[] pos={10,13,13,13,13,10};
		plancia.setPosizioniCavalli(pos);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(2);
		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(4);
		ArrayList<Cavallo> cavalli=new ArrayList<Cavallo>();
		cavalli.add(plancia.getCavalloAt(1));
		cavalli.add(plancia.getCavalloAt(2));
		cavalli.add(plancia.getCavalloAt(3));
		cavalli.add(plancia.getCavalloAt(4));
		plancia.sortPerQuotazioneDecrescente(cavalli);
		assertEquals(5,cavalli.get(0).getQuotazione());
		assertEquals(4,cavalli.get(1).getQuotazione());
		assertEquals(3,cavalli.get(2).getQuotazione());
		assertEquals(2,cavalli.get(3).getQuotazione());
	}
	
	@Test
	public void casoTestGetPrimiUltimi(){
		int[] pos={10,13,12,8,13,8};
		plancia.setPosizioniCavalli(pos);
		int[] primi=plancia.getCavalliPrimiPari();
		int[] ultimi=plancia.getCavalliUltimiPari();
		assertEquals(0,primi[0]);
		assertEquals(1,primi[1]);
		assertEquals(0,primi[2]);
		assertEquals(0,primi[3]);
		assertEquals(1,primi[4]);
		assertEquals(0,primi[5]);
		
		assertEquals(0,ultimi[0]);
		assertEquals(0,ultimi[1]);
		assertEquals(0,ultimi[2]);
		assertEquals(1,ultimi[3]);
		assertEquals(0,ultimi[4]);
		assertEquals(1,ultimi[5]);
	}
	
	@Test
	public void casoTestMuovi(){
		int[] pos={9,9,9,9,9,9};
		plancia.setPosizioniCavalli(pos);
		//Nero
		plancia.getCavalloAt(0).setQuotazione(2);
		//Blu
		plancia.getCavalloAt(1).setQuotazione(3);
		//Verde
		plancia.getCavalloAt(2).setQuotazione(4);

		//Rosso
		plancia.getCavalloAt(3).setQuotazione(5);
		//Giallo
		plancia.getCavalloAt(4).setQuotazione(6);
		//Bianco
		plancia.getCavalloAt(5).setQuotazione(7);

		lavagna.setDebug(true);
		plancia.setDebug(true);
		while(!plancia.tuttiArrivati()){
			plancia.muovi();
		}
		String[] arrivi=plancia.getColoriArrivi();
		assertEquals("Nero",arrivi[0]);
		assertEquals("Blu",arrivi[1]);
		assertEquals("Verde",arrivi[2]);
		assertEquals("Bianco",arrivi[3]);
		assertEquals("Giallo",arrivi[4]);
		assertEquals("Rosso",arrivi[5]);
	}
	
	@Test
	public void casoTestReset(){
		plancia.reset();
		int[] pos=plancia.getPosizioniCavalli();
		for (int i=0;i<6;i++){
			assertEquals(0,pos[i]);
			assertEquals(0, plancia.getAzioniSuCorsia(i).size());
		}
		assertEquals(0, plancia.getCavalliArrivati().size());
		
	}
}
