package horsefever;

public class Personaggio extends Carta{
	public int denari;
	public String quotScuderia;
	
	Personaggio(String nome,int denari,String quotScuderia){
		this.nome = nome;
		this.denari = denari;
		this.quotScuderia = quotScuderia;
	}

	public int getDenari() {
		return denari;
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
