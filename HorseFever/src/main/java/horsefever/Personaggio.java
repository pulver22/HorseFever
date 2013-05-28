package horsefever;

public class Personaggio extends Carta{
	public long denari;
	public String quotScuderia;
	
	public Personaggio(String nome,long denari,String quotScuderia){
		this.nome = nome;
		this.denari = denari;
		this.quotScuderia = quotScuderia;
	}

	public long getDenari() {
		return this.denari;
	}

	public void setDenari(int denari) {
		this.denari = denari;
	}

	public String getQuotScuderia() {
		return quotScuderia;
	}

	public void setQuotScuderia(String quotScuderia) {
		this.quotScuderia = quotScuderia;
	}
	
	
}
