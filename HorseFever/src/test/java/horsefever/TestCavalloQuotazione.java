package horsefever;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestCavalloQuotazione {

	Cavallo cavallo;
	Azione azione1;
	Azione azione2;
	
	@Before
	public void setup(){
		cavallo=new Cavallo("Nero");
		cavallo.setQuotazione(4);
		azione1=new Azione("Alfio Allibratore","Grigio","Quotazione","+2",'W');
		azione2=new Azione("Steven Sting","Grigio","Quotazione","-2",'Y');
	}
	
	@Test
	public void casoTestQuot1(){
		cavallo.setEffettoQuotazione(azione1.getValoreEffetto());
		assertEquals(2,cavallo.getQuotazione());
	}
	
	@Test
	public void casoTestQuot2(){
		cavallo.setEffettoQuotazione(azione2.getValoreEffetto());
		assertEquals(6,cavallo.getQuotazione());
	}
	
	@Test
	public void casoTestQuotLimite1(){
		cavallo.setQuotazione(3);
		cavallo.setEffettoQuotazione(azione1.getValoreEffetto());
		assertEquals(2,cavallo.getQuotazione());
	}
	
	@Test
	public void casoTestQuotLimite2(){
		cavallo.setQuotazione(6);
		cavallo.setEffettoQuotazione(azione2.getValoreEffetto());
		assertEquals(7,cavallo.getQuotazione());
	}
	
}
