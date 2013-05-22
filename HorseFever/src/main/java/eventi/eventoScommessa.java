package eventi;

public class eventoScommessa implements HorseFeverEvent{

	private String nomeGiocatore;
	private int corsia;
	private long denari;
	private char tipoScommessa;
	
	public eventoScommessa(String nomeGiocatore, int corsia, long denari, char tipoScommessa){
		this.nomeGiocatore=nomeGiocatore;
		this.corsia=corsia;
		this.denari=denari;
		this.tipoScommessa=tipoScommessa;
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
