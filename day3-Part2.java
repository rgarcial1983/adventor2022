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
            int indice = 1;
            String cadena1 = "";
            String cadena2 = "";
            String cadena3 = "";

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                // Se obtienen las tres cadenas
                if(indice == 1) cadena1 = linea;
                if(indice == 2) cadena2 = linea;
                if(indice == 3) cadena3 = linea;

                // Si no tenemos las 3 cadenas informadas no hacemos nada.
                if(cadena1.isEmpty() || cadena2.isEmpty() || cadena3.isEmpty()) {
                    indice++;
                    continue;
                }

                // Obtener el caracter que se repite
                String letraRepetida = obtenerLetraRepetida(cadena1, cadena2, cadena3);
                System.out.println("Letra repetida -> " + letraRepetida);

                // Según el caracter repetido obtener puntuación
                totalPuntos += calcularPuntos(letraRepetida);

                // Inicializamos el índice y las cadenas
                cadena1 = "";
                cadena2 = "";
                cadena3 = "";
                indice = 1;
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
    private static String obtenerLetraRepetida(String cadena1, String cadena2, String cadena3) {
        String letraRepetida = "";
        List<Character> letrasC1 = convertirCadenaAListaChar(cadena1);
        List<Character> letrasC2 = convertirCadenaAListaChar(cadena2);
        List<Character> letrasC3 = convertirCadenaAListaChar(cadena3);

        // Recorremos la primera lista y comprobamos si está contenida en la segunda.
        for(Character letra : letrasC1) {
            if(letrasC2.contains(letra) && letrasC3.contains(letra)) {
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
