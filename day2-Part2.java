package org.rgarcial.junit5app.ejemplos;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("D:\\piedaPapelTijera.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            int totalPuntos = 0;
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                totalPuntos += combate(linea.charAt(0), linea.charAt(2));
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


    private static int combate(char oponente, char resultadoDebeSer) {
        int puntuacion = 0;

        //Indice 0 > piedra
        //Indice 1 > papel
        //Indice 2 > tijera
        int[][] tablaPuntosSegunJugadas =  {
                {4,8,3},
                {1,5,9},
                {7,2,6}};

        Integer indiceOponente = obtenerEleccion(oponente);
        Integer indiceYo = obtenerEleccionContraria(oponente, resultadoDebeSer);

        // Asigno la puntuación de la jugada
        puntuacion = tablaPuntosSegunJugadas[indiceOponente][indiceYo];
        System.out.println("Puntuación -> " + puntuacion);

        return puntuacion;
    }

    /**
     * Devuelve la opción según el resultado.
     * @param oponente
     * @param resultadoDebeSer
     * @return
     */
    private static Integer obtenerEleccionContraria(char oponente, char resultadoDebeSer) {

        switch (resultadoDebeSer) {
            case 'X'://Perder
                if(oponente == 'A') return 2;
                if(oponente == 'B') return 0;
                if(oponente == 'C') return 1;
            case 'Y'://Empatar
                if(oponente == 'A') return 0;
                if(oponente == 'B') return 1;
                if(oponente == 'C') return 2;
            case 'Z'://Ganar
                if(oponente == 'A') return 1;
                if(oponente == 'B') return 2;
                if(oponente == 'C') return 0;
        }
        return 0;
    }

    /**
     * Devuelve 0 si se ha elegido piedra, 1 si se ha elegido papel y 2 si se ha elegido tijeras
     * @param letra
     * @return
     */
    private static int obtenerEleccion(char letra) {
        switch (letra) {
            case 'A':
            case 'X':
                return 0;
            case 'B':
            case 'Y':
                return 1;
            case 'C':
            case 'Z':
                return 2;
        }
        return 0;
    }
}
