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
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final double freq;
        private final Node left, right;

        Node(char ch, double freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            if(this.equals(that))
                return 0;
            else 
                return (this.freq-that.freq>0)?1:-1;
        }
    }//guarda chufi
    
    /**
     * buildHuffmanTree: builds a Huffman trie 
     * @param freqs the hashmap of frequencies associated with each char.
     * @return the root of the Huffman tree.
     */
    private static Node buildHuffmanTree( HashMap<Character, Double> freqs ) {
            PriorityQueue<Node> prioQ = new PriorityQueue<Node>();
            for ( char c: freqs.keySet() ) 
                    prioQ.add( new Node( c, freqs.get(c), null, null ) );
            
            while ( prioQ.size() > 1 ) {
                    Node left = prioQ.poll();
                    Node right = prioQ.poll();
                    prioQ.add( new Node( '#', left.freq + right.freq, left, right ) );
            }
            
            return prioQ.poll();
    }
    
    
    /**
     * Recursively performs a DFS to visit the Huffman trie and assign code to each
     * leaf. 
     * @param node
     * @param code
     * @param encoding
     */
    private static void generaCodigo(Node node, String code, HashMap<Character, String> encoding) {
            if ( node.isLeaf() ) 
                    encoding.put( node.ch, code );
            else {
                    if ( node.left != null ) 
                            generaCodigo( node.left, code+"0", encoding );
                    if ( node.right != null )
                            generaCodigo( node.right, code+"1", encoding );
            }
    }
    
    
    
    private HashMap<Character, String> encodeTable = new HashMap<Character, String>();
    private HashMap<String, Character> decodeTable = new HashMap<String, Character>();



    public Huffman(HashMap<Character,Double> frecuencias)
    {
        HashMap<Character,String> codigo = new HashMap<Character,String>();
        generaCodigo(buildHuffmanTree(frecuencias),"",codigo);
        this.encodeTable=codigo;
        this.decodeTable = new HashMap<String,Character>();
        for(Map.Entry <Character,String>e: encodeTable.entrySet()){
            decodeTable.put(e.getValue(),e.getKey());
        }
        
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
