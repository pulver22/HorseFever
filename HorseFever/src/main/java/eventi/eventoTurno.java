package eventi;

public class eventoTurno implements HorseFeverEvent{

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
		// TODO Auto-generated method stub
		return null;
	}

}
