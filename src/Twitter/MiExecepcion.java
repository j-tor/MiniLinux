/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Twitter;

import java.awt.Color;

/**
 *
 * @author juanf
 */
public class MiExecepcion extends Exception{
    
    Color color;

    public MiExecepcion() {
        super();
    }

    public MiExecepcion(Color color, String message) {
        super(message);
        this.color = color;
    }
    
    
    
}
