package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Huffman
{
    private HashMap<Character, String> encodeTable = new HashMap<Character, String>();
    private HashMap<String, Character> decodeTable = new HashMap<String, Character>();


    /**Este  metodo genera el codigo de huffman para un arbol jeje
     * @param t
     */
    public static void huffman(TreeSet<Simbolo> t){
        if(t.size() ==2 ||t.size()==1){//feito TODO
            t.first().setCodigo("0");
            t.last().setCodigo("1");
        }
        else{
            Simbolo s1 = t.pollLast();
            Simbolo s2 = t.pollLast();
            Simbolo sc = new Simbolo(s1.getNombre()+s2.getNombre(),s1.getProbabilidad()+s2.getProbabilidad());
            t.add(sc);
            huffman(t);
            t.remove(sc);
            s1.setCodigo(sc.getCodigo()+"0");
            s2.setCodigo(sc.getCodigo()+"1");
            t.add(s1);
            t.add(s2);
            
        }
    }

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
