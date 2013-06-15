package controller;

import java.util.ArrayList;
import adapter.Adapter;

import horsefever.*;

public class Controller {
	
	private final static int MOLTIPLICATORE=100;
	private final static int PV_MIN=2;
	private final static int DIECI=10;
	
	
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare=new ArrayList<Azione>();
	private Adapter adapter;
	private boolean finePar=false;
	
	private boolean debug=false;
	

	/**
	 * Per il numero di turni del gioco, svolge tutte le attività di gioco e alla fine conclude la partita
	 */
	public void start(){
		
		int numturni=partita.getNumturni();
		String nomeGiocatore;
		
		partita.preparazione();
		
		int numGioc=partita.getNumgiocatori();
		for(int i=0;i<numGioc;i++){
			
			nomeGiocatore=partita.getGiocatori(i).getNome();
			adapter.evidenziaGiocatore(nomeGiocatore, i);
		}
		
		for (int i=0; i<numturni; i++){	
			
			partita.setTurnoAttuale(i+1);
			this.faseDistribuzioneCarte();
			this.faseScommesse();
			if(finePar){
				 break;
			}
			this.faseCorsa();
			this.faseFineTurno();
			
		}
		partita.finePartita();
		
	}
	
	/**
	 * Per ciascun giocatore pesca due carte azione dal mazzo delle carte azione 
	 * e le assegna alle carte azione a disposizione del giocatore
	 */
	public void faseDistribuzioneCarte(){
		for(int i=0; i<partita.getGiocatori().size();i++){
			carteDaAssegnare=new ArrayList<Azione>();
			if (debug){
				carteDaAssegnare.add(new Azione("Rochelle Recherche","Grigio","Azione","Rimuovi_positive",'Z',17));
				carteDaAssegnare.add(new Azione("Friz Finden","Grigio","Azione","Rimuovi_negative",'X',15));
			}else{
				carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
				carteDaAssegnare.add((Azione) partita.getMazzoAzione().pesca());
			}
			partita.getGiocatori(i).setCarteAzione(carteDaAssegnare);
			
		}
		adapter.prosegui("Sono state distribuite le carte", 0);
	}
	
	/**
	 * Ciascuno dei seguenti metodi:
	 * - Scommetti con parametro prima scommessa (obbligatoria)
	 * - Scommetti con parametro seconda scommessa (facoltativa)
	 * - Trucca 
	 * Viene chiamato per ciascun giocatore seguendo per il primo e il terzo un giro orario 
	 * partendo dal primo giocatore e per il secondo un giro antiorario
	 */
	public void faseScommesse(){
		
		Scommessa scom;
		Giocatore giocatoreCorrente;
		int[] numSegnalini=partita.getNumSegnalini();
		int numcorsia,i;
		
		for(i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			scom=scommetti(giocatoreCorrente,1,numSegnalini,i);
			if(scom.getTipoScomessa()=='F'){
				return;
			}
			numcorsia=scom.getCorsia();
			if (scom.getTipoScomessa()!='N'){
				numSegnalini[numcorsia]--;
			}
			partita.getBetManager().AggiungiScommessa(scom);
		}
	    
		for(i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			trucca(giocatoreCorrente,i);
			
		}
		
		for(i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			trucca(giocatoreCorrente,i);
			
		}
		numSegnalini=partita.getNumSegnalini();
        
		for(i=partita.getNumgiocatori()-1; i>=0;i--){
			
        	giocatoreCorrente=partita.getGiocatori(i);
			scom=scommetti(giocatoreCorrente,2,numSegnalini,i);
        	numcorsia=scom.getCorsia();
			if (scom.getTipoScomessa()!='N'){
				numSegnalini[numcorsia]--;
			}
			partita.getBetManager().AggiungiScommessa(scom);
		}
        
        
	
		adapter.prosegui("Sono state effettuate tutte le scommesse.", 0);
	}
	
	/**
	 * Fino a che tutti i cavalli non hanno raggiunto il traguardo pesca una carta movimento dal mazzo movimento
	 * calcola l'incremento di posizione che deve subire ciascun cavallo in base alla carta movimento
	 * Quando tutti i cavalli hanno raggiunto il traguardo invoca il BetManager che si occupa del pagamento delle scommesse
	 * Successivamente chiede alla Lavagna di aggiornare le quotazioni in base all'ordine di arrivo
	 */
	public void faseCorsa(){
		
		partita.getPlancia().applicaAzioni();
		while(!partita.getPlancia().tuttiArrivati()){
			
			partita.getPlancia().muovi();
			
			
		}
		
		String[] ordineArrivo = partita.getPlancia().getColoriArrivi();
		String[][] quotazioni= partita.getLavagna().getQuotazioni();
		
		partita.getBetManager().Pagamenti(ordineArrivo,quotazioni,partita.getGiocatori());
		partita.getLavagna().ricalcolaQuotazioni(ordineArrivo);
		
		adapter.prosegui("E' terminata la fase di corsa.", 0);
	}
	
	/**
	 * Chiama il reset di Partita, che resetta i Mazzi e la plancia alla situazione iniziale
	 * e cambia il primo giocatore. 
	 */
	public void faseFineTurno(){
		
		partita.generaEventoResetGrafico();
		
		partita.reset();
		
		adapter.prosegui("Fine del turno", 0);
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
    public Scommessa scommetti(Giocatore giocatore,int numScommessa,int[] numSegnalini, int indice){
       
    	
    	int pv, numCorsia=0, numCorsiaPrecedente=0;
    	long denari,importo;
    	int scommessaMinima;
    	char tipoScommessa='N',tipoScommessaPrecedente='N';
    	boolean buonfine=false;
    	String messaggio;
    	String[] parametriScommessa=new String[3];
    	Scommessa scommessa;
    	BetManager bManager=partita.getBetManager();
    	ArrayList<Scommessa> scommesse=new ArrayList<Scommessa>();
    	
    	importo=0;
    	pv=giocatore.getPV();
    	denari=giocatore.getDenari();
    	scommessaMinima=pv*MOLTIPLICATORE;
    	
    	/* se la scommessa non è obbligatoria chiama seconda scommessa che chiede prima all'utente 
    	 * se vuole ancora scommettere, se i denari sono insufficienti per la scommessa minima 
    	 * in ogni caso il giocatore non scommette
    	 */
    	if(numScommessa==2){
    		
    		while(!buonfine){
    			adapter.stampaMessaggio("Il giocatore "+giocatore.getNome()+" deve fare una scelta.", indice);
    			parametriScommessa=adapter.chiediSecondaScommessa(indice);
    			buonfine=true;
    			
    			if(denari<scommessaMinima || parametriScommessa[2].equals("N")){
    	
    				scommessa=new Scommessa(giocatore,DIECI,0,'N');
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
    			
                if(importo>denari || importo<scommessaMinima || numCorsia>5 || numCorsia<0 || (tipoScommessa!='P' && tipoScommessa!='V' && buonfine)){
    				
    				adapter.stampaMessaggio("Parametri non corretti !!",indice);
    				buonfine=false;
    			}

    			if(numCorsia==numCorsiaPrecedente && tipoScommessaPrecedente==tipoScommessa && buonfine){
    				
    				adapter.stampaMessaggio("Errore, non puoi fare due scommesse identiche !!",indice);
    				buonfine=false;
    				
    			}
    			
    			if(numSegnalini[numCorsia]<=0 && buonfine){
    				
    				adapter.stampaMessaggio("Scommesse esaurite su corsia "+(numCorsia+1)+" !!",indice);
    				buonfine=false;
    			}	
    				
    		}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
			giocatore.setDenari(denari-importo);
    		return scommessa;
      	
    	}
    	/*  Se la scommessa è obbligatoria e il giocatore non ha abbastanza denari
 	   gli vengono sottratti 2 PV, se non ha 2 PV il giocatore perde la partita */
    	if(denari<scommessaMinima){
    		
            if(pv<PV_MIN){   
            	
            	messaggio=""+giocatore.getNome() +" hai perso la partita";
            	adapter.stampaMessaggio(messaggio,indice);
            	partita.rimuoviGiocatore(giocatore);
            	
            	if(partita.getGiocatori().size()==1){
        			finePar=true;
        			scommessa=new Scommessa(giocatore,DIECI,0,'F');
        			return scommessa;
        		}
            	scommessa=new Scommessa(giocatore,DIECI,0,'N');
        		return scommessa;
            }   
            else{
            	 messaggio="Non hai abbastanza denari per scommettere! Perdi due PV!";
            	 adapter.stampaMessaggio(messaggio,indice);
            	 pv=pv-2;
    		     giocatore.setPV(pv);
    		     
    		     scommessa=new Scommessa(giocatore,DIECI,0,'N');
           		 return scommessa;
            }
    	}
    	else{
    		
    		while(!buonfine){
    			adapter.stampaMessaggio("Il giocatore "+giocatore.getNome()+" deve fare una scelta.", indice);
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
    			
                if(numSegnalini[numCorsia]<=0 && buonfine){
    				
    				adapter.stampaMessaggio("Scommesse esaurite su corsia "+(numCorsia+1)+" !!",indice);
    				buonfine=false;
    			}
    			
    				
        	}
    		scommessa=new Scommessa(giocatore,numCorsia,importo,tipoScommessa);
    		giocatore.setDenari(denari-importo);
    		return scommessa;
    	}
    }
    
    /**
     * Viene chiesto al giocatore quale delle sue carte azione vuole giocare e su quale corsia
     * applicarla, queste informazioni vengono poi passate al metodo TruccaCorsia in plancia
     * La carta giocata viene infine rimossa dalle carte a disposizione del giocatore
     * @param il Giocatore
     * @param l'indice della carta
     */
    public void trucca(Giocatore giocatore, int indice){
        
    	ArrayList<Azione> carteAzione;
    	carteAzione=giocatore.getCarteAzione();
    	String[] scelta;
    	boolean buonfine = false;
    	int numCartaAzione=0,numCorsia=0;
    	
    	while(!buonfine){
    		adapter.stampaMessaggio("Il giocatore "+giocatore.getNome()+" deve fare una scelta.", indice);
			scelta=adapter.chiediTrucca(carteAzione,indice);
			buonfine=true;
			
			numCartaAzione=Integer.parseInt(scelta[0]);
			numCartaAzione--;
			numCorsia=Integer.parseInt(scelta[1]);
			numCorsia--;
	    
			if(numCartaAzione>1 || numCartaAzione<0 || numCorsia>5 || numCorsia<0){
				buonfine=false;
			}
				
    	}
    	
    	partita.getPlancia().truccaCorsia(carteAzione.get(numCartaAzione), numCorsia,giocatore.getNome());
    	
    	
    	carteAzione.remove(numCartaAzione);
        giocatore.setCarteAzione(carteAzione);	
        
        
    }
    
    /**
     * Setta al controller la partita corrente
     * @param la partita corrente
     * */
    public void setPartita(Partita p){
    	this.partita=p;
    }
    
    /**
     * Seleziona l'adapter giusto a seconda si giochi in locale o remoto
     * @param a
     */
    public void setAdapter(Adapter a){
    	this.adapter=a;
    }
    
    /**
     * Rimuove giocatore non più raggiungibile in rete
     * */
    public void rimuoviGiocatoreIrraggiungibile(int i){
    	partita.rimuoviGiocatore(partita.getGiocatori(i));
    }
    
    
    /**
     * Setta il controller in stato di debug
     * */
    public void setDebug(boolean debug){
    	this.debug=debug;
    }
    
}
