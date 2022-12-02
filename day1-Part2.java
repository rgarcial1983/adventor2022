package org.rgarcial.junit5app.ejemplos;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
            int totalElfo = 0;
            List<Integer> comidas = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                if(!linea.isEmpty()) {
                    totalElfo+= Integer.parseInt(linea);
                } else {
                    comidas.add(totalElfo);
                    totalElfo = 0;
                }
            }
            List<Integer> ordenados = comidas.stream().collect(Collectors.toList());
            ordenados.sort(Comparator.reverseOrder());

            System.out.println(ordenados.get(0));
            System.out.println(ordenados.get(1));
            System.out.println(ordenados.get(2));
            Integer sumatorio = ordenados.get(0).intValue() + ordenados.get(1).intValue() + ordenados.get(2).intValue();
            System.out.println("SUMATORIO = " +  sumatorio);

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
