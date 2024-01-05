package ui.gui.component;

import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// creating a moving ball
public class MovingBall extends JComponent implements ActionListener {

    private static final int RADIUS = 5;
    private static final int DELAY = 100;
    private static final double X_CONSTANT = 75;
    private static final double Y_CONSTANT = 26.5;
    private static final int MAX = 36;

    private static final double[] PHASE_X = {625, 627, 627.5, 628, 628.3, 629, 630, 632, 633, 636, 639, 642, 645,
            648, 650, 652, 654, 656, 658, 659, 661, 663, 665, 667, 669, 671, 672, 673, 674, 675, 677, 679, 680, 682,
            683, 685, 687};
    private static final double[] PHASE_Y = {395.5, 394, 390, 386.5, 383, 380, 377, 374, 371, 369, 368, 368, 369, 370,
            371, 372, 373, 374, 375, 376, 378, 380, 382, 384, 386, 388, 390, 392, 393, 395, 397, 406, 410, 413, 416,
            423, 424.5};

    private int circleX;
    private int circleY;
    private int index;

    // EFFECTS : create a moving ball
    public MovingBall() {
        Timer timer = new Timer(DELAY, this);
        index = 0;
        timer.start();
    }

    // EFFECTS : create a circle
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g);
    }

    // EFFECTS : create a circle
    private void drawCircle(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(new Color(205, 207, 109));
        graphics2D.fillOval(circleX, circleY, RADIUS, RADIUS);
    }

    // MODIFIES : this
    // EFFECTS : making the animation
    @Override
    public void actionPerformed(ActionEvent e) {
        circleX = RelativeTo.relativeToWidth(((PHASE_X[index++] * 0.9) + X_CONSTANT) / RelativeTo.getWidth());
        circleY = RelativeTo.relativeToHeight(((PHASE_Y[index++] * 0.9) + Y_CONSTANT) / RelativeTo.getHeight());
        if (index == MAX) {
            index = 0;
        }
        repaint();
    }
}
