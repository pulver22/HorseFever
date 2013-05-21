package View;

import horsefever.*;

public interface View {
	
	//chiamati dal controller
	public long[] chiediScommessa();
	
	public int[] chiediTrucca();
	
	
	//chiamati dal model
	public void notify(HorseFeverEvent e);
}
