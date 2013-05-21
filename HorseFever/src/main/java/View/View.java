package View;

import horsefever.*;

public interface View {
	
	//chiamati dal controller
	public Scommessa chiediScommessa();
	
	public int[] chiediTrucca();
	
	
	//chiamati dal model
	public void notify(HorseFeverEvent e);
}
