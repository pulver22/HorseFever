package View;

import java.util.Arrays;

import eventi.eventoArrivi;
import eventi.eventoCorsa;

public class ThreadCorsa extends Thread{

	private boolean stop=false;
	private GUIView vista;
	private eventoCorsa evento;
	private int[] posizioniAggiornate;
	private int[] valoriMovimento;
	private int[] esitoDadi;
	private String immagineMovimento;
	private Board board;
	
	public ThreadCorsa(GUIView vista){
		
		this.vista=vista;
		this.board=vista.getBoard();		
	}

	public void aggiornaPosizioni(int[] posizioni){
		for(int i=0; i<6; i++){
			board.getPedina(i).muovi(posizioni[i]);
		}
	}
	
	@Override
	public void run() {
		
          while(stop==false){
        	  
        	  evento=vista.getEventoCorsa();
        	  board.settaAreaNotifica("");
        	  
        	  if(evento!=null){
        		  
        		  posizioniAggiornate=evento.getPosizioniAggiornate();
           	      valoriMovimento=evento.getValoriMovimento();
           	      esitoDadi=evento.getEsitoDadi();
           	      immagineMovimento=evento.getImmagineMovimento();
           	      
           	   board.settaAreaNotifica("Eisto dadi= "+Arrays.toString(esitoDadi)+"\n");   
           	   board.setImmagineMovimento(immagineMovimento);
        	   aggiornaPosizioni(posizioniAggiornate);
        	   

        	   if(vista.getArrivati().size()==6){
        		   
        		   
        		   for(int i=0; i<6;i++){
        			   
        		   eventoArrivi e=vista.getArrivati().get(i);
        		   
        		   int posArrivo=e.getPosArrivo();
            	   String cavallo=e.getCavallo();
            	   String rappresentazione=e.rappresentazione();
            	   int numCorsia=10;
            	   
            	   if(cavallo.equals("Nero")) numCorsia=1;
            	   if(cavallo.equals("Blu")) numCorsia=2;
            	   if(cavallo.equals("Verde")) numCorsia=3;
            	   if(cavallo.equals("Rosso")) numCorsia=4;
            	   if(cavallo.equals("Giallo")) numCorsia=5;
            	   if(cavallo.equals("Bianco")) numCorsia=6;
            	   
            	   if(board.getPedina(i).getArrivata()==true){
            		   
            		 board.settaAreaNotifica(""+rappresentazione+"\n");
           	   		if(posArrivo==1 || posArrivo==2 || posArrivo==3){
           		   
           	   			board.stampaPiazzamento(numCorsia,posArrivo);
           	   		}    
            		   
            	   }
        		   
            	   }
        	   }
        	   try {
        		   Thread.sleep(3500);
        	   } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
        	   }
        		  
        	  }
        	  
      }
		
	}

}
