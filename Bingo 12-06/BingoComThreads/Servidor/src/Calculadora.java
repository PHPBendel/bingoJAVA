import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.Date;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import bd.daos.Bingo;
//import bd.dbos.Bingos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Calculadora extends JFrame {
	protected static final long serialVersionUID = 1L;

	
	
	

	public Calculadora() {

		super("Calculadora bin�ria");
		
		

		this.addWindowListener(new FechamentoDeJanela());

		
		this.setSize(700, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		

	}

	protected class MeuJPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public MeuJPanel() {
			super();

		}
	}

		protected class Sair implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//new CarregarVencedores().vencedores();		
				System.exit(0);
				
				
				
			}
		}

		protected class FechamentoDeJanela extends WindowAdapter {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		}
	
	}