package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.Map;


public class TableGenerator
{
    private HashMap<Character, Double> table = new HashMap<Character, Double>();
    
    public TableGenerator()
    {
        super();
    }

    public void generator(String filename)
        throws FileNotFoundException
    {
        int total = 0;
        try
        {           
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));                       
            int control = in.read();
            char current = (char) control;
            while (control != -1)
            {
                    total++;
                    System.out.println(""+current);
                    System.out.println(total);
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
            this.table.put(entry.getKey(), entry.getValue() / total);
        }


    }
}

