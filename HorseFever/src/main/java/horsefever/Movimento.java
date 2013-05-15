package horsefever;

public class Movimento extends Carta {

   private int[] valorimov=new int[6];
   
   public Movimento(int[] valori){
	   
	   this.valorimov=valori;
   }
   
   public int getMovimento(int i){
		
		return valorimov[i];
   }

}
