package ui.gui.component;

import java.awt.*;
import java.awt.geom.Line2D;

// class for drawing arrow
public class Arrow extends Line {

    // EFFECTS : component for an arrow
    public Arrow(double x1, double y1, double x2, double y2, int thickness, Color color) {
        super(x1, y1, x2, y2, thickness, color);
    }

    // EFFECTS : draw an arrow
    private void drawArrow(Graphics g) {
        drawLine(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(thickness));
        drawTop(graphics2D);
        drawBottom(graphics2D);
    }

    // EFFECTS : draw the top part
    private void drawTop(Graphics2D g) {
        if (y1 == y2) {  // horizontal arrow
            g.draw(new Line2D.Double(x2, y2, (0.75 * (x2 - x1)) + x1, 1.015 * y2));
        }
        if (x1 == x2) {  // vertical arrow
            g.draw(new Line2D.Double(x2, y2, 0.985 * x2, (0.75 * (y2 - y1)) + y1));
        }
    }

    // EFFECTS : draw the bottom part
    private void drawBottom(Graphics2D g) {
        if (y1 == y2) {  // horizontal arrow
            g.draw(new Line2D.Double(x2, y2, (0.75 * (x2 - x1)) + x1, 0.985 * y2));
        }
        if (x1 == x2) {  // vertical arrow
            g.draw(new Line2D.Double(x2, y2, 1.015 * x2, (0.75 * (y2 - y1) + y1)));
        }
    }

    // EFFECTS : draw an arrow
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawArrow(g);
    }
}
