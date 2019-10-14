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

import java.util.TreeSet;

import modelo.Huffman;
import modelo.BinUtils;
import modelo.RLC;
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
            System.out.println("eee"+BinUtils.bin2String(BinUtils.string2Bin("0000000100001")));
            
            
            TableGenerator tabla = new TableGenerator("Espanol.txt");
            Huffman h = new Huffman(tabla.getTable());
            String txtToString = archToString("Espanol.txt");
            
            RLC rlc = new RLC();
            String comprimidoRLC = rlc.enconde(txtToString);
            System.out.println("/n/nAAAAAAAAAAAAAAAAAAAAAAAAAA/n");
            System.out.println(comprimidoRLC);
            String descomprimidoRLC = rlc.decode(comprimidoRLC);
            System.out.println(descomprimidoRLC+"/n/n");
            
            
            String comprimido = h.encode(txtToString); //AAA OLA FER
            System.out.println(comprimido);
            File file = new File("Espanol_huf.bin");
            
            try(FileOutputStream output = new FileOutputStream(file)){
                ObjectOutputStream objetero = new ObjectOutputStream(output);
                objetero.writeObject(h);
                output.write(BinUtils.string2Bin(comprimido));
                System.out.println("datos escritos correctamente");
            }
            catch(IOException e){
                e.printStackTrace();
            }
            
        }
        catch (FileNotFoundException e)
        {
            System.out.println("no hay archivoo");
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
