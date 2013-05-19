package horsefever;

public class Movimento extends Carta {

   private int[] valorimov=new int[6];
   
   public Movimento(int[] valori){
	   
	   this.valorimov=valori;
   }
   
   public Movimento(int m1,int m2, int m3, int m4, int m5, int m6){
	   valorimov[0]=m1;
	   valorimov[1]=m2;
	   valorimov[2]=m3;
	   valorimov[3]=m4;
	   valorimov[4]=m5;
	   valorimov[5]=m6;
   }
   
   public int getMovimento(int i){
		
		return valorimov[i];
   }
   
   public int[] getArrayMovimenti(){
	   return valorimov;
   }

}
