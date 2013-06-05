package eventi;

import horsefever.Scommessa;

public class eventoScommessa implements HorseFeverEvent{

	private String nomeGiocatore;
	private int corsia;
	private long denari;
	private char tipoScommessa;
	
	public eventoScommessa(Scommessa scom){
		
		this.nomeGiocatore=String.valueOf(scom.getScommettitore().getNome());
		this.corsia=Integer.valueOf(scom.getCorsia()) +1;
		this.denari=Long.valueOf(scom.getImporto());
		this.tipoScommessa= scom.getTipoScomessa();
	}
	
	@Override
	public String rappresentazione() {
		return "Giocatore: "+nomeGiocatore+" Corsia: "+corsia+" Importo: "+denari+" TipoScommessa "+tipoScommessa;
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

	public long getDenari() {
		return denari;
	}

	public void setDenari(long denari) {
		this.denari = denari;
	}

	public char getTipoScommessa() {
		return tipoScommessa;
	}

	public void setTipoScommessa(char tipoScommessa) {
		this.tipoScommessa = tipoScommessa;
	}
	
	

}
