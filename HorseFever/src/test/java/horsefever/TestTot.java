package horsefever;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;

import View.ViewTestMain;
import adapter.Adapter;
import adapter.AdapterLocale;

public class TestTot {

	Partita partita;
	Controller c;
	Adapter adapter;
	ViewTestMain vtm;
	
	@Before
	public void setup(){
		
		adapter = new AdapterLocale();
		vtm = new ViewTestMain();
		adapter.addView(vtm);
		partita = new Partita(2,adapter);
		c= new Controller();
		c.setAdapter(adapter);
		c.setPartita(partita);
		partita.setDebug(true);
		c.setDebug(true);
	}
	
	@Test
	public void casoTesttot(){
		c.start();
		assertEquals(7,partita.getGiocatori().get(0).getPV());
	}
	
}
