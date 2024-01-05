package ui.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

// class for drawing line
public class Line extends JComponent {

    protected final double x1;
    protected final double x2;
    protected final double y1;
    protected final double y2;
    protected final int thickness;
    protected final Color color;

    // EFFECTS : component for a line
    public Line(double x1, double y1, double x2, double y2, int thickness, Color color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.thickness = thickness;
        this.color = color;
    }

    // EFFECTS : draw a line
    protected void drawLine(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    // EFFECTS : draw a line
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawLine(g);
    }
}
