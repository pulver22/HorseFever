package eventi;

import java.util.Arrays;

public class eventoCorsa implements HorseFeverEvent{
	
	private int[] posizioniAggiornate;
	private int[] valoriMovimento;
	private int[] esitoDadi;
	private String immagineMovimento;
	private int[] movTeorici;
	
	
	public eventoCorsa(int[] posizioniAggiornate, int[] valoriMovimento, int[] esitoDadi, String immagineMovimento){
		this.posizioniAggiornate=posizioniAggiornate;
		this.valoriMovimento=valoriMovimento;
		this.esitoDadi=esitoDadi;
		this.immagineMovimento=immagineMovimento;

	}
	
	public String getImmagineMovimento() {
		return immagineMovimento;
	}

	public void setImmagineMovimento(String immagineMovimento) {
		this.immagineMovimento = immagineMovimento;
	}

	@Override
	public String rappresentazione() {
		return "CartaMovimento: "+Arrays.toString(valoriMovimento)+" EsitoDadi: "+Arrays.toString(esitoDadi)+" Posizioni Aggiornate: "+Arrays.toString(posizioniAggiornate);
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

	public int[] getMovTeorici() {
		return movTeorici;
	}

	public void setMovTeorici(int[] movTeorici) {
		this.movTeorici = movTeorici;
	}
	
	

}
