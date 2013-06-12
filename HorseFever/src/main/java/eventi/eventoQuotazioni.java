package eventi;

import java.util.Arrays;

public class eventoQuotazioni extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113430620159959248L;
	private String[][] tabellaQuot=new String[6][2];
	private String[] colori={"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	
	public eventoQuotazioni(String[][] tabellaQuot){
		for (int i=0; i<6; i++){
			this.tabellaQuot[i][0]=String.valueOf(colori[i]);
			this.tabellaQuot[i][1]="1:"+String.valueOf(tabellaQuot[i][1]);
		}
	}
	
	@Override
	public String rappresentazione() {
		return "Quotazioni Attuali: "+Arrays.deepToString(tabellaQuot);
	}

	public String[][] getTabellaQuot() {
		return tabellaQuot;
	}

	
	
}
