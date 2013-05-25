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
		Azione pluto = new Azione("eee","fff","ggg","hhh");
		GUIView view = new GUIView();
		String[] trucco = new String[2];
		String[] scommessa = new String[3];
		ArrayList<Azione> carteAzione = new ArrayList<Azione>();
		carteAzione.add(pippo);
		carteAzione.add(pluto);
		
		
		
		//Testato
		//scommessa = view.chiediSecondaScommessa();
		//System.out.println(scommessa[0]);
		//System.out.println(scommessa[1]);
		//System.out.println(scommessa[2]);
		
		
		
		
		//NonTestato
		trucco = view.chiediTrucca(carteAzione);
		System.out.println(trucco[0]);
		System.out.println(trucco[1]);
	}
}
