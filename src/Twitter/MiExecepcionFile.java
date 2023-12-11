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
public class MiExecepcionFile extends Exception{
    
    Color color;

    public MiExecepcionFile() {
        super();
    }

    public MiExecepcionFile(Color color, String message) {
        super(message);
        this.color = color;
    }
    
    
    
}
