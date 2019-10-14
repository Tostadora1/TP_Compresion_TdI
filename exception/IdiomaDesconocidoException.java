package exception;

public class IdiomaDesconocidoException
    extends Exception
{
    private String idioma;

    public IdiomaDesconocidoException(String idioma)
    {       
        super(idioma);
    }

   
}
