package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Board extends JFrame {

	private Pedina[] pedine=new Pedina[6];
	
	private JTextArea areaNotifica;
	private JTextArea quotazioni;
	
	private JSplitPane pannelloDivisore;
    private JScrollPane scroll;
	private JScrollPane quotazioniScrollPane;
	
    private Background background=new Background("elementiBoard/sfondo.jpg");
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloGiocatore=new JPanel();
	private JPanel pannelloLavagna=new JPanel(new BorderLayout());
	private JPanel pannelloSinistra=new JPanel();
	
	private JLabel labelPV=new JLabel("PV:");
	private JLabel labelDenari=new JLabel("Denari:");
	private JLabel labelNomeGiocatore=new JLabel("Giocatore:");
	private JLabel labelScuderia=new JLabel("Scuderia:");
	
	private JTextField PV=new JTextField("                 ");
	private JTextField nomeGiocatore=new JTextField("                 ");
	private JTextField denari=new JTextField("                 ");
	private JTextField scuderia=new JTextField("                 ");
	
	private Image plancia;
	private Image cartaMov;
	private Image titolo;
	private Image[] piazzamento=new Image[3];
	
	private JButton[] bottoneProva=new JButton[6];
	
	private boolean[] stampaPiazzamento=new boolean[3];
	
	private int[] yPiazzamento=new int[3];
	
	private Font fontPersonale=new Font("Monaco",Font.BOLD,20);

	
    public Board(){
    	
    
    	//inizializzazione Pedine
    	
    	pedine[0]=new Pedina(455,90,"pedine/nero.png",this);
    	pedine[1]=new Pedina(455,140,"pedine/blu.png",this);
    	pedine[2]=new Pedina(455,195,"pedine/verde.png",this);
    	pedine[3]=new Pedina(455,245,"pedine/rosso.png",this);
    	pedine[4]=new Pedina(455,295,"pedine/giallo.png",this);
    	pedine[5]=new Pedina(455,345,"pedine/bianco.png",this);
		
    	
    	//Area Notifica
   
    	areaNotifica=new JTextArea();
    	areaNotifica.setEditable(false);
    	quotazioni=new JTextArea();
    	quotazioni.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    
    	pannelloNotifica.setBounds(50,120,350,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.decode("#d6a45f"));
    	
    	//Plancia
       
        ImageIcon ii=new ImageIcon(this.getClass().getResource("elementiBoard/plancia.jpg"));
		plancia=ii.getImage();	
		
		
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,280);
		pannelloGiocatore.setBackground(Color.decode("#d6a45f"));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatore"));
		
		PV.setEditable(false);
		denari.setEditable(false);
		nomeGiocatore.setEditable(false);
		scuderia.setEditable(false);
		pannelloGiocatore.setLayout(new GridLayout(4,2));
		pannelloGiocatore.add(labelNomeGiocatore);
		pannelloGiocatore.add(nomeGiocatore);
		pannelloGiocatore.add(labelScuderia);
		pannelloGiocatore.add(scuderia);
		pannelloGiocatore.add(labelPV);
		pannelloGiocatore.add(PV);
		pannelloGiocatore.add(labelDenari);
		pannelloGiocatore.add(denari);
		pannelloGiocatore.setVisible(true);
		
		//Lavagna
		
		pannelloLavagna.setBounds(50,370,350,300);
		pannelloLavagna.setBackground(Color.decode("#d6a45f"));
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		pannelloLavagna.setVisible(true);
		
		quotazioni.append("Quotazioni:\n");
		quotazioni.setFont(fontPersonale);
		quotazioniScrollPane=new JScrollPane(quotazioni);
		
		pannelloSinistra.setBackground(Color.white);
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
    	this.add(background);
    
    	this.setResizable(false);
    	this.setTitle("Horse Fever");
    	this.setSize(1200,700);
    	this.setVisible(true);
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,80, null);
        g2d.drawImage(cartaMov, 60,420,null);
        g2d.drawImage(titolo, 170,8,null);
        
        for(int i=0;i<3;i++){
        	
        	if(stampaPiazzamento[i]==true){
        	
        		g2d.drawImage(piazzamento[i],995,yPiazzamento[i],null);
        	}
        }
        
        g2d.drawImage(pedine[0].getImmagine(),pedine[0].getX(),pedine[0].getY(),this);
        g2d.drawImage(pedine[1].getImmagine(),pedine[1].getX(),pedine[1].getY(),this);
        g2d.drawImage(pedine[2].getImmagine(),pedine[2].getX(),pedine[2].getY(),this);
        g2d.drawImage(pedine[3].getImmagine(),pedine[3].getX(),pedine[3].getY(),this);
        g2d.drawImage(pedine[4].getImmagine(),pedine[4].getX(),pedine[4].getY(),this);
        g2d.drawImage(pedine[5].getImmagine(),pedine[5].getX(),pedine[5].getY(),this);
        
    
	}
	
	//Getter e Setter

	public void stampaPiazzamento(int numCorsia, int posizione){
		
		posizione--;
		numCorsia--;
		//le corsie sono distanziate di 50px l'una dall'altra e la prima parte a 86px
		yPiazzamento[posizione]=numCorsia*50+86;
		stampaPiazzamento[posizione]=true;
		
	}
	public void setNomeScuderia(String scuderia) {
		this.scuderia.setText(scuderia);
	}

	/**
	 * Scrive sulla JTextArea delle quotazioni
	 * @param messaggio
	 */
	public void settaAreaQuotazioni(int[] quot){
		
		quotazioni.append("\nNero:   1:"+quot[0]);
		quotazioni.append("\nBlu:    1:"+quot[1]);
		quotazioni.append("\nVerde:  1:"+quot[2]);
		quotazioni.append("\nRosso:  1:"+quot[3]);
		quotazioni.append("\nGiallo: 1:"+quot[4]);
		quotazioni.append("\nBianco: 1:"+quot[5]);
	}
	
	/**
	 * setta i pv del giocatore
	 * @param PV
	 */
	public void setPV(int PV){
		
		this.PV.setText(""+PV);
	}
	
	/**
	 * setta il nome del giocatore
	 * @param nome
	 */
    public void setNomeGiocatore(String nome){
    	
    	this.nomeGiocatore.setText(nome);
    }
	
	/**
	 * setta i denari del giocatore
	 * @param denari
	 */
    public void setDenari(long denari){
		
		this.denari.setText(""+denari);
	}
	/**
	 * Scrive sulla JTextArea delle notifiche della partita
	 * @param messaggio
	 */
	public void settaAreaNotifica(String messaggio){
		
		areaNotifica.append(messaggio);
	}
	
	/**
	 * viene passata la stringa con il nome dell'immagine movimento che viene stampata sulla lavagna
	 * @param cartaMovimento
	 */
	public void setImmagineMovimento(String cartaMovimento){
		
		ImageIcon imgMov=new ImageIcon(getClass().getResource("carteMovimento/"+cartaMovimento));
		cartaMov=imgMov.getImage();
		repaint();
		
	}
	
	public Pedina getPedina(int i) {
		return pedine[i];
	}

}
