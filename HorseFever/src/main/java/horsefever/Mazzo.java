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
		
		if (nome == "MazzoAzione"){
			Mazzo.add(new Azione("Magna Velocitas","Verde","Partenza","=4"));
			Mazzo.add(new Azione("Flagellum Fulguris","Verde","Sprint","+1"));
			Mazzo.add(new Azione("Herba Magica","Verde","Sprint","=2"));
			Mazzo.add(new Azione("In Igni Veritas","Verde","Fotofinish","Vincitore"));
			Mazzo.add(new Azione("Fustis et Radix","Verde","Traguardo","+2"));
			Mazzo.add(new Azione("Vigor Ferreum","Verde","Utimo","=4"));
	
			Mazzo.add(new Azione("Globulus Obscurus","Rosso","Partenza","=0"));
			Mazzo.add(new Azione("Aqua Putrida","Rosso","Partenza","-1"));
			Mazzo.add(new Azione("Serum Maleficum","Rosso","Sprint","=0"));
			Mazzo.add(new Azione("Venenum Veneficum","Rosso","Sprint","-1"));
			Mazzo.add(new Azione("Mala tempora","Rosso","Fotofinish","Perdente"));
			Mazzo.add(new Azione("XIII","Rosso","Traguardo","=0"));
			Mazzo.add(new Azione("Feliz Infernalis","Rosso","Primo","=0"));
	
			Mazzo.add(new Azione("Alfio Allibratore","Grigio","Quotazione","+2"));
			Mazzo.add(new Azione("Fritz Finden","Grigio","Azione","Rimuovi_negative"));
			Mazzo.add(new Azione("Steven Sting","Grigio","Quotazione","-2"));
			Mazzo.add(new Azione("Rochelle Recherche","Grigio","Azione","Rimuovi_positive"));
		}
		else if (nome == "MazzoPersonaggio"){
			Mazzo.add(new Personaggio("Cranio Mercanti", 3400, "1:2"));
			Mazzo.add(new Personaggio("Steve McSkull", 3600, "1:3"));
			Mazzo.add(new Personaggio("Viktor von Schadel", 3800, "1:4"));
			Mazzo.add(new Personaggio("Cesar Crane", 4000, "1:5"));
			Mazzo.add(new Personaggio("Craneo Cervantes", 4200, "1:6"));
			Mazzo.add(new Personaggio("Sigvard Skalle", 4400, "1:7"));
		}
		else if (nome == "MazzoProprietario"){
			Mazzo.add(new Proprietario("Nero"));
			Mazzo.add(new Proprietario("Blu"));
			Mazzo.add(new Proprietario("Verde"));
			Mazzo.add(new Proprietario("Rosso"));
			Mazzo.add(new Proprietario("Giallo"));
			Mazzo.add(new Proprietario("Bianco"));
		}
		else if(nome == "MazzoMovimento"){
			
			//aggiungere mazzo movimento
		}
			
		}

}


