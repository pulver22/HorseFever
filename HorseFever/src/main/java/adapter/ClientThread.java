package adapter;

import View.View;

public class ClientThread extends Thread{

	AdapterReteClient client;
	String serverIP;
	
	/**
	 * Crea un thread per ogni client associandolo all'indirizzo dell'IP e reegistrandosi
	 * @param serverIP
	 * @param v
	 */
	public ClientThread(String serverIP, View v){
		super();
		this.serverIP=serverIP;
		client=new AdapterReteClient();
		client.addView(v);
	}
	
	/**
	 * Lancia il thread del client, aspettando però tre secondi in modo tale che il server possa essere lanciato
	 */
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
