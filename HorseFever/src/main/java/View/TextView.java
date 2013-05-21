package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import horsefever.HorseFeverEvent;
import horsefever.Scommessa;

public class TextView implements View{

	@Override
	public String[] chiediScommessa() {
		boolean buonfine = false;
		String[] scommessa = null;
		
		
		while(buonfine==false){

    		System.out.println("Inserisci l'importo da scommettere ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[0]=(""+myInput.readLine());
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
	
		while(buonfine==false){	
	    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi scommettere: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[1]=(""+myInput.readLine());
    			 
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

    		System.out.println("Vuoi scommettere piazzato (P) o vincente (V)?");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[2]=myInput.readLine();
    			if(scommessa[2]!="V" && scommessa[2]!="P"){
    	    	
    				System.out.println("Errore, scegli P o V ");
    				buonfine=false;
    			}
            
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	
    	}	
		return scommessa;
	}

	public String[] chiediSecondaScommessa(){
		boolean buonfine=false;
		String[] scommessa = null;
		String risposta = "/";
		while(buonfine==false){
    		
    		System.out.println("Vuoi scommettere ancora?? (S/N)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			risposta= myInput.readLine();
    		}
    		catch(IOException e){
        		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		
    		}
		if (risposta =="S") scommessa = chiediScommessa();
		if (risposta == "N") scommessa[2] = "N";
		return scommessa;
	}
	
	
	@Override
	public int[] chiediTrucca() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notify(HorseFeverEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
