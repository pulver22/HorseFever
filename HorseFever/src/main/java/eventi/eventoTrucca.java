package eventi;

public class eventoTrucca implements HorseFeverEvent{

	private String nomeGiocatore;
	private int corsia;
	private String cartaAzione;
	
	public eventoTrucca(String nomeGiocatore, int corsia, String cartaAzione){
		this.nomeGiocatore=nomeGiocatore;
		this.corsia=corsia;
		this.cartaAzione=cartaAzione;
	}
	
	@Override
	public String rappresentazione() {
		// TODO Auto-generated method stub
		return null;
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

	public String getCartaAzione() {
		return cartaAzione;
	}

	public void setCartaAzione(String cartaAzione) {
		this.cartaAzione = cartaAzione;
	}
	
	

}
