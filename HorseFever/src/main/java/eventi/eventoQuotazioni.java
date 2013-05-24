package eventi;

import java.util.Arrays;

public class eventoQuotazioni implements HorseFeverEvent{

	private String[][] tabellaQuot=new String[6][2];
	private String[] colori={"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	
	public eventoQuotazioni(String[][] tabellaQuot){
		for (int i=0; i<6; i++){
			tabellaQuot[i][0]=new String(colori[i]);
			tabellaQuot[i][1]=new String(tabellaQuot[i][1]);
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
