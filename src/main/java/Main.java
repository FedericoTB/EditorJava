import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new views.MainView().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
