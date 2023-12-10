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
    private static final int LONG_SIZE = 8;  // Tamaño de un long en bytes

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
            return storedUsuario.getContraseña().equals(contraseña);
            
        }
        return false;
    }

    public static void abrirSistemaDeArchivos(String usuario) {
        System.out.println("no se que hace esto");
        File directorioUsuario = new File("Z:/" + usuario);
        if (!directorioUsuario.exists()) {
            directorioUsuario.mkdirs(); 
            System.out.println("si existe");
            
            
        }

//        JOptionPane.showMessageDialog(null, "Sistema de archivos abierto para " + usuario, "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
    }

       public void crearUsuario(String nuevoUsuario, String nuevaContraseña, String tipoUsuario) {
//         
            
         nuevoUsuario = CrearcionUsuarios.nombrenuevo.getText();
         nuevaContraseña = CrearcionUsuarios.Contranueva.getPassword().toString();
         tipoUsuario = CrearcionUsuarios.Tipo.getSelectedItem().toString();
        // Verifica si el usuario ya existe
        if (usuarios.containsKey(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Agrega el nuevo usuario a la lista y guarda en el archivo binario
           Usuario nuevoUsuarioObj = new Usuario(nuevoUsuario, nuevaContraseña, tipoUsuario);
            usuarios.put(nuevoUsuario, nuevoUsuarioObj);
            guardarUsuarioEnArchivo(nuevoUsuarioObj);
            crearCarpetasIniciales(nuevoUsuario);

            abrirSistemaDeArchivos(nuevoUsuario);
        }
    }

    private void crearUsuarioAdmin() {       
        System.out.println("se crearon admin");

        if (!usuarios.containsKey("admin")) {
            Usuario admin = new Usuario("admin", "admin", "Administrador");
            usuarios.put("admin", admin);
            guardarUsuarioEnArchivo(admin);
        }
    }

    private void crearCarpetasIniciales(String usuario) {
        System.out.println("se crearon carpetas");
        File documentos = new File("Z:/" + usuario + "/Mis Documentos");
        File musica = new File("Z:/" + usuario + "/Música");
        File imagenes = new File("Z:/" + usuario + "/Mis Imágenes");

        documentos.mkdirs();
        musica.mkdirs();
        imagenes.mkdirs();
        mostrarUbicacionArchivos(usuario);
    }
    
    
    public static void mostrarUbicacionArchivos(String usuario) {
    String rutaDocumentos = "Z:/" + usuario + "/Mis Documentos";
    String rutaMusica = "Z:/" + usuario + "/Música";
    String rutaImagenes = "Z:/" + usuario + "/Mis Imágenes";

    String mensaje = "Ubicación de carpetas y archivos para el usuario " + usuario + ":\n";
    mensaje += "Documentos: " + rutaDocumentos + "\n";
    mensaje += "Música: " + rutaMusica + "\n";
    mensaje += "Imágenes: " + rutaImagenes;
    
        System.out.println(mensaje);

//    JOptionPane.showMessageDialog(null, mensaje, "Ubicación de Archivos", JOptionPane.INFORMATION_MESSAGE);
}


 private void cargarUsuariosDesdeArchivo() {
    try (RandomAccessFile raf = new RandomAccessFile("usuarios.dat", "rw")) {
        while (raf.getFilePointer() < raf.length()) {
            try {
                System.out.println("me esta carganndo de los usarios");
                Usuario usuario = new Usuario(raf.readUTF(), raf.readUTF(), raf.readUTF());
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
            raf.writeUTF(usuario.getTipo());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Usuario {
        private String nombre;
        private String contraseña;
        private String tipo;

        public Usuario(String nombre, String contraseña, String tipo) {
            this.nombre = nombre;
            this.contraseña = contraseña;
            this.tipo = tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getContraseña() {
            return contraseña;
        }

        public String getTipo() {
            return tipo;
        }
    }
}

