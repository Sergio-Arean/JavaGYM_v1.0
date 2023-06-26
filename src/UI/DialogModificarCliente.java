/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;
import UI.UtilidadesUI.CentradorDeColumna;
import excepciones.ExistenteException;
import excepciones.NoEncontradoException;

import java.awt.Image;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.ActivYrutina.Actividad;
import model.Enum.*;
import model.Otros.Gimnasio;
import model.Persona.Cliente;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author sergi
 */
public class DialogModificarCliente extends javax.swing.JDialog {

    /**
     * Creates new form DialogAgregarCliente
     */
    private ArrayList<Actividad> actividades;
    private Gimnasio gimnasio;
    private TableRowSorter <DefaultTableModel> sorter; //esto permite realizar el filtrado en la tabla
    private ArrayList<Actividad> actividades_a_calcular; 
    private String json_cliente_a_modificar;
    private TreeSet<Actividad> actividades_anteriores;
    
    public DialogModificarCliente(java.awt.Frame parent, boolean modal,Gimnasio gimnasio,String json_cliente_a_modificar) {
        super(parent, modal);
        initComponents();
        CargarComponentes();

        
        this.json_cliente_a_modificar = json_cliente_a_modificar;
        this.gimnasio = gimnasio;
        actividades = ObtenerActividades(); //desde aca el arraylist es cargado
        CargarTabla(actividades);
        
        actividades_a_calcular = new ArrayList<>();
        limpiarJList();
        InicializarCampos(); 
        
    }
    private void InicializarCampos(){
        
        Cliente cliente_a_modificar = ObtenerCliente();
        double precio_estimado =0;
        
        //metodo para mostrar campos modificables en pantalla
        tfDni.setText(cliente_a_modificar.getDni());//inmodificable
        tfNombre.setText(cliente_a_modificar.getNombre());
        
        cbGenero.setSelectedItem(cliente_a_modificar.getGenero());
        
        tfDomicilio.setText(cliente_a_modificar.getDomicilio());
        
        //pasamos de localdate a date?
        LocalDate localdate_fecha_nacimiento = cliente_a_modificar.getFecha_nacimiento();
        ZoneId zoneId = ZoneId.systemDefault();
        Date date_fecha_nacimiento = Date.from(localdate_fecha_nacimiento.atStartOfDay(zoneId).toInstant());
        dcFechaNacimiento.setDate(date_fecha_nacimiento);
        
        tfTelefono.setText(cliente_a_modificar.getTelefono());
        tfTelEmergencia.setText(cliente_a_modificar.getContacto_emergencia());
        tfEmail.setText(cliente_a_modificar.getEmail());
        tfObraSocial.setText(cliente_a_modificar.getObra_social());
        
        cbGrupoSanguineo.setSelectedItem(cliente_a_modificar.getGrupo_sanguineo());
        
        //fecha de inscripcion
        LocalDate localdate_fecha_inscripcion = cliente_a_modificar.getFecha_de_inscripcion();
        ZoneId zoneIdd = ZoneId.systemDefault();
        Date date_fecha_inscripcion = Date.from(localdate_fecha_inscripcion.atStartOfDay(zoneIdd).toInstant());
        dcFechaInscripcion.setDate(date_fecha_inscripcion);
        
        

        
        
        JSONObject jSONObject_cliente;
        actividades_anteriores = new TreeSet<>();
        
        try{
            jSONObject_cliente = new JSONObject(json_cliente_a_modificar);
            JSONArray jSONArray_actividades = jSONObject_cliente.getJSONArray("Actividades");
            for(int i=0; i<jSONArray_actividades.length(); i++){
                JSONObject jSONObject_actividad = new JSONObject();
                jSONObject_actividad = jSONArray_actividades.getJSONObject(i);
                
                Actividad actividad = new Actividad();
                actividad = actividad.fromJson(jSONObject_actividad);
                actividades_a_calcular.add(actividad);
                actividades_anteriores.add(actividad);
            }

        }catch(JSONException e){
            Utilidades.MostrarMensajeWarning("Hubo un problema al recuperar datos");
        }
        
        //una vez cargado el arraylist de las actividades a calcular.. los agrego en la jlist para q el cliente pueda elegir..modifcar
        for(Actividad actividad : actividades_a_calcular){
            MostrarActividadEnLista(actividad);
        }
        
         precio_estimado = gimnasio.CalcularPrecio(actividades_a_calcular); // se calcula precio
         tfPrecio.setText(String.valueOf(precio_estimado)); //se muestra en pantalla
       
    }
    private Cliente ObtenerCliente(){
        //A partir del json del cliente, nos servimos del from Json y ya tenemos a nuestro cliente
        Cliente cliente_a_modificar = new Cliente();
        try {
            JSONObject jSONObject_cliente_a_modificar = new JSONObject(json_cliente_a_modificar);
            cliente_a_modificar = cliente_a_modificar.fromJson(jSONObject_cliente_a_modificar);

        } catch (JSONException e) {
            Utilidades.MostrarMensajeWarning("Hubo un error al intentar recuperar los datos del cliente");
        }

        return cliente_a_modificar;
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
    


    private void CargarTabla(ArrayList<Actividad> actividades) {

        DefaultTableModel model = (DefaultTableModel) tablaObjetos.getModel();
        model.setRowCount(0); // Limpia todas las filas existentes

        JTable jTableClientes = tablaObjetos;

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Actividad");
        modeloTabla.addColumn("Horario");
        modeloTabla.addColumn("Instructor");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Disponible");
        modeloTabla.addColumn("Cupos");

        modeloTabla.addColumn("Dias");//columna oculta

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

        TableColumnModel columnModel = jTableClientes.getColumnModel();

        int columnIndex = 0;
        TableColumn column = columnModel.getColumn(columnIndex);

        int preferredWidth = 100;
        column.setPreferredWidth(preferredWidth);

        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            TableColumn columna_a_centrar = columnModel.getColumn(i);
            columna_a_centrar.setCellRenderer(new CentradorDeColumna());

        }

        jTableClientes.setAutoCreateRowSorter(true);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableClientes.setRowSorter(sorter);

        InvisibilizarColumna(4);
        InvisibilizarColumna(6);

        AjustarAnchoColumna(0, 180);
        AjustarAnchoColumna(1, 180);
        AjustarAnchoColumna(2, 160);

        tablaObjetos.setDefaultEditor(Object.class, null); //haciendo que tabla no sea editable

    }
    private void AjustarAnchoColumna(int columna,int ancho){
              //establece el ancho de una columna
     
               TableColumnModel columnModel = tablaObjetos.getColumnModel();
               
               int columnIndex = columna;
               TableColumn column = columnModel.getColumn(columnIndex);

               int preferredWidth = ancho; 
               column.setPreferredWidth(preferredWidth);
        

    }
    private void InvisibilizarColumna(int columna){
        //metodo que invisibiliza la columna indicada por parametro
        tablaObjetos.getColumnModel().getColumn(columna).setMinWidth(0);
        tablaObjetos.getColumnModel().getColumn(columna).setMaxWidth(0);
        tablaObjetos.getColumnModel().getColumn(columna).setWidth(0);
        
    }

    
    private void CargarComponentes(){
        //este metodo carga los distintos componentes que forman parte del dialog
        CargarComboBox();
        BotonesCirculares();
        
    }
    private void CargarComboBox(){
        //este metodo carga los combobox
        
        //Grupo Sanguineo
          for (EGrupoSanguineo grupo_sanguineo : EGrupoSanguineo.values()) {
            cbGrupoSanguineo.addItem(grupo_sanguineo.name());
        }
          
                          for (EGenero genero : EGenero.values()) {
            cbGenero.addItem(genero.name());
        }
    }
    private void BotonesCirculares(){
        //setear botones con forma circular
        btnAgregarActividad.putClientProperty( "JButton.buttonType", "roundRect" );
        btnQuitarActividad.putClientProperty( "JButton.buttonType", "roundRect" );
        btnModificar.putClientProperty( "JButton.buttonType", "roundRect" );
        btnLimpiar.putClientProperty( "JButton.buttonType", "roundRect" );
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        lblDomicilio = new javax.swing.JLabel();
        tfDomicilio = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        tfTelefono = new javax.swing.JTextField();
        lblTelEmergencia = new javax.swing.JLabel();
        tfTelEmergencia = new javax.swing.JTextField();
        lblFechaNacimiento = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        tfNombre = new javax.swing.JTextField();
        lblDni = new javax.swing.JLabel();
        tfDni = new javax.swing.JTextField();
        lblObraSocial = new javax.swing.JLabel();
        tfObraSocial = new javax.swing.JTextField();
        lblGrupoSanguineo = new javax.swing.JLabel();
        checkAltaMedica = new javax.swing.JCheckBox();
        cbGrupoSanguineo = new javax.swing.JComboBox<>();
        lblFechaInscripcion = new javax.swing.JLabel();
        dcFechaInscripcion = new com.toedter.calendar.JDateChooser();
        dcFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaObjetos = new javax.swing.JTable();
        tfFiltrarActividad = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        taDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        listActividades = new javax.swing.JList<>();
        btnModificar = new javax.swing.JButton();
        checkSolicitarRutina = new javax.swing.JCheckBox();
        btnAgregarActividad = new javax.swing.JButton();
        btnQuitarActividad = new javax.swing.JButton();
        lblPrecio = new javax.swing.JLabel();
        lblFechaInscripcion1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        tfPrecio = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        cbGenero = new javax.swing.JComboBox<>();
        lblNombre1 = new javax.swing.JLabel();
        tfEmail = new javax.swing.JTextField();
        lblTelEmergencia1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Cliente");

        background.setPreferredSize(new java.awt.Dimension(720, 624));

        lblDomicilio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDomicilio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDomicilio.setText("Domicilio");
        lblDomicilio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfDomicilio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelefono.setText("Teléfono");
        lblTelefono.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTelEmergencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelEmergencia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelEmergencia.setText("Tel. Emergencia");
        lblTelEmergencia.setToolTipText("");
        lblTelEmergencia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfTelEmergencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaNacimiento.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaNacimiento.setText("Fecha de Nacimiento");
        lblFechaNacimiento.setToolTipText("");
        lblFechaNacimiento.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre");
        lblNombre.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDni.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDni.setText("DNI");
        lblDni.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfDni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfDni.setEnabled(false);

        lblObraSocial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial.setText("Obra Social");
        lblObraSocial.setToolTipText("");
        lblObraSocial.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfObraSocial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblGrupoSanguineo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblGrupoSanguineo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGrupoSanguineo.setText("Grupo Sanguíneo");
        lblGrupoSanguineo.setToolTipText("");
        lblGrupoSanguineo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        checkAltaMedica.setText("Alta Médica");

        cbGrupoSanguineo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFechaInscripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaInscripcion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaInscripcion.setText("Fecha Inscripción");
        lblFechaInscripcion.setToolTipText("");

        dcFechaInscripcion.setEnabled(false);
        dcFechaInscripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        dcFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

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
        ));
        tablaObjetos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaObjetos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaObjetos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaObjetosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaObjetos);

        tfFiltrarActividad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfFiltrarActividadKeyReleased(evt);
            }
        });

        taDescripcion.setColumns(20);
        taDescripcion.setRows(5);
        taDescripcion.setEnabled(false);
        jScrollPane3.setViewportView(taDescripcion);

        jScrollPane2.setViewportView(listActividades);

        btnModificar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        checkSolicitarRutina.setText("Solicitar Rutina");

        btnAgregarActividad.setText("+");
        btnAgregarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActividadActionPerformed(evt);
            }
        });

        btnQuitarActividad.setText("-");
        btnQuitarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActividadActionPerformed(evt);
            }
        });

        lblPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio.setText("Precio");

        lblFechaInscripcion1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFechaInscripcion1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaInscripcion1.setText("Buscar");
        lblFechaInscripcion1.setToolTipText("");

        tfPrecio.setEnabled(false);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        cbGenero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre1.setText("Género");
        lblNombre1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tfEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblTelEmergencia1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelEmergencia1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTelEmergencia1.setText("Email");
        lblTelEmergencia1.setToolTipText("");
        lblTelEmergencia1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(lblFechaInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lblFechaInscripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(tfFiltrarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dcFechaInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDni, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                                    .addComponent(tfDni)))
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblObraSocial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblGrupoSanguineo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(backgroundLayout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(lblFechaNacimiento))
                                        .addComponent(lblDomicilio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNombre1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblTelEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTelEmergencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTelEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGrupoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(lblPrecio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkSolicitarRutina, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLimpiar)
                                        .addGap(0, 7, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(backgroundLayout.createSequentialGroup()
                                        .addComponent(btnAgregarActividad)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(checkAltaMedica, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnQuitarActividad)))
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(37, 37, 37))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(tfDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDomicilio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(dcFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(tfTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTelEmergencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelEmergencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelEmergencia1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObraSocial)
                    .addComponent(checkAltaMedica))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbGrupoSanguineo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGrupoSanguineo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInscripcion)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(btnAgregarActividad))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(dcFechaInscripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfFiltrarActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaInscripcion1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnQuitarActividad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPrecio)
                        .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(checkSolicitarRutina)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:

        if (ValidarCamposCompletos()) {
            ModificarCliente();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tfFiltrarActividadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfFiltrarActividadKeyReleased
        // TODO add your handling code here:
        filtrar();
    }//GEN-LAST:event_tfFiltrarActividadKeyReleased

    private void btnAgregarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActividadActionPerformed
        // TODO add your handling code here:
        CalcularPrecio();
    }//GEN-LAST:event_btnAgregarActividadActionPerformed

    private void tablaObjetosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaObjetosMouseClicked
        // TODO add your handling code here:
        MostrarInformacionAdicional();
    }//GEN-LAST:event_tablaObjetosMouseClicked

    private void btnQuitarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActividadActionPerformed
        // TODO add your handling code here:
        QuitarActividad();
    }//GEN-LAST:event_btnQuitarActividadActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        LimpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnAgregarActividad;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnQuitarActividad;
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JComboBox<String> cbGrupoSanguineo;
    private javax.swing.JCheckBox checkAltaMedica;
    private javax.swing.JCheckBox checkSolicitarRutina;
    private com.toedter.calendar.JDateChooser dcFechaInscripcion;
    private com.toedter.calendar.JDateChooser dcFechaNacimiento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblFechaInscripcion;
    private javax.swing.JLabel lblFechaInscripcion1;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblGrupoSanguineo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblObraSocial;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblTelEmergencia;
    private javax.swing.JLabel lblTelEmergencia1;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JList<String> listActividades;
    private javax.swing.JTextArea taDescripcion;
    private javax.swing.JTable tablaObjetos;
    private javax.swing.JTextField tfDni;
    private javax.swing.JTextField tfDomicilio;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFiltrarActividad;
    private javax.swing.JTextField tfNombre;
    private javax.swing.JTextField tfObraSocial;
    private javax.swing.JTextField tfPrecio;
    private javax.swing.JTextField tfTelEmergencia;
    private javax.swing.JTextField tfTelefono;
    // End of variables declaration//GEN-END:variables

  
            
            public  Icon icono (String path, int width,int height){
             Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()
                     .getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
             
             return img;
            }

    private void filtrar() {
         sorter.setRowFilter(RowFilter.regexFilter("(?i)"+tfFiltrarActividad.getText()));
    }

    private void CalcularPrecio() {
        /*este metodo podria llamarse simplemente agregar actividad, pero la idea es que 
        por cada actividad que se va agregando con el boton + , se vaya haciendo un calculo
        estimado del costo a pagar por mes. El calculo se hace a traves de una lista
        que , solo cuando el cliente haga click en boton confirmar sera una lista de actividades
        como tal... en esta instancia son listas que pueden ser transitorias segun el precio que se va
        calculando y mostrando en pantalla*/
         double precio_estimado=0;
         int fila_seleccionada = tablaObjetos.getSelectedRow();
         if (fila_seleccionada >= 0) {
             
             //Obtengo la actividad seleccionada
             EtipoActividad nombre_actividad =   (EtipoActividad) tablaObjetos.getValueAt(fila_seleccionada, 0);
             String horario = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
             ArrayList<EdiaSemana> lista_dias = (ArrayList<EdiaSemana>) tablaObjetos.getValueAt(fila_seleccionada, 6);
             Actividad actividad_a_buscar = new Actividad(nombre_actividad,horario,lista_dias,"",0,0,false,"",0);
             
             try{
                    Actividad actividad_encontrada = gimnasio.buscarActividad(actividad_a_buscar);  //busco actividad
                    if(actividad_encontrada.isDisponible() ){
                        if(!ActividadYaElegida(actividad_encontrada)){//si no fue elegida.. se agrega
                            actividades_a_calcular.add(actividad_encontrada); // la agrego a lista transitoria
                            
                         
                            precio_estimado = gimnasio.CalcularPrecio(actividades_a_calcular); // se calcula precio
                            tfPrecio.setText(String.valueOf(precio_estimado)); //se muestra en pantalla
                            MostrarActividadEnLista(actividad_encontrada);
                            //Utilidades.MostrarMensajeInformativo(actividades_a_calcular.toString()); //DE PRUEBA
                            
                        }
                        else{
                            Utilidades.MostrarMensajeWarning("La actividad seleccionada ya fue agregada!");
                        }
                    }
                    else{
                        Utilidades.MostrarMensajeWarning("La actividad seleccionada no tiene cupo disponible");
                    }

             }catch(NoEncontradoException e){
                 Utilidades.MostrarMensajeWarning(e.getMessage());
             } 
                 
         }
         else{
             Utilidades.MostrarMensajeWarning("Debe seleccionar una actividad!");
         }
    }
    
    private boolean ActividadYaElegida(Actividad actividad_a_agregar){
        //metodo permite que  la actividad elegida no se repita
        //xq un arraylist? x facilidad al manejar pocos datos, no haremos operaciones de busqueda
        //a su vez, facilita la gestion del JList al poder contar con un indice de cada actividad
        boolean rta = false;
        for(Actividad actividad : actividades_a_calcular){
            if(actividad_a_agregar.equals(actividad)){
                rta = true;
            }
        }
        return rta;
    }

    private void MostrarActividadEnLista(Actividad actividad) {
        //agrega como item en la lista la actividad seleccionada
        String descripcion_actividad = actividad.getNombre().name() + " - " + actividad.getHorario() + " - " + actividad.getNombre_instructor() + " - " + String.valueOf(actividad.getPrecio_mensual());
        ListModel<String> model = listActividades.getModel();
        if (model instanceof DefaultListModel) {
            DefaultListModel<String> defaultModel = (DefaultListModel<String>) model;
            defaultModel.addElement(descripcion_actividad);
        }
        else{
            Utilidades.MostrarMensajeInformativo("La actividad no puede agregarse a la lista");
        }
        
    }
    
    public DefaultListModel limpiarJList(){

DefaultListModel modelo = new DefaultListModel();
listActividades.setModel(modelo);

return modelo;

}

    private void QuitarActividad() {
        //metodo invocado al presionar el menos en la lista de actividades
        double precio_estimado = 0;
        if(listActividades.getSelectedIndex()<0){ //si no esta seleccionada ninguna actividad de la lista...
            Utilidades.MostrarMensajeWarning("Debe seleccionar una actividad!");
        }else{
               if(actividades_a_calcular.size()>0){ //si la lista tiene por lo menos un elemento... 
            actividades_a_calcular.remove(listActividades.getSelectedIndex()); //se elimina del arraylist propiamente
            DefaultListModel modelo = (DefaultListModel) listActividades.getModel();
            modelo.remove(listActividades.getSelectedIndex()); //se elimina de la lista que aparece en pantalla
            
           precio_estimado = gimnasio.CalcularPrecio(actividades_a_calcular); // se vuelve a calcular con la lista actualizada
           
           if(actividades_a_calcular.size()==0){
               tfPrecio.setText(""); //Limpiar
           }else{
               tfPrecio.setText(String.valueOf(precio_estimado)); //se muestra en pantalla el precio actualizado
           }

        }
        }
 

    }

    private void MostrarInformacionAdicional() {
        //metodo que muestra informacion adicional en el text area correspondiente
       int fila_seleccionada = tablaObjetos.getSelectedRow();
         if (fila_seleccionada >= 0) {
             EtipoActividad nombre_actividad =   (EtipoActividad) tablaObjetos.getValueAt(fila_seleccionada, 0);
             String horario = (String)tablaObjetos.getValueAt(fila_seleccionada, 1);
             ArrayList<EdiaSemana> lista_dias = (ArrayList<EdiaSemana>) tablaObjetos.getValueAt(fila_seleccionada, 6);
             
             Actividad actividad_a_buscar = new Actividad(nombre_actividad,horario,lista_dias,"",0,0,false,"",0);
             
             try{
             Actividad actividad_encontrada = gimnasio.buscarActividad(actividad_a_buscar);
             taDescripcion.setText("Instructor: " + actividad_encontrada.getNombre_instructor() + "\n" + actividad_encontrada.getComentario());
             }catch(NoEncontradoException e){
                 taDescripcion.setText(e.getMessage());
             }


         }
    }

    private void ModificarCliente() {
        if (actividades_a_calcular.size() == 0) {
            Utilidades.MostrarMensajeWarning("Debe dar de baja el cliente si desea quitar todas sus actividades");
            InicializarCampos();
            Utilidades.MostrarMensajeInformativo("actividades a calcular:" + actividades_a_calcular);
        } else {

            //Fecha de Nacimiento
            Date fecha_date = dcFechaNacimiento.getDate();
            Instant instant = fecha_date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate fecha_de_nacimiento = instant.atZone(zoneId).toLocalDate();

            //Grupo Sanguineo
            String enum_formato_string = (String) cbGrupoSanguineo.getSelectedItem();
            EGrupoSanguineo grupo_sanguineo = EGrupoSanguineo.valueOf(enum_formato_string);

            //Genero
            String genero_formato_string = (String) cbGenero.getSelectedItem();
            EGenero genero = EGenero.valueOf(genero_formato_string);

            TreeSet<Actividad> actividades = new TreeSet<>(actividades_a_calcular);

            Cliente cliente = ObtenerCliente();

            try {
                gimnasio.modificarCliente(tfDni.getText(), tfNombre.getText(), tfDni.getText(), genero, tfTelefono.getText(),
                        tfDomicilio.getText(), tfEmail.getText(), cliente.getEstado(), grupo_sanguineo, tfTelEmergencia.getText(),
                        tfObraSocial.getText(), fecha_de_nacimiento, cliente.getComentario(), cliente.isAlta_medica(),
                        cliente.isSolicito_rutina(), cliente.isDebe(), cliente.getRutina(), actividades);
                gimnasio.sumarInscripto(actividades);
                gimnasio.restarInscripto(actividades_anteriores);
                Utilidades.MostrarMensajeInformativo("El cliente fue modificado exitosamente!");
                this.dispose();

            } catch (NoEncontradoException e) {
                Utilidades.MostrarMensajeWarning("Se produjo un error al localizar el cliente");
            }

        }

    }
        private boolean ValidarCamposCompletos() {
        boolean rta = false;
        if(CampoInvalido(tfDni.getText()) || CampoInvalido(tfNombre.getText()) || CampoInvalido(tfDomicilio.getText()) || CampoInvalido(tfTelefono.getText()) || CampoInvalido(tfTelEmergencia.getText())
                || CampoInvalido(tfEmail.getText()) || CampoInvalido(tfObraSocial.getText()) || cbGenero.getSelectedIndex()<0 || cbGrupoSanguineo.getSelectedIndex()<0 || dcFechaNacimiento.getDate()==null || dcFechaInscripcion.getDate()==null){
            Utilidades.MostrarMensajeWarning("Todos los campos deben estar completos!");
        }else if(actividades_a_calcular.size()==0){ //si no eligio ninguna actividad
            Utilidades.MostrarMensajeWarning("El cliente debe estar inscripto en al menos una actividad");
        }
        else{
            rta = true;
        }
        return rta;
        
    }
    
    private boolean CampoInvalido(String texto){
       boolean rta = false;
        if (texto == null || texto.trim().isEmpty()) {
            rta = true;
        } 
        return rta;
    }

    private void LimpiarCampos() {
                //metodo que limpia los campos 
                // tfDni.setText("");
                tfNombre.setText("");
                cbGenero.setSelectedIndex(-1);
                tfDomicilio.setText("");
                dcFechaNacimiento.setDate(null);
                tfTelefono.setText("");
                tfTelEmergencia.setText("");
                tfEmail.setText("");
                tfObraSocial.setText("");
                tfFiltrarActividad.setText("");
                checkAltaMedica.setSelected(false);
                cbGrupoSanguineo.setSelectedIndex(-1);

                
                //jlist
                DefaultListModel model = (DefaultListModel) listActividades.getModel();
                model.removeAllElements();
                
                checkSolicitarRutina.setSelected(false);
                
                actividades_a_calcular.clear();
                tfPrecio.setText("");
    }



    }
    

