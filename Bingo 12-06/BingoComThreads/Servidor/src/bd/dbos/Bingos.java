package bd.dbos;

public class Bingos
{
    private String email;
    private String senha;
    private String primeiraDataMes;
    private int  qtdVitorias;
 
   
    
    public void setEmail (String email) throws Exception
    {
        if (email==null || email.equals(""))
            throw new Exception ("Email Inválido");

        this.email = email;
    }   
    
    public void setSenha (String senha) throws Exception
    {
        if (senha==null || senha.equals(""))
            throw new Exception ("Senha Inválida");

        this.senha = senha;
    }   

    public void setPrimeiraDataMes (String primeiraDataMes) throws Exception
    {
        if (primeiraDataMes==null || primeiraDataMes.equals(""))
            throw new Exception ("Data Inválida");

        this.primeiraDataMes = primeiraDataMes;
    }   

    public void setQtdVitorias (int qtdVitorias) throws Exception
    {
        if (qtdVitorias <= 0)
            throw new Exception ("Codigo invalido");

        this.qtdVitorias = qtdVitorias;
    }   

    public int getQtdVitorias ()
    {
        return this.qtdVitorias;
    }

    public String getPrimeiraDataMes ()
    {
        return this.primeiraDataMes;
    }

    public String getSenha ()
    {
        return this.senha;
    }

    public String getEmail ()
    {
        return this.email;
    }
    
    public Bingos (String primeiraDataMes, String email, String senha, int qtdVitorias)
    {
    	
        this.qtdVitorias      = qtdVitorias;
        this.email            = email;
        this.senha            = senha;
        this.primeiraDataMes  = primeiraDataMes;
    	
    }

  
    public String toString()
    {
    	String ret = "";
    	
    	ret += this.email;
    	
    	ret += " ," + this.senha;
    	ret += " ," + this.qtdVitorias;
    	ret += " ," + this.primeiraDataMes;
    	
    	return ret;
    }
    
    public boolean equals(Object obj)
    {
    	if (obj == null)
    		return false;
    	
    	if (this == obj)
    		return true;
    	
    	if (this.getClass() != obj.getClass())
    		return false;
    	
    	Bingos b = (Bingos) obj;
    	
    	if (!this.email.equals(b.email))
    		return false;
    	
    	if (!this.senha.equals(b.senha))
    		return false;
    	
    	if(!this.primeiraDataMes.equals(b.primeiraDataMes))
    		return false;
    	
    	if (this.qtdVitorias != b.qtdVitorias)
    		return false;
    	
    	return true;
    }
    
    public int hashCode()
    {
    	int ret = 14;
    	
    	ret = ret * 7 + new Integer(this.email).hashCode();
    	ret = ret * 7 + new Integer(this.senha).hashCode();
    	ret = ret * 7 + new Integer(this.primeiraDataMes).hashCode();
    	ret = ret * 7 + new Integer(this.qtdVitorias).hashCode();
    	
    	return ret;    	
    }
    
    public Bingos (Bingos bingo) throws Exception
    {
    	if (bingo == null)
    		throw new Exception ("Modelo ausente");
    	
    	this.email = bingo.email;
    	this.senha = bingo.senha;
    	this.primeiraDataMes = bingo.primeiraDataMes;
    	this.qtdVitorias = bingo.qtdVitorias;    	    
    }
    
    
    public Object clone()
    {
    	Bingos ret = null;
    	
    	try {
    		ret = new Bingos(this);
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    	return ret;
    }
    // é claro que os métodos obrigatórios deveriam ser feitos
    // para a implementação ficar completa
}