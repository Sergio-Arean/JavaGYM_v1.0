/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package UI;
import excepciones.NoEncontradoException;
import model.Otros.Gimnasio;
import model.Personal.Usuario;

/**
 *
 * @author sergi
 */
public class DialogCambiarContrasenia extends javax.swing.JDialog {

    /**
     * Creates new form DialogCambiarContrasenia
     */
    private Gimnasio gimnasio;
    private Usuario usuario;
    public DialogCambiarContrasenia(java.awt.Frame parent, boolean modal,Usuario usuario, Gimnasio gimnasio) {
        super(parent, modal);
        initComponents();
        
        this.usuario = usuario;
        this.gimnasio = gimnasio;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblDni3 = new javax.swing.JLabel();
        tfNombreUsuario = new javax.swing.JTextField();
        lblDni2 = new javax.swing.JLabel();
        lblDni4 = new javax.swing.JLabel();
        btnCambiarContra = new javax.swing.JButton();
        pfRepetirContrasenia = new javax.swing.JPasswordField();
        pfContrasenia = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblDni3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDni3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDni3.setText("Nombre de Usuario");

        tfNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblDni2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDni2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDni2.setText("Nueva Contraseña");

        lblDni4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDni4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDni4.setText("Repetir Contraseña");

        btnCambiarContra.setText("Cambiar Contraseña");
        btnCambiarContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraActionPerformed(evt);
            }
        });

        pfRepetirContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        pfContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCambiarContra, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDni4, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(lblDni2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblDni3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfNombreUsuario)
                            .addComponent(pfRepetirContrasenia)
                            .addComponent(pfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(115, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni3)
                    .addComponent(tfNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni2)
                    .addComponent(pfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni4)
                    .addComponent(pfRepetirContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCambiarContra, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCambiarContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContraActionPerformed
        // TODO add your handling code here:
       if(ContraseniasCoinciden()){
           CambiarContrasenia();
       }else{
            Utilidades.MostrarMensajeWarning("Las contraseñas no coinciden!");
       }
        
    }//GEN-LAST:event_btnCambiarContraActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarContra;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDni2;
    private javax.swing.JLabel lblDni3;
    private javax.swing.JLabel lblDni4;
    private javax.swing.JPasswordField pfContrasenia;
    private javax.swing.JPasswordField pfRepetirContrasenia;
    private javax.swing.JTextField tfNombreUsuario;
    // End of variables declaration//GEN-END:variables

    private void CambiarContrasenia() {
        //String.valueOf(tfContrasenia.getPassword())
        
        try{
            gimnasio.ModifcarContrasenia(tfNombreUsuario.getText(), String.valueOf(pfContrasenia.getPassword()));
            Utilidades.MostrarMensajeInformativo("La contraseña fue modificada con éxito!");
            this.dispose();
        }catch(NoEncontradoException e){
            Utilidades.MostrarMensajeWarning(e.getMessage());
        }
    }

    private boolean ContraseniasCoinciden() {
        //metodo que verifica que coincidan las contrasenias
        boolean rta = false;
        if(String.valueOf(pfContrasenia.getPassword()).equals(String.valueOf(pfRepetirContrasenia.getPassword()))){
            rta = true;
        }
        return rta;
    }
}