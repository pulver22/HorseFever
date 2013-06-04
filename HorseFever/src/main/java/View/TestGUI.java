package View;

import horsefever.*;
import adapter.Adapter;
import adapter.AdapterLocale;
import controller.*;

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
		
		Adapter adapter=new AdapterLocale();
		GUIView view = new GUIView("Alex","Blanco",8000);
		adapter.addView(view);
		Partita p=new Partita(6,adapter);
		Controller controller=new Controller(p);
		
		
		controller.setAdapter(adapter);
		p.setAdapter(adapter);
		
		
		p.preparazione();
		//controller.start();
		
		//controller.FaseScommesse();
		controller.FaseCorsa();
		
		//for(int i=0;i<6;i++)
		//view.getBoard().getPedina(i).muovi(10);
		//view.getBoard().getPedina(1).muovi(3);
		
		/*
		Azione pippo = new Azione("aaa","bbb","ccc","ddd",'a');
		Azione pluto = new Azione("eee","fff","ggg","hhh",'b');
		String[] trucco = new String[2];
		String[] scommessa = new String[3];
		ArrayList<Azione> carteAzione = new ArrayList<Azione>();
		carteAzione.add(pippo);
		carteAzione.add(pluto);
		
		*/
		
		//Testato
		//scommessa = view.chiediScommessa();
		//System.out.println(scommessa[0]);
		//System.out.println(scommessa[1]);
		//System.out.println(scommessa[2]);
		//trucco = view.chiediTrucca(carteAzione);
		//System.out.println(trucco[0]);
		//System.out.println(trucco[1]);
		//view.stampaMessaggio("Ciao Baldo");
		
		
		
		
		//NonTestato
		
		
	}
}
