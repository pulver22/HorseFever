package eventi;

public class eventoMovimentoAnomalo extends HorseFeverEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8862445888665384677L;
	private String cavallo;
	private int movTeorico;
	private int movReale;
	private String causa;
	
	/**
	 * Inizializza l'evento movimento Anomalo.
	 * @param il colore del cavallo.
	 * @param il movimento teorico che avrebbe dovuto assumere il cavallo
	 * @param il movimento reale che ha effettuato il cavallo
	 * @param la causa che ha causato la variazione
	 * */
	public eventoMovimentoAnomalo(String cavallo, int movTeorico, int movReale, String causa){
		this.cavallo=String.valueOf(cavallo);
		this.movTeorico=Integer.valueOf(movTeorico);
		this.movReale=Integer.valueOf(movReale);
		this.causa=String.valueOf(causa);
	}

	
	/***/
	public String getCavallo() {
		return cavallo;
	}
	/***/
	public int getMovTeorico() {
		return movTeorico;
	}
	/***/
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
