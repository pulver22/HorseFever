package horsefever;
import java.util.ArrayList;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> cartedaassegnare;
	public Turno(Partita par){
		
		this.partita=par;
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
			
			partita.getGiocatori(i).scommetti(2);
		}
		
	}
	
	public void FaseCorsa(){
		
	}
}
