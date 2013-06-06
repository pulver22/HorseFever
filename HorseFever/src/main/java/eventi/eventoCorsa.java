package eventi;

import java.util.Arrays;

public class eventoCorsa extends HorseFeverEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1238805836797424930L;
	private int[] posizioniAggiornate;
	private int[] valoriMovimento;
	private int[] esitoDadi;
	private String immagineMovimento;
	
	
	public eventoCorsa(int[] posizioniAggiornate, int[] valoriMovimento, int[] esitoDadi, String immagineMovimento){
		this.posizioniAggiornate=posizioniAggiornate.clone();
		this.valoriMovimento=valoriMovimento.clone();
		this.esitoDadi=esitoDadi.clone();
		this.immagineMovimento=immagineMovimento;

	}
	
	public String getImmagineMovimento() {
		return immagineMovimento;
	}

	@Override
	public String rappresentazione() {
		return "CartaMovimento: "+Arrays.toString(valoriMovimento)+" EsitoDadi: "+Arrays.toString(esitoDadi)+" Posizioni Aggiornate: "+Arrays.toString(posizioniAggiornate);
	}

	public int[] getPosizioniAggiornate() {
		return posizioniAggiornate;
	}

	public int[] getValoriMovimento() {
		return valoriMovimento;
	}

	public int[] getEsitoDadi() {
		return esitoDadi;
	}

}
