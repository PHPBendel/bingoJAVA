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

public class JanelaLogin extends JFrame {
	protected static final long serialVersionUID = 1L;

	protected JLabel labelEmail = new JLabel("E-mail:"), labelSenha = new JLabel("Senha:"),
			lblRecordes = new JLabel("Recordes do Mês"), lblNome = new JLabel("Nome"),
			lblVitorias = new JLabel("Vitórias"), lblNome1 = new JLabel("Fulano de tal"),
			lblNome2 = new JLabel("Fulano de tal"), lblNome3 = new JLabel("Fulano de tal"),
			lblVitorias1 = new JLabel("50"), lblVitorias2 = new JLabel("60"), lblVitorias3 = new JLabel("70");

	protected JTextField txtEmail = new JTextField(20), txtSenha = new JPasswordField(20);

	protected JButton btnEntrar = new JButton("Entrar"), btnCancela = new JButton("Cancela"),
			btnCadastro = new JButton("Cadastre-se");
	
	protected Socket servidor;

	public JanelaLogin(Socket servidor) {

		super("Login");
		
		this.servidor = servidor;

		this.addWindowListener(new FechamentoDeJanela());

		JPanel pnlLogin = new JPanel();
		pnlLogin.setBorder(new EmptyBorder(20, 10, 10, 10));

		GroupLayout layoutLogin = new GroupLayout(pnlLogin);

		pnlLogin.setLayout(layoutLogin);

		layoutLogin.setAutoCreateGaps(true);
		layoutLogin.setAutoCreateContainerGaps(true);
		layoutLogin.setHorizontalGroup(layoutLogin.createSequentialGroup()
				.addGroup(layoutLogin.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(labelEmail)
						.addComponent(labelSenha))
				.addGroup(layoutLogin.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(txtEmail)
						.addComponent(txtSenha)));

		layoutLogin.setVerticalGroup(layoutLogin.createSequentialGroup()
				.addGroup(layoutLogin.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelEmail)
						.addComponent(txtEmail))
				.addGroup(layoutLogin.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSenha)
						.addComponent(txtSenha)));

		JPanel pnlBotoes = new MeuJPanel();
		GroupLayout layoutBotoes = new GroupLayout(pnlBotoes);

		pnlBotoes.setLayout(layoutBotoes);

		btnCancela.addActionListener(new Sair());
		btnEntrar.addActionListener(new Logar());
		btnCadastro.addActionListener(new Cadastro());
		
		layoutBotoes.setAutoCreateGaps(true);
		layoutBotoes.setAutoCreateContainerGaps(true);
		layoutBotoes.setHorizontalGroup(layoutBotoes.createSequentialGroup().addComponent(btnEntrar)
				.addComponent(btnCancela).addComponent(btnCadastro));

		layoutBotoes.setVerticalGroup(layoutBotoes.createSequentialGroup()
				.addGroup(layoutBotoes.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnEntrar)
						.addComponent(btnCancela).addComponent(btnCadastro)));

		JPanel pnlRecorde = new JPanel();
		pnlRecorde.setBorder(new EmptyBorder(20, 60, 40, 10));
		GroupLayout layoutRecorde = new GroupLayout(pnlRecorde);
		layoutRecorde.setHorizontalGroup(layoutRecorde.createParallelGroup(Alignment.LEADING).addGroup(layoutRecorde
				.createSequentialGroup()
				.addGroup(layoutRecorde.createParallelGroup(Alignment.LEADING).addGroup(layoutRecorde
						.createSequentialGroup()
						.addGroup(layoutRecorde.createParallelGroup(Alignment.LEADING).addComponent(lblNome)
								.addComponent(lblNome1).addComponent(lblNome2).addComponent(lblNome3))
						.addGap(44)
						.addGroup(layoutRecorde.createParallelGroup(Alignment.LEADING).addComponent(lblVitorias1)
								.addComponent(lblVitorias).addComponent(lblVitorias2).addComponent(lblVitorias3)))
						.addComponent(lblRecordes, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layoutRecorde.setVerticalGroup(layoutRecorde.createParallelGroup(Alignment.TRAILING).addGroup(layoutRecorde
				.createSequentialGroup()
				.addComponent(lblRecordes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGap(17)
				.addGroup(layoutRecorde.createParallelGroup(Alignment.LEADING)
						.addGroup(layoutRecorde.createSequentialGroup().addComponent(lblNome)
								.addGroup(layoutRecorde.createParallelGroup(Alignment.BASELINE).addComponent(lblNome1)
										.addComponent(lblVitorias1))
								.addGroup(layoutRecorde.createParallelGroup(Alignment.BASELINE).addComponent(lblNome2)
										.addComponent(lblVitorias2))
								.addGroup(layoutRecorde.createParallelGroup(Alignment.BASELINE).addComponent(lblNome3)
										.addComponent(lblVitorias3)))
						.addComponent(lblVitorias))));

		lblNome.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVitorias.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRecordes.setFont(new Font("Roboto", Font.BOLD, 20));

		pnlRecorde.setLayout(layoutRecorde);

		Container cntForm = this.getContentPane();

		cntForm.add(pnlLogin, BorderLayout.NORTH);
		cntForm.add(pnlBotoes, BorderLayout.CENTER);
		cntForm.add(pnlRecorde, BorderLayout.SOUTH);

		this.setSize(300, 350);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		pnlBotoes.setFocusable(true);
		pnlBotoes.requestFocusInWindow();

	}

	protected class MeuJPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		public MeuJPanel() {
			super();

		}

	}
	
	/* protected class CarregarVencedores {
		public void vencedores() throws Exception{
			String login = txtEmail.getText();
			String senha = txtSenha.getText();
			Bingos bingo = null;
			Date data = new Date();
			@SuppressWarnings("deprecation")
			int dia = data.getDate();
			int mes = data.getMonth()+1;
			int ano = data.getYear()+1900;
			String dataCompleta = dia+"/"+""+mes+"/"+ano;
			bingo.setEmail(login);
			bingo.setSenha(senha);
			bingo.setPrimeiraDataMes(dataCompleta);
			bingo.setQtdVitorias(0);
			new Bingo().incluir(bingo);
			}
	} */
	
	
	
	
	protected class Logar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String login = txtEmail.getText();
			String senha = txtSenha.getText();
			
			try 
			{
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("logando");
				saida.println(login);
				saida.println(senha);
			} 
			catch (IOException e1) 
			{
				e1.getMessage();
			}
			
			
			/*boolean logado = false;
			try {
				//logado = new Bingo().cadastrado(login,senha);
				
				JOptionPane.showMessageDialog(null,"Bem vindo: "+login , null, 3);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"Usuário ou Senha incorreto" , null, 0);
			}*/
		}
	}
	

	protected class Cadastro implements ActionListener{
		public void actionPerformed(ActionEvent e){
		
			
			try 
			{
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("abrirCadastro");				
			} 
			catch (IOException e1) 
			{
				e1.getMessage();
			}
			
			

					
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