/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RedSocial;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;


public class LabelPerfil extends JLabel{
      private Shape shape;
      private int radio;
    
    public LabelPerfil(int radio) {
        setOpaque(false);
        this.radio = radio;
    }

    @Override
    protected void paintComponent(Graphics g) {

        int w = getWidth();
        int h = getHeight();

        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Double(0, 0, w, h, radio, radio); // 100 es el radio de los bordes
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setClip(shape);
        super.paintComponent(g);
    }

    @Override
    public boolean contains(int x, int y) {
        Shape shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radio, radio);
        return shape.contains(x, y);
    }
}
