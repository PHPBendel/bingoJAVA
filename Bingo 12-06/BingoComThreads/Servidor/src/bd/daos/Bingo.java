package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Bingo
{
    public boolean cadastrado (String email, String senha) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;
            sql = "IF EXISTS( SELECT  email "+
            	    "FROM bingoJava as cx  "+
            	    "WHERE email= ? and senha = ?) SELECT 1 AS returnValue";
            
            BD.comando.prepareStatement (sql);
            BD.comando.setString (1, email);
            BD.comando.setString (2, senha);

            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();
            
            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Usuário ou senha incorreto!");
        }

        return retorno;
    }
    
    public boolean jaTemCadastro (String email) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;
            sql = "IF EXISTS( SELECT  email "+
            	    "FROM bingoJava as cx  "+
            	    "WHERE email= ?) SELECT 1 AS returnValue";
            
            BD.comando.prepareStatement (sql);
            BD.comando.setString (1, email);            

            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();
            
            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Usuário ou senha incorreto!");
        }

        return retorno;
    }
    
    public void mudarVitorias(String email, int v) throws Exception
    {
    	 if (email==null)
             throw new Exception ("Email nao fornecido");
        
         try
         {
             String sql;

             sql = "UPDATE bingoJava " +                 
                   "SET qtdVitorias=? " +
                   "WHERE email = ?";

             BD.comando.prepareStatement (sql);

             BD.comando.setInt (1, v);
             BD.comando.setString (2, email);
            
             BD.comando.executeUpdate ();
             BD.comando.commit        ();
         }
         catch (SQLException erro)
         {
             throw new Exception ("Erro ao atualizar dados de livro");
         }
    }
    
    public void incluir (Bingos bingo) throws Exception
    {
        if (bingo==null)
            throw new Exception ("Usuário Não Informado");
        try
        {
            String sql;

            sql = "insert into bingoJava" + 
            	  "(email,senha,primeiraDataMes,qtdVitorias)"+
            	  "values (?,?,?,?)"; 

            BD.comando.prepareStatement (sql);

            BD.comando.setString (1, bingo.getEmail());
            BD.comando.setString (2, bingo.getSenha());
            BD.comando.setString (3, bingo.getPrimeiraDataMes());
            BD.comando.setInt    (4, bingo.getQtdVitorias());

            BD.comando.executeUpdate ();
            BD.comando.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir usuário");
        }
    }

    
    public void alterar (Bingos bingo) throws Exception
    {
        if (bingo==null)
            throw new Exception ("Livro nao fornecido");

        if (!cadastrado (bingo.getEmail(),bingo.getSenha()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE bingoJava " +
                  "SET email=? " +
                  "SET senha=? " +
                  "SET primeiraDataMes=? " +
                  "SET qtdVitorias=? " +
                  "WHERE email = ?";

            BD.comando.prepareStatement (sql);

            BD.comando.setString (1, bingo.getEmail());
            BD.comando.setString (2, bingo.getSenha());
            BD.comando.setString (3, bingo.getPrimeiraDataMes());
            BD.comando.setInt    (4, bingo.getQtdVitorias());
            BD.comando.setString (5, bingo.getEmail());

            BD.comando.executeUpdate ();
            BD.comando.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de livro");
        }
    }

//    public Bingos getMelhoresUsuarios (String email) throws Exception
//    {
//    	Bingos bingo = null;
//
//        try
//        {
//            String sql;
//
//            sql = "SELECT TOP 3 qtdVitorias " +
//                  "FROM bingoJava " +
//                  "ORDER BY qtdVitorias DESC";
//
//            BD.comando.prepareStatement (sql);
//
//            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();
//
//            if (!resultado.first())
//                throw new Exception ("Nao cadastrado");
//
//            bingo = new Bingos (resultado.getString ("email"),
//                                resultado.getString("primeiraDataMes"),
//                                resultado.getString ("senha"),
//                                resultado.getInt ("qtdVitorias"));
//        }
//        catch (SQLException erro)
//        {
//            throw new Exception ("Erro ao procurar livro");
//        }
//
//        return bingo;
//    }
//    
    
    

    public MeuResultSet getMelhoresUsuarios () throws Exception
    {
    	 MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT TOP 3 qtdVitorias, email " +
                  "FROM bingoJava " +
                  "ORDER BY qtdVitorias DESC";

            BD.comando.prepareStatement (sql);

           resultado = (MeuResultSet)BD.comando.executeQuery ();

         
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return resultado;
    }
    
    public Bingos getUsuario (String email) throws Exception
    {
    	Bingos bingo = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM bingoJava " +
                  "WHERE email = ?";

            BD.comando.prepareStatement (sql);
            BD.comando.setString (1, email);
            
            MeuResultSet resultado = (MeuResultSet)BD.comando.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            bingo = new Bingos ( resultado.getString("primeiraDataMes"),
            					resultado.getString ("email"),
                                resultado.getString ("senha"),
                                resultado.getInt ("qtdVitorias"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar livro");
        }

        return bingo;
    }
}