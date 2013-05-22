package eventi;

public class eventoCorsa implements HorseFeverEvent{
	
	private int[] posizioniAggiornate;
	private int[] valoriMovimento;
	private int[] esitoDadi;
	
	
	public eventoCorsa(int[] posizioniAggiornate, int[] valoriMovimento, int[] esitoDadi){
		this.posizioniAggiornate=posizioniAggiornate;
		this.valoriMovimento=valoriMovimento;
		this.esitoDadi=esitoDadi;
	}
	
	@Override
	public String rappresentazione() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] getPosizioniAggiornate() {
		return posizioniAggiornate;
	}

	public void setPosizioniAggiornate(int[] posizioniAggiornate) {
		this.posizioniAggiornate = posizioniAggiornate;
	}

	public int[] getValoriMovimento() {
		return valoriMovimento;
	}

	public void setValoriMovimento(int[] valoriMovimento) {
		this.valoriMovimento = valoriMovimento;
	}

	public int[] getEsitoDadi() {
		return esitoDadi;
	}

	public void setEsitoDadi(int[] esitoDadi) {
		this.esitoDadi = esitoDadi;
	}
	
	

}
