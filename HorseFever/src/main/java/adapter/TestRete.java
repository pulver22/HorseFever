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
		
		View tv=new GUIView();
		final AdapterReteServer server=new AdapterReteServer(2,tv);
		ClientThread client1=new ClientThread("localhost",tv);
		client1.start();
		server.startServer();
		/*
		new Thread() {
			@Override
			public void run(){
				server.startServer();
			}
		}.start();
		*/
		/*
		ClientThread client2=new ClientThread("localhost",tv);
		client2.start();
		*/
		Partita p=new Partita(numGiocatori,server);
		Controller c = new Controller(p);
		c.setAdapter(server);
		c.start();
	}
}
