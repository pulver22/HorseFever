package View;

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
           	      
           	      
           	   board.setImmagineMovimento(immagineMovimento);
        	   aggiornaPosizioni(posizioniAggiornate);
        	   
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
