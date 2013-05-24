package horsefever;

import View.*;
import adapter.*;
import controller.*;
import horsefever.*;

/**
 * testa i movimenti senza gli effetti delle carte Azione
 * @author alessiorossotti
 *
 */
public class TestMovimenti {

	private Partita partitaprova;
	 private Controller controller;
	 private Adapter adapter;
	 private TextView view;
	 
	 
  
	 public TestMovimenti(){
		 partitaprova=new Partita(6);
		 controller=new Controller(partitaprova);
		 adapter=new Adapter();
		 view =new TextView();
		 controller.setView(view);
		 partitaprova.setAdapter(adapter);
		 adapter.addView(view);
		 
	 }
	 
	 public void startTest(){
		 controller.FaseCorsa();
	 }
}
