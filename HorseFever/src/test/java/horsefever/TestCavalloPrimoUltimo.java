package horsefever;

import static org.junit.Assert.assertEquals;

import org.junit.*;

public class TestCavalloPrimoUltimo {

	Cavallo cavallo;
	Azione azione1;
	Azione azione2;
	
	@Before
	public void setup(){
		cavallo=new Cavallo("Nero");
		azione1=new Azione("Vigor Ferreum","Verde","Utimo","=4",'G',1);
		azione2=new Azione("Feliz Infernalis","Rosso","Primo","=0",'G',2);
	}
	
	@Test
	public void casoTestPrimo1(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione1.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,true,false);
		assertEquals(2,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestPrimo2(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,true,false);
		assertEquals(0,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestUltimo1(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione1.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,false,true);
		assertEquals(4,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestUltimo2(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,false,true);
		assertEquals(2,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestNessuno1(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,false,false);
		assertEquals(2,cavallo.getPosizione());
	}
	
	@Test
	public void casoTestNessuno2(){
		int incremento=2;
		cavallo.setEffettoUltimoPrimo(azione2.getValoreEffetto());
		cavallo.aggiornaPosizionePrimoUltimo(incremento,false,false);
		assertEquals(2,cavallo.getPosizione());
	}
	
}
