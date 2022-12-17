package org.rgarcial.junit5app.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

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

            int arbolesVisibles = 0;
            // Se recorren todos los árboles de izquierda a derecha y de arriba abajo
            for (int fila = 0; fila < bosque.length; fila++) {
                for (int columna = 0; columna < bosque.length; columna++) {

                    // Primera fila, última fila, primer árbol izquierdo y último árbol derecho
                    boolean primeraFila = fila == 0;
                    boolean ultimaFila = fila == bosque.length - 1;
                    boolean primeraColuma = columna == 0 && fila > 0 && fila < bosque.length - 1;
                    boolean ultimaColuma = columna == bosque.length - 1 && fila > 0 && fila < bosque.length - 1;

                    if (primeraFila || ultimaFila || primeraColuma || ultimaColuma) {
                        System.out.println("Arbol exterior: " + fila + "|" + columna + " height:" + bosque[fila][columna]);
                        arbolesVisibles++;
                    }

                    // Arboles internos
                    if (fila > 0 && columna > 0 && fila < bosque.length - 1 && columna < bosque.length - 1) {

                        int valorArbolActual = Integer.parseInt(bosque[fila][columna]);
                        boolean visibleIzquierda = true;
                        boolean visibleDerecha = true;
                        boolean visibleArriba = true;
                        boolean visibleAbajo = true;

                        // Visible desde Izquierda
                        visibleIzquierda = isVisibleIzquierda(columna, valorArbolActual, bosque[fila]);

                        // Visible desde Derecha
                        visibleDerecha = isVisibleDerecha(bosque, fila, columna, valorArbolActual);

                        // Visible desde arriba
                        visibleArriba = isVisibleArriba(bosque, fila, columna, valorArbolActual);

                        // Visible desde abajo
                        visibleAbajo = isVisibleAbajo(bosque, fila, columna, valorArbolActual);

                        // Si desde algún punto es visible el árbol se considera visible
                        if (visibleIzquierda || visibleDerecha || visibleArriba || visibleAbajo) {
                            arbolesVisibles++;
                            System.out.println("Árbol de posición: " + fila + "|" + columna + " altura: " + bosque[fila][columna] + " es visible\n");
                        }
                    }
                }
            }

            System.out.println("Total árboles visibles: " + arbolesVisibles);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isVisibleAbajo(String[][] bosque, int fila, int columna, int valorArbolActual) {
        for (int indice = fila + 1; indice < bosque.length; indice++) {
            int bottomTreeSize = Integer.parseInt(bosque[indice][columna]);
            if (bottomTreeSize >= valorArbolActual) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleArriba(String[][] bosque, int fila, int columna, int valorArbolActual) {
        for (int indice = 0; indice < fila; indice++) {
            int valorArbolArriba = Integer.parseInt(bosque[indice][columna]);
            if (valorArbolArriba >= valorArbolActual) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleDerecha(String[][] bosque, int fila, int columna, int valorArbolActual) {
        for (int indice = columna + 1; indice < bosque.length; indice++) {
            int valorArbolDerecha = Integer.parseInt(bosque[fila][indice]);
            if (valorArbolDerecha >= valorArbolActual) {
                return false;
            }
        }
        return true;
    }

    private static boolean isVisibleIzquierda(int columna, int valorArbolActual, String[] bosque) {
        for (int indice = 0; indice < columna; indice++) {
            int valorArbolIzquierda = Integer.parseInt(bosque[indice]);
            if (valorArbolIzquierda >= valorArbolActual) {
                return false;
            }
        }
        return true;
    }
}
