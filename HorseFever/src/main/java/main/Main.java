package main;

import javax.swing.JOptionPane;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {

	public static void main(String[] args){
	
		View tv;
		String sceltaVista;
		String[] opzioniVista = {"Graphic User Interface","Testuale"};
		sceltaVista = (String)JOptionPane.showInputDialog(null, "Con quale interfaccia vuoi giocare?","Opzioni Vista", JOptionPane.PLAIN_MESSAGE,null, opzioniVista, opzioniVista);
		if (sceltaVista.equals("Testuale"))
			tv= new TextView();
		else 
			tv= new GUIView();
		Adapter ad=new AdapterLocale();
		ad.addView(tv);
		Partita p=new Partita(2,ad);
		Controller c = new Controller(p);
		c.setAdapter(ad);
		c.start();
	}
	
	
}
