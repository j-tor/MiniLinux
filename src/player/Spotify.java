/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author aleja
 */
public class Spotify extends javax.swing.JInternalFrame {
    Playlist pl=new Playlist();
    
    ArrayList updateList= new ArrayList();
    Timer timer;
    javazoom.jl.player.Player player;
    File simpan;
    
    
    
    public Spotify() {
        initComponents();
         this.setLocation(50, 50); 
        timer = new Timer(10000, e -> updateSlider()); // Actualiza cada segundo
        timer.setInitialDelay(0);
    }
    
    void updateSlider() {
        if (player != null) {
            int currentPosition = (int) (player.getPosition() / 10000); // Posición en segundos
            jSlider1.setValue(currentPosition);
        }
    }
    
    void updateList(){
    
        updateList=pl.getListSong();
        DefaultListModel model=new DefaultListModel();
        
        for(int i=0;i<updateList.size();i++)
            {
                int j=1+1;
                model.add(i,j + " | " + ((File) updateList.get(i)).getName());
                
                
            }
        jPlaylist.setModel(model);
           
    }
    
    
   void add() {
    // Obtén el JFrame principal que contiene la ventana interna
    java.awt.Container container = this.getParent();
    while (!(container instanceof javax.swing.JFrame) && container != null) {
        container = container.getParent();
    }

    
    if (container instanceof javax.swing.JFrame) {
        pl.add((javax.swing.JFrame) container);
        updateList();
    } else {
        System.out.println("No se pudo encontrar un JFrame principal.");
    }
}

    
    void remove(){
    
        try {
              int memoherdez=jPlaylist.getLeadSelectionIndex();
              pl.ls.remove(memoherdez);
              updateList();
            
        } catch (Exception e) {
        }
        
    }

     void up(){
    
         try {
                int s1=jPlaylist.getLeadSelectionIndex();
                simpan=(File)pl.ls.get(s1);
                pl.ls.remove(s1);
                pl.ls.add(s1-1,simpan);
                updateList();
                jPlaylist.setSelectedIndex(s1-1);
             
         } catch (Exception e) {
         }
        
    }
     
      void down(){
          
          try {
                int s1=jPlaylist.getLeadSelectionIndex();
                simpan=(File)pl.ls.get(s1);
                pl.ls.remove(s1);
                pl.ls.add(s1+1,simpan);
                updateList();
                jPlaylist.setSelectedIndex(s1+1);
             
         } catch (Exception e) {
         }
        
    }
      
      void open(){
          
           java.awt.Container container = this.getParent();
            while (!(container instanceof javax.swing.JFrame) && container != null) {
                container = container.getParent();
            }

            // Verifica si encontró un JFrame antes de agregar la ventana interna
            if (container instanceof javax.swing.JFrame) {

                updateList();
                pl.openPls((javax.swing.JFrame) container);
               updateList();
            } else {
                System.out.println("No se pudo encontrar un JFrame principal.");
            }
    
    
    }
      
      void save(){
          
          java.awt.Container container = this.getParent();
            while (!(container instanceof javax.swing.JFrame) && container != null) {
                container = container.getParent();
            }

           
            if (container instanceof javax.swing.JFrame) {

                updateList();
                pl.saveAsPlaylist((javax.swing.JFrame) container);
               updateList();
            } else {
                System.out.println("No se pudo encontrar un JFrame principal.");
            }
    
       
    }
      
      File play1;
      static int a=0;
      
       void putar() {
        if (a == 0) {
            try {
                int p1 = jPlaylist.getSelectedIndex();
                play1 = (File) this.updateList.get(p1);
                FileInputStream fis = new FileInputStream(play1);
                BufferedInputStream bis = new BufferedInputStream(fis);
                player = new javazoom.jl.player.Player(bis);
                a = 1;

                // Inicia el temporizador al iniciar la reproducción
                timer.start();
            } catch (Exception e) {
                System.out.println("Problema al tocar música");
                System.out.println(e);
            }

            new Thread() {
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (Exception e) {
                        // Manejo de excepciones, si es necesario
                    }
                }
            }.start();
        } else {
            player.close();
            a = 0;
            // Detiene el temporizador al detener la reproducción
            timer.stop();
        }
    }


      
      File sa;
      void next(){
      
      if(a==0)
             {
                 try {
                        int s1=jPlaylist.getSelectedIndex()+1;
                        sa=(File)this.pl.ls.get(s1);
                        FileInputStream fis=new FileInputStream(sa);
                        BufferedInputStream bis=new BufferedInputStream(fis);
                        player=new javazoom.jl.player.Player(bis);
                        a=1;
                        jPlaylist.setSelectedIndex(s1);
                     
                 } catch (Exception e) {
                     System.out.println("Problema al tocar musica");
                     System.out.println(e);
                 }
                 
                 new Thread()
                     {
                         @Override
                         public void run(){
                         
                             try {
                                   player.play();
                                 
                             } catch (Exception e) {
                                 
                             }
                         }
                  
                     }.start();
                 
             }
          else
              {
                 player.close();
                 a=0;
                 next();
                  
              }    
          
      }
      
      void previous(){
      
             if(a==0)
             {
                 try {
                        int s1=jPlaylist.getSelectedIndex()-1;
                        sa=(File)this.pl.ls.get(s1);
                        FileInputStream fis=new FileInputStream(sa);
                        BufferedInputStream bis=new BufferedInputStream(fis);
                        player=new javazoom.jl.player.Player(bis);
                        a=1;
                        jPlaylist.setSelectedIndex(s1);
                     
                 } catch (Exception e) {
                     System.out.println("Problema al tocar musica");
                     System.out.println(e);
                 }
                 
                 new Thread()
                     {
                         @Override
                         public void run(){
                         
                             try {
                                   player.play();
                                 
                             } catch (Exception e) {
                                 
                             }
                         }
                  
                     }.start();
                 
             }
          else
              {
                 player.close();
                 a=0;
                 previous();
                  
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

        btnsave = new javax.swing.JButton();
        btnup = new javax.swing.JButton();
        btnopen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPlaylist = new javax.swing.JList<>();
        btnprevius = new javax.swing.JButton();
        btnplay = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        btndown = new javax.swing.JButton();
        btnremove1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_save_326688.png"))); // NOI18N
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_arrow-up-thick_216098.png"))); // NOI18N
        btnup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupActionPerformed(evt);
            }
        });

        btnopen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_folder-open_1608888.png"))); // NOI18N
        btnopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopenActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jPlaylist);

        btnprevius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_skip-previous_326509.png"))); // NOI18N
        btnprevius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviusActionPerformed(evt);
            }
        });

        btnplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_play-arrow_326577.png"))); // NOI18N
        btnplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplayActionPerformed(evt);
            }
        });

        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_next_293690.png"))); // NOI18N
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnstop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_media-stop_216325.png"))); // NOI18N
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        btnremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_remove-rounded_383082.png"))); // NOI18N
        btnremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveActionPerformed(evt);
            }
        });

        btndown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_145_111066.png"))); // NOI18N
        btndown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndownActionPerformed(evt);
            }
        });

        btnremove1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_add_126583.png"))); // NOI18N
        btnremove1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremove1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnremove1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btndown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnremove)
                        .addGap(334, 334, 334)
                        .addComponent(btnopen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnsave))
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnprevius, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnplay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(btnnext)
                .addGap(18, 18, 18)
                .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnremove1)
                    .addComponent(btnopen)
                    .addComponent(btnremove)
                    .addComponent(btnsave))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndown)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnstop))
                    .addComponent(btnprevius, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        
        save();
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupActionPerformed
        up();
    }//GEN-LAST:event_btnupActionPerformed

    private void btnopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopenActionPerformed
        open();
    }//GEN-LAST:event_btnopenActionPerformed

    private void btnpreviusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpreviusActionPerformed
        previous();
    }//GEN-LAST:event_btnpreviusActionPerformed

    private void btnplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplayActionPerformed
        putar();
    }//GEN-LAST:event_btnplayActionPerformed

    private void btnnextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnextActionPerformed
        next();
    }//GEN-LAST:event_btnnextActionPerformed

    private void btnstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnstopActionPerformed
        player.close();

    }//GEN-LAST:event_btnstopActionPerformed

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
        remove();
    }//GEN-LAST:event_btnremoveActionPerformed

    private void btndownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndownActionPerformed
        // TODO add your handling code here:
         down();
    }//GEN-LAST:event_btndownActionPerformed

    private void btnremove1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremove1ActionPerformed
        // TODO add your handling code here:
        add();
    }//GEN-LAST:event_btnremove1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndown;
    private javax.swing.JButton btnnext;
    private javax.swing.JButton btnopen;
    private javax.swing.JButton btnplay;
    private javax.swing.JButton btnprevius;
    private javax.swing.JButton btnremove;
    private javax.swing.JButton btnremove1;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnstop;
    private javax.swing.JButton btnup;
    private javax.swing.JList<String> jPlaylist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
