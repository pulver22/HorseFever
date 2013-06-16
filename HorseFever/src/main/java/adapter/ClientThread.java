package adapter;

import java.util.logging.Level;
import java.util.logging.Logger;

import View.View;

public class ClientThread extends Thread{

	private AdapterReteClient client;
	private String serverIP;
	
	private static final Logger log = Logger.getLogger(AdapterReteServer.class.getName());
	
	/**
	 * Crea un thread per ogni client associandolo all'indirizzo dell'IP e registrandosi
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
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		client.connetti(serverIP);
		client.start();
	}

}
