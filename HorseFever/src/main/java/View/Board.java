package View;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class Board extends JFrame {

	private JTextArea areaNotifica;
	private JTextArea quotazioni=new JTextArea();
    private JScrollPane scroll;
    private Background background=new Background("sfondo.jpg");
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloPlancia=new JPanel();
	private JPanel carteMovimento=new JPanel();
	private JPanel pannelloGiocatore=new JPanel();
	private JPanel pannelloLavagna=new JPanel();
	private Image plancia;
	private Image movimento;
	
    public Board(){
    	
    	//toglie il Layout di default del JFrame
    	//getContentPane().setLayout(null);
    	
    	
    	//Area Notifica
    	
    	areaNotifica=new JTextArea();
    	areaNotifica.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    
    	pannelloNotifica.setBounds(50,50,350,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.decode("#FFFFFF"));
    	
    	//Plancia
    	pannelloPlancia.setBounds(450,50,650,300);
        pannelloPlancia.setVisible(true);
        pannelloPlancia.setBackground(Color.white);
       
        ImageIcon ii=new ImageIcon(this.getClass().getResource("plancia.jpg"));
		plancia=ii.getImage();	
		ii=new ImageIcon(this.getClass().getResource("horseFever-103.jpg"));
		movimento=ii.getImage();
		
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,380,650,270);
		pannelloGiocatore.setBackground(Color.decode("#FFFFFF"));
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatore"));
		pannelloGiocatore.setVisible(true);
		
		//Lavagna
		pannelloLavagna.setBounds(50,350,350,300);
		pannelloLavagna.setBackground(Color.decode("#FFFFFF"));
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		pannelloLavagna.setVisible(true);
		carteMovimento.setBackground(Color.black);
		pannelloLavagna.add(carteMovimento);
		pannelloLavagna.add(quotazioni);
		
    	
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

	}
	
	public void paint(Graphics g) {
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(plancia, 450,50, null);
        g2d.drawImage(movimento, 60,360,null);
        
        
    
	}
	

}
