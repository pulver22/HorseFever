package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Pedina implements ActionListener {

	private static final int LIMITE_PLANCIA=1046;
	private static final int TRAGUARDO=900;
	private static final int DX_INIZ=37;
	private static final int DX_FIN=48;
	
	private static final int DT_SCATTO=300;
	
	private int x;
	private int y;
	private int dx=DX_INIZ;
	private Image immagine;
	private Timer timer;
	private Board board;
	private int posizioni=0;
	private int posizioniprec=0;
	private boolean arrivata=false;
	

	/**
	 * Inizializza la pedina.
	 * @param x
	 * @param y
	 * @param il nome dell'immagine
	 * @param il riferimento alla board
	 * */
	public Pedina(int x, int y, String nomeImmagine,Board board){
		
		this.x=x;
		this.y=y;
		this.board=board;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(nomeImmagine));
        immagine = ii.getImage();
        
		
	}

	/**
	 * Muove le pedine 
	 * @param numPos nuova posizione delle pedine
	 * posizioni= numero scatti da fare
	 * posizioniprec= posizione precedente delle pedine
	 */
	public void muovi(int numPos){

	   this.posizioni=numPos-posizioniprec;
	   posizioniprec=posizioniprec+posizioni;
	  
	   timer=new Timer(DT_SCATTO,this);
	   timer.start();
	}
	
	//getter e setter
	/***/
	public void setPosizioniprec(int posizioniprec) {
		this.posizioniprec = Integer.valueOf(posizioniprec);
	}
	/***/
	public int getX() {
		return x;
	}
	/***/
	public void setX(int x) {
		this.x = Integer.valueOf(x);
	}
	/***/
	public int getY() {
		return y;
	}
	/***/
	public void setY(int y) {
		this.y = Integer.valueOf(y);
	}
	/***/
	public Image getImmagine() {
		return immagine;
	}
	/***/
	public void setImmagine(Image immagine) {
		this.immagine = immagine;
	}
	/***/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(posizioni>0){
		
			posizioni--;
			if(x+dx<LIMITE_PLANCIA){
			  
				if(x+dx>TRAGUARDO-DX_INIZ-1){
					dx=DX_FIN;	
				}
				
				x+=dx;
				if(x>TRAGUARDO){ arrivata=true; }
			}
			else{ 
				  x=LIMITE_PLANCIA;
			}
			board.repaint();
		}
		else{
			timer.stop();
		}
	}
	/***/
	public void setDx(int dx) {
		this.dx = Integer.valueOf(dx);
	}
	/***/
	public void setArrivata(boolean x){
		
		this.arrivata=x;
	}
	/***/
	public boolean getArrivata(){
		
		return arrivata;
	}
}
