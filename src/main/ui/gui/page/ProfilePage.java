package ui.gui.page;

import ui.gui.component.Arrow;
import ui.gui.component.Rectangle;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// create the profile page
public class ProfilePage extends JFrame {

    private final CustomFont customFont;
    private static JLayeredPane pane;
    private JTextField field;

    private static int backgroundLayer;

    // EFFECTS : creating the profile page
    public ProfilePage() {
        customFont = new CustomFont();
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setTextField();
        setCredits();
        setArrow();
    }

    // MODIFIES : this
    // EFFECTS : set up the frame and pane
    private void setPane() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(0.71), RelativeTo.relativeToHeight(0.77));
        add(pane);

        setTitle("Profile");
        setSize(RelativeTo.relativeToWidth(0.71), RelativeTo.relativeToHeight(0.77));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set up the background
    private void setBackground() {
        JLabel background = LoadImage.getImageMap().get("gradientSmall");
        background.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        pane.add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : set up the header
    private void setHeader() {
        JLabel text = new JLabel("Set Up Profile");
        text.setFont(customFont.getFont(120f));
        text.setForeground(new Color(202, 210, 197, 153));
        text.setSize(RelativeTo.relativeToWidth(0.56), RelativeTo.relativeToHeight(0.2));
        text.setLocation(RelativeTo.relativeToWidth(0.163), RelativeTo.relativeToHeight(-0.05));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the picture
    private void setPicture() {
        Rectangle rect = new Rectangle(0, RelativeTo.relativeToHeight(0.178), RelativeTo.relativeToWidth(0.8),
                RelativeTo.relativeToHeight(0.31), new Color(217, 217, 217, 51));
        rect.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);
        JLabel pict = LoadImage.getImageMap().get("people");
        pict.setBounds(RelativeTo.relativeToWidth(0), RelativeTo.relativeToHeight(-0.0025),
                RelativeTo.relativeToWidth(0.7), RelativeTo.relativeToHeight(0.7));
        pane.setLayer(pict, backgroundLayer + 2);
        pane.add(pict);
    }

    // MODIFIES : this
    // EFFECTS : set up the textfield
    private void setTextField() {
        Rectangle rect = new Rectangle(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.55),
                RelativeTo.relativeToWidth(0.32), RelativeTo.relativeToHeight(0.12),
                new Color(202, 210, 197, 51));
        rect.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(rect, backgroundLayer + 1);
        pane.add(rect);

        JLabel text = new JLabel("Name");
        text.setFont(customFont.getFont(26f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.2), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.34), RelativeTo.relativeToHeight(0.535));
        pane.setLayer(text, backgroundLayer + 2);
        pane.add(text);

        field = new JTextField();
        field.setFont(customFont.getFont(12f));
        field.setForeground(new Color(47, 62, 70));
        field.setBackground(new Color(53, 79, 82, 127));
        field.setSize(RelativeTo.relativeToWidth(0.26), RelativeTo.relativeToHeight(0.03));
        field.setLocation(RelativeTo.relativeToWidth(0.23), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(field, backgroundLayer + 2);
        pane.add(field);
    }

    // MODIFIES : this
    // EFFECTS : set up the credits
    private void setCredits() {
        JLabel text = new JLabel("Image AI Generated | Fotor");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        text.setLocation(RelativeTo.relativeToWidth(0.54), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the arrow
    private void setArrow() {
        Arrow arrow = new Arrow(RelativeTo.relativeToWidth(0.46), RelativeTo.relativeToHeight(0.6365),
                RelativeTo.relativeToWidth(0.48), RelativeTo.relativeToHeight(0.6365), 2,
                new Color(202, 210, 197));
        arrow.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(arrow, backgroundLayer + 3);
        pane.add(arrow);

        JButton button = new JButton();
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setSize(RelativeTo.relativeToWidth(0.08), RelativeTo.relativeToHeight(0.035));
        button.setLocation(RelativeTo.relativeToWidth(0.415), RelativeTo.relativeToHeight(0.618));
        button.addActionListener(e -> {
            LoadJson.setProfileName(field.getText());
            new TermPage();
            this.dispose();
        });
        pane.setLayer(button, backgroundLayer + 4);
        pane.add(button);
    }
}
