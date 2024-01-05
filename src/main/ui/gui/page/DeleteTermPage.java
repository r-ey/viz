package ui.gui.page;

import model.component.Term;
import model.util.SimpleList;
import ui.gui.util.CustomFont;
import ui.gui.util.LoadImage;
import persistence.LoadJson;
import ui.gui.util.RelativeTo;

import javax.swing.*;
import java.awt.*;

// create page for deleting term
public class DeleteTermPage extends JFrame {
    private final CustomFont customFont;
    private JLayeredPane pane;
    private int backgroundLayer;

    // EFFECTS : creating delete course frame
    public DeleteTermPage() {
        customFont = new CustomFont();
        setFrame();
        setBackground();
        setTerm();
    }

    // MODIFIES : this
    // EFFECTS : set the frame
    private void setFrame() {
        pane = new JLayeredPane();
        pane.setLayout(null);
        pane.setSize(RelativeTo.relativeToHeight(1), RelativeTo.relativeToHeight(1));
        add(pane);

        setTitle("Delete Term");
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
    // EFFECTS : set term panel
    private void setTerm() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(RelativeTo.relativeToWidth(0.38),
                RelativeTo.relativeToHeight(0.3)));
        panel.setBackground(new Color(0, 0, 0, 0));
        SimpleList<Term> termList = LoadJson.getProfile().getTermList();
        int size = termList.size();

        setButton(size, panel, termList);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(panel);
        scrollPane.setBounds(0, 0, pane.getWidth(), pane.getHeight());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        pane.setLayer(scrollPane, backgroundLayer);
        pane.add(scrollPane);
    }

    // MODIFIES : this
    // EFFECTS : set button
    private void setButton(int size, JPanel panel, SimpleList<Term> termList) {
        for (int i = 0; i < size; i++) {
            JButton button = new JButton(termList.get(i).getTermName());
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
                LoadJson.deleteTerm(finalI);
                this.dispose();
            });
            panel.add(button);
        }
    }
}
