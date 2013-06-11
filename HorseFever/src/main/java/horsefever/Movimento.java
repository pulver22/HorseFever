package horsefever;


public class Movimento extends Carta {

   /**
	 * 
	 */
	private static final long serialVersionUID = -7830854352517037416L;
	private int[] valorimov=new int[6];
   private String nomefile;
   
   public Movimento(int[] valori,String nome){
	   
	   this.valorimov=valori.clone();
	   this.nomefile=String.valueOf(nome);
   }
   
  

public Movimento(int m1,int m2, int m3, int m4, int m5, int m6,String nome){
	   valorimov[0]=Integer.valueOf(m1);
	   valorimov[1]=Integer.valueOf(m2);
	   valorimov[2]=Integer.valueOf(m3);
	   valorimov[3]=Integer.valueOf(m4);
	   valorimov[4]=Integer.valueOf(m5);
	   valorimov[5]=Integer.valueOf(m6);
	   this.nomefile=String.valueOf(nome);
	   
   }
   
   public int getMovimento(int i){
		
		return valorimov[i];
   }
   
   public int[] getArrayMovimenti(){
	   return valorimov;
   }
   
   public String getNomefile() {
		return nomefile;
	}

}
