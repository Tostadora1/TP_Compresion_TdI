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

    public static void main(String[] args)//en el rlc poner imbooloo y dsp la cantidad
    {
        try
        {
            
            TableGenerator tabla = new TableGenerator("Espanol.txt");
            Huffman h = new Huffman(tabla.getTable());
            String txtToString = archToString("Espanol.txt");
            String comprimido = h.encode(txtToString); //AAA OLA FER
            System.out.println(comprimido);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Espanol_huf.txt"), "utf-8"));
            //out.write(he.toString()); //TODO METER LA TABLA
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
    
    public static String archToString(String filename)
    {
        String input = "";
        StringBuilder st = null;
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));
            st = new StringBuilder();
            input = in.readLine();            
            while (input != null)
            {   
                st.append(input);
                input = in.readLine();
            }
        }
        catch (UnsupportedEncodingException e)
        {
        }
        catch (FileNotFoundException e)
        {
        }
        catch (IOException e)
        {
        }
        return st.toString();
    }
}
