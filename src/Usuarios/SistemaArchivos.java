/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Usuarios;

/**
 *
 * @author aleja
 */
import java.io.EOFException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public class SistemaArchivos extends JFrame {

    private JTextField usuarioTextField;
    private JPasswordField passwordField;
    private Map<String, Usuario> usuarios;  // Mapa para almacenar la información de cada usuario
    public static final int LONG_SIZE = 8;  // Tamaño de un long en bytes

    public SistemaArchivos() {
        usuarios = new HashMap<>();  
        cargarUsuariosDesdeArchivo();      
        crearUsuarioAdmin();      
        setVisible(true);
    }
    
    
//    public static boolean esAdministrador(String nombreUsuario) {
////        Usuario usuario = usuarios.get(nombreUsuario);
////        return usuario != null && usuario.getTipo().equalsIgnoreCase("Administrador");
//    }

    private void login() {
        String usuario = usuarioTextField.getText();
        String contraseña = new String(passwordField.getPassword());

        if (autenticarUsuario(usuario, contraseña)) {
            abrirSistemaDeArchivos(usuario);
            abrirSistemaDeArchivos(usuario);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean autenticarUsuario(String usuario, String contraseña) {
        // Verifica si el usuario y la contraseña coinciden con la lista de usuarios
       
        if (usuarios.containsKey(usuario)) {
             System.out.println("usario autenticado");
            Usuario storedUsuario = usuarios.get(usuario);
             
            mostrarUbicacionArchivos(usuario);
            abrirSistemaDeArchivos(usuario);
            linux.Inicio.nombreIngresado=usuario;
            linux.Inicio.typoIngresado=obtenerTipoUsuario(usuario);
            
            return storedUsuario.getContraseña().equals(contraseña);
            
        }
        return false;
    }

    public static void abrirSistemaDeArchivos(String usuario) {
       
        File directorioUsuario = new File("Z/" + usuario);
        if (!directorioUsuario.exists()) {
            directorioUsuario.mkdirs(); 
            System.out.println("si existe");
            
            
        }
 
    }

       public void crearUsuario(String nuevoUsuario, String nuevaContraseña) {

         nuevoUsuario = CrearcionUsuarios.nombrenuevo.getText();
         nuevaContraseña = new String(CrearcionUsuarios.Contranueva.getPassword());
           System.out.println("contra"+nuevaContraseña);
         
        // Verifica si el usuario existe
        if (usuarios.containsKey(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Agrega el nuevo usuario a la lista y guarda en el archivo binario
           Usuario nuevoUsuarioObj = new Usuario(nuevoUsuario, nuevaContraseña);
            usuarios.put(nuevoUsuario, nuevoUsuarioObj);
            guardarUsuarioEnArchivo(nuevoUsuarioObj);
            crearCarpetasIniciales(nuevoUsuario);

            abrirSistemaDeArchivos(nuevoUsuario);
        }
    }

    private void crearUsuarioAdmin() {       
        

        if (!usuarios.containsKey("admin")) {
            System.out.println("se crearon admin");
            Usuario admin = new Usuario("admin", "admin");
            usuarios.put("admin", admin);
            guardarUsuarioEnArchivo(admin);
        }
    }

    private void crearCarpetasIniciales(String usuario) {
        System.out.println("se crearon carpetas");
        File documentos = new File("Z/" + usuario + "/Mis Documentos");
        File musica = new File("Z/" + usuario + "/Música");
        File imagenes = new File("Z/" + usuario + "/Mis Imágenes");

        documentos.mkdirs();
        musica.mkdirs();
        imagenes.mkdirs();
        mostrarUbicacionArchivos(usuario);
    }
    
    
    public static void mostrarUbicacionArchivos(String usuario) {
    String rutaDocumentos = "Z/" + usuario + "/Mis Documentos";
    String rutaMusica = "Z/" + usuario + "/Música";
    String rutaImagenes = "Z/" + usuario + "/Mis Imágenes";

    String mensaje = "Ubicación de carpetas y archivos para el usuario " + usuario + ":\n";
    mensaje += "Documentos: " + rutaDocumentos + "\n";
    mensaje += "Música: " + rutaMusica + "\n";
    mensaje += "Imágenes: " + rutaImagenes;
    System.out.println(mensaje);

}


 private void cargarUsuariosDesdeArchivo() {
    try (RandomAccessFile raf = new RandomAccessFile("usuarios.dat", "rw")) {
        while (raf.getFilePointer() < raf.length()) {
            try {
                System.out.println("me esta carganndo de los usarios");
                Usuario usuario = new Usuario(raf.readUTF(), raf.readUTF());
                usuarios.put(usuario.getNombre(), usuario);
            } catch (EOFException e) {
                
                break;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void guardarUsuarioEnArchivo(Usuario usuario) {
        try (RandomAccessFile raf = new RandomAccessFile("usuarios.dat", "rw")) {
            System.out.println("lo gaurdo en el archivo");
            raf.seek(raf.length()); 
            raf.writeUTF(usuario.getNombre());
            raf.writeUTF(usuario.getContraseña());         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public  String obtenerTipoUsuario(String nombreUsuario) {
        try (RandomAccessFile raf = new RandomAccessFile("usuarios.dat", "rw")) {
        while (raf.getFilePointer() < raf.length()) {
                long offset = raf.getFilePointer();
                String storedUsuario = raf.readUTF();
                String storedContraseña = raf.readUTF();
                String storedTipo = raf.readUTF();

                if (storedUsuario.equals(nombreUsuario)) {
                    return storedTipo;
                }
            }
        } catch (EOFException e) {

        } catch (IOException e) {
 
        }

        return "Usuario no encontrado";
    }
    
    
    
   


    private static class Usuario {
        private String nombre;
        private String contraseña;
      
        

        public Usuario(String nombre, String contraseña) {
            this.nombre = nombre;
            this.contraseña = contraseña;
            
        }

        public String getNombre() {
            return nombre;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
        
        

      
    }
}

