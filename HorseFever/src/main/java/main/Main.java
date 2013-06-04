package main;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {

	public static void main(String[] args){
	

		Adapter ad=new AdapterLocale();
		Partita p=new Partita(2,ad);
		Controller c = new Controller(p);
		TextView tv= new TextView();
		ad.addView(tv);
		c.setAdapter(ad);
		c.start();
	}
	
	
}
