package horsefever;

import static org.junit.Assert.*;

import org.junit.*;

import View.TextView;
import adapter.Adapter;
import adapter.AdapterLocale;

public class TestLavagna {
	Partita partita;
	Lavagna lavagna;
	Adapter adapter;
	TextView textview;
	String[] colori={"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	String[][] tabellaQuot=new String[6][2];
	
	@Before
	public void setup(){
		adapter = new AdapterLocale();
		partita = new Partita(2,adapter);
		textview = new TextView();
		adapter.addView(textview);
		partita.setAdapter(adapter);
		lavagna=new Lavagna(partita);
		lavagna.setPlancia(partita.getPlancia());
		for (int i=0; i<6;i++){
			tabellaQuot[i][0]=colori[i];
			tabellaQuot[i][1]=Integer.toString(i+2);
		}
		lavagna.setQuotazioni(tabellaQuot);
	}
	
	@Test
	public void casoTestRicalcolaQuotazione(){
		String[] arrivi={"Bianco","Giallo","Rosso","Verde","Blu","Nero"};
		lavagna.ricalcolaQuotazioni(arrivi);
		String[][] quotazioni=lavagna.getQuotazioni();
		assertEquals(3,Integer.parseInt(quotazioni[0][1]));
		assertEquals(4,Integer.parseInt(quotazioni[1][1]));
		assertEquals(5,Integer.parseInt(quotazioni[2][1]));
		assertEquals(4,Integer.parseInt(quotazioni[3][1]));
		assertEquals(5,Integer.parseInt(quotazioni[4][1]));
		assertEquals(6,Integer.parseInt(quotazioni[5][1]));
	}
	
	@Test
	public void casoTestGetScuderiaInit(){
		lavagna.setDebug(true);
		assertEquals("Nero",lavagna.getScuderiaInit("2"));
		assertEquals("Blu",lavagna.getScuderiaInit("3"));
		assertEquals("Verde",lavagna.getScuderiaInit("4"));
		assertEquals("Rosso",lavagna.getScuderiaInit("5"));
		assertEquals("Giallo",lavagna.getScuderiaInit("6"));
		assertEquals("Bianco",lavagna.getScuderiaInit("7"));
	}
	
	@Test
	public void casoTestGetQuotazioneDaColoreIniziale(){
		lavagna.setDebug(true);
		assertEquals(2,lavagna.getQuotazioneDaColoreIniziale("Nero"));
		assertEquals(3,lavagna.getQuotazioneDaColoreIniziale("Blu"));
		assertEquals(4,lavagna.getQuotazioneDaColoreIniziale("Verde"));
		assertEquals(5,lavagna.getQuotazioneDaColoreIniziale("Rosso"));
		assertEquals(6,lavagna.getQuotazioneDaColoreIniziale("Giallo"));
		assertEquals(7,lavagna.getQuotazioneDaColoreIniziale("Bianco"));
	}
	
	@Test
	public void casoTestSetQuotazioneAlCavallo(){
		lavagna.setDebug(true);
		lavagna.setQuotazioneAlCavallo("Blu","+2");
		lavagna.setQuotazioneAlCavallo("Giallo","-2");
		lavagna.setQuotazioneAlCavallo("Verde","+2");
		lavagna.setQuotazioneAlCavallo("Rosso","-2");
		String[][] quot=lavagna.getQuotazioni();
		assertEquals("2",quot[1][1]);
		assertEquals("7",quot[4][1]);
		assertEquals("2",quot[2][1]);
		assertEquals("7",quot[3][1]);
	}
}
