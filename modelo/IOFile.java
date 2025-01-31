package modelo;

import exception.IdiomaDesconocidoException;
import exception.MetodoDesconocidoException;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IOFile
{


    public IOFile()
    {
        super();
    }

    public static void lee(ICodigo codigo, String codificado, String idioma, String metodo)
        throws Exception
    {
        String salida = "";       
        if (idioma.equals("Espanol") || idioma.equals("Ingles") || idioma.equals("latin") || idioma.equals("noruego") ||
            idioma.equals("ruso"))
        {            
            if (metodo.equals("huf") || metodo.equals("fan") || metodo.equals("rlc"))
            {
                //File file = new File("Datos/" + idioma+"_"+metodo+".txt");
                FileInputStream input = new FileInputStream("Datos/" + idioma+"_"+metodo+".txt");
                if (metodo.equals("huf") || metodo.equals("fan"))
                {
                    ObjectInputStream objetero = new ObjectInputStream(input);
                    codigo = (ICodigo) objetero.readObject();                    
                }      
                
                DataInputStream inputBIN = new DataInputStream(input);
                ArrayList<Byte> bytesArrayList = new ArrayList<Byte>();
                byte a;
                
                while((a= inputBIN.readByte())!=-1)
                {
                   bytesArrayList.add( inputBIN.readByte());
                }
                byte[] arreglo = new byte[bytesArrayList.size()];
                
                int i;
                for(i=0;i<bytesArrayList.size();i++)
                    arreglo[i]=bytesArrayList.get(i);
                
                codificado = BinUtils.bin2String(arreglo);
               //codificado = BinUtils.bin2String();
                
            }                   
            else
            {
                throw new MetodoDesconocidoException(metodo);
            }            
        }
        else
            throw new IdiomaDesconocidoException(idioma);
            
    }


    public static void escribe(ICodigo codigo, String codificacion, String idioma, String metodo)
    {
        File file = new File("Datos/" + idioma + "_" + metodo + ".txt");

        if (codigo != null) //HUFFMAN o SHANNON
        {
            try (FileOutputStream output = new FileOutputStream(file))
            {
                ObjectOutputStream objetero = new ObjectOutputStream(output);
                objetero.writeObject(codigo);

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try (FileOutputStream output = new FileOutputStream(file))
        {
            output.write(BinUtils.string2Bin(codificacion));
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
            BufferedReader in =
                new BufferedReader(new InputStreamReader(new FileInputStream("Datos/" + filename), "utf-8"));
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

    public static HashMap<Character, Double> generator(String filename)
        throws FileNotFoundException
    {
        HashMap<Character, Double> table = new HashMap<Character, Double>();
        int total = 0;
        try
        {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("Datos/"+filename), "utf-8"));
            int control = in.read();
            char current = (char) control;
            while (control != -1)
            {
                total++;                
                if (table.containsKey(current))
                    table.put(current, table.get(current) + 1.0);
                else
                    table.put(current, 1.0);
                control = in.read();
                current = (char) control;
            }
        }
        catch (IOException e)
        {
            System.out.println("No se encontro el archivo");
        }
        for (Map.Entry<Character, Double> entry: table.entrySet())
        {
            table.put(entry.getKey(), entry.getValue() / total);
        }
        return table;
    }
    
}
