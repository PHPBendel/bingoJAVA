import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class Cartela extends JFrame
{
	protected JButton[][] matrizBotoes = new JButton[5][5];
	
	protected JButton btnB = new JButton("B"),
					  btnI = new JButton("I"),
					  btnN = new JButton("N"),
					  btnG = new JButton("G"),
					  btnO = new JButton("O");
	
	public JLabel numeroSorteado = new JLabel ("*");
	protected JLabel statusBar2 = new JLabel ("Numero Sorteado: ");
	protected JLabel statusBar3 = new JLabel ("");	
	private final JLabel statusBar1 = new JLabel("");
	
	private Socket servidor;
	
	private Vector<Integer> numerosSorteados = new Vector<Integer>();
	
	public Cartela (String numeros, Socket servidor)
	{
		super ("Cartela de Bingo");	
		
		this.servidor = servidor;
		
		JPanel numerosCartela = new JPanel();
		numerosCartela.setLayout(new GridLayout(6, 5, 5, 5));
		
		btnB.setBorderPainted(false);
		btnB.setFocusPainted(false);
		btnB.setContentAreaFilled(false);
		btnI.setBorderPainted(false);
		btnI.setFocusPainted(false);
		btnI.setContentAreaFilled(false);
		btnN.setBorderPainted(false);
		btnN.setFocusPainted(false);
		btnN.setContentAreaFilled(false);
		btnG.setBorderPainted(false);
		btnG.setFocusPainted(false);
		btnG.setContentAreaFilled(false);
		btnO.setBorderPainted(false);
		btnO.setFocusPainted(false);
		btnO.setContentAreaFilled(false);
			
		numerosCartela.add(btnB);
		numerosCartela.add(btnI);
		numerosCartela.add(btnN);
		numerosCartela.add(btnG);
		numerosCartela.add(btnO);

		StringTokenizer quebrador = new StringTokenizer(numeros, ",");
		
		for (int i=0; i<5; i++)
			for (int j=0; j<5; j++)
			{			
				if (i==2 && j==2)
				{
					matrizBotoes[i][j] = new JButton("BINGO");
					matrizBotoes[i][j].addActionListener(new FoiBingo());
					matrizBotoes[i][j].setFont(new Font("Trebuchet MS", Font.BOLD, 13));
				}
				else
				{
					//colocando numeros alatorios na cartela
					matrizBotoes[i][j] = new JButton(quebrador.nextToken());
					
					
					//guardando n�meros sorteados em um vetor
					numerosSorteados.add( Integer.parseInt(matrizBotoes[i][j].getText()));
					
					matrizBotoes[i][j].setFont(new Font("Trebuchet MS", Font.BOLD, 18));
				}
				
				//aparencia do bot�o
				matrizBotoes[i][j].setFocusPainted(false);
				matrizBotoes[i][j].setContentAreaFilled(false);
				matrizBotoes[i][j].addActionListener(new MudaCorBotao());
				
				
				numerosCartela.add(matrizBotoes[i][j]);
			}
		
		//statusBar1.setText(numeros);	
		//statusBar1.setText("Numero Sorteado: " + conexao.getNumeroSorteado());
		
		numeroSorteado.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		statusBar2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		JPanel pnlStatus = new JPanel();
		pnlStatus.setLayout(new BorderLayout());
		pnlStatus.add(statusBar2, BorderLayout.WEST);
		pnlStatus.add(numeroSorteado, BorderLayout.CENTER);
		pnlStatus.add(statusBar3, BorderLayout.SOUTH);

		Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (numerosCartela, BorderLayout.CENTER);
        cntForm.add (pnlStatus, BorderLayout.SOUTH);
        
        pnlStatus.add(statusBar1, BorderLayout.NORTH);
        
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,600);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}

	public void atualizaNumero()
	{
		
	}
	
	protected class MudaCorBotao implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {       	
        	for (int i = 0; i < 5; i++)
        		for (int j = 0; j < 5; j++) 
        	       if (matrizBotoes[i][j] == e.getSource()) 
        	       {
        	    	   if (matrizBotoes[i][j].getBackground() == Color.GREEN)
        	    	   {
        	    		   matrizBotoes[i][j].setBackground(null);
        	    		   matrizBotoes[i][j].setContentAreaFilled(false);
        	    	   }
        	    	   else
        	    	   {
        	    		   matrizBotoes[i][j].setContentAreaFilled(true);
        	    		   matrizBotoes[i][j].setBackground(Color.GREEN); 
        	    	   }
        	    	   
        	    	   statusBar1.setText("Linha: " + (i+1) + " " + "Coluna: " + (j+1) + " " + " Numero: " + matrizBotoes[i][j].getText()); 
        	       }
        }
    }
	
	protected class FoiBingo implements ActionListener
	{
		public void actionPerformed (ActionEvent e) 
		{	     

			try 
			{
				PrintStream saida = new PrintStream(servidor.getOutputStream());
				saida.println("B");
		
			} 
			catch (IOException e1) 
			{
				e1.getMessage();
			}
			
			
		}
	}
	
}
