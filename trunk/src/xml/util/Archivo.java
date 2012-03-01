/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author Fabiola
 */
public class Archivo {

    private static File archivo;

    /**
     *
     * @param nombreArchivo
     * @param texto
     * @param sobreEscribir
     * @param archivoSoloLectura
     * @throws IOException
     */
    public static void escribirEnAchivo(String nombreArchivo, String texto, boolean sobreEscribir, boolean archivoSoloLectura)
            throws IOException {
        archivo = new File(nombreArchivo);
        archivo.createNewFile();
        if (archivoSoloLectura) {
            archivo.setReadOnly();
        }
        PrintWriter archivoWriter = new PrintWriter(new FileWriter(archivo, !sobreEscribir));
        archivoWriter.println(texto);
        if (archivoWriter != null) {
            archivoWriter.close();
        }
    }

    /**
     *
     * @param ruta
     * @return
     */
    public static boolean existeArchivo(String ruta) {
        archivo = new File(ruta);
        return archivo.isFile();
    }

    /**
     *
     * @param ruta
     * @return
     */
    public static long getFileSize(String ruta) {
        archivo = new File(ruta);
        if (archivo.isFile()) {
            return archivo.length();
        }
        return 0;
    }

    /**
     * deve permanecer en la mismas ruta del jar
     * @param nombreArchivo
     * @return
     * @throws IOException
     */
    public static String leerArchivo(String nombreArchivo) throws IOException {
        String texto = "";
        FileInputStream fis = null;
        BufferedReader reader = null;
        try {
            fis = new FileInputStream(nombreArchivo);
            reader = new BufferedReader(new InputStreamReader(fis));
            String linea;
            while ((linea = reader.readLine()) != null) {
                texto += linea + "\n";
            }
            return texto;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fis.close();
            reader.close();
        }
        return texto;
    }
}
