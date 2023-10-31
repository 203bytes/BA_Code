package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.logging.Logger;

public class BasicGUI {

    //url to load icons
    static URL url_svg = BasicGUI.class.getResource("/org/example/icons/key.svg");
    static URL url_bitmap = BasicGUI.class.getResource("/org/example/icons/key.png");
    static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // toolbar
    static JToolBar tb = new JToolBar();;

    // buttons
    static JButton jb1 = new JButton("Click Me");

    // frame
    static JFrame f = new JFrame("Toolbar demo local");

    public static void main(String[] args) {

        JLabel jl = new JLabel(String.valueOf(UIManager.getLookAndFeel()), SwingConstants.CENTER);
        jl.setFont(new Font("Open Sans", Font.BOLD, 14));

        String[] columnnames = {"First Name", "Last Name", "Sport", "# of Years", "Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith", "Snowboarding", 5, Boolean.FALSE},
                {"John", "Doe", "Rowing", 3, Boolean.TRUE},
                {"Sue", "Black", "Knitting", 2, Boolean.FALSE},
                {"Jane", "White", "Speed reading", 20, Boolean.TRUE},
                {"Joe", "Brown", "Pool", 10, Boolean.FALSE}
        };
        JTable table = new JTable(data, columnnames);
        JPanel jp = new JPanel();
        jp.add(table);

        jb1.addActionListener((ActionEvent e)-> log.info("Hello"));
        tb.add(jb1);

        f.add(tb, BorderLayout.NORTH);
        f.add(jp, BorderLayout.CENTER);
        f.add(jl, BorderLayout.SOUTH);


        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
