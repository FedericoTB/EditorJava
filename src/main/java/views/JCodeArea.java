package views;

import models.Document;

import javax.swing.*;

public class JCodeArea extends JScrollPane {

    private JTextArea jTextArea;
    public JCodeArea(Document document){
        this.
        this.jTextArea = new JTextArea();
        this.jTextArea.setText(document.getContent());
        this.add(this.jTextArea);
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public void setjTextArea(JTextArea jTextPane) {
        this.jTextArea = jTextPane;
    }
}
