package texteditor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.BufferedWriter;

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

public class editor extends javax.swing.JFrame {
 boolean subpapplay;
 boolean fondoapply;
 boolean negritaapply;
 boolean italicapply;
    public editor() {
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
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fuente = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel = new javax.swing.JTextPane();
        color = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        sub = new javax.swing.JButton();
        tamaño = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        bold = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        abrirArchivo = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        crearArchivo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Editor de texto");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

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

        jLabel3.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Color:");

        jScrollPane2.setViewportView(panel);

        color.setBackground(new java.awt.Color(0, 0, 0));
        color.setForeground(new java.awt.Color(255, 255, 255));
        color.setText("A");
        color.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colorMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("Subrayar:");

        sub.setBackground(new java.awt.Color(255, 255, 255));
        sub.setForeground(new java.awt.Color(0, 0, 0));
        sub.setText("Sub");
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

        jLabel6.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Fondo:");

        jButton1.setBackground(new java.awt.Color(255, 255, 0));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("...");
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

        jLabel8.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Negrita:");

        bold.setBackground(new java.awt.Color(255, 255, 255));
        bold.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bold.setForeground(new java.awt.Color(0, 0, 0));
        bold.setText("B");
        bold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boldMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Italic:");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("I");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        abrirArchivo.setBackground(new java.awt.Color(255, 255, 255));
        abrirArchivo.setForeground(new java.awt.Color(0, 0, 0));
        abrirArchivo.setText("Abrir archivo");
        abrirArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirArchivoMouseClicked(evt);
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
        jButton4.setText("Copiar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Paste");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(0, 51, 153));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Editor de Texto");
        jLabel12.setOpaque(true);

        crearArchivo.setBackground(new java.awt.Color(255, 255, 255));
        crearArchivo.setForeground(new java.awt.Color(0, 0, 0));
        crearArchivo.setText("Guardar");
        crearArchivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                crearArchivoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fuente, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(abrirArchivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(crearArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bold)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sub, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tamaño, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(color)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {bold, jButton1, jButton2, sub});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {fuente, tamaño});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {abrirArchivo, crearArchivo});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton4, jButton5});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fuente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jButton3)
                                    .addComponent(jButton5)
                                    .addComponent(jButton4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(color)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(crearArchivo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(abrirArchivo))))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub)
                    .addComponent(jLabel4)
                    .addComponent(bold)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(jLabel10)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {bold, jButton1, jButton2, sub});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {fuente, tamaño});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {abrirArchivo, crearArchivo, jButton3, jButton4, jButton5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        panel.paste();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        panel.copy();
        panel.removeAll();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        panel.cut();
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
                StyleConstants.setBackground(estilo, Color.YELLOW);
                doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);

            }
        } catch (Exception ex) {
            System.out.println("ups");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void tamañoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamañoActionPerformed
        StyleConstants.setFontSize(estilo, Integer.parseInt(tamaño.getSelectedItem().toString()));
        doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
    }//GEN-LAST:event_tamañoActionPerformed

    private void subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subActionPerformed

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

    private void colorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorMouseClicked
        try {
            StyleConstants.setForeground(estilo, JColorChooser.showDialog(this, "Elija color", Color.yellow));

            doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
        } catch (Exception ex) {
            System.out.println("error en color");
        }
    }//GEN-LAST:event_colorMouseClicked

    private void fuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fuenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fuenteActionPerformed

    private void fuenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fuenteMouseClicked

    }//GEN-LAST:event_fuenteMouseClicked

    private void fuenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_fuenteItemStateChanged
        StyleConstants.setFontFamily(estilo, fuente.getSelectedItem().toString());
        doc.setCharacterAttributes(panel.getSelectionStart(), panel.getSelectionEnd() - panel.getSelectionStart(), panel.getStyle("n"), true);
    }//GEN-LAST:event_fuenteItemStateChanged

    /**
     * @param args the command line arguments
     */
 
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane panel;
    private javax.swing.JButton sub;
    private javax.swing.JComboBox<String> tamaño;
    // End of variables declaration//GEN-END:variables

}
