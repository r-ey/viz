package ui.gui.page;

import model.component.Course;
import model.util.SimpleList;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// create page to delete course
public class DeleteCoursePage extends JFrame {

    private final CustomFont customFont;
    private JLayeredPane pane;
    private int backgroundLayer;
    private final int termIndex;

    // EFFECTS : creating delete course frame
    public DeleteCoursePage(int t) {
        customFont = new CustomFont();
        termIndex = t;
        setFrame();
        setBackground();
        setCourses();
    }

    // MODIFIES : this
    // EFFECTS : set the frame
    private void setFrame() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(1), RelativeTo.relativeToHeight(1));
        add(pane);

        setTitle("Delete Course");
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
        JLabel background = LoadImage.getImageMap().get("gradientSmall");
        background.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        pane.add(background);
        backgroundLayer = JLayeredPane.getLayer(background);
    }

    // MODIFIES : this
    // EFFECTS : set course panel
    private void setCourses() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(RelativeTo.relativeToWidth(0.38),
                RelativeTo.relativeToHeight(0.3)));
        panel.setBackground(new Color(0, 0, 0, 0));
        SimpleList<Course> courseList = LoadJson.getProfile().getTermList().get(termIndex).getCourseList();
        int size = courseList.size();

        setButton(size, panel, courseList);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        pane.setLayer(scrollPane, backgroundLayer);
        pane.add(scrollPane);
    }

    // MODIFIES : this
    // EFFECTS : set button
    private void setButton(int size, JPanel panel, SimpleList<Course> courseList) {
        for (int i = 0; i < size; i++) {
            JButton button = new JButton(courseList.get(i).getName());
            button.setBackground(new Color(82, 121, 111));
            button.setForeground(new Color(53, 79, 82));
            button.setPreferredSize(new Dimension(RelativeTo.relativeToWidth(0.15),
                    RelativeTo.relativeToHeight(0.04)));
            button.setFont(customFont.getFont(12f));
            button.setOpaque(true);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            int finalI = i;
            button.addActionListener(e -> {
                LoadJson.deleteCourse(termIndex, finalI);
                this.dispose();
            });
            panel.add(button);
        }
    }
}
