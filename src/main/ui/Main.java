package ui;

import ui.gui.VizGui;
import ui.console.VizApp;

import javax.swing.*;
import java.util.Scanner;

// starting point for the app
public class Main {

    public static void main(String[] args) {
        System.out.print("Press 0 for console based and 1 for GUI : ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        if (input == 0) {
            new VizApp();
        } else {
            SwingUtilities.invokeLater(VizGui::new);
        }
    }
}
