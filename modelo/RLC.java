package modelo;

public class RLC
{
    
    public RLC()
    {
        super();
    }
    
    public String enconde(String entrada)
    {
        String salida = "";
        int cant = 0; char ant = '^';
        for(int i = 0; i < entrada.length() ; i++)
        {            
            if(entrada.charAt(i) == ant)
            {
                cant++;                
            }                
            else
            {
                if(cant != 0)
                    salida+=""+cant+ant;
                cant = 1;
            }
            ant = entrada.charAt(i);
        }
        return salida;
    }
    
    public String decode(String entrada)
    {
        String salida = "";        
        int cant = 0; char ant = '^';
        for(int i = 0; i < entrada.length() ; i+=2)        
            for(int j = 0; j<(entrada.charAt(i)-'0') ; j++)            
                salida += entrada.charAt(i+1);  
        return salida;
    }
}