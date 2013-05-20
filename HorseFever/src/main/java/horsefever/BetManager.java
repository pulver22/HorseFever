package horsefever;
import java.util.ArrayList;


public class BetManager {
	
	private ArrayList<Scommessa> bManager = new ArrayList<Scommessa>();

	/**
	 * Aggiunge la scommessa di ogni giocatore alla lista delle scommesse
	 * @param scom
	 */
	public void AggiungiScommessa(Scommessa scom){
		bManager.add(scom);
	}
	
	/**
	 * Restituisce la scommessa vinta ad ogni giocatore, in base a vincente/piazzato e 
	 * i relativi PV
	 * 
	 * @param ordineArrivo : griglia d'arrivo
	 * @param quotazioni : griglia che mostra in base al cavallo la sua quotazione
	 * @param giocatori : lista dei giocatori
	 */
	public void Pagamenti(String[] ordineArrivo,String[][] quotazioni,ArrayList<Giocatore> giocatori){
	for (int i=0;i<bManager.size();i++){
		if( (bManager.get(i)).getTipoScomessa() == 'V' & bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivo[0])){
		
			int corsia = bManager.get(i).getCorsia();
			long nuoviDenari=bManager.get(i).getScommettitore().getDenari();
			nuoviDenari = nuoviDenari + (long) (bManager.get(i).getImporto() * Long.parseLong(quotazioni[corsia][1]));
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +3 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
		else if( (bManager.get(i)).getTipoScomessa() == 'P' & bManager.get(i).getCorsia() >= Integer.valueOf(ordineArrivo[0])
				& bManager.get(i).getCorsia() <= Integer.valueOf(ordineArrivo[2])){
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
		if(giocatori.get(i).getScuderia() == ordineArrivo[0]) giocatori.get(i).setDenari(denari + 600);
		if(giocatori.get(i).getScuderia() == ordineArrivo[1]) giocatori.get(i).setDenari(denari + 400);
		if(giocatori.get(i).getScuderia() == ordineArrivo[2]) giocatori.get(i).setDenari(denari + 200);
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
