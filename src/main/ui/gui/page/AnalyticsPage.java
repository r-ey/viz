package ui.gui.page;

import model.component.Course;
import model.component.Profile;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutionException;

// create analytics page
public class AnalyticsPage extends JFrame {

    private static final String PATH = "./data/img/walking-bear.gif";

    private final CustomFont customFont;
    private JLayeredPane pane;
    private int backgroundLayer;

    private final Profile profile;
    private final int termIndex;
    private final int courseIndex;

    // EFFECTS : creating analytics page
    public AnalyticsPage(Profile p, int t, int c) {
        customFont = new CustomFont();
        profile = p;
        termIndex = t;
        courseIndex = c;
        setPane();
        setBackground();
        setHeader();
        setPicture();
        setPanel();
        setCredits();
    }

    // MODIFIES : this
    // EFFECTS : set up the frame and pane
    private void setPane() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(0.71), RelativeTo.relativeToHeight(0.77));
        add(pane);

        setTitle("Analytics");
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
        JLabel text = new JLabel("Analytics Result");
        text.setFont(customFont.getFont(120f));
        text.setForeground(new Color(202, 210, 197, 153));
        text.setSize(RelativeTo.relativeToWidth(0.56), RelativeTo.relativeToHeight(0.2));
        text.setLocation(RelativeTo.relativeToWidth(0.12), RelativeTo.relativeToHeight(-0.05));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up the picture
    private void setPicture() {
        Icon pict = new ImageIcon(PATH);
        JLabel image = new JLabel(pict);
        image.setBounds(RelativeTo.relativeToWidth(-0.46), RelativeTo.relativeToHeight(-0.21),
                RelativeTo.relativeToWidth(1.2), RelativeTo.relativeToHeight(1.2));
        pane.setLayer(image, backgroundLayer + 1);
        pane.add(image);
    }

    // MODIFIES : this
    // EFFECTS : set up the panel for the result
    private void setPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setSize(new Dimension(RelativeTo.relativeToWidth(0.35),
                RelativeTo.relativeToHeight(0.4)));
        panel.setBackground(new Color(202, 210, 197, 51));

        setResult(panel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(RelativeTo.relativeToWidth(0.3), RelativeTo.relativeToHeight(0.21),
                panel.getWidth(), panel.getHeight());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        pane.setLayer(scrollPane, backgroundLayer + 1);
        pane.add(scrollPane);
    }

    // MODIFIES : this
    // EFFECTS : set up result
    private void setResult(JPanel panel) {
        Course course = profile.getTermList().get(termIndex).getCourseList().get(courseIndex);
        try {
            String analyticsName = course.getAnalyticsName();
            String analyticsResult = course.getAnalyticsResult();
            setAnalyticsName(panel, analyticsName);
            setAnalyticsResult(panel, analyticsResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES : this
    // EFFECTS : set up result
    private void setAnalyticsName(JPanel panel, String s) {
        String display = "   " + s + "    ";
        JLabel text = new JLabel(display);
        text.setFont(customFont.getFont(24f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        panel.add(text);
    }

    // MODIFIES : this
    // EFFECTS : set up result
    private void setAnalyticsResult(JPanel panel, String s) {
        String[] result = s.split("\n");
        for (String r : result) {
            if (r.contains(":")) {
                String[] numbers = r.split(": ");
                numbers[1] = numbers[1].substring(0, numbers[1].indexOf(","));
                float percentage = Float.parseFloat(numbers[2]) / Float.parseFloat(numbers[1]) * 100;
                r = String.format("%.2f", percentage);
                r = r.concat("%");
            }
            String display = "    " + r + "    ";
            JLabel text = new JLabel(display);
            text.setFont(customFont.getFont(16f));
            text.setForeground(new Color(47, 62, 70));
            text.setSize(RelativeTo.relativeToWidth(0.4), RelativeTo.relativeToHeight(0.14));
            panel.add(text);
        }
    }

    // MODIFIES : this
    // EFFECTS : set up the credits
    private void setCredits() {
        JLabel text = new JLabel("GIF from Time");
        text.setFont(customFont.getFont(20f));
        text.setForeground(new Color(47, 62, 70));
        text.setSize(RelativeTo.relativeToWidth(0.27), RelativeTo.relativeToHeight(0.14));
        text.setLocation(RelativeTo.relativeToWidth(0.61), RelativeTo.relativeToHeight(0.62));
        pane.setLayer(text, backgroundLayer + 1);
        pane.add(text);
    }
}
