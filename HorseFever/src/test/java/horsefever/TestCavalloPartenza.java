package horsefever;

import static org.junit.Assert.*;

import org.junit.*;

public class TestCavalloPartenza {

	Cavallo cavallo1;
	Cavallo cavallo2;
	Cavallo cavallo3;
	Cavallo cavallo4;
	Azione azione1;
	Azione azione2;
	Azione azione3;
	Azione azione4;
	
	@Before
	public void setup(){
		cavallo1=new Cavallo("Nero");
		azione1=new Azione("Magna Velocitas","Verde","Partenza","=4",'A');
		azione2=new Azione("Fortuna Malevola","Verde","Partenza","+1",'B');
		azione3=new Azione("Globulus Obscurus","Rosso","Partenza","=0",'A');
		azione4=new Azione("Aqua Putrida","Rosso","Partenza","-1",'B');
	}
	
	@Test
	public void casoTestDoppio(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione1.getValoreEffetto());
		cavallo1.setEffettoPartenza2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(5,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTest2Doppio(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione1.getValoreEffetto());
		cavallo1.setEffettoPartenza2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(3,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestDoppio3(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione3.getValoreEffetto());
		cavallo1.setEffettoPartenza2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(1,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestDoppio4(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione3.getValoreEffetto());
		cavallo1.setEffettoPartenza2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo1(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione1.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(4,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo2(){
		int incremento=2;
		cavallo1.setEffettoPartenza(azione3.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo3(){
		int incremento=2;
		cavallo1.setEffettoPartenza2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(3,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo4(){
		int incremento=2;
		cavallo1.setEffettoPartenza2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(1,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestNessuno(){
		int incremento=2;
		cavallo1.aggiornaPosizionePartenza(incremento);
		assertEquals(2,cavallo1.getPosizione());
	}
	
}
