package adapter;

import horsefever.Azione;

import java.util.ArrayList;

import View.View;

import eventi.HorseFeverEvent;

public interface Adapter {

	public String[] chiediScommessa(int indice);
	
	public String[] chiediSecondaScommessa(int indice);
	
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice);
	
	public void stampaMessaggio(String messaggio,int indice);
	
	public void notify(HorseFeverEvent e);
	
	public void prosegui(String messaggio, int indice);
	
	public void addView(View v);
	
	public void removeView(int i);
	
	public void startServer();
	
	public void connetti(String serverIP);
	
	public void start();
	
}
