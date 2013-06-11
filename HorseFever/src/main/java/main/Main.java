package main;

import javax.swing.JOptionPane;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {

	public static void main(String[] args){
	
		int numGiocatori;
		String sceltaGiocatori;
		String[] opzioniGiocatori = {"2","3","4","5","6"};
		sceltaGiocatori = (String)JOptionPane.showInputDialog(null, "Quanti sono i giocatori?","Numero Giocatori", JOptionPane.PLAIN_MESSAGE,null, opzioniGiocatori, opzioniGiocatori);
		numGiocatori=Integer.parseInt(sceltaGiocatori);
		View tv;
		String sceltaVista;
		String[] opzioniVista = {"Graphic User Interface","Testuale"};
		sceltaVista = (String)JOptionPane.showInputDialog(null, "Con quale interfaccia vuoi giocare?","Opzioni Vista", JOptionPane.PLAIN_MESSAGE,null, opzioniVista, opzioniVista);
		if (sceltaVista.equals("Testuale"))
			tv= new TextView();
		else 
			tv= new GUIView();
		String sceltaRete;
		String[] opzioniRete = {"Locale","Rete"};
		Adapter ad;
		sceltaRete = (String)JOptionPane.showInputDialog(null, "Vuoi giocare in rete o in locale?","Opzioni Rete", JOptionPane.PLAIN_MESSAGE,null, opzioniRete, opzioniRete);
		if (sceltaRete.equals("Locale")){
			ad=new AdapterLocale();
			ad.addView(tv);
		}
		else {
			String[] opzioniClient= {"Server","Client"};
			String sceltaClient;
			sceltaClient=(String)JOptionPane.showInputDialog(null, "Vuoi essere Client o Server?","Opzioni Client/Server", JOptionPane.PLAIN_MESSAGE,null, opzioniClient, opzioniClient);
			if (sceltaClient.equals("Server")){
				ad=new AdapterReteServer(numGiocatori,tv);
				ad.startServer();
			} else {
				String serverIP;
				serverIP = (String) JOptionPane.showInputDialog( null, "Inserisci l'IP del Server a cui connettersi.","Inserimento Server IP", JOptionPane.PLAIN_MESSAGE);
				ad=new AdapterReteClient();
				ad.addView(tv);
				ad.connetti(serverIP);
				ad.start();
			}
		}
		ad.addView(tv);
		Partita p=new Partita(numGiocatori,ad);
		Controller c = new Controller(p);
		c.setAdapter(ad);
		c.start();
	}
	
	
}
