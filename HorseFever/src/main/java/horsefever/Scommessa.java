package horsefever;

public class Scommessa {

	private Giocatore scommettitore;
	private int corsia;
	private long importo;
	private char tipoScomessa;
	
	/**
	 * Crea la scommessa per quel giocatore salvando il cavallo puntato,la somma e piazzato/vincente
	 * @param scommettitore
	 * @param corsia
	 * @param importo
	 * @param tipoScommessa
	 */
	public Scommessa(Giocatore scommettitore,int corsia,long importo,char tipoScommessa){
		this.scommettitore = scommettitore;
		this.corsia = corsia;
		this.importo = importo;
		this.tipoScomessa = tipoScommessa;
	}

	public Giocatore getScommettitore() {
		return scommettitore;
	}

	public int getCorsia() {
		return corsia;
	}

	public long getImporto() {
		return importo;
	}

	public char getTipoScomessa() {
		return tipoScomessa;
	}

	


}
