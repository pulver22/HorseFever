package eventi;

public class eventoGiocatore implements HorseFeverEvent{

	private long denari;
	private int pv;
	private String[] carteAzione;
	
	public eventoGiocatore(long denari, int pv, String[] carteAzione){
		this.denari=denari;
		this.pv=pv;
		this.carteAzione=carteAzione;
	}
	
	@Override
	public String rappresentazione() {
		// TODO Auto-generated method stub
		return null;
	}

}
