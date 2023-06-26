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
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
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
public class PanelClientes extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
      private Gimnasio gimnasio;
      private Usuario usuario;
     private TableRowSorter <DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla
     
     //Prueba para poder actualizar clientes
     private ArrayList<Cliente> clientes; //arraylist de clientes que se carga con el metodo obtenerclientes
     
     
    public PanelClientes(Usuario usuario, Gimnasio gimnasio) {
        initComponents();

        this.gimnasio = gimnasio;
        this.usuario = usuario;

        clientes = ObtenerClientes(); //desde aca el arraylist es cargado
        CargarTabla(clientes);
        btnCobrar.putClientProperty("JButton.buttonType", "roundRect");
        btnAgregarComentario.putClientProperty("JButton.buttonType", "roundRect");

        btnAgregar.putClientProperty("JButton.buttonType", "roundRect");
        btnModificar.putClientProperty("JButton.buttonType", "roundRect");
        btnCambiarEstado.putClientProperty("JButton.buttonType", "roundRect");

        SetearComponentes();
        
    }
    

    
    private void SetearComponentes(){
        //con este metodo seteamos componentes..
        cbFiltrar.setSelectedIndex(0); //que esten listados los activos
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
        String json_clientes= gimnasio.CompartirDatosClientes();
        ArrayList <Cliente> clientes = new ArrayList<>();
        try {
            JSONArray jsonArray_clientes = new JSONArray(json_clientes);
            for(int i=0; i<jsonArray_clientes.length();i++){
                Cliente cliente = new Cliente();
                cliente = cliente.fromJson(jsonArray_clientes.getJSONObject(i));
                clientes.add(cliente);
                
            }
            
        }catch(JSONException e){
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

    public PanelClientes() {
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
        btnAgregarComentario = new javax.swing.JButton();
        tfFiltroBusqueda = new javax.swing.JTextField();
        btnCobrar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        cbFiltrar = new javax.swing.JComboBox<>();

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

        btnAgregar.setText("Nuevo");
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

        btnAgregarComentario.setText("+");
        btnAgregarComentario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarComentarioActionPerformed(evt);
            }
        });

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

        btnCobrar.setText("Cobrar");
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCambiarEstado.setText("Dar de Baja");
        btnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoActionPerformed(evt);
            }
        });

        cbFiltrar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activos", "Inactivos", "Baneados" }));
        cbFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltrarActionPerformed(evt);
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
                        .addGap(96, 96, 96)
                        .addComponent(lblInfoAdicional)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarComentario))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(btnAgregar)
                .addGap(24, 24, 24)
                .addComponent(btnModificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCambiarEstado)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfFiltroBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblInfoAdicional)
                    .addComponent(btnAgregarComentario)
                    .addComponent(cbFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCobrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnModificar)
                    .addComponent(btnCambiarEstado))
                .addGap(40, 40, 40))
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

             AbrirDialogAgregarCliente();
             ActualizarTabla();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void tfFiltroBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaKeyReleased
        // TODO add your handling code here:
                filtrar();
    }//GEN-LAST:event_tfFiltroBusquedaKeyReleased

    private void tfFiltroBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroBusquedaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        ModificarCliente();

         
        
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        // TODO add your handling code here:
        DarDeBajaAlta();
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:
    
        MostrarInformacionAdicional();
    }//GEN-LAST:event_tablaObjetosMouseClicked

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        // TODO add your handling code here:
        //gimnasio.Cobrar();
        CobrarCliente();
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void btnAgregarComentarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarComentarioActionPerformed
        // TODO add your handling code here:
        AbrirDialogComentarioApercibimiento();
        ActualizarTabla();
        cbFiltrar.setSelectedIndex(0);
        taInfoAdicional.setText("");
        tablaObjetos.clearSelection();
    }//GEN-LAST:event_btnAgregarComentarioActionPerformed

    private void cbFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltrarActionPerformed
        // TODO add your handling code here:

        if(cbFiltrar.getSelectedIndex()>=0){
            String seleccion = (String) cbFiltrar.getSelectedItem();
            if (seleccion.equals("Activos")) {
                FiltrarTabla(Eestado.ACTIVO);
                btnCambiarEstado.setText("Dar de Baja");
            }
            else if(seleccion.equals("Inactivos")){
                FiltrarTabla(Eestado.INACTIVO);
                btnCambiarEstado.setText("Dar de Alta");
            }else if(seleccion.equals("Baneados")){
                FiltrarTabla(Eestado.BANEADO);
                btnCambiarEstado.setText("Desbanear");
            }

        }
    }//GEN-LAST:event_cbFiltrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarComentario;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbFiltrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblInfoAdicional;
    private javax.swing.JTextArea taInfoAdicional;
    private javax.swing.JTable tablaObjetos;
    private javax.swing.JTextField tfFiltroBusqueda;
    // End of variables declaration//GEN-END:variables

    private void AbrirDialogAgregarCliente() {
        //metodo que abre el dialog para agregarClientes
                    DialogAgregarCliente dialog_agregar_cliente = new DialogAgregarCliente(new Escritorio(usuario,gimnasio), true,gimnasio);
                    dialog_agregar_cliente.setLocationRelativeTo(this);
                    dialog_agregar_cliente.setVisible(true);
                    dialog_agregar_cliente.setSize(730,624);
                
    }
    
         
        private void FiltrarTabla_Deudores(){
            //metodo que a partir de lo que seleccione el cliente en combobox, filtra los deudores
        
        ArrayList <Cliente> clientes_filtrados = new ArrayList<>();
        for(Cliente cliente : clientes){
            if(cliente.isDebe()){
                clientes_filtrados.add(cliente);
            }
            
        }
        CargarTabla(clientes_filtrados);
    }
    
    
    /*Fin Metodos que filtran tabla segun criterios (nombre,dni,telefono,deudores) */

    private void MostrarInformacionAdicional() {
      //metodo que muestra informacion adicional en el text area correspondiente
       String texto_adicional = "" ;
       String dni_cliente = "";
       int fila_seleccionada = tablaObjetos.getSelectedRow();
       try{
             if (fila_seleccionada >= 0) {
            dni_cliente = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
            texto_adicional = gimnasio.InformacionAdicionalCliente(dni_cliente);
            taInfoAdicional.setText(texto_adicional);
         }
           
       }catch(NoEncontradoException e){
           Utilidades.MostrarMensajeWarning(e.getMessage());
       }

    }

    private void AbrirDialogComentarioApercibimiento() {
        String dni_cliente = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {
            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            DialogAgregarComentario dialog_agregar_comentario = new DialogAgregarComentario(new Escritorio(usuario, gimnasio), true, dni_cliente,gimnasio);
            dialog_agregar_comentario.setLocationRelativeTo(this);
            dialog_agregar_comentario.setVisible(true);
            
        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
        }



    }

    private void ActualizarTabla() {
        //metodo que actualiza los datos de la tabla
        clientes = ObtenerClientes(); //desde aca el arraylist es cargado
        CargarTabla(clientes);
    }

    private void AbrirDialogModificarCliente(String json_cliente_seleccionado) {
                    DialogModificarCliente dialog_modificar_cliente = new DialogModificarCliente (new Escritorio(usuario,gimnasio), true,gimnasio,json_cliente_seleccionado);
                    dialog_modificar_cliente.setVisible(true);
                    dialog_modificar_cliente.setSize(730,624);
    }

    private String ObtenerDatosCliente(String dni_cliente) {
        //metodo que, a partir de un dni seleccionado, localiza en el arraylist en memoria que es la fuente de datos de la tabla, el cliente
        //en cuestion, y devuelve el Json Correspondiente a dicho cliente, para asi poder manipularlo a futuro en la ventna de modificar.
        String rta = "";
        boolean encontrado = false;
        Cliente cliente_encontrado = null;
        for(int i=0; i<clientes.size() && !encontrado;i++){
            if(clientes.get(i).getDni().equals(dni_cliente)){
                encontrado = true;
                cliente_encontrado = clientes.get(i);
            }
        }
        if(encontrado){
            try{
                JSONObject jSONObject_cliente = cliente_encontrado.toJsonObj();
                rta = jSONObject_cliente.toString();
            }catch(JSONException e){
                rta = e.getMessage();
            }
            
        }
        
        
        return rta;
    }

    private void CobrarCliente() {
        //gimnasio.cobrar(TOOL_TIP_TEXT_KEY);
        String dni_cliente = "";
        
       int fila_seleccionada = tablaObjetos.getSelectedRow();
       try{
             if (fila_seleccionada >= 0) {
            dni_cliente = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
            gimnasio.cobrar(dni_cliente);
            Utilidades.MostrarMensajeInformativo("El cliente fue cobrado exitosamente!");
            ActualizarTabla();
         }
             else{
                 Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
             }
           
       }catch(NoEncontradoException e){
           Utilidades.MostrarMensajeWarning(e.getMessage());
       }catch(ClienteDeudorException e){
           Utilidades.MostrarMensajeWarning(e.getMessage());
       }
       
    }

    private void DarDeBajaAlta() {
        //metodo que, segun el caso permite dar de baja o alta
        String dni_cliente = "";
        String texto_pregunta = "";
        boolean confirmacion = false;

        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {

            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            try {
                //antes de cambiar el estado, vamos a validar...
                if (gimnasio.ConsultarClienteActivo(dni_cliente)) {
                    //Seguro que desea dar de baja?
                    texto_pregunta = "¿Dar de baja?";
                } else {
                    //seguro que desea dar de alta?
                    texto_pregunta = "¿Dar de alta?";
                }
                confirmacion = ConfirmarOperacion(texto_pregunta);
                if (confirmacion) { //si confirma.. procedemos
                    gimnasio.cambiarEstado(dni_cliente);
                    // el cliente esta de baja o de alta?
                    if (gimnasio.ConsultarClienteActivo(dni_cliente)) {
                        Utilidades.MostrarMensajeInformativo("El cliente fue dado de alta exitosamente");
                    } else {
                        Utilidades.MostrarMensajeInformativo("El cliente fue dado de baja exitosamente");
                    }
                    ActualizarTabla();
                    cbFiltrar.setSelectedIndex(0); //
                }

            } catch (NoEncontradoException e) {
                Utilidades.MostrarMensajeWarning(e.getMessage());
            }

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
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

    private void FiltrarTabla(Eestado estado) {
        ArrayList<Cliente> clientes_filtrados = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getEstado() == estado) {
                clientes_filtrados.add(cliente);
            }
        }
        CargarTabla(clientes_filtrados);
    }

    private void ModificarCliente() {
        String dni_cliente = "";
        String json_cliente_seleccionado = "";
        int fila_seleccionada = tablaObjetos.getSelectedRow();
        if (fila_seleccionada >= 0) {

            dni_cliente = (String) tablaObjetos.getValueAt(fila_seleccionada, 1);
            json_cliente_seleccionado = ObtenerDatosCliente(dni_cliente);
            AbrirDialogModificarCliente(json_cliente_seleccionado);
            //ActualizarTabla();

        } else {
            Utilidades.MostrarMensajeWarning("Debe seleccionar un cliente!");
        }

    }


    
    
}
