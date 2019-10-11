package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;


    

public class Huffman
{
    // Huffman trie node
    private static class Nodo implements Comparable<Nodo> {
        private final char ch;
        private final double freq;
        private final Nodo izq, der;

        Nodo(char ch, double freq, Nodo izq, Nodo der) {
            this.ch    = ch;
            this.freq  = freq;
            this.izq  = izq;
            this.der = der;
        }

        private boolean esHoja() {
            
            return (izq == null) && (der == null);
        }


        public int compareTo(Nodo that) {
            if(this.equals(that))
                return 0;
            else 
                return (this.freq-that.freq>0)?1:-1;
        }
    }//guarda chufi
    
    private static Nodo generaArbol( HashMap<Character, Double> freqs ) { //genera el arbol primero
            PriorityQueue<Nodo> pq = new PriorityQueue<Nodo>();
            for ( char c: freqs.keySet() ) 
                    pq.add( new Nodo( c, freqs.get(c), null, null ) );
            
            while ( pq.size() > 1 ) {
                    Nodo izq = pq.poll();
                    Nodo der = pq.poll();
                    pq.add( new Nodo( '£', izq.freq + der.freq, izq, der ) );
            }
            
            return pq.poll();
    }
    
    
    private static void generaCodigo(Nodo nodo, String code, HashMap<Character, String> encoding) { //agarra las hojas y genera el hashmap
            if ( nodo.esHoja() ) 
                    encoding.put( nodo.ch, code );
            else {
                    if ( nodo.izq != null ) 
                            generaCodigo( nodo.izq, code+"0", encoding );
                    if ( nodo.der != null )
                            generaCodigo( nodo.der, code+"1", encoding );
            }
    }
    
    
    
    private HashMap<Character, String> encodeTable = new HashMap<Character, String>();
    private HashMap<String, Character> decodeTable = new HashMap<String, Character>();



    public Huffman(HashMap<Character,Double> frecuencias)
    {
        HashMap<Character,String> codigo = new HashMap<Character,String>();
        generaCodigo(generaArbol(frecuencias),"",codigo);
        this.encodeTable=codigo;
        this.decodeTable = new HashMap<String,Character>();
        for(Map.Entry <Character,String>e: encodeTable.entrySet()){
            decodeTable.put(e.getValue(),e.getKey());
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
