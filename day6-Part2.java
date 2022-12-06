package org.rgarcial.junit5app.ejemplos;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\entrada.txt"))){
            //mjqjpqmgbljsphdztnvjfqwrcgsmlb
            String linea;
            int posicionInicio = 0;
            int posicionFin = 14;
            int tamanyoGrupo = 14;

            while ((linea = br.readLine()) != null) {
                String[] fila = linea.split("");
                // Método que compara si hay repetidos y la posición final en la que lo ha encontrado
                int solucion = indiceFinalGrupoLetrasUnicos(fila,posicionInicio,posicionFin,tamanyoGrupo);
                System.out.println("POSICION -> " + solucion);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int indiceFinalGrupoLetrasUnicos (String[] linea, int posicionInicial, int posicionFinal, int tamanyoGrupo) {
        Set<String> grupoCuatroLetras = new HashSet<>();
        int resultado = 0;
        for(int i = posicionInicial; i < posicionFinal; i++) {
            grupoCuatroLetras.add(linea[i]);
        }
        // Al ser un Set los valores no se repiten, entonces si su longitud no es 4 entoces hay repetidos
        if(grupoCuatroLetras.size() != tamanyoGrupo) {
            posicionInicial++;
            posicionFinal++;
            return indiceFinalGrupoLetrasUnicos(linea,posicionInicial,posicionFinal,tamanyoGrupo);
        }
        resultado = posicionFinal;
        return resultado;
    }
}
