package horsefever;

import static org.junit.Assert.*;

import org.junit.*;

public class TestCavalloSprint {

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
		azione1=new Azione("Flagellum Fulguris","Verde","Sprint","+1",'C',1);
		azione2=new Azione("Herba Magica","Verde","Sprint","=2",'D',2);
		azione3=new Azione("Serum Maleficum","Rosso","Sprint","=0",'C',3);
		azione4=new Azione("Venenum Veneficum","Rosso","Sprint","-1",'D',4);
	}
	
	@Test
	public void casoTestDoppio(){
		cavallo1.setEffettoSprint(azione1.getValoreEffetto());
		cavallo1.setEffettoSprint2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(3,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTest2Doppio(){
		cavallo1.setEffettoSprint(azione1.getValoreEffetto());
		cavallo1.setEffettoSprint2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(1,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestDoppio3(){
		cavallo1.setEffettoSprint(azione3.getValoreEffetto());
		cavallo1.setEffettoSprint2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestDoppio4(){
		cavallo1.setEffettoSprint(azione3.getValoreEffetto());
		cavallo1.setEffettoSprint2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo1(){
		cavallo1.setEffettoSprint(azione1.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(2,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo2(){
		cavallo1.setEffettoSprint(azione3.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo3(){
		cavallo1.setEffettoSprint2(azione2.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(2,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestSingolo4(){
		cavallo1.setEffettoSprint2(azione4.getValoreEffetto());
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(0,cavallo1.getPosizione());
	}
	
	@Test
	public void casoTestNessuno(){
		cavallo1.aggiornaPosizioneSprint();
		assertEquals(1,cavallo1.getPosizione());
	}
}