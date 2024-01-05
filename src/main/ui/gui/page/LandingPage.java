package ui.gui.page;

import ui.gui.component.Line;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// create the landing page
public class LandingPage extends JLayeredPane implements Runnable {

    private static final double CONSTANT = 0.05;
    private CustomFont customFont;

    private int backgroundLayer;

    // EFFECTS : create the landing page panel
    @Override
    public void run() {
        customFont = new CustomFont();
        setBackground();
        setHeader();
        setCore();
        setButton();
        setCredits();
    }

    // MODIFIES : this
    // EFFECTS : setting the image background
    private void setBackground() {
        setLayout(null);
        JLabel background = LoadImage.getImageMap().get("landingPageBackground");
        background.setBounds(0, 0, RelativeTo.relativeToWidth(0.9), RelativeTo.relativeToHeight(0.9));
        add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : setting the header
    private void setHeader() {
        setHeaderViz();
        setHeaderWelcome();
    }

    // MODIFIES : this
    // EFFECTS : setting the core
    private void setCore() {
        setLeftLine();
        setCoreHeader();
        setRightLine();
    }

    // MODIFIES : this
    // EFFECTS : setting the button
    private void setButton() {
        setButtonText();

        setButtonTextLoad();
        setButtonLoad();
        setButtonLoadText();
        setButtonLoadSize();
        setButtonLoadSizeText();

        setButtonTextNew();
        setButtonNew();
        setButtonNewText();
        setButtonNewSize();
        setButtonNewSizeText();
    }

    // MODIFIES : this
    // EFFECTS : setting the text
    private void setButtonText() {
        setButtonComponent();
        setButtonButton();
    }

    // MODIFIES : this
    // EFFECTS : setting the credits
    private void setCredits() {
        setCreditsUnsplash();
        setCreditDribbble();
    }

    // MODIFIES : this
    // EFFECTS : setting the viz in the header
    private void setHeaderViz() {
        JLabel text = new JLabel("V I Z !");
        text.setFont(customFont.getFont(40f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.184), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(-0.02 + CONSTANT), RelativeTo.relativeToHeight(0.01));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the Welcome in the header
    private void setHeaderWelcome() {
        JLabel text = new JLabel("W E L C O M E");
        text.setFont(customFont.getFont(80f));
        text.setForeground(new Color(82, 121, 111, 127));
        text.setSize(RelativeTo.relativeToWidth(0.41), RelativeTo.relativeToHeight(0.13));
        text.setLocation(RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(-0.006));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the left line in the core
    private void setLeftLine() {
        Line line = new Line(RelativeTo.relativeToWidth(0.14 + CONSTANT), RelativeTo.relativeToHeight(0.32),
                RelativeTo.relativeToWidth(0.14 + CONSTANT), RelativeTo.relativeToHeight(0.38),3,
                new Color(205, 207, 109));
        line.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(line, backgroundLayer + 1);
        add(line);
    }

    // MODIFIES : this
    // EFFECTS : setting the right line in the core
    private void setRightLine() {
        Line line = new Line(RelativeTo.relativeToWidth(0.66 + CONSTANT), RelativeTo.relativeToHeight(0.32),
                RelativeTo.relativeToWidth(0.66 + CONSTANT), RelativeTo.relativeToHeight(0.38),3,
                new Color(205, 207, 109));
        line.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(line, backgroundLayer + 1);
        add(line);
    }

    // MODIFIES : this
    // EFFECTS : setting the header in the core
    private void setCoreHeader() {
        JLabel text = new JLabel("Hello There");
        text.setFont(customFont.getFont(60f));
        text.setForeground(new Color(202, 210, 197, 127));
        text.setSize(RelativeTo.relativeToWidth(0.54), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.32 + CONSTANT), RelativeTo.relativeToHeight(0.28));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the text in the button
    private void setButtonComponent() {
        JLabel text = new JLabel("Components #");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(132, 169, 140));
        text.setSize(RelativeTo.relativeToWidth(0.28), RelativeTo.relativeToHeight(0.08));
        text.setLocation(RelativeTo.relativeToWidth(0.07 + CONSTANT), RelativeTo.relativeToHeight(0.59));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the text in the button
    private void setButtonButton() {
        JLabel text = new JLabel("BUTTON");
        text.setFont(customFont.getFont(50f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.15), RelativeTo.relativeToHeight(0.11));
        text.setLocation(RelativeTo.relativeToWidth(0.09 + CONSTANT), RelativeTo.relativeToHeight(0.62));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button in the button
    private void setButtonLoad() {
        JButton button = new JButton();
        button.setBackground(new Color(82, 121, 111));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(e -> {
            LoadJson.loadExistingFile();
            new MainPage();
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        button.setSize(RelativeTo.relativeToWidth(0.14), RelativeTo.relativeToHeight(0.06));
        button.setLocation(RelativeTo.relativeToWidth(0.25 + CONSTANT), RelativeTo.relativeToHeight(0.65));
        setLayer(button, backgroundLayer + 1);
        add(button);
    }

    // MODIFIES : this
    // EFFECTS : setting the text in the button
    private void setButtonTextLoad() {
        JLabel text = new JLabel("Load");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.303 + CONSTANT), RelativeTo.relativeToHeight(0.64));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonLoadText() {
        JLabel text = new JLabel("Medium (0.8)");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(132, 169, 140));
        text.setSize(RelativeTo.relativeToWidth(0.12), RelativeTo.relativeToHeight(0.05));
        text.setLocation(RelativeTo.relativeToWidth(0.25 + CONSTANT), RelativeTo.relativeToHeight(0.7));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonLoadSize() {
        Line top = new Line(RelativeTo.relativeToWidth(0.4 + CONSTANT), RelativeTo.relativeToHeight(0.65),
                RelativeTo.relativeToWidth(0.41 + CONSTANT), RelativeTo.relativeToHeight(0.65), 2,
                new Color(205, 207, 109));
        Line middle = new Line(RelativeTo.relativeToWidth(0.405 + CONSTANT), RelativeTo.relativeToHeight(0.65),
                RelativeTo.relativeToWidth(0.405 + CONSTANT), RelativeTo.relativeToHeight(0.71), 2,
                new Color(205, 207, 109));
        Line bottom = new Line(RelativeTo.relativeToWidth(0.4 + CONSTANT), RelativeTo.relativeToHeight(0.71),
                RelativeTo.relativeToWidth(0.41 + CONSTANT), RelativeTo.relativeToHeight(0.71), 2,
                new Color(205, 207, 109));
        top.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        middle.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        bottom.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(top, backgroundLayer + 1);
        setLayer(middle, backgroundLayer + 1);
        setLayer(bottom, backgroundLayer + 1);
        add(bottom);
        add(middle);
        add(top);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonLoadSizeText() {
        JLabel text = new JLabel("40px");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(205, 207, 109));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.412 + CONSTANT), RelativeTo.relativeToHeight(0.645));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button in the button
    private void setButtonNew() {
        JButton button = new JButton();
        button.setBackground(new Color(82, 121, 111));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setSize(RelativeTo.relativeToWidth(0.17), RelativeTo.relativeToHeight(0.08));
        button.setLocation(RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(0.63));
        button.addActionListener(e -> {
            new ProfilePage();
            SwingUtilities.getWindowAncestor(this).dispose();
        });
        setLayer(button, backgroundLayer + 1);
        add(button);
    }

    // MODIFIES : this
    // EFFECTS : setting the text in the button
    private void setButtonTextNew() {
        JLabel text = new JLabel("New");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.548 + CONSTANT), RelativeTo.relativeToHeight(0.63));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonNewText() {
        JLabel text = new JLabel("Large (1.0)");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(132, 169, 140));
        text.setSize(RelativeTo.relativeToWidth(0.12), RelativeTo.relativeToHeight(0.05));
        text.setLocation(RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(0.7));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonNewSize() {
        Line left = new Line(RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(0.595),
                RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(0.615), 2,
                new Color(205, 207, 109));
        Line middle = new Line(RelativeTo.relativeToWidth(0.48 + CONSTANT), RelativeTo.relativeToHeight(0.605),
                RelativeTo.relativeToWidth(0.65 + CONSTANT), RelativeTo.relativeToHeight(0.605), 2,
                new Color(205, 207, 109));
        Line right = new Line(RelativeTo.relativeToWidth(0.65 + CONSTANT), RelativeTo.relativeToHeight(0.595),
                RelativeTo.relativeToWidth(0.65 + CONSTANT), RelativeTo.relativeToHeight(0.615), 2,
                new Color(205, 207, 109));
        left.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        middle.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        right.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(left, backgroundLayer + 1);
        setLayer(middle, backgroundLayer + 1);
        setLayer(right, backgroundLayer + 1);
        add(right);
        add(middle);
        add(left);
    }

    // MODIFIES : this
    // EFFECTS : setting the button size text in the button
    private void setButtonNewSizeText() {
        JLabel text = new JLabel("82px");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(205, 207, 109));
        text.setSize(RelativeTo.relativeToWidth(0.09), RelativeTo.relativeToHeight(0.06));
        text.setLocation(RelativeTo.relativeToWidth(0.55 + CONSTANT), RelativeTo.relativeToHeight(0.5465));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the credit text
    private void setCreditsUnsplash() {
        JLabel text = new JLabel("Image by Sebastian Svenson | Unsplash");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        text.setLocation(RelativeTo.relativeToWidth(0.61 + CONSTANT), RelativeTo.relativeToHeight(0.71));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : setting the credit text
    private void setCreditDribbble() {
        JLabel text = new JLabel("Inspired by Dima Groshev | Dribbble");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        text.setLocation(RelativeTo.relativeToWidth(0.624 + CONSTANT), RelativeTo.relativeToHeight(0.74));
        setLayer(text, backgroundLayer + 1);
        add(text);
    }
}
