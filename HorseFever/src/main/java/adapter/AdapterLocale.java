package adapter;

import horsefever.Azione;

import java.util.ArrayList;
import View.*;
import eventi.HorseFeverEvent;

public class AdapterLocale implements Adapter {

	ArrayList<View> viewRegistrate=new ArrayList<View>();

	public String[] chiediScommessa(int indice) {
		
		String[] valori=new String[3];
		
		valori=viewRegistrate.get(0).chiediScommessa();
		
		
		return valori;
	}
	
	public String[] chiediSecondaScommessa(int indice){
		
        String[] valori=new String[3];
        
        valori=viewRegistrate.get(0).chiediSecondaScommessa();
		
		return valori;
	}
	
	public String[] chiediTrucca(ArrayList<Azione> carteAzione, int indice) {
		
		String[] scelta = new String[2];
		
		scelta=viewRegistrate.get(0).chiediTrucca(carteAzione);
		
		return scelta;
	}
	
	public void stampaMessaggio(String messaggio,int indice){
		
		viewRegistrate.get(0).stampaMessaggio(messaggio);
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
