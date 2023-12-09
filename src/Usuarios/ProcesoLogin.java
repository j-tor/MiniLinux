package Usuarios;

import Menu.InicioDeSistema;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProcesoLogin {

    
    File f = new File("Usuario");
    int ln;
    private String Username,Password,Tipo;
    
    public String tipodentro;
    


    public void createFolder(){
        if(!f.exists()){
            f.mkdirs();
        }
    }
    
  
    
    public void AgregarUsuario(String usr,String pswd,String mail){
        try {
            RandomAccessFile ra = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0;i<ln;i++){
            ra.readLine();
            }
            if(ln>0){
            ra.writeBytes("\r\n");
            ra.writeBytes("\r\n");
            }
            ra.writeBytes("Username:"+usr+ "\r\n");
            ra.writeBytes("Password:"+pswd+ "\r\n");
            ra.writeBytes("Tipo:"+mail);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean CheckData(String usr,String pswd){
    
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            String line = raf.readLine();
            Username=line.substring(9);
            Password=raf.readLine().substring(9);
            Tipo = raf.readLine().substring(5);
            if(usr.equals(Username)& pswd.equals(Password)){
                JOptionPane.showMessageDialog(null, "Password matched");
                
                this.tipodentro = Tipo;
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Wrong user/Password");
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    public void logic(String usr,String pswd){
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0;i<ln;i+=4){System.out.println("count "+i);
            
                String forUser = raf.readLine().substring(9);
                String forPswd = raf.readLine().substring(9);
                if(usr.equals(forUser) & pswd.equals(forPswd)){
                    JOptionPane.showMessageDialog(null, "password matched");
                    break;
                }else if(i==(ln-3)){
                    JOptionPane.showMessageDialog(null, "incorrect username/password");
                    break;
                }
                // if you are using user & passwword without email
                // then dont forget to replace  k<=2 with k=2 below
                for(int k=1;k<=2;k++){
                    raf.readLine();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void countLines(){
        try {
            ln=0;
            RandomAccessFile raf = new RandomAccessFile(f+"\\logins.txt", "rw");
            for(int i=0;raf.readLine()!=null;i++){
                ln++;
            }
            System.out.println("number of lines:"+ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicioDeSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
