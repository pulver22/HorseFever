package eventi;

import java.util.Arrays;

public class eventoGiocatore implements HorseFeverEvent{

	private String nomeGiocatore;
	private long denari;
	private int pv;
	private String[] carteAzione;
	
	public eventoGiocatore(String nomeGiocatore, long denari, int pv, String[] carteAzione){
		
		this.denari=denari;
		this.pv=pv;
		this.carteAzione=carteAzione;
	}
	
	@Override
	public String rappresentazione() {
		
		return "Giocatore: "+nomeGiocatore+" Denari: "+denari+" PV: "+pv+" CarteAzione: "+Arrays.toString(carteAzione);
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
