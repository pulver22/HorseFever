package View;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class Board extends JFrame {

	private JTextArea areaNotifica;
    private JScrollPane scroll;
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloPlancia=new JPanel();
	private JPanel pannelloGiocatore=new JPanel();
	private JPanel pannelloLavagna=new JPanel();
	private Image plancia;
	private Image sfondo;
	
    public Board(){
    	
    	//toglie il Layout di default del JFrame
    	getContentPane().setLayout(null);
    	
    	//Area Notifica
    	
    	areaNotifica=new JTextArea();
    	areaNotifica.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    
    	pannelloNotifica.setBounds(50,50,300,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new BevelBorder(BevelBorder.RAISED), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.ORANGE);
    	
    	//Plancia
    	pannelloPlancia.setBounds(450,50,650,300);
        pannelloPlancia.setVisible(true);
        pannelloPlancia.setBackground(Color.white);
       
        ImageIcon ii=new ImageIcon(this.getClass().getResource("plancia.jpg"));
		plancia=ii.getImage();
		
		ImageIcon ii2=new ImageIcon(this.getClass().getResource("sfondo.jpg"));
		sfondo=ii2.getImage();
		
		//Giocatore
		
		pannelloGiocatore.setBounds(450,400,500,250);
		pannelloGiocatore.setBackground(Color.orange);
		pannelloGiocatore.setBorder (new TitledBorder (new BevelBorder(BevelBorder.RAISED),"Giocatore"));
		
		
		//Lavagna
		pannelloLavagna.setBounds(50,400,300,250);
		pannelloLavagna.setBackground(Color.orange);
		pannelloLavagna.setBorder ( new TitledBorder (new BevelBorder(BevelBorder.RAISED), "Lavagna" ) );
		
		this.add(pannelloGiocatore);
    	this.add(pannelloNotifica);
    	this.add(pannelloLavagna);
    	//this.add(pannelloPlancia);
    	this.add(pannelloGiocatore);
    	
    	
    	
    	this.setBackground(Color.gray);
    	this.setResizable(false);
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
        g2d.drawImage(sfondo, 0,0,null);
        g2d.drawImage(plancia, 450,50, null);
        
    
	}
	

}
