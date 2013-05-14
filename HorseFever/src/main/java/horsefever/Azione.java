package horsefever;

public class Azione extends Carta{
	private String colore;
	private String tipo_effetto;
	private String valore_effetto;

	Azione(String nome,String colore,String tipo_effetto, String valore_effetto){
		this.nome = nome;
		this.colore = colore;
		this.tipo_effetto = tipo_effetto;
		this.valore_effetto = valore_effetto;
	}
	
	//Metodi Getter e Setter
	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getTipo_effetto() {
		return tipo_effetto;
	}

	public void setTipo_effetto(String tipo_effetto) {
		this.tipo_effetto = tipo_effetto;
	}

	public String getValore_effetto() {
		return valore_effetto;
	}

	public void setValore_effetto(String valore_effetto) {
		this.valore_effetto = valore_effetto;
	}
}
