package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Board extends JFrame {

	private JTextArea areaNotifica;
	private JTextArea quotazioni;
	
	private JSplitPane pannelloDivisore;
    private JScrollPane scroll;
    private JScrollPane immagineCartaMovimento;
	private JScrollPane quotazioniScrollPane;
	
    private Background background=new Background("sfondo.jpg");
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloPlancia=new JPanel();
	private JPanel pannelloGiocatore=new JPanel();
	private JPanel pannelloLavagna=new JPanel(new BorderLayout());
	
	private JLabel cartaMovimento;
	private JLabel labelPV=new JLabel("PV:");
	private JLabel labelDenari=new JLabel("Denari:");
	
	private JTextField PV=new JTextField("    0   ");
	private JTextField denari=new JTextField("   default     ");
	
	private Image plancia;
	
	private Font fontPersonale=new Font("Monaco",Font.BOLD,20);
	
    public Board(){
    	
    	//Area Notifica
    	
    	areaNotifica=new JTextArea();
    	areaNotifica.setEditable(false);
    	quotazioni=new JTextArea();
    	quotazioni.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    
    	pannelloNotifica.setBounds(50,50,350,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.decode("#883456"));
    	
    	//Plancia
    	pannelloPlancia.setBounds(450,50,650,300);
        pannelloPlancia.setVisible(true);
        pannelloPlancia.setBackground(Color.white);
       
        ImageIcon ii=new ImageIcon(this.getClass().getResource("plancia.jpg"));
		plancia=ii.getImage();	
		
		
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,270);
		pannelloGiocatore.setBackground(Color.decode("#883456"));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatore"));
		pannelloGiocatore.add(labelPV);
		pannelloGiocatore.add(PV);
		pannelloGiocatore.add(labelDenari);
		pannelloGiocatore.add(denari);
		pannelloGiocatore.setVisible(true);
		
		//Lavagna
		pannelloLavagna.setBounds(50,350,350,300);
		pannelloLavagna.setBackground(Color.decode("#883456"));
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		pannelloLavagna.setVisible(true);

		ImageIcon imgMov=new ImageIcon(getClass().getResource("horseFever-85.png"));
		cartaMovimento=new JLabel(imgMov);
		quotazioni.append("Quotazioni:\n");
		quotazioni.setFont(fontPersonale);
		immagineCartaMovimento = new JScrollPane(cartaMovimento);
		quotazioniScrollPane=new JScrollPane(quotazioni);
		
		pannelloDivisore=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,immagineCartaMovimento,quotazioniScrollPane);
	    pannelloDivisore.setDividerLocation(190); 
	
	    
	    pannelloLavagna.add(pannelloDivisore,BorderLayout.CENTER);
	    
		this.add(pannelloNotifica);
    	this.add(pannelloLavagna);
    	//this.add(pannelloPlancia);
    	this.add(pannelloGiocatore);
    	this.add(background);
    
    	
    	this.setResizable(false);
    	this.setTitle("Horse Fever");
    	this.setSize(1200,700);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	
	public static void main(String[] args) {
		
		Board prova=new Board();
		prova.settaAreaNotifica("prova prova polvara culo\n");
		prova.settaAreaNotifica("prova prova polvara culo");
		prova.settaAreaQuotazioni("    \n");
		prova.settaAreaQuotazioni("    1:2\n");
		prova.settaAreaQuotazioni("    1:3\n");
		prova.settaAreaQuotazioni("    1:4\n");
		prova.settaAreaQuotazioni("    1:5\n");
		prova.settaAreaQuotazioni("    1:6\n");
		prova.settaAreaQuotazioni("    1:7 \n");
		
	
	}
	
	/**
	 * Scrive sulla JTextArea delle quotazioni
	 * @param messaggio
	 */
	public void settaAreaQuotazioni(String messaggio){
		
		quotazioni.append(messaggio);
	}
	
	/**
	 * Scrive sulla JTextArea delle notifiche della partita
	 * @param messaggio
	 */
	public void settaAreaNotifica(String messaggio){
		
		areaNotifica.append(messaggio);
	}
	
	public void setImmagineMovimento(ImageIcon immagine){
		
		
		
	}
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,50, null);
        //g2d.drawImage(movimento, 100,450,null);
        
        
    
	}
	

}
