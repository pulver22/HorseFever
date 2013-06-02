package View;

import eventi.eventoCorsa;

public class ThreadCorsa implements Runnable{

	private boolean stop=false;
	private GUIView vista;
	private eventoCorsa evento;
	private int[] posizioniAggiornate;
	private int[] valoriMovimento;
	private int[] esitoDadi;
	private String immagineMovimento;
	private Board board;
	private Thread t;
	
	public ThreadCorsa(GUIView vista){
		
		this.vista=vista;
		this.board=vista.getBoard();
		t=Thread.currentThread();
		t.start();
		
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
        	  
        	  if(evento!=null){
        		  
        		  posizioniAggiornate=evento.getPosizioniAggiornate();
           	      valoriMovimento=evento.getValoriMovimento();
           	      esitoDadi=evento.getEsitoDadi();
           	      immagineMovimento=evento.getImmagineMovimento();
           	      
           	      
           	   board.setImmagineMovimento(immagineMovimento);
        	   aggiornaPosizioni(posizioniAggiornate);
        	   
        	   try {
        		   Thread.sleep(1000);
        	   } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
        	   }
        		  
        	  }
        	  
      }
		
	}

}
