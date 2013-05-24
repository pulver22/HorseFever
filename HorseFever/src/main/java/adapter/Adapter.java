package adapter;

import java.util.ArrayList;
import View.*;
import eventi.HorseFeverEvent;

public class Adapter {

	ArrayList<View> viewRegistrate=new ArrayList<View>();
	
	public void notify(HorseFeverEvent e){
		
		for (View v: viewRegistrate){
			v.notify(e);
		}
		
		
	}
	
	
	public void addView(View v){
		viewRegistrate.add(v);
	}
	public void removeView(int i){
		viewRegistrate.remove(i);
	}
}
