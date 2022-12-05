package org.rgarcial.junit5app.ejemplos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\entrada.txt"))){
            int totalParte1 = 0;
            int totalParte2 = 0;
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                // Construir cadenas
                String[] secciones = linea.split(",");
                Integer seccion11 = Integer.valueOf(secciones[0].split("-")[0]);
                Integer seccion12 = Integer.valueOf(secciones[0].split("-")[1]);
                Integer seccion21 = Integer.valueOf(secciones[1].split("-")[0]);
                Integer seccion22 = Integer.valueOf(secciones[1].split("-")[1]);


                // Comprobaciones
                if(seccion11 <= seccion21 && seccion12 >= seccion22)
                    totalParte1++;
                else if(seccion11 >= seccion21 && seccion12 <= seccion22)
                    totalParte1++;

                if((seccion11 <= seccion21 && seccion12 >= seccion21) || (seccion11 <= seccion22 && seccion12 >= seccion22))
                    totalParte2++;
                else if((seccion21 <= seccion11 && seccion22 >= seccion11) || (seccion21 <= seccion12 && seccion22 >= seccion12))
                    totalParte2++;

            }

            System.out.println("TOTAL PARTE 1 -> " + totalParte1);
            System.out.println("TOTAL PARTE 2 -> " + totalParte2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
