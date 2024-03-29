package linux;

/*
aleja
 */

import Usuarios.CrearcionUsuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.*;
import javax.swing.tree.*;
import static linux.Inicio.pantalladeinicio;
import player.Spotify;



public class FileManager {


    public static final String APP_TITLE = "Explorador";
    private Desktop desktop;
    private FileSystemView fileSystemView;
    private File currentFile;
    private JPanel gui;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private JTable table;
    private JProgressBar progressBar;
    private FileTableModel fileTableModel;
    private File clipboardFile;
    private boolean isCutOperation; 
    private ListSelectionListener listSelectionListener;
    private boolean cellSizesSet = false;
    private int rowIconPadding = 6;
    private JButton openFile;
    private JButton editFile;
    private JButton deleteFile;
    private JButton newFile;
    private JButton copyFile;
    private JLabel fileName;
    private JTextField path;
    private JLabel date;
    private JLabel size;
    private JCheckBox readable;
    private JCheckBox writable;
    private JCheckBox executable;
    private JRadioButton isDirectory;
    private JRadioButton isFile;
    private JPanel newFilePanel;
    private JRadioButton newTypeFile;
    private JTextField name;
    Login log;
   
    

    public Container getGui() {
        if (gui == null) {
            gui = new JPanel(new BorderLayout(3, 3));
            gui.setBorder(new EmptyBorder(5, 5, 5, 5));

            fileSystemView = FileSystemView.getFileSystemView();
            desktop = Desktop.getDesktop();

            JPanel detailView = new JPanel(new BorderLayout(3, 3));
       

            table = new JTable();
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.setAutoCreateRowSorter(true);
            table.setShowVerticalLines(false);

            listSelectionListener =
                    new ListSelectionListener() {
                        @Override
                        public void valueChanged(ListSelectionEvent lse) {
                            int row = table.getSelectionModel().getLeadSelectionIndex();
                            setFileDetails(((FileTableModel) table.getModel()).getFile(row));
                        }
                    };
            table.getSelectionModel().addListSelectionListener(listSelectionListener);
            JScrollPane tableScroll = new JScrollPane(table);
            Dimension d = tableScroll.getPreferredSize();
            tableScroll.setPreferredSize(
                    new Dimension((int) d.getWidth(), (int) d.getHeight() / 2));
            detailView.add(tableScroll, BorderLayout.CENTER);

            // el file choser
            String rutaDirectorioUsuario;
            if (log.UserLoging != null && log.UserLoging.equals("admin")) {
           
                rutaDirectorioUsuario = "Z/";
                
            } else {
                rutaDirectorioUsuario = "Z/"+log.UserLoging ;
               
            }
        
           DefaultMutableTreeNode root = createTreeNode(new File(rutaDirectorioUsuario));
           treeModel = new DefaultTreeModel(root);
            
            

            TreeSelectionListener treeSelectionListener =
                    new TreeSelectionListener() {
                        public void valueChanged(TreeSelectionEvent tse) {
                            DefaultMutableTreeNode node =
                                    (DefaultMutableTreeNode) tse.getPath().getLastPathComponent();
                            showChildren(node);
                            setFileDetails((File) node.getUserObject());
                        }
                    };

            // muestra las rutas de los archivos.
            File[] roots = fileSystemView.getRoots();
            for (File fileSystemRoot : roots) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(fileSystemRoot);
                root.add(node);
           
                File[] files = fileSystemView.getFiles(fileSystemRoot, true);
                for (File file : files) {
                    if (file.isDirectory()) {
                        node.add(new DefaultMutableTreeNode(file));
                    }
                }

            }

            tree = new JTree(treeModel);
            tree.setRootVisible(false);
            tree.addTreeSelectionListener(treeSelectionListener);
            tree.setCellRenderer(new FileTreeCellRenderer());
            tree.expandRow(0);
            JScrollPane treeScroll = new JScrollPane(tree);

            tree.setVisibleRowCount(15);

            Dimension preferredSize = treeScroll.getPreferredSize();
            Dimension widePreferred = new Dimension(200, (int) preferredSize.getHeight());
            treeScroll.setPreferredSize(widePreferred);

            JPanel fileMainDetails = new JPanel(new BorderLayout(4, 2));
            fileMainDetails.setBorder(new EmptyBorder(0, 6, 0, 6));

            JPanel fileDetailsLabels = new JPanel(new GridLayout(0, 1, 2, 2));
            fileMainDetails.add(fileDetailsLabels, BorderLayout.WEST);

            JPanel fileDetailsValues = new JPanel(new GridLayout(0, 1, 2, 2));
            fileMainDetails.add(fileDetailsValues, BorderLayout.CENTER);

            fileDetailsLabels.add(new JLabel("File", JLabel.TRAILING));
            fileName = new JLabel();
            fileDetailsValues.add(fileName);
            fileDetailsLabels.add(new JLabel("Path/name", JLabel.TRAILING));
            path = new JTextField(5);
            path.setEditable(false);
            fileDetailsValues.add(path);
            fileDetailsLabels.add(new JLabel("Last Modified", JLabel.TRAILING));
            date = new JLabel();
            fileDetailsValues.add(date);
            fileDetailsLabels.add(new JLabel("File size", JLabel.TRAILING));
            size = new JLabel();
            fileDetailsValues.add(size);
            fileDetailsLabels.add(new JLabel("Type", JLabel.TRAILING));

            JPanel flags = new JPanel(new FlowLayout(FlowLayout.LEADING, 4, 0));
            isDirectory = new JRadioButton("Directory");
            isDirectory.setEnabled(false);
            flags.add(isDirectory);

            isFile = new JRadioButton("File");
            isFile.setEnabled(false);
            flags.add(isFile);
            fileDetailsValues.add(flags);

            int count = fileDetailsLabels.getComponentCount();
            for (int ii = 0; ii < count; ii++) {
                fileDetailsLabels.getComponent(ii).setEnabled(false);
            }

            JToolBar toolBar = new JToolBar();
            toolBar.setFloatable(false);

            openFile = new JButton("Open");
            openFile.setMnemonic('o');

            openFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    try {
                         if (currentFile.isDirectory()) {               
                        openDirectoryInNewWindow(currentFile);
                         }else if (isImageFile(currentFile)) {
                            openInImageViewer(currentFile);
                        } else if (isTextFile(currentFile)) {
                            openInTextEditor(currentFile);
                        } else if (isMP3File(currentFile)) {
                            addMP3ToPlaylist(currentFile);
                        } else {
                            desktop.open(currentFile);
                        }
                    } catch (Throwable t) {
                        showThrowable(t);
                    }
                    gui.repaint();
                }
            });
            toolBar.add(openFile);

            editFile = new JButton("Edit");
            editFile.setMnemonic('e');
            editFile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            try {
                                desktop.edit(currentFile);
                            } catch (Throwable t) {
                                showThrowable(t);
                            }
                        }
                    });
            toolBar.addSeparator();
            
            JButton copyButton = new JButton("Copy");
            copyButton.setMnemonic('y');
            copyButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    copyFile();
                     
                }
            });
            toolBar.add(copyButton);


            JButton cutButton = new JButton("Cut");
            cutButton.setMnemonic('t');
            cutButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    cutFile(); 
                    gui.repaint();
                    
                }
            });
            toolBar.add(cutButton);
            
            JButton pasteButton = new JButton("Paste");
            pasteButton.setMnemonic('p');
            pasteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    pasteFile();
                     gui.repaint();
                }
            });
            toolBar.add(pasteButton);


 
            openFile.setEnabled(desktop.isSupported(Desktop.Action.OPEN));
            editFile.setEnabled(desktop.isSupported(Desktop.Action.EDIT));


            toolBar.addSeparator();

            newFile = new JButton("New");
            newFile.setMnemonic('n');
            newFile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            newFile();
                        }
                    });
            toolBar.add(newFile);

            copyFile = new JButton("Copy");
            copyFile.setMnemonic('c');
            copyFile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            showErrorMessage("'Copy' not implemented.", "Not implemented.");
                        }
                    });


            JButton renameFile = new JButton("Rename");
            renameFile.setMnemonic('r');
            renameFile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            renameFile();
                        }
                    });
            toolBar.add(renameFile);

            deleteFile = new JButton("Delete");
            deleteFile.setMnemonic('d');
            deleteFile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            deleteFile();
                        }
                    });
            toolBar.add(deleteFile);

            toolBar.addSeparator();

            readable = new JCheckBox("Read  ");
            readable.setMnemonic('a');
             readable.setEnabled(false);


            writable = new JCheckBox("Write  ");
            writable.setMnemonic('w');
             writable.setEnabled(false);


            executable = new JCheckBox("Execute");
            executable.setMnemonic('x');
             executable.setEnabled(false);

            JPanel fileView = new JPanel(new BorderLayout(3, 3));

            fileView.add(toolBar, BorderLayout.NORTH);
            fileView.add(fileMainDetails, BorderLayout.CENTER);

            detailView.add(fileView, BorderLayout.SOUTH);

            JSplitPane splitPane =
                    new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScroll, detailView);
            gui.add(splitPane, BorderLayout.CENTER);

            JPanel simpleOutput = new JPanel(new BorderLayout(3, 3));
            progressBar = new JProgressBar();
            simpleOutput.add(progressBar, BorderLayout.EAST);
            progressBar.setVisible(false);

            gui.add(simpleOutput, BorderLayout.SOUTH);
        }
        return gui;
    }
    
    private DefaultMutableTreeNode createTreeNode(File rootFile) {
    DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootFile);
    File[] files = fileSystemView.getFiles(rootFile, true);

    for (File file : files) {
        if (file.isDirectory()) {
            root.add(new DefaultMutableTreeNode(file));
        }
    }

    return root;
}


    public void showRootFile() {
        tree.setSelectionInterval(0, 0);
    }

    private TreePath findTreePath(File find) {
        for (int ii = 0; ii < tree.getRowCount(); ii++) {
            TreePath treePath = tree.getPathForRow(ii);
            Object object = treePath.getLastPathComponent();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
            File nodeFile = (File) node.getUserObject();

            if (nodeFile.equals(find)) {
                return treePath;
            }
        }
        return null;
    }

    private void renameFile() {
        if (currentFile == null) {
            showErrorMessage("No file selected to rename.", "Select File");
            return;
        }

        String renameTo = JOptionPane.showInputDialog(gui, "New Name");
        if (renameTo != null) {
            try {
                boolean directory = currentFile.isDirectory();
                TreePath parentPath = findTreePath(currentFile.getParentFile());
                DefaultMutableTreeNode parentNode =
                        (DefaultMutableTreeNode) parentPath.getLastPathComponent();

                boolean renamed =
                        currentFile.renameTo(new File(currentFile.getParentFile(), renameTo));
                if (renamed) {
                    if (directory) {

                        TreePath currentPath = findTreePath(currentFile);
                        System.out.println(currentPath);
                        DefaultMutableTreeNode currentNode =
                                (DefaultMutableTreeNode) currentPath.getLastPathComponent();

                        treeModel.removeNodeFromParent(currentNode);

                    }

                    showChildren(parentNode);
                } else {
                    String msg = "The file '" + currentFile + "' could not be renamed.";
                    showErrorMessage(msg, "Rename Failed");
                }
            } catch (Throwable t) {
                showThrowable(t);
            }
        }
        gui.repaint();
    }

private void deleteFile() {
    if (currentFile == null) {
        showErrorMessage("No file selected for deletion.", "Select File");
        return;
    }
    

    int result =
            JOptionPane.showConfirmDialog(
                    gui,
                    "Are you sure you want to delete this file?",
                    "Delete File",
                    JOptionPane.ERROR_MESSAGE);
    if (result == JOptionPane.OK_OPTION) {
        try {
            System.out.println("currentFile: " + currentFile);
            Path filePath = currentFile.toPath(); // Convert File to Path

            TreePath parentPath = findTreePath(currentFile.getParentFile());
            System.out.println("parentPath: " + parentPath);
            DefaultMutableTreeNode parentNode =
                    (DefaultMutableTreeNode) parentPath.getLastPathComponent();
            System.out.println("parentNode: " + parentNode);

            boolean directory = Files.isDirectory(filePath); // Check if it's a directory
            Files.delete(filePath); // Delete the file or directory

            if (directory) {
                TreePath currentPath = findTreePath(currentFile);
                System.out.println(currentPath);
                
                DefaultMutableTreeNode currentNode =
                        (DefaultMutableTreeNode) currentPath.getLastPathComponent();

                treeModel.removeNodeFromParent(currentNode);
            }

            showChildren(parentNode);
        } catch (IOException e) {
            String msg = "The file '" + currentFile + "' could not be deleted.";
            showErrorMessage(msg, "Delete Failed");
        } catch (Throwable t) {
            showThrowable(t);
        }
    }
    gui.repaint();
}

    private void newFile() {
        if (currentFile == null) {
            showErrorMessage("No location selected for new file.", "Select Location");
            return;
        }

        if (newFilePanel == null) {
            newFilePanel = new JPanel(new BorderLayout(3, 3));

            JPanel southRadio = new JPanel(new GridLayout(1, 0, 2, 2));
            newTypeFile = new JRadioButton("File", true);
            JRadioButton newTypeDirectory = new JRadioButton("Directory");
            ButtonGroup bg = new ButtonGroup();
            bg.add(newTypeFile);
            bg.add(newTypeDirectory);
            southRadio.add(newTypeFile);
            southRadio.add(newTypeDirectory);

            name = new JTextField(15);

            newFilePanel.add(new JLabel("Name"), BorderLayout.WEST);
            newFilePanel.add(name);
            newFilePanel.add(southRadio, BorderLayout.SOUTH);
        }

        int result =
                JOptionPane.showConfirmDialog(
                        gui, newFilePanel, "Create File", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                boolean created;
                File parentFile = currentFile;
                if (!parentFile.isDirectory()) {
                    parentFile = parentFile.getParentFile();
                }
                File file = new File(parentFile, name.getText());
                if (newTypeFile.isSelected()) {
                    created = file.createNewFile();
                } else {
                    created = file.mkdir();
                }
                if (created) {

                    TreePath parentPath = findTreePath(parentFile);
                    DefaultMutableTreeNode parentNode =
                            (DefaultMutableTreeNode) parentPath.getLastPathComponent();

                    if (file.isDirectory()) {
                        // add the new node..
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);

                        TreePath currentPath = findTreePath(currentFile);
                        if (currentPath != null) {
                            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) currentPath.getLastPathComponent();
                            treeModel.insertNodeInto(newNode, parentNode, parentNode.getChildCount());
                        }
                    }

                    showChildren(parentNode);
                } else {
                    String msg = "The file '" + file + "' could not be created.";
                    showErrorMessage(msg, "Create Failed");
                }
            } catch (Throwable t) {
            }
        }
        gui.repaint();
    }

    private void showErrorMessage(String errorMessage, String errorTitle) {
        JOptionPane.showMessageDialog(gui, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
    }

    private void showThrowable(Throwable t) {
        t.printStackTrace();
        JOptionPane.showMessageDialog(gui, t.toString(), t.getMessage(), JOptionPane.ERROR_MESSAGE);
        gui.repaint();
    }

    /** Update the table on the EDT */
    private void setTableData(final File[] files) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        if (fileTableModel == null) {
                            fileTableModel = new FileTableModel();
                            table.setModel(fileTableModel);
                        }
                        table.getSelectionModel()
                                .removeListSelectionListener(listSelectionListener);
                        fileTableModel.setFiles(files);
                        table.getSelectionModel().addListSelectionListener(listSelectionListener);
                        if (!cellSizesSet) {
                            try {
                                Icon icon = fileSystemView.getSystemIcon(files[0]);

                            table.setRowHeight(icon.getIconHeight() + rowIconPadding);

                            setColumnWidth(0, -1);
                            setColumnWidth(3, 60);
                            table.getColumnModel().getColumn(3).setMaxWidth(120);
                            setColumnWidth(4, -1);
                            setColumnWidth(5, -1);
                            setColumnWidth(6, -1);
                            setColumnWidth(7, -1);
                            setColumnWidth(8, -1);
                            setColumnWidth(9, -1);

                            cellSizesSet = true;
                            } catch (Exception e) {
                            }
                            
                        }
                    }
                });
    }

  private void setColumnWidth(int column, int width) {
     try {
      TableColumn tableColumn = table.getColumnModel().getColumn(column);
     
          if (width < 0) {

        JLabel label = new JLabel((String) tableColumn.getHeaderValue());
        Dimension preferred = label.getPreferredSize();

        width = (int) preferred.getWidth() + 14;
    }
    tableColumn.setPreferredWidth(width);
    tableColumn.setMaxWidth(width);
    tableColumn.setMinWidth(width);
      } catch (Exception e) {
          
          
      }
    
}



    /**
     * Add the files that are contained within the directory of this node. Thanks to Hovercraft Full
     * Of Eels.
     */
    private void showChildren(final DefaultMutableTreeNode node) {
        tree.setEnabled(false);
        progressBar.setVisible(true);
        progressBar.setIndeterminate(true);

        SwingWorker<Void, File> worker =
                new SwingWorker<Void, File>() {
                    @Override
                    public Void doInBackground() {
                        File file = (File) node.getUserObject();
                        if (file.isDirectory()) {
                            File[] files = fileSystemView.getFiles(file, true); // !!
                            if (node.isLeaf()) {
                                for (File child : files) {
                                    if (child.isDirectory()) {
                                        publish(child);
                                    }
                                }
                            }
                            setTableData(files);
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<File> chunks) {
                        for (File child : chunks) {
                            node.add(new DefaultMutableTreeNode(child));
                        }
                    }

                    @Override
                    protected void done() {
                        progressBar.setIndeterminate(false);
                        progressBar.setVisible(false);
                        tree.setEnabled(true);
                    }
                };
        worker.execute();
    }


    private void setFileDetails(File file) {
        currentFile = file;
        Icon icon = fileSystemView.getSystemIcon(file);
        fileName.setIcon(icon);
        fileName.setText(fileSystemView.getSystemDisplayName(file));
        path.setText(file.getPath());
        date.setText(new Date(file.lastModified()).toString());
        size.setText(file.length() + " bytes");
        readable.setSelected(file.canRead());
        writable.setSelected(file.canWrite());
        executable.setSelected(file.canExecute());
        isDirectory.setSelected(file.isDirectory());
        isFile.setSelected(file.isFile());

        JFrame f = (JFrame) gui.getTopLevelAncestor();
        if (f != null) {
            f.setTitle(APP_TITLE + " : " + fileSystemView.getSystemDisplayName(file));
        }
        
        gui.repaint();
    }
    
    
        private boolean isImageFile(File file) {
        String fileName = file.getName().toLowerCase();
        return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
               fileName.endsWith(".png") || fileName.endsWith(".gif") ||
               fileName.endsWith(".bmp") || fileName.endsWith(".tiff");
        }
    
        private boolean isTextFile(File file) {
           return file.getName().toLowerCase().endsWith(".txt");
       }
     
     
            private boolean isMP3File(File file) {
            String fileName = file.getName().toLowerCase();
            return fileName.endsWith(".mp3");
        }

   
            private void addMP3ToPlaylist(File mp3File) {
            try {
                player.Spotify spotify = new Spotify();
                spotify.setVisible(true);
                Inicio.pantalladeinicio.add(spotify);
                spotify.toFront();
                if (spotify != null) {
                    
                    spotify.pl.addSong(mp3File);
                    
                    spotify.updateList();

                    if (spotify.a == 0) {
                        spotify.putar();
                    }
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

     private void openInTextEditor(File file) {
        texteditor.texteditor editor = new texteditor.texteditor();
        editor.setVisible(true);
        editor.setTitle(file.getName());
        Inicio.pantalladeinicio.add(editor);

        try {
            FileInputStream fis = new FileInputStream(file);
            editor.panel.read(fis, null);
            fis.close();
        } catch (IOException e) {

        }

        try {
            editor.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {

            e.printStackTrace();
        }
    }

    private void openInImageViewer(File imageFile) {
     try {
         JInternalFrame internalFrame = new JInternalFrame("Imagen"+currentFile.toString(), true, true, true, true);
         internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
         internalFrame.setBackground(Color.GRAY);
         BufferedImage image = ImageIO.read(imageFile);
         int initialWidth = 816;
         int initialHeight = 504;
         Image scaledImage = image.getScaledInstance(initialWidth, initialHeight, Image.SCALE_SMOOTH);
         ImageIcon scaledIcon = new ImageIcon(scaledImage);
         JLabel label = new JLabel(scaledIcon);
         internalFrame.getContentPane().add(label, BorderLayout.CENTER);
         internalFrame.setSize(initialWidth, initialHeight);
         Inicio.pantalladeinicio.add(internalFrame);
         internalFrame.toFront();
         internalFrame.setVisible(true);
     } catch (Exception e) {
         System.out.println("No se pudo abrir el JInternalFrame: " + e.getMessage());
     }
 }
        private void openDirectoryInNewWindow(File directory) {
         JInternalFrame internalFrame = new JInternalFrame("Explorer", true, true, true, true);
    
        FileManager newFileManager = new FileManager();
        Container newGui = newFileManager.getGui();

        // abre el nuevo directorio 
        newFileManager.setCurrentDirectory(directory);

        internalFrame.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        internalFrame.getContentPane().add(newGui);
        internalFrame.pack();
        internalFrame.setVisible(true);
        Inicio.pantalladeinicio.add(internalFrame);
        internalFrame.toFront();
     }
        
        public void setCurrentDirectory(File directory) {
            currentFile = directory;
            showChildren(createTreeNode(directory));
            setFileDetails(directory);
        }





        private void copyFile() {
        if (currentFile == null) {
            showErrorMessage("No file selected for copy.", "Select File");
            return;
        }

        clipboardFile = currentFile;
        isCutOperation = false;
         gui.repaint();
    }

    private void pasteFile() {
        if (clipboardFile == null) {
            showErrorMessage("Clipboard is empty.", "Empty Clipboard");
            return;
        }

        if (currentFile == null || !currentFile.isDirectory()) {
            showErrorMessage("Cannot paste into a non-directory location.", "Invalid Location");
            return;
        }

        try {
            File destination = new File(currentFile, clipboardFile.getName());

            if (isCutOperation) {

                Files.move(clipboardFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                isCutOperation = false; // Reset cut operation after pasting
            } else {

                Files.copy(clipboardFile.toPath(), destination.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
            }
            
             TreePath parentPath = findTreePath(currentFile);
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) parentPath.getLastPathComponent();
            showChildren(parentNode);
            gui.repaint();
        } catch (IOException e) {
            showErrorMessage("Error during paste operation.", "Paste Error");
            
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Paste correct", null, JOptionPane.INFORMATION_MESSAGE);

            gui.repaint();
            
        }
        
    }

    private void cutFile() {
        if (currentFile == null) {
            showErrorMessage("No file selected for cut.", "Select File");
            return;
        }

        clipboardFile = currentFile;
        isCutOperation = true;
        gui.repaint();
    }


        public static boolean copyFile(File from, File to) throws IOException {

            boolean created = to.createNewFile();

            if (created) {
                FileChannel fromChannel = null;
                FileChannel toChannel = null;
                try {
                    fromChannel = new FileInputStream(from).getChannel();
                    toChannel = new FileOutputStream(to).getChannel();

                    toChannel.transferFrom(fromChannel, 0, fromChannel.size());

                    to.setReadable(from.canRead());
                    to.setWritable(from.canWrite());
                    to.setExecutable(from.canExecute());
                    
                } finally {
                    if (fromChannel != null) {
                        fromChannel.close();
                    }
                    if (toChannel != null) {
                        toChannel.close();
                    }
                    return false;
                }
            }
            return created;
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception weTried) {
                        }
                        JFrame f = new JFrame(APP_TITLE);
                        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                        FileManager fileManager = new FileManager();
                        f.setContentPane(fileManager.getGui());

                        try {
                            URL urlBig = fileManager.getClass().getResource("fm-icon-32x32.png");
                            URL urlSmall = fileManager.getClass().getResource("fm-icon-16x16.png");
                            ArrayList<Image> images = new ArrayList<Image>();
                            images.add(ImageIO.read(urlBig));
                            images.add(ImageIO.read(urlSmall));
                            f.setIconImages(images);
                        } catch (Exception weTried) {
                        }

                        f.pack();
                        f.setLocationByPlatform(true);
                        f.setMinimumSize(f.getSize());
                        f.setVisible(true);

                        fileManager.showRootFile();
                    }
                });
    }
}

//tabla de archivos 
class FileTableModel extends AbstractTableModel {

    private File[] files;
    private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    private String[] columns = {
        "Icon", "File", "Path/name", "Size", "Last Modified"/*, "R", "W", "E", "D", "F"*/,
    };

    FileTableModel() {
        this(new File[0]);
    }

    FileTableModel(File[] files) {
        this.files = files;
    }

    public Object getValueAt(int row, int column) {
        File file = files[row];
        switch (column) {
            case 0:
                return fileSystemView.getSystemIcon(file);
            case 1:
                return fileSystemView.getSystemDisplayName(file);
            case 2:
                return file.getPath();
            case 3:
                return file.length();
            case 4:
                return file.lastModified();
            case 5:
                return file.canRead();
            case 6:
                return file.canWrite();
            case 7:
                return file.canExecute();
            case 8:
                return file.isDirectory();
            case 9:
                return file.isFile();
            default:
                System.err.println("Logic Error");
        }
        return "";
    }

    public int getColumnCount() {
        return columns.length;
    }

    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return ImageIcon.class;
            case 3:
                return Long.class;
            case 4:
                return Date.class;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return Boolean.class;
        }
        return String.class;
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public int getRowCount() {
        return files.length;
    }

    public File getFile(int row) {
        return files[row];
    }

    public void setFiles(File[] files) {
        this.files = files;
        fireTableDataChanged();
    }
}


class FileTreeCellRenderer extends DefaultTreeCellRenderer {

    private FileSystemView fileSystemView;

    private JLabel label;

    FileTreeCellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        File file = (File) node.getUserObject();
        label.setIcon(fileSystemView.getSystemIcon(file));
        label.setText(fileSystemView.getSystemDisplayName(file));
        label.setToolTipText(file.getPath());

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }
}
