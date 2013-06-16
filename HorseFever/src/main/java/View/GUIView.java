package View;


import javax.swing.*;

import adapter.AdapterReteServer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import horsefever.Azione;
import eventi.*;


public class GUIView implements View{

	private static final int NUM_AZIONI=18;
	private static final int NUM_CORSIE=6;
	
	public static final int DIM_SCOM=3;
	public static final int DIM_TRUC=2;
	
	public static final int IMPORTO=0;
	public static final int CARTA_SCELTA=0;
	public static final int CORSIA=1;
	public static final int TIPO=2;
	public static final int WIDTH=1200;
	public static final int HEIGHT=700;
	
	private Board board;
	private Queue<eventoCorsa> eventiCorsa=new ConcurrentLinkedQueue<eventoCorsa>();
	private Queue<eventoGiocatore> eventiPagamento= new ConcurrentLinkedQueue<eventoGiocatore>();
	private ArrayList<eventoArrivi> arrivati=new ArrayList<eventoArrivi>();
	private ArrayList<eventoQuotazioni> quotazioni=new ArrayList<eventoQuotazioni>();
	private ThreadCorsa threadCorsa;
	private boolean quotIniziali=true;
	private boolean first=true;
	private JFrame frame=new JFrame("Horse Fever");
	private static final String[] sceltaCorsia = {"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	private static final String colorePannelli="#FFFFFF";
	
	private static final Logger log = Logger.getLogger(AdapterReteServer.class.getName());
	
	//Immagini Carte Azione
	private ImageIcon[] carteAzione=new ImageIcon[NUM_AZIONI];
	private String[] nomiCarte = {
			"carteAzione/Magna Velocitas.png",
			"carteAzione/Fortuna Benevola.png",
			"carteAzione/Flagellum Fulguris.png",
			"carteAzione/Herba Magica.png",
			"carteAzione/In Igni Veritas.png",
			"carteAzione/Fustis et Radix.png",
			"carteAzione/Vigor Ferreum.png",
			
			"carteAzione/Globus Obscurus.png",
			"carteAzione/Aqua Putrida.png",
			"carteAzione/Serum Maleficum.png",
			"carteAzione/Venenum Veneficum.png",
			"carteAzione/Mala Tempora.png",
			"carteAzione/XIII.png",
			"carteAzione/Felix Infernalis.png",
			
			"carteAzione/Alfio Allibratore.png",
			"carteAzione/Friz Finden.png",
			"carteAzione/Steven Sting.png",
			"carteAzione/Rochelle Recherche.png"
	};
	
	/**
	 * Inizializza la GUIView
	 * */
	public GUIView(){
		
		board=new Board();
		frame.add(board);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int i=0;i<nomiCarte.length;i++){
			carteAzione[i]=new ImageIcon(getClass().getResource(nomiCarte[i]));
		}
	}
	
	private static Object lock2 = new Object();
	/**
	 * Chiede all'utente i valori per scommettere
	 * @return un array di stringhe con i valori inseriti dall'utente
	 * */
	@Override
	public String[] chiediScommessa() {
		
		boolean buonfine= false;
		String[] scommessa = new String[DIM_SCOM];
		
		while (!buonfine){
			final JFrame frame = new JFrame("Make your Choice!!");

			JButton invioScommessa = new JButton("Conferma");

			JLabel importo = new JLabel("Inserisci Importo");
			JTextField sceltaImporto = new JTextField();

			JLabel corsia = new JLabel("Scegli la corsia : ");
			JComboBox corsieDisponibili = new JComboBox();
			
			for (int i=0; i<sceltaCorsia.length;i++){
				corsieDisponibili.addItem(String.valueOf(sceltaCorsia[i]));
			}

			String[] genereScommessa={"Piazzato","Vincente"};
			JLabel tipo = new JLabel("Scegli se piazzato o vincente : ");
			JComboBox tipoScommessa = new JComboBox();
			tipoScommessa.addItem(String.valueOf(genereScommessa[0]));
			tipoScommessa.addItem(String.valueOf(genereScommessa[1]));


			invioScommessa.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent ae){
					synchronized(lock2){
						lock2.notifyAll();
						frame.dispose();
					}
				}
			});

			int righeGriglia=3;
			int colonneGriglia=2;

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(righeGriglia,colonneGriglia));
			panel.setBackground(Color.decode(colorePannelli));


			JPanel panel_conferma = new JPanel();
			panel_conferma.setLayout(new FlowLayout());
			panel_conferma.setBackground(Color.decode(colorePannelli));

			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
			frame.add(panel, BorderLayout.NORTH);
			frame.add(panel_conferma, BorderLayout.SOUTH);

			panel.add(importo);
			panel.add(sceltaImporto);
			panel.add(corsia);
			panel.add(corsieDisponibili);
			panel.add(tipo);
			panel.add(tipoScommessa);

			panel_conferma.add(invioScommessa);


			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			Thread t = new Thread() {
				public void run() {
					synchronized(lock2) {
						while (frame.isVisible()){
							try {
								lock2.wait();
							} catch (InterruptedException e) {
								log.log(Level.SEVERE, e.getMessage(), e);
							}
						}
					}
				}
			};
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			scommessa[IMPORTO] = (String) sceltaImporto.getText();
			scommessa[CORSIA] = (String) corsieDisponibili.getSelectedItem();
	
			if (scommessa[CORSIA].equals(sceltaCorsia[0])){
				scommessa[CORSIA]="1";
			} else if (scommessa[1].equals(sceltaCorsia[1])){
				scommessa[CORSIA]="2";
			} else if (scommessa[1].equals(sceltaCorsia[2])){
				scommessa[CORSIA]="3";
			} else if (scommessa[1].equals(sceltaCorsia[3])){
				scommessa[CORSIA]="4";
			} else if (scommessa[1].equals(sceltaCorsia[4])){
				scommessa[CORSIA]="5";
			} else if (scommessa[1].equals(sceltaCorsia[5])){
				scommessa[CORSIA]="6";
			}
			scommessa[TIPO] =  (String) tipoScommessa.getSelectedItem();
			buonfine=true;
			try{
				Integer.parseInt((String)sceltaImporto.getText());
			} catch (NumberFormatException e){
				JOptionPane.showMessageDialog(null,"Hai inserito dei valori non validi. Riprova.");
				buonfine=false;
			}
		}

		return scommessa;
			
	}
		
	
	/**
	 * Metodo per la seconda scommessa. 
	 * @return array di stringhe con i parametri inseriti da utente per la seconda scommessa
	 * */
	@Override
	public String[] chiediSecondaScommessa() {
		
		String[] scommessa = new String[DIM_SCOM];
		
		JOptionPane panel = new JOptionPane("Vuoi effettuare una seconda scommessa? ");
		panel.setOptions(new String[]  {"Si", "No"});
		panel.setBackground(Color.decode(colorePannelli));
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		JDialog dialog = panel.createDialog(frame, "Seconda scommessa");
		dialog.setVisible(true);
		Object value = panel.getValue();
		
		if(value != null & value.equals("Si")){ scommessa = chiediScommessa(); }
		else{ scommessa[TIPO] = "N"; }
		
		return scommessa;
	}
	
	
	private static Object lock = new Object();
	/**
	 * Metodo che chiede a utente di truccare una corsia
	 * @param l'arraylist di carte azione in mano al giocatore corrispondente
	 * @return l'array di stringhe con i valori inseriti dall'utente
	 * */
	@Override
	public String[] chiediTrucca( ArrayList<Azione> carteAzione) throws NullPointerException{
		
		String[] scelta = new String[DIM_TRUC];
		JPanel panelCarta = new JPanel();
		panelCarta.setBackground(Color.decode(colorePannelli));
		panelCarta.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode(colorePannelli));
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JPanel panelConferma = new JPanel();
		panelConferma.setBackground(Color.decode(colorePannelli));
		JLabel label = new JLabel("Quale carta vuoi giocare?");
		JLabel label2 = new JLabel("Su quale corsia la vuoi giocare?");
		JButton conferma = new JButton("Conferma");
		
		JComboBox sceltaCarta = new JComboBox();
		for (int i=0;i< carteAzione.size();i++){
			JLabel descrizioneCarta = new JLabel(this.carteAzione[carteAzione.get(i).getIndice()]);
			panelCarta.add(descrizioneCarta);
			String nomeCarta = carteAzione.get(i).getNome();
			sceltaCarta.addItem(nomeCarta);
		}
		
		JComboBox sceltaCorsia = new JComboBox();
		sceltaCorsia.addItem("1");
		sceltaCorsia.addItem("2");
		sceltaCorsia.addItem("3");
		sceltaCorsia.addItem("4");
		sceltaCorsia.addItem("5");
		sceltaCorsia.addItem("6");
		
		frame.add(panelCarta, BorderLayout.NORTH);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(panelConferma, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		conferma.addActionListener(new ActionListener(){
				@Override	
				public void actionPerformed(ActionEvent arg0) {
				synchronized(lock){
				lock.notifyAll();
				frame.dispose();
				}
				
				}
			
		});
		
		panel.setLayout(new FlowLayout());
		panel.add(label);
		panel.add(sceltaCarta);
		panelConferma.add(conferma);
		
		panel.add(label2);
		panel.add(sceltaCorsia);
		
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(null);

		Thread t = new Thread() {
        	public void run() {
            	synchronized(lock) {
                	while (frame.isVisible()){
                    	try {
                        	lock.wait();
                    	} catch (InterruptedException e) {
                    		log.log(Level.SEVERE, e.getMessage(), e);
                    	}
                	}
            	}
        	}
    	};
    	t.start();


    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, e.getMessage(), e);
		}

    	int indiceCarta=0;
		for(int i=0;i<carteAzione.size();i++){
				if(sceltaCarta.getSelectedItem().equals(carteAzione.get(i).getNome())){ 
					indiceCarta=i+1;
				}
		}
		if (indiceCarta==1) {
			scelta[CARTA_SCELTA] = "1";
		}else {
			scelta[CARTA_SCELTA]="2";
		}
		scelta[CORSIA] = sceltaCorsia.getSelectedItem().toString();
		
		return scelta;
	}

	/**
	 * Verifica quale istanza di HorseFeverEvent è stata notificata e in base a questo gestisce la
	 * visualizzazione dell'evento sulla board
	 */
	@Override
	public void notify(HorseFeverEvent e) {

	   /*
	    * cerca il giocatore 
	    * se tipo 0 (NUOVO) inserisce il giocatore nel primo spazio vuoto
	    * se tipo 1 (MODIFICA) cerca il nome del giocatore corrispondente e aggiorna i dati
	    * se tipo 4 (PERSO) elimina il giocatore
	    * 
	    */
       if(e instanceof eventoGiocatore){
    	   
    	   boolean inserito=false;
    	   int i=0;
    	   
    	   String nomeGioc=((eventoGiocatore) e).getNome();
           long denari=((eventoGiocatore) e).getDenari();
    	   int pv=((eventoGiocatore) e).getPv();
    	   String scuderia=((eventoGiocatore) e).getScuderia();
    	   int tipo=((eventoGiocatore) e).getTipo();
    	   
    	   if(tipo==eventoGiocatore.NUOVO){
    		  
        	   while(!inserito){
        		   
        		   if(board.getNomeGiocatore(i).equals(" ")){
        			   
        			   inserito=true;
        			   board.setNomeGiocatore(nomeGioc, i);
        			   board.setPV(pv,i);
        			   board.setDenari(denari, i);
        			   board.setNomeScuderia(scuderia, i);
        		   }
        		   i++;
        	   }   
    	   }
    	   else if(tipo==eventoGiocatore.MODIFICA){
    		   
    		   while(!inserito){
    			   if(board.getNomeGiocatore(i).equals(nomeGioc)){
    			   
    				   inserito=true;
    				   board.setPV(pv,i);
    				   board.setDenari(denari, i);
    				   board.setNomeScuderia(scuderia, i);
    		       }
    			   i++;
    		   }
    	   }	   
    	  else if(tipo==eventoGiocatore.PERSO){
    		  
    		  while(!inserito){
   			   if(board.getNomeGiocatore(i).equals(nomeGioc)){
   			   
   				   inserito=true;
   				   board.setPV(0,i);
   				   board.setDenari(0, i);
   				   board.setNomeScuderia("Perdente", i);
   		       }
   			   i++;
   		   }
    	  }
    	  else if(tipo==eventoGiocatore.PRIMO){
    		   
    		   while(!inserito){
       			   if(board.getNomeGiocatore(i).equals(nomeGioc)){
       			   
       				   inserito=true;
       				   board.settaAreaNotifica("\n"+e.rappresentazione());
       		       }
       			   i++;
    		   
    	   }	   
    	  }
    	  else if(tipo==eventoGiocatore.VINTO){
    		  
    		  this.stampaMessaggio("Ha vinto il giocatore: "+nomeGioc+" !!!");
    		  
    		  
    	  }
    	  else if(tipo==eventoGiocatore.PAGAMENTO){
    		  
    		  eventiPagamento.add((eventoGiocatore) e);
    		  
    	  }
    		 	   
    	}
     
       
       else if(e instanceof eventoScommessa){
    	   
    	   String nomeGioc=((eventoScommessa) e).getNomeGiocatore();  
    	   long denari=((eventoScommessa) e).getDenari();  
    	   int numCorsia=((eventoScommessa) e).getCorsia();  
    	   char tipoScom=((eventoScommessa) e).getTipoScommessa();
    	   String tipoSc="";
    	   
    	   if(tipoScom=='V'){ tipoSc="Vincente"; }
    	   if(tipoScom=='P'){ tipoSc="Piazzato"; }
;    	   board.settaAreaNotifica("Il giocatore "+nomeGioc+" ha scommesso "+denari+"\nsulla corsia "+numCorsia+"  "+tipoSc+"\n ");
       
       }
       
       else if(e instanceof eventoTrucca){
    	   
    	   String nomeGioc=((eventoTrucca) e).getNomeGiocatore();  
    	   int numCorsia=((eventoTrucca) e).getCorsia();
    	   
    	   board.settaAreaNotifica("Il giocatore "+nomeGioc+"\n ha truccato la corsia numero: "+numCorsia+"\n");
    	   
       }
       
       else if(e instanceof eventoCorsa){
    	   
    	   if(first){
    		   first=false;
    		   threadCorsa=new ThreadCorsa(this);
    		   threadCorsa.start();
    	   }
    	   
           eventiCorsa.add((eventoCorsa) e);
    	   
    	   
    	   
       }
       
       else if(e instanceof eventoEffettoAvvenuto){
    	   
    	   String rappresentazione=((eventoEffettoAvvenuto) e).rappresentazione();
    	   
    	   board.settaAreaNotifica(""+rappresentazione+"\n");
       }
       
       else if(e instanceof eventoQuotazioni){
    	   
    	   //se sono le quotazioni iniziali vengono immediatamente stampate
    	   if(quotIniziali){
    		   
    		   quotIniziali=false;
        	   
    		   String[][] tabellaQuot= ((eventoQuotazioni) e).getTabellaQuot();
        	   String[] quot=new String[NUM_CORSIE];
        	   
        	   for(int i=0;i<NUM_CORSIE;i++){
        		   
        		   quot[i]=tabellaQuot[i][1];
        	   }
        	   
        	   board.settaAreaQuotazioni(quot);
    	    }
    	    else{ quotazioni.add((eventoQuotazioni) e); }
    		   
    	   }
    	 
  
       else if(e instanceof eventoArrivi){
    	   
    	   arrivati.add((eventoArrivi) e);
    	 
       }
       
       else if(e instanceof eventoTurno){
    	   
    	   int turnoCor=((eventoTurno) e).getTurnoCorrente();
    	   int turniTot=((eventoTurno) e).getTurniTotali();
    	   
    	   board.setTurni(turnoCor, turniTot);
       }
       
       else if(e instanceof eventoResetGrafico){
    	   
    	   board.reset();
       }
		
	}
	/**
	 * Setta il booleano first
	 * @param il valore booleano da assegnare a first
	 * */
	public void setFirst(boolean first) {
		this.first = first;
	}
	/**
	 * Metodo per la stampa di messaggi generici all'utente
	 * @param il messaggio da stampare
	 * */
	@Override
	public void stampaMessaggio(String messaggio) {
		UIManager.put("OptionPane.background",Color.decode(colorePannelli));
		UIManager.put("Panel.background",Color.decode(colorePannelli));
		JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", JOptionPane.PLAIN_MESSAGE);
		
		
	}
	/**
	 * Estrae un eventoQuotazioni dalla coda corrispondente
	 * @return l'eventoQuotazioni in testa alla coda
	 * */
	public eventoQuotazioni getEventoQuotaz(){
		
        eventoQuotazioni e;
		
		if(quotazioni.size()>0){
			
			e=quotazioni.get(0);
			quotazioni.remove(0);
			
			return e;
			
		}
		
		return null;
	}

	/**
	 * Estrae un eventoArrivi dalla coda corrispondente
	 * @return l'eventoArrivi in testa alla coda
	 * */
    public eventoArrivi getEventoArrivi(){
		
		eventoArrivi e;
		
		if(arrivati.size()>0){
			
			e=arrivati.get(0);
			arrivati.remove(0);
			
			return e;
			
		}
		
		return null;
	}
    
    /**
	 * Ritorna la coda di eventoCorsa
	 * @return la coda di eventoCorsa
	 * */
	public Queue<eventoCorsa> getEventiCorsa() {
		return eventiCorsa;
	}
	/**
	 * Estrae un eventoCorsa dalla coda corrispondente
	 * @return l'eventoCorsa in testa alla coda
	 * */
	public eventoCorsa getEventoCorsa(){
		
		eventoCorsa e;
		
		if(eventiCorsa.size()>0){
			
			e=eventiCorsa.poll();
			
			return e;
			
		}
		
		return null;
	}
	/**
	 * Estrae un eventoPagamento dalla coda corrispondente
	 * @return l'eventoPagamento in testa alla coda
	 * */
    public eventoGiocatore getEventoPagamento(){
		
		eventoGiocatore e;
		
		if(eventiPagamento.size()>0){
			
			e=eventiPagamento.poll();
			
			return e;
			
		}
		
		return null;
	}

    /**
	 * Estrae un eventoPagamento dalla coda corrispondente
	 * @return l'eventoPagamento in testa alla coda
	 * */
	public Queue<eventoGiocatore> getEventiPagamento() {
		return eventiPagamento;
	}
	/***/
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Metodo con l'unico scopo di bloccare la logica tra una fase e l'altra se l'utente non preme
	 * invio.
	 * @param messaggio da stampare connesso a questo stop della logica
	 * */
	@Override
	public void prosegui(String messaggio) {
		
		UIManager.put("OptionPane.background",Color.decode(colorePannelli));
		UIManager.put("Panel.background",Color.decode(colorePannelli));
	
		
		if(messaggio.equals("E' terminata la fase di corsa.")){
		
		    try {
				threadCorsa.join();
                
			} catch (InterruptedException e) {
				
				log.log(Level.SEVERE, e.getMessage(), e);
			}
			
			JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", JOptionPane.PLAIN_MESSAGE);
			
			 
			first=true;
			
		}
		else{
			JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}	

}
