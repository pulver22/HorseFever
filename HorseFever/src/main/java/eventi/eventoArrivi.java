package eventi;

public class eventoArrivi extends HorseFeverEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7147089307672438729L;
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
