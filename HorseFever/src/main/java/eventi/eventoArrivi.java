package eventi;

import java.util.Arrays;

public class eventoArrivi implements HorseFeverEvent{
	
	int posArrivo;
	String cavallo;
	
	public eventoArrivi(String cavallo, int posArrivo){
		this.posArrivo=posArrivo;
		this.cavallo=new String(cavallo);
	}
	
	@Override
	public String rappresentazione() {
		return "Il Cavallo "+cavallo+" è arrivato "+posArrivo;
	}

	public int getPosArrivo() {
		return posArrivo;
	}

	public void setPosArrivo(int posArrivo) {
		this.posArrivo = posArrivo;
	}

	public String getCavallo() {
		return cavallo;
	}

	public void setCavallo(String cavallo) {
		this.cavallo = cavallo;
	}

	
}
