package adapter;

import java.net.ServerSocket;
import java.net.Socket;

public class AdapterClientHandler{

	Socket clientSocket;
	ServerSocket serverSocket;
	
	public AdapterClientHandler(Socket s, ServerSocket ss){
		this.clientSocket=s;
		this.serverSocket=ss;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	
	
}
