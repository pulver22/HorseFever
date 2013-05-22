package eventi;

import horsefever.Giocatore;

import java.util.Arrays;

public class eventoGiocatore implements HorseFeverEvent{

	private String nomeGiocatore;
	private String scuderia;
	private long denari;
	private int pv;
	private String[] carteAzione;
	
	public eventoGiocatore(Giocatore g){
		this.nomeGiocatore=new String(g.getNome());
		this.scuderia=new String(g.getScuderia());
		this.denari=new Long(g.getDenari());
		this.pv=new Integer(g.getPV());
		String[] azioni=g.getStringheAzioni();
		for (int i=0; i<azioni.length; i++){
			this.carteAzione[i]=new String(azioni[i]);
		}
	}
	
	@Override
	public String rappresentazione() {
		
		return "Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv+" CarteAzione: "+Arrays.toString(carteAzione);
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
