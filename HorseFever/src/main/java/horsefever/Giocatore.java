package horsefever;

import java.util.ArrayList;

public class Giocatore {

	private int PV=0;
	private long denari;
	private Carta cartaPersonaggio;
	private String scuderia;
	private ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
	
	public Giocatore(Personaggio cartaPersonaggio, String scuderia){
		this.cartaPersonaggio=cartaPersonaggio;
		denari=cartaPersonaggio.getDenari();
		this.scuderia=scuderia;
		
	}
	
    //Metodi Setter e Getter
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
	}
	public Carta getCartaPersonaggio() {
		return cartaPersonaggio;
	}
	public void setCartaPersonaggio(Personaggio cartaPersonaggio) {
		this.cartaPersonaggio = cartaPersonaggio;
	}
	public ArrayList<Azione> getCarteAzione() {
		return carteAzione;
	}
	public void setCarteAzione(ArrayList<Azione> carteAzione) {
		this.carteAzione = carteAzione;
	}
	public void setScudera(String scuderia){
		this.scuderia=scuderia;
	}
	public long getDenari() {
		return denari;
	}
	public void setDenari(long nuoviDenari) {
		this.denari = nuoviDenari;
	}
	
	public String getScuderia() {
		return scuderia;
	}
	public void setScuderia(String scuderia) {
		this.scuderia = scuderia;
	}
	
}
