package horsefever;

public class Scommessa {

	private int giocatore;
	private int corsia;
	private double quota;
	private char tipoScomessa;
	
	public Scommessa(int giocatore,int corsia,double quota,char tipoScommessa){
		this.giocatore = giocatore;
		this.corsia = corsia;
		this.quota = quota;
		this.tipoScomessa = tipoScommessa;
	}

	public int getGiocatore() {
		return giocatore;
	}

	public int getCorsia() {
		return corsia;
	}

	

	public double getQuota() {
		return quota;
	}

	

	public char getTipoScomessa() {
		return tipoScomessa;
	}



}
