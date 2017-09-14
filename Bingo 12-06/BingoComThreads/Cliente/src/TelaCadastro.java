import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintStream;
import java.net.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaCadastro extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JLabel lblEmail = new JLabel("E-mail: "), 
			lblSenha = new JLabel("Senha: "),
			lblConfirmacao = new JLabel("Confirmação: ");

	private JTextField txtEmail = new JTextField(500), 
			txtSenha = new JPasswordField(30),
			txtConfirmacao = new JPasswordField(30);

	private JButton btnOk = new JButton("Ok"), 
			btnCancela = new JButton("Cancelar");
	
	private PrintStream saida;
	
	private Socket servidor;
	//----------------------------
	
	
	public TelaCadastro(Socket servidor) throws IOException {
		
		super("Cadastro");
		
		this.servidor = servidor;
		//-------------------------------------
		btnOk.addActionListener(new ClicouOk());
		saida = new PrintStream(servidor.getOutputStream());
		
		this.addWindowListener(new FechamentoDeJanela());

		JPanel pnlCadastro = new JPanel();
		pnlCadastro.setBorder(new EmptyBorder(30, 23, 10, 23));

		Container cntForm = this.getContentPane();

		cntForm.add(pnlCadastro, BorderLayout.NORTH);
		GroupLayout gl_pnlCadastro = new GroupLayout(pnlCadastro);
		gl_pnlCadastro
				.setHorizontalGroup(gl_pnlCadastro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCadastro.createSequentialGroup().addGroup(gl_pnlCadastro
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(gl_pnlCadastro.createSequentialGroup()
										.addGroup(gl_pnlCadastro.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSenha) .addComponent(lblEmail))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_pnlCadastro.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_pnlCadastro.createSequentialGroup()
														.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, 125,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(lblConfirmacao)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txtConfirmacao, 0, 0, Short.MAX_VALUE))
												.addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 369,
														Short.MAX_VALUE)))
								.addGroup(gl_pnlCadastro.createSequentialGroup().addGap(139).addComponent(btnOk)
										.addGap(18).addComponent(btnCancela)))
								.addContainerGap()));

		gl_pnlCadastro.setVerticalGroup(gl_pnlCadastro.createParallelGroup(Alignment.LEADING).addGroup(gl_pnlCadastro
				.createSequentialGroup().addGap(5)
				.addGroup(gl_pnlCadastro.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(
						txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCadastro.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCadastro.createSequentialGroup().addGap(5).addComponent(lblSenha))
						.addGroup(gl_pnlCadastro.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_pnlCadastro.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblConfirmacao).addComponent(txtConfirmacao,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))))
				.addPreferredGap(ComponentPlacement.UNRELATED).addGroup(gl_pnlCadastro
						.createParallelGroup(Alignment.BASELINE).addComponent(btnOk).addComponent(btnCancela))
				.addContainerGap(175, Short.MAX_VALUE)));

		lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblSenha.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblConfirmacao.setFont(new Font("Trebuchet MS", Font.BOLD, 15));

		pnlCadastro.setLayout(gl_pnlCadastro);

		btnCancela.addActionListener((new Sair()));
		
		this.setSize(500, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

	}
	
	//-------------------------------------------------------------------------------------------
	protected class ClicouOk implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if (txtEmail.getText().isEmpty() || txtSenha.getText().isEmpty() || txtConfirmacao.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null,"Preencha todos os campos" , null, 0);
			}
			else if (!txtSenha.getText().equals(txtConfirmacao.getText()))
			{
				JOptionPane.showMessageDialog(null,"Senha e confirmação devem ser iguais" , null, 0);
			}
			else 
			{
				try 
				{
					PrintStream saida = new PrintStream(servidor.getOutputStream());
					saida.println("cadastrando");
					saida.println(txtEmail.getText());
					saida.println(txtSenha.getText());
				} 
				catch (IOException e1) 
				{
					e1.getMessage();
				}
				
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------

	
	protected class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try 
			{
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("sairCadastro");			
			} 
			catch (IOException e1) 
			{
				e1.getMessage();
			}
		}
	}

	//-------------------------------------------------------------------------------------------
	
	protected class FechamentoDeJanela extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
		
			try 
			{
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("sairCadastro");			
			} 
			catch (IOException e1) 
			{
				e1.getMessage();
			}

		}
	}

}
