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
		partita = new Partita(2);
		adapter = new AdapterLocale();
		textview = new TextView();
		adapter.addView(textview);
		partita.setAdapter(adapter);
		lavagna=new Lavagna(partita);
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
}
