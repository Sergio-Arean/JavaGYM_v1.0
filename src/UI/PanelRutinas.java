/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import UI.UtilidadesUI.CentradorDeColumna;
import excepciones.ClienteDeudorException;
import excepciones.NoEncontradoException;
import excepciones.RutinaYaPedida;
import java.awt.Image;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Factura;
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
public class PanelRutinas extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
    private Gimnasio gimnasio;
    private Usuario usuario;
    private TableRowSorter<DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla

    //Prueba para poder actualizar clientes
    private ArrayList<Cliente> clientes; //arraylist de clientes que se carga con el metodo obtenerclientes

    private ArrayList<Factura> facturas_cliente_seleccionado;

    public PanelRutinas(Usuario usuario, Gimnasio gimnasio) {
        initComponents();

        this.gimnasio = gimnasio;
        this.usuario = usuario;

        clientes = ObtenerClientes(); //desde aca el arraylist es cargado
        CargarTabla(clientes);

        SetearComponentes();
    }

    
    private void SetearComponentes(){
        //con este metodo seteamos componentes..
        cbFiltrar.setSelectedIndex(0); //por defecto que setee los activos
        btnNuevaRutina.putClientProperty( "JButton.buttonType", "roundRect" );
        btnSolicitarRutina.putClientProperty( "JButton.buttonType", "roundRect" );
    }
    

    private void pintarImagen(JLabel lbl, String ruta) {
        ImageIcon imagen = new ImageIcon(ruta);
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(
                lbl.getWidth(),
                lbl.getHeight(),
                Image.SCALE_DEFAULT
        )
        );
        lbl.setIcon(icono);
        this.repaint();

 }



    private void CargarTabla(ArrayList<Cliente> clientes){
       
        int edad = 0;
        DefaultTableModel model = (DefaultTableModel) tablaObjetos.getModel();
        model.setRowCount(0);

        JTable jTableClientes = tablaObjetos;

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dni");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Alta Médica");
        modeloTabla.addColumn("Solicitó Rutina");

        for (Cliente cliente : clientes) {
            edad = CalcularEdad(cliente.getFecha_nacimiento());
            Object[] fila = {cliente.getNombre(), cliente.getDni(), cliente.getGenero().name(), edad, cliente.getAltaMedica(), cliente.getSolicitoRutina()};
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
    private int CalcularEdad(LocalDate fecha_nacimiento) {
        int edad = 0;

        LocalDate fecha_actual = LocalDate.now();

        Period periodo = Period.between(fecha_nacimiento, fecha_actual);

        edad = periodo.getYears();

        return edad;
        
    }
    
    
    private ArrayList<Cliente> ObtenerClientes(){
        String json_clientes = gimnasio.CompartirDatosClientes();

        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            JSONArray jsonArray_clientes = new JSONArray(json_clientes);
            for (int i = 0; i < jsonArray_clientes.length(); i++) {
                Cliente cliente = new Cliente();
                cliente = cliente.fromJson(jsonArray_clientes.getJSONObject(i));
                clientes.add(cliente);

            }

        } catch (JSONException e) {
            Utilidades.MostrarMensajeInformativo(e.getMessage());
        }
        return clientes;

    }
    
                               
    private void filtrar(){
       sorter.setRowFilter(RowFilter.regexFilter("(?i)"+tfFiltroBusqueda.getText()));
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public PanelRutinas() {
    }
// @SuppressWarnings("unchecked"); //esto esta comentaado por mi
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObjetos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescripcionRutina = new javax.swing.JTextArea();
        tfFiltroBusqueda = new javax.swing.JTextField();
        cbFiltrar = new javax.swing.JComboBox<>();
        btnNuevaRutina = new javax.swing.JButton();
        btnSolicitarRutina = new javax.swing.JButton();

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

        taDescripcionRutina.setEditable(false);
        taDescripcionRutina.setColumns(20);
        taDescripcionRutina.setRows(5);
        jScrollPane2.setViewportView(taDescripcionRutina);

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

        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Con Rutina Pendiente", "Inactivos", " " }));
        cbFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltrarActionPerformed(evt);
            }
        });

        btnNuevaRutina.setText("Agregar Rutina");
        btnNuevaRutina.setActionCommand("");
        btnNuevaRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaRutinaActionPerformed(evt);
            }
        });

        btnSolicitarRutina.setText("Solicitar Rutina");
        btnSolicitarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarRutinaActionPerformed(evt);
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
                        .addGap(82, 82, 82)
                        .addComponent(btnSolicitarRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(btnNuevaRutina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitarRutina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevaRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
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

    private void cbFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltrarActionPerformed
        // TODO add your handling code here:
    
        if(cbFiltrar.getSelectedIndex()>=0){
             String seleccion = (String) cbFiltrar.getSelectedItem();
                if (seleccion.equals("Activos")) {
                     FiltrarTabla(Eestado.ACTIVO);     
                }
                else if(seleccion.equals("Con Rutina Pendiente")){
                    FiltrarTabla_RutinaPendiente();
                    
                }else if(seleccion.equals("Inactivos")){
                    FiltrarTabla(Eestado.INACTIVO);
                }
                
        }
    }//GEN-LAST:event_cbFiltrarActionPerformed

    private void tfFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroBusquedaActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:
        MostrarRutinaSegunCliente();

    }//GEN-LAST:event_tablaObjetosMouseClicked

    private void btnNuevaRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaRutinaActionPerformed
        // TODO add your handling code here:
        AbrirDialogAgregarRutina();
        ActualizarTabla();


    }//GEN-LAST:event_btnNuevaRutinaActionPerformed

    private void btnSolicitarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarRutinaActionPerformed
        // TODO add your handling code here:
        SolicitarRutina();
    }//GEN-LAST:event_btnSolicitarRutinaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnNuevaRutina;
    private javax.swing.JButton btnSolicitarRutina;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taDescripcionRutina;
    private javax.swing.JTable tablaObjetos;
    private javax.swing.JTextField tfFiltroBusqueda;
    // End of variables declaration//GEN-END:variables

        private void FiltrarTabla(Eestado estado){
        ArrayList <Cliente> clientes_filtrados = new ArrayList<>();
        for(Cliente cliente : clientes){
            if(cliente.getEstado()==estado){
                clientes_filtrados.add(cliente);
            }
        }
        CargarTabla(clientes_filtrados);
    }
        




    private void ActualizarTabla() {
        //metodo que actualiza los datos de la tabla
        clientes = ObtenerClientes(); //desde aca el arraylist es cargado
        CargarTabla(clientes);
        taDescripcionRutina.setText("");
    }

    private Cliente ObtenerCliente(String dni_cliente) {
        //A partir del json del cliente, nos servimos del from Json y ya tenemos a nuestro cliente
        Cliente cliente_a_modificar = new Cliente();
        String json_cliente_seleccionado = ObtenerDatosCliente(dni_cliente);
        try {
            JSONObject jSONObject_cliente_a_modificar = new JSONObject(json_cliente_seleccionado);
            cliente_a_modificar = cliente_a_modificar.fromJson(jSONObject_cliente_a_modificar);

        } catch (JSONException e) {
            Utilidades.MostrarMensajeWarning("Hubo un error al intentar recuperar los datos del cliente");
        }

        return cliente_a_modificar;
    }

    private ArrayList<Factura> ObtenerListaFacturas(String dni) {
        //con este metodo, obtenemos la lista de facturas que corresponde a cada cliente seleccionado
        //la idea es, a partir del json, obtener la lista de facturas

        ArrayList<Factura> facturas = new ArrayList<>();
        String json_cliente_seleccionado = ObtenerDatosCliente(dni);

        try {
            JSONObject jSONObject_cliente = new JSONObject(json_cliente_seleccionado);
            JSONArray jSONArray_facturas = jSONObject_cliente.getJSONArray("Facturas");
            for (int i = 0; i < jSONArray_facturas.length(); i++) {
                JSONObject jsonObject_factura = jSONArray_facturas.getJSONObject(i);
                Factura factura = new Factura();
                factura = factura.fromJson(jsonObject_factura);
                facturas.add(factura);
            }

        } catch (JSONException e) {
            Utilidades.MostrarMensajeWarning("Hubo un error al intentar recuperar los datos del cliente");
        }
        return facturas;

    }

    private String ObtenerDatosCliente(String dni_cliente) {
        //metodo que, a partir de un dni seleccionado, localiza en el arraylist en memoria que es la fuente de datos de la tabla, el cliente
        //en cuestion, y devuelve el Json Correspondiente a dicho cliente, para asi poder manipularlo a futuro en la ventna de modificar.
        String rta = "";
        boolean encontrado = false;
        Cliente cliente_encontrado = null;
        for (int i = 0; i < clientes.size() && !encontrado; i++) {
            if (clientes.get(i).getDni().equals(dni_cliente)) {
                encontrado = true;
                cliente_encontrado = clientes.get(i);
            }
        }
        if (encontrado) {
            try {
                JSONObject jSONObject_cliente = cliente_encontrado.toJsonObj();
                rta = jSONObject_cliente.toString();
            } catch (JSONException e) {
                rta = e.getMessage();
            }

        }

        return rta;
    }

    private void FiltrarTabla_RutinaPendiente() {
        ArrayList <Cliente> clientes_filtrados = new ArrayList<>();
        for(Cliente cliente : clientes){
            if(cliente.isSolicito_rutina()){
                clientes_filtrados.add(cliente);
            }
        }
        CargarTabla(clientes_filtrados);
    }

    private void MostrarRutinaSegunCliente() {
        String dni_cliente = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {
            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            Cliente cliente = ObtenerCliente(dni_cliente);
            if (cliente.getRutina() != null) {
                taDescripcionRutina.setText(cliente.getRutina().toString());
            }

        }

    }

    private void AbrirDialogAgregarRutina() {
        String dni_cliente = "";
        String json_cliente_seleccionado = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {

            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            json_cliente_seleccionado = ObtenerDatosCliente(dni_cliente);

            DialogAgregarRutina dialog_agregar_rutina = new DialogAgregarRutina(new Escritorio(usuario, gimnasio), true, dni_cliente, json_cliente_seleccionado, gimnasio);
            dialog_agregar_rutina.setLocationRelativeTo(this);
            dialog_agregar_rutina.setVisible(true);

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
        }

    }

    private void SolicitarRutina() {
        String dni_cliente = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {
            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            try {
                gimnasio.pedidoDeRutina(dni_cliente);
                Utilidades.MostrarMensajeInformativo("Se solicito la rutina exitosamente");
                ActualizarTabla();
            } catch (RutinaYaPedida e) {
                Utilidades.MostrarMensajeWarning(e.getMessage());
            } catch (NoEncontradoException e) {
                Utilidades.MostrarMensajeWarning(e.getMessage());
            }

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");

        }

    }
}
