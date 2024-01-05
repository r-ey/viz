package ui.gui.component;

import javax.swing.*;
import java.awt.*;

// creating a triangle
public class Triangle extends JComponent {

    private final Polygon polygon;
    private final Color color;

    // EFFECTS : making a triangle
    public Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color color) {
        polygon = new Polygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
        this.color = color;
    }

    // EFFECTS : making a triangle
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawTriangle(g);
    }

    // EFFECTS : draw a triangle
    public void drawTriangle(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(color);
        graphics2D.fill(polygon);
    }
}
