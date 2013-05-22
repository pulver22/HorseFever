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

}
