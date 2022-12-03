package org.rgarcial.junit5app.ejemplos;

import java.io.*;
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
            archivo = new File("D:\\entrada.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            int totalPuntos = 0;
            
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                int indiceMitad = linea.length() / 2;

                // Se obtienen las dos cadenas
                String cadena1 = linea.substring(0, indiceMitad);
                String cadena2 = linea.substring(indiceMitad);

                // Obtener el caracter que se repite
                String letraRepetida = obtenerLetraRepetida(cadena1,cadena2);
                System.out.println("Letra repetida -> " + letraRepetida);

                // Según el caracter repetido obtener puntuación
                totalPuntos += calcularPuntos(letraRepetida);
            }
            System.out.println("TOTAL PUNTOS -> " + totalPuntos);
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

    /**
     * Calcular puntos
     * @param letraRepetida
     * @return
     */
    private static int calcularPuntos(String letraRepetida) {
        List<String> listaPuntosPorLetra = List.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
        int puntos = listaPuntosPorLetra.indexOf(letraRepetida) + 1;//los arrays comienzan por 0
        System.out.println("Letra " + letraRepetida + " en posicion(puntos) -> " + puntos);
        return puntos;
    }

    /**
     * Se obtiene la letra repetida dada dos cadenas
     * @param cadena1
     * @param cadena2
     * @return
     */
    private static String obtenerLetraRepetida(String cadena1, String cadena2) {
        String letraRepetida = "";
        List<Character> letrasC1 = convertirCadenaAListaChar(cadena1);
        List<Character> letrasC2 = convertirCadenaAListaChar(cadena2);

        // Recorremos la primera lista y comprobamos si está contenida en la segunda.
        for(Character letra : letrasC1) {
            if(letrasC2.contains(letra)) {
                letraRepetida = letra.toString();
                break;
            }
        }
        return letraRepetida;
    }

    /**
     * Convierte una cadena en un array de caracteres
     * @param cadena
     * @return
     */
    private static List<Character> convertirCadenaAListaChar(String cadena){
        List<Character> caracteres = cadena.chars()
                .mapToObj(e -> (char)e)
                .collect(Collectors.toList());
        return caracteres;
    }

}
