package eventi;

import java.util.Arrays;

public class eventoQuotazioni implements HorseFeverEvent{

	private String[][] tabellaQuot;
	
	public eventoQuotazioni(String[][] tabellaQuot){
		this.tabellaQuot=tabellaQuot;
		for (int i=0; i<6; i++){
			tabellaQuot[i][1]="1:"+tabellaQuot[i][1];
		}
	}
	
	@Override
	public String rappresentazione() {
		return "Quotazioni Attuali: "+Arrays.toString(tabellaQuot);
	}

	public String[][] getTabellaQuot() {
		return tabellaQuot;
	}

	public void setTabellaQuot(String[][] tabellaQuot) {
		this.tabellaQuot = tabellaQuot;
	}

	
	
}
