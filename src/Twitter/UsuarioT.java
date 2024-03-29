package Twitter;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author juanf
 */
public class UsuarioT implements Serializable {

    String Nusuario;
    char genero;
    String username;
    String password;
    Date fechaEntradaSistema;
    int edad;
    boolean cuentaActiva;
    public RandomAccessFile registro;
    private static String userlog;
    public static String actualuser;

    public UsuarioT() {
        try {
            File file = new File("Usertwit");
            file.mkdirs();

            registro = new RandomAccessFile("Usertwit/user.twc", "rw");
        } catch (IOException e) {
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

    public String retornoU() {
        try {
//            registro.seek(0);
            while (registro.getFilePointer() < registro.length()) {
                registro.readUTF();
                registro.readUTF();
                String usuario = registro.readUTF();
                String contra = registro.readUTF();
                registro.readInt();
                registro.readUTF();
                registro.readBoolean();
                return usuario;
            }
        } catch (EOFException e) {

            System.err.println("Reached end of file while checking for user existence.");
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }

    public String retornoC() {
        try {
//            registro.seek(0);
            while (registro.getFilePointer() < registro.length()) {
                registro.readUTF();
                registro.readUTF();
                String usuario = registro.readUTF();
                String contra = registro.readUTF();
                registro.readInt();
                registro.readUTF();
                registro.readBoolean();
                return contra;
            }
        } catch (EOFException e) {

            System.err.println("Reached end of file while checking for user existence.");
        } catch (IOException e) {

            e.printStackTrace();
        }
        return "";
    }

    
      public void publicarT(String mensaje) {
        try {
            RandomAccessFile registro = new RandomAccessFile("Usertwit/" + actualuser + "/twits.twc", "rw");
            registro.seek(registro.length());
//            registro.readUTF();
            registro.writeUTF(mensaje);
            System.out.println("el usuario que publico el twitt"+actualuser);

        } catch (IOException e) {
            System.out.println("Erros");
        }
    }

    
    public void Settings(){
        
    }
    
    public void TimeLine(){
        
    }
    
    public String displayH(String clave) {

        String rutaCarpeta = "Usertwit/";

        File carpeta = new File(rutaCarpeta);
        String contF = "";

        if (carpeta.isDirectory()) {

            File[] archivos = carpeta.listFiles();

            if (archivos != null) {

                for (File archivo : archivos) {

                    if (!archivo.getName().equals("user.twc")) {

                        try (RandomAccessFile raf = new RandomAccessFile(rutaCarpeta+actualuser + "/twits.twc", "rw")) {
                            String contenidoStr = raf.readUTF();
                            
                            if (contenidoStr.contains(clave)) {
                                contF = contF + "\n" + contenidoStr;
                            }

                        } catch (EOFException e) {

                            System.out.println("lee todo el archivo");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                return contF;
            } else {
                System.out.println("La carpeta está vacía o no se pueden listar los archivos");
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta o no existe");
        }
        return "";
    }
    
    

    public String imprimirTwitsAuser() {
        StringBuilder sb = new StringBuilder();

        try {

            String carpetaUsuario = carpetauser(actualuser);
            String rutaArchivo = carpetaUsuario + "/twits.twc";

   
            if (new File(rutaArchivo).exists()) {
 
                try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        sb.append("Usuario:"+actualuser+"\n");
                        sb.append(linea).append("\n");
                    }
                }
            } else {
                sb.append("No hay twits para mostrar.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.append("Error al leer los twits.");
        }

        return sb.toString();
    }

     public String displayI(String clave) {

        String rutaCarpeta = "Usertwit";

        File carpeta = new File(rutaCarpeta);
        String contF = "";

        if (carpeta.isDirectory()) {

            File[] archivos = carpeta.listFiles();

            if (archivos != null) {

                for (File archivo : archivos) {

                    if (!archivo.getName().equals("user.twc")) {

                        
                        try (RandomAccessFile raf = new RandomAccessFile(rutaCarpeta+actualuser + "/twits.twc", "rw")) {
                            String contenidoStr = raf.readUTF();
                            
                            if (contenidoStr.contains(clave)) {
                                contF = contF + "\n" + contenidoStr;
                            }


                        }catch (EOFException e) {

                            System.out.println("lee todo el archivo");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                    }
                }
                
                return contF;
            } else {
                System.out.println("La carpeta está vacía o no se pueden listar los archivos");
            }
        } else {
            System.out.println("La ruta especificada no es una carpeta o no existe");
        }
        return "";
    }
    
    
    
    
    public boolean Existeuser(String user) throws IOException {
        try {
            registro.seek(0);
            while (registro.getFilePointer() < registro.length()) {
                registro.readUTF();
                registro.readUTF();
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

    private String carpetauser(String user) {
        return "Usertwit/" + user;
    }

    private void carpetausers(String user) throws IOException {
        File file = new File(carpetauser(user));
        file.mkdirs();
        try {
            new File("Usertwit/" + user + "/following.twc").createNewFile();
            new File("Usertwit/" + user + "/followers.twc").createNewFile();
            new File("Usertwit/" + user + "/twits.twc").createNewFile();
        } catch (IOException e) {
            System.out.println("No se pudo crear");
        }
    }

    public void agguser(String nombre, String genero, String user, String contra, int edad, String fecha, boolean activa) throws IOException {
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
            userlog = user;
        }
    }

    public boolean iniciosesion(String user, String contra) throws IOException {
        while (registro.getFilePointer() < registro.length()) {
            registro.readUTF();
            registro.readUTF();
            String usuario = registro.readUTF();
            String password = registro.readUTF();
            actualuser=usuario;
            System.out.println("actula"+actualuser);
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if (user.equals(usuario.trim()) && contra.equals(password.trim())) {
                userlog = user;
                return true;
            }

        }

        return false;
    }

    public String getUserlog() {
        return userlog;
    }

    public boolean desaccuenta() throws IOException {
        while (registro.getFilePointer() < registro.length()) {
            registro.readUTF();
            registro.readChar();
            String usuario = registro.readUTF();
            registro.readUTF();
            registro.readInt();
            registro.readUTF();
            registro.readBoolean();
            if (usuario.equals(userlog)) {
                registro.writeBoolean(false);
                return true;
            }

        }
        return false;
    }

    public String[] obtenerUsuarios() throws IOException {
        ArrayList<String> usuarios = new ArrayList<>();
        registro.seek(0);

        while (registro.getFilePointer() < registro.length()) {
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
