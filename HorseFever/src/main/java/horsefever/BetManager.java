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
		if( (bManager.get(i)).getTipoScomessa() == 'V' & bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivoColori[0])){
		
			int corsia = bManager.get(i).getCorsia();
			long nuoviDenari=bManager.get(i).getScommettitore().getDenari();
			nuoviDenari = nuoviDenari + (long) (bManager.get(i).getImporto() * Long.parseLong(quotazioni[corsia][1]));
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +3 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
		else if( (bManager.get(i)).getTipoScomessa() == 'P' & (bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivoColori[0])
				|| bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivoColori[2]) || bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivoColori[1]))){
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
	
	
	public void convertiOrdineArrivo(String[] ordineArrivo){
		
		int i;
		
		for(i=0;i<ordineArrivo.length;i++){
			
			if(ordineArrivo[i]=="0") ordineArrivo[i]="Nero";
			if(ordineArrivo[i]=="1") ordineArrivo[i]="Blu";
			if(ordineArrivo[i]=="2") ordineArrivo[i]="Verde";
			if(ordineArrivo[i]=="3") ordineArrivo[i]="Rosso";
			if(ordineArrivo[i]=="4") ordineArrivo[i]="Giallo";
			if(ordineArrivo[i]=="5") ordineArrivo[i]="Bianco";
			
		}
		
	}
	//metodi getter e setter
	public ArrayList<Scommessa> getbManager() {
		return bManager;
	}

	public void setbManager(ArrayList<Scommessa> bManager) {
		this.bManager = bManager;
	}
}
