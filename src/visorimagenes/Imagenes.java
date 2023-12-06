package visorimagenes;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.File;
import javax.swing.Icon;
import javax.swing.JLabel;

public class Imagenes extends javax.swing.JInternalFrame {
    private File[] imageFiles;
    private int currentIndex;
    private Icon myimage;

    public Imagenes() {
        initComponents();
        this.setTitle("Mis Imagenes");
        
//        File imageFolder = new File("Z/" + MenuPrincipal.nombreIngresado+"/Mis Imagenes");
         File imageFolder = new File("C:/Users/aleja/OneDrive - Universidad Tecnologica Centroamericana/Universidad/Progra-2/Linux/src/Imagenes");


        imageFiles = imageFolder.listFiles();

        ImageIcon initialIcon = new ImageIcon(imageFiles[currentIndex].getPath());
        currentIndex = 0;



    }
    
    public void setImageFile(File file) {
        
         
             
            if (file != null && file.isFile()) {
            // Assuming you have a JLabel named imageLabel to display the image
            ImageIcon icon = new ImageIcon(file.getAbsolutePath());            
            Image originalImage = icon.getImage();

            // Obtener el tamaño del JLabel
            int labelWidth = jLabel1.getWidth();
            int labelHeight = jLabel1.getHeight();

            // Escalar la imagen al tamaño del JLabel
            Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

            // Crear un nuevo ImageIcon con la imagen escalada
            ImageIcon newIcon = new ImageIcon(scaledImage);

            // Establecer el nuevo ImageIcon en el JLabel
            jLabel1.setIcon(newIcon);
            
        } else {
            // Handle the case where the file is not valid or not an image
            System.out.println("Invalid or non-image file: " + file);
        }
        
         
    
        
        
    }
    
//    public ImageIcon secticon(String m, byte[] image) {
//        if (m != null) {
//            myimage = new ImageIcon(m);
//
//        } else {
//            myimage = new ImageIcon(image);
//        }
//        Image img1 = myimage.getImage();
//        Image img2 = img1.getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon i = new ImageIcon(img2);
//        return i;
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 102, 102));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Mis Imagenes");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_skip-previous_326509.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_next_293690.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(199, 199, 199)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jButton2)))
                        .addGap(0, 201, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = imageFiles.length - 1;
        }
//        ImageIcon newIcon = new ImageIcon(imageFiles[currentIndex].getPath());
//        jLabel1.setIcon(newIcon);
         // Obtener la imagen original
           // Obtener la imagen original
        ImageIcon originalIcon = new ImageIcon(imageFiles[currentIndex].getPath());
        Image originalImage = originalIcon.getImage();

        // Obtener el tamaño del JLabel
        int labelWidth = jLabel1.getWidth();
        int labelHeight = jLabel1.getHeight();

        // Escalar la imagen al tamaño del JLabel
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon con la imagen escalada
        ImageIcon newIcon = new ImageIcon(scaledImage);

        // Establecer el nuevo ImageIcon en el JLabel
        jLabel1.setIcon(newIcon);
        
       
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        currentIndex++;
//        if (currentIndex >= imageFiles.length) {
//            currentIndex = 0;
//        }
//        ImageIcon newIcon = new ImageIcon(imageFiles[currentIndex].getPath());
//        jLabel1.setIcon(newIcon);

        currentIndex++;
        if (currentIndex >= imageFiles.length) {
            currentIndex = 0;
        }

         // Obtener la imagen original
           // Obtener la imagen original
        ImageIcon originalIcon = new ImageIcon(imageFiles[currentIndex].getPath());
        Image originalImage = originalIcon.getImage();

        // Obtener el tamaño del JLabel
        int labelWidth = jLabel1.getWidth();
        int labelHeight = jLabel1.getHeight();

        // Escalar la imagen al tamaño del JLabel
        Image scaledImage = originalImage.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon con la imagen escalada
        ImageIcon newIcon = new ImageIcon(scaledImage);

        // Establecer el nuevo ImageIcon en el JLabel
        jLabel1.setIcon(newIcon);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Imagenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Imagenes().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public static javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
