package View;

import java.awt.*;
import javax.swing.*;


public class Pedina {

	private int x;
	private int y;
	private final int dx=10;
	private Image immagine;
	
	private final int DIM_PEDINA=20;
	

	public Pedina(int x, int y, String nomeImmagine){
		
		this.x=x;
		this.y=y;
		
		ImageIcon ii = new ImageIcon(this.getClass().getResource(nomeImmagine));
        immagine = ii.getImage();
        immagine.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		
	}

	public void muovi(int numPos){
		
		x+=(numPos*dx);
		
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
}
