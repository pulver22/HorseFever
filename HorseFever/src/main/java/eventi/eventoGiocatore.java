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

	public long getDenari() {
		return denari;
	}

	public void setDenari(long denari) {
		this.denari = denari;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public String[] getCarteAzione() {
		return carteAzione;
	}

	public void setCarteAzione(String[] carteAzione) {
		this.carteAzione = carteAzione;
	}
	
	

}
