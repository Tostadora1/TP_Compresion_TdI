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

import java.io.OutputStreamWriter;

import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.util.TreeSet;

import modelo.Huffman;
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
            
            File file = new File("Espanol_huf.bin");
            byte[] datos = comprimido.getBytes(StandardCharsets.UTF_8);
            
            for(int i = 0;i<datos.length;i++)
                datos[i]-=48;
            
            byte[] datosApretados = new byte[datos.length/8+1];
            
            
            
            int i=0; int j=0;
            byte byteactual=0;
            
            while(i<datos.length){
                if((i)%8==0 && i!=0){ //esta enterito lo meto
                    datosApretados[j]=byteactual;
                    j++;
                    byteactual=0;
                }
                else{ //siga el bailee
                    byteactual = (byte) (byteactual << 1); //byteactual << 1 pero en croto
                    
                     byteactual = (byte) (byteactual | (datos[i]&0x1));
                }
                i++;
            }
            
            //guarda con el de la punta
            byteactual = (byte) (byteactual << 8-i-1); //se nos ocurrio mirando una representacion gráfica
            datosApretados[j]=byteactual;
            
            try(FileOutputStream output = new FileOutputStream(file)){
                output.write(datosApretados);
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
