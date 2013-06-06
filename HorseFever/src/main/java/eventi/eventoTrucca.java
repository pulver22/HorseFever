package eventi;

public class eventoTrucca extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2369087174732693884L;
	private String nomeGiocatore;
	private int corsia;
	
	public eventoTrucca(String nomeGiocatore, int corsia){
		
		this.nomeGiocatore=String.valueOf(nomeGiocatore);
		this.corsia=Integer.valueOf(corsia)+1;
	}
	
	@Override
	public String rappresentazione() {
		return "Il Giocatore: "+nomeGiocatore+" ha truccato la Corsia: "+corsia;
	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public int getCorsia() {
		return corsia;
	}

}
