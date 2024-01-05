package ui.gui.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// util class to load all the images
public final class LoadImage {

    private static final String PATH = "./data/img/";
    private static final String[] FILENAME = {"apple.png", "people.png", "gradient-big.png",
            "gradient-small.png", "landing-page-background.png"};

    private static final Map<String, JLabel> imageMap = new HashMap<>();

    // MODIFIES : this
    // EFFECTS : load all the images
    public static void loadImage() {
        String[] keys = {"apple", "people", "gradientBig", "gradientSmall", "landingPageBackground"};
        BufferedImage[] image = new BufferedImage[FILENAME.length];
        for (int i = 0; i < FILENAME.length; i++) {
            try {
                image[i] = ImageIO.read(new File(PATH + FILENAME[i]));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            if (i == 2 || i == 4) {  // background image and gif
                double originalRatio = (double)image[i].getWidth() / (image[i].getHeight());
                Image scaledImage = image[i].getScaledInstance((int)(RelativeTo.getHeight() * originalRatio),
                        RelativeTo.getHeight(), Image.SCALE_SMOOTH);
                imageMap.put(keys[i], new JLabel(new ImageIcon(scaledImage)));
            } else {
                imageMap.put(keys[i], new JLabel(new ImageIcon(image[i])));
            }
        }
    }

    public static Map<String, JLabel> getImageMap() {
        return imageMap;
    }
}
