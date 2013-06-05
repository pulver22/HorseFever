package eventi;

import horsefever.Giocatore;

import java.util.Arrays;

public class eventoGiocatore implements HorseFeverEvent{

	public static final int PRIMO=1;
	public static final int NUOVO=0;
	public static final int MODIFICA=2;
	public static final int DITURNO=3;
	public static final int PERSO=4;
	
	
	private String nomeGiocatore;
	private String scuderia;
	private long denari;
	private int pv;
	private String[] carteAzione=new String[2];
	private int tipo;
	private int numcarte;
	
	public eventoGiocatore(Giocatore g, int tipo){
		
		this.tipo=Integer.valueOf(tipo);
		this.nomeGiocatore=String.valueOf(g.getNome());
		this.scuderia=String.valueOf(g.getScuderia());
		this.denari=Long.valueOf(g.getDenari());
		this.pv=Integer.valueOf(g.getPV());
		
		if (g.getCarteAzione().size()!=0){
			String[] azioni=g.getStringheAzioni();
			for (int i=0; i<azioni.length; i++){
				if (azioni[i]!=null) {
					this.carteAzione[i]=new String(azioni[i]);
					numcarte++;
				}
			}
		}
	}
	
	@Override
	public String rappresentazione() {
		if (tipo==MODIFICA)
			return "Modifica al Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv+" ha in mano: "+numcarte+" Carte Azione.";
		else if (tipo==NUOVO)
			return "Si è aggiunto Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv;
		else if (tipo==PRIMO)
			return "Il primo giocatore è Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv+" ha in mano: "+numcarte+" Carte Azione.";
		else if (tipo==DITURNO)
			return "Deve fare una scelta il Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv+" ha in mano: "+numcarte+" Carte Azione.";
		else 
			return "Ha perso il Giocatore: "+nomeGiocatore+" Scuderia: "+scuderia+" Denari: "+denari+" PV: "+pv;
	}
	
	public int getTipo(){
		
		return tipo;
	}

	public String getScuderia(){
		
		return scuderia;
	}
	public String getNome(){
		
		return nomeGiocatore;
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
		numcarte=0;
		for (int i=0;i<carteAzione.length;i++){
			if (carteAzione[i]!=null) numcarte++;
		}
	}
	
	

}
