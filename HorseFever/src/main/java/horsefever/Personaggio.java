package horsefever;

public class Personaggio extends Carta{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6774319758950087240L;
	private long denari;
	private String quotScuderia;
	/**
	 * Inizializza la carta personaggio.
	 * @param il nome del personaggio
	 * @param i denari iniziali
	 * @param la quotazione della scuderia di proprieta
	 * */
	public Personaggio(String nome,long denari,String quotScuderia){
		this.nome = nome;
		this.denari = denari;
		this.quotScuderia = quotScuderia;
	}
	/***/
	public long getDenari() {
		return this.denari;
	}
	/***/
	public String getQuotScuderia() {
		return quotScuderia;
	}

	
}
