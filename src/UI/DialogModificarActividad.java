/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;

import static UI.Utilidades.MostrarMensajeInformativo;
import excepciones.ExistenteException;
import excepciones.NoEncontradoException;
import excepciones.UsuarioExistenteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import model.ActivYrutina.Actividad;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.EdiaSemana;
import model.Enum.EtipoActividad;
import model.Otros.Gimnasio;
import model.Personal.Instructor;
import model.Personal.Usuario;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author sergi
 */
public class DialogModificarActividad extends javax.swing.JDialog {

    /**
     * Creates new form DialogRegistrarse
     */
    private Gimnasio gimnasio;
    private Actividad actividad_seleccionada;
    public DialogModificarActividad(java.awt.Frame parent, boolean modal,Gimnasio gimnasio, Actividad actividad_seleccionada) {
        super(parent, modal);
        this.gimnasio = gimnasio;
        this.actividad_seleccionada = actividad_seleccionada;
        initComponents();
        btnModificar.putClientProperty( "JButton.buttonType", "roundRect" ); //botoncircular
        CargarComponentes();
        SetearCampos();
    }
        private void CargarComponentes(){
        CargarComboBox();
        
        
    }

    private void CargarComboBox(){
        //Cargando los nombres de las actividades
        for (EtipoActividad tipo_actividad : EtipoActividad.values()) {
            cbNombreActividad.addItem(tipo_actividad.name());
        }
        
        //Cargando nombres instructores
        ArrayList <String> nombres_instructores = ObtenerNombresInstructores();
          for (String nombre : nombres_instructores) {
            cbNombreInstructor.addItem(nombre);
        }
                
        
        
    }
    
    private void SetearCampos(){
        cbNombreActividad.setSelectedItem(actividad_seleccionada.getNombre().name());
        
        cbNombreInstructor.setSelectedItem(actividad_seleccionada.getNombre_instructor());
        
        String texto_horario = actividad_seleccionada.getHorario();
        



        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm");


        String[] partes = texto_horario.split(" - ");
        String horarioInicio = partes[0];
        String horarioFin = partes[1];


        LocalTime tiempoInicio = LocalTime.parse(horarioInicio, formateador);
        LocalTime tiempoFin = LocalTime.parse(horarioFin, formateador);


        int horaInicio = tiempoInicio.getHour();
        int minutosInicio = tiempoInicio.getMinute();


        int horaFin = tiempoFin.getHour();
        int minutosFin = tiempoFin.getMinute();


        String horaInicioFormateada = String.format("%02d", horaInicio);
        String minutosInicioFormateados = String.format("%02d", minutosInicio);
        String horaFinFormateada = String.format("%02d", horaFin);
        String minutosFinFormateados = String.format("%02d", minutosFin);
        
        tfHoraInicioA.setText(horaInicioFormateada);
        tfHoraInicioB.setText(minutosInicioFormateados);
        tfHoraFinalA.setText(horaFinFormateada);
        tfHoraFinalB.setText(minutosFinFormateados);
        
        ArrayList <EdiaSemana> dias = actividad_seleccionada.getListaDias();
        for (EdiaSemana dia : dias) {
            if (dia.name().equals("LUNES")) {
                rbLunes.setSelected(true);
            }
            if (dia.name().equals("MARTES")) {
                rbMartes.setSelected(true);
            }
            if (dia.name().equals("MIERCOLES")) {
                rbMiercoles.setSelected(true);
            }
            if (dia.name().equals("JUEVES")) {
                rbJueves.setSelected(true);
            }
            if (dia.name().equals("VIERNES")) {
                rbViernes.setSelected(true);
            }
            if (dia.name().equals("SABADO")) {
                rbSabado.setSelected(true);
            }
            if (dia.name().equals("DOMINGO")) {
                rbDomingo.setSelected(true);
            }
            
        }
        tfCupo.setText(String.valueOf(actividad_seleccionada.getCupo()));
        tfPrecio.setText(String.valueOf(actividad_seleccionada.getPrecio_mensual()));
        taComentario.setText(actividad_seleccionada.getComentario());


        
    }

    
    private ArrayList <String> ObtenerNombresInstructores(){
         String json_instructores= gimnasio.CompartirDatosInstructores();
        //System.out.println("json de instructores " + json_instructores);
        ArrayList <String> nombres_instructores = new ArrayList<>();
        try {
            JSONArray jsonArray_clientes = new JSONArray(json_instructores);
            for(int i=0; i<jsonArray_clientes.length();i++){
                Instructor instructor = new Instructor();
                instructor = instructor.fromJson(jsonArray_clientes.getJSONObject(i));
                nombres_instructores.add(instructor.getNombre());
            }}catch(JSONException e){
                    Utilidades.MostrarMensajeInformativo(e.getMessage());
                    }
               return nombres_instructores;
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        lblObraSocial = new javax.swing.JLabel();
        tfHoraInicioA = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        lblNombre1 = new javax.swing.JLabel();
        cbNombreActividad = new javax.swing.JComboBox<>();
        rbLunes = new javax.swing.JRadioButton();
        rbMartes = new javax.swing.JRadioButton();
        rbMiercoles = new javax.swing.JRadioButton();
        rbJueves = new javax.swing.JRadioButton();
        rbViernes = new javax.swing.JRadioButton();
        rbSabado = new javax.swing.JRadioButton();
        rbDomingo = new javax.swing.JRadioButton();
        lblNombre2 = new javax.swing.JLabel();
        cbNombreInstructor = new javax.swing.JComboBox<>();
        lblObraSocial1 = new javax.swing.JLabel();
        lblObraSocial2 = new javax.swing.JLabel();
        lblObraSocial4 = new javax.swing.JLabel();
        lblObraSocial5 = new javax.swing.JLabel();
        lblObraSocial6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taComentario = new javax.swing.JTextArea();
        tfHoraInicioB = new javax.swing.JTextField();
        tfHoraFinalA = new javax.swing.JTextField();
        lblObraSocial7 = new javax.swing.JLabel();
        tfHoraFinalB = new javax.swing.JTextField();
        tfCupo = new javax.swing.JTextField();
        lblObraSocial8 = new javax.swing.JLabel();
        tfPrecio = new javax.swing.JTextField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Actividad");
        setResizable(false);

        lblObraSocial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial.setText("Hora de Finalización");
        lblObraSocial.setToolTipText("");

        tfHoraInicioA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfHoraInicioA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfHoraInicioAActionPerformed(evt);
            }
        });
        tfHoraInicioA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHoraInicioAKeyTyped(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.setActionCommand("");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblNombre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre1.setText("Actividad");

        cbNombreActividad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        rbLunes.setText("Lunes");

        rbMartes.setText("Martes");

        rbMiercoles.setText("Miércoles");

        rbJueves.setText("Jueves");

        rbViernes.setText("Viernes");

        rbSabado.setText("Sábado");

        rbDomingo.setText("Domingo");

        lblNombre2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre2.setText("Instructor");

        cbNombreInstructor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblObraSocial1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial1.setText("Hora de Inicio");
        lblObraSocial1.setToolTipText("");

        lblObraSocial2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObraSocial2.setText(":");
        lblObraSocial2.setToolTipText("");

        lblObraSocial4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial4.setText("Días");
        lblObraSocial4.setToolTipText("");

        lblObraSocial5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial5.setText("Cupo");
        lblObraSocial5.setToolTipText("");

        lblObraSocial6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial6.setText("Comentario");
        lblObraSocial6.setToolTipText("");

        taComentario.setColumns(20);
        taComentario.setRows(5);
        jScrollPane1.setViewportView(taComentario);

        tfHoraInicioB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfHoraInicioB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHoraInicioBKeyTyped(evt);
            }
        });

        tfHoraFinalA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfHoraFinalA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHoraFinalAKeyTyped(evt);
            }
        });

        lblObraSocial7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObraSocial7.setText(":");
        lblObraSocial7.setToolTipText("");

        tfHoraFinalB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfHoraFinalB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHoraFinalBKeyTyped(evt);
            }
        });

        tfCupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfCupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfCupoKeyTyped(evt);
            }
        });

        lblObraSocial8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblObraSocial8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObraSocial8.setText("Precio");
        lblObraSocial8.setToolTipText("");

        tfPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombre1)
                            .addComponent(lblNombre2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbNombreInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(226, 226, 226))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblObraSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObraSocial1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObraSocial4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfHoraInicioA, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObraSocial2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfHoraInicioB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfHoraFinalA, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObraSocial7, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfHoraFinalB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(331, 331, 331))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblObraSocial5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(lblObraSocial8, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfPrecio)
                .addGap(338, 338, 338))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblObraSocial6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(cbNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre2)
                    .addComponent(cbNombreInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObraSocial1)
                    .addComponent(tfHoraInicioA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObraSocial2)
                    .addComponent(tfHoraInicioB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObraSocial)
                    .addComponent(tfHoraFinalA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObraSocial7)
                    .addComponent(tfHoraFinalB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLunes)
                    .addComponent(lblObraSocial4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMartes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbMiercoles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbJueves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbViernes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSabado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbDomingo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObraSocial5)
                    .addComponent(tfCupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblObraSocial8)
                    .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblObraSocial6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
      if(ValidarCamposCompletos()){
      ModificarActividad();
         }
      
    }//GEN-LAST:event_btnModificarActionPerformed

    private void tfHoraInicioAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfHoraInicioAActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfHoraInicioAActionPerformed

    private void tfHoraInicioAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraInicioAKeyTyped
        // TODO add your handling code here:

        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_tfHoraInicioAKeyTyped

    private void tfHoraInicioBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraInicioBKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_tfHoraInicioBKeyTyped

    private void tfHoraFinalAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraFinalAKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_tfHoraFinalAKeyTyped

    private void tfHoraFinalBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHoraFinalBKeyTyped
        // TODO add your handling code here:
                char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();
    }//GEN-LAST:event_tfHoraFinalBKeyTyped

    private void tfCupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfCupoKeyTyped
        // TODO add your handling code here:
        
                        char c = evt.getKeyChar();
        if(c<'0' || c>'9') evt.consume();

    }//GEN-LAST:event_tfCupoKeyTyped

    private void tfPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioKeyTyped
        // TODO add your handling code here:
          char c = evt.getKeyChar();
    if (!(Character.isDigit(c) || c == '.')) {
        evt.consume(); 
    }

    }//GEN-LAST:event_tfPrecioKeyTyped

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cbNombreActividad;
    private javax.swing.JComboBox<String> cbNombreInstructor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombre2;
    private javax.swing.JLabel lblObraSocial;
    private javax.swing.JLabel lblObraSocial1;
    private javax.swing.JLabel lblObraSocial2;
    private javax.swing.JLabel lblObraSocial4;
    private javax.swing.JLabel lblObraSocial5;
    private javax.swing.JLabel lblObraSocial6;
    private javax.swing.JLabel lblObraSocial7;
    private javax.swing.JLabel lblObraSocial8;
    private javax.swing.JRadioButton rbDomingo;
    private javax.swing.JRadioButton rbJueves;
    private javax.swing.JRadioButton rbLunes;
    private javax.swing.JRadioButton rbMartes;
    private javax.swing.JRadioButton rbMiercoles;
    private javax.swing.JRadioButton rbSabado;
    private javax.swing.JRadioButton rbViernes;
    private javax.swing.JTextArea taComentario;
    private javax.swing.JTextField tfCupo;
    private javax.swing.JTextField tfHoraFinalA;
    private javax.swing.JTextField tfHoraFinalB;
    private javax.swing.JTextField tfHoraInicioA;
    private javax.swing.JTextField tfHoraInicioB;
    private javax.swing.JTextField tfPrecio;
    // End of variables declaration//GEN-END:variables


    private ArrayList<EdiaSemana> ObtenerDiasSeleccionados(){
        //Permite obtener los dias seleccionados para la nueva actividad
        ArrayList<EdiaSemana> listaDias = new ArrayList<>();

        if (rbLunes.isSelected()) {
            listaDias.add(EdiaSemana.LUNES);
        }
        if (rbMartes.isSelected()) {
            listaDias.add(EdiaSemana.MARTES);
        }
        if (rbMiercoles.isSelected()) {
            listaDias.add(EdiaSemana.MIERCOLES);
        }
        if (rbJueves.isSelected()) {
            listaDias.add(EdiaSemana.JUEVES);
        }
        if (rbViernes.isSelected()) {
            listaDias.add(EdiaSemana.VIERNES);
        }
        if (rbSabado.isSelected()) {
            listaDias.add(EdiaSemana.SABADO);
        }
        if (rbDomingo.isSelected()) {
            listaDias.add(EdiaSemana.DOMINGO);
        }

        return listaDias;
    }
    
    private String ObtenerHorario(){
        //este metodo permite obtener el horario ingresado, se servira a su vez de metodos de validacion de horario
        //retorna el horario
        
        boolean horario_valido = false;
        String horario_completo = "";
        
         //Validacion Horario de Inicio
        String horario_inicio = tfHoraInicioA.getText() + ":" + tfHoraInicioB.getText();
        try{
            horario_valido = ValidarHorario(horario_inicio);
        }catch(ParseException e){
            Utilidades.MostrarMensajeWarning("El horario ingresado no es válido. Ingrese nuevamente");
        }
        
        //Validacion Horario de Finalizacion
        String horario_finalizacion = tfHoraFinalA.getText() + ":" + tfHoraFinalB.getText();
        try{
            horario_valido = ValidarHorario(horario_finalizacion);
        }catch(ParseException e){
            Utilidades.MostrarMensajeWarning("El horario ingresado no es válido. Ingrese nuevamente");
        }
        
        horario_completo = horario_inicio + " - " + horario_finalizacion;
        
        
        
        
        return horario_completo;
    }
    

        private boolean ValidarHorario(String horario) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false); 
            Date date = dateFormat.parse(horario);
            String horarioConvertido = dateFormat.format(date);
            return horario.equals(horarioConvertido);
    }

    private void ModificarActividad() {

        try{
       
        //Nombre
        String nombre_formato_string = (String) cbNombreActividad.getSelectedItem();
        EtipoActividad tipo_actividad = EtipoActividad.valueOf(nombre_formato_string);
        
        //Horario
        String horario = ObtenerHorario();
        
        //Dias Seleccionados
        ArrayList<EdiaSemana> listaDias = ObtenerDiasSeleccionados();
       
        //Nombre instructor
        String nombre_instructor = (String) cbNombreInstructor.getSelectedItem();

        //Se instancia actividad
        Actividad nueva_actividad = new Actividad(tipo_actividad,horario,listaDias,nombre_instructor,Integer.parseInt(tfCupo.getText()),0,true,taComentario.getText(),Double.parseDouble(tfPrecio.getText()));
            
        boolean agregado = gimnasio.modificarActividad(actividad_seleccionada, nueva_actividad);
            if(agregado){
                Utilidades.MostrarMensajeInformativo("La actividad fue modificada exitosamente!");
            }
            else{
                Utilidades.MostrarMensajeWarning("La actividad no pudo ser modificada.");
            }
            
            
            this.dispose();
        }catch(NoEncontradoException e){
            Utilidades.MostrarMensajeWarning(e.getMessage());
        }catch(ExistenteException e){
            Utilidades.MostrarMensajeWarning(e.getMessage());
        }
        
    }

      private boolean ValidarCamposCompletos() {
        //cuando es un combo box  cbGenero.getSelectedIndex()<0
        //radio button isSelected
        boolean rta = true;
        if(cbNombreActividad.getSelectedIndex()<0 || cbNombreInstructor.getSelectedIndex()<0 
                || CampoInvalido(tfHoraInicioA.getText()) || CampoInvalido(tfHoraInicioB.getText())
                        || CampoInvalido(tfHoraFinalA.getText()) || CampoInvalido(tfHoraFinalB.getText()) || NingunDiaSeleccionado()
                || CampoInvalido(tfCupo.getText()) || CampoInvalido(tfPrecio.getText())){
            Utilidades.MostrarMensajeWarning("Uno o más campos está incompleto o presenta algún error");
            rta = false;
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
        
        private boolean NingunDiaSeleccionado(){
           boolean rta = false;
            if(!rbLunes.isSelected() &&
                    !rbMartes.isSelected() &&
                    !rbMiercoles.isSelected() &&
                    !rbJueves.isSelected() &&
                    !rbViernes.isSelected() &&
                    !rbSabado.isSelected() &&
                    !rbDomingo.isSelected()){
                rta = true;
            }
            return rta;
        }




}
