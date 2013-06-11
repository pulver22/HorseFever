package horsefever;
import java.util.ArrayList;
import adapter.Adapter;
import eventi.*;

public class Partita {

	private ArrayList<Giocatore> giocatori;
	private int numturni=2;
	private int turnoAttuale=1;
	private int numgiocatori;
	private Mazzo mazzoAzione=new Mazzo("MazzoAzione");
	private Mazzo mazzoPersonaggio=new Mazzo("MazzoPersonaggio");;
	private Mazzo mazzoMovimento=new Mazzo("MazzoMovimento");
	private Mazzo mazzoProprietario=new Mazzo("MazzoProprietario");
	private Lavagna lavagna;
	private Plancia plancia;
	private Adapter adapter;
	

	private BetManager betManager;
	private int numSegnaliniScommessaPerColore;
	private int[] numSegnalini=new int[6];
	
	/**
	 * @author niccolò
	 * 
	 * @param numgiocatori
	 */
	public Partita(int numgiocatori, Adapter a){
		
		this.adapter=a;
		
		this.numgiocatori=Integer.valueOf(numgiocatori);
		if(numgiocatori==4) numturni=4;
		else if (numgiocatori==5) numturni=5;
		else numturni=6;
		giocatori=new ArrayList<Giocatore>(numgiocatori);
		lavagna = new Lavagna(this);
		plancia = new Plancia(lavagna,this);
		lavagna.setPlancia(plancia);
		betManager= new BetManager(this);
		if(numgiocatori==2) numSegnaliniScommessaPerColore=1;
		else if(numgiocatori==3) numSegnaliniScommessaPerColore=2;
		else if(numgiocatori==4) numSegnaliniScommessaPerColore=3;
		else numSegnaliniScommessaPerColore=4;
		
		
		for(int i=0; i<6; i++){
			
			numSegnalini[i]=numSegnaliniScommessaPerColore;
		}
			
	}
	
	public void preparazione(){
		
		String quotazione;
		String scuderia;
		
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
	
	
	public void notifyObserver(HorseFeverEvent e){
		
		adapter.notify(e);
	}
	
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
		Giocatore g=giocatori.get(0);
		giocatori.remove(0);
		giocatori.add(g);
		betManager.resetbManager();
		notifyObserver(new eventoGiocatore(giocatori.get(0),eventoGiocatore.PRIMO));
	}
	
	public void generaEventoResetGrafico(){
		notifyObserver(new eventoResetGrafico());
	}
	
	//Metodi Getter e Setter
	
	public void setTurnoAttuale(int turno){
		
		this.turnoAttuale=Integer.valueOf(turno);
		adapter.notify(new eventoTurno(turnoAttuale,numturni));
	}
	public void setAdapter(Adapter a){
		this.adapter=a;
	}

	public BetManager getBetManager() {
		return betManager;
	}

	public int[] getNumSegnalini() {
		
		
		return numSegnalini.clone();
	}

	public void setNumSegnalini(int[] numSegnalini) {
		this.numSegnalini = numSegnalini.clone();
	}

	public void setBetManager(BetManager betManager) {
		this.betManager = betManager;
	}
	
	public ArrayList<Giocatore> getGiocatori() {
		return giocatori;
	}
	
	public Giocatore getGiocatori(int i) {
		return giocatori.get(i);
	}

	public void setGiocatori(ArrayList<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	public int getNumturni() {
		return numturni;
	}

	public void setNumturni(int numturni) {
		this.numturni = Integer.valueOf(numturni);
	}

	public int getNumgiocatori() {
		return numgiocatori;
	}

	public void setNumgiocatori(int numgiocatori) {
		this.numgiocatori = Integer.valueOf(numgiocatori);
	}

	public Mazzo getMazzoAzione() {
		return mazzoAzione;
	}

	public void setMazzoAzione(Mazzo mazzoAzione) {
		this.mazzoAzione = mazzoAzione;
	}

	public Mazzo getMazzoPersonaggio() {
		return mazzoPersonaggio;
	}

	public void setMazzoPersonaggio(Mazzo mazzoPersonaggio) {
		this.mazzoPersonaggio = mazzoPersonaggio;
	}

	public Mazzo getMazzoMovimento() {
		return mazzoMovimento;
	}

	public void setMazzoMovimento(Mazzo mazzoMovimento) {
		this.mazzoMovimento = mazzoMovimento;
	}

	public Mazzo getMazzoProprietario() {
		return mazzoProprietario;
	}

	public void setMazzoProprietario(Mazzo mazzoProprietario) {
		this.mazzoProprietario = mazzoProprietario;
	}

	public Lavagna getLavagna() {
		return lavagna;
	}

	public void setLavagna(Lavagna lavagna) {
		this.lavagna = lavagna;
	}

	public Plancia getPlancia() {
		return plancia;
	}

	public void setPlancia(Plancia plancia) {
		this.plancia = plancia;
	}
	
	public Adapter getAdapter() {
		return adapter;
	}
	
}
