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
public class MiExecepcionUsers extends Exception{
    
    Color color;

    public MiExecepcionUsers() {
        super();
    }

    public MiExecepcionUsers(Color color, String message) {
        super(message);
        this.color = color;
    }
    
    
    
}
