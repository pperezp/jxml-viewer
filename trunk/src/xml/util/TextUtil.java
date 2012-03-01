/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author Fabiola Muñoz
 */
public class TextUtil implements FocusListener{
    private JTextField campo;
    private String mensaje;
    
    public TextUtil(JTextField campo, String mensajeFocusLost){
        this.campo = campo;
        this.campo.addFocusListener(this);
        mensaje = mensajeFocusLost;
    }

    @Override
    public void focusGained(FocusEvent e) {
        campo.setText("");
        campo.setFont(new Font("Tahoma", Font.PLAIN, 12));
        campo.setForeground(Color.BLACK);
    }

    @Override
    public void focusLost(FocusEvent e) {
        campo.setFont(new Font("Tahoma", Font.ITALIC, 12));
        campo.setText(mensaje);
        campo.setForeground(new Color(153,153,153));
    }
}
