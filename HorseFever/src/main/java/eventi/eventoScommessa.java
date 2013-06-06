package eventi;

import horsefever.Scommessa;

public class eventoScommessa extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5872180560159747470L;
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

	public int getCorsia() {
		return corsia;
	}

	public long getDenari() {
		return denari;
	}

	public char getTipoScommessa() {
		return tipoScommessa;
	}
	
	

}
