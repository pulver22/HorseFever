package View;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;

public class Board extends JFrame {

	private JTextArea areaNotifica;
    private JScrollPane scroll;
	private JPanel pannelloNotifica=new JPanel();
	private JPanel pannelloPlancia=new JPanel();
	
    public Board(){
    	
    	//toglie il Layout di default del JFrame
    	getContentPane().setLayout(null);
    	
    	areaNotifica=new JTextArea();
    	//areaNotifica.setEditable(false);
    	scroll=new JScrollPane(areaNotifica);
    	scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
    	
    	
    	pannelloNotifica.setBounds(50,50,300,250);
    	pannelloNotifica.setLayout(new BorderLayout());
    	pannelloNotifica.setBorder ( new TitledBorder ( new EtchedBorder (), "Area Notifica" ) );
        pannelloNotifica.add(scroll);
    	pannelloNotifica.setVisible(true);
    	pannelloNotifica.setBackground(Color.ORANGE);
    	
    	pannelloPlancia.setBounds(450,50,650,300);
        pannelloPlancia.setVisible(true);
        pannelloPlancia.setBackground(Color.white);
        
    	this.add(pannelloNotifica);
    	this.add(pannelloPlancia);
    	
    	this.setBackground(Color.gray);
    	this.setResizable(false);
    	this.setSize(1200,700);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
	
	public static void main(String[] args) {
		
		Board prova=new Board();

	}

}
