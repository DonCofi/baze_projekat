package view;

import javax.swing.*;

public class Menu extends JMenuBar {
    public Menu(){
        JMenu file = new JMenu("File");
        JMenu analyze = new JMenu("Analyze");
        JMenu window = new JMenu("Window");
        JMenu help = new JMenu("Help");

        add(file);
        add(analyze);
        add(window);
        add(help);
    }
}
