package adapter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class AdapterClientHandler{

	Socket clientSocket;
	ServerSocket serverSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public AdapterClientHandler(Socket s, ServerSocket ss){
		this.clientSocket=s;
		this.serverSocket=ss;
		try {
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public Socket getClientSocket() {
		return clientSocket;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	
	
}
