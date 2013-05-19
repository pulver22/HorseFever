package horsefever;
import java.util.ArrayList;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare;
	final int FINE_CORSA=13;
	private int[]  posizioniAggiornate=new int[6];
	
	
	/**
	 * Il costruttore di Turno riceve in ingresso Partita per poter utilizzare tutti i dati correnti della partita
	 *
	 */
	public Turno(Partita par){
		
		this.partita=par;
	}
	
	public void start(){
		
		this.FaseDistribuzioneCarte();
		this.FaseScommesse();
		this.FaseCorsa();
		this.FaseFineTurno();
		
	}
	
	/**
	 * Per ciascun giocatore pesca due carte azione dal mazzo delle carte azione 
	 * e le assegna alle carte azione a disposizione del giocatore
	 */
	public void FaseDistribuzioneCarte(){
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
			carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
			partita.getGiocatori(i).setCarteAzione(carteDaAssegnare);
			
		}	
	}
	
	/**
	 * Ciascuno dei seguenti metodi:
	 * - Scommetti con parametro prima scommessa (obbligatoria)
	 * - Trucca 
	 * - Scommetti con parametro seconda scommessa (facoltativa)
	 * Viene chiamato per ciascun giocatore seguendo per i primi due un giro orario partendo dal primo giocatore
	 * e per il terzo un giro antiorario
	 */
	public void FaseScommesse(){
		
		Scommessa scom;
		int[] numSegnalini=partita.getNumSegnalini();
		int numcorsia;
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			scom=partita.getGiocatori(i).Scommetti(1,numSegnalini);
			numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
		partita.resetNumSegnalini();
		numSegnalini=partita.getNumSegnalini();
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			partita.getGiocatori(i).Trucca();
		}
        for(int i=partita.getNumgiocatori(); i>0;i++){
			
        	scom=partita.getGiocatori(i).Scommetti(2,numSegnalini);
        	numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
        partita.resetNumSegnalini();
	}
	
	/**
	 * Fino a che tutti i cavalli non hanno raggiunto il traguardo pesca una carta movimento dal mazzo movimento
	 * Calcola l'incremento di posizione che deve subire ciascun cavallo in base alla carta movimento
	 * e l'incremento di posizione dovuto al lancio dei dadi sprint
	 * Quando tutti i cavalli hanno raggiunto il traguardo invoca il BetManager che si occupa del pagamento delle scommesse
	 * Successivamente chiede alla Lavagna di aggiornare le quotazioni in base all'ordine di arrivo
	 */
	public void FaseCorsa(){
		
		int[] posizioni=partita.getPlancia().getPosizione();
		
		while(Arrivati(posizioni)==false){
			
			Movimento cartamov=(Movimento) partita.getMazzoMovimento().pesca();
			IncrementaPos(cartamov);
			DadiSprint();
			
			posizioni=partita.getPlancia().getPosizione();
			
		}
		
		String[] ordineArrivo = partita.getPlancia().getOrdineArrivo();
		String[][] quotazioni= partita.getLavagna().getQuotazioni();
		
		partita.getBetManager().Pagamenti(ordineArrivo,quotazioni,partita.getGiocatori());
		partita.getLavagna().ricalcolaQuotazioni(ordineArrivo);
		
	}
	
	/**
	 * I mazzi azione e movimento vengono resettati e mischiati
	 * Il numero di turni della partita viene incrementato
	 * Se il turno corrente era l'ultimo turno viene chiamata la fine della partita
	 */
	public void FaseFineTurno(){
		
		int numeroturni=partita.getNumturni();
		numeroturni++;
		
		partita.setMazzoAzione(new Mazzo("MazzoAzione"));
		partita.getMazzoAzione().mischia();
		partita.setMazzoMovimento(new Mazzo("MazzoMovimento"));
		partita.getMazzoMovimento().mischia();
		
		partita.setNumturni(numeroturni);
	}
	
	/**
	 * Verifica se tutti i cavalli hanno raggiunto il traguardo
	 * @param array delle posizioni dei cavalli
	 * @return true o false se i cavalli sono tutti giunti al traguardo o meno
	 */
    public boolean Arrivati(int[] posizioni){
    	
    	for(int i=0;i<6;i++){
    	
    	if(posizioni[i]<FINE_CORSA) return false;
    	
    	}
    	return true;
    }
    
    /**
     * Chiede a lavagna i valori correnti delle quotazioni dei cavalli e, in base alla carta movimento pescata
     * costruisce l'array che indica di quanto deve avanzare ciascun cavallo
     * Questo array viene poi passato alla plancia che aggiorna i valori delle posizioni effettive dei cavalli
     * @param carta movimento
     */
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
    
    /**
     * Simula i dadi generando casualmente due numeri e incrementando un array nella posizione corrispondente
     * Questo array viene poi passato alla plancia che aggiorna i valori delle posizioni effettive dei cavalli
     */
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
