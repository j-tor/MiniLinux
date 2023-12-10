package Usuarios;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Users {

    RandomAccessFile User;
    static File archivos = null;

    public Users() {
        try {
            User = new RandomAccessFile("ArchivoUsuario/RegistrosUsers.usr", "rw");
        } catch (IOException e) {

        }

    }
    
    // Usage example:
//public static void main(String[] args) {
//    ArrayList<UsuarioT> users = obtenerUsuarios("users.twc");
//    // Process the list of users as needed
//}
//
//    
//    public static void printAllUsers(String filePath) {
//    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
//        long fileLength = file.length();
//
//            while (file.getFilePointer() < fileLength) {
//                UsuarioT user = readUserFromFile(file);
//                System.out.println(user); // Assuming toString() is implemented in UsuarioT
//            }
//        } catch (IOException e) {
//            
//        }
//    }

    
    
//   public static void addUserToFile(UsuarioT newUser, String filePath) {
//        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
//            file.seek(file.length());
//
//            
//            writeUserToFile(newUser, file);
//        } catch (IOException e) {
////            e.printStackTrace();
//        }
//    }
//
//    
//    private static void writeUserToFile(UsuarioT user, RandomAccessFile file) throws IOException {
//        file.writeUTF(user.getNusuario());
//        file.writeChar(user.getGenero());
//        file.writeUTF(user.getUsername());
//        file.writeUTF(user.getPassword());
//        file.writeLong(user.getFechaEntradaSistema().getTime());
//        file.writeInt(user.getEdad());
//        file.writeBoolean(user.isCuentaActiva());
//    }
//
//    
////  public static boolean validateUser(String username, String password, String filePath) {
//    try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
//        long fileLength = file.length();
//
//        while (file.getFilePointer() < fileLength) {
//            long startPosition = file.getFilePointer();
//            UsuarioT user = readUserFromFile(file);
//
//            System.out.println("Stored: " + user.getUsername() + " / " + user.getPassword());
//            System.out.println("Entered: " + username + " / " + password);
//
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                return true;
//            }
//
//            long endPosition = file.getFilePointer();
//            if (startPosition == endPosition) {
//                break;
//            }
//
//            // If there's no more data, break out of the loop
//            if (file.getFilePointer() >= fileLength) {
//                break;
//            }
//        }
//    } catch (EOFException e) {
//        // Handle the end of the file gracefully
//    } catch (IOException e) {
////        e.printStackTrace();
//    }
//    return false;
//}



   
//    private static UsuarioT readUserFromFile(RandomAccessFile file) throws IOException {
//        String nUsuario = file.readUTF();
//        char genero = file.readChar();
//        String username = file.readUTF();
//        String password = file.readUTF();
//        Date fechaEntradaSistema = new Date(file.readLong());
//        int edad = file.readInt();
//        boolean cuentaActiva = file.readBoolean();
//
//        return new UsuarioT(nUsuario, genero, username, password, fechaEntradaSistema, edad, cuentaActiva);
//    }

   
    

    public long ObtenerPuntero() throws IOException {

        if (User.length() != 0) {
            User.seek(0);
            while (User.getFilePointer() < User.length()) {
                User.readUTF();
                User.readUTF();
                User.readUTF();
            }
        }
        return User.getFilePointer();

    }

    public void setArchivos(String direccion) {
        archivos = new File(direccion);
    }

    public boolean crearFolder() {
        return archivos.mkdir();
    }

    public boolean CrearCarpetasUser(String usuario, String contraseña, String tipo) throws IOException {
        try {
            User.seek(User.length());
            User.writeUTF(usuario);
            User.writeUTF(contraseña);
            User.writeUTF(tipo);

            setArchivos("Z");
            archivos.mkdir();
            setArchivos("Z/" + usuario);

            if (crearFolder()) {
                setArchivos("Z/" + usuario + "/Mis Imagenes");
                archivos.mkdir();

                setArchivos("Z/" + usuario + "/Mis Documentos");
                archivos.mkdir();

                setArchivos("Z/" + usuario + "/Mi Musica");
                archivos.mkdir();
                return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
    public boolean VerificarUsuarios(String usuario, String contraseña) throws IOException {
        User.seek(0);
        String user;
        String password;
        while (User.getFilePointer() < User.length()) {
            long pos = User.getFilePointer();
            user = User.readUTF();
            password = User.readUTF();
            User.readUTF();
            if (user.equals(usuario) && password.equals(contraseña)) {
                User.seek(pos);
                return true;
            }
        }
        return false;
    }
        public boolean buscarUsuario(String usuario) throws IOException {
        User.seek(0);
        String user;
        while (User.getFilePointer() < User.length()) {
            long pos = User.getFilePointer();
            user = User.readUTF();
            User.readUTF();
            User.readUTF();
            if (user.equals(usuario)) {
                User.seek(pos);
                return true;
            }
        }
        return false;
    }

    public String BuscarTipo(String usuario, String contra) throws IOException {

        User.seek(0);
        String user;
        String password;
        String tipo;
        while (User.getFilePointer() < User.length()) {
            user = User.readUTF();
            password = User.readUTF();
            tipo = User.readUTF();
            if (user.equals(usuario) && password.equals(contra)) {
                return tipo;
            }
        }
        return null;

    }

}
