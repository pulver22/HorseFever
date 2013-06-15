package adapter;

import horsefever.Azione;

import java.util.ArrayList;

import View.View;

import eventi.HorseFeverEvent;

public interface Adapter {

	String[] chiediScommessa(int indice);
	
	String[] chiediSecondaScommessa(int indice);
	
	String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice);
	
	void stampaMessaggio(String messaggio,int indice);
	
	void notify(HorseFeverEvent e);
	
	void prosegui(String messaggio, int indice);
	
	void addView(View v);
	
	void removeView(int i);
	
	void startServer();
	
	void connetti(String serverIP);
	
	void start();
	
	void evidenziaGiocatore(String nomeGiocatore, int indice);
	
}
