package eventi;

public class eventoMovimentoAnomalo extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862445888665384677L;
	String cavallo;
	int movTeorico;
	int movReale;
	String causa;
	
	public eventoMovimentoAnomalo(String cavallo, int movTeorico, int movReale, String causa){
		this.cavallo=new String(cavallo);
		this.movTeorico=movTeorico;
		this.movReale=movReale;
		this.causa=new String(causa);
	}

	
	
	public String getCavallo() {
		return cavallo;
	}
	
	public int getMovTeorico() {
		return movTeorico;
	}

	public int getMovReale() {
		return movReale;
	}

	public String getCausa() {
		return causa;
	}

	@Override
	public String rappresentazione() {
		return "Il cavallo "+cavallo+" si sarebbe dovuto muovere di "+movTeorico+" invece si è mosso di "+movReale+" a causa di un "+causa;
	}
}
