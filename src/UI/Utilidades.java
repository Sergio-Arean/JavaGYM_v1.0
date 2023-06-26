/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.*;
import jdk.dynalink.linker.support.Guards;


/**
 *
 * @author sergi
 */
public class Utilidades {
            public static void MostrarMensajeInformativo(String texto_mensaje){
        JOptionPane.showMessageDialog(null, texto_mensaje);
    }
            
                        public static void MostrarMensajeWarning(String texto_mensaje){
        JOptionPane.showMessageDialog(null, texto_mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

            

            

    
}
