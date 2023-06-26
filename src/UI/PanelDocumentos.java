/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import UI.UtilidadesUI.CentradorDeColumna;
import excepciones.ClienteDeudorException;
import excepciones.NoEncontradoException;
import java.awt.Image;
import java.time.LocalDate;
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
public class PanelDocumentos extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
    private Gimnasio gimnasio;
    private Usuario usuario;
    private TableRowSorter<DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla

    //Prueba para poder actualizar clientes
    private ArrayList<Cliente> clientes; //arraylist de clientes que se carga con el metodo obtenerclientes

    private ArrayList<Factura> facturas_cliente_seleccionado;

    public PanelDocumentos(Usuario usuario, Gimnasio gimnasio) {
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



    private void CargarTabla(ArrayList<Cliente> clientes){
     
        DefaultTableModel model = (DefaultTableModel) tablaObjetos.getModel();
        model.setRowCount(0);

        JTable jTableClientes = tablaObjetos;

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Dni");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Domicilio");
        modeloTabla.addColumn("Deudor");

        for (Cliente cliente : clientes) {
            Object[] fila = {cliente.getNombre(), cliente.getDni(), cliente.getTelefono(), cliente.getDomicilio(), cliente.getDebe()};
            modeloTabla.addRow(fila);

        }

        jTableClientes.setModel(modeloTabla);

        TableColumnModel columnModel = jTableClientes.getColumnModel();
        int columnIndex = 0;
        TableColumn column = columnModel.getColumn(columnIndex);

        int preferredWidth = 100;
        column.setPreferredWidth(preferredWidth);

        //INICIO PRUEBA DE CENTRALIZAR TODAS LAS COLUMNAS
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn columna_a_centrar = columnModel.getColumn(i);
            columna_a_centrar.setCellRenderer(new CentradorDeColumna());

        }
        //FIN PRUEBA DE CENTRALIZAR TODAS LAS COLUMNAS

        //inicio para filtrar
        jTableClientes.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableClientes.setRowSorter(sorter);
        //fin para filtrar

        tablaObjetos.setDefaultEditor(Object.class, null); //haciendo que tabla no sea editable 

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
    //Fin metodos para lograr filtro de busqueda


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public PanelDocumentos() {
    }
// @SuppressWarnings("unchecked"); //esto esta comentaado por mi
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObjetos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescripcionFactura = new javax.swing.JTextArea();
        tfFiltroBusqueda = new javax.swing.JTextField();
        cbFiltrar = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        cbNombreFactura = new javax.swing.JComboBox<>();

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

        taDescripcionFactura.setEditable(false);
        taDescripcionFactura.setColumns(20);
        taDescripcionFactura.setRows(5);
        jScrollPane2.setViewportView(taDescripcionFactura);

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

        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Inactivos", "Baneados" }));
        cbFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltrarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Imprimir");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        cbNombreFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNombreFacturaActionPerformed(evt);
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
                        .addGap(94, 94, 94)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(cbNombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNombreFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(70, Short.MAX_VALUE))))
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
                else if(seleccion.equals("Inactivos")){
                    FiltrarTabla(Eestado.INACTIVO);
                }else if(seleccion.equals("Baneados")){
                    FiltrarTabla(Eestado.BANEADO);
                }
                
        }
    }//GEN-LAST:event_cbFiltrarActionPerformed

    private void tfFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroBusquedaActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:
        cbNombreFactura.removeAllItems(); //con esto limpiamos las opciones del combobox
        taDescripcionFactura.setText("");
        CargarFacturasSegunCliente();
    }//GEN-LAST:event_tablaObjetosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        Utilidades.MostrarMensajeWarning("Error. Verifique conexión a impresora.");
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void cbNombreFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNombreFacturaActionPerformed
        // TODO add your handling code here:
        int indice_seleccionado = cbNombreFactura.getSelectedIndex();
        if (indice_seleccionado != -1  && facturas_cliente_seleccionado!=null) {
               
               taDescripcionFactura.setText(facturas_cliente_seleccionado.get(indice_seleccionado).toString());
        }
    }//GEN-LAST:event_cbNombreFacturaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JComboBox<String> cbNombreFactura;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taDescripcionFactura;
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
        


    private void CargarFacturasSegunCliente() {
        //con este metodo logramos que por cada cliente seleccionado, se listen sus correspondientes facturas en el combobox correspondiente

       String dni_cliente = "";
      Cliente cliente = null;
       int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {
            dni_cliente = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
           cliente = ObtenerCliente(dni_cliente); //a partir de un dni, obtengo un cliente, gracias al json
           //pero lo que necesito, mas que el cliente, es su lista de facturas asique llamo al metodo obtenerlistafacturas
            facturas_cliente_seleccionado = ObtenerListaFacturas(dni_cliente);
           CargarComboBox(facturas_cliente_seleccionado);

         }else{
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
        }
           

    }
    
    private void CargarComboBox(ArrayList <Factura> facturas){
            if(facturas!=null){
             for (Factura factura : facturas) {
            cbNombreFactura.addItem(factura.getMes() + " " + factura.getAnio());
                    }
            }

    }
        private Cliente ObtenerCliente(String dni_cliente){
        //A partir del json del cliente, nos servimos del from Json y ya tenemos a nuestro cliente
        Cliente cliente_a_modificar  = new Cliente();
        String json_cliente_seleccionado = ObtenerDatosCliente(dni_cliente);
        try{
            JSONObject jSONObject_cliente_a_modificar = new JSONObject(json_cliente_seleccionado);
            cliente_a_modificar = cliente_a_modificar.fromJson(jSONObject_cliente_a_modificar);
           // System.out.println("CLIENTE A MODIFICAR(cliente creado a partir del json):\n" + cliente_a_modificar.toString());
            
        }catch(JSONException e){
            Utilidades.MostrarMensajeWarning("Hubo un error al intentar recuperar los datos del cliente");
        }
        
        return cliente_a_modificar;
    }
        
        private ArrayList<Factura> ObtenerListaFacturas(String dni){
            //con este metodo, obtenemos la lista de facturas que corresponde a cada cliente seleccionado
            //la idea es, a partir del json, obtener la lista de facturas
            
            ArrayList <Factura> facturas = new ArrayList<>();
            String json_cliente_seleccionado = ObtenerDatosCliente(dni);
            
             try{
            JSONObject jSONObject_cliente = new JSONObject(json_cliente_seleccionado);
            JSONArray jSONArray_facturas = jSONObject_cliente.getJSONArray("Facturas");
            for(int i=0; i<jSONArray_facturas.length();i++){
                JSONObject jsonObject_factura = jSONArray_facturas.getJSONObject(i);
                Factura factura = new Factura();
                factura = factura.fromJson(jsonObject_factura);
                facturas.add(factura);
            }
            
        }catch(JSONException e){
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
    
    
}
