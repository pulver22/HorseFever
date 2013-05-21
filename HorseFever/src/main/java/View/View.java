package View;

import horsefever.*;

public interface View {
	
	//chiamati dal controller
	public String[] chiediScommessa();
	public String[] chiediSecondaScommessa();
	
	public int[] chiediTrucca();
	
	
	//chiamati dal model
	public void notify(HorseFeverEvent e);
}
