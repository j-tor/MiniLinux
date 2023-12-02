/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linux;

/**
 *
 * @author aleja
 */
//import java.awt.GridLayout;
//import java.io.File;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Arrays;
//import java.util.List;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTree;
//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
//import javax.swing.event.TreeModelListener;
//import javax.swing.tree.TreeModel;
//import javax.swing.tree.TreePath;
//
///**
// * @author NagasharathK
// *
// */
//public class FileExplorer extends JFrame {
//
// private JTree fileManagerTree = null;
//
// public FileExplorer() {
//  initComponents();
// }
//
// /**
//  * Initializes components
//  */
// private void initComponents() {
//  this.getContentPane().add(new JScrollPane(createFileManagerTree()));
//  this.setSize(500, 500);
//  this.setResizable(true);
//  this.setTitle("File Manager..");
// }
//
// /**
//  * @return JPanel object which contains other comp...
//  */
// private JPanel createFileManagerTree() {
//  JPanel panel = new JPanel();
//  panel.setLayout(new GridLayout());
//
//  fileManagerTree = new JTree();
//  fileManagerTree.setModel(new FilesContentProvider("C:\\"));
//  panel.add(fileManagerTree);
//  return panel;
// }
//
// class FilesContentProvider implements TreeModel {
//
//  private File node;
//
//  public FilesContentProvider(String path) {
//   node = new File(path);
//
//  }
//
//  @Override
//  public void addTreeModelListener(TreeModelListener l) {
//
//  }
//
//  @Override
//  public Object getChild(Object parent, int index) {
//   if (parent == null)
//    return null;
//   return ((File) parent).listFiles()[index];
//  }
//
//  @Override
//  public int getChildCount(Object parent) {
//   if (parent == null)
//    return 0;
//   return (((File) parent).listFiles() != null) ? ((File) parent).listFiles().length : 0;
//  }
//
//  @Override
//  public int getIndexOfChild(Object parent, Object child) {
//   List<File> list = Arrays.asList(((File) parent).listFiles());
//   return list.indexOf(child);
//  }
//
//  @Override
//  public Object getRoot() {
//   return node;
//  }
//
//  @Override
//  public boolean isLeaf(Object node) {
//   return ((File) node).isFile();
//  }
//
//  @Override
//  public void removeTreeModelListener(TreeModelListener l) {
//
//  }
//
//  @Override
//  public void valueForPathChanged(TreePath path, Object newValue) {
//
//  }
//
// }
//
// /**
//  * @param args
//  * @throws InvocationTargetException
//  * @throws InterruptedException
//  * @throws UnsupportedLookAndFeelException
//  * @throws IllegalAccessException
//  * @throws InstantiationException
//  * @throws ClassNotFoundException
//  */
//// public static void main(String[] args) throws InvocationTargetException, InterruptedException,
////   ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
////  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//  FileExplorer explorerUI = new FileExplorer();
//  SwingUtilities.invokeAndWait(new Runnable() {
//   @Override
//   public void run() {
//    explorerUI.setVisible(true);
//   }
//  });
// }
//}
// 

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//public class FileExplorer extends JFrame {
//
//    private JList<String> fileList;
//    private DefaultListModel<String> listModel;
//
//    public FileExplorer() {
//        super("File Explorer");
//
//        listModel = new DefaultListModel<>();
//        fileList = new JList<>(listModel);
//
//        JScrollPane scrollPane = new JScrollPane(fileList);
//        JButton openButton = new JButton("Open");
//
//        openButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openSelectedFile();
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.add(scrollPane, BorderLayout.CENTER);
//        panel.add(openButton, BorderLayout.SOUTH);
//
//        add(panel);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(400, 300);
//        setLocationRelativeTo(null);
//
//        refreshFileList(new File(System.getProperty("user.home"))); // Start with user's home directory
//    }
//
//    private void refreshFileList(File directory) {
//        listModel.clear();
//
//        File[] files = directory.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                listModel.addElement(file.getName());
//            }
//        }
//    }
//
//    private void openSelectedFile() {
//        String selectedFileName = fileList.getSelectedValue();
//        if (selectedFileName != null) {
//            // You can perform actions with the selected file here
//            JOptionPane.showMessageDialog(this, "Selected file: " + selectedFileName);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new FileExplorer().setVisible(true);
//            }
//        });
//    }
//}




import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import javax.swing.tree.TreePath;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Comparator;

public class FileExplorer extends JFrame {

    private JTree fileTree;
    private JButton organizeButton;
    private JButton sortButton;
    private JButton renameButton;

    public FileExplorer() {
        // Configurar la interfaz gráfica
        setTitle("File Organizer");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Configurar el árbol de archivos
        File root = new File("C:/U programs"); // Cambia esto a la carpeta raíz que quieras
        DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode(root.getName());
        buildFileTree(root, rootTreeNode);
        fileTree = new JTree(rootTreeNode);
        JScrollPane treeScrollPane = new JScrollPane(fileTree);
        add(treeScrollPane);

        // Configurar botones
        organizeButton = new JButton("Organizar");
        sortButton = new JButton("Ordenar por nombre");
        renameButton = new JButton("Cambiar nombre");

        // Agregar listeners a los botones
        organizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organizeFiles();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortFiles();
            }
        });

        renameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                renameFile();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(organizeButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(renameButton);
        add(buttonPanel);

        // Configurar el JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildFileTree(File file, DefaultMutableTreeNode treeNode) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName());
                treeNode.add(childNode);
                buildFileTree(child, childNode);
            }
        }
    }

    private void organizeFiles() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
        if (selectedNode == null || !selectedNode.getAllowsChildren()) {
            JOptionPane.showMessageDialog(this, "Selecciona una carpeta para organizar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File selectedFolder = new File(getFolderPath(selectedNode));
        File[] files = selectedFolder.listFiles();

        if (files == null || files.length == 0) {
            JOptionPane.showMessageDialog(this, "La carpeta seleccionada no contiene archivos para organizar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        File imageFolder = new File(selectedFolder, "Imagenes");
        File documentFolder = new File(selectedFolder, "Documentos");
        File musicFolder = new File(selectedFolder, "Musica");

        imageFolder.mkdir();
        documentFolder.mkdir();
        musicFolder.mkdir();

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".gif")) {
                    moveFile(file, imageFolder);
                } else if (fileName.endsWith(".txt") || fileName.endsWith(".doc") || fileName.endsWith(".pdf")) {
                    moveFile(file, documentFolder);
                } else if (fileName.endsWith(".mp3") || fileName.endsWith(".wav") || fileName.endsWith(".ogg")) {
                    moveFile(file, musicFolder);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Archivos organizados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        refreshTree();
    }

    private void moveFile(File file, File destinationFolder) {
        File newFile = new File(destinationFolder, file.getName());
        try {
            Files.move(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortFiles() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
        if (selectedNode == null || !selectedNode.getAllowsChildren()) {
            JOptionPane.showMessageDialog(this, "Selecciona una carpeta para ordenar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File selectedFolder = new File(getFolderPath(selectedNode));
        File[] files = selectedFolder.listFiles();

        if (files == null || files.length == 0) {
            JOptionPane.showMessageDialog(this, "La carpeta seleccionada no contiene archivos para ordenar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                return file1.getName().compareToIgnoreCase(file2.getName());
            }
        });

        refreshTree();
        JOptionPane.showMessageDialog(this, "Archivos ordenados por nombre.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void renameFile() {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
        if (selectedNode == null || !selectedNode.getAllowsChildren()) {
            JOptionPane.showMessageDialog(this, "Selecciona un archivo o carpeta para cambiar el nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newName = JOptionPane.showInputDialog(this, "Nuevo nombre:");
        if (newName == null || newName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File selectedFile = new File(getFolderPath(selectedNode));
        File parentFolder = selectedFile.getParentFile();
        File newFile = new File(parentFolder, newName);

        if (selectedFile.renameTo(newFile)) {
            refreshTree();
            JOptionPane.showMessageDialog(this, "Nombre cambiado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo cambiar el nombre del archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTree() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) fileTree.getModel().getRoot();
        root.removeAllChildren();
        buildFileTree(new File("C:/"), root);
        ((DefaultTreeModel) fileTree.getModel()).reload();
    }

    private String getFolderPath(DefaultMutableTreeNode node) {
        StringBuilder path = new StringBuilder(node.toString());
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while (parent != null) {
            path.insert(0, parent.toString() + File.separator);
            parent = (DefaultMutableTreeNode) parent.getParent();
        }
        return path.toString();
    }

       public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileExplorer();
            }
        });
    }
}