package controllers;
import models.Document;
import views.JCodeArea;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.print.PrinterException;
import java.io.*;
import java.util.HashMap;

public class Functions {
    private static Functions instance;
    private HashMap<Integer,Document> documents;

    private Functions() {
        this.documents = new HashMap<>();
        try {
            this.documents.put(1, new Document("empty",
                    System.getProperty("user.dir")+File.separator+"src"+File.separator+
                            "main"+File.separator+ "resources"+File.separator, ".java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Functions getInstance() {
        if (instance == null) {
            instance = new Functions();
        }
        return instance;
    }

    public void newFile() throws IOException {
        this.documents.put(this.documents.size()+1, new Document("empty",
                System.getProperty("user.dir")+File.separator+"src"+File.separator+
                        "main"+File.separator+ "resources"+File.separator, ".java"));
    }
    public Document openFile() throws IOException {
        Document tempDocument = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos de java (*.java)", "java"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int selection = fileChooser.showOpenDialog(null);
        if (selection == JFileChooser.APPROVE_OPTION)
            tempDocument = new Document(fileChooser.getSelectedFile());
        return tempDocument;
    }
    public static void save(Document document,JTextArea jTextArea) {
        document.setContent(jTextArea.getText());
    }

    public void saveAs(Document document) throws IOException {
        JFileChooser saveChooser = new JFileChooser();
        saveChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        saveChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveChooser.addChoosableFileFilter(new FileNameExtensionFilter("Archivos de java (*.java)", "java"));
        saveChooser.setAcceptAllFileFilterUsed(false);
        File file = saveChooser.getSelectedFile();
        int selection = saveChooser.showSaveDialog(null);
        if (selection == JFileChooser.APPROVE_OPTION && file != null) {
            document.fileWriter(file,document);
        }
    }
    public void closeFile(JTabbedPane pane){

    }
    public void printFile(JCodeArea jTextArea) throws PrinterException {
        jTextArea.getJTextArea().print();
    }
    public void play(Document document, JTextArea terminal){

        try {

            String command = "cmd.exe /C java "+document.getFile().toString();
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line=br.readLine())!=null){
                terminal.setText(terminal.getText()+"\n"+line+"\n");
            }
        } catch (IOException e) {
            terminal.setText(e.getMessage());
        }
    }
    public HashMap<Integer, Document> getDocuments() {
        return documents;
    }

    public void setDocuments(HashMap<Integer, Document> documents) {
        this.documents = documents;
    }
}
