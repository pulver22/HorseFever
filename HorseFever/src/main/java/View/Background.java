package View;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Background extends JPanel {
	
	Image sfondo;
	
	
	public Background(String wallpaper){
		
		
		ImageIcon ii=new ImageIcon(this.getClass().getResource(wallpaper));
		sfondo=ii.getImage();
		setLayout(new BorderLayout());
	}
    
	public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(sfondo, 0,0,1200, 700, null); 
    }
}

