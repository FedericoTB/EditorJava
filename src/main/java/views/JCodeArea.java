package views;

import models.Document;

import javax.swing.*;
import javax.swing.text.View;

public class JCodeArea extends JScrollPane {

    private JTextArea jTextArea;
    public JCodeArea(Document document){
        this.jTextArea = new JTextArea("asdasdsa");
        this.jTextArea.setText(document.getContent());
        this.add(this.jTextArea);
        this.setViewportView(jTextArea);
    }

    public JTextArea getJTextArea() {
        return jTextArea;
    }

    public void setJTextArea(JTextArea jTextPane) {
        this.jTextArea = jTextPane;
    }
}
