package adapter;

import horsefever.Azione;

import java.util.ArrayList;
import View.*;
import eventi.HorseFeverEvent;

public class Adapter {

	ArrayList<View> viewRegistrate=new ArrayList<View>();

	public String[] chiediScommessa(int indice) {
		
		String[] valori=new String[3];
		
		valori=viewRegistrate.get(indice).chiediScommessa();
		
		
		return valori;
	}
	
	public String[] chiediSecondaScommessa(int indice){
		
        String[] valori=new String[3];
        
        valori=viewRegistrate.get(indice).chiediSecondaScommessa();
		
		return valori;
	}
	
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String[] scelta = new String[2];
		
		scelta=viewRegistrate.get(indice).chiediTrucca(carteAzione);
		
		return scelta;
	}
	
	public void stampaMessaggio(String messaggio,int indice){
		
		viewRegistrate.get(indice).stampaMessaggio(messaggio);
	}

	public void notify(HorseFeverEvent e){
		
		for (View v: viewRegistrate){
			v.notify(e);
		}
	}
	
	
	public void addView(View v){
		viewRegistrate.add(v);
	}
	public void removeView(int i){
		viewRegistrate.remove(i);
	}
}
