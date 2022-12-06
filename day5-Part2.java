package org.rgarcial.junit5app.ejemplos;

import java.io.*;
import java.net.StandardSocketOptions;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static Stack<String> pila1 = new Stack<>();
    static Stack<String> pila2 = new Stack<>();
    static Stack<String> pila3 = new Stack<>();
    static Stack<String> pila4 = new Stack<>();
    static Stack<String> pila5 = new Stack<>();
    static Stack<String> pila6 = new Stack<>();
    static Stack<String> pila7 = new Stack<>();
    static Stack<String> pila8 = new Stack<>();
    static Stack<String> pila9 = new Stack<>();

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get("D:\\entrada.txt"))){
            Map<Integer, Stack<String>> mapaPilas = new HashMap<>();

            // Cargar valores
            cargarDatos(mapaPilas);

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] fila = linea.split(" ");
                Integer mover = Integer.valueOf(fila[1]);
                Integer origen = Integer.valueOf(fila[3]);
                Integer destino = Integer.valueOf(fila[5]);

                // bloque a mover
                List<String> bloqueAMover = new ArrayList<String>();
                for (int i = 0; i < mover; i++) {
                    // Cogemos la letra a mover y la agregamos al bloque
                    bloqueAMover.add(mapaPilas.get(origen).peek());
                    // La borramos de la pila origen
                    mapaPilas.get(origen).pop();
                }
                // Agregamos el bloque entero a la pila destino pero en orden inverso
                List<String> lista = bloqueAMover;
                Collections.reverse(lista);
                for(String letra: lista) {
                    mapaPilas.get(destino).push(letra);
                }
            }

            // Formamos la palabra con el último valor de las respectivas pilas
            String palabra = "";
            for(Stack<String> p : mapaPilas.values()) {
                palabra+= p.lastElement();
            }
            System.out.println("PALABRA -> " + palabra);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cargamos los mapas con los datos de las pilas recibidas
     * @param mapaPilas
     */
    private static void cargarDatos(Map<Integer, Stack<String>> mapaPilas) {
        // 1
        pila1.push("B");
        pila2.push("R");
        pila3.push("B");
        pila4.push("C");
        pila5.push("D");
        pila6.push("H");
        pila7.push("D");
        pila8.push("C");
        pila9.push("N");

        // 2
        pila1.push("Q");
        pila2.push("Q");
        pila3.push("M");
        pila4.push("Z");
        pila5.push("Z");
        pila6.push("N");
        pila7.push("G");
        pila8.push("G");
        pila9.push("J");

        // 3
        pila1.push("C");
        pila2.push("W");
        pila3.push("R");
        pila4.push("H");
        pila5.push("H");
        pila6.push("P");
        pila7.push("T");
        pila8.push("M");
        pila9.push("B");

        // 4
        pila2.push("Z");
        pila3.push("L");
        pila4.push("V");
        pila5.push("B");
        pila6.push("C");
        pila7.push("R");
        pila8.push("N");
        pila9.push("M");

        // 5
        pila3.push("V");
        pila4.push("T");
        pila5.push("N");
        pila6.push("J");
        pila7.push("W");
        pila8.push("B");
        pila9.push("W");

        //6
        pila4.push("W");
        pila5.push("V");
        pila6.push("F");
        pila7.push("Z");
        pila8.push("W");
        pila9.push("Q");

        //7
        pila5.push("G");
        pila6.push("V");
        pila7.push("S");
        pila8.push("Z");
        pila9.push("F");

        //8
        pila6.push("Q");
        pila8.push("P");
        pila9.push("P");

        mapaPilas.put(1, pila1);
        mapaPilas.put(2, pila2);
        mapaPilas.put(3, pila3);
        mapaPilas.put(4, pila4);
        mapaPilas.put(5, pila5);
        mapaPilas.put(6, pila6);
        mapaPilas.put(7, pila7);
        mapaPilas.put(8, pila8);
        mapaPilas.put(9, pila9);
    }

}
