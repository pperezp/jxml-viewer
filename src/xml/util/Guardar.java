/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.util;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Patricioz
 */
public class Guardar {

    private static String ruta;
    private static String[] extensiones;
    private static JFileChooser fileChooser;
    private static FileNameExtensionFilter[] filtros;
    private static File archivo;
    private static String rutaSinExtension;

    /**
     * @return the rutaSinExtension
     */
    public static String getRutaSinExtension() {
        return rutaSinExtension;
    }


    private static void setExtension(String[] extensiones) {
        Guardar.extensiones = new String[extensiones.length];
        Guardar.extensiones = extensiones;
        escribirExtensiones();
    }

    private static void escribirExtensiones() {
        for (final String extension : extensiones) {
            fileChooser.addChoosableFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    if (f.getName().contains(extension) || f.isDirectory()) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public String getDescription() {
                    return "*."+extension;
                }
            });
        }
    }

    /**
     *
     * @param nombreDeArchivo
     * @param extensiones
     * Las extensiones son los Filtros con los cuales se basará
     * en guardar un archivo.<br>
     * Escriba los filtros de extensiones separados de una coma<br>
     * <p><b>Ejemplo:</b> exe,jpeg,bat</p>
     *
     * Si como parámetro le entregas null a extensiones, se podrán abrir todos los tipos,
     * en otras palabras no habrá filtros
     *
     *
     * @param textoDeBotonDeAprovacion
     * @param rutaDirectorioPorDefecto 
     * @return Retornará TRUE si el usuario guarda algun archivo y
     * Retornará FALSE si el usuario no guarda algún archivo u
     * ocurrio un error
     */
    public static boolean guardarComo(String nombreDeArchivo, String extensiones, String textoDeBotonDeAprovacion, String rutaDirectorioPorDefecto) {
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (extensiones != null) {
            String[] ext = extensiones.split(",");
            sacarEspacios(ext);
            setExtension(ext);
        }

        
        fileChooser.setSelectedFile(new File(nombreDeArchivo));
        fileChooser.setCurrentDirectory(new File(rutaDirectorioPorDefecto));
        return guardar(textoDeBotonDeAprovacion);
    }

    /**
     *
     * @return
     */
    public static String getRuta() {
        return ruta;
    }

    private static void sacarEspacios(String[] ext) {
        for (int i = 0; i < ext.length; i++) {
            ext[i] = ext[i].trim();
        }
    }

    private static boolean guardar(String textoDeBotonDeAprovacion) {
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                ruta = archivo.getPath() + fileChooser.getFileFilter().getDescription().substring(1);
                rutaSinExtension = archivo.getPath();
                if (new File(ruta).exists()) {//si el archivo existe, llamo a la misma funcion, recursivamente
                    Mensaje.warning("Archivo ya Existente", "El archivo ya existe. Cambie el nombre del Archivo");
                    return guardar(textoDeBotonDeAprovacion);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean elegirUbicacion(String textoDeBotonDeAprovacion){
        int seleccion = fileChooser.showDialog(null, textoDeBotonDeAprovacion);
        try {
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                ruta = archivo.getPath();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean abrirUbicacion(String textoDeBotonDeAprovacion){
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
//        fileChooser.setAcceptAllFileFilterUsed(false);
        return elegirUbicacion(textoDeBotonDeAprovacion);
    }
}
