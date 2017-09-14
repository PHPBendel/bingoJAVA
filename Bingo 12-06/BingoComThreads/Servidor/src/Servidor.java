import java.io.*;
import java.net.*;
import java.util.*;

import bd.daos.Bingo;

public class Servidor {

	private Socket cliente;
	private String mensagem;
	
	public Servidor(Socket cliente){
		this.cliente = cliente;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			Derrubadora derrubadora = new Derrubadora();
			derrubadora.start();
			
			ServerSocket servidor = new ServerSocket(10000);
			Jogo jogo = new Jogo();
			jogo.start();
			
			while (true)
			{
				Socket conexao = servidor.accept();
				Cuidadora cuidadora = new Cuidadora(conexao, jogo);
				cuidadora.start();
				
				
				if (jogo.terminou())
				{
					jogo = new Jogo();
					jogo.start();
				}
			}
		}
		catch(IOException e)
		{
			//e.getMessage();
		}
	}
	
	/*(public void run()
	{
		try
		{
			Scanner entrada = new Scanner(this.cliente.getInputStream()); //input do cliente(o que ele envia)
			PrintStream saida = new PrintStream(this.cliente.getOutputStream()); //output do cliente (o que ele vai receber)
			
			while(entrada.hasNextLine())
			{
				mensagem = entrada.nextLine();
				
				GeraNumerosCartela g = new GeraNumerosCartela();
				g.geraNumAleatorios();
				
				if (this.mensagem.equals("logando"))
				{
					String login = entrada.nextLine();
					String senha = entrada.nextLine();
					boolean logado = false;
					
					try 
					{
						logado = new Bingo().cadastrado(login,senha);
					} 
					catch (Exception e) 
					{
						e.getMessage();
					}
					
					if (logado)
					{
						
						saida.println("logou");
						saida.println(g.toString());
					}
					else
						saida.println("cliente nao logou");
				}
			}
		}
		catch (IOException e)
		{
			//e.getMessage();
		}
	}*/
	
	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		
		if (this == obj)
			return true;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		Servidor s = (Servidor)obj;
		
		if (this.mensagem != s.mensagem)
			return false;
		
		return true;
	}

}
