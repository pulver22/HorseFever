package horsefever;
import java.util.ArrayList;

import eventi.eventoScommessa;


public class BetManager {
	
	private ArrayList<Scommessa> bManager = new ArrayList<Scommessa>();
	private eventoScommessa e;
	private Partita p;
	
	public BetManager(Partita p){
		
		this.p=p;
	}

	/**
	 * Aggiunge la scommessa di ogni giocatore alla lista delle scommesse
	 * @param scom
	 */
	public void AggiungiScommessa(Scommessa scom){
		bManager.add(scom);
		
		// NOTIFICA EVENTO
		e=new eventoScommessa(scom);
		p.notifyObserver(e);
	}
	
	/**
	 * Restituisce la scommessa vinta ad ogni giocatore, in base a vincente/piazzato e 
	 * i relativi PV
	 * 
	 * @param ordineArrivo : griglia d'arrivo
	 * @param quotazioni : griglia che mostra in base al cavallo la sua quotazione
	 * @param giocatori : lista dei giocatori
	 */
	public void Pagamenti(String[] ordineArrivoColori,String[][] quotazioni,ArrayList<Giocatore> giocatori){
	for (int i=0;i<bManager.size();i++){
		int corsiaInt = bManager.get(i).getCorsia();
		String corsiaString = convertiOrdineArrivo(corsiaInt);
		
		if( (bManager.get(i)).getTipoScomessa() == 'V' & corsiaString.equals(ordineArrivoColori[0])){
			long nuoviDenari=bManager.get(i).getScommettitore().getDenari();
			nuoviDenari = nuoviDenari + (long) (bManager.get(i).getImporto() * Long.parseLong(quotazioni[corsiaInt][1]));
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +3 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
		else if( (bManager.get(i)).getTipoScomessa() == 'P' & (corsiaString.equals(ordineArrivoColori[0])
				|| corsiaString.equals(ordineArrivoColori[2]) || corsiaString.equals(ordineArrivoColori[1]))){
			long nuoviDenari=bManager.get(i).getScommettitore().getDenari();
			nuoviDenari = nuoviDenari+(long) (bManager.get(i).getImporto() * 2);
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +1 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
	}
	//Pagamenti ai proprietari di scuderia
	for(int i=0;i< giocatori.size();i++){
		long denari = giocatori.get(i).getDenari();
		//convertiOrdineArrivo(ordineArrivo);
		if(giocatori.get(i).getScuderia() == ordineArrivoColori[0]) giocatori.get(i).setDenari(denari + 600);
		if(giocatori.get(i).getScuderia() == ordineArrivoColori[1]) giocatori.get(i).setDenari(denari + 400);
		if(giocatori.get(i).getScuderia() == ordineArrivoColori[2]) giocatori.get(i).setDenari(denari + 200);
	}
	
	
	}
	
	
	public String convertiOrdineArrivo(int ordineArrivo){
		
		int i;
		String ordine = new String();
		
			if(ordineArrivo==0) ordine ="Nero";
			if(ordineArrivo==1) ordine ="Blu";
			if(ordineArrivo==2) ordine ="Verde";
			if(ordineArrivo==3) ordine ="Rosso";
			if(ordineArrivo==4) ordine ="Giallo";
			if(ordineArrivo==5) ordine ="Bianco";
			
			return ordine;
		}
		
	
	//metodi getter e setter
	public ArrayList<Scommessa> getbManager() {
		return bManager;
	}

	public void setbManager(ArrayList<Scommessa> bManager) {
		this.bManager = bManager;
	}
}
