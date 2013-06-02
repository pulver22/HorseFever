package eventi;

public class eventoEffettoAvvenuto implements HorseFeverEvent{

	private String cartaAttivata;
	private int corsia;
	
	public eventoEffettoAvvenuto(String cartaAttivata, int corsia){
		this.cartaAttivata=cartaAttivata;
		this.corsia=corsia;
	}
	
	@Override
	public String rappresentazione() {
		return "Ha avuto effetto la carta "+cartaAttivata+" sulla corsia "+corsia;
	}

	public String getCartaAttivata() {
		return cartaAttivata;
	}

	public void setCartaAttivata(String cartaAttivata) {
		this.cartaAttivata = cartaAttivata;
	}

	public int getCorsia() {
		return corsia;
	}

	public void setCorsia(int corsia) {
		this.corsia = corsia;
	}
	
	
	
	
	
}
