package View;

/*
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
*/

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import horsefever.Azione;
import eventi.*;


public class GUIView implements View{

	private Board board;
	private Queue<eventoCorsa> eventiCorsa=new ConcurrentLinkedQueue<eventoCorsa>();
	private Queue<eventoGiocatore> eventiPagamento= new ConcurrentLinkedQueue<eventoGiocatore>();
	private ArrayList<eventoArrivi> arrivati=new ArrayList<eventoArrivi>();
	private ArrayList<eventoQuotazioni> quotazioni=new ArrayList<eventoQuotazioni>();
	private ThreadCorsa threadCorsa;
	private boolean quotIniziali=true;
	private boolean first=true;
	private JFrame frame=new JFrame("Horse Fever");
	private final String[] sceltaCorsia = {"Nero","Blu","Verde","Rosso","Giallo","Bianco"};
	
	//Immagini Carte Azione
	private ImageIcon[] carteAzione=new ImageIcon[23];
	
	public GUIView(){
		
		board=new Board();
		frame.add(board);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(1200,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		carteAzione[0]=new ImageIcon(getClass().getResource("carteAzione/Magna Velocitas.png"));
		carteAzione[1]=new ImageIcon(getClass().getResource("carteAzione/Fortuna Benevola.png"));
		carteAzione[2]=new ImageIcon(getClass().getResource("carteAzione/Flagellum Fulguris.png"));
		carteAzione[3]=new ImageIcon(getClass().getResource("carteAzione/Herba Magica.png"));
		carteAzione[4]=new ImageIcon(getClass().getResource("carteAzione/In Igni Veritas.png"));
		carteAzione[5]=new ImageIcon(getClass().getResource("carteAzione/Fustis et Radix.png"));
		carteAzione[6]=new ImageIcon(getClass().getResource("carteAzione/Vigor Ferreum.png"));
		
		carteAzione[7]=new ImageIcon(getClass().getResource("carteAzione/Globus Obscurus.png"));
		carteAzione[8]=new ImageIcon(getClass().getResource("carteAzione/Aqua Putrida.png"));
		carteAzione[9]=new ImageIcon(getClass().getResource("carteAzione/Serum Maleficum.png"));
		carteAzione[10]=new ImageIcon(getClass().getResource("carteAzione/Venenum Veneficum.png"));
		carteAzione[11]=new ImageIcon(getClass().getResource("carteAzione/Mala Tempora.png"));
		carteAzione[12]=new ImageIcon(getClass().getResource("carteAzione/XIII.png"));
		carteAzione[13]=new ImageIcon(getClass().getResource("carteAzione/Felix Infernalis.png"));
		
		carteAzione[14]=new ImageIcon(getClass().getResource("carteAzione/Alfio Allibratore.png"));
		carteAzione[15]=new ImageIcon(getClass().getResource("carteAzione/Friz Finden.png"));
		carteAzione[16]=new ImageIcon(getClass().getResource("carteAzione/Steven Sting.png"));
		carteAzione[17]=new ImageIcon(getClass().getResource("carteAzione/Rochelle Recherche.png"));
	}
	
	private static Object lock2 = new Object();
	@Override
	public String[] chiediScommessa() {
		
		boolean buonfine= false;
		String[] scommessa = new String[3];
		
		while (!buonfine){
			final JFrame frame = new JFrame("Make your Choice!!");

			JButton invioScommessa = new JButton("Conferma");

			JLabel importo = new JLabel("Inserisci Importo");
			JTextField sceltaImporto = new JTextField();

			JLabel corsia = new JLabel("Scegli la corsia : ");
			JComboBox corsieDisponibili = new JComboBox();
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[0]));
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[1]));
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[2]));
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[3]));
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[4]));
			corsieDisponibili.addItem(String.valueOf(sceltaCorsia[5]));

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



			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,2));


			JPanel panel_conferma = new JPanel();
			panel_conferma.setLayout(new FlowLayout());

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
						while (frame.isVisible())
							try {
								lock2.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
					}
				}
			};
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scommessa[0] = (String) sceltaImporto.getText();
			scommessa[1] = (String) corsieDisponibili.getSelectedItem();
			if (scommessa[1].equals(sceltaCorsia[0])){
				scommessa[1]="1";
			} else if (scommessa[1].equals(sceltaCorsia[1])){
				scommessa[1]="2";
			} else if (scommessa[1].equals(sceltaCorsia[2])){
				scommessa[1]="3";
			} else if (scommessa[1].equals(sceltaCorsia[3])){
				scommessa[1]="4";
			} else if (scommessa[1].equals(sceltaCorsia[4])){
				scommessa[1]="5";
			} else if (scommessa[1].equals(sceltaCorsia[5])){
				scommessa[1]="6";
			}
			scommessa[2] =  (String) tipoScommessa.getSelectedItem();
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
		
	

	@Override
	public String[] chiediSecondaScommessa() {
		
		String[] scommessa = new String[3];
		
		JOptionPane panel = new JOptionPane("Vuoi effettuare una seconda scommessa? ");
		panel.setOptions(new String[]  {"Si", "No"});
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		JDialog dialog = panel.createDialog(frame, "Seconda scommessa");
		dialog.setVisible(true);
		Object value = panel.getValue();
		
		if(value != null & value.equals("Si")){ scommessa = chiediScommessa(); }
		else{ scommessa[2] = "N"; }
		
		return scommessa;
	}
	
	
	private static Object lock = new Object();
	@Override
	public String[] chiediTrucca( ArrayList<Azione> carteAzione) throws NullPointerException{
		
		String[] scelta = new String[2];
		JPanel panelCarta = new JPanel();
		panelCarta.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		final JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JPanel panelConferma = new JPanel();
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
                	while (frame.isVisible())
                    	try {
                        	lock.wait();
                    	} catch (InterruptedException e) {
                        	e.printStackTrace();
                    	}
            	}
        	}
    	};
    	t.start();


    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	int indiceCarta=0;
		for(int i=0;i<carteAzione.size();i++){
				if(sceltaCarta.getSelectedItem().equals(carteAzione.get(i).getNome())){ 
					indiceCarta=i+1;
				}
		}
		if (indiceCarta==1) {
			scelta[0] = "1";
		}else {
			scelta[0]="2";
		}
		scelta[1] = sceltaCorsia.getSelectedItem().toString();
		
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
        	   String[] quot=new String[6];
        	   
        	   for(int i=0;i<6;i++){
        		   
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
	
	public void setFirst(boolean first) {
		this.first = first;
	}
    
	@Override
	public void stampaMessaggio(String messaggio) {
		JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", 1);
		
	}

	public eventoQuotazioni getEventoQuotaz(){
		
        eventoQuotazioni e;
		
		if(quotazioni.size()>0){
			
			e=quotazioni.get(0);
			quotazioni.remove(0);
			
			return e;
			
		}
		
		return null;
	}

    public eventoArrivi getEventoArrivi(){
		
		eventoArrivi e;
		
		if(arrivati.size()>0){
			
			e=arrivati.get(0);
			arrivati.remove(0);
			
			return e;
			
		}
		
		return null;
	}
    
	public Queue<eventoCorsa> getEventiCorsa() {
		return eventiCorsa;
	}
	
	public eventoCorsa getEventoCorsa(){
		
		eventoCorsa e;
		
		if(eventiCorsa.size()>0){
			
			e=eventiCorsa.poll();
			
			return e;
			
		}
		
		return null;
	}
	
    public eventoGiocatore getEventoPagamento(){
		
		eventoGiocatore e;
		
		if(eventiPagamento.size()>0){
			
			e=eventiPagamento.poll();
			
			return e;
			
		}
		
		return null;
	}

	public Queue<eventoGiocatore> getEventiPagamento() {
		return eventiPagamento;
	}
	public Board getBoard() {
		return board;
	}
	@Override
	public void prosegui(String messaggio) {
		
		if(messaggio.equals("E' terminata la fase di corsa.")){
		
		    try {
				threadCorsa.join();
                
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", 1);
			first=true;
			
		}
		else{
			JOptionPane.showMessageDialog(null, ""+messaggio,"Attenzione", 1);
			
		}
		
	}	

}
