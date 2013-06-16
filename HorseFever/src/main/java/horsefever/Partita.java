package horsefever;
import java.util.ArrayList;
import adapter.Adapter;
import eventi.*;

public class Partita {

	private static final int NUM_CORSIE=6;
	
	private ArrayList<Giocatore> giocatori;
	private int numturni=2;
	private int turnoAttuale=1;
	private int numgiocatori;
	private Mazzo mazzoAzione=new Mazzo("MazzoAzione");
	private Mazzo mazzoPersonaggio=new Mazzo("MazzoPersonaggio");;
	private Mazzo mazzoMovimento=new Mazzo("MazzoMovimento");
	private Lavagna lavagna;
	private Plancia plancia;
	private Adapter adapter;
	
	private boolean debug=false;

	private BetManager betManager;
	private int numSegnaliniScommessaPerColore;
	private int[] numSegnalini=new int[NUM_CORSIE];
	
	/**
	 * Crea una nuova partita con il numero di giocatori scelto, scegliendo il numero di turni e i corretti segbalini
	 * 
	 * @param numgiocatori
	 */
	public Partita(int numgiocatori, Adapter a){
		
		this.adapter=a;
		
		int uno=1;
		int due=2;
		int tre=3;
		int quattro=4;
		int cinque=5;
		int sei=6;
		
		this.numgiocatori=Integer.valueOf(numgiocatori);
		if(numgiocatori==quattro){ numturni=quattro; }
		else if (numgiocatori==cinque){ numturni=cinque; }
		else { numturni=sei; }
		giocatori=new ArrayList<Giocatore>(numgiocatori);
		lavagna = new Lavagna(this);
		plancia = new Plancia(lavagna,this);
		lavagna.setPlancia(plancia);
		betManager= new BetManager(this);
		if(numgiocatori==due){ numSegnaliniScommessaPerColore=uno; }
		else if(numgiocatori==tre){ numSegnaliniScommessaPerColore=due; }
		else if(numgiocatori==quattro){ numSegnaliniScommessaPerColore=tre; }
		else { numSegnaliniScommessaPerColore=quattro; }
		
		
		for(int i=0; i<numSegnalini.length; i++){
			
			numSegnalini[i]=numSegnaliniScommessaPerColore;
		}
			
	}
	
	/**
	 * Fase iniziale di gioco,in cui ogni giocatore pesca due carte,vengono settate le quotazioni delle scuderie,
	 * aggiunti i giocatori
	 */
	public void preparazione(){
		
		String quotazione;
		String scuderia;
		if (debug){
			int soldiSigvard=4400;
			int soldiCraneo=4200;
			Personaggio p=new Personaggio("Sigvard Skalle", soldiSigvard, "7");
			giocatori.add(new Giocatore(p,"Bianco",this));	
			notifyObserver(new eventoGiocatore(giocatori.get(0),eventoGiocatore.NUOVO));
			
			Personaggio p2=new Personaggio("Craneo Cervantes", soldiCraneo, "6");
			giocatori.add(new Giocatore(p2,"Giallo",this));	
			notifyObserver(new eventoGiocatore(giocatori.get(1),eventoGiocatore.NUOVO));

		}
		for (int i=0; i<numgiocatori; i++){
			Personaggio p= (Personaggio) mazzoPersonaggio.pesca();
			quotazione=p.getQuotScuderia();
			scuderia=lavagna.getScuderiaInit(quotazione);
			giocatori.add(new Giocatore(p,scuderia,this));	
			notifyObserver(new eventoGiocatore(giocatori.get(i),eventoGiocatore.NUOVO));
			
		}
		
	}
   
	/**
	 * Verifica il giocatore con il numero di PV più alto che è il giocatore vincente
	 * @return Giocatore vincente
	 */
	public void finePartita(){
		
		int i=0,indiceGiocVinc=-1,maxPV=-1;
		for(i=0;i<giocatori.size();i++){
			
			if(giocatori.get(i).getPV()>maxPV){
				
				indiceGiocVinc=i;
				maxPV=giocatori.get(i).getPV();
			}
		}
		for (int j=0;j<giocatori.size();j++){
			if (giocatori.get(j).getPV()==maxPV && j!=indiceGiocVinc){
				if (giocatori.get(j).getDenari()>giocatori.get(indiceGiocVinc).getDenari()){
					indiceGiocVinc=j;
				}
			}
		}
		notifyObserver(new eventoGiocatore(giocatori.get(indiceGiocVinc),eventoGiocatore.VINTO));
		
	}
	
	/**
	 * Metodo che notifica tutti gli eventi lanciati da partita (o un suo componente) all'adapter
	 * il quale lo smisterà alle view registrate
	 * @param l'evento da notificare
	 * */
	public void notifyObserver(HorseFeverEvent e){
		
		adapter.notify(e);
	}
	
	/**
	 * Rimuove un giocatore dall'arrayList di partita
	 * @param il giocatore da rimuovere
	 * */
	public void rimuoviGiocatore(Giocatore gioc){
		
		notifyObserver(new eventoGiocatore(gioc,eventoGiocatore.PERSO));
		giocatori.remove(gioc);
	}
	
	/**
	 * Resetta la plancia e i mazzi alla situazione iniziale. 
	 * Cambia il primo giocatore. 
	 * */
	public void reset(){
		plancia.reset();
		mazzoAzione=new Mazzo("MazzoAzione");
		mazzoAzione.mischia();
		mazzoMovimento=new Mazzo("MazzoMovimento");
		mazzoMovimento.mischia();
		if (!debug){ //Se non in debug, cambia il primoGiocatore
			Giocatore g=giocatori.get(0);
			giocatori.remove(0);
			giocatori.add(g);
		}
		betManager.resetbManager();
		notifyObserver(new eventoGiocatore(giocatori.get(0),eventoGiocatore.PRIMO));
	}
	/**
	 * Metodo per generare a fine turno l'evento che, catturato dalla GUI View, viene usato per
	 * generare evento di reset grafico.
	 * */
	public void generaEventoResetGrafico(){
		notifyObserver(new eventoResetGrafico());
	}
	
	//Metodi Getter e Setter
	/***/
	public void setTurnoAttuale(int turno){
		
		this.turnoAttuale=Integer.valueOf(turno);
		adapter.notify(new eventoTurno(turnoAttuale,numturni));
	}
	/***/
	public void setAdapter(Adapter a){
		this.adapter=a;
	}
	/***/
	public BetManager getBetManager() {
		return betManager;
	}
	/***/
	public int[] getNumSegnalini() {
		
		
		return numSegnalini.clone();
	}
	/***/
	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}
	/***/
	public Giocatore getGiocatori(int i) {
		return giocatori.get(i);
	}
	/***/
	public int getNumturni() {
		return numturni;
	}
	/***/
	public int getNumgiocatori() {
		return numgiocatori;
	}
	/***/
	public Mazzo getMazzoAzione() {
		return mazzoAzione;
	}
	/***/
	public Mazzo getMazzoMovimento() {
		return mazzoMovimento;
	}
	/***/
	public Lavagna getLavagna() {
		return lavagna;
	}
	/***/
	public Plancia getPlancia() {
		return plancia;
	}
	/**
	 * Setta la partia nello stato di Debug o di Testing. Lo propaga ai suoi componenti
	 * @param il booleano a cui settare debug
	 * */
	public void setDebug(boolean debug){
		this.debug=debug;
		plancia.setDebug(debug);
		lavagna.setDebug(debug);
	}
	
}
