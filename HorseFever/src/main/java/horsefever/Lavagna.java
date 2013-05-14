package horsefever;

import java.util.ArrayList;

public class Lavagna {

	private String[][] quotazioni = new String[6][2];
	private String[] arrivi = new String[6];
	
	public Lavagna(){
		int temp;
		quotazioni[0][0]="Nero";
		quotazioni[1][0]="Blu";
		quotazioni[2][0]="Verde";
		quotazioni[3][0]="Rosso";
		quotazioni[4][0]="Giallo";
		quotazioni[5][0]="Bianco";
		
		ArrayList<Integer> init = new ArrayList(6);
		for (int i=2; i<8;i++){
			init.add(i);
		}
		for (int j=0; j<6; j++){
			temp=(int) (Math.random()*init.size());
			quotazioni[j][1]=init.get(temp).toString();
			init.remove(temp);
			
		}
	}
	
	public void ricalcolaQuotazioni(){
		for (int i=0; i<6; i++){
			
		}
	}
	
	public String getScuderiaInit(String quotazione){
		String scuderia=null;
		for (int j=0; j<6;j++){
			if (quotazione==quotazioni[j][1]) scuderia=quotazioni[j][0];
		}
		return scuderia;
	}
	
	public void setArrivi(String[] arrivi){
		this.arrivi=arrivi;
	}
}
