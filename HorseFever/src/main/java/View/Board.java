package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private static final int NUM_CORSIE=6;
	private static final int MAX_GIOC=NUM_CORSIE;
	private static final int NUM_PIAZZ=3;
	private static final int NUM_MOVIMENTI=23;
	
	private Pedina[] pedine=new Pedina[NUM_CORSIE];
	
	private JTextArea areaNotifica;
	private JTextArea quotazioni;
	
	private JSplitPane pannelloDivisore;
    private JScrollPane scroll;
	private JScrollPane quotazioniScrollPane;
	
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloGiocatore=new JPanel();
	private JPanel pannelloLavagna=new JPanel(new BorderLayout());
	private JPanel pannelloSinistra=new JPanel();
	private JPanel pannelloTurni=new JPanel();
	
	private JLabel labelPV=new JLabel("    PV:");
	private JLabel labelDenari=new JLabel("    Denari:");
	private JLabel labelNomeGiocatore=new JLabel("    Giocatore:");
	private JLabel labelScuderia=new JLabel("    Scuderia:");
	
	private JTextField[] pv=new JTextField[MAX_GIOC];
	private JTextField[] nomeGiocatore=new JTextField[MAX_GIOC];
	private JTextField[] denari=new JTextField[MAX_GIOC];
	private JTextField[] scuderia=new JTextField[MAX_GIOC];
	private JTextField numTurno=new JTextField("   ");
	
	private Image plancia;
	private Image cartaMov;
	private Image titolo;
	private Image sfondo;
	private Image[] piazzamento=new Image[NUM_PIAZZ];
	private ImageIcon[] carteMovimento=new ImageIcon[NUM_MOVIMENTI];
	
	private boolean[] stampaPiazzamento=new boolean[NUM_PIAZZ];
	private boolean tutteArrivate=false;
	
	private int[] yPiazzamento=new int[NUM_PIAZZ];
	
	private Font fontPersonale=new Font("Monaco",Font.BOLD,20);

	private static final String COLORE_PANNELLI="#c38335";
	private static final String COLORE_EVIDENZIA="#FFFF33";
	private static final int X_PEDINE_INIZ=455;
	
	/**
	 * Inizializza tutti gli elementi grafici
	 */
    public Board(){
    	
    	this.setLayout(null);
    	
    	//numero turni
    	
    	pannelloTurni.setBounds(1130,10,60,50);
    	pannelloTurni.setLayout(new BorderLayout());
    	pannelloTurni.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Turno" ) );
        pannelloTurni.add(numTurno);
        pannelloTurni.setVisible(true);
        pannelloTurni.setBackground(Color.decode(COLORE_PANNELLI));
        numTurno.setEditable(false);
        
        
    	//inizializzazione Pedine
    	
    	pedine[0]=new Pedina(X_PEDINE_INIZ,67,"pedine/nero.png",this);
    	pedine[1]=new Pedina(X_PEDINE_INIZ,117,"pedine/blu.png",this);
    	pedine[2]=new Pedina(X_PEDINE_INIZ,172,"pedine/verde.png",this);
    	pedine[3]=new Pedina(X_PEDINE_INIZ,222,"pedine/rosso.png",this);
    	pedine[4]=new Pedina(X_PEDINE_INIZ,272,"pedine/giallo.png",this);
    	pedine[5]=new Pedina(X_PEDINE_INIZ,322,"pedine/bianco.png",this);
		
    	//inizializzazione Carta Movimento
    	
    	for(int i=0;i<carteMovimento.length;i++){
    		
    		carteMovimento[i]=new ImageIcon(getClass().getResource("carteMovimento/horseFever-"+(i+83)+".png")); 
    	}
    	
    	//Area Notifica
   
    	areaNotifica=new JTextArea();
    	areaNotifica.setEditable(false);
    	quotazioni=new JTextArea();
    	quotazioni.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    	scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
    		public void adjustmentValueChanged(AdjustmentEvent e) {  
    		e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
    		}});
    	
    	pannelloNotifica.setBounds(50,120,350,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.decode(COLORE_PANNELLI));
    	
    	//Sfondo
  
    	ImageIcon ii=new ImageIcon(this.getClass().getResource("elementiBoard/background.jpg"));
		sfondo=ii.getImage();
		
    	//Plancia
       
        ii=new ImageIcon(this.getClass().getResource("elementiBoard/plancia.jpg"));
		plancia=ii.getImage();	
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,280);
		pannelloGiocatore.setBackground(Color.decode(COLORE_PANNELLI));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatori"));
		pannelloGiocatore.setLayout(new GridLayout(7,4));
		
		pannelloGiocatore.add(labelNomeGiocatore);
		pannelloGiocatore.add(labelScuderia);
		pannelloGiocatore.add(labelPV);
		pannelloGiocatore.add(labelDenari);
		
		for(int i=0;i<MAX_GIOC;i++){
		
			nomeGiocatore[i]=new JTextField(" ");
			pannelloGiocatore.add(nomeGiocatore[i]);
			scuderia[i]=new JTextField(" ");
			pannelloGiocatore.add(scuderia[i]);
			pv[i]=new JTextField(" ");
			pannelloGiocatore.add(pv[i]);
			denari[i]=new JTextField(" ");
			pannelloGiocatore.add(denari[i]);
		}
		
		for(int i=0;i<MAX_GIOC;i++){
				
			pv[i].setEditable(false);
			denari[i].setEditable(false);
			nomeGiocatore[i].setEditable(false);
			scuderia[i].setEditable(false);
		
		}
		
		pannelloGiocatore.setVisible(true);
		
		//Lavagna
		
		pannelloLavagna.setBounds(50,370,350,300);
		pannelloLavagna.setBackground(Color.decode(COLORE_PANNELLI));
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		pannelloLavagna.setVisible(true);
		
		quotazioni.setFont(fontPersonale);
		quotazioniScrollPane=new JScrollPane(quotazioni);
		
		pannelloSinistra.setBackground(Color.decode("#ededed"));
		pannelloDivisore=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pannelloSinistra,quotazioniScrollPane);
	    pannelloDivisore.setDividerLocation(175); 
	    pannelloDivisore.setEnabled(false);
	    
	    pannelloLavagna.add(pannelloDivisore,BorderLayout.CENTER);
	    
	    //titolo
	    
	    ii=new ImageIcon(this.getClass().getResource("elementiBoard/titolo_horse.png"));
		titolo=ii.getImage();	
		
		//tesserine piazzamenti
		
		ii=new ImageIcon(this.getClass().getResource("piazzamenti/primo.png"));
		piazzamento[0]=ii.getImage();
		ii=new ImageIcon(this.getClass().getResource("piazzamenti/secondo.png"));
		piazzamento[1]=ii.getImage();	
		ii=new ImageIcon(this.getClass().getResource("piazzamenti/terzo.png"));
		piazzamento[2]=ii.getImage();	
	
		this.add(pannelloNotifica);
    	this.add(pannelloLavagna);
    	this.add(pannelloGiocatore);
    	this.add(pannelloTurni);
    	
    	
    	pannelloNotifica.setDoubleBuffered(true);
    	pannelloLavagna.setDoubleBuffered(true);
    	pannelloGiocatore.setDoubleBuffered(true);
    	pannelloTurni.setDoubleBuffered(true);
    	
    	this.setDoubleBuffered(true);
    }
    
    /**
     * Disegna lo sfondo della finestra
     * @param la grafica Graphics
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sfondo, 0,0, null);
    }    
 
    /**
     * Disegna la plancia, carta movimento, titolo, pedine ed eventuali piazzamenti
     * @param la Grafica Graphics
     */
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,60, null);
        g2d.drawImage(cartaMov, 60,400,null);
        g2d.drawImage(titolo, 170,-14,null);
        
        for(int i=0;i<3;i++){
        	
        	if(stampaPiazzamento[i]){
        	
        		g2d.drawImage(piazzamento[i],904,yPiazzamento[i],null);
        	}
        }
        
        for (int i=0; i<pedine.length;i++){
        	g2d.drawImage(pedine[i].getImmagine(),pedine[i].getX(),pedine[i].getY(),this);
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
	}
	
	/**
	 *  resetta la plancia impostando che:
	 *  tutte le pedine non sono arrivate
	 *  immagine pedine ai valori iniziali
	 *  i piazzamenti non vengono stampati
	 */
	public void reset(){
		
		this.setTutteArrivate(false);
		
		int dxIniz=37;
		
		for(int i=0;i<pedine.length;i++){
			
			pedine[i].setArrivata(false);
			pedine[i].setX(X_PEDINE_INIZ);
			pedine[i].setDx(dxIniz);
			pedine[i].setPosizioniprec(0);
			if(i<NUM_PIAZZ){ stampaPiazzamento[i]=false; }
			this.resetAreaNotifica();
			repaint();
		}
	}
	
	/**
	 * Stampa il piazzamento 
	 * @param numCorsia (corsia in cui è arrivato il cavallo che si è piazzato)
	 * @param posizione (1°,2° o 3° posto)
	 */
	public void stampaPiazzamento(int numCorsia, int posizione){
		
		posizione--;
		numCorsia--;
		//le corsie sono distanziate di 50px l'una dall'altra e la prima parte a 86px
		yPiazzamento[posizione]=numCorsia*50+66;
		stampaPiazzamento[posizione]=true;
		this.invalidate();
		
	}
	
	/**
	 * Scrive sulla JTextArea delle quotazioni
	 * @param L'array di stringhe che rappresenta le quotazioni
	 */
	public void settaAreaQuotazioni(String[] quot){
		
		quotazioni.setText(null);
		quotazioni.append("Quotazioni:\n");
		quotazioni.append("\nNero:   "+quot[0]);
		quotazioni.append("\nBlu:    "+quot[1]);
		quotazioni.append("\nVerde:  "+quot[2]);
		quotazioni.append("\nRosso:  "+quot[3]);
		quotazioni.append("\nGiallo: "+quot[4]);
		quotazioni.append("\nBianco: "+quot[5]);
		this.invalidate();
	}
	
	/**
	 * Scrive sulla JTextArea delle notifiche della partita
	 * @param messaggio
	 */
	public void settaAreaNotifica(String messaggio){
		
		areaNotifica.append(messaggio);
	}
	
	/**
	 *  Pulisce la JTextArea delle notifiche
	 */
	public void resetAreaNotifica(){
		
		areaNotifica.setText("");
		
	}
	
	/**
	 * viene passata la stringa con il nome dell'immagine movimento che viene stampata sulla lavagna
	 * @param cartaMovimento
	 */
	public void setImmagineMovimento(String cartaMovimento){
		
		int indice=Integer.parseInt(cartaMovimento);
		cartaMov=carteMovimento[indice].getImage();
		this.invalidate();
		
	}
	
	/**
	 * Colora lo sfondo del nome del giocatore
	 * @param ind indice del JTextField corrispondente
	 */
	public void setGiocatoreEvidenziato(String nomeGioc){
		
		boolean inserito=false;
		int i=0;
		
		while(!inserito){
 		   
 		   if(this.getNomeGiocatore(i).equals(nomeGioc)){
 			   
 			   inserito=true;
 			   this.nomeGiocatore[i].setBackground(Color.decode(COLORE_EVIDENZIA));
 		   }
 		   i++;
 	   }   
		
	}
	
	//Getter e Setter
	/**
	 * 
	 * */
	public void setNomeScuderia(String scuderia,int indice) {
		this.scuderia[indice].setText(scuderia);
	}

	/**
	 * 
	 * */
	public String getNomeScuderia(int indice){
		
		return this.scuderia[indice].getText();
	}
	
	/**
	 * 
	 * */
	public void setPV(int puntiVita,int indice){
		
		this.pv[indice].setText("  "+puntiVita);
	}
	 
	/**
	 * 
	 * */
	public String getPV(int indice){
	    	
	    	return this.pv[indice].getText();
	}
	
	/**
	 * 
	 * */
    public void setNomeGiocatore(String nome,int indice){
    	
    	this.nomeGiocatore[indice].setText(nome);
    }
	
    /**
     * 
     * */
    public String getNomeGiocatore(int indice){
    	
    	return this.nomeGiocatore[indice].getText();
    }
 
    /**
     * 
     * */
    public void setDenari(long denari,int indice){
		
		this.denari[indice].setText(""+denari);
	}
    
    /**
     * 
     * */
    public String getDenari(int indice){
    	
    	return this.denari[indice].getText();
    }
	
    /**
     * 
     * */
	public Pedina getPedina(int i) {
		return pedine[i];
	}

	/**
	 * 
	 * */
	public void setTutteArrivate(boolean a){
		
		this.tutteArrivate=a;
	}
	
	/**
	 * 
	 * */
	public boolean getTutteArrivate(){
		
		return this.tutteArrivate;
	}
	
	/**
	 * 
	 * */
	public void setTurni(int turnoCor, int turniTot){
		
		numTurno.setText(turnoCor+"/"+turniTot);
	}
}
