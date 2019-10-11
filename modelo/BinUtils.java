package modelo;

import java.nio.charset.StandardCharsets;

public class BinUtils {
    public BinUtils() {
        super();
    }
    
    public static byte[] string2Bin(String string){
        byte[] datos = string.getBytes(StandardCharsets.UTF_8);
        
        for(int i = 0;i<datos.length;i++)
            datos[i]-=48;
        byte[] datosApretados = new byte[datos.length/8+1+1]; //el ultimo byte es para guardar la cantidad de ceros que estan de más
        int i=0; int j=0;
        byte byteactual=0;
        
        while(i<datos.length){
            if((i)%8==0 && i!=0){ //esta enterito lo meto
                datosApretados[j]=byteactual;
                j++;
                byteactual=datos[i]; //si no perdia uno en el camino
            }
            else{ //siga el bailee
                byteactual = (byte) (byteactual << 1); //byteactual << 1 pero en croto
                byteactual = (byte) (byteactual | (datos[i]&0x1));
                
            }
            i++;
        }
        
        //guarda con el de la punta
        byteactual = (byte) (byteactual << 8-i%8); //se nos ocurrio mirando una representacion gráfica
        datosApretados[j]=byteactual;
        datosApretados[j+1] = (byte) (8 -i % 8); //si el ultimo bit tenia que ser 111, se guarda 11100000 y en este caso el numero 5, por que
        // es el numero de ceros extras que hay que ignorar
        
        return datosApretados;
    }

    /**
     * En este metodo va a devolver el String estirado ya, de ceros y unos.
     * Lamentablemente va a tener los ceros extras que se agregan por al limitacion que tiene java de no permitir escribir de a bits
     * por lo que si el ultimo bit a escribir eran po rejemplo solo (111), en el string resultado quedará (11100000)
     * @param bytes
     * @return
     */
    public static String bin2String(byte[] bytes){
        StringBuilder resultado = new StringBuilder();
        int j=0;
        for(j=0;j<bytes.length-2;j++){ //por el tema de los ceros
            int i;
            for(i=0b10000000;i>0;i/=2){
                if((bytes[j]&i)==0)
                    resultado.append("0");
                else
                    resultado.append("1");
            }
        }
        int bitsAIgnorar = bytes[bytes.length-1];
        for(int i=0b10000000;i>=(0b1<<bitsAIgnorar);i/=2){
            if((bytes[j]&i)==0)
                resultado.append("0");
            else
                resultado.append("1");
        }
        
        
        return resultado.toString();
        
        
    }
}
