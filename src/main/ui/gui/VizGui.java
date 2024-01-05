package ui.gui;

import ui.gui.page.LandingPage;
import ui.gui.page.LoadingPage;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import ui.gui.util.RelativeTo;
import ui.gui.util.TextToSpeech;

import javax.swing.*;
import java.awt.*;

// driver class for GUI
public class VizGui {

    private static JFrame landingFrame;
    private static JFrame loadingFrame;

    private static JLayeredPane loadingPane;

    private final CustomFont customFont;

    private static int layer;

    // EFFECTS : creating all the GUI components
    public VizGui() {
        customFont = new CustomFont();
        landingFrame = new JFrame("Viz!");
        loadingFrame = new JFrame("Viz!");
        loadingPane = new JLayeredPane();
        layer = 1;

        LoadImage.loadImage();
        loading();
        run();
    }

    // EFFECTS : running the landing page
    private void run() {
        LandingPage landingPage = new LandingPage();
        Thread landingPageThread = new Thread(landingPage);
        landingPageThread.start();
        setFrame(landingFrame);
        setNextButton();
        landingFrame.setVisible(false);
        landingFrame.add(landingPage);
    }

    // EFFECTS : running the loading page
    private void loading() {
        setPane(loadingPane);
        LoadingPage loadingPage = new LoadingPage();
        Thread loadingPageThread = new Thread(loadingPage);
        loadingPageThread.start();
        loadingPage.setBounds(0, 0, RelativeTo.relativeToWidth(0.9), RelativeTo.relativeToHeight(0.9));
        addToLoadingPane(loadingPage);
        loadingFrame.add(loadingPane);
        setFrame(loadingFrame);
    }

    // MODIFIES : this
    // EFFECTS : set the frame
    private void setFrame(JFrame f) {
        f.setSize(RelativeTo.relativeToWidth(0.9), RelativeTo.relativeToHeight(0.9));
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setUndecorated(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set the main pane
    private void setPane(JLayeredPane pane) {
        pane.setBackground(new Color(47, 62, 70, 0));
        pane.setLayout(null);
        pane.setOpaque(true);
    }

    // MODIFIES : this
    // EFFECTS : adding the component to the pane
    private void addToLoadingPane(Component c) {
        loadingPane.add(c);
        layer = JLayeredPane.getLayer((JComponent) c) + 1;
    }

    // MODIFIES : this
    // EFFECTS : adding the next button
    private void setNextButton() {
        JButton nextButton = new JButton("Next");
        nextButton.setFont(customFont.getFont(32f));
        nextButton.setForeground(new Color(202, 210, 197));
        nextButton.setOpaque(false);
        nextButton.setBorderPainted(false);
        nextButton.setFocusPainted(false);
        nextButton.setSize(RelativeTo.relativeToWidth(0.18), RelativeTo.relativeToHeight(0.2));
        nextButton.setLocation(RelativeTo.relativeToWidth(0.36), RelativeTo.relativeToHeight(0.65));
        nextButton.addActionListener(e -> {
            landingFrame.setVisible(true);
            TextToSpeech.speak("Welcome to Viz");
            loadingFrame.dispose();
        });
        loadingPane.setLayer(nextButton, layer);
        loadingPane.add(nextButton);
        layer = JLayeredPane.getLayer(nextButton) + 1;
    }
}
