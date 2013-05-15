package horsefever;
import java.util.ArrayList;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare;
	final int FINE_CORSA=13;
	private int[]  posizioniAggiornate=new int[6];
	
	public Turno(Partita par){
		
		this.partita=par;
	}
	
	public void start(){
		
	}
	
	public void FaseDistribuzioneCarte(){
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
			carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
			partita.getGiocatori(i).setCarteAzione(carteDaAssegnare);
			
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
			
			Movimento cartamov=(Movimento) partita.getMazzoMovimento().pesca();
			IncrementaPos(cartamov);
			DadiSprint();
			
		}
		
		//BetManager.Pagamenti(partita.getPlancia().getOrdineArrivo());
		partita.getLavagna().ricalcolaQuotazioni(partita.getPlancia().getOrdineArrivo());
		
	}
	
	public void FaseFineTurno(){
		
	}
	
    public boolean Arrivati(int[] posizioni){
    	
    	for(int i=0;i<6;i++){
    	
    	if(posizioni[i]<FINE_CORSA) return false;
    	
    	}
    	return true;
    }
    public void IncrementaPos(Movimento movimento){
    	int j,i;
    	
    	for(i=0;i<6;i++){ 
    		posizioniAggiornate[i]=0;
    	}
    	
    	for(i=0;i<6;i++){
    	
        j=partita.getLavagna().getRigaMovimento(i);
        posizioniAggiornate[i]=movimento.getMovimento(j);
    	
    	}
    	
    	partita.getPlancia().AggiornaPosizione(posizioniAggiornate);
    }
    public void DadiSprint(){
    	
    	int i,j;
    	
    	for(i=0;i<6;i++){ 
    		posizioniAggiornate[i]=0;
    	} 
    	
    	j = (int) (Math.random() * 6);
    	posizioniAggiornate[j]++;
   		j = (int) (Math.random() * 6);
   		posizioniAggiornate[j]++;
   		partita.getPlancia().AggiornaPosizione(posizioniAggiornate);
 
    }
    
}
