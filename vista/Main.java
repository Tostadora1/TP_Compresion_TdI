package vista;

import java.io.FileNotFoundException;

import java.util.TreeSet;

import modelo.Huffman;
import modelo.Simbolo;
import modelo.TableGenerator;

public class Main {
    public Main() {
        super();
    }
    
    public static void main(String[] args) {
        try {
            TableGenerator tabla = new TableGenerator("ruso.txt");
            TreeSet<Simbolo> arbol = tabla.getTreeSet();
            Huffman.huffman(arbol);
            Huffman h = new Huffman();
            
            for(Simbolo s:arbol.descendingSet()){
                h.addSymbol(s.getNombre().charAt(0), s.getCodigo()); //TODO ESTO ES MEDIO PELIGROSO
            }
            
            h.encode() //AAA OLA FER
            
            
        } catch (FileNotFoundException e) {
            System.out.println("no hay archivoo");
        }

    }
}
