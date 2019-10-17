package vista;

import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.TreeSet;

import modelo.Huffman;
import modelo.BinUtils;
import modelo.IOFile;
import modelo.RLC;
import modelo.ShannonFano;
import modelo.TableGenerator;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(String[] args) {
        
        try {
            Huffman h = new Huffman(IOFile.generator("Espanol.txt"));
            String resultado = h.encode(IOFile.archToString("Espanol.txt"));
            IOFile.escribe(h, resultado, "Espanol", "huf");
            System.out.println(resultado);
            Huffman h2 = null;
            String traido = null;
            IOFile.lee(h2,traido,"Espanol","huf");
            System.out.println(traido);
            
        } catch (FileNotFoundException e) {
            System.out.println("no existia");
        } catch (Exception e) {
            System.out.println("xd");
            e.printStackTrace();
        }
    }



  /*  public static void main(String[] args) //en el rlc poner imbooloo y dsp la cantidad
    {
        try
        {
            System.out.println("eee" + BinUtils.bin2String(BinUtils.string2Bin("0000000100001")));


            TableGenerator tabla = new TableGenerator("Espanol.txt");
            Huffman h = new Huffman(tabla.getTable());
            String txtToString = IOFile.archToString("Espanol.txt");
                

            RLC rlc = new RLC();
            String comprimidoRLC = rlc.enconde(txtToString);
            System.out.println("/n/nAAAAAAAAAAAAAAAAAAAAAAAAAA/n");
            System.out.println(comprimidoRLC);
            String descomprimidoRLC = rlc.decode(comprimidoRLC);
            System.out.println(descomprimidoRLC + "/n/n");


            String comprimido = h.encode(txtToString); //AAA OLA FER
            System.out.println(comprimido);
            File file = new File("Datos/" + "Espanol_huf.bin");

            try (FileOutputStream output = new FileOutputStream(file))
            {
                ObjectOutputStream objetero = new ObjectOutputStream(output);
                objetero.writeObject(h);
                output.write(BinUtils.string2Bin(comprimido));
                System.out.println("datos escritos correctamente");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println("no hay archivoo");
        }
    }

*/
}
