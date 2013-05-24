package horsefever;

import View.TextView;
import adapter.Adapter;
import controller.Controller;

public class mainTest {

	
	public static void main(String[] args) {
		
		/*
		 * Test Pagamenti
		TestPagamento prova=new TestPagamento();
		prova.setUp();
		prova.testDenariGiocatori();
        */
		Partita p=new Partita(6);
		Controller c=new Controller(p);
		Adapter ad = new Adapter();
		TextView v=new TextView();
		c.setView(v);
		ad.addView(v);
		p.setAdapter(ad);
		for (int i=0; i<6;i++){
			Azione a=(Azione)p.getMazzoAzione().pesca();
			p.getPlancia().TruccaCorsia(a, i+1);
			System.out.println(a.toString());
		}
		
		c.FaseCorsa();
	}

}
