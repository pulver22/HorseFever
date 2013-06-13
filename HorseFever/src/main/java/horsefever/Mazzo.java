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
		String Verde="Verde";
		String Rosso="Rosso";
		String Grigio="Grigio";
		if (nome.equals("MazzoAzione")){
			Mazzo.add(new Azione("Magna Velocitas",Verde,"Partenza","=4",'A'));
			Mazzo.add(new Azione("Fortuna Malevola",Verde,"Partenza","+1",'B'));
			Mazzo.add(new Azione("Flagellum Fulguris",Verde,"Sprint","+1",'C'));
			Mazzo.add(new Azione("Herba Magica",Verde,"Sprint","=2",'D'));
			Mazzo.add(new Azione("In Igni Veritas",Verde,"Fotofinish","=1",'E'));
			Mazzo.add(new Azione("Fustis et Radix",Verde,"Traguardo","+2",'F'));
			Mazzo.add(new Azione("Vigor Ferreum",Verde,"Utimo","=4",'G'));
	
			Mazzo.add(new Azione("Globulus Obscurus",Rosso,"Partenza","=0",'A'));
			Mazzo.add(new Azione("Aqua Putrida",Rosso,"Partenza","-1",'B'));
			Mazzo.add(new Azione("Serum Maleficum",Rosso,"Sprint","=0",'C'));
			Mazzo.add(new Azione("Venenum Veneficum",Rosso,"Sprint","-1",'D'));
			Mazzo.add(new Azione("Mala tempora",Rosso,"Fotofinish","=0",'E'));
			Mazzo.add(new Azione("XIII",Rosso,"Traguardo","=0",'F'));
			Mazzo.add(new Azione("Feliz Infernalis",Rosso,"Primo","=0",'G'));
	
			Mazzo.add(new Azione("Alfio Allibratore",Grigio,"Quotazione","+2",'W'));
			Mazzo.add(new Azione("Fritz Finden",Grigio,"Azione","Rimuovi_negative",'X'));
			Mazzo.add(new Azione("Steven Sting",Grigio,"Quotazione","-2",'Y'));
			Mazzo.add(new Azione("Rochelle Recherche",Grigio,"Azione","Rimuovi_positive",'Z'));
		}
		else if (nome.equals("MazzoPersonaggio")){
			Mazzo.add(new Personaggio("Cranio Mercanti", 3400, "2"));
			Mazzo.add(new Personaggio("Steve McSkull", 3600, "3"));
			Mazzo.add(new Personaggio("Viktor von Schadel", 3800, "4"));
			Mazzo.add(new Personaggio("Cesar Crane", 4000, "5"));
			Mazzo.add(new Personaggio("Craneo Cervantes", 4200, "6"));
			Mazzo.add(new Personaggio("Sigvard Skalle", 4400, "7"));
		}
		else if (nome.equals("MazzoProprietario")){
			Mazzo.add(new Proprietario("Nero"));
			Mazzo.add(new Proprietario("Blu"));
			Mazzo.add(new Proprietario("Verde"));
			Mazzo.add(new Proprietario("Rosso"));
			Mazzo.add(new Proprietario("Giallo"));
			Mazzo.add(new Proprietario("Bianco"));
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


