package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


@SuppressWarnings("serial")
public class Board extends JFrame {

	private Pedina[] pedine=new Pedina[6];
	
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
	private JLabel labelNomeGiocatore=new JLabel("Giocatore:");
	private JLabel labelScuderia=new JLabel("Scuderia:");
	
	private JTextField PV=new JTextField("            ");
	private JTextField nomeGiocatore=new JTextField("Mario Rossi    ");
	private JTextField denari=new JTextField("            ");
	private JTextField scuderia=new JTextField(" BiancoNero      ");
	
	private Image plancia;
	
	private Font fontPersonale=new Font("Monaco",Font.BOLD,20);
	
	
	JMenuBar menubar=new JMenuBar();
	JMenu menu=new JMenu("Opzioni");
	JMenuItem prova1=new JMenuItem("Prova1");
	JMenuItem prova2=new JMenuItem("Prova2");
	
    public Board(){
    	
    	
    	//inizializzazione Pedine
    	pedine[0]=new Pedina(455,90,"nero.png");
    	pedine[1]=new Pedina(455,140,"blu.png");
    	pedine[2]=new Pedina(455,195,"verde.png");
    	pedine[3]=new Pedina(455,245,"rosso.png");
    	pedine[4]=new Pedina(455,295,"giallo.png");
    	pedine[5]=new Pedina(455,345,"bianco.png");
    	
    	//Menu
    	menu.add(prova1);
		menu.add(prova2);
		menubar.add(menu);
		setJMenuBar(menubar);
		
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
    	pannelloNotifica.setBackground(Color.decode("#d6a45f"));
    	
    	//Plancia
    	pannelloPlancia.setBounds(450,200,650,300);
        pannelloPlancia.setVisible(true);
        pannelloPlancia.setBackground(Color.white);
       
        ImageIcon ii=new ImageIcon(this.getClass().getResource("plancia.jpg"));
		plancia=ii.getImage();	
		
		
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,270);
		pannelloGiocatore.setBackground(Color.decode("#d6a45f"));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatore"));
		
		PV.setEditable(false);
		denari.setEditable(false);
		nomeGiocatore.setEditable(false);
		scuderia.setEditable(false);
		pannelloGiocatore.setLayout(new GridLayout(5,2));
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
		pannelloLavagna.setBounds(50,350,350,300);
		pannelloLavagna.setBackground(Color.decode("#d6a45f"));
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		pannelloLavagna.setVisible(true);

		ImageIcon imgMov=new ImageIcon(getClass().getResource("horseFever-85.png"));
		cartaMovimento=new JLabel(imgMov);
		quotazioni.append("Quotazioni:\n");
		quotazioni.setFont(fontPersonale);
		immagineCartaMovimento = new JScrollPane(cartaMovimento);
		quotazioniScrollPane=new JScrollPane(quotazioni);
		
		pannelloDivisore=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,immagineCartaMovimento,quotazioniScrollPane);
	    pannelloDivisore.setDividerLocation(175); 
	    pannelloDivisore.setEnabled(false);
	    
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
		prova.settaAreaNotifica("prova prova \n");
		prova.settaAreaNotifica("prova prova ");
		prova.settaAreaQuotazioni("    \n");
		prova.settaAreaQuotazioni("    1:2\n");
		prova.settaAreaQuotazioni("    1:3\n");
		prova.settaAreaQuotazioni("    1:4\n");
		prova.settaAreaQuotazioni("    1:5\n");
		prova.settaAreaQuotazioni("    1:6\n");
		prova.settaAreaQuotazioni("    1:7 \n");
		prova.setPV(100);
		prova.setDenari(40000);
		
	
	}
	
	/**
	 * Scrive sulla JTextArea delle quotazioni
	 * @param messaggio
	 */
	public void settaAreaQuotazioni(String messaggio){
		
		quotazioni.append(messaggio);
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
	
	public void setImmagineMovimento(ImageIcon immagine){
		
		
		
	}
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,80, null);
        //g2d.drawImage(movimento, 100,450,null);
        
        g2d.drawImage(pedine[0].getImmagine(),pedine[0].getX(),pedine[0].getY(),this);
        g2d.drawImage(pedine[1].getImmagine(),pedine[1].getX(),pedine[1].getY(),this);
        g2d.drawImage(pedine[2].getImmagine(),pedine[2].getX(),pedine[2].getY(),this);
        g2d.drawImage(pedine[3].getImmagine(),pedine[3].getX(),pedine[3].getY(),this);
        g2d.drawImage(pedine[4].getImmagine(),pedine[4].getX(),pedine[4].getY(),this);
        g2d.drawImage(pedine[5].getImmagine(),pedine[5].getX(),pedine[5].getY(),this);
        
    
	}
	
	public Pedina[] getPedine() {
		return pedine;
	}

}
