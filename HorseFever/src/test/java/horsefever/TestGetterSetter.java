package horsefever;

import static org.junit.Assert.*;

import org.junit.*;

import controller.*;
import eventi.*;
import View.*;
import adapter.*;

public class TestGetterSetter {

	Partita p;
	Adapter a;
	View v;
	Controller c;
	HorseFeverEvent e;
	
	@Before
	public void setup(){
		a=new AdapterLocale();
		v=new TextView();
		a.addView(v);
		p=new Partita(2,a);
		c=new Controller(p);
	}
	
	@Test
	public void casoTest(){

	}
}
