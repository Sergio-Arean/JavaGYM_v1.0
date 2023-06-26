/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import UI.UtilidadesUI.CentradorDeColumna;
import excepciones.NoEncontradoException;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.ActivYrutina.Actividad;
import model.Enum.EGrupoSanguineo;
import model.Enum.EdiaSemana;
import model.Enum.Eestado;
import model.Enum.EtipoActividad;
import model.Otros.Gimnasio;
//import model.Otros.PermisoInvalidoException;
import model.Personal.Usuario;
import model.Persona.*;
import model.Personal.Administrativo;
import model.Personal.Instructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergi
 */
public class PanelUsuarios extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
      private Gimnasio gimnasio;
      private Usuario usuario;
     private TableRowSorter <DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla
     
     //Prueba para poder actualizar clientes
     private ArrayList<Usuario> administrativos; //arraylist que se carga con el metodo obtenerclientes
     
     public PanelUsuarios(Usuario usuario, Gimnasio gimnasio) {
        initComponents();

        this.gimnasio = gimnasio;
        this.usuario = usuario;
        administrativos = ObtenerAdministrativos(); //desde aca el arraylist es cargado
        CargarTabla(administrativos);
        btnBorrar.putClientProperty("JButton.buttonType", "roundRect");

    }

    private void CargarTabla(ArrayList<Usuario> administrativos) {

        DefaultTableModel model = (DefaultTableModel) tablaObjetos.getModel();
        model.setRowCount(0); // Limpia todas las filas existentes

        JTable jTableClientes = tablaObjetos;

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Email");
        modeloTabla.addColumn("Nombre de Usuario");

        for (Usuario administrativo : administrativos) {
            Object[] fila = {administrativo.getNombre(), administrativo.getGenero().name(), administrativo.getEmail(), administrativo.getUsuario()};
            modeloTabla.addRow(fila);

        }

        jTableClientes.setModel(modeloTabla);

        TableColumnModel columnModel = jTableClientes.getColumnModel();

        int columnIndex = 0;
        TableColumn column = columnModel.getColumn(columnIndex);

        int preferredWidth = 100;
        column.setPreferredWidth(preferredWidth);

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn columna_a_centrar = columnModel.getColumn(i);
            columna_a_centrar.setCellRenderer(new CentradorDeColumna());

        }

        //inicio para filtrar
        jTableClientes.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableClientes.setRowSorter(sorter);
        //fin para filtrar

        tablaObjetos.setDefaultEditor(Object.class, null); //haciendo que tabla no sea editable

    }
    
    private ArrayList<Usuario> ObtenerAdministrativos() {
        String json_instructores = gimnasio.CompartirDatosAdministrativos();
        ArrayList<Usuario> administrativos = new ArrayList<>();
        try {
            JSONArray jsonArray_clientes = new JSONArray(json_instructores);
            for (int i = 0; i < jsonArray_clientes.length(); i++) {
                Usuario usuario = new Usuario();
                usuario = usuario.fromJson(jsonArray_clientes.getJSONObject(i));
                administrativos.add(usuario);
            }
        } catch (JSONException e) {
            Utilidades.MostrarMensajeInformativo(e.getMessage());
        }
        return administrativos;

    }

                         
    private void filtrar(){
       sorter.setRowFilter(RowFilter.regexFilter("(?i)"+tfFiltroBusqueda.getText()));
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public PanelUsuarios() {
    }
// @SuppressWarnings("unchecked"); //esto esta comentaado por mi
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObjetos = new javax.swing.JTable();
        tfFiltroBusqueda = new javax.swing.JTextField();
        btnBorrar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(757, 347));

        background.setMaximumSize(new java.awt.Dimension(915, 347));
        background.setMinimumSize(new java.awt.Dimension(915, 347));

        tablaObjetos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaObjetos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaObjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObjetosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaObjetos);
        if (tablaObjetos.getColumnModel().getColumnCount() > 0) {
            tablaObjetos.getColumnModel().getColumn(0).setResizable(false);
            tablaObjetos.getColumnModel().getColumn(1).setResizable(false);
            tablaObjetos.getColumnModel().getColumn(2).setResizable(false);
            tablaObjetos.getColumnModel().getColumn(3).setResizable(false);
        }

        tfFiltroBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroBusquedaActionPerformed(evt);
            }
        });
        tfFiltroBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFiltroBusquedaKeyReleased(evt);
            }
        });

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 757, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfFiltroBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaKeyReleased
        // TODO add your handling code here:
                filtrar();
    }//GEN-LAST:event_tfFiltroBusquedaKeyReleased

    private void tfFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroBusquedaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarConValidacion();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:

        
    }//GEN-LAST:event_tablaObjetosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaObjetos;
    private javax.swing.JTextField tfFiltroBusqueda;
    // End of variables declaration//GEN-END:variables


    private void ModificarInstructor() {
        String dni_instructor = "";
        Instructor instructor_a_modificar = null;
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {

            try {
                dni_instructor = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
                instructor_a_modificar = gimnasio.LocalizarInstructor(dni_instructor);
                AbrirDialogModificarInstructor(instructor_a_modificar);

            } catch (NoEncontradoException e) {
                Utilidades.MostrarMensajeWarning(e.getMessage());
            }

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un instructor!");
        }

    }

    private void AbrirDialogModificarInstructor(Instructor instructor_a_modificar) {
        DialogModificarInstructor dialog_modificar_instructor = new DialogModificarInstructor(new Escritorio(usuario, gimnasio), true, gimnasio, instructor_a_modificar);
        dialog_modificar_instructor.setLocationRelativeTo(this);
        dialog_modificar_instructor.setVisible(true);
        dialog_modificar_instructor.setSize(730, 624);

    }

    private void ActualizarTabla() {
        administrativos = ObtenerAdministrativos(); //desde aca el arraylist es cargado
        CargarTabla(administrativos);
    }

    private boolean ConfirmarOperacion(String texto_pregunta) {
        boolean confirma = false;
        String[] opciones = {"No", "Sí"};
        int rta = JOptionPane.showOptionDialog(null, texto_pregunta, "Atención",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, opciones, opciones[1]);

        if (rta == 1) {
            confirma = true;
        }
        return confirma;
    }

    private void BorrarConValidacion() {
        String nombre_usuario = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {

            try {

                nombre_usuario = (String) tablaObjetos.getValueAt(fila_seleccionada, 3);
                boolean confirmar_operacion = ConfirmarOperacion("¿Borrar Usuario?");
                if (confirmar_operacion) {
                    gimnasio.borrarUsuario(nombre_usuario);
                    Utilidades.MostrarMensajeInformativo("El usuario fue eliminado exitosamente");
                    ActualizarTabla();
                }

            } catch (NoEncontradoException e) {
                Utilidades.MostrarMensajeWarning(e.getMessage());
            }

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un Usuario!");
        }

    }


}