package views;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;

public class FileBrowser {
    private DefaultMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;
    private String defaultDirectory;

    public FileBrowser() {
        defaultDirectory = "C:\\";
        root = new DefaultMutableTreeNode(new File(defaultDirectory));

    }
}
