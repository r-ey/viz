package ui.gui.util;

import javax.swing.*;
import java.awt.*;

// class for calculate px to percentage relative to local
public final class RelativeTo {

    private static final double SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static final double SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    // EFFECTS : return screen width
    public static int getWidth() {
        return (int)(SCREEN_WIDTH);
    }

    // EFFECTS : return screen height
    public static int getHeight() {
        return (int)(SCREEN_HEIGHT);
    }

    // EFFECTS : return adjusted px based on local screen
    public static int relativeToWidth(double d) {
        return (int)(d * SCREEN_WIDTH);
    }

    // EFFECTS : return adjusted px based on local screen
    public static int relativeToHeight(double d) {
        return (int)(d * SCREEN_HEIGHT);
    }
}
