package ui.gui.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

// load the custom font
// from https://fontsfree.net/apple-ligothic-medium-font-download.html
public class CustomFont {

    private static final String PATH = "./data/others/Apple-LiGothicMedium.ttf";
    private static Font customFont;

    // EFFECTS : create Apple LiGothic Medium font
    public CustomFont() {
        File fontFile = new File(PATH);
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            customFont = font.deriveFont(20f);
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            environment.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    // EFFECTS : get and set font size
    public Font getFont(float size) {
        return customFont.deriveFont(size);
    }
}
