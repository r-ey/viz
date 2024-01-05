package ui.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

// creating a circle
public class Circle extends JComponent {

    private final double x1;
    private final double y1;
    private final double start;
    private final double width;
    private final double height;
    private final double extent;
    private final Color color;

    // EFFECTS : making a circle
    public Circle(double x1, double y1, double start, double extent, double width, double height, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.start = start;
        this.width = width;
        this.height = height;
        this.extent = extent;
        this.color = color;
    }

    // EFFECTS : making a circle
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCircle(g);
    }

    // EFFECTS : draw a circle
    public void drawCircle(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.fill(new Arc2D.Double(x1, y1, width, height, start, extent, Arc2D.PIE));
    }
}
