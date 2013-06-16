package horsefever;
import java.util.ArrayList;

import eventi.eventoScommessa;


public class BetManager {
	
	private static final int PV_VITT=3;
	private static final int PV_PIAZZ=1;
	private static final int PROP_PRIMO=600;
	private static final int PROP_SECONDO=400;
	private static final int PROP_TERZO=200;
	
	private ArrayList<Scommessa> bManager = new ArrayList<Scommessa>();
	private eventoScommessa e;
	private Partita p;
	
	/**
	 * Inizializza betManager
	 * @param la partita di riferimento
	 * */
	public BetManager(Partita p){
		
		this.p=p;
	}

	/**
	 * Aggiunge la scommessa di ogni giocatore alla lista delle scommesse
	 * @param scom
	 */
	public void AggiungiScommessa(Scommessa scom){
		
		if(scom.getTipoScomessa()!='N'){
			
			bManager.add(scom);
			
			// NOTIFICA EVENTO
			e=new eventoScommessa(scom);
			p.notifyObserver(e);
		}
		
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
			bManager.get(i).getScommettitore().setDenariPaga(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +PV_VITT ;
			bManager.get(i).getScommettitore().setPVPaga(nuoviPV);
		}
		else if( (bManager.get(i)).getTipoScomessa() == 'P' & (corsiaString.equals(ordineArrivoColori[0])
				|| corsiaString.equals(ordineArrivoColori[2]) || corsiaString.equals(ordineArrivoColori[1]))){
			long nuoviDenari=bManager.get(i).getScommettitore().getDenari();
			nuoviDenari = nuoviDenari+(long) (bManager.get(i).getImporto() * 2);
			bManager.get(i).getScommettitore().setDenariPaga(nuoviDenari);
			int nuoviPV = bManager.get(i).getScommettitore().getPV() +PV_PIAZZ ;
			bManager.get(i).getScommettitore().setPVPaga(nuoviPV);
		}
	}
	//Pagamenti ai proprietari di scuderia
	for(int i=0;i< giocatori.size();i++){
		long denari = giocatori.get(i).getDenari();
		if(giocatori.get(i).getScuderia().equals(ordineArrivoColori[0])){
			
			giocatori.get(i).setDenariPaga(denari + PROP_PRIMO);
		}
		else if(giocatori.get(i).getScuderia().equals(ordineArrivoColori[1])){
			
			giocatori.get(i).setDenariPaga(denari + PROP_SECONDO);
		}
		else if(giocatori.get(i).getScuderia().equals(ordineArrivoColori[2])){
			
			giocatori.get(i).setDenariPaga(denari + PROP_TERZO);
		}
	}
	
	
	}
	
	/**
	 * Converte l'ordine d'arrivo dal numero di corsia nel colore corrispondente
	 * @param ordineArrivo
	 * @return
	 */
	public String convertiOrdineArrivo(int ordineArrivo){
		
		String ordine = null;
		int primo=0;
		int secondo=1;
		int terzo=2;
		int quarto=3;
		int quinto=4;
		int sesto=5;
		
			if(ordineArrivo==primo){
				
				ordine ="Nero";
			}
			else if(ordineArrivo==secondo){
				
				ordine ="Blu";
			}
			else if(ordineArrivo==terzo){
				
				ordine ="Verde";
			}
			else if(ordineArrivo==quarto){
				
				ordine ="Rosso";
			}
			else if(ordineArrivo==quinto){
				
				ordine ="Giallo";
			}
			else if(ordineArrivo==sesto){
				
				ordine ="Bianco";
			}
			
			return ordine;
		}
		
	/**
	 * Pulisce l'array di scommesse a fine turno
	 */
	public void resetbManager(){
		int i=0;
		while(bManager.size() != 0) {
			bManager.remove(i);
		}
	}
	
	//metodi getter e setter
	/***/
	public ArrayList<Scommessa> getbManager() {
		return bManager;
	}

}
