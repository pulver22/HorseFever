package horsefever;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;

public class Giocatore {

	private int PV=1;
	private long denari;
	private Plancia plancia;
	private Carta cartaPersonaggio;
	private String scuderia;
	private Scommessa scommessa;
	
	private ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
	
	
	public Giocatore(Personaggio cartaPersonaggio, String scuderia){
		this.cartaPersonaggio=cartaPersonaggio;
		denari=cartaPersonaggio.getDenari();
		this.scuderia=scuderia;
		
	}
	/**
	 *  @param numscommessa 1:obbligatoria 2:facoltativa
	 *  Viene verificato se la scommessa è obbligatoria o meno, in tal caso viene chiesto al giocatore
	 *  se vuole scommettere oppure no, in caso di risposta affermativa viene chiesto al giocatore 
	 *  il numero di corsia, l'importo e il tipo di scommessa che vuole effettare
	 *  @return scommessa 
	 */
    public Scommessa Scommetti(int numscommessa,int[] numSegnalini){
       
    	int importo=0, numcorsia=0, ScommessaMinima=PV*100;
    	char risposta='N',tiposcommessa='N';
    	boolean buonfine=false;
    	
    	// Chiede al giocatore se vuole scommettere visto che la seconda scommessa è facoltativa 
    	if(numscommessa==2){
    		
    		while(buonfine==false){
    		
    		System.out.println("Vuoi scommettere ancora?? (S/N)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			risposta= myInput.readLine().charAt(0);
    		}
    		catch(IOException e){
        		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		
    		}
    		/* Se il giocatore non vuole più scommettere o non ha denari per scommettere
    		  restituisce una scommessa con il campo tiposcommessa=N */
    		if(risposta=='N' || risposta=='n' || denari<ScommessaMinima){
    			
    			scommessa=new Scommessa(this,10,0,'N');
    			return scommessa;
    		}
    		
    	}
    	
    	/*  Se la scommessa è obbligatoria e il giocatore non ha abbastanza denari
    	   gli vengono sottratti 2 PV, se non ha 2 PV il giocatore perde la partita */
    	 
    	if(denari<ScommessaMinima){
    		
            if(PV<2){   
            	
            	System.out.println("Hai perso la partita");
            	//aggiungere eliminazione giocatore
            }   
            else{
    		       PV=PV-2;
    		       ScommessaMinima=PV*100;
    		       System.out.println("Non hai abbastanza denari per scommettere! Perdi due PV!");
            }
    	}
    	else{
    		
    	System.out.println("Hai a disposizione "+denari+" denari.");
    	
    	
    	//Chiede al giocatore su quale corsia vuole scommettere
    	 
    	while(buonfine==false){	
    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi scommettere: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			numcorsia=Integer.parseInt(""+myInput.readLine());
    			if(numcorsia<1 || numcorsia>6){
    	    	
    				System.out.println("Errore!! Inserisci un numero da 1 a 6");
    				buonfine=false;
    			}
    			
    			 if(numSegnalini[numcorsia]==0){
    			 
    			 System.out.println("Errore!! Non puoi fare altre scommesse su questa corsia!");
    			 buonfine=false;
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
    	
    	//chiede al giocatore l'importo da scommettere
    	while(buonfine==false){

    		System.out.println("Inserisci l'importo da scommettere (min "+ScommessaMinima+" denari)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			importo=Integer.parseInt(""+myInput.readLine());
    			if(importo>denari || importo<ScommessaMinima){
    	    	
    				System.out.println("Errore, importo non consentito!! ");
    				buonfine=false;
    			}
            
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
    	
    	//chiede al giocatore se vuole scommettere piazzato o vincente
    	while(buonfine==false){

    		System.out.println("Vuoi scommettere piazzato (P) o vincente (V)?");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			tiposcommessa=myInput.readLine().charAt(0);
    			if(tiposcommessa!='V' && tiposcommessa!='P'){
    	    	
    				System.out.println("Errore, scegli P o V ");
    				buonfine=false;
    			}
            
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	
    	}
      }
     
      scommessa=new Scommessa(this,numcorsia,importo,tiposcommessa);
	  System.out.println("Hai scommesso "+importo+" denari sulla corsia: "+numcorsia);
	  return scommessa;
    }

    /**
     * Viene chiesto al giocatore quale delle sue carte azione vuole giocare e su quale corsia
     * applicarla, queste informazioni vengono poi passate al metodo TruccaCorsia in plancia
     * La carta giocata viene infine rimossa dalle carte a disposizione del giocatore
     */
    public void Trucca(){
    	boolean buonfine = false;
    	int numcarta=0,numcorsia=0;
    	System.out.println("Hai in mano queste carte: " );
    	
    	for (int i=0;i< carteAzione.size();i++){
    		System.out.println("" +(i+1)+") " +carteAzione.get(i).getNome()+"," +carteAzione.get(i).getTipoEffetto() +"," +carteAzione.get(i).getValoreEffetto());
    		}
    	
    	while( buonfine==false){	
    		System.out.println("Scegli quale carta vuoi giocare:");
        	try{
        		buonfine=true;
        	    InputStreamReader reader=new InputStreamReader(System.in);
        	    BufferedReader myInput=new BufferedReader(reader);
        	    
        	    numcarta = Integer.parseInt(""+myInput.readLine());
        	    
        	    if(numcarta<1 || numcarta>carteAzione.size()){
        	    	System.out.println("Errore!! Inserisci un numero da 1 a " +carteAzione.size());
        	    	buonfine=false;
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
      	
    	while(buonfine==false){	
    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi giocare la carta: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			numcorsia=Integer.parseInt(""+myInput.readLine());
    			if(numcorsia<1 || numcorsia>6){
    	    	
    				System.out.println("Errore!! Inserisci un numero da 1 a 6");
    				buonfine=false;
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
    	plancia.TruccaCorsia(carteAzione.get(numcarta-1), numcorsia);
    	carteAzione.remove(numcarta -1);
        	
    }
    
    //Metodi Setter e Getter
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}
	public Carta getCartaPersonaggio() {
		return cartaPersonaggio;
	}
	public void setCartaPersonaggio(Personaggio cartaPersonaggio) {
		this.cartaPersonaggio = cartaPersonaggio;
	}
	public ArrayList<Azione> getCarteAzione() {
		return carteAzione;
	}
	public void setCarteAzione(ArrayList<Azione> carteAzione) {
		this.carteAzione = carteAzione;
	}
	public void setScudera(String scuderia){
		this.scuderia=scuderia;
	}
	public long getDenari() {
		return denari;
	}
	public void setDenari(long nuoviDenari) {
		this.denari = nuoviDenari;
	}
	
		
		
	
	
	
	
}
