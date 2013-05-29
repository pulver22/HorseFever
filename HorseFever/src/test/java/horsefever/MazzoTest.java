package horsefever;
import static org.junit.Assert.*;

import org.junit.*;

public class MazzoTest {
	public Mazzo deck = new Mazzo("MazzoPersonaggio");
	public Mazzo deck2 = new Mazzo("MazzoPersonaggio");
	
	@Test
	public void mischiaTest(){
		deck.mischia();
		
		
		assertNotSame(deck.pescaCertezza(0),deck2.pescaCertezza(0));
		assertNotSame(deck.pescaCertezza(1),deck2.pescaCertezza(1));
		assertNotSame(deck.pescaCertezza(2),deck2.pescaCertezza(2));
		assertNotSame(deck.pescaCertezza(3),deck2.pescaCertezza(3));
		assertNotSame(deck.pescaCertezza(4),deck2.pescaCertezza(4));
		assertNotSame(deck.pescaCertezza(5),deck2.pescaCertezza(5));
		
		
	}
	
	@Test 
	public void inserisciTest(){
		Personaggio carta = new Personaggio("prova",100,"5");
		deck.inserisci(carta);
		assertSame(carta,deck.pescaCertezza(6));
	}
	
	@Test
	public void pescaTest(){
		Carta carta = deck.pesca();
		
		assertNotSame(deck.pescaCertezza(0),carta);
		assertNotSame(deck.pescaCertezza(1),carta);
		assertNotSame(deck.pescaCertezza(2),carta);
		assertNotSame(deck.pescaCertezza(3),carta);
		assertNotSame(deck.pescaCertezza(4),carta);

	}
	

}
