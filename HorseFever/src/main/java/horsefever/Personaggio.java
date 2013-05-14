package horsefever;

public class Personaggio extends Carta{
	public int denari;
	public String quot_scuderia;
	
	Personaggio(String nome,int denari,String quot_scuderia){
		this.nome = nome;
		this.denari = denari;
		this.quot_scuderia = quot_scuderia;
	}

	public int getDenari() {
		return denari;
	}

	public void setDenari(int denari) {
		this.denari = denari;
	}

	public String getQuot_scuderia() {
		return quot_scuderia;
	}

	public void setQuot_scuderia(String quot_scuderia) {
		this.quot_scuderia = quot_scuderia;
	}
	
}
