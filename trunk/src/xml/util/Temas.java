/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xml.util;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import xml.gui.Aplicacion;

/**
 *
 * @author Patricio Pérez Pinto
 */
public class Temas {
    public static final String WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String WINDOWS_CLASSIC= "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    public static final String MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String NIMBUS = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
    public static final String SO_ACTUAL = UIManager.getSystemLookAndFeelClassName();
    public static String ACTUAL;
    public static boolean IS_LINEAS;
    public static boolean IS_SUBSTANCE;
    
    public static void cambiarTema(String tema, Object[] formularios) {
        try {
            UIManager.setLookAndFeel(tema);
            for(Object f: formularios){
                if(f instanceof JFrame){
                    JFrame form = (JFrame)f;
                    SwingUtilities.updateComponentTreeUI(form);
                }else if(f instanceof JDialog){
                    JDialog form = (JDialog)f;
                    SwingUtilities.updateComponentTreeUI(form);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
