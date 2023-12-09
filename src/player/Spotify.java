/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package player;

import java.io.*;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.swing.Timer;
import jaco.mp3.player.MP3Player;
import javax.swing.ImageIcon;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
/**
 *
 * @author aleja
 */
public class Spotify extends javax.swing.JInternalFrame {
   private FileSystemView fileSystemView;
    public Playlist pl=new Playlist();
    ArrayList updateList= new ArrayList();
    private MP3Player mp3Player;
    private long startTime;
    File simpan;
    Timer timer;
    
    
    
    public Spotify() {      
        initComponents();
         mp3Player = new MP3Player();
//        this.setLocationRelativeTo(null);
        timer = new Timer(1000, e -> updateSlider()); // Actualiza cada segundo
        timer.setInitialDelay(0);
        TimerSlider.setEnabled(false);
        
        VolumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                VolumeSliderChanged(evt);
            }
        });
       
         TimerSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateSlider();
            }
        });
        checkSongTerminada();
        
    }
    
     private void VolumeSliderChanged(javax.swing.event.ChangeEvent evt) {
        float newVolume = VolumeSlider.getValue() / 100.0f;
        adjustSystemVolume(newVolume);
    }
    

    private void adjustSystemVolume(float newVolume) {
        try {
            Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
            for (Mixer.Info info : mixerInfo) {
                Mixer mixer = AudioSystem.getMixer(info);
                if (mixer.isLineSupported(Port.Info.SPEAKER)) {
                    Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
                    port.open();

                    if (port.isControlSupported(FloatControl.Type.VOLUME)) {
                        FloatControl volumeControl = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
                        volumeControl.setValue(newVolume);
                    }

                    port.close();
                }
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
  
        private void updateSlider() {
        if (mp3Player != null) {
            if (mp3Player != null) {
            long currentTime = System.currentTimeMillis();
            long elapsedTimeInMillis = currentTime - startTime;
            int elapsedTimeInSeconds = (int) (elapsedTimeInMillis / 1000);
            int minutes = elapsedTimeInSeconds / 60;
            int seconds = elapsedTimeInSeconds % 60;
            String elapsedTimeString = String.format("%02d:%02d", minutes, seconds);
            minutos.setText(elapsedTimeString);
            TimerSlider.setValue(elapsedTimeInSeconds);
        }
        }
    }
     
    public void updateList(){
    
        updateList=pl.getListSong();
        DefaultListModel model=new DefaultListModel();
        
        for(int i=0;i<updateList.size();i++)
            {
                int j=1+1;
                model.add(i,j + " | " + ((File) updateList.get(i)).getName());
                
                
            }
        jPlaylist.setModel(model);
           
    }
    
    
    public void add(){
    
    java.awt.Container container = this.getParent();
    while (!(container instanceof javax.swing.JFrame) && container != null) {
        container = container.getParent();
    }

    
    if (container instanceof javax.swing.JFrame) {
        pl.add((javax.swing.JFrame) container);
        updateList();
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
      public static int a=0;
      
    public void putar() {
        if (a == 0) {
            try {
                int p1 = jPlaylist.getSelectedIndex();
                play1 = (File) this.updateList.get(p1);
                mp3Player = new MP3Player(play1.toURI().toURL()); 

                fileSystemView = FileSystemView.getFileSystemView();
                ImageIcon fileIcon = (ImageIcon) fileSystemView.getSystemIcon(play1);
                imagendecancion.setIcon(fileIcon);
                


                new Thread(() -> {
                    try {
                        mp3Player.play();
                        startTime = System.currentTimeMillis();
                        timer.start();
                         a = 1;
                         
                        while (mp3Player != null && !mp3Player.isStopped()) {
                        Thread.sleep(100);
                        }
                        timer.stop();
                        minutos.setText("00:00");
                        TimerSlider.setValue(0);
                        TimerSlider.setEnabled(false);
                        a = 0;

                         
                         
                    } catch (Exception e) {
                        System.out.println("Error playing music");
                        JOptionPane.showMessageDialog(null, "select a song from file", null, JOptionPane.ERROR_MESSAGE);

                    }
                }).start();
                


            } catch (Exception e) {
                System.out.println("Problem playing music");              
                JOptionPane.showMessageDialog(null, "select a song from playlist", null, JOptionPane.INFORMATION_MESSAGE);


            }

            } else {


                if (mp3Player.isPaused()) {
                    System.out.println("play");
                    mp3Player.play();
                    timer.start();          
                } else {
                    System.out.println("pausada");
                    mp3Player.pause();          
                    timer.stop();
                }
            }
        }
    
    private void checkSongTerminada() {
        if (mp3Player != null && mp3Player.isStopped()|| this.isClosed) {
            timer.stop();
            minutos.setText("00:00");
            TimerSlider.setValue(0);
            TimerSlider.setEnabled(false); 
            a = 0;
        }
    }

      File sa;
    void next() {
    if (a == 0) {
        try {
            int s1 = jPlaylist.getSelectedIndex() + 1;
            sa = (File) this.pl.ls.get(s1);
            mp3Player = new MP3Player(sa.toURI().toURL()); // Use the File's URL
            a = 1;
            jPlaylist.setSelectedIndex(s1);
        } catch (Exception e) {
            System.out.println("Problem playing music");
            System.out.println(e);
        }

        new Thread(() -> {
            try {
                mp3Player.play();
            } catch (Exception e) {
                System.out.println("Error playing music");
                System.out.println(e);
            }
        }).start();
    } else {
        mp3Player.stop();  
        a = 0;
        next();
    }
}

      
    void previous() {
    if (a == 0) {
        try {
            int s1 = jPlaylist.getSelectedIndex() - 1;
            sa = (File) this.pl.ls.get(s1);
            mp3Player = new MP3Player(sa.toURI().toURL()); 
            a = 1;
            jPlaylist.setSelectedIndex(s1);
        } catch (Exception e) {
            System.out.println("Problem playing music");
            System.out.println(e);
        }

        new Thread(() -> {
            try {
                mp3Player.play();
            } catch (Exception e) {
                System.out.println("Error playing music");
                System.out.println(e);
            }
        }).start();
        } else {
            if (mp3Player != null) {
                mp3Player.stop();  
            }
            a = 0;
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
        btnremove = new javax.swing.JButton();
        btndown = new javax.swing.JButton();
        btnremove1 = new javax.swing.JButton();
        TimerSlider = new javax.swing.JSlider();
        minutos = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        VolumeSlider = new javax.swing.JSlider();
        jButton3 = new javax.swing.JButton();
        btnprevius = new javax.swing.JButton();
        btnplay = new javax.swing.JButton();
        btnnext = new javax.swing.JButton();
        btnstop = new javax.swing.JButton();
        imagendecancion = new javax.swing.JLabel();

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

        TimerSlider.setMaximum(250);
        TimerSlider.setValue(0);

        minutos.setText("00:00");

        jButton2.setText("+");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        VolumeSlider.setMaximum(150);

        jButton3.setText("-");
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnprevius.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_skip-previous_326509.png"))); // NOI18N
        btnprevius.setBorderPainted(false);
        btnprevius.setContentAreaFilled(false);
        btnprevius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpreviusActionPerformed(evt);
            }
        });

        btnplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_play-arrow_326577.png"))); // NOI18N
        btnplay.setBorderPainted(false);
        btnplay.setContentAreaFilled(false);
        btnplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplayActionPerformed(evt);
            }
        });

        btnnext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_next_293690.png"))); // NOI18N
        btnnext.setBorderPainted(false);
        btnnext.setContentAreaFilled(false);
        btnnext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnextActionPerformed(evt);
            }
        });

        btnstop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconfinder_media-stop_216325.png"))); // NOI18N
        btnstop.setBorderPainted(false);
        btnstop.setContentAreaFilled(false);
        btnstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnstopActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnremove1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btndown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnremove)
                                .addGap(28, 28, 28)
                                .addComponent(imagendecancion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(266, 266, 266)
                                .addComponent(btnopen)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnsave))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TimerSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(VolumeSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(minutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btnprevius, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnplay, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnremove1)
                        .addComponent(btnopen)
                        .addComponent(btnremove)
                        .addComponent(btnsave))
                    .addComponent(imagendecancion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btndown))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TimerSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minutos))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(VolumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnprevius, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnext, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnstop, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        System.out.println("iba una funcion pero no la Use :no funciono");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.out.println("iba una funcion pero no la Use :no funciono");
    }//GEN-LAST:event_jButton3ActionPerformed

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
        if (mp3Player != null) {
            mp3Player.stop(); // Close the MP3 player using jaco.mp3.player.MP3Player
            mp3Player = null; // Set the player to null after closing
            a = 0;
        }

    }//GEN-LAST:event_btnstopActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider TimerSlider;
    private javax.swing.JSlider VolumeSlider;
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
    private javax.swing.JLabel imagendecancion;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JList<String> jPlaylist;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel minutos;
    // End of variables declaration//GEN-END:variables
}
