/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import excepciones.CredencialesInvalidasException;
import excepciones.ExistenteException;
import excepciones.NoEncontradoException;
import excepciones.UsuarioExistenteException;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Image;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeSet;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.ActivYrutina.Actividad;
import model.ActivYrutina.Rutina;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.EdiaSemana;
import model.Enum.Eestado;
import model.Enum.EtipoActividad;
import model.Otros.Factura;
import model.Otros.Gimnasio;
import model.Persona.Cliente;
import model.Personal.Administrativo;
import model.Personal.Instructor;
import model.Personal.Usuario;


/**
 *
 * @author sergi
 */
public class Login extends javax.swing.JFrame {
    
    private Gimnasio gimnasio;
    /**
     * Creates new form Login
     */

    public Login(Gimnasio gimnasio) { 
        initComponents();
        lblOlvidoContra.setText("<html><u>¿Olvidó su Contraseña?</u></html>");
        lblRegistrarse.setText("<html><u>Registrarse</u></html>");
        //gimnasio = new Gimnasio();
        this.gimnasio = gimnasio;
        tfUsuario.putClientProperty( "JComponent.roundRect", true );
        tfContrasenia.putClientProperty( "JComponent.roundRect", true );
        CargarLogo();
        CargarIcono();
        FormatearBotones();
        
    }
    
    private void FormatearBotones(){
        btnIniciarSesion.putClientProperty( "JButton.buttonType", "roundRect" );
        
        
    }
    
    private void CargarLogo(){
      lblLogo.setSize(200,200);
         pintarImagen(lblLogo, "src/UI/imagenes/iconocentrado.png");
    }
    

    private void CargarIcono(){
        ImageIcon icono = new ImageIcon("src/UI/imagenes/javagymicon3.png");
        this.setIconImage(icono.getImage());
 
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


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblOlvidoContra = new javax.swing.JLabel();
        tfUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfContrasenia = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblRegistrarse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Usuario:");
        jLabel1.setToolTipText("");

        lblOlvidoContra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOlvidoContra.setText("¿Olvidó su Contraseña?");
        lblOlvidoContra.setToolTipText("");
        lblOlvidoContra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOlvidoContra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOlvidoContraMouseClicked(evt);
            }
        });

        tfUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfUsuario.setBorder(null);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Contraseña:");
        jLabel5.setToolTipText("");

        tfContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tfContrasenia.setBorder(null);

        btnIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarSesionActionPerformed(evt);
            }
        });

        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTitulo.setText("JavaGYM v1.0");

        lblRegistrarse.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRegistrarse.setText("Registrarse ");
        lblRegistrarse.setToolTipText("");
        lblRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegistrarseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(lblOlvidoContra, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(175, 175, 175))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177))))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRegistrarse)
                .addGap(25, 25, 25)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(btnIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblOlvidoContra)
                .addGap(12, 12, 12)
                .addComponent(lblTitulo)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarSesionActionPerformed

       
       try{
           Usuario usu = gimnasio.IngresarAlSistema(tfUsuario.getText(), String.valueOf(tfContrasenia.getPassword()));
           AbrirVentanaPrincipal(usu);
           
           
       }
       
       catch(CredencialesInvalidasException e){
           Utilidades.MostrarMensajeInformativo("Usuario y/o contraseña invalidos");
       }
    }//GEN-LAST:event_btnIniciarSesionActionPerformed

    private void lblOlvidoContraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOlvidoContraMouseClicked
        // TODO add your handling code here:
        OlvidoSuContrasenia();
    }//GEN-LAST:event_lblOlvidoContraMouseClicked

    private void lblRegistrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegistrarseMouseClicked
        // TODO add your handling code here:
        AbrirVentanaRegistrarse();
    }//GEN-LAST:event_lblRegistrarseMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]){
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
           // UIManager.setLookAndFeel( new FlatLightLaf() );
                 //    InicializacionGeneral();

                        FlatArcDarkOrangeIJTheme.setup(); 
                        Gimnasio javagym = new Gimnasio();
                        Login login = new Login(javagym);
                        login.setVisible(true);

                 
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        //</editor-fold>

        /* Create and display the form */
       /* java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });*/ //comentado
    }
    
    public void AbrirVentanaPrincipal(Usuario usuario){
        //Este metodo abre la ventana principal
            
            Escritorio form = new Escritorio(usuario,gimnasio);
            form.setVisible(true);
            this.dispose();
    }
    
    public static void InicializacionGeneral(){
          //


    }


    public Login(Gimnasio gimnasio, JPanel background, JButton btnIniciarSesion, JLabel jLabel1, JLabel jLabel5, JLabel lblRegistrarse, JPasswordField tfContrasenia, JTextField tfUsuario, GraphicsConfiguration gc) {
        super(gc);
        this.gimnasio = gimnasio;
        this.background = background;
        this.btnIniciarSesion = btnIniciarSesion;
        this.jLabel1 = jLabel1;
        this.jLabel5 = jLabel5;
        this.lblOlvidoContra = lblRegistrarse;
        this.tfContrasenia = tfContrasenia;
        this.tfUsuario = tfUsuario;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Login(Gimnasio gimnasio, JPanel background, JButton btnIniciarSesion, JLabel jLabel1, JLabel jLabel5, JLabel lblRegistrarse, JPasswordField tfContrasenia, JTextField tfUsuario) throws HeadlessException {
        this.gimnasio = gimnasio;
        this.background = background;
        this.btnIniciarSesion = btnIniciarSesion;
        this.jLabel1 = jLabel1;
        this.jLabel5 = jLabel5;
        this.lblOlvidoContra = lblRegistrarse;
        this.tfContrasenia = tfContrasenia;
        this.tfUsuario = tfUsuario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblOlvidoContra;
    private javax.swing.JLabel lblRegistrarse;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPasswordField tfContrasenia;
    private javax.swing.JTextField tfUsuario;
    // End of variables declaration//GEN-END:variables

    private void AbrirVentanaRegistrarse() {
        //este metodo abre la ventana para registrarse.
                    DialogRegistrarUsuario registrarse = new DialogRegistrarUsuario(this, true,gimnasio);
                    registrarse.setLocationRelativeTo(this);
                    registrarse.setVisible(true);
              
    }

    private void OlvidoSuContrasenia() {
                    DialogReestablecerContrasenia dialog = new DialogReestablecerContrasenia(new Login(gimnasio), true,gimnasio);
                    dialog.setLocationRelativeTo(this);
                    dialog.setVisible(true);
        
    }
}