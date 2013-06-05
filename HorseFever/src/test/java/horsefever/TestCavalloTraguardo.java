package horsefever;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestCavalloTraguardo {

	Cavallo cavallo;
	Azione azione1;
	Azione azione2;
	
	@Before
	public void setup(){
		cavallo=new Cavallo("Nero");
		azione1=new Azione("Fustis et Radix","Verde","Traguardo","+2",'F');
		azione2=new Azione("XIII","Rosso","Traguardo","=0",'F');
	}
	
	@Test
	public void casoTestTraguardo1(){
		int incremento=2;
		cavallo.setPosizione(11);
		cavallo.setEffettoTraguardo(azione1.getValoreEffetto());
		cavallo.aggiornaPosizione(incremento);
		assertEquals(15,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestTraguardo2(){
		int incremento=2;
		cavallo.setPosizione(12);
		cavallo.setEffettoTraguardo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizione(incremento);
		assertEquals(12,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestNoTraguardo1(){
		int incremento=2;
		cavallo.setPosizione(10);
		cavallo.setEffettoTraguardo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizione(incremento);
		assertEquals(12,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestNoTraguardo2(){
		int incremento=2;
		cavallo.setPosizione(10);
		cavallo.setEffettoTraguardo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizione(incremento);
		assertEquals(12,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestNessuno(){
		int incremento=2;
		cavallo.aggiornaPosizione(incremento);
		assertEquals(2,cavallo.getPosizione());
	}
	
}
