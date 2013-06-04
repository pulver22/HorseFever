package main;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {

	public static void main(String[] args){
	

		TextView tv= new TextView();
		Adapter ad=new AdapterLocale();
		ad.addView(tv);
		Partita p=new Partita(2,ad);
		Controller c = new Controller(p);
		c.setAdapter(ad);
		c.start();
	}
	
	
}
