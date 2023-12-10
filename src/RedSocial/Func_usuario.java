
package RedSocial;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;


public class Func_usuario {
    
    private String user;
    private String contra;
    private String nombre;
    private char genero;
    private int edad;
    private Date creacion;
    private boolean activo;
    private Image img_perfil;
    
    private RandomAccessFile f_usuarios;
    private RandomAccessFile f_followers;
    private RandomAccessFile f_followings;
    private RandomAccessFile f_twits;
    
    
    public Func_usuario(String user) throws FileNotFoundException, IOException, NoExisteUsr{
        
        
        f_usuarios = new RandomAccessFile("RedRetro/usuarios.twc", "rw");
        
        if(existeUsuario(user, f_usuarios)){
            
            this.user = user;
            this.contra = f_usuarios.readUTF();
            this.nombre = f_usuarios.readUTF();
            this.genero = f_usuarios.readChar();
            this.edad = f_usuarios.readInt();
            this.creacion = new Date(f_usuarios.readLong());
            this.activo = f_usuarios.readBoolean();

            //obtiene la imagen del archivo
            byte[] img_buffer = new byte[f_usuarios.readInt()];

            f_usuarios.read(img_buffer);
            ByteArrayInputStream bytes = new ByteArrayInputStream(img_buffer);
            BufferedImage buffer_img = ImageIO.read(bytes);

            this.img_perfil = buffer_img;
            
            f_followers = new RandomAccessFile("RedRetro/" + user+"/followers.twc", "rw");
            f_followings = new RandomAccessFile("RedRetro/" + user+"/followings.twc", "rw");
            f_twits = new RandomAccessFile("RedRetro/" + user+"/twits.twc", "rw");

        }else{
            throw new NoExisteUsr();
        }
        
        
    }

    public String getUser() {
        return user;
    }

    public String getContra() {
        return contra;
    }

    public String getNombre() {
        return nombre;
    }

    public char getGenero() {
        return genero;
    }

    public int getEdad() {
        return edad;
    }

    public Date getCreacion() {
        return creacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public Image getImg_perfil() {
        return img_perfil;
    }


    public RandomAccessFile getF_usuarios() {
        return f_usuarios;
    }

    public ArrayList<String[]> misTwits() throws IOException {

        ArrayList<String[]> mensajes = new ArrayList<>();

        f_twits.seek(0);

        while (f_twits.getFilePointer() < f_twits.length()) {
            String[] temp = new String[3];

            temp[0] = f_twits.readUTF();
            temp[1] = f_twits.readUTF();
            temp[2] = f_twits.readLong() + "";

            mensajes.add(temp);
        }

        return mensajes;

    }

    public static boolean existeUsuario(String user, RandomAccessFile usuarios) throws IOException {
        try {
            
        usuarios.seek(0);
             while (usuarios.getFilePointer() < usuarios.length()) {

            String user_v = usuarios.readUTF();

            if (user_v.equals(user)) {
                return true;
                } else {
                    usuarios.readUTF();
                    usuarios.readUTF();
                    usuarios.readChar();
                    usuarios.readInt();
                    usuarios.readLong();
                    usuarios.readBoolean();

                    byte[] img_buffer = new byte[usuarios.readInt()];
                    usuarios.read(img_buffer);

                    if (usuarios.getFilePointer() == usuarios.length()) {
                        return false;
                    }
                }

            }
            } catch (Exception e) {
        }

        return false;
    }

    public ArrayList<String[]> cargarTwits() throws IOException, FileNotFoundException, NoExisteUsr {

        ArrayList<String[]> twits = new ArrayList<>();
        
        System.out.println("cargo mis twits");

        if (misTwits() != null) {
            twits.addAll(misTwits());
        }

        System.out.println("Cargando los twits de los que sigo");
        f_followings.seek(0);

        while (f_followings.getFilePointer() < f_followings.length()) {
            Func_usuario temp = new Func_usuario(f_followings.readUTF());
            
            boolean p = f_followings.readBoolean();
            System.out.println("User: " + temp.getUser() + " " + p);
            
            if (temp.activo && p) {
                twits.addAll(temp.misTwits());
            }
        }
        
        System.out.println("termino de cargar los twits que sigo");

        //ordenar los twist del mas antiguo al mas reciente
        for (int i = 0; i < twits.size(); i++) {

            for (int j = 0; j < twits.size() - i - 1; j++) {

                if (Long.parseLong(twits.get(i)[2]) > Long.parseLong(twits.get(i + 1)[2])) {

                    System.out.println(i + " vuelta");
                    String[] temp = twits.get(i + 1);
                    twits.set(i + 1, twits.get(i));
                    twits.set(i, temp);

                }

            }

        }
        System.out.println("Termino de cargar los twits");
        return twits;
    }

    public void guardarTwit(String msg) throws IOException {
        f_twits.seek(f_twits.length());

        f_twits.writeUTF(this.user);
        f_twits.writeUTF(msg);
        f_twits.writeLong(Calendar.getInstance().getTimeInMillis());

    }

    public ArrayList<String[]> cargarInteracciones() throws IOException, FileNotFoundException, NoExisteUsr {

        //Carga todos los twits;
        ArrayList<String[]> twits = new ArrayList<>();

        f_usuarios.seek(0);

        while (f_usuarios.getFilePointer() < f_usuarios.length()) {

            Func_usuario temp = new Func_usuario(f_usuarios.readUTF());
            if (temp.isActivo()) {
                twits.addAll(temp.misTwits());
            }

            sigUsuario();
        }

        //Agrega los elementos donde se menciona al usuario;
        ArrayList<String[]> interacciones = new ArrayList<>();

        CharSequence user = "@" + this.user;

        for (int i = 0; i < twits.size(); i++) {

            String[] temp = twits.get(i);
            String msg = temp[1];

            if (msg.contains(user)) {
                interacciones.add(twits.get(i));
            }

        }

        //ordenar los twist del mas antiguo al mas reciente
        for (int i = 0; i < interacciones.size(); i++) {

            for (int j = 0; j < interacciones.size() - i - 1; j++) {

                if (Long.parseLong(interacciones.get(i)[2]) > Long.parseLong(interacciones.get(i + 1)[2])) {

                    String[] temp = interacciones.get(i + 1);
                    interacciones.set(i + 1, interacciones.get(i));
                    interacciones.set(i, temp);

                }

            }

        }

        return interacciones;
    }

    public Object[][] usrCoincidencias(CharSequence str, String logUsr) throws IOException, FileNotFoundException, NoExisteUsr {

        ArrayList<String> conc = new ArrayList<>();

        f_usuarios.seek(0);

        while (f_usuarios.getFilePointer() < f_usuarios.length()) {

            Func_usuario user = new Func_usuario(f_usuarios.readUTF());
            if (user.isActivo()) {
                if (user.getUser().contains(str) && !user.getUser().equals(logUsr)) {
                    conc.add(user.getUser());
                }
            }

            sigUsuario();

        }

        Object[][] usrs;

        if (!conc.isEmpty()) {
            usrs = new Object[conc.size()][1];

            for (int i = 0; i < usrs.length; i++) {
                usrs[i][0] = conc.get(i);

            }

        } else {
            usrs = null;
        }

        return usrs;
    }

    public ArrayList<String[]> buscarHashTag(String[] str) throws IOException, FileNotFoundException, NoExisteUsr {

        ArrayList<String[]> twits = new ArrayList<>();

        f_usuarios.seek(0);

        while (f_usuarios.getFilePointer() < f_usuarios.length()) {
            Func_usuario temp = new Func_usuario(f_usuarios.readUTF());

            if (temp.activo) {
                twits.addAll(temp.misTwits());
            }
            sigUsuario();
        }

        //ordenar los twist del mas antiguo al mas reciente
        for (int i = 0; i < twits.size(); i++) {

            for (int j = 0; j < twits.size() - i - 1; j++) {

                if (Long.parseLong(twits.get(i)[2]) > Long.parseLong(twits.get(i + 1)[2])) {

                    String[] temp = twits.get(i + 1);
                    twits.set(i + 1, twits.get(i));
                    twits.set(i, temp);

                }

            }

        }

        ArrayList<String[]> twits_hash = new ArrayList<>();

        String[] hashtags = str;

        for (int i = 0; i < twits.size(); i++) {

            String[] temp = twits.get(i);
            String msg = temp[1];

            //busca los hashtag en el msg 
            ArrayList<String> hash_msg = new ArrayList<>();

            for (int j = 0; j < msg.length(); j++) {
                if (msg.charAt(j) == '#') {

                    for (int k = j + 1; k < msg.length(); k++) {

                        if (msg.charAt(k) == ' ' || k == msg.length() - 1) {
                            String sub;
                            if (k == msg.length() - 1) {
                                sub = msg.substring(j, k + 1);
                                hash_msg.add(sub);
                            } else {
                                sub = msg.substring(j, k);
                                hash_msg.add(sub);
                                break;
                            }

                        }

                    }

                }

            }

            //Si algun hashtag que se encontro en el msg esta en los hashtag 
            // que le mandamos lo agrega
            for (int j = 0; j < hashtags.length; j++) {
                if (hash_msg.contains(hashtags[j])) {

                    if (!twits_hash.contains(temp)) {
                        twits_hash.add(temp);
                        break;
                    }

                }

            }
        }

        return twits_hash;
    }

    //activar y descativar
    public void descUsuario() throws IOException {

        if (existeUsuario(this.user, f_usuarios)) {

            f_usuarios.readUTF();
            f_usuarios.readUTF();
            f_usuarios.readChar();
            f_usuarios.readInt();
            f_usuarios.readLong();

            f_usuarios.writeBoolean(false);
        }

    }

    public void actUsuario() throws IOException {
        if (existeUsuario(this.user, f_usuarios)) {

            f_usuarios.readUTF();
            f_usuarios.readUTF();
            f_usuarios.readChar();
            f_usuarios.readInt();
            f_usuarios.readLong();

            f_usuarios.writeBoolean(true);
        }
    }

    
    //Funciones para seguir/ desactivar 
    public int getFollowings() throws IOException {
        if (f_followings.length() > 0) {

            int f = 0;
            f_followings.seek(0);

            while (f_followings.getFilePointer() < f_followings.length()) {
                f_followings.readUTF();
                
                if(f_followings.readBoolean())
                    f++;
            }

            return f;
        } else {
            return 0;
        }

    }

    public int getFollowers() throws IOException {
        if (f_followers.length() > 0) {

            int f = 0;
            f_followers.seek(0);

            while (f_followers.getFilePointer() < f_followers.length()) {
                f_followers.readUTF();
                if(f_followers.readBoolean())
                    f++;
            }

            return f;
        } else {
            return 0;
        }

    }
    
    public boolean loSigo(String usr) throws IOException{
        
        if(f_followings.length() > 0){
            
            boolean sigo = false;

            f_followings.seek(0);

            while (f_followings.getFilePointer() < f_followings.length()) {

                String tmpUsr = f_followings.readUTF();
                long posc = f_followings.getFilePointer();
                boolean tmpAct = f_followings.readBoolean();
                
                if(tmpUsr.equals(usr)){
                    f_followings.seek(posc);
                    sigo = tmpAct;
                    break;
                }

            }
            
            return sigo;
            
        }else
            return false;
        
    }
    
    public void dejarDeSeguir(String usr) throws IOException, FileNotFoundException, NoExisteUsr{
        
        Func_usuario selectUsr = new Func_usuario(usr);
        
        if(f_followings.length() > 0){
            
            f_followings.seek(0);

            while (f_followings.getFilePointer() < f_followings.length()) {

                
                String tmpUsr = f_followings.readUTF();
                
                if(tmpUsr.equals(usr)){
                    f_followings.writeBoolean(false);
                    selectUsr.desSeguidor(this.getUser());
                    break;
                }

            }
        }
        
    }
    
    public void seguir(String usr) throws IOException, FileNotFoundException, NoExisteUsr{
        
        Func_usuario selectUsr = new Func_usuario(usr);
        
        if(f_followings.length() > 0){
            
            f_followings.seek(0);
            
            while(f_followings.getFilePointer() < f_followings.length()){
                
                if(usr.equals(f_followings.readUTF())){
                    f_followings.writeBoolean(true);
                    selectUsr.addSeguidor(this.getUser());
                    return;
                }
            }
            
            
            f_followings.seek(f_followings.length());
            f_followings.writeUTF(usr);
            f_followings.writeBoolean(true);
            selectUsr.addSeguidor(this.getUser());
            
        }else{
            f_followings.writeUTF(usr);
            f_followings.writeBoolean(true);
            selectUsr.addSeguidor(this.getUser());
            
        }

    }
    
    public void addSeguidor(String usr) throws IOException{
        
        if(f_followers.length() > 0){
            
            f_followers.seek(0);
            
            while(f_followers.getFilePointer() < f_followers.length()){
                
                if(usr.equals(f_followers.readUTF())){
                    f_followers.writeBoolean(true);
                    return;
                }
            }
            
            f_followers.seek(f_followers.length());
            f_followers.writeUTF(usr);
            f_followers.writeBoolean(true);
            
            
        }else{
            f_followers.writeUTF(usr);
            f_followers.writeBoolean(true);
        }
        
    }
    
    public void desSeguidor(String usr) throws IOException{
        
         if(f_followers.length() > 0){
            
            f_followers.seek(0);

            while (f_followers.getFilePointer() < f_followers.length()) {

                String tmpUsr = f_followers.readUTF();
                
                if((tmpUsr.equals(usr))){
                    f_followers.writeBoolean(false);
                    return;
                }

            }
        }
        
    }
    private void sigUsuario() throws IOException {

        f_usuarios.readUTF();
        f_usuarios.readUTF();
        f_usuarios.readChar();
        f_usuarios.readInt();
        f_usuarios.readLong();
        f_usuarios.readBoolean();

        byte[] img_buffer = new byte[f_usuarios.readInt()];
        f_usuarios.read(img_buffer);
    }
}
