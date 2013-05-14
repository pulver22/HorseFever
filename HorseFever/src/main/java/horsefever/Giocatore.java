package horsefever;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Giocatore {

	private int PV=1;
	private long denari;
	private Carta cartaPersonaggio;
	private String scuderia;
	
	private ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
	
	
	public Giocatore(Personaggio cartaPersonaggio, String scuderia){
		this.cartaPersonaggio=cartaPersonaggio;
		denari=cartaPersonaggio.getDenari();
		this.scuderia=scuderia;
		
	}
    public void scommetti(int numscommessa){
       
    	int importo=0, numcorsia=0, ScommessaMinima=PV*100;
    	char risposta='N';
    	boolean buonfine=false;
    	
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
    		if(risposta=='N' || risposta=='n') return;
    		if(denari<ScommessaMinima) return;
    	}
    		
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
    	
    	
		System.out.println("Hai scommesso "+importo+" denari sulla corsia: "+numcorsia);
    	}
	
    }

    public void trucca(){
    	boolean buonfine = false;
    	int numcarta=0;
    	System.out.println("Hai in mano queste carte: " );
    	
    	for (int i=0;i< carteAzione.size();i++){
    		System.out.println("" +(i+1)+") " +carteAzione.get(i).getNome()+"," +carteAzione.get(i).getTipo_effetto() +"," +carteAzione.get(i).getValore_effetto());
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
	public void setDenari(int denari) {
		this.denari = denari;
	}
		
		
	
	
	
	
}
