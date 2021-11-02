package views;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProContrastIJTheme;
import lombok.*;
import controllers.Functions;

@Data
public class MainView extends JFrame {
    private Functions func;
    private JPanel mainPanel;
    private Menu menu;
    private JMenuBar menuBar;

    private JTabbedPane documentPane;
    private JCodeArea documentTextArea;
    private JScrollPane documentScrollPane;

    private JTextArea consoleTextArea;

    public MainView() throws IOException {
        initComponents();}

    public void initComponents() throws IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        try {
            UIManager.setLookAndFeel( new FlatMonokaiProContrastIJTheme());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        func= Functions.getInstance();
        mainPanel = new JPanel();
        menu = new Menu(this);

        documentPane = new JTabbedPane();
        documentTextArea = new JCodeArea(func.getDocuments().get(1));
        documentPane.add(func.getDocuments().get(1).getTitle()+func.getDocuments().get(1).getType(),documentTextArea);
        documentPane.setBorder(new EtchedBorder());

        consoleTextArea = new JTextArea();
        consoleTextArea.setRows(5);
        consoleTextArea.setBorder(new EtchedBorder());
        consoleTextArea.setText("CONSOLE");
        consoleTextArea.setEditable(false);

        mainPanel.setLayout(new BorderLayout());
        this.add(mainPanel);
        this.setJMenuBar(menu.getMenuBar());
        mainPanel.add(documentPane,BorderLayout.CENTER);
        mainPanel.add(consoleTextArea,BorderLayout.SOUTH);

        pack();
    }

    public Functions getFunc() {
        return func;
    }

    public void setFunc(Functions func) {
        this.func = func;
    }

    public JTabbedPane getDocumentPane() {
        return documentPane;
    }

    public void setDocumentPane(JTabbedPane documentPane) {
        this.documentPane = documentPane;
    }

    public JTextArea getConsoleTextArea() {
        return consoleTextArea;
    }

    public void setConsoleTextArea(JTextArea consoleTextArea) {
        this.consoleTextArea = consoleTextArea;
    }
}
