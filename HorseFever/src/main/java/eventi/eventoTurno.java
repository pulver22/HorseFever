package eventi;

public class eventoTurno extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7990007490326932245L;
	private int turnoCorrente;
	private int turniTotali;
	
    public eventoTurno(int turnCor,int turnTot){
    	
    	this.turnoCorrente=turnCor;
    	this.turniTotali=turnTot;
    }
    
    
    //getters 
	public int getTurnoCorrente() {
		return turnoCorrente;
	}

	public int getTurniTotali() {
		return turniTotali;
	}

	@Override
	public String rappresentazione() {
		
        
		return "Turno:"+turnoCorrente+"/"+turniTotali;
	}

}
