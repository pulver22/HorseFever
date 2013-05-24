package View;

import horsefever.Azione;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TestGUI {

	public static void main(String[] args){
		Azione pippo = new Azione("aaa","bbb","ccc","ddd");
		GUIView view = new GUIView();
		
		
		ArrayList<Azione> carteAzione = new ArrayList<Azione>();
		carteAzione.add(pippo);
		
		
		
		//Testato
		//view.chiediScommessa();
		//view.chiediSecondaScommessa();
		
		
		
		//NonTestato
		view.chiediTrucca(carteAzione);
	}
}
