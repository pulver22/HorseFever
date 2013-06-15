package main;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import horsefever.*;
import View.*;
import controller.*;
import adapter.*;


public class Main {
	/**
	 * Il main effettivo che lancia l'intero gioco
	 * @author Niccolo
	 * */
	
	//private final static ImageIcon sfondo=new ImageIcon(View.class.getResource("elementiBoard/background.jpg"));
	
	public static void main(String[] args){
	
		String colorePannelli="#FFFFFF";
		UIManager.put("OptionPane.background",Color.decode(colorePannelli));
		UIManager.put("Panel.background",Color.decode(colorePannelli));
		//Scelta numero giocatori
		int numGiocatori;
		String sceltaGiocatori;
		String[] opzioniGiocatori = {"2","3","4","5","6"};
		sceltaGiocatori = (String)JOptionPane.showInputDialog(null, "Quanti sono i giocatori?","Numero Giocatori", JOptionPane.PLAIN_MESSAGE,null, opzioniGiocatori, opzioniGiocatori);
		numGiocatori=Integer.parseInt(sceltaGiocatori);
		
		//Scelta Vista
		View tv;
		String sceltaVista;
		String[] opzioniVista = {"Graphic User Interface","Testuale"};
		sceltaVista = (String)JOptionPane.showInputDialog(null, "Con quale interfaccia vuoi giocare?","Opzioni Vista", JOptionPane.PLAIN_MESSAGE,null, opzioniVista, opzioniVista);
		if (sceltaVista.equals("Testuale")){
			tv= new TextView();
		}else {
			tv= new GUIView();
		}
		Controller c = new Controller();
		
		//Scelta Rete
		String sceltaRete;
		String[] opzioniRete = {"Locale","Rete"};
		Adapter ad;
		sceltaRete = (String)JOptionPane.showInputDialog(null, "Vuoi giocare in rete o in locale?","Opzioni Rete", JOptionPane.PLAIN_MESSAGE,null, opzioniRete, opzioniRete);
		if (sceltaRete.equals("Locale")){
			ad=new AdapterLocale();
			ad.addView(tv);
		}
		else {
			//Scelta Client-Server
			String[] opzioniClient= {"Server","Client"};
			String sceltaClient;
			sceltaClient=(String)JOptionPane.showInputDialog(null, "Vuoi essere Client o Server?","Opzioni Client/Server", JOptionPane.PLAIN_MESSAGE,null, opzioniClient, opzioniClient);
			if (sceltaClient.equals("Server")){
				ad=new AdapterReteServer(numGiocatori,tv,c);
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
		
		Partita p=new Partita(numGiocatori,ad);
		c.setPartita(p);
		c.setAdapter(ad);
		//Inizio Partita
		c.start();
	}
	
	
}
