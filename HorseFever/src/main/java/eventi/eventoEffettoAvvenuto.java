package eventi;

public class eventoEffettoAvvenuto implements HorseFeverEvent{

	private String cartaAttivata;
	
	public eventoEffettoAvvenuto(String cartaAttivata){
		this.cartaAttivata=cartaAttivata;
	}
	
	@Override
	public String rappresentazione() {
		return "Ha avuto effetto la carta "+cartaAttivata;
	}

	public String getCartaAttivata() {
		return cartaAttivata;
	}

	public void setCartaAttivata(String cartaAttivata) {
		this.cartaAttivata = cartaAttivata;
	}
	
	
	
}
