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

		 adapter=new AdapterLocale();
		 partitaprova=new Partita(6,adapter);
		 controller=new Controller(partitaprova);
		 view =new TextView();
		 //controller.setView(view);
		 partitaprova.setAdapter(adapter);
		 adapter.addView(view);
		 
	 }
	 
	 public void startTest(){
		 controller.FaseCorsa();
	 }
}
