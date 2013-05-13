package provaFinaleIS;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Carta azione=new Azione();
	private static Mazzo mazzoAzione;
	
    public static void main( String[] args )
    {
    	mazzoAzione  = new Mazzo(azione);
    }
}
