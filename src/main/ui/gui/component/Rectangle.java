package ui.gui.component;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

// creating a rectangle
public class Rectangle extends JComponent {

    private final double x1;
    private final double y1;
    private final double width;
    private final double height;
    private final Color color;

    // EFFECTS : make a rectangle
    public Rectangle(double x, double y, double width, double height, Color color) {
        this.x1 = x;
        this.y1 = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // EFFECTS : making a rectangle
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawRectangle(g);
    }

    // EFFECTS : draw a rectangle
    public void drawRectangle(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        Rectangle2D rect = new Rectangle2D.Double(x1, y1, width, height);
        graphics2D.fill(rect);
    }
}
