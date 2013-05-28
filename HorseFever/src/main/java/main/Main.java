package main;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {

	public static void main(String[] args){
	
		Partita p=new Partita(2);
		Adapter ad=new Adapter();
		Controller c = new Controller(p);
		TextView tv= new TextView();
		ad.addView(tv);
		p.setAdapter(ad);
		c.setAdapter(ad);
		c.start();
	}
	
	
}
