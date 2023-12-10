/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package RedSocial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CrearUsuario extends javax.swing.JDialog {

    /**
     * Creates new form CrearUsuario
     */
    
    
    private RandomAccessFile f_usuarios;
    private final LabelPerfil lbl_img;
    private File img_p;
    private final File img_default = new File("varios/icon_usuario.png");
    
    public String nombre;
    public String tipo;
    
    public CrearUsuario(javax.swing.JInternalFrame padre, String nombre, String tipo) {
        
        this.nombre = nombre;
        this.tipo = tipo;
        initComponents();
        this.setModal(true);
        
        this.setLocationRelativeTo(padre);
        this.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(9, 26, 52));
        this.setTitle("Crear Usuario");

        String[] numeros = new String[100];

        for (int i = 0; i < 100; i++) {

            numeros[i] = "" + (18 + i);
        }

        cbEdad.setModel(new javax.swing.DefaultComboBoxModel<>(numeros));

        try {
            f_usuarios = new RandomAccessFile("RedRetro/usuarios.twc", "rw");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error en archivo Usuarios", JOptionPane.ERROR_MESSAGE);
        }

        lbl_img = new LabelPerfil(100);

        lbl_img.setBounds(158, 50, 100, 100);

        lbl_img.setOpaque(false);
        lbl_img.setPreferredSize(new Dimension(100, 100));

        img_p = null;
        
        lbl_img.setIcon(new javax.swing.ImageIcon(img_default.getPath())); // NOI18N
        lbl_img.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_img.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_imgMouseClicked(evt);
            }
        });

        this.setLayout(null);
        this.add(lbl_img);
        this.setVisible(true);
    }

    private boolean validarCampos() {

        if (txtNombreC.getText().length() != 0) {
            if (txtContraseña.getText().length() != 0) {
                if (txtUsuario.getText().length() != 0) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "El campo de Usuario esta vacio");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El campo de Contraseña esta vacio");
            }
        } else {
            JOptionPane.showMessageDialog(this, "El Campo de nombre esta vacio");
        }

        return false;

    }

    private void lbl_imgMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        JFileChooser chooser = new JFileChooser();

        if (tipo.equals("Administrador")) {
            chooser.setCurrentDirectory(new File("Z"));
        } else 
            chooser.setCurrentDirectory(new File("Z/" + nombre));
       
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);

        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {

            try {
                File img_selected = chooser.getSelectedFile();

                BufferedImage originalImage = ImageIO.read(img_selected);

                // JOptionPane.showMessageDialog(this, "selecionada", "s", 1, new ImageIcon(originalImage));
                // Crear una nueva imagen escalada con el tamaño deseado
                BufferedImage scaledImage = null;
                scaledImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

                // Escalar la imagen original al tamaño deseado y copiarla en la nueva imagen escalada
                Graphics2D g2d = null;
                g2d = scaledImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(originalImage, 0, 0, 100, 100, null);

                g2d.dispose();

                // Guardar la imagen escalada en un archivo
                img_p = new File("RedRetro/foto_perfil_temp.jpg");
                ImageIO.write(scaledImage, "jpg", img_p);

                //JOptionPane.showMessageDialog(this, "Imagen seleccionnada", "otro", 1, new ImageIcon(img_p.getPath()));
                ImageIcon perfil = new ImageIcon(img_p.getAbsolutePath());

                lbl_img.setIcon(new ImageIcon(img_p.getPath()));

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error en la seleccion de imagen\n " + e.getMessage());
            }

        }

    }


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNombreC = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        cbEdad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel1.setText("Nombre Completo");

        txtNombreC.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        txtNombreC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreCMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel2.setText("Edad");

        jLabel3.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel3.setText("Genero");

        cbGenero.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));

        jLabel4.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel4.setText("Usuario");

        txtUsuario.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        txtContraseña.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jLabel5.setText("Contraseña");

        btnCrear.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(255, 153, 153));
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        cbEdad.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        if (validarCampos()) {

            try {
                if (!Func_usuario.existeUsuario(txtUsuario.getText(), f_usuarios)) {

                    f_usuarios.seek(f_usuarios.length());
                    
                    f_usuarios.writeUTF(txtUsuario.getText());
                    f_usuarios.writeUTF(txtContraseña.getText());
                    f_usuarios.writeUTF(txtNombreC.getText());

                    String g = cbGenero.getSelectedItem().toString();
                    f_usuarios.writeChar(g.toLowerCase().charAt(0));

                    String e = cbEdad.getSelectedItem().toString();
                    int n = Integer.parseInt(e);
                    f_usuarios.writeInt(n);

                    Calendar hoy = Calendar.getInstance();

                    f_usuarios.writeLong(hoy.getTimeInMillis());
                    f_usuarios.writeBoolean(true);

                    if(img_p == null)
                        img_p = img_default;
                    BufferedImage pe = ImageIO.read(img_p);

                    // Convertir la imagen en un array de bytes
                    
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    
                    String ext = "";
                    
                    int i = img_p.getName().lastIndexOf('.');
                    if(i > 0){
                        ext = img_p.getName().substring(i+1);
                    }

                    if (ext.equals("png")) {
                        ImageIO.write(pe, "png", baos);
                    }
                    
                    if(ext.equals("jpg"))
                        ImageIO.write(pe, "jpg", baos);

                    byte[] bytes = baos.toByteArray();

                    // Escribir el tamaño de la imagen para que su lectura sea
                    // mas rapida y eficiente en el archivo
                    
                    f_usuarios.writeInt(bytes.length);
                    
                    //ahora escribe los bytes de la imagen;
                    f_usuarios.write(bytes);

                    File ruta_usuario = new File("RedRetro/" + txtUsuario.getText());
                    ruta_usuario.mkdir();

                    File following = new File(ruta_usuario.getPath() + "/followings.twc");
                    File followers = new File(ruta_usuario.getPath() + "/followers.twc");
                    File twits = new File(ruta_usuario.getPath() + "/twits.twc");

                    following.createNewFile();
                    followers.createNewFile();
                    twits.createNewFile();

                    f_usuarios.close();
                    
                    if(!img_p.equals(img_default))
                        img_p.delete();
                    
                    JOptionPane.showMessageDialog(this, "Usuario Creado correctamente");
                    
                    this.dispose();

                    
                } else {
                    JOptionPane.showMessageDialog(this, "El usuario ya existe");
                    if(!img_p.equals(img_default))
                        img_p.delete();
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                    txtNombreC.setText("");
                    cbGenero.setSelectedIndex(0);
                    cbEdad.setSelectedIndex(0);

                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        }

    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNombreCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreCMouseClicked

    }//GEN-LAST:event_txtNombreCMouseClicked

    
   
    
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbEdad;
    private javax.swing.JComboBox<String> cbGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtNombreC;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
