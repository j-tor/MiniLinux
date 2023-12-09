package Twitter;
import java.awt.Image;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanf
 */
public class UsuarioT {

    String Nusuario;
    char genero;
    String username;
    String password;
    Date fechaEntradaSistema;
    int edad;
    boolean cuentaActiva;
    

    public UsuarioT() {
    }

    public UsuarioT(String Nusuario, char genero, String username, String password, Date fechaEntradaSistema, int edad, boolean cuentaActiva) {
        this.Nusuario = Nusuario;
        this.genero = genero;
        this.username = username;
        this.password = password;
        this.fechaEntradaSistema = fechaEntradaSistema;
        this.edad = edad;
        this.cuentaActiva = cuentaActiva;
        
    }

    public String getNusuario() {
        return Nusuario;
    }

    public void setNusuario(String Nusuario) {
        this.Nusuario = Nusuario;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaEntradaSistema() {
        return fechaEntradaSistema;
    }

    public void setFechaEntradaSistema(Date fechaEntradaSistema) {
        this.fechaEntradaSistema = fechaEntradaSistema;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isCuentaActiva() {
        return cuentaActiva;
    }

    public void setCuentaActiva(boolean cuentaActiva) {
        this.cuentaActiva = cuentaActiva;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Nusuario=" + Nusuario + ", genero=" + genero + ", username=" + username + ", password=" + password + ", fechaEntradaSistema=" + fechaEntradaSistema + ", edad=" + edad + ", cuentaActiva=" + cuentaActiva + '}';
    }


    
    

}
