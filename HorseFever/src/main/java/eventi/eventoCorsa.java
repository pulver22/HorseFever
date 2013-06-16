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
	
	/**
	 * Inizializza l'eventoCorsa
	 * @param l'array con le posizioni attuali dei cavalli
	 * @param l'array con i valori segnati dalla carta movimento
	 * @param l'array con l'esito dei dadi
	 * @param la stringa con il nome dell'immagine della carta movimento corrispondente
	 * */
	public eventoCorsa(int[] posizioniAggiornate, int[] valoriMovimento, int[] esitoDadi, String immagineMovimento){
		this.posizioniAggiornate=posizioniAggiornate.clone();
		this.valoriMovimento=valoriMovimento.clone();
		this.esitoDadi=esitoDadi.clone();
		this.immagineMovimento=immagineMovimento;

	}
	/***/
	public String getImmagineMovimento() {
		return immagineMovimento;
	}
	/**
	 * Vedi classe astratta
	 * */
	@Override
	public String rappresentazione() {
		return "CartaMovimento: "+Arrays.toString(valoriMovimento)+" EsitoDadi: "+Arrays.toString(esitoDadi)+" Posizioni Aggiornate: "+Arrays.toString(posizioniAggiornate);
	}
	/***/
	public int[] getPosizioniAggiornate() {
		return posizioniAggiornate.clone();
	}
	/***/
	public int[] getValoriMovimento() {
		return valoriMovimento.clone();
	}
	/***/
	public int[] getEsitoDadi() {
		return esitoDadi.clone();
	}

}
