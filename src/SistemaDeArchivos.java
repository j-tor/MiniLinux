/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleja
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class SistemaDeArchivos {
    private static final String DIRECTORIO_RAIZ = "C:/Users/aleja/Music/";
    private static final String CARPETA_DOCUMENTOS = "Mis Documentos";
    private static final String CARPETA_MUSICA = "Música";
    private static final String CARPETA_IMAGENES = "Mis Imágenes";

    private Map<String, Usuario> usuarios;
    private Usuario usuarioActual;

    public SistemaDeArchivos() {
        this.usuarios = new HashMap<>();
        configuracionInicial();
    }

    private void configuracionInicial() {
        // Crear usuario administrador por defecto
        Usuario admin = new Usuario("admin", "admin");
        usuarios.put("admin", admin);

        // Crear directorio raíz por defecto (C:\)
        admin.crearDirectorio(DIRECTORIO_RAIZ);

        // Establecer usuario administrador como usuario actual por defecto
        usuarioActual = admin;
    }

    public void crearNuevoUsuario(String nombreUsuario, String contrasena) {
        Usuario nuevoUsuario = new Usuario(nombreUsuario, contrasena);
        usuarios.put(nombreUsuario, nuevoUsuario);

        // Crear carpetas básicas para el nuevo usuario
        nuevoUsuario.crearDirectorio(DIRECTORIO_RAIZ + nombreUsuario + "/" + CARPETA_DOCUMENTOS);
        nuevoUsuario.crearDirectorio(DIRECTORIO_RAIZ + nombreUsuario + "/" + CARPETA_MUSICA);
        nuevoUsuario.crearDirectorio(DIRECTORIO_RAIZ + nombreUsuario + "/" + CARPETA_IMAGENES);
    }

    public void iniciarSesion(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarios.get(nombreUsuario);

        if (usuario != null && usuario.verificarContrasena(contrasena)) {
            usuarioActual = usuario;
            System.out.println("Sesión iniciada como: " + nombreUsuario);
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    public void listarContenidoActual() {
        usuarioActual.listarContenido();
    }

    public static void main(String[] args) {
        SistemaDeArchivos sistema = new SistemaDeArchivos();
        Scanner scanner = new Scanner(System.in);

        // Ejemplo de uso
        sistema.crearNuevoUsuario("usuario1", "pass123");
        sistema.iniciarSesion("usuario1", "pass123");
        sistema.listarContenidoActual();

        scanner.close();
    }
}
class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private Map<String, String> sistemaArchivos; // Nombre de carpeta - Ruta

    public Usuario(String nombreUsuario, String contrasena) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.sistemaArchivos = new HashMap<>();
    }

    public void crearDirectorio(String ruta) {
        sistemaArchivos.put(ruta, "Directorio vacío");
    }

    public void listarContenido() {
        System.out.println("Contenido de " + nombreUsuario + ":");
        for (Map.Entry<String, String> entry : sistemaArchivos.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public boolean verificarContrasena(String contrasena) {
        return this.contrasena.equals(contrasena);
    }
}