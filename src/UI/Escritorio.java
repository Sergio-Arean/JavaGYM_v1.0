/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatMonocaiIJTheme;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import model.Otros.Gimnasio;
import model.Personal.Administrativo;
import model.Personal.Usuario;

/**
 *
 * @author sergi
 */
public class Escritorio extends javax.swing.JFrame {

    /**
     * Creates new form Escritorio
     */
    Usuario usuario;
    Gimnasio gimnasio;
    
    //inicio sentencias para mover ventana
    private int xMouse;
    private int yMouse;
     //fin sentencias para mover ventana
    public Escritorio(Usuario usuario,Gimnasio gimnasio) {
        initComponents();
        SetearBotones(25, 25);//25, 25

        this.usuario = usuario; //este es el usuario que esta operando en el sistema en la sesion iniciada
        this.gimnasio = gimnasio; //este es el gimnasio instanciado anteriormente

        InicializarDatos();

        InicializarPopUpMenu();

        AbrirPanelInicio();
        RestringirBotones();

        CargarLogo();
        CargarIcono();

        btnFinanzas.setVisible(false);
    }
    private void CargarLogo(){
         lblLogo.setSize(200,200);
         pintarImagen(lblLogo, "src/UI/imagenes/javagymicon3.png");
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
     
    private void InicializarDatos(){
        //metodo que inicializa los datos del escritorio, como por ejemplo el usuario que esta logueado
        //String texto = usuario.getNombre() + " (" + gimnasio.getTipoDeUsuario(usuario) + ")";
        String texto = usuario.getNombre();
        lblUsuario.setText(lblUsuario.getText() + " " + texto);
    }
    

    private void AbrirPanelClientes(){
       PanelClientes clientes = new PanelClientes(usuario,gimnasio);
       //clientes.setSize(915,347);
       clientes.setSize(757,347);
       clientes.setLocation(0,0);
       
       content.removeAll();
       content.add(clientes,BorderLayout.CENTER);
       content.revalidate();
       content.repaint();
    }

    
    private void SetearBotones(int alto,int ancho){
        //metodo para setear los iconos en los botones con el tamanio correcto
        SetearIcono(ancho, alto, btnInicio); //25, 25
        SetearIcono(ancho, alto, btnActividades);
        SetearIcono(ancho, alto, btnClientes);
        SetearIcono(ancho, alto, btnRutinas);
        SetearIcono(ancho, alto, btnStaff);
        SetearIcono(ancho, alto, btnDocumentos);
        SetearIcono(ancho, alto, btnAcercaDe);
        SetearIcono(ancho, alto, btnFinanzas);
        SetearIcono(ancho, alto, btnUsuarios);
        
    }
    
    private void SetearIcono(int ancho, int alto, JButton boton){
        //metodo para setear iconos de los botones en el tamanio justo
        // Obtén el icono actualmente asociado al botón
    ImageIcon iconoActual = (ImageIcon) boton.getIcon();


    Image imagenActual = iconoActual.getImage();


    int nuevoAncho = ancho;
    int nuevoAlto = alto;
    Image imagenRedimensionada = imagenActual.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);


    ImageIcon iconoRedimensionado = new ImageIcon(imagenRedimensionada);


    boton.setIcon(iconoRedimensionado);
        
    }
    private void RestringirBotones(){
        //este metodo, a partir del usuario que opera en el sistema, restringira algunos botones
        if(usuario instanceof Administrativo){
            btnFinanzas.setVisible(false);
            btnUsuarios.setVisible(false);
        }
    }
    
    private void InicializarPopUpMenu(){
        //este metodo inicializa el popupmenu de "cerrar sesion"
        JMenuItem cerrar_sesion = new JMenuItem("Cerrar sesión");

        JMenuItem cambiar_contrasenia = new JMenuItem("Cambiar contraseña");

        if (usuario instanceof Administrativo) { //encargado nuede cambiar contrasenia
            ppMenuCerrarSesion.add(cambiar_contrasenia);
        }

        ppMenuCerrarSesion.add(cerrar_sesion);

        lblUsuario.setComponentPopupMenu(ppMenuCerrarSesion);

        cerrar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CerrarSesion();
            }
        });
        cambiar_contrasenia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AbrirDialgModificarContrasenia();
            }

        });

    }

    private void AbrirDialgModificarContrasenia() {
        DialogCambiarContrasenia dialog_modificar_contra = new DialogCambiarContrasenia(new Escritorio(usuario, gimnasio), true, usuario, gimnasio);
        dialog_modificar_contra.setLocationRelativeTo(this);
        dialog_modificar_contra.setVisible(true);

    }
    
    
        private void CerrarSesion() {
            gimnasio.guardarEnArchivo();
            this.setVisible(false);
            Login form = new Login(gimnasio);
            form.pack();
            form.setVisible(true);

            }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ppMenuCerrarSesion = new javax.swing.JPopupMenu();
        background = new javax.swing.JPanel();
        barraHorizontal = new javax.swing.JPanel();
        btnMenu = new rsbuttom.RSButtonMetro();
        lblUsuario = new javax.swing.JLabel();
        lblCerrar = new javax.swing.JLabel();
        content = new javax.swing.JPanel();
        columnaMenu = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        btnActividades = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnRutinas = new javax.swing.JButton();
        btnStaff = new javax.swing.JButton();
        btnDocumentos = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnAcercaDe = new javax.swing.JButton();
        btnFinanzas = new javax.swing.JButton();
        minipanelcierre = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        barraHorizontal.setBackground(new java.awt.Color(213, 89, 6));
        barraHorizontal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barraHorizontalMouseDragged(evt);
            }
        });
        barraHorizontal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barraHorizontalMousePressed(evt);
            }
        });

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/img/menu.png"))); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario:");

        lblCerrar.setBackground(new java.awt.Color(105, 65, 140));
        lblCerrar.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        lblCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCerrar.setText("X");
        lblCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCerrarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout barraHorizontalLayout = new javax.swing.GroupLayout(barraHorizontal);
        barraHorizontal.setLayout(barraHorizontalLayout);
        barraHorizontalLayout.setHorizontalGroup(
            barraHorizontalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraHorizontalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barraHorizontalLayout.setVerticalGroup(
            barraHorizontalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraHorizontalLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraHorizontalLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(barraHorizontalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsuario)
                    .addComponent(lblCerrar))
                .addGap(11, 11, 11))
        );

        content.setPreferredSize(new java.awt.Dimension(912, 347));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 757, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        columnaMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconhome.png"))); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnInicio.setBorderPainted(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.setHideActionText(true);
        btnInicio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnInicio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInicio.setIconTextGap(40);
        btnInicio.setInheritsPopupMenu(true);
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        columnaMenu.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 37));

        btnActividades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconbiceps.png"))); // NOI18N
        btnActividades.setText("Actividades");
        btnActividades.setToolTipText("");
        btnActividades.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnActividades.setBorderPainted(false);
        btnActividades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnActividades.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnActividades.setIconTextGap(40);
        btnActividades.setVerifyInputWhenFocusTarget(false);
        btnActividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActividadesActionPerformed(evt);
            }
        });
        columnaMenu.add(btnActividades, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 37, 200, 37));

        btnClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconclientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnClientes.setBorderPainted(false);
        btnClientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClientes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnClientes.setIconTextGap(40);
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        columnaMenu.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 74, 200, 37));

        btnRutinas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRutinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconrutinas.png"))); // NOI18N
        btnRutinas.setText("Rutinas");
        btnRutinas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnRutinas.setBorderPainted(false);
        btnRutinas.setHideActionText(true);
        btnRutinas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRutinas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRutinas.setIconTextGap(40);
        btnRutinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutinasActionPerformed(evt);
            }
        });
        columnaMenu.add(btnRutinas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 111, 200, 37));

        btnStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconkimono.png"))); // NOI18N
        btnStaff.setText("Staff");
        btnStaff.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnStaff.setBorderPainted(false);
        btnStaff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnStaff.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnStaff.setIconTextGap(40);
        btnStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStaffActionPerformed(evt);
            }
        });
        columnaMenu.add(btnStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 148, 200, 37));

        btnDocumentos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDocumentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/icondocumentos.png"))); // NOI18N
        btnDocumentos.setText("Documentos");
        btnDocumentos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnDocumentos.setBorderPainted(false);
        btnDocumentos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDocumentos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDocumentos.setIconTextGap(40);
        btnDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentosActionPerformed(evt);
            }
        });
        columnaMenu.add(btnDocumentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 185, 200, 37));

        btnUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconajustes.png"))); // NOI18N
        btnUsuarios.setText("Usuarios");
        btnUsuarios.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnUsuarios.setBorderPainted(false);
        btnUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUsuarios.setIconTextGap(40);
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        columnaMenu.add(btnUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 259, 200, 37));

        btnAcercaDe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconinformacion.png"))); // NOI18N
        btnAcercaDe.setText("Acerca de");
        btnAcercaDe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnAcercaDe.setBorderPainted(false);
        btnAcercaDe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAcercaDe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAcercaDe.setIconTextGap(40);
        btnAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcercaDeActionPerformed(evt);
            }
        });
        columnaMenu.add(btnAcercaDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 222, 200, 37));

        btnFinanzas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnFinanzas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/imagenes/iconodinero.png"))); // NOI18N
        btnFinanzas.setText("Finanzas");
        btnFinanzas.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 10, 1, 1, new java.awt.Color(0, 0, 0)));
        btnFinanzas.setBorderPainted(false);
        btnFinanzas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFinanzas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnFinanzas.setIconTextGap(40);
        columnaMenu.add(btnFinanzas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 296, 200, 37));

        minipanelcierre.setPreferredSize(new java.awt.Dimension(50, 40));

        javax.swing.GroupLayout minipanelcierreLayout = new javax.swing.GroupLayout(minipanelcierre);
        minipanelcierre.setLayout(minipanelcierreLayout);
        minipanelcierreLayout.setHorizontalGroup(
            minipanelcierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        minipanelcierreLayout.setVerticalGroup(
            minipanelcierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lblLogo.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraHorizontal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(1120, 1120, 1120)
                .addComponent(minipanelcierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(columnaMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(barraHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minipanelcierre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(columnaMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(backgroundLayout.createSequentialGroup()
                                .addComponent(content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        // TODO add your handling code here:
        int posicion = this.columnaMenu.getX();
        if(posicion> -1){
            Animacion.Animacion.mover_izquierda(0, -200, 2, 2, columnaMenu);
        }else{
            Animacion.Animacion.mover_derecha(-200, 0, 2, 2, columnaMenu);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        AbrirPanelClientes();
    }//GEN-LAST:event_btnClientesActionPerformed

    private void lblCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseClicked
        // TODO add your handling code here:
        CerrarConValidacion();
    }//GEN-LAST:event_lblCerrarMouseClicked

    private void barraHorizontalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraHorizontalMousePressed
        // TODO add your handling code here:
     //inicio sentencias para mover ventana
    xMouse = evt.getX();
    yMouse = evt.getY();
     //fin sentencias para mover ventana
    }//GEN-LAST:event_barraHorizontalMousePressed

    private void barraHorizontalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barraHorizontalMouseDragged
        // TODO add your handling code here:x
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse,y-yMouse);
    }//GEN-LAST:event_barraHorizontalMouseDragged

    private void lblCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseEntered
        // TODO add your handling code here:
        lblCerrar.setForeground(Color.RED);
    }//GEN-LAST:event_lblCerrarMouseEntered

    private void lblCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCerrarMouseExited
        // TODO add your handling code here:
        lblCerrar.setForeground(null);
    }//GEN-LAST:event_lblCerrarMouseExited

    private void btnActividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActividadesActionPerformed
        AbrirPanelActividades();
    }//GEN-LAST:event_btnActividadesActionPerformed

    private void btnStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStaffActionPerformed
        // TODO add your handling code here:

        AbrirPanelStaff();
    }//GEN-LAST:event_btnStaffActionPerformed

    private void btnDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentosActionPerformed
        // TODO add your handling code here:
        AbrirPanelDocumentos();
    }//GEN-LAST:event_btnDocumentosActionPerformed

    private void btnRutinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutinasActionPerformed
        // TODO add your handling code here:
        AbrirPanelRutinas();
    }//GEN-LAST:event_btnRutinasActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        // TODO add your handling code here:
        AbrirPanelInicio();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        // TODO add your handling code here:
        AbrirPanelUsuarios();
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcercaDeActionPerformed
        // TODO add your handling code here:
        AbrirDialogAcercaDe();
    }//GEN-LAST:event_btnAcercaDeActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JPanel barraHorizontal;
    private javax.swing.JButton btnAcercaDe;
    private javax.swing.JButton btnActividades;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnDocumentos;
    private javax.swing.JButton btnFinanzas;
    private javax.swing.JButton btnInicio;
    private rsbuttom.RSButtonMetro btnMenu;
    private javax.swing.JButton btnRutinas;
    private javax.swing.JButton btnStaff;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JPanel columnaMenu;
    private javax.swing.JPanel content;
    private javax.swing.JLabel lblCerrar;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel minipanelcierre;
    private javax.swing.JPopupMenu ppMenuCerrarSesion;
    // End of variables declaration//GEN-END:variables

    private void CerrarConValidacion() {
        //este metodo cierra la aplicacion pero anteponiendo una validacion, por medio de la cual ademas en el back, se guarda en archivo
        String[] opciones = {"No", "Sí"};
        int rta = JOptionPane.showOptionDialog(null, "¿Cerrar Aplicación?", "Atención",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, opciones, opciones[1]);

        if (rta == 1) {
            gimnasio.guardarEnArchivo();
            System.exit(0);
        }

    }

    public Icon icono(String path, int width, int height) {
        Icon img = new ImageIcon(new ImageIcon(getClass().getResource(path)).getImage()
                .getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));

        return img;
    }

    private void AbrirPanelActividades() {
        PanelActividades actividades = new PanelActividades(usuario, gimnasio);
        //clientes.setSize(915,347);
        actividades.setSize(757, 347);
        actividades.setLocation(0, 0);

        content.removeAll();
        content.add(actividades, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private void AbrirPanelStaff() {
        PanelStaff staff = new PanelStaff(usuario, gimnasio);
        //clientes.setSize(915,347);
        staff.setSize(757, 347);
        staff.setLocation(0, 0);

        content.removeAll();
        content.add(staff, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private void AbrirPanelInicio() {
        PanelInicio inicio = new PanelInicio(usuario,gimnasio);
       //clientes.setSize(915,347);
       inicio.setSize(757,347);
       inicio.setLocation(0,0);
       
       content.removeAll();
       content.add(inicio,BorderLayout.CENTER);
       content.revalidate();
       content.repaint();
    }

    private void AbrirPanelDocumentos() {
        PanelDocumentos documentos = new PanelDocumentos(usuario,gimnasio);
       //clientes.setSize(915,347);
       documentos.setSize(757,347);
       documentos.setLocation(0,0);
       
       content.removeAll();
       content.add(documentos,BorderLayout.CENTER);
       content.revalidate();
       content.repaint();
    }

    private void AbrirPanelRutinas() {
        PanelRutinas rutinas = new PanelRutinas(usuario,gimnasio);
       rutinas.setSize(757,347);
       rutinas.setLocation(0,0);
       
       content.removeAll();
       content.add(rutinas,BorderLayout.CENTER);
       content.revalidate();
       content.repaint();
    }

    private void AbrirPanelUsuarios() {
        PanelUsuarios usuarios = new PanelUsuarios(usuario,gimnasio);
       usuarios.setSize(757,347);
       usuarios.setLocation(0,0);
       
       content.removeAll();
       content.add(usuarios,BorderLayout.CENTER);
       content.revalidate();
       content.repaint();
    }

    private void AbrirDialogAcercaDe() {
                     DialogAcercaDe dialogAcercaDe = new DialogAcercaDe(new Escritorio(usuario,gimnasio), true);
                    dialogAcercaDe.setLocationRelativeTo(this);
                    dialogAcercaDe.setVisible(true);
    }
}
