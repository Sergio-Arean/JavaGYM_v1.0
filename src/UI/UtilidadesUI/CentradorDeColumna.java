/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.UtilidadesUI;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CentradorDeColumna extends DefaultTableCellRenderer { //centrador = renderizador
    //Esta clase permite centralizar lo que apaerece debajo de una columna en particular en un jtable
    
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (renderer instanceof JLabel) {
            ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER);
        }
        return renderer;
    }
    
}
