import java.io.*;
import java.net.*;
import java.util.*;

public class Jogador {

	 private      Socket         conexao;
	 public final Scanner RECEPTOR;
	 public final PrintStream    TRANSMISSOR;
	 private Vector<Integer> cartela = new Vector<Integer>();
	 private String email;
	 private int vitorias;
	 
	 public Jogador (Socket c) throws Exception
	 {
	  if (c==null)
	   throw new Exception ("Conexao ausente");

	  this.conexao     = c;
	  this.RECEPTOR    = new Scanner (this.conexao.getInputStream());
	  this.TRANSMISSOR = new PrintStream (this.conexao.getOutputStream());
	 }
		
	 public void setCartela(Vector<Integer> c)
	 {
		 this.cartela = c;
	 }
	 
	 public void setEmail(String e)
	 {
		 this.email = e;
	 }
	 
	 public void setVitorias(int v)
	 {
		 this.vitorias = v;
	 }
	 
	 public String getEmail()
	 {
		 return this.email;
	 }
	 
	 public int getVitorias()
	 {
		 return this.vitorias;
	 }
	 
	 public Vector<Integer> getCartela()
	 {
		 return this.cartela;		 
	 }
	 
	 public void morra () throws IOException
	 {
	  this.TRANSMISSOR.close();
	  this.RECEPTOR   .close();
	  this.conexao    .close();
	 }
	 
}
