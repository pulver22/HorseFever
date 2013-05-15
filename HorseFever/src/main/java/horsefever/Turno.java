package horsefever;
import java.util.ArrayList;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> cartedaassegnare;
	final int FINE_CORSA=10;
	
	public Turno(Partita par){
		
		this.partita=par;
	}
	
	public void start(){
		
	}
	
	public void FaseDistribuzioneCarte(){
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			cartedaassegnare.add((Azione) partita.getMazzoAzione().pesca());
			cartedaassegnare.add((Azione) partita.getMazzoAzione().pesca());
			partita.getGiocatori(i).setCarteAzione(cartedaassegnare);
			
		}	
	}
	
	public void FaseScommesse(){
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			partita.getGiocatori(i).scommetti(1);
		}
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			partita.getGiocatori(i).trucca();
		}
        for(int i=partita.getNumgiocatori(); i>0;i++){
			
			partita.getGiocatori(i).scommetti(2);
		}
		
	}
	
	public void FaseCorsa(){
		
		while(Arrivati(partita.getPlancia().getPosizione())==false){
			
			IncrementaPos(partita.getMazzoMovimento());
			DadiSprint();
			
		}
		
		
		
	}
	
    public boolean Arrivati(int[] posizioni){
    	
    	for(int i=0;i<6;i++){
    	
    	if(posizioni[i]<FINE_CORSA) return false;
    	
    	}
    	return true;
    }
    public void IncrementaPos(Mazzo movimento){
    	
    	for(int i=0;i<6;i++){
    	//partita.getPlancia().AggiornaPosizione(i,movimento.pesca().getMovimento(i));
    	}
    }
    public void DadiSprint(){
    	
    	boolean fatto=false;
    	
    	while(fatto==false){
    	
    		int j = (int) (Math.random() * 6);
    		if(partita.getPlancia().AggiornaPosizione(1, j)==true)
    			fatto=true;
    	}
    	
    	fatto=false;
    	while(fatto==false){
        	
    		int j = (int) (Math.random() * 6);
    		if(partita.getPlancia().AggiornaPosizione(1, j)==true)
    			fatto=true;
    	}
    	
    }
}
