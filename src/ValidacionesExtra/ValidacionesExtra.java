/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ValidacionesExtra;

import static cjb.ci.CtrlInterfaz.habilita;
import static cjb.ci.CtrlInterfaz.selecciona;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author HP
 */
public class ValidacionesExtra
{

    public static void validaCopyPaste(KeyEvent evt)
    {
        if ((evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_C)
                || // Ctrl + C
                (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_X)
                || // Ctrl + X
                (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V))
        {  // Ctrl + V
            evt.consume(); // Cancela la acción
        }
    }

    public static void validaLongitud(KeyEvent evt, String texto, int longitud)
    {
        if (texto.length() > longitud)
        {
            evt.consume();
        }
    }

    public static void cambia(int keyConstant, KeyEvent evt, Object obj)
    {
        if (evt.getKeyCode() == keyConstant)
        {
            habilita(true, obj);
            if (obj instanceof JTextField jTextField)
            {
                selecciona(jTextField);
            } else if (obj instanceof JButton jButton)
            {
                jButton.requestFocus();
            } else if (obj instanceof JComboBox<?> jComboBox)
            {
                jComboBox.requestFocus();
            } else if (obj instanceof JMenuItem jMenuItem)
            {
                jMenuItem.requestFocus();
            } else if (obj instanceof JToolBar jToolBar)
            {
                jToolBar.requestFocus();
            } else if (obj instanceof JTextArea jTextArea)
            {
                jTextArea.requestFocus();
            }
        }
    }

}
