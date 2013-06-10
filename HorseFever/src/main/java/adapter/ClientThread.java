package adapter;

import View.View;

public class ClientThread extends Thread{

	AdapterReteClient client;
	
	public ClientThread(String serverIP, View v){
		super();
		client=new AdapterReteClient();
		client.connetti(serverIP);
		client.addView(v);
	}
	@Override
	public void run() {
		client.start();
	}

}
