/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cmd;

/**
 *
 * @author aleja
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandConsol extends JInternalFrame {
    private JTextArea consoleOutput;
    private String currentDirectory;
    private java.io.File mifile = null;
    private Map<String, String> commandMap;

    public CommandConsol() {
        setTitle("Command Console");
        setSize(600, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true); 
        setClosable(true);

        currentDirectory = "C:/";
        
        commandMap = new HashMap<>();
        commandMap.put("mkdir", "Create a new folder");
        commandMap.put("rm", "Remove a folder");
        commandMap.put("cd", "Change directory");
        commandMap.put("cd..", "Navigate up");
        commandMap.put("dir", "List contents");
        commandMap.put("date", "Show current date");
        commandMap.put("time", "Show current time");
        commandMap.put("mfile", "Create a new file");
        commandMap.put("wr", "Write in file");
        commandMap.put("rd", "read in file");
        commandMap.put("help", "Show available commands");

        consoleOutput = new JTextArea();
        consoleOutput.setEditable(true);
        consoleOutput.setBackground(Color.BLACK);
        consoleOutput.setForeground(Color.WHITE);
        
     

        JScrollPane scrollPane = new JScrollPane(consoleOutput);

        consoleOutput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        processCommand(getLastCommand());
                    } catch (IOException ex) {
                        Logger.getLogger(CommandConsol.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        updatePrompt();
    }
    
     private void appendToConsole(String text) {
        consoleOutput.append(text + "\n");
    }
     
   private void updatePrompt() {
        setTitle(currentDirectory + ">");
        consoleOutput.setCaretPosition(consoleOutput.getDocument().getLength()); 
    }

    private String getLastCommand() {
        String text = consoleOutput.getText().trim();
        int lastNewline = text.lastIndexOf("\n");
        if (lastNewline >= 0) {
            return text.substring(lastNewline).trim();
        } else {
            return text;
        }
    }
    

    private void processCommand(String input) throws IOException {
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();

        switch (command) {
            case "mkdir":
                if (tokens.length > 1) {
                    String folderName = tokens[1];
                    createFolder(folderName);
                } else {
                    appendToConsole("Usage: mkdir <foldername>");
                }
                break;    
            case "mfile":
                if (tokens.length > 1) {
                    String folderName = tokens[1];
                    crearfile(folderName);
                } else {
                    appendToConsole("Usage: mfir <foldername>");
                }
                break;                
            case "rm":
                
              
                if (tokens.length > 1) {                    
                String entityName = tokens[1];
                
                removeFolder(entityName);
                removeFile(entityName);
                 
                } else {
                    appendToConsole("Usage: rm <foldername>");
                }
                break;
            case "cd":
                if (tokens.length > 1) {
                    String folderName = tokens[1];
                    changeDirectory(folderName);
                } else {
                    appendToConsole("Usage: cd <foldername>");
                }
                break;
            case "cd..":
                navigateUp();
                break;
            case "dir":
                listContents();
                break;
            case "date":
                showDate();
                break;
            case "time":
                showTime();
                break;
            case "wr":

                     if (tokens.length > 1) {
                        String filename = tokens[1];                        
                        String content = input.substring(command.length() + filename.length() + 2).trim();
                        writeText(true, filename, content);
                    } else {
                        appendToConsole("Usage: wr <filename> <content>");
                    }
                    break;
            case "rd":                
                if (tokens.length > 1) {
                    String folderName = tokens[1];
                    readText(folderName);
                } else {
                    appendToConsole("Usage: rd <foldername>");
                }
                break;
            case "help":
                showHelp();
                break;
            default:
                appendToConsole("Command not recognized: " + input);
        
                       
        }
    }

    private void createFolder(String folderName) {
        File newFolder = new File(currentDirectory + File.separator + folderName);
        if (newFolder.mkdir()) {
            appendToConsole("\nCreated folder: " + newFolder.getAbsolutePath());
        } else {
            appendToConsole("\nFailed to create folder: " + newFolder.getAbsolutePath());
        }
    }

    private void removeFolder(String folderName) {
        File folderToRemove = new File(currentDirectory + File.separator + folderName);
        if (folderToRemove.exists() && folderToRemove.isDirectory()) {
            if (folderToRemove.delete()) {
                appendToConsole("\nRemoved folder: " + folderToRemove.getAbsolutePath());
            } else {
                appendToConsole("\nFailed to remove folder: " + folderToRemove.getAbsolutePath());
            }
        } else {
            appendToConsole("\nFolder not found: " + folderToRemove.getAbsolutePath());
        }
    }
    
    private void removeFile(String filename) {
        File fileremove = new File(currentDirectory + File.separator + filename);
        if (fileremove.exists() && fileremove.isFile()) {
            if (fileremove.delete()) {
                appendToConsole("\nRemoved file: " + fileremove.getAbsolutePath());
            } else {
                appendToConsole("\nFailed to remove file: " + fileremove.getAbsolutePath());
            }
        } else {
            appendToConsole("\nFolder not found: " + fileremove.getAbsolutePath());
        }
    }

    private void changeDirectory(String folderName) {
        File newDirectory = new File(currentDirectory + File.separator + folderName);
        if (newDirectory.isDirectory()) {
            currentDirectory = newDirectory.getAbsolutePath();
            updatePrompt();
        } else {
            appendToConsole("\nDirectory not found: " + newDirectory.getAbsolutePath());
        }
    }

    private void navigateUp() {
        File currentDirFile = new File(currentDirectory);
        String parent = currentDirFile.getParent();
        if (parent != null) {
            currentDirectory = parent;
            updatePrompt();
        } else {
            appendToConsole("\nAlready at the root directory.");
        }
    }

    private void listContents() {
        File currentDir = new File(currentDirectory);
        File[] files = currentDir.listFiles();
        if (files != null) {
            appendToConsole("\nContents of " + currentDirectory + ":");
            for (File file : files) {
                appendToConsole(file.getName());
            }
        } else {
            appendToConsole("\nFailed to list contents of " + currentDirectory);
        }
    }

    private void showDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        appendToConsole("\nCurrent date: " + currentDate);
    }

    private void showTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String currentTime = timeFormat.format(new Date());
        appendToConsole("\nCurrent time: " + currentTime);
    }

    private void showHelp() {
        appendToConsole("\nAvailable commands:");
        for (Map.Entry<String, String> entry : commandMap.entrySet()) {
            appendToConsole(entry.getKey() + ": " + entry.getValue());
        }
    }

   

   

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CommandConsol().setVisible(true);
            }
        });
    }
    
    public  void crearfile(String filename) throws IOException{        
        File newFile = new File(currentDirectory + File.separator + filename);
        if (newFile.createNewFile()) {
            appendToConsole("\nCreated Archive: " + newFile.getAbsolutePath());
        } else {
            appendToConsole("\nFailed to create Archive: " + newFile.getAbsolutePath());
        }
    }
    
    
     public void readText(String fileread)throws IOException{
         try(FileReader fr= new FileReader(fileread)){
             char buffer[]= new char[(int)fileread.length()];             
             int bytes = fr.read(buffer);
             FileReader fr2 = new FileReader(fileread);
             Scanner  lector = new Scanner(fr2);
             
             while(lector.hasNext()){
             appendToConsole("\nContent:");
             appendToConsole(lector.nextLine());
             appendToConsole("Bytes leido"+bytes);
             
             
         }
             fr2.close();
             
        }
         
     }
     
     public void writeText(boolean append, String filename, String content) throws IOException {
    try (FileWriter fw = new FileWriter(currentDirectory + File.separator + filename, append)) {
        fw.write(content);
        appendToConsole("\nContent written to file: " + filename);
        } catch (IOException e) {
            appendToConsole("\nFailed to write content to file: " + filename);
            throw e;
        }
    }
}
