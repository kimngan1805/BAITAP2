package View;
import Controller.Controller;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Views extends JFrame {
    private JTextArea textArea = new JTextArea();
    private JTree fileTree;

    public Views() {
        super("Notepad");
        JScrollPane textScrollPane = new JScrollPane(this.textArea);
        this.fileTree = new JTree();
        this.fileTree.getSelectionModel().setSelectionMode(1);
        JScrollPane fileScrollPane = new JScrollPane(this.fileTree);
        JButton openButton = new JButton("Open");
        openButton.addActionListener((e) -> {
            this.openFile();
        });
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener((e) -> {
            this.saveFile();
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener((e) -> {
            System.exit(0);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exitButton);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(fileScrollPane, "West");
        contentPane.add(textScrollPane, "Center");
        contentPane.add(buttonPanel, "South");
        this.setDefaultCloseOperation(3);
        this.setSize(600, 400);
        this.setLocationRelativeTo((Component)null);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == 0) {
            File selectedFile = fileChooser.getSelectedFile();
            List<String> content = Controller.loadFromFile(selectedFile.getAbsolutePath());
            if (content != null) {
                StringBuilder sb = new StringBuilder();
                Iterator var6 = content.iterator();

                while(var6.hasNext()) {
                    String line = (String)var6.next();
                    sb.append(line).append("\n");
                }

                this.textArea.setText(sb.toString());
            }
        }

    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == 0) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            String content = this.textArea.getText();

            try {
                Controller.saveToFile(fileName, content);
                JOptionPane.showMessageDialog(this, "File saved successfully!");
            } catch (Exception var7) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + var7.getMessage(), "Error", 0);
                var7.printStackTrace();
            }
        }

    }

    



    public static void main(String[] args) throws IOException {
        FileReader in=null;
        FileWriter out=null;
        Scanner scanner = new Scanner(System.in);
        SwingUtilities.invokeLater(() -> {
            Views textView = new Views ();
            textView.setVisible(true);
        });
    }
}
