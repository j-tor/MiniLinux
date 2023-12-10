package Twitter;
import java.awt.Image;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanf
 */
public class UsuarioT implements Serializable{

    String Nusuario;
    char genero;
    String username;
    String password;
    Date fechaEntradaSistema;
    int edad;
    boolean cuentaActiva;
    public RandomAccessFile registro;
    private static String userlog;

    public UsuarioT() {
        try{
        File file=new File("Usertwit");
        file.mkdirs();
        registro=new RandomAccessFile("Usertwit/user.twc","rw");
        }catch(IOException e){
            System.out.println("Erros");
        }
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
    
    
    
    public boolean Existeuser(String user) throws IOException {
       try {
        registro.seek(0); 
        while (registro.getFilePointer() < registro.length()) {
            registro.readUTF();
            registro.readChar();
            String usuario = registro.readUTF();
            registro.readUTF(); 
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if (user.equals(usuario)) {
                return true;
            }
        }
         } catch (EOFException e) {
        
        System.err.println("Reached end of file while checking for user existence.");
    } catch (IOException e) {
      
        e.printStackTrace();
    }

    return false;
}

    private String carpetauser(String user){
        return"Usertwit/"+user;
    }
    private void carpetausers(String user) throws IOException{
       File file=new File(carpetauser(user));
        file.mkdirs();
        try{
            new File("Usertwit/" + user + "/following.twc").createNewFile();
            new File("Usertwit/" + user + "/followers.twc").createNewFile();
            new File("Usertwit/" + user + "/twits.twc").createNewFile();
            }catch(IOException e){
            System.out.println("No se pudo crear");
            } 
    }
     public void agguser(String nombre, String genero, String user, String contra, int edad,String fecha,boolean activa) throws IOException {
        if (!Existeuser(user)) {
                registro.seek(registro.length());
                registro.writeUTF(nombre);
                registro.writeUTF(genero);
                registro.writeUTF(user);
                registro.writeUTF(contra);
                registro.writeInt(edad);
                registro.writeUTF(fecha);
                registro.writeBoolean(activa);
                carpetausers(user);
                System.out.println("agg user");
                userlog=user;
        }
    }
     
     public  boolean iniciosesion(String user, String contra) throws IOException{
         while(registro.getFilePointer() < registro.length()){
            registro.readUTF();
            registro.readChar();
            String usuario=registro.readUTF();
            String password=registro.readUTF();
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if(user.equals(usuario.trim()) && contra.equals(password.trim())){
                userlog=user;
                return true;
            }
                 
         }
           
         return false;
     }
     
     
      public String getUserlog() {
        return userlog;
    }
    public boolean desaccuenta()throws IOException{
       while(registro.getFilePointer() < registro.length()){
            registro.readUTF();
            registro.readChar();
            String usuario=registro.readUTF();
            registro.readUTF();
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if(usuario.equals(userlog)){
                registro.writeBoolean(false);
                return true;
            }
                 
         } 
       return false;
    }
    
    
    
    
    public String[] obtenerUsuarios() throws IOException{
        ArrayList<String> usuarios = new ArrayList<>();
        registro.seek(0);

        while (registro.getFilePointer() < registro.length()){
            registro.readUTF(); 
            registro.readChar(); 
            String usuario = registro.readUTF(); 
            registro.readUTF(); 
            registro.readInt(); 
            registro.readUTF(); 
            registro.readBoolean(); 
            usuarios.add(usuario);
        }
        String[] usuariosArray = new String[usuarios.size()];
        usuariosArray = usuarios.toArray(usuariosArray);
        return usuariosArray;
    }

    public void imprimirUsuarios() {
    try {
        registro.seek(0);

        while (registro.getFilePointer() < registro.length()) {
            try {
                String nombre = registro.readUTF();
                char genero = registro.readChar();
                String usuario = registro.readUTF();
                String contra = registro.readUTF();
                int edad = registro.readInt();
                String fecha = registro.readUTF();
                boolean activa = registro.readBoolean();

                System.out.println("Nombre: " + nombre);
                System.out.println("Género: " + genero);
                System.out.println("Usuario: " + usuario);
                System.out.println("Contraseña: " + contra);
                System.out.println("Edad: " + edad);
                System.out.println("Fecha: " + fecha);
                System.out.println("Cuenta activa: " + activa);
                System.out.println("-----------");
            } catch (EOFException e) {
                // Fin del archivo, salir del bucle
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
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
