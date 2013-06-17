package eventi;

import java.util.Arrays;

public class eventoQuotazioni extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113430620159959248L;
	private static final int NUM_CORSIE=6;
	private String[][] tabellaQuot=new String[NUM_CORSIE][2];
	private String[] colori={"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	
	/**
	 * Inizializza l'evento Quotazioni
	 * @param la tabella delle quotazioni attuali aggiornate
	 * */
	public eventoQuotazioni(String[][] tabellaQuot){
		for (int i=0; i<NUM_CORSIE; i++){
			this.tabellaQuot[i][0]=String.valueOf(colori[i]);
			this.tabellaQuot[i][1]="1:"+tabellaQuot[i][1];
		}
	}
	/***/
	@Override
	public String rappresentazione() {
		return "Quotazioni Attuali: "+Arrays.deepToString(tabellaQuot);
	}
	/***/
	public String[][] getTabellaQuot() {
		return tabellaQuot;
	}

	
	
}
