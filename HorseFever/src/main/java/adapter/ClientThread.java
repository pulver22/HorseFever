package adapter;

import View.View;

public class ClientThread extends Thread{

	AdapterReteClient client;
	String serverIP;
	
	public ClientThread(String serverIP, View v){
		super();
		this.serverIP=serverIP;
		client=new AdapterReteClient();
		client.addView(v);
	}
	@Override
	public void run() {
		try {
			this.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.connetti(serverIP);
		client.start();
	}

}
