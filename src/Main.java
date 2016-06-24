import com.java.gui.MainWindow;

        import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {

        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
        mainWindow.setResizable(false);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
