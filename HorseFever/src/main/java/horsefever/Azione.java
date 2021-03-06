package horsefever;


public class Azione extends Carta{

	private static final long serialVersionUID = 2573588524457267064L;
	private String colore;
	private String tipoEffetto;
	private String valoreEffetto;
	private char lettera;
	private int indice;

	/**
	 * Crea una nuova cartazione con i parametri passati dal mazzo
	 * @param nome
	 * @param colore
	 * @param tipoeEffetto
	 * @param valoreEffetto
	 * @param lettera
	 */
	public Azione(String nome,String colore,String tipoeEffetto, String valoreEffetto, char lettera){
		this.nome = nome;
		this.colore = colore;
		this.tipoEffetto = tipoeEffetto;
		this.valoreEffetto = valoreEffetto;
		this.lettera=lettera;
	}
	
	//Metodi Getter e Setter
	/***/
	public String getColore() {
		return colore;
	}
	/***/
	public String getTipoEffetto() {
		return tipoEffetto;
	}
	/***/
	public String getValoreEffetto() {
		return valoreEffetto;
	}
	/**
	 * Rappresentazione in stringa della carta azione.
	 * @return la rappresentazione a stringa
	 * */
	public String toString(){
		return this.nome+" "+this.colore+" "+this.tipoEffetto+" "+this.valoreEffetto;
	}
	/***/
	public char getLettera() {
		return lettera;
	}
	/***/
	public int getIndice() {
		return indice;
	}
	/***/
	public void setIndice(int indice){
		this.indice=indice;
	}
	
}
