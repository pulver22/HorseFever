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
		
		if (nome.equals("MazzoAzione")){
			Mazzo.add(new Azione("Magna Velocitas","Verde","Partenza","=4",'A'));
			Mazzo.add(new Azione("Fortuna Malevola","Verde","Partenza","+1",'B'));
			Mazzo.add(new Azione("Flagellum Fulguris","Verde","Sprint","+1",'C'));
			Mazzo.add(new Azione("Herba Magica","Verde","Sprint","=2",'D'));
			Mazzo.add(new Azione("In Igni Veritas","Verde","Fotofinish","=1",'E'));
			Mazzo.add(new Azione("Fustis et Radix","Verde","Traguardo","+2",'F'));
			Mazzo.add(new Azione("Vigor Ferreum","Verde","Utimo","=4",'G'));
	
			Mazzo.add(new Azione("Globulus Obscurus","Rosso","Partenza","=0",'A'));
			Mazzo.add(new Azione("Aqua Putrida","Rosso","Partenza","-1",'B'));
			Mazzo.add(new Azione("Serum Maleficum","Rosso","Sprint","=0",'C'));
			Mazzo.add(new Azione("Venenum Veneficum","Rosso","Sprint","-1",'D'));
			Mazzo.add(new Azione("Mala tempora","Rosso","Fotofinish","=0",'E'));
			Mazzo.add(new Azione("XIII","Rosso","Traguardo","=0",'F'));
			Mazzo.add(new Azione("Feliz Infernalis","Rosso","Primo","=0",'G'));
	
			Mazzo.add(new Azione("Alfio Allibratore","Grigio","Quotazione","+2",'W'));
			Mazzo.add(new Azione("Fritz Finden","Grigio","Azione","Rimuovi_negative",'X'));
			Mazzo.add(new Azione("Steven Sting","Grigio","Quotazione","-2",'Y'));
			Mazzo.add(new Azione("Rochelle Recherche","Grigio","Azione","Rimuovi_positive",'Z'));
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
			
			Mazzo.add(new Movimento(3,3,2,2,2,2,"horseFever-83.png"));
			Mazzo.add(new Movimento(3,2,3,2,2,2,"horseFever-84.png"));
			Mazzo.add(new Movimento(3,2,2,3,2,2,"horseFever-85.png"));
			Mazzo.add(new Movimento(3,2,2,2,3,2,"horseFever-86.png"));
			Mazzo.add(new Movimento(2,2,2,2,3,3,"horseFever-87.png"));
			Mazzo.add(new Movimento(2,2,2,3,2,3,"horseFever-88.png"));
			Mazzo.add(new Movimento(2,2,3,2,2,3,"horseFever-89.png"));
			Mazzo.add(new Movimento(2,3,2,2,2,3,"horseFever-90.png"));
			Mazzo.add(new Movimento(2,3,2,2,3,2,"horseFever-91.png"));
			Mazzo.add(new Movimento(2,3,2,2,3,2,"horseFever-92.png"));
			Mazzo.add(new Movimento(2,3,3,2,2,2,"horseFever-93.png"));
			Mazzo.add(new Movimento(3,2,2,2,2,3,"horseFever-94.png"));
			Mazzo.add(new Movimento(2,2,3,3,2,2,"horseFever-95.png"));
			Mazzo.add(new Movimento(2,2,3,2,3,2,"horseFever-96.png"));
			Mazzo.add(new Movimento(2,2,2,3,3,2,"horseFever-97.png"));
			Mazzo.add(new Movimento(3,3,2,2,2,2,"horseFever-98.png"));
			Mazzo.add(new Movimento(4,3,2,2,2,2,"horseFever-99.png"));
			Mazzo.add(new Movimento(4,2,2,2,2,0,"horseFever-100.png"));
			Mazzo.add(new Movimento(2,2,2,2,1,0,"horseFever-101.png"));
			Mazzo.add(new Movimento(2,2,2,2,1,1,"horseFever-102.png"));
			Mazzo.add(new Movimento(3,2,2,2,2,1,"horseFever-103.png"));
			Mazzo.add(new Movimento(2,2,3,1,2,2,"horseFever-104.png"));
			Mazzo.add(new Movimento(2,4,2,2,0,2,"horseFever-105.png"));
		}
			
	}

}


