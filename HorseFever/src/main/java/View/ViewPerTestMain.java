package View;

import horsefever.Azione;

import java.util.ArrayList;

import eventi.HorseFeverEvent;

public class ViewPerTestMain implements View{

	private int scomCount=0,trucCount=0;
	
	@Override
	public String[] chiediScommessa() {
		String[] scommessa=new String[3];
		scommessa[0]="100";
		scommessa[2]="V";
		if (scomCount==0){
			scommessa[1]="1";
			scomCount++;
		} else {
			scommessa[1]="2";
			scomCount=0;
		}
		
		return scommessa;
	}
	
	
	@Override
	public String[] chiediSecondaScommessa(){
		String[] scommessa = new String[3];
		scommessa[2] = "N";
		return scommessa;
	}

	
	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione) {
		String[] scelta = new String[2];
		scelta[0]="1";
		if (trucCount==0){
			scelta[1]="3";
			trucCount++;
		} else {
			scelta[1]="4";
			trucCount=0;
		}
    	
		return scelta;
	
	}

	@Override
	public void stampaMessaggio(String messaggio) {

		System.out.println(""+messaggio);
		
	}
	
	@Override
	public void notify(HorseFeverEvent e) {
		System.out.println(e.rappresentazione());
		
	}

	@Override
	public void prosegui(String messaggio) {
		
		System.out.println(messaggio);
		System.out.println("\n");

	}

}
