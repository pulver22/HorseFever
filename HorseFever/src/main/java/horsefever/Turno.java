package horsefever;
import java.util.ArrayList;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> cartedaassegnare;
	final int FINE_CORSA=10;
	private int[]  posizioniaggiornate=new int[6];
	
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
    	int j,i;
    	
    	for(i=0;i<6;i++){ 
    		posizioniaggiornate[i]=0;
    	}
    	
    	for(i=0;i<6;i++){
    	
        //j=getRigaMovimento(i);
        //posizioniaggiornate[i]=movimento.pesca().getMovimento(j);
    	
    	}
    	
    	//partita.getPlancia().AggiornaPosizione(posizioniaggiornate);
    }
    public void DadiSprint(){
    	
    	int i,j;
    	
    	for(i=0;i<6;i++){ 
    		posizioniaggiornate[i]=0;
    	} 
    	
    	j = (int) (Math.random() * 6);
    	posizioniaggiornate[j]++;
   		j = (int) (Math.random() * 6);
   		posizioniaggiornate[j]++;
   		partita.getPlancia().AggiornaPosizione(posizioniaggiornate);
 
    }
    
}
