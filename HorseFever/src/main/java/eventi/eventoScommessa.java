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

}
