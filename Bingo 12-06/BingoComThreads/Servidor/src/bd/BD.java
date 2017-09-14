package bd;

import bd.core.*;
import bd.daos.*;

public class BD
{
    public static final MeuPreparedStatement comando;
    public static final Bingo bingos; //um como esse para cada classe DAO

    static
    {
    	MeuPreparedStatement _comando = null;
     	Bingo               _bingos  = null; //um como esse para cada classe DAO

    	try
        {
            _comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://f78ac7a5-180f-4d34-b7ec-a77c013e667d.sqlserver.sequelizer.com:1433;databasename=dbf78ac7a5180f4d34b7eca77c013e667d",
            "gihapwbqrirtlvma", "kmCy3RusfrtGKoKARhSp5xuqpLjKbpFUU7KHkbzF7a5sGCdzSncthneVZmipFRrX");

            _bingos = new Bingo (); //um como esse para cada classe DAO
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        comando = _comando;
        bingos  = _bingos; //um como esse para cada classe DAO
    }
}