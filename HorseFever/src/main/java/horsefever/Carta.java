package horsefever;

import java.io.Serializable;

abstract class Carta implements Serializable{
	
	private static final long serialVersionUID = 6450535504838344718L;
	protected String nome;

	//Metodi Getter e Setter
	
	public String getNome() {
		return nome;
	}

}
