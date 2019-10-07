package vista;

import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;

import java.util.TreeSet;

import modelo.Huffman;
import modelo.Simbolo;
import modelo.TableGenerator;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(String[] args)
    {
        try
        {
            TableGenerator tabla = new TableGenerator("Espanol.txt");
            TreeSet<Simbolo> arbol = tabla.getTreeSet();
            Huffman.huffman(arbol);
            Huffman h = new Huffman();

            for (Simbolo s: arbol.descendingSet())
            {
                h.addSymbol(s.getNombre().charAt(0), s.getCodigo()); //TODO ESTO ES MEDIO PELIGROSO
            }
            String comprimido = h.encode("Espanol.txt"); //AAA OLA FER
            System.out.println(comprimido);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Espanol_huf.txt"), "utf-8"));
            out.write(arbol.toString());
            out.write(comprimido);
            System.out.println("La codificacion se escribio en Espanol_huf.txt");
            out.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("no hay archivoo");
        }
        catch (UnsupportedEncodingException e)
        {
        }
        catch (IOException e)
        {
        }

    }
}
