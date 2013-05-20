package horsefever;

import static org.junit.Assert.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class TestPagamento {

	private Partita partita=new Partita(6);
	private BetManager betManager=new BetManager();
	private ArrayList<Scommessa> scommessebManager = new ArrayList<Scommessa>();
	

	public void setUp() {
		
		int i;
		String[][] quotazioni = new String[6][2];
		String[] ordineArrivo = new String[6];
		ArrayList<Giocatore> giocatori=new ArrayList(6);
		
		partita.preparazione();
	    giocatori=partita.getGiocatori();
		
	    // stampa i giocatori come sono stati preparati
		
	    System.out.println("------------ Situazione Iniziale Giocatori ------------");
	    
		for(i=0;i<6;i++){
			
			System.out.println("");
			System.out.println("Giocatore numero "+(i+1));
			System.out.println("Personaggio: "+giocatori.get(i).getCartaPersonaggio().getNome());
			System.out.println("Numero PV: "+giocatori.get(i).getPV());
			System.out.println("Numero denari: "+giocatori.get(i).getDenari());
			System.out.println("Scuderia: "+giocatori.get(i).getScuderia());
			System.out.println("-------------------------------------------");
			
		}
		
		//set di quotazioni
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
		
		//set di ordine arrivo
		ordineArrivo[0]="1";
		ordineArrivo[1]="0";
		ordineArrivo[2]="2";
		ordineArrivo[3]="3";
		ordineArrivo[4]="4";
		ordineArrivo[5]="5";
		
		//creazione scommesse ad-hoc
		Scommessa scom1=new Scommessa(giocatori.get(0),1,1000,'P');
		Scommessa scom2=new Scommessa(giocatori.get(1),2,1000,'P');
		Scommessa scom3=new Scommessa(giocatori.get(2),3,1000,'P');
		Scommessa scom4=new Scommessa(giocatori.get(3),4,1000,'P');
		Scommessa scom5=new Scommessa(giocatori.get(4),5,1000,'P');
		Scommessa scom6=new Scommessa(giocatori.get(5),0,1000,'P');
		Scommessa scom7=new Scommessa(giocatori.get(0),1,1000,'P');
		Scommessa scom8=new Scommessa(giocatori.get(1),2,1000,'P');
		Scommessa scom9=new Scommessa(giocatori.get(2),3,1000,'P');
		Scommessa scom10=new Scommessa(giocatori.get(3),4,1000,'P');
		Scommessa scom11=new Scommessa(giocatori.get(4),5,1000,'P');
		Scommessa scom12=new Scommessa(giocatori.get(5),0,1000,'P');
		
		//aggiunta di tutte le scommesse al betmanager
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
		
		scommessebManager=betManager.getbManager();
		int numcorsia;
		long importo;
		char tiposcommessa;
		
		//stampa le scommesse memorizzate in Bet Manager
		for(i=0;i<12;i++){
			
			numcorsia=scommessebManager.get(i).getCorsia();
			importo=scommessebManager.get(i).getImporto();
			tiposcommessa=scommessebManager.get(i).getTipoScomessa();
			System.out.println("Scommessa numero "+(i+1)+" : importo= "+importo+" corsia= "+numcorsia+" tipo scommessa= "+tiposcommessa);
		}
		
		//Richiamo metodo pagamenti da betmanager
		betManager.Pagamenti(ordineArrivo, quotazioni , partita.getGiocatori());
		
	}

	public void testDenariGiocatori() {

		int i;
		ArrayList<Giocatore> giocatori=new ArrayList(6);
		
		giocatori= partita.getGiocatori();

        
        for(i=0;i<6;i++){
			
			System.out.println("");
			System.out.println("Giocatore numero "+(i+1));
			System.out.println("Personaggio: "+giocatori.get(i).getCartaPersonaggio().getNome());
			System.out.println("Numero PV: "+giocatori.get(i).getPV());
			System.out.println("Numero denari: "+giocatori.get(i).getDenari());
			System.out.println("Scuderia: "+giocatori.get(i).getScuderia());
			System.out.println("-------------------------------------------");
			
		}
      
	   
		
        
	}
}