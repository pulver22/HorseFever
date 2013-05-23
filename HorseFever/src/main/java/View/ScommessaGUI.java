package View;

import javax.swing.*;
import java.awt.event.*;

public class ScommessaGUI{
  
	public static void main(String[] args){
		final Object[] possibilità = {"Vincente", "Piazzato"};
		JFrame frame = new JFrame("Make your Choice!!");
		JButton importo = new JButton("Scegli l'importo");
		JButton numCorsia = new JButton("Scegli la corsia");
		JButton tipoScommessa = new JButton("Scegli se piazzato o vincente");
		importo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String str = JOptionPane.showInputDialog(null, "Inserisci l'importo : ", "Scelta Importo", 1);
				if(str != null)
					JOptionPane.showMessageDialog(null, "Hai scommesso : " + str,"Scelta Importo", 1);
				else
					JOptionPane.showMessageDialog(null, "Hai annullato la scelta.", "Scelta importo", 1);
			}
		});
		
		numCorsia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String str = JOptionPane.showInputDialog(null, "Scegli la corsia : ", "Scelta Corsia", 1);
				if(str != null)
					JOptionPane.showMessageDialog(null, "Hai scommesso sulla corsia : " + str,"Scelta Corsia", 1);
				else
					JOptionPane.showMessageDialog(null, "Hai annullato la scelta.", "Scelta Corsia", 1);
			}
		});
		
		tipoScommessa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String str = (String) JOptionPane.showInputDialog(null, "Scegli se piazzato o vincente : ", "Scelta tipo di Scommessa", JOptionPane.PLAIN_MESSAGE,null, possibilità,null);
				if(str != null)
					JOptionPane.showMessageDialog(null, "Hai deciso di scommettere come: " + str,"Scelta tipo di Scommessa", 1);
				else
					JOptionPane.showMessageDialog(null, "Hai annullato la scelta.", "Scelta tipo di Scommessa", 1);
			}
		});
		JPanel panel = new JPanel();
		panel.add(importo);
		panel.add(numCorsia);
		frame.add(tipoScommessa);
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	}