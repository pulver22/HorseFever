package controller;

import java.util.ArrayList;
import java.util.Arrays;
import adapter.Adapter;
import eventi.*;

import horsefever.*;
import View.*;

public class Controller {
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare;
	private int[]  posizioniAggiornate=new int[6];
	private Adapter adapter;
	private View vista;
	private HorseFeverEvent e;
	
	
	
	/**
	 * Il costruttore di Controller riceve in ingresso Partita per poter utilizzare tutti i dati del Model
	 *
	 */
	public Controller(Partita par){
		
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
	 * - Scommetti con parametro seconda scommessa (facoltativa)
	 * - Trucca 
	 * Viene chiamato per ciascun giocatore seguendo per il primo e il terzo un giro orario 
	 * partendo dal primo giocatore e per il secondo un giro antiorario
	 */
	public void FaseScommesse(){
		
		Scommessa scom;
		Giocatore giocatoreCorrente;
		int[] numSegnalini=partita.getNumSegnalini();
		int numcorsia;
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			scom=Scommetti(giocatoreCorrente,1,numSegnalini,partita);
			numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
		numSegnalini=partita.getNumSegnalini();
        
		for(int i=partita.getNumgiocatori()-1; i>=0;i--){
			
        	giocatoreCorrente=partita.getGiocatori(i);
			scom=Scommetti(giocatoreCorrente,2,numSegnalini,partita);
        	numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
        
        
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			Trucca(giocatoreCorrente);
			Trucca(giocatoreCorrente);
		}
        
	}
	
	/**
	 * Fino a che tutti i cavalli non hanno raggiunto il traguardo pesca una carta movimento dal mazzo movimento
	 * calcola l'incremento di posizione che deve subire ciascun cavallo in base alla carta movimento
	 * Quando tutti i cavalli hanno raggiunto il traguardo invoca il BetManager che si occupa del pagamento delle scommesse
	 * Successivamente chiede alla Lavagna di aggiornare le quotazioni in base all'ordine di arrivo
	 */
	public void FaseCorsa(){
		
		while(partita.getPlancia().tuttiArrivati()==false){
			
			Movimento cartamov=(Movimento) partita.getMazzoMovimento().pesca();
			
			posizioniCartaMov(cartamov);
			
			
		}
		System.out.println(Arrays.toString(partita.getPlancia().getColoriArrivi()));
		String[] ordineArrivo = partita.getPlancia().getColoriArrivi();
		String[][] quotazioni= partita.getLavagna().getQuotazioni();
		
		//NOTIFICA EVENTO
		e = new eventoQuotazioni(partita.getLavagna().getQuotazioni());
		partita.getAdapter().notify(e);	
		
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
     * Chiede a lavagna i valori correnti delle quotazioni dei cavalli e, in base alla carta movimento pescata
     * costruisce l'array che indica di quanto deve avanzare ciascun cavallo senza effetti delle carte azione
     * Questo array viene poi passato alla plancia che aggiorna i valori delle posizioni effettive dei cavalli
     * @param carta movimento
     */
    public void posizioniCartaMov(Movimento movimento){
    	int j,i;
    	
    	for(i=0;i<6;i++){ 
    		posizioniAggiornate[i]=0;
    	}
    	
    	for(i=0;i<6;i++){
    	
        j=partita.getLavagna().getRigaMovimento(i);
        posizioniAggiornate[i]=movimento.getMovimento(j);
    	
    	}
    	//System.out.println("Posizioni Aggiornate:");
    	//System.out.println(Arrays.toString(posizioniAggiornate));
    	partita.getPlancia().muovi(posizioniAggiornate);
    	//System.out.println("Posizioni Cavalli:");
    	//System.out.println(Arrays.toString(partita.getPlancia().getPosizioniCavalli()));
    }
    
    /**
	 *  @param numscommessa 1:obbligatoria 2:facoltativa
	 *  @param giocatore il giocatore che effettua la scommessa
	 *  @param numSegnalini è un array che indica quanti segnalini sono rimasti per ciascuna corsia  
	 *  Viene verificato se la scommessa è obbligatoria o meno, in tal caso viene chiesto al giocatore
	 *  se vuole scommettere oppure no, in caso di risposta affermativa viene chiesto al giocatore 
	 *  il numero di corsia, l'importo e il tipo di scommessa che vuole effettare
	 *  @return scommessa 
	 */
    public Scommessa Scommetti(Giocatore giocatore,int numScommessa,int[] numSegnalini,Partita par){
       
    	
    	int PV, numCorsia=0, numCorsiaPrecedente=0;
    	long denari,importo,scommessaMinima;
    	char tipoScommessa='N',tipoScommessaPrecedente='N';
    	boolean buonfine=false;
    	String messaggio;
    	String[] parametriScommessa=new String[3];
    	Scommessa scommessa;
    	BetManager bManager=par.getBetManager();
    	ArrayList<Scommessa> scommesse=new ArrayList<Scommessa>();
    	
    	importo=0;
    	PV=giocatore.getPV();
    	denari=giocatore.getDenari();
    	scommessaMinima=PV*100;
    	
    	/* se la scommessa non è obbligatoria chiama seconda scommessa che chiede prima all'utente 
    	 * se vuole ancora scommettere, se i denari sono insufficienti per la scommessa minima 
    	 * in ogni caso il giocatore non scommette
    	 */
    	if(numScommessa==2){
    		
    		while(buonfine==false){
    			
    			parametriScommessa=vista.chiediSecondaScommessa();
    			buonfine=true;
    			
    			if(denari<scommessaMinima || parametriScommessa[2].equals("N")){
    	
    				scommessa=new Scommessa(giocatore,10,0,'N');
    				return scommessa;
    		
    			}
    		
    			importo=Long.parseLong(parametriScommessa[0]);
    			numCorsia=Integer.parseInt(parametriScommessa[1]);
    			numCorsia--;
    			tipoScommessa=parametriScommessa[2].charAt(0);
    		
    			//Scommessa precedente
    			scommesse=bManager.getbManager();
    			for(int i=0;i<partita.getNumgiocatori();i++){
    				
    				if(scommesse.get(i).getScommettitore().equals(giocatore))
    				{
    					numCorsiaPrecedente=scommesse.get(i).getCorsia();
    					tipoScommessaPrecedente=scommesse.get(i).getTipoScomessa();
    				}	
    			}
    			
    			if(numCorsia==numCorsiaPrecedente && tipoScommessaPrecedente==tipoScommessa){
    				
    				vista.stampaMessaggio("Errore, non puoi fare due scommesse identiche !!");
    				buonfine=false;
    				
    			}
    			
    			
    			if(importo>denari || importo<scommessaMinima || numCorsia>5 || numCorsia<0 || (tipoScommessa!='P' && tipoScommessa!='V')){
    				
    				vista.stampaMessaggio("Parametri non corretti !!");
    				buonfine=false;
    			}
    				
    		}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
    		// NOTIFICA EVENTO
    		e=new eventoScommessa(scommessa);
			adapter.notify(e);
			
    		return scommessa;
      	
    	}
    	/*  Se la scommessa è obbligatoria e il giocatore non ha abbastanza denari
 	   gli vengono sottratti 2 PV, se non ha 2 PV il giocatore perde la partita */
    	if(denari<scommessaMinima){
    		
            if(PV<2){   
            	
            	messaggio="Hai perso la partita";
            	vista.stampaMessaggio(messaggio);
            	partita.rimuoviGiocatore(giocatore);
            	scommessa=new Scommessa(giocatore,10,0,'N');
        		return scommessa;
            }   
            else{
            	 messaggio="Non hai abbastanza denari per scommettere! Perdi due PV!";
            	 vista.stampaMessaggio(messaggio);
            	 PV=PV-2;
    		     giocatore.setPV(PV);
    		     
    		     //NOTIFICA EVENTO
    		     e=new eventoGiocatore(giocatore);
    		     adapter.notify(e);
    		     
    		     scommessa=new Scommessa(giocatore,10,0,'N');
           		 return scommessa;
            }
    	}
    	else{
    		
    		while(buonfine==false){
    			
    			parametriScommessa=vista.chiediScommessa();
    			buonfine=true;
    			
    			importo=Long.parseLong(parametriScommessa[0]);
    			numCorsia=Integer.parseInt(parametriScommessa[1]);
    			numCorsia--;
    			tipoScommessa=parametriScommessa[2].charAt(0);
    	    
    			if(importo>denari || importo<scommessaMinima || numCorsia>5 || numCorsia<0 || (tipoScommessa!='P' && tipoScommessa!='V')){
    				
    				vista.stampaMessaggio("Parametri non corretti !!");
    				buonfine=false;
    			}
    				
        	}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
    		
    		// NOTIFICA EVENTO
    		e=new eventoScommessa(scommessa);
			adapter.notify(e);
			
    		return scommessa;
    	}
    }
    
    /**
     * Viene chiesto al giocatore quale delle sue carte azione vuole giocare e su quale corsia
     * applicarla, queste informazioni vengono poi passate al metodo TruccaCorsia in plancia
     * La carta giocata viene infine rimossa dalle carte a disposizione del giocatore
     */
    public void Trucca(Giocatore giocatore){
        
    	ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
    	carteAzione=giocatore.getCarteAzione();
    	String[] scelta = new String[2];
    	String cartaAzioneGiocata,nomeGiocatore=giocatore.getNome();
    	boolean buonfine = false;
    	int numCartaAzione=0,numCorsia=0;
    	
    	while(buonfine==false){
			
			scelta=vista.chiediTrucca(carteAzione);
			buonfine=true;
			
			numCartaAzione=Integer.parseInt(scelta[0]);
			numCartaAzione--;
			numCorsia=Integer.parseInt(scelta[1]);
			numCorsia--;
	    
			if(numCartaAzione>1 || numCartaAzione<0 || numCorsia>5 || numCorsia<0)
				buonfine=false;
    	}
    	
    	cartaAzioneGiocata=carteAzione.get(numCartaAzione).toString();
    	partita.getPlancia().TruccaCorsia(carteAzione.get(numCartaAzione), numCorsia);
    	
    	// NOTIFICA EVENTO
    	e=new eventoTrucca(nomeGiocatore,numCorsia,cartaAzioneGiocata);
    	adapter.notify(e);
    	
    	carteAzione.remove(numCartaAzione);
        giocatore.setCarteAzione(carteAzione);	
        
        //NOTIFICA EVENTO
        e=new eventoGiocatore(giocatore);
        adapter.notify(e);
    }
}
