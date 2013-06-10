package adapter;

import horsefever.Partita;

import javax.swing.JOptionPane;

import View.GUIView;
import View.TextView;
import View.View;
import controller.Controller;

public class TestRete {

	public static void main(String[] args){
		
		int numGiocatori=2;
		
		View tv=new TextView();
		final AdapterReteServer server=new AdapterReteServer(2);
		new Thread() {
			@Override
			public void run(){
				server.startServer();
			}
		}.start();
		
		ClientThread client1=new ClientThread("localhost",tv);
		ClientThread client2=new ClientThread("localhost",tv);
		client1.start();
		client2.start();
		Partita p=new Partita(numGiocatori,server);
		Controller c = new Controller(p);
		c.setAdapter(server);
		c.start();
	}
}
