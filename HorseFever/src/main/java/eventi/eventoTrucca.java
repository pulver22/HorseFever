package eventi;

public class eventoTrucca implements HorseFeverEvent{

	private String nomeGiocatore;
	private int corsia;
	
	public eventoTrucca(String nomeGiocatore, int corsia){
		this.nomeGiocatore=nomeGiocatore;
		this.corsia=corsia;
	}
	
	@Override
	public String rappresentazione() {
		return "Il Giocatore: "+nomeGiocatore+" ha truccato la Corsia: "+corsia;
	}

	public String getNomeGiocatore() {
		return nomeGiocatore;
	}

	public void setNomeGiocatore(String nomeGiocatore) {
		this.nomeGiocatore = nomeGiocatore;
	}

	public int getCorsia() {
		return corsia;
	}

	public void setCorsia(int corsia) {
		this.corsia = corsia;
	}	

}
