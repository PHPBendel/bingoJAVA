import java.io.IOException;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import bd.BD;
import bd.daos.*;
import bd.dbos.*;

public class Cuidadora extends Thread {

	private Jogador jogador;
	private Jogo jogo;
	private Bingos objBingo;
	private boolean pare;
	

	public Cuidadora(Socket c, Jogo jogo) throws Exception {
		if (c == null)
			throw new Exception("Conexao ausente");

		this.pare = false;
		this.jogador = new Jogador(c);
		this.jogo = jogo;
	}

	public synchronized void run() {
		try {
			while (this.jogador.RECEPTOR.hasNextLine() || this.pare == false) {

				String desejo = this.jogador.RECEPTOR.nextLine();
//				System.out.println("Dados Recebidos na Cuidadora: " + desejo);

				if (desejo.equals("logando")) {
					String login = this.jogador.RECEPTOR.nextLine();
					String senha = this.jogador.RECEPTOR.nextLine();

					boolean logado = false;

					try {
						logado = new Bingo().cadastrado(login, senha);
					} catch (Exception e) {
						e.getMessage();
					}

					if (logado) {

						objBingo = new Bingo().getUsuario(login);
						
						jogador.setEmail(objBingo.getEmail());
						jogador.setVitorias(objBingo.getQtdVitorias());
						
						if (jogo.iniciado()) {
							// supondo que o protocolo mande enviar "I" quando o
							// jogo ja esta iniciado e um jogador quer jogar

							this.jogador.TRANSMISSOR.println("I");
							this.jogador.TRANSMISSOR.flush();
						} else {
						
							GeraNumerosCartela g = new GeraNumerosCartela(this.jogador.TRANSMISSOR);
							g.gerarCartela();

							Vector<Integer> cartela = new Vector<Integer>();
							String numero = g.getRet();

							StringTokenizer quebrador = new StringTokenizer(numero, ",");

							for (int i = 0; i <= 23; i++) {

								cartela.add(Integer.parseInt(quebrador.nextToken()));
							}

							jogador.setCartela(cartela);

							jogo.receba(jogador, this);

							// supondo que o protocolo mande enviar "B" quando o
							// jogador clicar BINGO em seu programa

						}
						// this.jogador.TRANSMISSOR.println(g.toString());

//						System.out.println("Dados Compativeis");
					} else {
						this.jogador.TRANSMISSOR.println("NL");
					}

				} else if (desejo.equals("abrirCadastro")) {

					this.jogador.TRANSMISSOR.println("AC");

				} else if (desejo.equals("sairCadastro")) {

					this.jogador.TRANSMISSOR.println("SC");

				} else if (desejo.equals("cadastrando")) {

					String login = this.jogador.RECEPTOR.nextLine();
					String senha = this.jogador.RECEPTOR.nextLine();

					boolean logado = false;

					try {
						logado = new Bingo().jaTemCadastro(login);
					} catch (Exception e) {
						e.getMessage();
					}

					if (logado) {

						this.jogador.TRANSMISSOR.println("JaTemCadastro");
						//System.out.println("Já cadastrado");

					} else {
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						LocalDate dataAtual = LocalDate.now();
						String data = dtf.format(dataAtual);

						objBingo = new Bingos(data, login, senha, 0);
						
						new Bingo().incluir(objBingo);
						this.jogador.TRANSMISSOR.println("cadastrou");

					}

				}  else if (desejo.equals("B")) {
					try {
						this.jogo.termine(jogador);
					} catch (Exception e) {
						this.jogador.TRANSMISSOR.println("E");
						this.jogador.TRANSMISSOR.println(e.getMessage());
					}
				}
			}

		} catch (IOException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void enviarNumeroSorteado(int numero) {
		int numeroSorteado = numero;

		this.jogador.TRANSMISSOR.println("NS");
		this.jogador.TRANSMISSOR.println(numeroSorteado);
	}

	public void parar() {
		this.pare = true;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (this.getClass() != obj.getClass())
			return false;

		Cuidadora c = (Cuidadora) obj;

		if (this.jogador != c.jogador)
			return false;

		if (!this.jogo.equals(c.jogo))
			return false;
		
		if (!this.objBingo.equals(c.objBingo))
			return false;
		
		return true;
	}
	
	public String toString()
	{
		String ret = "";
		
		ret += this.jogador.getEmail();
		ret += " ," + this.jogador.getVitorias();
		ret += " ," + this.jogador.getCartela();
		
		ret += " ," + this.jogo;
				
		return ret;		
	}
}
