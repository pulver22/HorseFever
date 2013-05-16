package horsefever;
import java.util.ArrayList;


public class BetManager {
	
	private ArrayList<Scommessa> bManager = new ArrayList<Scommessa>();

	/**
	 * Aggiunge la scommessa di ogni giocatore alla lista delle scommesse
	 * @param scommettitore
	 * @param corsia
	 * @param importo
	 * @param tipo
	 */
	public void AggiungiScommessa(Giocatore scommettitore, int corsia, long importo,char tipo){
		bManager.add(new Scommessa(scommettitore,corsia,importo,tipo));
	}
	
	/**
	 * Restituisce la scommessa vinta ad ogni giocatore, in base a vincente/piazzato e 
	 * i relativi PV
	 * 
	 * @param ordineArrivo : griglia d'arrivo
	 * @param bManager : insieme di scommettitore,scomessa,cavallo sui cui si punta e tipo scommessa
	 * @param quotazioni : griglia che mostra in base al cavallo la sua quotazione
	 */
	public void Pagamenti(String[] ordineArrivo,String[][] quotazioni){
	for (int i=0;i<bManager.size();i++){
		if( (bManager.get(i)).getTipoScomessa() == 'V' & bManager.get(i).getCorsia() == Integer.valueOf(ordineArrivo[0])){
			//capire perchè richiede long e se quotazioni[][] restituisce valore contenuto
			long nuoviDenari = (long) (bManager.get(i).getImporto() * Long.parseLong(quotazioni[i][1]));
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +3 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
		else if( (bManager.get(i)).getTipoScomessa() == 'P' & bManager.get(i).getCorsia() >= Integer.valueOf(ordineArrivo[0])
				& bManager.get(i).getCorsia() <= Integer.valueOf(ordineArrivo[2])){
			long nuoviDenari = (long) (bManager.get(i).getImporto() * 2);
			bManager.get(i).getScommettitore().setDenari(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +1 ;
			bManager.get(i).getScommettitore().setPV(nuoviPV);
		}
	}
	}
}
