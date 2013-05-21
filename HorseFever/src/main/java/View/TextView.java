package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import horsefever.HorseFeverEvent;
import horsefever.Scommessa;

public class TextView implements View{

	@Override
	public long[] chiediScommessa() {
		boolean buonfine = false;
		long[] scommessa = null;
		while(buonfine==false){

    		System.out.println("Inserisci l'importo da scommettere ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			scommessa[0]=Long.parseLong(""+myInput.readLine());
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
    			scommessa[1]=Long.parseLong(""+myInput.readLine());
    			 
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
