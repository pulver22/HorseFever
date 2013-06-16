package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import horsefever.Azione;
import eventi.*;

public class TextView implements View{
	
	
	public static final int DIM_SCOM=3;
	public static final int DIM_TRUC=2;
	
	public static final int IMPORTO=0;
	public static final int CARTA_SCELTA=0;
	public static final int CORSIA=1;
	public static final int TIPO=2;
	
	/**
	 * Chiede all'utente i valori per scommettere
	 * @return un array di stringhe con i valori inseriti dall'utente
	 * */
	@Override
	public String[] chiediScommessa() {
		boolean buonfine = false;
		String[] scommessa = new String[DIM_SCOM];
		
		
		while(!buonfine){

    		System.out.println("Inserisci l'importo da scommettere ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[IMPORTO]=myInput.readLine();
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	    catch(NumberFormatException e){
    		
    		    System.out.println("Errore, ci vuole un numero !!!");
    		    buonfine=false;
    	    }
    	}
		buonfine=false;
		while(!buonfine){	
	    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi scommettere: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[CORSIA]=myInput.readLine();
    		}
    		catch(IOException e){
    		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		catch(NumberFormatException e){
    		
    			System.out.println("Errore, ci vuole un numero !!!");
    			buonfine=false;
    		}
    	
    	}
		buonfine=false;
		while(!buonfine){

    		System.out.println("Vuoi scommettere piazzato (P) o vincente (V)?");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[TIPO]=myInput.readLine();
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	
    	}	
		return scommessa;
	}
	
	/**
	 * Metodo per la seconda scommessa. 
	 * @return array di stringhe con i parametri inseriti da utente per la seconda scommessa
	 * */
	@Override
	public String[] chiediSecondaScommessa(){
		boolean buonfine=false;
		String[] scommessa = new String[DIM_SCOM];
		String risposta = "/";
		while(!buonfine){
    		
    		System.out.println("Vuoi scommettere ancora?? (S/N)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			risposta= myInput.readLine();
    			if (risposta!=null && !risposta.equals("S") && !risposta.equals("N")) {
    				System.out.println("Errore, devi inserire S o N");
    				buonfine=false;
    			}
    		}
    		catch(IOException e){
        		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		
    	}
		if (risposta.equals("S")){ scommessa = chiediScommessa(); }
		if (risposta.equals("N")){ scommessa[TIPO] = "N"; }
		return scommessa;
	}

	/**
	 * Metodo che chiede a utente di truccare una corsia
	 * @param l'arraylist di carte azione in mano al giocatore corrispondente
	 * @return l'array di stringhe con i valori inseriti dall'utente
	 * */
	@Override
	public String[] chiediTrucca(ArrayList<Azione> carteAzione) {
		boolean buonfine = false;
		String[] scelta = new String[DIM_TRUC];
		
		System.out.println("Hai in mano queste carte: " );
    	for (int i=0;i< carteAzione.size();i++){
    		System.out.println("" +(i+1)+") " +carteAzione.get(i).getNome()+"," +carteAzione.get(i).getTipoEffetto() +"," +carteAzione.get(i).getValoreEffetto());
    		}
    	
    	while(!buonfine){	
    		System.out.println("Seleziona il numero della carta che vuoi giocare:");
        	try{
        		buonfine=true;
        	    InputStreamReader reader=new InputStreamReader(System.in);
        	    BufferedReader myInput=new BufferedReader(reader);
        	    scelta[CARTA_SCELTA] = myInput.readLine();
        	    int i=Integer.parseInt(scelta[CARTA_SCELTA]);
        	    if (carteAzione.size()==2){
        	    	if (i!=1 &&i!=2){
        	    		System.out.println("Devi inserire uno dei valori in elenco");
        	    		buonfine=false;
        	    	}
        	    }
        	    if (carteAzione.size()==1){
        	    	if (i!=1){
        	    		System.out.println("Devi inserire uno dei valori in elenco");
        	    		buonfine=false;
        	    	}
        	    }
               	}
       
        	catch(IOException e){
        		System.out.println("Errore !!!\n");
        		buonfine=false;
        	}
            catch(NumberFormatException e){
        		System.out.println("Errore, ci vuole un numero !!!");
        		buonfine=false;
        	}
    	}
      	buonfine=false;
    	while(!buonfine){	
    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi giocare la carta: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scelta[CORSIA]=myInput.readLine();
    		}
    		catch(IOException e){
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		catch(NumberFormatException e){
    			System.out.println("Errore, ci vuole un numero !!!");
    			buonfine=false;
    		}
    	
    	}
    	
		return scelta;
	
	}

	/**
	 * Metodo per la stampa di messaggi generici all'utente
	 * @param il messaggio da stampare
	 * */
	@Override
	public void stampaMessaggio(String messaggio) {

		System.out.println(""+messaggio);
		
	}
	/**
	 * Notifica degli eventi lanciati da partita
	 * @param l'evento notificato da gestire
	 * */
	@Override
	public void notify(HorseFeverEvent e) {
		System.out.println(e.rappresentazione());
		
	}

	/**
	 * Metodo con l'unico scopo di bloccare la logica tra una fase e l'altra se l'utente non preme
	 * invio.
	 * @param messaggio da stampare connesso a questo stop della logica
	 * */
	@Override
	public void prosegui(String messaggio) {
		
		System.out.println(messaggio);
		System.out.println("Premi invio per proseguire.");
		try{
			InputStreamReader reader=new InputStreamReader(System.in);
			BufferedReader myInput=new BufferedReader(reader);
			myInput.readLine();
		}
		catch(IOException e){
			System.out.println("Errore !!!\n");
		}

	}


}


