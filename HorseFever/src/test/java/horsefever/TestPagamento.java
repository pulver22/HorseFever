package horsefever;

import static org.junit.Assert.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestPagamento {

	private Partita partita;
	private BetManager betManager;
	

	public void setUp() {

		partita=new Partita(6);
		
		partita.preparazione();
		String[][] quotazioni = new String[6][2];
		String[] ordineArrivo = new String[6];
		
		quotazioni[0][0]="Nero";
		quotazioni[1][0]="Blu";
		quotazioni[2][0]="Verde";
		quotazioni[3][0]="Rosso";
		quotazioni[4][0]="Giallo";
		quotazioni[5][0]="Bianco";
		
		quotazioni[0][1]="2";
		quotazioni[1][1]="3";
		quotazioni[2][1]="4";
		quotazioni[3][1]="5";
		quotazioni[4][1]="6";
		quotazioni[5][1]="7";
		
		ordineArrivo[0]="1";
		ordineArrivo[1]="2";
		ordineArrivo[2]="3";
		ordineArrivo[3]="4";
		ordineArrivo[4]="5";
		ordineArrivo[5]="6";
		
		Scommessa scom1=new Scommessa(partita.getGiocatori(1),1,1000,'V');
		Scommessa scom2=new Scommessa(partita.getGiocatori(2),2,1000,'V');
		Scommessa scom3=new Scommessa(partita.getGiocatori(3),3,1000,'V');
		Scommessa scom4=new Scommessa(partita.getGiocatori(4),4,1000,'V');
		Scommessa scom5=new Scommessa(partita.getGiocatori(5),5,1000,'V');
		Scommessa scom6=new Scommessa(partita.getGiocatori(6),6,1000,'V');
		Scommessa scom7=new Scommessa(partita.getGiocatori(1),1,1000,'V');
		Scommessa scom8=new Scommessa(partita.getGiocatori(2),2,1000,'V');
		Scommessa scom9=new Scommessa(partita.getGiocatori(3),3,1000,'V');
		Scommessa scom10=new Scommessa(partita.getGiocatori(4),4,1000,'V');
		Scommessa scom11=new Scommessa(partita.getGiocatori(5),5,1000,'V');
		Scommessa scom12=new Scommessa(partita.getGiocatori(6),6,1000,'V');
		
		betManager.AggiungiScommessa(scom1);
		betManager.AggiungiScommessa(scom2);
		betManager.AggiungiScommessa(scom3);
		betManager.AggiungiScommessa(scom4);
		betManager.AggiungiScommessa(scom5);
		betManager.AggiungiScommessa(scom6);
		betManager.AggiungiScommessa(scom7);
		betManager.AggiungiScommessa(scom8);
		betManager.AggiungiScommessa(scom9);
		betManager.AggiungiScommessa(scom10);
		betManager.AggiungiScommessa(scom11);
		betManager.AggiungiScommessa(scom12);
		
		betManager.Pagamenti(ordineArrivo, quotazioni , partita.getGiocatori());
		
	}

	public boolean testTimeToDestination() {

		int i;
		boolean sbagliato=false;
		Personaggio bla=new Personaggio("bla",0,"1");
		Giocatore Gioc=new Giocatore(bla,"Nero");
		
		ArrayList<Giocatore> giocatori=new ArrayList(6);
		ArrayList<Giocatore> giocatori1=new ArrayList(6);
		giocatori= partita.getGiocatori();
        giocatori1=partita.getGiocatori();
        
        
        for(i=0;i<6;i++){
        	
        	Gioc.setDenari(5000);
        	Gioc.setPV(3);
        	giocatori1.add(Gioc);
        }
	    
        	for(i=0;i<6;i++){
        	
        		if(giocatori.get(i).getDenari()!=giocatori1.get(i).getDenari()) sbagliato=true;
        		if(giocatori.get(i).getPV()!=giocatori1.get(i).getPV()) sbagliato=true;
        	}
        	
            System.out.println("Sbagliato = "+sbagliato);
    	    return sbagliato;
        }
		
        
	}
