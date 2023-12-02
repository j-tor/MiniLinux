/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package linux;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author LENOVO
 */
public class NavegadorArchivos extends JFrame {
      private JTree fileTree;
    private DefaultTreeModel treeModel;

    public NavegadorArchivos() {
        // Crear la raíz del árbol
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Sistema de Archivos");
        treeModel = new DefaultTreeModel(root);
        // Agregar carpetas específicas
        agregarCarpetaUsuario("Escritorio", root);
        agregarCarpetaUsuario("Descargas", root);
        agregarCarpetaUsuario("Documentos", root);
        agregarCarpetaUsuario("Música", root);
        agregarPapeleraReciclaje(root);
        // Crear el árbol con el modelo
        fileTree = new JTree(treeModel);
        // Agregar el árbol a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(fileTree);
        // Crear botón para organizar archivos
        JButton organizeButton = new JButton("Organizar");
        organizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                organizarArchivos();
            }
        });

        // Crear botones para ordenar
        JButton sortByNameButton = new JButton("Ordenar por Nombre");
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ordenarArchivosPorNombre();
                } catch (IOException ex) {
                    Logger.getLogger(NavegadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton sortByDateButton = new JButton("Ordenar por Fecha");
        sortByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ordenarArchivosPorFecha();
                } catch (IOException ex) {
                    Logger.getLogger(NavegadorArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Crear panel para botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(organizeButton);
        buttonPanel.add(sortByNameButton);
        buttonPanel.add(sortByDateButton);

        // Configuración básica de la ventana
        setTitle("Navegador de Archivos");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Agregar componentes a la ventana
        add(scrollPane);
        add(buttonPanel, "South");
    }

    private void agregarCarpetaUsuario(String carpeta, DefaultMutableTreeNode root) {
        File carpetaUsuario = new File(System.getProperty("user.home") + File.separator + carpeta);
        DefaultMutableTreeNode nodoCarpeta = new DefaultMutableTreeNode(carpetaUsuario);
        root.add(nodoCarpeta);
    }

    private void agregarPapeleraReciclaje(DefaultMutableTreeNode root) {        
        File escritorio = new File(System.getProperty("user.home") + File.separator + "Escritorio");
        File papeleraReciclaje = new File(escritorio, "$RECYCLE.BIN");
        DefaultMutableTreeNode nodoPapelera = new DefaultMutableTreeNode(papeleraReciclaje);
        root.add(nodoPapelera);
    }

    private void organizarArchivos() {
     // aqui me falta ubicar el code y metodos de organizar   
         TreePath selectedPath = fileTree.getSelectionPath();
    if (selectedPath == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una carpeta para organizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selectedPath.getLastPathComponent();
    File directorioOrigen = (File) selectedNode.getUserObject();
    // Crear un cuadro de diálogo para seleccionar el directorio de destino
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    fileChooser.setDialogTitle("Seleccionar Directorio de Destino");
    int resultado = fileChooser.showDialog(this, "Seleccionar");
    if (resultado == JFileChooser.APPROVE_OPTION) {
        File directorioDestino = fileChooser.getSelectedFile();

        // Organizar archivos
        organizarArchivosEnDirectorio(directorioOrigen, directorioDestino);

        // Actualizar el árbol
        loadTree(new File(System.getProperty("user.home")), (DefaultMutableTreeNode) treeModel.getRoot());
        treeModel.reload();
    }
    }
   // metodo privado para la obtencion de ordenar archivos 
    private void organizarArchivosEnDirectorio(File directorioOrigen, File directorioDestino) {
    File[] archivos = directorioOrigen.listFiles();
    if (archivos != null) {
        for (File archivo : archivos) {
           // este me sirve para organizar los archivos
         String extension=ObtenerExtension(archivo);
            if (extension != null) {
                File destino = new File(directorioDestino, extension);
                destino = new File(destino, archivo.getName());
                if (archivo.renameTo(destino)) {
                    // Archivo movido con éxito
                    JOptionPane.showMessageDialog(rootPane, "Archivo Movido Existosamente...");
                } else {
                    // Error al mover el archivo
                    String mensaje="";
                   JOptionPane.showMessageDialog(this, mensaje, "Error al mover el archivo.", JOptionPane.ERROR_MESSAGE);
                }
            }  
        }
    }
}
    private String ObtenerExtension(File archivo){
        String NombreArchivo=archivo.getName();
        int index=NombreArchivo.lastIndexOf('.');
        //este if me sirve de validacion ya que toma el nombre de un archivo y extrae la extensión 
        if(index>0 && index<NombreArchivo.length()-1){
            return NombreArchivo.substring(index + 1).toLowerCase();
        }
        return null; // aqui se retorna null por si hay algun archivo oculto
    }
    // metodo del loadTree
    private void loadTree(File file, DefaultMutableTreeNode parentNode) {
    if (file.isDirectory()) {
        DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file);
        parentNode.add(dirNode);
        File[] files = file.listFiles();
        if (files != null) {
            Arrays.sort(files);
            for (File f : files) {
                loadTree(f, dirNode);
            }
        }
    } else {
        parentNode.add(new DefaultMutableTreeNode(file));
    }
}
    
    private void ordenarArchivosPorNombre() throws IOException {
    // ubicar lo ordenar Archivos en orden alfabetico  
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        ordenarNodos(root, new NombreComparator());
        treeModel.reload(); 
    }

    private void ordenarArchivosPorFecha() throws IOException {
    // ubicar ordenar archivos por Fecha,desde el mas viejo al nuevo
    DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        ordenarNodos(root, new FechaModificacionComparator());
        treeModel.reload();
        
    }

     private void ordenarNodos(DefaultMutableTreeNode root, Comparator<TreeNode> comparator)throws IOException {
        Enumeration<TreeNode> enumeration = root.depthFirstEnumeration();
        TreeNode[] nodes = new TreeNode[root.getChildCount()];

        int index = 0;
        while (enumeration.hasMoreElements()) {
            nodes[index++] = enumeration.nextElement();
        }

        Arrays.sort(nodes, comparator);

        root.removeAllChildren();
        for (TreeNode node : nodes) {
            root.add((MutableTreeNode) node);
        }
    }

    private static class NombreComparator implements Comparator<TreeNode> {
        @Override
        public int compare(TreeNode node1, TreeNode node2){
            File file1 = (File) ((DefaultMutableTreeNode) node1).getUserObject();
            File file2 = (File) ((DefaultMutableTreeNode) node2).getUserObject();
            return file1.getName().compareToIgnoreCase(file2.getName());
        }
    }

    private static class FechaModificacionComparator implements Comparator<TreeNode> {
        @Override
        public int compare(TreeNode node1, TreeNode node2) {
            File file1 = (File) ((DefaultMutableTreeNode) node1).getUserObject();
            File file2 = (File) ((DefaultMutableTreeNode) node2).getUserObject();
            long time1 = file1.lastModified();
            long time2 = file2.lastModified();

            // Ordenar desde el más antiguo hasta el más nuevo
            return Long.compare(time1, time2);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NavegadorArchivos navegador = new NavegadorArchivos();
            navegador.setVisible(true);
        });
    }  
}