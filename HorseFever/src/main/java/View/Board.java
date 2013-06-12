package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

@SuppressWarnings("serial")
public class Board extends JPanel {

	private Pedina[] pedine=new Pedina[6];
	
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
	
	private JTextField[] pv=new JTextField[6];
	private JTextField[] nomeGiocatore=new JTextField[6];
	private JTextField[] denari=new JTextField[6];
	private JTextField[] scuderia=new JTextField[6];
	private JTextField numTurno=new JTextField("   ");
	
	private Image plancia;
	private Image cartaMov;
	private Image titolo;
	private Image sfondo;
	private Image[] piazzamento=new Image[3];
	private ImageIcon[] carteMovimento=new ImageIcon[23];
	
	private boolean[] stampaPiazzamento=new boolean[3];
	private boolean tutteArrivate=false;
	
	private int[] yPiazzamento=new int[3];
	
	private Font fontPersonale=new Font("Monaco",Font.BOLD,20);

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
        pannelloTurni.setBackground(Color.decode("#c38335"));
        numTurno.setEditable(false);
        
        
    	//inizializzazione Pedine
    	
    	pedine[0]=new Pedina(455,67,"pedine/nero.png",this);
    	pedine[1]=new Pedina(455,117,"pedine/blu.png",this);
    	pedine[2]=new Pedina(455,172,"pedine/verde.png",this);
    	pedine[3]=new Pedina(455,222,"pedine/rosso.png",this);
    	pedine[4]=new Pedina(455,272,"pedine/giallo.png",this);
    	pedine[5]=new Pedina(455,322,"pedine/bianco.png",this);
		
    	//inizializzazione Carta Movimento
    	
    	for(int i=0;i<23;i++){
    		
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
    	pannelloNotifica.setBackground(Color.decode("#c38335"));
    	
    	//Sfondo
  
    	ImageIcon ii=new ImageIcon(this.getClass().getResource("elementiBoard/background.jpg"));
		sfondo=ii.getImage();
		
    	//Plancia
       
        ii=new ImageIcon(this.getClass().getResource("elementiBoard/plancia.jpg"));
		plancia=ii.getImage();	
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,280);
		pannelloGiocatore.setBackground(Color.decode("#c38335"));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatori"));
		pannelloGiocatore.setLayout(new GridLayout(7,4));
		
		pannelloGiocatore.add(labelNomeGiocatore);
		pannelloGiocatore.add(labelScuderia);
		pannelloGiocatore.add(labelPV);
		pannelloGiocatore.add(labelDenari);
		
		for(int i=0;i<6;i++){
		
			nomeGiocatore[i]=new JTextField(" ");
			pannelloGiocatore.add(nomeGiocatore[i]);
			scuderia[i]=new JTextField(" ");
			pannelloGiocatore.add(scuderia[i]);
			pv[i]=new JTextField(" ");
			pannelloGiocatore.add(pv[i]);
			denari[i]=new JTextField(" ");
			pannelloGiocatore.add(denari[i]);
		}
		
		for(int i=0;i<6;i++){
				
			pv[i].setEditable(false);
			denari[i].setEditable(false);
			nomeGiocatore[i].setEditable(false);
			scuderia[i].setEditable(false);
		
		}
		
		pannelloGiocatore.setVisible(true);
		
		//Lavagna
		
		pannelloLavagna.setBounds(50,370,350,300);
		pannelloLavagna.setBackground(Color.decode("#c38335"));
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
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sfondo, 0,0, null);
    }    
 
    /**
     * Disegna la plancia, carta movimento, titolo, pedine ed eventuali piazzamenti
     */
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,60, null);
        g2d.drawImage(cartaMov, 60,400,null);
        g2d.drawImage(titolo, 170,-14,null);
        
        for(int i=0;i<3;i++){
        	
        	if(stampaPiazzamento[i]==true){
        	
        		g2d.drawImage(piazzamento[i],904,yPiazzamento[i],null);
        	}
        }
        
        g2d.drawImage(pedine[0].getImmagine(),pedine[0].getX(),pedine[0].getY(),this);
        g2d.drawImage(pedine[1].getImmagine(),pedine[1].getX(),pedine[1].getY(),this);
        g2d.drawImage(pedine[2].getImmagine(),pedine[2].getX(),pedine[2].getY(),this);
        g2d.drawImage(pedine[3].getImmagine(),pedine[3].getX(),pedine[3].getY(),this);
        g2d.drawImage(pedine[4].getImmagine(),pedine[4].getX(),pedine[4].getY(),this);
        g2d.drawImage(pedine[5].getImmagine(),pedine[5].getX(),pedine[5].getY(),this);
        
        
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
		
		for(int i=0;i<6;i++){
			
			pedine[i].setArrivata(false);
			pedine[i].setX(455);
			pedine[i].setDx(37);
			pedine[i].setPosizioniprec(0);
			if(i<3){ stampaPiazzamento[i]=false; }
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
	 * @param messaggio
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
 			   this.nomeGiocatore[i].setBackground(Color.decode("#FFFF33"));
 		   }
 		   i++;
 	   }   
		
	}
	
	//Getter e Setter
	
	public void setNomeScuderia(String scuderia,int indice) {
		this.scuderia[indice].setText(scuderia);
	}

	public String getNomeScuderia(int indice){
		
		return this.scuderia[indice].getText();
	}
	
	public void setPV(int puntiVita,int indice){
		
		this.pv[indice].setText("  "+puntiVita);
	}
	 
	public String getPV(int indice){
	    	
	    	return this.pv[indice].getText();
	}
	
    public void setNomeGiocatore(String nome,int indice){
    	
    	this.nomeGiocatore[indice].setText(nome);
    }
	
    public String getNomeGiocatore(int indice){
    	
    	return this.nomeGiocatore[indice].getText();
    }
 
    public void setDenari(long denari,int indice){
		
		this.denari[indice].setText(""+denari);
	}
    
    public String getDenari(int indice){
    	
    	return this.denari[indice].getText();
    }
		
	public Pedina getPedina(int i) {
		return pedine[i];
	}

	public void setTutteArrivate(boolean a){
		
		this.tutteArrivate=a;
	}
	
	public boolean getTutteArrivate(){
		
		return this.tutteArrivate;
	}
	
	public void setTurni(int turnoCor, int turniTot){
		
		numTurno.setText(turnoCor+"/"+turniTot);
	}
}
