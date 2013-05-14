package provaFinaleIS;

public class Personaggio extends Carta{
	public int denari;
	public String quot_scuderia;
	
	Personaggio(String nome,int denari,String quot_scuderia){
		this.nome = nome;
		this.denari = denari;
		this.quot_scuderia = quot_scuderia;
	}
}
