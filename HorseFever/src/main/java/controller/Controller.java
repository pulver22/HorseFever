package controller;

import java.util.ArrayList;
import java.util.Arrays;
import adapter.Adapter;
import eventi.*;

import horsefever.*;
import View.*;

public class Controller {
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare=new ArrayList<Azione>();
	private Adapter adapter;
	private HorseFeverEvent e;
	
	
	
	/**
	 * Il costruttore di Controller riceve in ingresso Partita per poter utilizzare tutti i dati del Model
	 *
	 */
	public Controller(Partita par){
		
		this.partita=par;
	}
	
	public void start(){
		int numturni=partita.getNumturni();
		
		partita.preparazione();
		
		for (int i=0; i<numturni; i++){	
			
			this.FaseDistribuzioneCarte();
			this.FaseScommesse();
			this.FaseCorsa();
			this.FaseFineTurno();
		}
		partita.FinePartita();
		
	}
	
	/**
	 * Per ciascun giocatore pesca due carte azione dal mazzo delle carte azione 
	 * e le assegna alle carte azione a disposizione del giocatore
	 */
	public void FaseDistribuzioneCarte(){
		
		for(int i=0; i<partita.getGiocatori().size();i++){
			
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
			scom=Scommetti(giocatoreCorrente,1,numSegnalini,i);
			numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
		numSegnalini=partita.getNumSegnalini();
        
		for(int i=partita.getNumgiocatori()-1; i>=0;i--){
			
        	giocatoreCorrente=partita.getGiocatori(i);
			scom=Scommetti(giocatoreCorrente,2,numSegnalini,i);
        	numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
        
        
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			Trucca(giocatoreCorrente,i);
			Trucca(giocatoreCorrente,i);
		}
        
	}
	
	/**
	 * Fino a che tutti i cavalli non hanno raggiunto il traguardo pesca una carta movimento dal mazzo movimento
	 * calcola l'incremento di posizione che deve subire ciascun cavallo in base alla carta movimento
	 * Quando tutti i cavalli hanno raggiunto il traguardo invoca il BetManager che si occupa del pagamento delle scommesse
	 * Successivamente chiede alla Lavagna di aggiornare le quotazioni in base all'ordine di arrivo
	 */
	public void FaseCorsa(){
		
		partita.getPlancia().applicaAzioni();
		while(partita.getPlancia().tuttiArrivati()==false){
			
			partita.getPlancia().muovi();
			
			
		}
		
		String[] ordineArrivo = partita.getPlancia().getColoriArrivi();
		String[][] quotazioni= partita.getLavagna().getQuotazioni();
		
		partita.getBetManager().Pagamenti(ordineArrivo,quotazioni,partita.getGiocatori());
		partita.getLavagna().ricalcolaQuotazioni(ordineArrivo);
		
	}
	
	/**
	 * Chiama il reset di Partita, che resetta i Mazzi e la plancia alla situazione iniziale
	 * e cambia il primo giocatore. 
	 */
	public void FaseFineTurno(){
		
		partita.reset();
		

	}
	

	
    
    /**
	 *  @param numscommessa 1:obbligatoria 2:facoltativa
	 *  @param giocatore il giocatore che effettua la scommessa
	 *  @param numSegnalini è un array che indica quanti segnalini sono rimasti per ciascuna corsia 
	 *  @param l'indice del giocatore corrente 
	 *  Viene verificato se la scommessa è obbligatoria o meno, in tal caso viene chiesto al giocatore
	 *  se vuole scommettere oppure no, in caso di risposta affermativa viene chiesto al giocatore 
	 *  il numero di corsia, l'importo e il tipo di scommessa che vuole effettare
	 *  @return scommessa 
	 */
    public Scommessa Scommetti(Giocatore giocatore,int numScommessa,int[] numSegnalini, int indice){
       
    	
    	int PV, numCorsia=0, numCorsiaPrecedente=0;
    	long denari,importo,scommessaMinima;
    	char tipoScommessa='N',tipoScommessaPrecedente='N';
    	boolean buonfine=false;
    	String messaggio;
    	String[] parametriScommessa=new String[3];
    	Scommessa scommessa;
    	BetManager bManager=partita.getBetManager();
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
    			
    			parametriScommessa=adapter.chiediSecondaScommessa(indice);
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
    				
    				adapter.stampaMessaggio("Errore, non puoi fare due scommesse identiche !!",indice);
    				buonfine=false;
    				
    			}
    			
    			
    			if(importo>denari || importo<scommessaMinima || numCorsia>5 || numCorsia<0 || (tipoScommessa!='P' && tipoScommessa!='V')){
    				
    				adapter.stampaMessaggio("Parametri non corretti !!",indice);
    				buonfine=false;
    			}
    				
    		}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
			
    		return scommessa;
      	
    	}
    	/*  Se la scommessa è obbligatoria e il giocatore non ha abbastanza denari
 	   gli vengono sottratti 2 PV, se non ha 2 PV il giocatore perde la partita */
    	if(denari<scommessaMinima){
    		
            if(PV<2){   
            	
            	messaggio="Hai perso la partita";
            	adapter.stampaMessaggio(messaggio,indice);
            	partita.rimuoviGiocatore(giocatore);
            	scommessa=new Scommessa(giocatore,10,0,'N');
        		return scommessa;
            }   
            else{
            	 messaggio="Non hai abbastanza denari per scommettere! Perdi due PV!";
            	 adapter.stampaMessaggio(messaggio,indice);
            	 PV=PV-2;
    		     giocatore.setPV(PV);
    		     
    		     scommessa=new Scommessa(giocatore,10,0,'N');
           		 return scommessa;
            }
    	}
    	else{
    		
    		while(buonfine==false){
    			
    			parametriScommessa=adapter.chiediScommessa(indice);
    			buonfine=true;
    			
    			importo=Long.parseLong(parametriScommessa[0]);
    			numCorsia=Integer.parseInt(parametriScommessa[1]);
    			numCorsia--;
    			tipoScommessa=parametriScommessa[2].charAt(0);
    	    
    			if(importo>denari || importo<scommessaMinima || numCorsia>5 || numCorsia<0 || (tipoScommessa!='P' && tipoScommessa!='V')){
    				
    				adapter.stampaMessaggio("Parametri non corretti !!",indice);
    				buonfine=false;
    			}
    				
        	}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
			
    		return scommessa;
    	}
    }
    
    /**
     * Viene chiesto al giocatore quale delle sue carte azione vuole giocare e su quale corsia
     * applicarla, queste informazioni vengono poi passate al metodo TruccaCorsia in plancia
     * La carta giocata viene infine rimossa dalle carte a disposizione del giocatore
     */
    public void Trucca(Giocatore giocatore, int indice){
        
    	ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
    	carteAzione=giocatore.getCarteAzione();
    	String[] scelta = new String[2];
    	String cartaAzioneGiocata,nomeGiocatore=giocatore.getNome();
    	boolean buonfine = false;
    	int numCartaAzione=0,numCorsia=0;
    	
    	while(buonfine==false){
			
			scelta=adapter.chiediTrucca(carteAzione,indice);
			buonfine=true;
			
			numCartaAzione=Integer.parseInt(scelta[0]);
			numCartaAzione--;
			numCorsia=Integer.parseInt(scelta[1]);
			numCorsia--;
	    
			if(numCartaAzione>1 || numCartaAzione<0 || numCorsia>5 || numCorsia<0)
				buonfine=false;
    	}
    	
    	cartaAzioneGiocata=carteAzione.get(numCartaAzione).toString();
    	partita.getPlancia().TruccaCorsia(carteAzione.get(numCartaAzione), numCorsia,giocatore.getNome());
    	
    	
    	carteAzione.remove(numCartaAzione);
        giocatore.setCarteAzione(carteAzione);	
        
        
    }
    
    public void setAdapter(Adapter a){
    	this.adapter=a;
    }
    
    
}
