/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;


import UI.ApiClima.Clima;
import UI.ApiClima.GestionClima;
import UI.UtilidadesUI.CentradorDeColumna;
import excepciones.ClienteDeudorException;
import excepciones.NoEncontradoException;
import java.awt.Image;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
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
import model.Otros.Gimnasio;
//import model.Otros.PermisoInvalidoException;
import model.Personal.Usuario;
import model.Persona.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.time.Period;
import javax.imageio.ImageIO;

/**
 *
 * @author sergi
 */
public class PanelInicio extends javax.swing.JPanel {

    /**
     * Creates new form PanelClientes
     */
      private Gimnasio gimnasio;
      private Usuario usuario;

     


     
    public PanelInicio(Usuario usuario, Gimnasio gimnasio) {
        initComponents();
        SetearComponentes();
        this.gimnasio = gimnasio;
        this.usuario = usuario;
        CargarImagenes();
        ActualizarDatosClima();
        EstimarCumpleanios();

    }
    
    private void CargarImagenes(){
             lblCandado.setSize(60,60);
         pintarImagen(lblCandado, "src/UI/imagenes/iconcandado.png");
         
         


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
    
   
    
    private void SetearComponentes() {
        //con este metodo seteamos componentes..
        //Hoy es...
        LocalDate now = LocalDate.now();
        Locale spanishLocale = new Locale("es", "ES");
        lblHoyEs.setText(now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));

        btnIngresar.putClientProperty("JButton.buttonType", "roundRect");
  
    }
    




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    public PanelInicio() {
    }
// @SuppressWarnings("unchecked"); //esto esta comentaado por mi
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        tfAbrirPuerta = new javax.swing.JTextField();
        jCalendario = new com.toedter.calendar.JCalendar();
        lblHoyEs = new javax.swing.JLabel();
        lblCandado = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        lblTemperatura = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCumples = new javax.swing.JTextArea();
        lblImagenClima = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(757, 347));

        background.setMaximumSize(new java.awt.Dimension(915, 347));
        background.setMinimumSize(new java.awt.Dimension(915, 347));

        btnIngresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIngresar.setText("Abrir");
        btnIngresar.setActionCommand("");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        tfAbrirPuerta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAbrirPuertaActionPerformed(evt);
            }
        });
        tfAbrirPuerta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfAbrirPuertaKeyReleased(evt);
            }
        });

        lblHoyEs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHoyEs.setText("     ");

        lblUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUbicacion.setText("Ubicacion");

        lblTemperatura.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTemperatura.setText("DatosClima");

        taCumples.setColumns(20);
        taCumples.setRows(5);
        taCumples.setEnabled(false);
        jScrollPane1.setViewportView(taCumples);

        lblImagenClima.setPreferredSize(new java.awt.Dimension(64, 64));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Próximos Cumpleaños");

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHoyEs, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImagenClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTemperatura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfAbrirPuerta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblCandado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblHoyEs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(lblImagenClima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUbicacion))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(lblCandado, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAbrirPuerta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        ValidarIngreso(tfAbrirPuerta.getText());
        tfAbrirPuerta.setText("");



    }//GEN-LAST:event_btnIngresarActionPerformed

    private void tfAbrirPuertaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAbrirPuertaKeyReleased
        // TODO add your handling code here:
              
    }//GEN-LAST:event_tfAbrirPuertaKeyReleased

    private void tfAbrirPuertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAbrirPuertaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAbrirPuertaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnIngresar;
    private com.toedter.calendar.JCalendar jCalendario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCandado;
    private javax.swing.JLabel lblHoyEs;
    private javax.swing.JLabel lblImagenClima;
    private javax.swing.JLabel lblTemperatura;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JTextArea taCumples;
    private javax.swing.JTextField tfAbrirPuerta;
    // End of variables declaration//GEN-END:variables

    private void ValidarIngreso(String dni) {
        try {
            if (!gimnasio.ClienteEstadoInvalido(dni)) {
                gimnasio.ValidarIngresoCliente(dni);
                Utilidades.MostrarMensajeInformativo("Ingreso OK! \nCliente: " + gimnasio.GetNombreCliente(dni));
            } else {
                Utilidades.MostrarMensajeWarning("El cliente no se encuentra en un estado válido para ingresar.");
            }

        } catch (NoEncontradoException e) {
            Utilidades.MostrarMensajeWarning(e.getMessage());
        } catch (ClienteDeudorException e) {
            Utilidades.MostrarMensajeWarning(e.getMessage());
        }
       
    }

    /**
     * Metodo que permite obtener la informacion actualizada del Clima a partir del consumo de una API
     */

    private void ActualizarDatosClima() {
        try {
            GestionClima gestionClima = new GestionClima();
            Clima clima = gestionClima.DatosClima();
            lblUbicacion.setText(clima.getCiudad());
            lblTemperatura.setText(clima.getTemperatura() + "°C , " + clima.getEstado().name());

            String url = "https:" + clima.getIcono();

            URL imageUrl = new URL(url);
            Image image = ImageIO.read(imageUrl);

            lblImagenClima.setSize(64, 64);

            Image scaledImage = image.getScaledInstance(lblImagenClima.getWidth(), lblImagenClima.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon imageIcon = new ImageIcon(scaledImage);

            lblImagenClima.setIcon(imageIcon);

        } catch (JSONException e) {
            lblUbicacion.setText("Información no disponible");
        } catch (IOException e) {
            lblImagenClima.setText("Imagen de clima no disponible");
        }

    }
    
  

    private void EstimarCumpleanios(){
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaFin = fechaHoy.plusDays(7);
        String texto = "";
        int edad = 0;
        String json_clientes = gimnasio.CompartirDatosClientes();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d 'de' MMMM", new Locale("es"));

        String fecha_formateada = "";

        try {
            JSONArray jsonArray_clientes = new JSONArray(json_clientes);
            for (int i = 0; i < jsonArray_clientes.length(); i++) {
                Cliente cliente = new Cliente();
                cliente = cliente.fromJson(jsonArray_clientes.getJSONObject(i));

                LocalDate fechaCumple = cliente.getFecha_nacimiento().withYear(fechaHoy.getYear());
                if (fechaCumple.isBefore(fechaHoy)) {
                    fechaCumple = fechaCumple.plusYears(1);
                }

                if (fechaCumple.isAfter(fechaHoy) && fechaCumple.isBefore(fechaFin)) {
                    edad = Period.between(fechaHoy, fechaCumple).getYears();
                    fecha_formateada = cliente.getFecha_nacimiento().format(formateador);
                    texto += cliente.getNombre() + " - " + fecha_formateada + "\n";
                }

            }
            taCumples.setText(texto);

        } catch (JSONException e) {
            Utilidades.MostrarMensajeInformativo(e.getMessage());
        }

    }
    
   
}
