package views;


import controllers.Functions;
import lombok.Data;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;

@Data
public class Menu {
    private MainView container;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem mFNew;
    private JMenuItem mFOpen;
    private JMenuItem mFSave;
    private JMenuItem mFSaveAs;
    private JMenuItem mFPrint;
    private JMenuItem mFClose;
    private JMenuItem mFExit;

    private JMenu menuEdit;
    private JMenuItem mECopy;
    private JMenuItem mECut;
    private JMenuItem mEPaste;

    private JMenu menuView;

    private JMenu menuTools;
    private JMenuItem mTRun;

    private JMenuItem mTCompile;

    private JMenu menuHelp;
    private JMenuItem mHAbout;
    private JMenuItem mHHelp;

    public Menu(MainView parent) {
        this.container = parent;
        Functions func = Functions.getInstance();
        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        mFNew = new JMenuItem("New");
        menuFile.add(mFNew);
        mFOpen = new JMenuItem("Open");
        menuFile.add(mFOpen);
        mFSave = new JMenuItem("Save");
        menuFile.add(mFSave);
        mFSaveAs = new JMenuItem("Save As");
        menuFile.add(mFSaveAs);
        mFPrint = new JMenuItem("Print");
        menuFile.add(mFPrint);
        mFClose = new JMenuItem("Close");
        menuFile.add(mFClose);
        mFExit = new JMenuItem("Exit");
        menuFile.add(mFExit);

        menuEdit = new JMenu("Edit");
        mECopy = new JMenuItem("Copy");
        menuEdit.add(mECopy);
        mECut = new JMenuItem("Cut");
        menuEdit.add(mECut);
        mEPaste = new JMenuItem("Paste");
        menuEdit.add(mEPaste);

        menuView = new JMenu("View");

        menuTools = new JMenu("Tools");
        mTRun = new JMenuItem("Run");
        menuTools.add(mTRun);
        mTCompile = new JMenuItem("Compile");
        menuTools.add(mTCompile);

        menuHelp = new JMenu("Help");
        mHAbout = new JMenuItem("About");
        menuHelp.add(mHAbout);
        mHHelp = new JMenuItem("Help");
        menuHelp.add(mHHelp);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuView);
        menuBar.add(menuTools);
        menuBar.add(menuHelp);

        mFNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    func.newFile();
                    container.getDocumentPane().addTab(func.getDocuments().get(func.getDocuments().size()).getTitle()+func.getDocuments().get(func.getDocuments().size()).getType(),new JCodeArea(func.getDocuments().get(func.getDocuments().size())));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        mFOpen.addActionListener(e -> {
            try {
                func.getDocuments().put(func.getDocuments().size()+1,func.openFile());
                container.getDocumentPane().addTab(func.getDocuments().get(func.getDocuments().size()).getTitle(),new JCodeArea(func.getDocuments().get(func.getDocuments().size())));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        mFSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    container.getFunc().saveAs(container.getFunc().getDocuments().get(1));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
       mFPrint.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
                   container.getFunc().printFile((JCodeArea) container.getDocumentPane().getSelectedComponent());
               } catch (PrinterException ex) {
                   ex.printStackTrace();
               }
           }
        });
        mFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.dispose();
            }
        });

    }
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }
}
