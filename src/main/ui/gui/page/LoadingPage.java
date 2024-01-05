package ui.gui.page;

import ui.gui.component.Line;
import ui.gui.component.MovingBall;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// create the loading page
public class LoadingPage extends JLayeredPane implements Runnable {

    private static final double CONSTANT = 0.05;

    private CustomFont customFont;
    private int backgroundLayer;

    // EFFECTS : creating the loading page before landing page
    @Override
    public void run() {
        customFont = new CustomFont();
        setUpPanel();
        setText();
        setLine();
        setBall();
    }

    // MODIFIES : this
    // EFFECTS : setting up the panel
    private void setUpPanel() {
        setLayout(null);
        JLabel background = LoadImage.getImageMap().get("gradientBig");
        background.setBounds(0, 0, RelativeTo.relativeToWidth(0.9), RelativeTo.relativeToHeight(0.9));
        add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
        setOpaque(true);
    }

    // MODIFIES : this
    // EFFECTS : set up the text
    private void setText() {
        JLabel text = new JLabel("V I Z !");
        text.setFont(customFont.getFont(40f));
        text.setForeground(new Color(202, 210, 197));
        text.setSize(RelativeTo.relativeToWidth(0.18), RelativeTo.relativeToHeight(0.1));
        text.setLocation(RelativeTo.relativeToWidth(0.368 + CONSTANT), RelativeTo.relativeToHeight(0.38));
        setLayer(text, backgroundLayer + 2);
        add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the line
    private void setLine() {
        Line top = new Line(RelativeTo.relativeToWidth(0.288 + CONSTANT), RelativeTo.relativeToHeight(0.47),
                RelativeTo.relativeToWidth(0.438 + CONSTANT), RelativeTo.relativeToHeight(0.47),3,
                new Color(82, 121, 111));
        Line bottom = new Line(RelativeTo.relativeToWidth(0.368 + CONSTANT), RelativeTo.relativeToHeight(0.48),
                RelativeTo.relativeToWidth(0.518 + CONSTANT), RelativeTo.relativeToHeight(0.48),3,
                new Color(82, 121, 111));
        top.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        bottom.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(top, backgroundLayer + 2);
        setLayer(bottom, backgroundLayer + 2);
        add(top);
        add(bottom);
    }

    // MODIFIES : this
    // EFFECTS : set up the ball
    private void setBall() {
        MovingBall ball = new MovingBall();
        ball.setSize(RelativeTo.getWidth(), RelativeTo.getHeight());
        setLayer(ball, backgroundLayer + 1);
        add(ball);
    }
}
