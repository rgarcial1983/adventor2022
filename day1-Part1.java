package org.rgarcial.junit5app.ejemplos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("D:\\comidas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            Integer maximo = 0;
            Integer totalElfo = 0;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                if(!linea.isEmpty()) {
                    totalElfo += Integer.valueOf(linea);
                } else {
                    maximo = (totalElfo > maximo)?totalElfo:maximo;
                    totalElfo = 0;
                }
            }
            System.out.println("MAXIMO-> " + maximo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
