package View;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

public class ScommessaGUI {
  
	public static void main(String[] args){
		

		final JFrame frame = new JFrame("Make your Choice!!");
		final String[] scommessa = new String[3];
		
		JButton invioScommessa = new JButton("Conferma");

		JLabel importo = new JLabel("Inserisci Importo");
		JTextField sceltaImporto = new JTextField();
		scommessa[0] = sceltaImporto.getText();
		
		
		JLabel corsia = new JLabel("Scegli la corsia : ");
		JComboBox corsieDisponibili = new JComboBox();
		corsieDisponibili.addItem("Corsia N.ro 1");
		corsieDisponibili.addItem("Corsia N.ro 2");
		corsieDisponibili.addItem("Corsia N.ro 3");
		corsieDisponibili.addItem("Corsia N.ro 4");
		corsieDisponibili.addItem("Corsia N.ro 5");
		corsieDisponibili.addItem("Corsia N.ro 6");
		corsieDisponibili.setEditable(true);
				
		scommessa[1] =  (String) corsieDisponibili.getSelectedItem();
		
		JLabel tipo = new JLabel("Scegli se piazzato o vincente : ");
		JComboBox tipoScommessa = new JComboBox();
		tipoScommessa.addItem("Piazzato");
		tipoScommessa.addItem("Vincente");
		tipoScommessa.setEditable(true);
				
		scommessa[2] =  (String) tipoScommessa.getSelectedItem();
		
		
		invioScommessa.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae){
				//visualizzare messaggio conferma invio e chiudere tutto
				JOptionPane.showMessageDialog(null,"Hai scommesso " +scommessa[0] +" denari, su  " +scommessa[1] +", come " +scommessa[2]);
				//vista.scommetti(scommessa);
				frame.dispose();
			}
		});
		
		
		
			
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2));
		
		
		JPanel panel_conferma = new JPanel();
		panel_conferma.setLayout(new FlowLayout());
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE) ;
		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel_conferma, BorderLayout.SOUTH);
		
		panel.add(importo);
		panel.add(sceltaImporto);
		panel.add(corsia);
		panel.add(corsieDisponibili);
		panel.add(tipo);
		panel.add(tipoScommessa);
		
		panel_conferma.add(invioScommessa);
		
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
			}
	}