import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class JogoIniciado extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblJogo = new JLabel("<html>Jogo já está em andamento.</html>");

	private PrintStream saida;

	private Socket servidor;
	// ----------------------------

	public JogoIniciado(Socket servidor) throws IOException {

		super("Aguardando próximo jogo");

		this.servidor = servidor;
		// -------------------------------------

		saida = new PrintStream(servidor.getOutputStream());

		this.addWindowListener(new FechamentoDeJanela());

		JPanel pnlAguardando = new JPanel();
		pnlAguardando.setBorder(new EmptyBorder(30, 23, 10, 23));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(78)
					.addComponent(lblJogo, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(97, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblJogo)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		lblJogo.setFont(new Font("Tahoma", Font.PLAIN, 24));

		this.setSize(485, 172);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}

	// -------------------------------------------------------------------------------------------

	// -------------------------------------------------------------------------------------------

	protected class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("sairCadastro");
			} catch (IOException e1) {
				e1.getMessage();
			}
		}
	}

	// -------------------------------------------------------------------------------------------

	protected class FechamentoDeJanela extends WindowAdapter {
		public void windowClosing(WindowEvent e) {

			System.exit(0);
		}
	}
}
