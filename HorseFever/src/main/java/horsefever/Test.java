package horsefever;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[] movimentiReali=new int[6];
		int[] temp=new int[6];
		Partita p=new Partita(6);
		Lavagna lavagna = new Lavagna(p);
		Plancia plancia = new Plancia(lavagna,p);
		Mazzo mazzoMovimento = new Mazzo("MazzoMovimento");
		Mazzo mazzoAzione = new Mazzo("MazzoAzione");
		
		Movimento m1 =(Movimento) mazzoMovimento.pesca();
		System.out.println(Arrays.toString(m1.getArrayMovimenti()));
		
		temp=m1.getArrayMovimenti();
		
		for (int i=0; i<6;i++){
			movimentiReali[i]=temp[lavagna.getRigaMovimento(i)];
		}
		System.out.println(Arrays.toString(movimentiReali));
		
		Azione a1=(Azione)mazzoAzione.pesca();
		System.out.println(a1.toString());
		
		plancia.TruccaCorsia(a1, 1);
		plancia.applicaAzioni();
		
		int[] testPos={12,12,0,0,0,0};
		plancia.setPosizioniCavalli(testPos);
		
		System.out.println(Arrays.toString(plancia.getPosizioniCavalli()));
		plancia.muovi(movimentiReali);
		System.out.println(Arrays.toString(plancia.getPosizioniCavalli()));
		
		System.out.println("Il cavallo arrivato 1° è "+plancia.getCavalloArrivatoInPos(0).toString());
		
		
		
	}

}
