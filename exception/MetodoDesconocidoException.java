package exception;

public class MetodoDesconocidoException
    extends Exception
{
    String metodo;

    public MetodoDesconocidoException(String metodo)
    {
        super(metodo);
    }
}
