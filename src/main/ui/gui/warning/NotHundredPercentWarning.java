package ui.gui.warning;

import ui.gui.component.Circle;
import ui.gui.component.Triangle;
import ui.gui.util.CustomFont;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// Create warning page
public class NotHundredPercentWarning extends JFrame {

    private final CustomFont customFont;
    private JLayeredPane pane;
    private int backgroundLayer;

    public NotHundredPercentWarning() {
        customFont = new CustomFont();
        setFrame();
        setBackground();
        setText();
        setShape();
    }

    // MODIFIES : this
    // EFFECTS : set the frame
    private void setFrame() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(1), RelativeTo.relativeToHeight(1));
        add(pane);

        setTitle("Not Equal to Hundred Percent Warning");
        setSize(RelativeTo.relativeToWidth(0.4), RelativeTo.relativeToHeight(0.08));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES : this
    // EFFECTS : set the background
    private void setBackground() {
        JPanel background = new JPanel();
        background.setBackground(new Color(47, 62, 70));
        background.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        pane.add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : set the text
    private void setText() {
        JLabel text = new JLabel("Percentage breakdown must equal to 100");
        text.setFont(customFont.getFont(16f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.56), RelativeTo.relativeToHeight(0.2));
        text.setLocation(RelativeTo.relativeToWidth(0.13), RelativeTo.relativeToHeight(-0.08));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set the exclamation mark shape
    private void setShape() {
        Triangle triangle = new Triangle(RelativeTo.relativeToWidth(0.116), RelativeTo.relativeToHeight(0.01),
                RelativeTo.relativeToWidth(0.122), RelativeTo.relativeToHeight(0.01),
                RelativeTo.relativeToWidth(0.119), RelativeTo.relativeToHeight(0.028),
                new Color(255, 143, 143));
        triangle.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(triangle, backgroundLayer + 1);
        pane.add(triangle);

        Circle circle = new Circle(RelativeTo.relativeToWidth(0.117), RelativeTo.relativeToHeight(0.033),
                0, 360, RelativeTo.relativeToHeight(0.007), RelativeTo.relativeToHeight(0.007),
                new Color(255, 143, 143));
        circle.setSize(RelativeTo.relativeToWidth(0.8), RelativeTo.relativeToHeight(0.8));
        pane.setLayer(circle, backgroundLayer + 1);
        pane.add(circle);
    }
}
