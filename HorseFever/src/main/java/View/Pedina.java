package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Pedina implements ActionListener {

	private int x;
	private int y;
	private int dx=37;
	private Image immagine;
	private Timer timer;
	private Board board;
	private int posizioni=0;
	private int posizioniprec=0;
	private boolean arrivata=false;
	

	public Pedina(int x, int y, String nomeImmagine,Board board){
		
		this.x=x;
		this.y=y;
		this.board=board;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(nomeImmagine));
        immagine = ii.getImage();
        
		
	}

	public void muovi(int numPos){
		
	   this.posizioni=numPos-posizioniprec;
	   posizioniprec=posizioniprec+posizioni;
	   
	  	
	   timer=new Timer(350,this);
	    
	   timer.start();
		  
		
		
	}
	
	//getter e setter
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Image getImmagine() {
		return immagine;
	}

	public void setImmagine(Image immagine) {
		this.immagine = immagine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(posizioni>0){
		
			posizioni--;
			if(x+dx<1046){
			  
				if(x+dx>862){
					dx=48;
					
					
				}
				
				x+=dx;
				if(x>900) arrivata=true;
			}
			else{ 
				  
				  x=1046;
			}
			board.repaint();
		}
		else{
			timer.stop();
		}
	}
	
	public void setArrivata(boolean x){
		
		this.arrivata=x;
	}
	public boolean getArrivata(){
		
		return arrivata;
	}
}
