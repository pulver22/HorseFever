package horsefever;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Turno {
	
	private Partita partita;
	private ArrayList<Azione> carteDaAssegnare;
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
		Giocatore giocatoreCorrente;
		int[] numSegnalini=partita.getNumSegnalini();
		int numcorsia;
		
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			scom=Scommetti(giocatoreCorrente,1,numSegnalini);
			numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
		numSegnalini=partita.getNumSegnalini();
        
		for(int i=partita.getNumgiocatori()-1; i>=0;i--){
			
        	giocatoreCorrente=partita.getGiocatori(i);
			scom=Scommetti(giocatoreCorrente,2,numSegnalini);
        	numcorsia=scom.getCorsia();
			numSegnalini[numcorsia]--;
			partita.getBetManager().AggiungiScommessa(scom);
		}
        
        
		for(int i=0; i<partita.getNumgiocatori();i++){
			
			giocatoreCorrente=partita.getGiocatori(i);
			Trucca(giocatoreCorrente);
		}
        
	}
	
	/**
	 * Fino a che tutti i cavalli non hanno raggiunto il traguardo pesca una carta movimento dal mazzo movimento
	 * Calcola l'incremento di posizione che deve subire ciascun cavallo in base alla carta movimento
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
    	System.out.println("Posizioni Aggiornate:");
    	System.out.println(Arrays.toString(posizioniAggiornate));
    	partita.getPlancia().muovi(posizioniAggiornate);
    	System.out.println("Posizioni Cavalli:");
    	System.out.println(Arrays.toString(partita.getPlancia().getPosizioniCavalli()));
    }
    
    /**
	 *  @param numscommessa 1:obbligatoria 2:facoltativa
	 *  Viene verificato se la scommessa è obbligatoria o meno, in tal caso viene chiesto al giocatore
	 *  se vuole scommettere oppure no, in caso di risposta affermativa viene chiesto al giocatore 
	 *  il numero di corsia, l'importo e il tipo di scommessa che vuole effettare
	 *  @return scommessa 
	 */
    public Scommessa Scommetti(Giocatore giocatore,int numscommessa,int[] numSegnalini){
       
    	
    	int PV=giocatore.getPV(),importo=0, numcorsia=0, ScommessaMinima=PV*100;
    	long denari=giocatore.getDenari();
    	char risposta='N',tiposcommessa='N';
    	boolean buonfine=false;
    	Scommessa scommessa;
    	
    	// Chiede al giocatore se vuole scommettere visto che la seconda scommessa è facoltativa 
    	if(numscommessa==2){
    		
    		while(buonfine==false){
    		
    		System.out.println("Vuoi scommettere ancora?? (S/N)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			risposta= myInput.readLine().charAt(0);
    		}
    		catch(IOException e){
        		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		
    		}
    		/* Se il giocatore non vuole più scommettere o non ha denari per scommettere
    		  restituisce una scommessa con il campo tiposcommessa=N */
    		if(risposta=='N' || risposta=='n' || denari<ScommessaMinima){
    			
    			scommessa=new Scommessa(giocatore,10,0,'N');
    			return scommessa;
    		}
    		
    	}
    	
    	/*  Se la scommessa è obbligatoria e il giocatore non ha abbastanza denari
    	   gli vengono sottratti 2 PV, se non ha 2 PV il giocatore perde la partita */
    	 
    	if(denari<ScommessaMinima){
    		
            if(PV<2){   
            	
            	System.out.println("Hai perso la partita");
            	//partita.rimuoviGiocatore();
            }   
            else{
    		       PV=PV-2;
    		       ScommessaMinima=PV*100;
    		       System.out.println("Non hai abbastanza denari per scommettere! Perdi due PV!");
            }
    	}
    	else{
    		
    	System.out.println("Hai a disposizione "+denari+" denari.");
    	
    	
    	//Chiede al giocatore su quale corsia vuole scommettere
    	 
    	while(buonfine==false){	
    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi scommettere: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			numcorsia=Integer.parseInt(""+myInput.readLine());
    			if(numcorsia<1 || numcorsia>6){
    	    	
    				System.out.println("Errore!! Inserisci un numero da 1 a 6");
    				buonfine=false;
    			}
    			
    			 if(numSegnalini[numcorsia]==0){
    			 
    			 System.out.println("Errore!! Non puoi fare altre scommesse su questa corsia!");
    			 buonfine=false;
    			 }
    			  
    			 
    		}
    		catch(IOException e){
    		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		catch(NumberFormatException e){
    		
    			System.out.println("Errore, ci vuole un numero !!!");
    			buonfine=false;
    		}
    	
    	}
    	
    	buonfine=false;
    	
    	//chiede al giocatore l'importo da scommettere
    	while(buonfine==false){

    		System.out.println("Inserisci l'importo da scommettere (min "+ScommessaMinima+" denari)");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			importo=Integer.parseInt(""+myInput.readLine());
    			if(importo>denari || importo<ScommessaMinima){
    	    	
    				System.out.println("Errore, importo non consentito!! ");
    				buonfine=false;
    			}
            
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	    catch(NumberFormatException e){
    		
    		    System.out.println("Errore, ci vuole un numero !!!");
    		    buonfine=false;
    	    }
    	}
    	
    	//chiede al giocatore se vuole scommettere piazzato o vincente
    	while(buonfine==false){

    		System.out.println("Vuoi scommettere piazzato (P) o vincente (V)?");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			tiposcommessa=myInput.readLine().charAt(0);
    			if(tiposcommessa!='V' && tiposcommessa!='P'){
    	    	
    				System.out.println("Errore, scegli P o V ");
    				buonfine=false;
    			}
            
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!");
        		buonfine=false;
        	}
    	
    	}
      }
     
      scommessa=new Scommessa(giocatore,numcorsia,importo,tiposcommessa);
	  System.out.println("Hai scommesso "+importo+" denari sulla corsia: "+numcorsia);
	  return scommessa;
    }

    /**
     * Viene chiesto al giocatore quale delle sue carte azione vuole giocare e su quale corsia
     * applicarla, queste informazioni vengono poi passate al metodo TruccaCorsia in plancia
     * La carta giocata viene infine rimossa dalle carte a disposizione del giocatore
     */
    public void Trucca(Giocatore giocatore){
    	
    	ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
    	carteAzione=giocatore.getCarteAzione();
    	boolean buonfine = false;
    	int numcarta=0,numcorsia=0;
    	System.out.println("Hai in mano queste carte: " );
    	
    	for (int i=0;i< carteAzione.size();i++){
    		System.out.println("" +(i+1)+") " +carteAzione.get(i).getNome()+"," +carteAzione.get(i).getTipoEffetto() +"," +carteAzione.get(i).getValoreEffetto());
    		}
    	
    	while( buonfine==false){	
    		System.out.println("Scegli quale carta vuoi giocare:");
        	try{
        		buonfine=true;
        	    InputStreamReader reader=new InputStreamReader(System.in);
        	    BufferedReader myInput=new BufferedReader(reader);
        	    
        	    numcarta = Integer.parseInt(""+myInput.readLine());
        	    
        	    if(numcarta<1 || numcarta>carteAzione.size()){
        	    	System.out.println("Errore!! Inserisci un numero da 1 a " +carteAzione.size());
        	    	buonfine=false;
        	    }
        	}
        	catch(IOException e){
        		
        		System.out.println("Errore !!!\n");
        		buonfine=false;
        	}
            catch(NumberFormatException e){
        		
        		System.out.println("Errore, ci vuole un numero !!!");
        		buonfine=false;
        	}
    	}
      	
    	while(buonfine==false){	
    	
    		System.out.println("Inserisci il numero di corsia (1-6) su cui vuoi giocare la carta: ");
    		try{
    			buonfine=true;
    			InputStreamReader reader=new InputStreamReader(System.in);
    			BufferedReader myInput=new BufferedReader(reader);
    			numcorsia=Integer.parseInt(""+myInput.readLine());
    			if(numcorsia<1 || numcorsia>6){
    	    	
    				System.out.println("Errore!! Inserisci un numero da 1 a 6");
    				buonfine=false;
    			}
    		}
    		catch(IOException e){
    		
    			System.out.println("Errore !!!\n");
    			buonfine=false;
    		}
    		catch(NumberFormatException e){
    		
    			System.out.println("Errore, ci vuole un numero !!!");
    			buonfine=false;
    		}
    	
    	}
    	partita.getPlancia().TruccaCorsia(carteAzione.get(numcarta-1), numcorsia);
    	carteAzione.remove(numcarta -1);
        giocatore.setCarteAzione(carteAzione);	
    }
    
}
