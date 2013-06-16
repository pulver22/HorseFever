package View;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import adapter.AdapterReteServer;

import eventi.eventoArrivi;
import eventi.eventoCorsa;
import eventi.eventoGiocatore;
import eventi.eventoQuotazioni;

public class ThreadCorsa extends Thread{

	private static final int NUM_CORSIE=6;
	private static final int PRIMO=1;
	private static final int SECONDO=2;
	private static final int TERZO=3;
	
	
	private boolean stop;
	private GUIView vista;
	private eventoCorsa evento;
	private int[] posizioniAggiornate;
	private int[] esitoDadi;
	private String immagineMovimento;
	private Board board;
	private int i=0;
	private boolean inserito=false;
	
	private static final Logger log = Logger.getLogger(AdapterReteServer.class.getName());
	
	/**
	 * Inizializza il thread Corsa.
	 * @param la GUIView di riferimento.
	 * */
	public ThreadCorsa(GUIView vista){
		
		this.vista=vista;
		this.board=vista.getBoard();
		this.stop=false;
	}

	/**
	 * Chiama il metodo muovi per ogni pedina, passandogli la posizione che deve assumere 
	 * @param posizioni vettore di posizioni aggiornate
	 */
	public void aggiornaPosizioni(int[] posizioni){
		for(i=0; i<posizioni.length; i++){
			board.getPedina(i).muovi(posizioni[i]);
		}
	}
	/***/
	@Override
	public void run() {
		
          while(!stop){
        	  
        	  evento=vista.getEventoCorsa();
        	  
        	  if(evento!=null){
        		          		
               esitoDadi=evento.getEsitoDadi();
           	   immagineMovimento=evento.getImmagineMovimento();
           	   posizioniAggiornate=evento.getPosizioniAggiornate();
           	   board.settaAreaNotifica("\nEsito dadi= "+Arrays.toString(esitoDadi)); 
           	   board.setImmagineMovimento(immagineMovimento);
        	   aggiornaPosizioni(posizioniAggiornate);
        	 
        	   try {
        		   board.repaint();
        		   Thread.sleep(2800);
        	   } catch (InterruptedException e) {
				
        		  log.log(Level.SEVERE, e.getMessage(), e);
        	   }
        		  
           }
        	
           //permette di far risparmiare il calcolo del tutteArrivate quando non servirebbe
           if(vista.getEventiCorsa().size()==0){
        	   
           
        	   //Verifica se tutte le pedine sono arrivate
        	   for(i=0; i<NUM_CORSIE;i++){

        		   board.setTutteArrivate(true);
        		   if(!board.getPedina(i).getArrivata()){

        			   board.setTutteArrivate(false);
        		   }
        	   }

        	   // animazione finita
        	   if(board.getTutteArrivate()){

        		   eventoQuotazioni e1=vista.getEventoQuotaz();
        		   String[][] tabellaQuot= e1.getTabellaQuot();
        		   String[] quot=new String[NUM_CORSIE];

        		   for(i=0;i<NUM_CORSIE;i++){

        			   quot[i]=tabellaQuot[i][1];
        		   }

        		   board.settaAreaQuotazioni(quot);

        		   for(i=0; i<NUM_CORSIE;i++){

        			   eventoArrivi e=vista.getEventoArrivi();

        			   int posArrivo=e.getPosArrivo();
        			   String cavallo=e.getCavallo();
        			   String rappresentazione=e.rappresentazione();
        			   int numCorsia=10;

        			   if(cavallo.equals("Nero")){ numCorsia=1; }
        			   else if(cavallo.equals("Blu")){ numCorsia=2; }
        			   else if(cavallo.equals("Verde")){ numCorsia=3; }
        			   else if(cavallo.equals("Rosso")){ numCorsia=4; }
        			   else if(cavallo.equals("Giallo")){ numCorsia=5; }
        			   else if(cavallo.equals("Bianco")){ numCorsia=6; }

        			   board.settaAreaNotifica("\n"+rappresentazione);
        			   if(posArrivo==PRIMO || posArrivo==SECONDO || posArrivo==TERZO){

        				   board.stampaPiazzamento(numCorsia,posArrivo);
        				   board.repaint();
        			   }   

        		   }
        		   
        		   while(vista.getEventiPagamento().size()>0){
        			   
        			   eventoGiocatore e=vista.getEventoPagamento();
        			   inserito=false;
        			   i=0;
        			   
        			   String nomeGioc=((eventoGiocatore) e).getNome();
        	           long denari=((eventoGiocatore) e).getDenari();
        	    	   int pv=((eventoGiocatore) e).getPv();
        	    	   String scuderia=((eventoGiocatore) e).getScuderia();
        	    	   
        			   while(!inserito){
                		   
                		   if(board.getNomeGiocatore(i).equals(nomeGioc)){
                			   
                			   inserito=true;
                			   board.setNomeGiocatore(nomeGioc, i);
                			   board.setPV(pv,i);
                			   board.setDenari(denari, i);
                			   board.setNomeScuderia(scuderia, i);
                		   }
                		   i++;
                	   }   
        			   
        		   }

        		   this.stop=true;
        		   vista.setFirst(true);
        	   }
           }
      }
		
	}

	/**
	 * termina il run del thread uscendo dal ciclo while
	 * @param stop
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
