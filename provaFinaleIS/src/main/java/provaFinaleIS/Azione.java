package provaFinaleIS;

public class Azione implements Carta{
	/*
	 Partenza = P
	 Sprint = S
	 Traguardo = T
	 Fotofinish = F
	 Primo/Ultimo = U
	 Punti Vittoria = V
	 Quotazione = Q
	 Carta Azione = A
	 Fase Pagamenti = $
	 */
	private String[] carte={
			"P=4",
			"P+1",
			"S+1",
			"S=2",
			"F=1",
			"T+2",
			"U=4",
			"P=0",
			"P-1",
			"S=0",
			"S-1",
			"F=0",
			"T=0",
			"U=0"
			};

	public String[] getCarte() {
		return carte;
	}

}
