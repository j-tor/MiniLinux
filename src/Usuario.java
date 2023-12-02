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

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Usuario {
    private String nombreUsuario;
    private String contraseña;

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    
}

class SistemaArchivos {
    
    public static void main(String[] args) {
        SistemaArchivos sistema = new SistemaArchivos();
        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Listar contenido del directorio del usuario actual");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre de usuario: ");
                    String nuevoUsuario = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    String contraseñaUsuario = scanner.nextLine();
                    sistema.crearUsuario(nuevoUsuario, contraseñaUsuario);
                    break;

                case 2:
                    System.out.print("Ingrese nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    String contraseña = scanner.nextLine();
                    sistema.iniciarSesion(nombreUsuario, contraseña);
                    break;

                case 3:
                    sistema.listarContenidoDirectorio();
                    break;

                case 4:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    
    private static final String DIRECTORIO_RAIZ = "C:/";
    private static final String DIRECTORIO_USUARIOS = "C:/U programs/";
    private static final String[] CARPETAS_INICIALES = {"Mis Documentos", "Música", "Mis Imágenes"};

    private Map<String, Usuario> usuarios;
    private Usuario usuarioActual;

    public SistemaArchivos() {
        this.usuarios = new HashMap<>();
        // Agregar un usuario administrador por defecto
        usuarios.put("admin", new Usuario("admin", "adminpass"));
        this.usuarioActual = null;
    }

    public void crearUsuario(String nombreUsuario, String contraseña) {
        if (!usuarios.containsKey(nombreUsuario)) {
            usuarios.put(nombreUsuario, new Usuario(nombreUsuario, contraseña));
            crearDirectorioUsuario(nombreUsuario);
            System.out.println("Usuario creado exitosamente.");
        } else {
            System.out.println("El nombre de usuario ya existe. Por favor, elija otro nombre.");
        }
    }

    public void iniciarSesion(String nombreUsuario, String contraseña) {
        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            usuarioActual = usuario;
            System.out.println("Sesión iniciada correctamente.");
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    public void listarContenidoDirectorio() {
        if (usuarioActual != null) {
            String directorioUsuario = DIRECTORIO_USUARIOS + usuarioActual.getNombreUsuario();
            File directorio = new File(directorioUsuario);
            String[] contenido = directorio.list();
            if (contenido != null) {
                System.out.println("Contenido del directorio del usuario " + usuarioActual.getNombreUsuario() + ":");
                for (String elemento : contenido) {
                    System.out.println(elemento);
                }
            }
        } else {
            System.out.println("Ningún usuario ha iniciado sesión.");
        }
    }

    private void crearDirectorioUsuario(String nombreUsuario) {
        String directorioUsuario = DIRECTORIO_USUARIOS + nombreUsuario;
        File directorio = new File(directorioUsuario);
        if (!directorio.exists()) {
            directorio.mkdirs();
            for (String carpeta : CARPETAS_INICIALES) {
                new File(directorioUsuario + "/" + carpeta).mkdir();
            }
        }
    }
}


