package View;

import horsefever.Azione;

import java.util.ArrayList;

import eventi.HorseFeverEvent;

public class ViewTestMain implements View{

	private int scomCount=0;
	private int trucCount=0;
	private String[] scommessa1={"700","1","P"};
	private String[] scommessa2={"100","6","V"};

	private static final int TRE=3;
	
	@Override
	public String[] chiediScommessa() {
		String[] scommessa;
		
		if(scomCount==0){
			scommessa=scommessa1;
			scomCount++;
		} else {
			scommessa=scommessa2;
			scomCount=0;
		}
		
		return scommessa;
	}

	@Override
	public String[] chiediSecondaScommessa() {
		String[] scommessa=new String[TRE];
		scommessa[2]="N";
		return scommessa;
	}

	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione) {
		String[] scelta=new String[2];
		scelta[0]="1";
		
		if (trucCount==0){
			scelta[1]="1";
			trucCount++;
		} else if (trucCount==1){
			scelta[1]="2";
			trucCount++;
		} else if (trucCount==2){
			scelta[1]="3";
			trucCount++;
		} else if (trucCount==TRE){
			scelta[1]="4";
			trucCount=0;
		}
		
		return scelta;
	}

	@Override
	public void stampaMessaggio(String messaggio) {
		System.out.println(messaggio);
		
	}

	@Override
	public void prosegui(String messaggio) {
		System.out.println(messaggio);
	}

	@Override
	public void notify(HorseFeverEvent e) {
		System.out.println(e.rappresentazione());
	}

}
