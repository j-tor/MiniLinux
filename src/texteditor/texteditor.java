/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package texteditor;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author aleja
 */
public class texteditor extends javax.swing.JInternalFrame {
boolean subpapplay;
 boolean fondoapply;
 boolean negritaapply;
 boolean italicapply;
    
    public texteditor() {
        initComponents();
         initComponents();
        doc = panel.getStyledDocument();
        estilo = panel.addStyle("n", null);
        f();
    }
        private void f() {
        DefaultComboBoxModel mod = (DefaultComboBoxModel) fuente.getModel();
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String listaF[] = e.getAvailableFontFamilyNames();
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fonts.length; i++) {
            mod.addElement(fonts[i]);
        }
        fuente.setModel(mod);
    }
    StyledDocument doc;
    Style estilo ;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fuente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel = new javax.swing.JTextPane();
        color = new javax.swing.JButton();
        sub = new javax.swing.JButton();
        tamaño = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        bold = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        crearArchivo = new javax.swing.JButton();
        abrirArchivo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        fuente.setBackground(new java.awt.Color(255, 255, 255));
        fuente.setForeground(new java.awt.Color(0, 0, 0));
        fuente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                fuenteItemStateChanged(evt);
            }
        });
        fuente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fuenteMouseClicked(evt);
            }
        });
        fuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fuenteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 102));
        jLabel1.setText("Fuente:");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Tamaño:");

        jScrollPane2.setViewportView(panel);

        color.setBackground(new java.awt.Color(0, 0, 0));
        color.setForeground(new java.awt.Color(255, 255, 255));
        color.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/letra.png"))); // NOI18N
        color.setText("A");
        color.setBorderPainted(false);
        color.setContentAreaFilled(false);
        color.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colorMouseClicked(evt);
            }
        });

        sub.setBackground(new java.awt.Color(255, 255, 255));
        sub.setForeground(new java.awt.Color(0, 0, 0));
        sub.setIcon(new javax.swing.ImageIcon("C:\\Users\\aleja\\OneDrive - Universidad Tecnologica Centroamericana\\Universidad\\Progra-2\\Linux\\src\\texteditor\\Subrayado.png")); // NOI18N
        sub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                subMouseClicked(evt);
            }
        });
        sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subActionPerformed(evt);
            }
        });

        tamaño.setBackground(new java.awt.Color(255, 255, 255));
        tamaño.setForeground(new java.awt.Color(0, 0, 0));
        tamaño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "8", "10", "12", "14", "16", "18", "20", "24", "28", "30", "36", "40", "44", "48", "52", "60", "70", "80", "90", "100" }));
        tamaño.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamañoActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/fondo.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bold.setBackground(new java.awt.Color(255, 255, 255));
        bold.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bold.setForeground(new java.awt.Color(0, 0, 0));
        bold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/negrita.png"))); // NOI18N
        bold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boldMouseClicked(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/Cursiva.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/Cortar.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/copy.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/pastes.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(0, 51, 153));

        jLabel12.setBackground(new java.awt.Color(0, 51, 153));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Word3.png"))); // NOI18N
        jLabel12.setText("WORD");
        jLabel12.setOpaque(true);

        crearArchivo.setBackground(new java.awt.Color(255, 255, 255));
        crearArchivo.setForeground(new java.awt.Color(0, 0, 0));
        crearArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/save.png"))); // NOI18N
        crearArchivo.setBorderPainted(false);
        crearArchivo.setContentAreaFilled(false);
        crearArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearArchivoMouseClicked(evt);
            }
        });

        abrirArchivo.setBackground(new java.awt.Color(255, 255, 255));
        abrirArchivo.setForeground(new java.awt.Color(0, 0, 0));
        abrirArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/texteditor/filechooser.png"))); // NOI18N
        abrirArchivo.setBorderPainted(false);
        abrirArchivo.setContentAreaFilled(false);
        abrirArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crearArchivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abrirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(crearArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(abrirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fuente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(tamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bold, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 181, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bold, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(fuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(tamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fuenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fuenteItemStateChanged
        StyleConstants.setFontFamily(estilo, fuente.getSelectedItem().toString());
        doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
    }//GEN-LAST:event_fuenteItemStateChanged

    private void fuenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fuenteMouseClicked

    }//GEN-LAST:event_fuenteMouseClicked

    private void fuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fuenteActionPerformed

    private void colorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorMouseClicked
        try {
            StyleConstants.setForeground(estilo, JColorChooser.showDialog(this, "Elija color", Color.yellow));

            doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
        } catch (Exception ex) {
            System.out.println("error en color");
        }
    }//GEN-LAST:event_colorMouseClicked

    private void subMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_subMouseClicked
        try {
            if (subpapplay) {
                System.out.println("no aplicado");
                subpapplay=false;
                StyleConstants.setUnderline(estilo, false);

                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
                //            sub.setVisible(true);
                //            quitar_sub.setVisible(false);
                System.out.println("quitó subrayado");

            } else {
                subpapplay=true;
                System.out.println("aplicado");
                StyleConstants.setUnderline(estilo, true);

                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
                //            sub.setVisible(false);
                //            quitar_sub.setVisible(true);
                System.out.println("subrayó");

            }

        } catch (Exception ex) {
            System.out.println("error en subrayar");
        }
    }//GEN-LAST:event_subMouseClicked

    private void subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subActionPerformed

    private void tamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamañoActionPerformed
        StyleConstants.setFontSize(estilo, Integer.parseInt(tamaño.getSelectedItem().toString()));
        doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
    }//GEN-LAST:event_tamañoActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        try {
            

            

            if (fondoapply) {
                
                fondoapply=false;
                System.out.println("no aplicado");
                StyleConstants.setBackground(estilo, Color.white);
                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            } else {
                
                fondoapply=true;
                System.out.println("aplicado");
                StyleConstants.setBackground(estilo,
                    JColorChooser.showDialog(this,
                            "Seleccione Color", Color.yellow));
//                StyleConstants.setBackground(estilo, Color.YELLOW);
                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            }
        } catch (Exception ex) {
            System.out.println("ups");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boldMouseClicked
        try {
            if (negritaapply) {
                negritaapply=false;
                System.out.println("no aplicada");
                StyleConstants.setBold(estilo, false);

                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            } else {
                negritaapply=true;
                System.out.println("aplicada");
                StyleConstants.setBold(estilo, true);

                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            }
        } catch (Exception ex) {
            System.out.println("error bold");
        }
    }//GEN-LAST:event_boldMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try {
            if (italicapply) {
                italicapply=false;
                System.out.println("no aplicada");

                StyleConstants.setItalic(estilo, false);

                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            } else {
                italicapply=true;
                System.out.println("aplicada");
                StyleConstants.setItalic(estilo, true);
                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            }
        } catch (Exception ex) {
            System.out.println("error italic");
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void abrirArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abrirArchivoMouseClicked
        JFileChooser fac = new JFileChooser();
        fac.setCurrentDirectory(new File("."));
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("TXT files", "txt");
        fac.setFileFilter(fnef);

        int AA = fac.showOpenDialog(null);

        if (AA == JFileChooser.APPROVE_OPTION) {
            File file = new File(fac.getSelectedFile().getAbsolutePath());

            try {
                FileInputStream fis = new FileInputStream(file);
                panel.setText("");
                JOptionPane.showMessageDialog(null, "SE HA ABIERTO EL ARCHIVO");
                StyledDocument doc = panel.getStyledDocument();
                RTFEditorKit kit = new RTFEditorKit();
                kit.read(fis, doc, 0);

            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException | BadLocationException e2) {
                e2.printStackTrace();
            }
        }
    }//GEN-LAST:event_abrirArchivoMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        panel.cut();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        panel.copy();
        panel.removeAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        panel.paste();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void crearArchivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_crearArchivoMouseClicked
        if (panel.getText().isEmpty() || panel.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "NO HA INGRESADO TEXTO");
        } else {
            JFileChooser fac = new JFileChooser();
            fac.setCurrentDirectory(new File("."));

            int AA = fac.showSaveDialog(null);

            if (AA == JFileChooser.APPROVE_OPTION) {
                File file = new File(fac.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    StyledDocument doc = panel.getStyledDocument();
                    FileOutputStream fos = new FileOutputStream(file);
                    RTFEditorKit kit = new RTFEditorKit();
                    kit.write(fos, doc, 0, doc.getLength());
                    panel.setText("");
                    JOptionPane.showMessageDialog(null, "ARCHIVO GUARDADO");
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException | BadLocationException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_crearArchivoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirArchivo;
    private javax.swing.JButton bold;
    private javax.swing.JButton color;
    private javax.swing.JButton crearArchivo;
    private javax.swing.JComboBox<String> fuente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane panel;
    private javax.swing.JButton sub;
    private javax.swing.JComboBox<String> tamaño;
    // End of variables declaration//GEN-END:variables
}
