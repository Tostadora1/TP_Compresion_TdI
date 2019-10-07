package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Huffman
{
    private HashMap<Character, String> encodeTable = new HashMap<Character, String>();
    private HashMap<String, Character> decodeTable = new HashMap<String, Character>();
    

    public Huffman()
    {
    }

    public void addSymbol(Character symbol, String encoding)
    {
        this.encodeTable.put(symbol, encoding);
        this.decodeTable.put(encoding, symbol);
    }

    public void load(String filename)
        throws FileNotFoundException
    {
        FileInputStream f = new FileInputStream(filename);
        Scanner sf = new Scanner(f);
        while (sf.hasNextLine())
        {
            String current = sf.nextLine();
            char schar = current.charAt(0);
            String encoding = current.split("\\t")[1];
            this.addSymbol(schar, encoding);
        }
    }


    public String encode(String input)
    {
        String output = "";
        for (char ch: input.toCharArray())
        {
            output += encodeTable.get(ch);
        }
        return output;
    }

    public String decode(String input)
    {
        String output = "";
        int posAct = 0;
        while (posAct < input.length())
        {
            String palabraActual = "";
            while (!this.decodeTable.containsKey(palabraActual))
            {
                palabraActual += input.charAt(posAct++);
            }
            output += this.decodeTable.get(palabraActual);
        }
        return output;
    }

}
