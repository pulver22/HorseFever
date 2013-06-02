package adapter;

import horsefever.Azione;

import java.util.ArrayList;

import View.View;
import eventi.HorseFeverEvent;

public class AdapterRete implements Adapter{

	@Override
	public String[] chiediScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] chiediSecondaScommessa(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void stampaMessaggio(String messaggio, int indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notify(HorseFeverEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addView(View v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeView(int i) {
		// TODO Auto-generated method stub
		
	}

}
