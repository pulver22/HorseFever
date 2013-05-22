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

}
