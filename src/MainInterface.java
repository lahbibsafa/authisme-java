import lahbib.safa.frames.Login;

import javax.swing.*;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.utils.MaterialColors;

public class MainInterface {
    static {
        try {
            JDialog.setDefaultLookAndFeelDecorated(true);
            JFrame.setDefaultLookAndFeelDecorated(false); // not support yet
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
            //UIManager.setLookAndFeel(new MaterialLookAndFeel(new DarkStackOverflowTheme()));
            UIManager.put(
                    "Button.mouseHoverEnable",
                    true); // Because the test are more difficulte with effect mouse hover
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] a) {
        Login loginWindow = new Login();
        loginWindow.show();
    }

}