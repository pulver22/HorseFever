package horsefever;

public class Azione extends Carta{
	private String colore;
	private String tipoEffetto;
	private String valoreEffetto;
	private char lettera;

	public Azione(String nome,String colore,String tipoeEffetto, String valoreEffetto, char lettera){
		this.nome = nome;
		this.colore = colore;
		this.tipoEffetto = tipoeEffetto;
		this.valoreEffetto = valoreEffetto;
		this.lettera=lettera;
	}
	
	//Metodi Getter e Setter
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getTipoEffetto() {
		return tipoEffetto;
	}

	public void setTipoEffetto(String tipoEffetto) {
		this.tipoEffetto = tipoEffetto;
	}

	public String getValoreEffetto() {
		return valoreEffetto;
	}

	public void setValoreEffetto(String valoreEffetto) {
		this.valoreEffetto = valoreEffetto;
	}
	
	public String toString(){
		return this.nome+" "+this.colore+" "+this.tipoEffetto+" "+this.valoreEffetto;
	}

	public char getLettera() {
		return lettera;
	}

	public void setLettera(char lettera) {
		this.lettera = lettera;
	}
	
	
}
