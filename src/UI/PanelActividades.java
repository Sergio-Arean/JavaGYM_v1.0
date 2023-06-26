/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import UI.UtilidadesUI.CentradorDeColumna;

import excepciones.ExistenteException;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergi
 */
public class PanelActividades extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
      private Gimnasio gimnasio;
      private Usuario usuario;
     private TableRowSorter <DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla
     
     //Prueba para poder actualizar clientes
     private ArrayList<Actividad> actividades; //arraylist de clientes que se carga con el metodo obtenerclientes
     
     
    public PanelActividades(Usuario usuario, Gimnasio gimnasio) {
        initComponents();
        SetearComponentes();
        this.gimnasio = gimnasio;
        this.usuario = usuario;

        actividades = ObtenerActividades(); //desde aca el arraylist es cargado
        CargarTabla(actividades);

        CargarComboBoxBusqueda();
        cbFiltrar.setSelectedIndex(0);

        btnAgregar.putClientProperty("JButton.buttonType", "roundRect");
        btnModificar.putClientProperty("JButton.buttonType", "roundRect");
        btnBorrar.putClientProperty("JButton.buttonType", "roundRect");
        
    }
    
   private void CargarComboBoxBusqueda(){
              cbFiltrar.addItem("Todos");
       for (EtipoActividad tipo_actividad : EtipoActividad.values()) {
            cbFiltrar.addItem(tipo_actividad.name());
        }
   }
    
    private void SetearComponentes(){
        //con este metodo seteamos componentes..
        cbFiltrar.setSelectedIndex(-1); //que ningun componente del combobox este seleccionado
    }
    


     private void pintarImagen(JLabel lbl, String ruta){
ImageIcon imagen = new ImageIcon(ruta);
Icon icono = new ImageIcon (imagen.getImage().getScaledInstance(
	lbl.getWidth(),
	lbl.getHeight(),
	Image.SCALE_DEFAULT
        )
	);lbl.setIcon(icono);
	this.repaint();
 
 }


         private ArrayList<Actividad> ObtenerActividades(){
        String json_actividades= gimnasio.CompartirDatosActividades();
        ArrayList <Actividad> actividades = new ArrayList<>();

        try{
            JSONArray jsonArray_actividades = new JSONArray(json_actividades);
            for(int i=0; i<jsonArray_actividades.length();i++){
                Actividad actividad = new Actividad();
                actividad = actividad.fromJson(jsonArray_actividades.getJSONObject(i));
                actividades.add(actividad);
            }
        }catch(JSONException e){
            Utilidades.MostrarMensajeInformativo("Hubo un error al cargar los datos");
        }
        
            return actividades;
    }
    


    private void CargarTabla(ArrayList<Actividad> actividades){
        
        DefaultTableModel model = (DefaultTableModel) tablaObjetos.getModel();
        model.setRowCount(0);

        JTable jTableClientes = tablaObjetos;

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Actividad");
        modeloTabla.addColumn("Horario");
        modeloTabla.addColumn("Instructor");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Disponible");
        modeloTabla.addColumn("Cupos");

        modeloTabla.addColumn("Dias");

        for (Actividad actividad : actividades) {
            Object[] fila = {actividad.getNombre(),
                actividad.getHorario(),
                actividad.getNombre_instructor(),
                actividad.getPrecio_mensual(),
                actividad.EstaDisponible(),
                (actividad.getCupo() - actividad.getInscriptos()),
                //Datos que tomamos pero no vamos a mostrar: (equals definido a partir de nombre(enum),horario,listadias
                actividad.getListaDias()

            };
            modeloTabla.addRow(fila);

        }

        jTableClientes.setModel(modeloTabla);

        AgrandarColumna(0, 100);
        AgrandarColumna(2, 150); //nombres
        AgrandarColumna(1, 170); //nombres

        TableColumnModel columnModel = jTableClientes.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn columna_a_centrar = columnModel.getColumn(i);
            columna_a_centrar.setCellRenderer(new CentradorDeColumna());

        }

        jTableClientes.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableClientes.setRowSorter(sorter);
        //fin para filtrar

        InvisibilizarColumna(4);
        InvisibilizarColumna(5);
        InvisibilizarColumna(6);


        
    }
    private void AgrandarColumna(int indice_columna, int ancho){
               TableColumnModel columnModel = tablaObjetos.getColumnModel();
               TableColumn column = columnModel.getColumn(indice_columna);
               column.setPreferredWidth(ancho);
 
        
    }
    private void InvisibilizarColumna(int columna){
        //metodo que invisibiliza la columna indicada por parametro
        tablaObjetos.getColumnModel().getColumn(columna).setMinWidth(0);
        tablaObjetos.getColumnModel().getColumn(columna).setMaxWidth(0);
        tablaObjetos.getColumnModel().getColumn(columna).setWidth(0);
        
    }
    
    

    //Inicio metodos para lograr filtro de busqueda
    //ver evento key released                                  
    private void filtrar(){
       sorter.setRowFilter(RowFilter.regexFilter("(?i)"+tfFiltroBusqueda.getText()));
    }
    //Fin metodos para lograr filtro de busqueda


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public PanelActividades() {
    }
// @SuppressWarnings("unchecked"); //esto esta comentaado por mi
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObjetos = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        lblInfoAdicional = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taInfoAdicional = new javax.swing.JTextArea();
        tfFiltroBusqueda = new javax.swing.JTextField();
        cbFiltrar = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
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

        btnAgregar.setText("Nueva");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        lblInfoAdicional.setText("Información Adicional");

        taInfoAdicional.setEditable(false);
        taInfoAdicional.setColumns(20);
        taInfoAdicional.setRows(5);
        jScrollPane2.setViewportView(taInfoAdicional);

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

        cbFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltrarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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
                .addGap(54, 54, 54)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(lblInfoAdicional))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(btnAgregar)
                .addGap(18, 18, 18)
                .addComponent(btnModificar)
                .addGap(18, 18, 18)
                .addComponent(btnBorrar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoAdicional))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnBorrar))
                .addContainerGap(41, Short.MAX_VALUE))
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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:

             AbrirDialogAgregarActividad();
             ActualizarTabla();
             
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tfFiltroBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaKeyReleased
        // TODO add your handling code here:
                filtrar();
    }//GEN-LAST:event_tfFiltroBusquedaKeyReleased

    private void cbFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltrarActionPerformed
       FiltrarTabla();
    }//GEN-LAST:event_cbFiltrarActionPerformed

    private void tfFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroBusquedaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        AbrirDialogModificar();
        ActualizarTabla();
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        BorrarActividad();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:
        MostrarInformacionAdicional();
        
        
    }//GEN-LAST:event_tablaObjetosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInfoAdicional;
    private javax.swing.JTextArea taInfoAdicional;
    private javax.swing.JTable tablaObjetos;
    private javax.swing.JTextField tfFiltroBusqueda;
    // End of variables declaration//GEN-END:variables


    private void MostrarInformacionAdicional() {
       int fila_seleccionada = tablaObjetos.getSelectedRow();
         if (fila_seleccionada >= 0) {
             EtipoActividad nombre_actividad =   (EtipoActividad) tablaObjetos.getValueAt(fila_seleccionada, 0);
             String horario = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
             ArrayList<EdiaSemana> lista_dias = (ArrayList<EdiaSemana>) tablaObjetos.getValueAt(fila_seleccionada, 6);
             
             Actividad actividad_a_buscar = new Actividad(nombre_actividad,horario,lista_dias,"",0,0,false,"",0);
             
             try{
             Actividad actividad_encontrada = gimnasio.buscarActividad(actividad_a_buscar);
             taInfoAdicional.setText(actividad_encontrada.toString());
             }catch(NoEncontradoException e){
                 taInfoAdicional.setText(e.getMessage());
             }


         }
    }

    private void AbrirDialogAgregarActividad() {
                    DialogAgregarActividad dialog_agregar_actividad = new DialogAgregarActividad(new Escritorio(usuario,gimnasio), true,gimnasio);
                    dialog_agregar_actividad.setLocationRelativeTo(this);
                    dialog_agregar_actividad.setVisible(true);
                    dialog_agregar_actividad.setSize(730,624);
    }

    private void ActualizarTabla() {
        actividades = ObtenerActividades(); //desde aca el arraylist es cargado
        CargarTabla(actividades);
    }

    private void AbrirDialogModificar() {
        int fila_seleccionada = tablaObjetos.getSelectedRow();
         if (fila_seleccionada >= 0) {
             EtipoActividad nombre_actividad =   (EtipoActividad) tablaObjetos.getValueAt(fila_seleccionada, 0);
             String horario = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
             ArrayList<EdiaSemana> lista_dias = (ArrayList<EdiaSemana>) tablaObjetos.getValueAt(fila_seleccionada, 6);
             
             Actividad actividad_a_buscar = new Actividad(nombre_actividad,horario,lista_dias,"",0,0,false,"",0);
             
             try{
             Actividad actividad_encontrada = gimnasio.buscarActividad(actividad_a_buscar);
             
                   DialogModificarActividad dialog_modificar_actividad = new DialogModificarActividad (new Escritorio(usuario,gimnasio), true,gimnasio,actividad_encontrada);
                   dialog_modificar_actividad.setLocationRelativeTo(this);
                   dialog_modificar_actividad.setVisible(true);
                    dialog_modificar_actividad.setSize(730,624);
             
             
             }catch(NoEncontradoException e){
                 Utilidades.MostrarMensajeWarning("Se produjo un error al obtener datos de la actividad");
             }
    }
         else{
             Utilidades.MostrarMensajeWarning("Debe seleccionar una actividad!");
         }

}

    private void BorrarActividad() {
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {
            EtipoActividad nombre_actividad = (EtipoActividad) tablaObjetos.getValueAt(fila_seleccionada, 0);
            String horario = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            ArrayList<EdiaSemana> lista_dias = (ArrayList<EdiaSemana>) tablaObjetos.getValueAt(fila_seleccionada, 6);

            Actividad actividad_a_buscar = new Actividad(nombre_actividad, horario, lista_dias, "", 0, 0, false, "", 0);

            try {

                Actividad actividad_encontrada = gimnasio.buscarActividad(actividad_a_buscar);
                boolean confirmar_operacion = ConfirmarOperacion("¿Borrar actividad?");
                if (confirmar_operacion) {
                    gimnasio.borrarActividad(actividad_encontrada);
                    Utilidades.MostrarMensajeInformativo("La actividad fue eliminada exitosamente");
                    ActualizarTabla();
                }

            } catch (NoEncontradoException e) {
                taInfoAdicional.setText(e.getMessage());
            }

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar una Actividad!");
        }

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

    private void FiltrarTabla() {
        
        //     BOXEO, DANZA, FUNCIONAL, ZUMBA, PILATES;
              String seleccion = (String) cbFiltrar.getSelectedItem();
        if (cbFiltrar.getSelectedIndex() >= 0) {
            if (seleccion.equals("Todos")) {
                ActualizarTabla();
            } else if (seleccion.equals("BOXEO")) {
                FiltrarTabla(EtipoActividad.BOXEO);
            } else if (seleccion.equals("DANZA")) {
                FiltrarTabla(EtipoActividad.DANZA);
            } else if (seleccion.equals("FUNCIONAL")) {
                FiltrarTabla(EtipoActividad.FUNCIONAL);
            } else if (seleccion.equals("ZUMBA")) {
                FiltrarTabla(EtipoActividad.ZUMBA);
            } else if (seleccion.equals("PILATES")) {
                FiltrarTabla(EtipoActividad.PILATES);

            }

    }
    }
    
           
           
        private void FiltrarTabla(EtipoActividad nombre_actividad){
        ArrayList <Actividad> actividades_filtradas = new ArrayList<>();
        for(Actividad actividad : actividades){
            if(actividad.getNombre()==nombre_actividad){
                actividades_filtradas.add(actividad);
            }
        }
        CargarTabla(actividades_filtradas);
    }
}

