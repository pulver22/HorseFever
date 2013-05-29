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
}
