/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.utilidades;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author andres
 */
public class Mensajes {
    public static  void error(Component parent, Object mensaje){
        JOptionPane.showMessageDialog(parent, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    public static  void info(Component parent, Object mensaje){
        JOptionPane.showMessageDialog(parent, mensaje, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
    }
}
