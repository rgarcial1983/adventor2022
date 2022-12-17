package org.rgarcial.junit5app.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\entrada.txt"))) {
            int indice = 0;
            String[][] bosque = new String[99][99];
            String linea = br.readLine();
            while (linea != null) {
                char[] arboles = linea.toCharArray();
                for (int i = 0; i < arboles.length; i++) {
                    bosque[indice][i] = String.valueOf(arboles[i]);
                }
                linea = br.readLine();
                indice++;
            }
            System.out.println("Arboles: " + Arrays.deepToString(bosque) + "\n");

            List<Integer> puntuaciones = new ArrayList<>();

            // Se recorren todos los árboles de izquierda a derecha y de arriba abajo
            for (int fila = 0; fila < bosque.length; fila++) {

                for (int columna = 0; columna < bosque.length; columna++) {

                    int arbolActual = Integer.parseInt(bosque[fila][columna]);
                    int totalzquierda = 0;
                    int totalDerecha = 0;
                    int totalArriba = 0;
                    int totalAbajo = 0;

                    for (int l = columna + 1; l < bosque.length; l++) {
                        int arbolDerecho = Integer.parseInt(bosque[fila][l]);
                        totalDerecha++;
                        if (arbolDerecho >= arbolActual) {
                            break;
                        }
                    }

                    for (int k = columna - 1; k >= 0; k--) {
                        int arbolIzquierdo = Integer.parseInt(bosque[fila][k]);
                        totalzquierda++;
                        if (arbolIzquierdo >= arbolActual) {
                            break;
                        }
                    }

                    for (int m = fila - 1; m >= 0; m--) {
                        int arbolArriba = Integer.parseInt(bosque[m][columna]);
                        totalArriba++;
                        if (arbolArriba >= arbolActual) {
                            break;
                        }
                    }

                    for (int n = fila + 1; n < bosque.length; n++) {
                        int arbolAbajo = Integer.parseInt(bosque[n][columna]);
                        totalAbajo++;
                        if (arbolAbajo >= arbolActual) {
                            break;
                        }
                    }
                    puntuaciones.add(totalzquierda * totalDerecha * totalAbajo * totalArriba);
                }
            }

            System.out.println("Arbol más visible: " + puntuaciones.stream()
                    .mapToInt(a -> a.intValue())
                    .max()
                    .getAsInt());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
