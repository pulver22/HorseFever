package horsefever;

import java.util.ArrayList;

import eventi.eventoGiocatore;
import adapter.*;

public class Giocatore {

	private int PV=0;
	private long denari;
	private Carta cartaPersonaggio;
	private String scuderia;
	private ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
	private String nomeGiocatore;
	private Partita partita;
	private eventoGiocatore e;
	
	public Giocatore(Personaggio cartaPersonaggio, String scuderia, Partita p){
		this.partita=p;
		this.cartaPersonaggio=cartaPersonaggio;
		denari=cartaPersonaggio.getDenari();
		this.scuderia=scuderia;
		nomeGiocatore=cartaPersonaggio.getNome();
	}
	
    //Metodi Setter e Getter
	public String getNome(){
		return nomeGiocatore;
	}
	
	public int getPV() {
		return PV;
	}
	public void setPV(int pV) {
		PV = pV;
		
		//NOTIFICA EVENTO
	     e=new eventoGiocatore(this);
	     partita.notifyObserver(e);
	}
	public Carta getCartaPersonaggio() {
		return cartaPersonaggio;
	}
	public void setCartaPersonaggio(Personaggio cartaPersonaggio) {
		this.cartaPersonaggio = cartaPersonaggio;
	}
	public Azione getCartaAzione(int indice){
		return carteAzione.get(indice);
	}
	public String[] getStringheAzioni(){
		String[] azioni=new String[2];
		for (int i=0; i<carteAzione.size(); i++){
			azioni[i]=carteAzione.get(i).toString();
		}
		return azioni;
	}
	
	public ArrayList<Azione> getCarteAzione() {
		return carteAzione;
	}
	public void setCarteAzione(ArrayList<Azione> carteAzione) {
		this.carteAzione = carteAzione;
		
		//NOTIFICA EVENTO
        e=new eventoGiocatore(this);
        partita.notifyObserver(e);
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
