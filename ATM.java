package Package1;

import javax.swing.*;
import java.io.IOException;

public class ATM extends ATM_GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ATM_GUI atmGui = new ATM_GUI();
            atmGui.setVisible(true);
        });
    }
}
