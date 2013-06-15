package horsefever;
import java.util.ArrayList;

public class Mazzo {
	
	private ArrayList<Carta> Mazzo = new ArrayList<Carta>();
	
	
/**
 	* Scorre il mazzo e sostituisce a caso una carta con un' altra	
 */
	public void mischia(){
		for(int i=Mazzo.size()-1;i > 0; i--){
			int j = (int) (Math.random()* i);
			Carta test = (Carta) Mazzo.get(j);
			Mazzo.set(j,Mazzo.get(i));
			Mazzo.set(i,test);
		}
	}
	/**
	 * Pesca una carta precisa
	 */
	public Carta pescaCertezza(int i){
		return Mazzo.get(i);
	}
	
/**
 	* Pesca una carta a caso dal mazzo,la restituisce e la rimuove dal mazzo
 * @return
 */
	public Carta pesca(){
		int j = (int) (Math.random() * Mazzo.size());
		Carta test =  (Carta) Mazzo.get(j);
		Mazzo.remove(j);
		return test;
	}
	
	
	/**
	* Inserisce una carta in fondo al mazzo
	 * @param carta
	 */
	public void inserisci(Carta carta){
		Mazzo.add(carta);
	}
	
	
	/**
	 * In base al tipo di carta passato, viene creato il mazzo opportuno
	 * @param nome
	 */
	public Mazzo(String nome){
		String verde="Verde";
		String rosso="Rosso";
		String grigio="Grigio";
		if (nome.equals("MazzoAzione")){
			Mazzo.add(new Azione("Magna Velocitas",verde,"Partenza","=4",'A',0));
			Mazzo.add(new Azione("Fortuna Benevola",verde,"Partenza","+1",'B',1));
			Mazzo.add(new Azione("Flagellum Fulguris",verde,"Sprint","+1",'C',2));
			Mazzo.add(new Azione("Herba Magica",verde,"Sprint","=2",'D',3));
			Mazzo.add(new Azione("In Igni Veritas",verde,"Fotofinish","=1",'E',4));
			Mazzo.add(new Azione("Fustis et Radix",verde,"Traguardo","+2",'F',5));
			Mazzo.add(new Azione("Vigor Ferreum",verde,"Utimo","=4",'G',6));
	
			Mazzo.add(new Azione("Globus Obscurus",rosso,"Partenza","=0",'A',7));
			Mazzo.add(new Azione("Aqua Putrida",rosso,"Partenza","-1",'B',8));
			Mazzo.add(new Azione("Serum Maleficum",rosso,"Sprint","=0",'C',9));
			Mazzo.add(new Azione("Venenum Veneficum",rosso,"Sprint","-1",'D',10));
			Mazzo.add(new Azione("Mala Tempora",rosso,"Fotofinish","=0",'E',11));
			Mazzo.add(new Azione("XIII",rosso,"Traguardo","=0",'F',12));
			Mazzo.add(new Azione("Felix Infernalis",rosso,"Primo","=0",'G',13));
	
			Mazzo.add(new Azione("Alfio Allibratore",grigio,"Quotazione","+2",'W',14));
			Mazzo.add(new Azione("Friz Finden",grigio,"Azione","Rimuovi_negative",'X',15));
			Mazzo.add(new Azione("Steven Sting",grigio,"Quotazione","-2",'Y',16));
			Mazzo.add(new Azione("Rochelle Recherche",grigio,"Azione","Rimuovi_positive",'Z',17));
		}
		else if (nome.equals("MazzoPersonaggio")){
			Mazzo.add(new Personaggio("Cranio Mercanti", 3400, "2"));
			Mazzo.add(new Personaggio("Steve McSkull", 3600, "3"));
			Mazzo.add(new Personaggio("Viktor von Schadel", 3800, "4"));
			Mazzo.add(new Personaggio("Cesar Crane", 4000, "5"));
			Mazzo.add(new Personaggio("Craneo Cervantes", 4200, "6"));
			Mazzo.add(new Personaggio("Sigvard Skalle", 4400, "7"));
		}
		else if(nome.equals("MazzoMovimento")){
			
			Mazzo.add(new Movimento(3,3,2,2,2,2,"0"));
			Mazzo.add(new Movimento(3,2,3,2,2,2,"1"));
			Mazzo.add(new Movimento(3,2,2,3,2,2,"2"));
			Mazzo.add(new Movimento(3,2,2,2,3,2,"3"));
			Mazzo.add(new Movimento(2,2,2,2,3,3,"4"));
			Mazzo.add(new Movimento(2,2,2,3,2,3,"5"));
			Mazzo.add(new Movimento(2,2,3,2,2,3,"6"));
			Mazzo.add(new Movimento(2,3,2,2,2,3,"7"));
			Mazzo.add(new Movimento(2,3,2,2,3,2,"8"));
			Mazzo.add(new Movimento(2,3,2,2,3,2,"9"));
			Mazzo.add(new Movimento(2,3,3,2,2,2,"10"));
			Mazzo.add(new Movimento(3,2,2,2,2,3,"11"));
			Mazzo.add(new Movimento(2,2,3,3,2,2,"12"));
			Mazzo.add(new Movimento(2,2,3,2,3,2,"13"));
			Mazzo.add(new Movimento(2,2,2,3,3,2,"14"));
			Mazzo.add(new Movimento(3,3,2,2,2,2,"15"));
			Mazzo.add(new Movimento(4,3,2,2,2,2,"16"));
			Mazzo.add(new Movimento(4,2,2,2,2,0,"17"));
			Mazzo.add(new Movimento(2,2,2,2,1,0,"18"));
			Mazzo.add(new Movimento(2,2,2,2,1,1,"19"));
			Mazzo.add(new Movimento(3,2,2,2,2,1,"20"));
			Mazzo.add(new Movimento(2,2,3,1,2,2,"21"));
			Mazzo.add(new Movimento(2,4,2,2,0,2,"22"));
		}
			
	}

}


