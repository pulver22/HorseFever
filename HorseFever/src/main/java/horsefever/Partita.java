package horsefever;
import java.util.ArrayList;
import java.util.Arrays;

public class Partita {

	private ArrayList<Giocatore> giocatori;
	private int numturni=2;
	private int numgiocatori;
	private Mazzo mazzoAzione=new Mazzo("MazzoAzione");
	private Mazzo mazzoPersonaggio=new Mazzo("MazzoPersonaggio");;
	private Mazzo mazzoMovimento=new Mazzo("MazzoMovimento");
	private Mazzo mazzoProprietario=new Mazzo("MazzoProprietario");
	private Lavagna lavagna;
	private Plancia plancia;
	private Turno turno;
	private BetManager betManager;
	private int numSegnaliniScommessaPerColore;
	private int[] numSegnalini=new int[6];
	
	/**
	 * @author niccolò
	 * Sono un bimbominchia
	 * @param numgiocatori
	 */
	public Partita(int numgiocatori){
		this.numgiocatori=numgiocatori;
		if(numgiocatori==4) numturni=4;
		else if (numgiocatori==5) numturni=5;
		else numturni=6;
		giocatori=new ArrayList<Giocatore>(numgiocatori);
		lavagna = new Lavagna();
		plancia = new Plancia(lavagna);
		betManager= new BetManager();
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
			giocatori.add(new Giocatore(p,scuderia));
			
		}
		
	}
	/**
	 * Lancia i turni per il numero di turni prestabilito
	 * Infine invoca FinePartita()
	 */
	public void Gioca(){
		
		int i;
		for(i=0;i<numturni;i++){
			
			turno.start();
		}
		FinePartita();
	}
   
	/**
	 * Verifica il giocatore con il numero di PV più alto che è il giocatore vincente
	 * @return Giocatore vincente
	 */
	public Giocatore FinePartita(){
		
		int i=0,indiceGiocVinc=0,maxPV=0;
		for(i=0;i<numgiocatori;i++){
			
			if(giocatori.get(i).getPV()>maxPV){
				
				indiceGiocVinc=i;
				maxPV=giocatori.get(i).getPV();
			}
		}
		
		return this.giocatori.get(indiceGiocVinc);
		
	}
	
	
	public void rimuoviGiocatore(int i){
		
		giocatori.remove(i);
	}
	//Metodi Getter e Setter

	public BetManager getBetManager() {
		return betManager;
	}

	public int[] getNumSegnalini() {
		return numSegnalini;
	}

	public void setNumSegnalini(int[] numSegnalini) {
		this.numSegnalini = numSegnalini;
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
		this.numturni = numturni;
	}

	public int getNumgiocatori() {
		return numgiocatori;
	}

	public void setNumgiocatori(int numgiocatori) {
		this.numgiocatori = numgiocatori;
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

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
}
