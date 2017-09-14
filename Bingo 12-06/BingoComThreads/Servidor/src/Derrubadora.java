import java.io.*;

public class Derrubadora extends Thread {
	public void run() {
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

		for (;;) {
			try{
			String linha = teclado.readLine();
			if (linha.toUpperCase().equals("CAIA")) {
				System.out.println("Servidor desligado!");
				System.exit(0);
			}
			
			}
			catch(IOException e){}
		}
	}
}
