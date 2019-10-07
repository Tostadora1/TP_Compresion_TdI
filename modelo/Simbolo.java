package modelo;

public class Simbolo
    implements Comparable<Simbolo>
{
    private String nombre;
    private double probabilidad;
    private String codigo = "";

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public Simbolo(String nombre, double probabilidad)
    {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
    }

    public String getNombre()
    {
        return nombre;
    }

    public double getProbabilidad()
    {
        return probabilidad;
    }

    @Override
    public String toString()
    {

        return "[" + this.getNombre() + " p(" + this.getNombre() + ")=" + this.getProbabilidad() + " Codigo: " +
               this.getCodigo() + "]";
    }

    @Override
    public int compareTo(Simbolo simbolo)
    {
        if (this.equals(simbolo))
            return 0;
        else
            return this.probabilidad > simbolo.getProbabilidad()? 1: -1;
    }
}
