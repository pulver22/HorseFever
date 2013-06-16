package horsefever;

import java.util.ArrayList;

import eventi.eventoGiocatore;

public class Giocatore {

	private int PV=1;
	private long denari;
	private String scuderia;
	private ArrayList<Azione> carteAzione=new ArrayList<Azione>(2);
	private String nomeGiocatore;
	private Partita partita;
	private eventoGiocatore e;
	
	/**
	 * Inizializza il giocatore
	 * @param La carta personaggio che identifica il giocatore
	 * @param il colore della scuderia alla quotazione definita dalla carta personaggio
	 * @param il riferimento alla partita in cui partecipa il giocatore
	 * @author Niccolo
	 * */
	public Giocatore(Personaggio cartaPersonaggio, String scuderia, Partita p){
		this.partita=p;
		this.denari=cartaPersonaggio.getDenari();
		this.scuderia=String.valueOf(scuderia);
		nomeGiocatore=cartaPersonaggio.getNome();
	}
	
    //Metodi Setter e Getter
	/***/
	public String getNome(){
		return nomeGiocatore;
	}
	/***/
	public int getPV() {
		return PV;
	}
	/**
	 * Setta i nuovi pv del giocatore e lancia un evento di modifica
	 * @param i nuovi PV
	 * */
	public void setPV(int pV) {
		PV = Integer.valueOf(pV);
		
		//NOTIFICA EVENTO
	     e=new eventoGiocatore(this, eventoGiocatore.MODIFICA);
	     partita.notifyObserver(e);
	}
	/**
	 * Setta i nuovi pv del giocatore quando avviene in seguito a un pagamento del betManager
	 * e lancia un evento di pagamento
	 * @param i nuovi pv
	 * */
	public void setPVPaga(int pV) {
		PV = Integer.valueOf(pV);
		
		//NOTIFICA EVENTO
	     e=new eventoGiocatore(this, eventoGiocatore.PAGAMENTO);
	     partita.notifyObserver(e);
	}
	/**
	 * Restituisce l'array delle .toString() delle carte Azione del Giocatore
	 * @return l'array di Stringhe che rappresentano le carte Azione in mano al giocatore
	 * */
	public String[] getStringheAzioni(){
		String[] azioni=new String[2];
		for (int i=0; i<2; i++){
			if (i<carteAzione.size()){ azioni[i]=carteAzione.get(i).toString(); }
		}
		return azioni;
	}
	/***/
	public ArrayList<Azione> getCarteAzione() {
		return carteAzione;
	}
	/***/
	public void setCarteAzione(ArrayList<Azione> carteAzione) {
		this.carteAzione = carteAzione;
		
		//NOTIFICA EVENTO

        partita.notifyObserver(new eventoGiocatore(this, eventoGiocatore.MODIFICA));
	}
	/***/
	public long getDenari() {
		return denari;
	}
	/**
	 * Setta i nuovi denari e lancia una notifica di modifica
	 * @param i nuovi denari
	 * */
	public void setDenari(long nuoviDenari) {
		this.denari = Long.valueOf(nuoviDenari);
		
		//NOTIFICA EVENTO
		e=new eventoGiocatore(this, eventoGiocatore.MODIFICA);
        partita.notifyObserver(e);
	}
	/**
	 * Setta i nuovi denari qualora ciò avvenga per un pagamento del betManager e lancia 
	 * un evento di pagamento
	 * @param i nuovi denari
	 * */
	public void setDenariPaga(long nuoviDenari) {
		this.denari = Long.valueOf(nuoviDenari);
		
		//NOTIFICA EVENTO
		e=new eventoGiocatore(this, eventoGiocatore.PAGAMENTO);
        partita.notifyObserver(e);
	}
	/***/
	public String getScuderia() {
		return scuderia;
	}
	/***/
	public void setScuderia(String scuderia) {
		this.scuderia = String.valueOf(scuderia);
	}
	/***/
	public void addCartaAzione(Azione a){
		this.carteAzione.add(a);
	}
	
}
