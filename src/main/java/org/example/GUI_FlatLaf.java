package org.example;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GUI_FlatLaf extends BasicGUI{
    public static void main(String[] args) {
        f.setTitle("GUI FlatLaF");
        jb1.addActionListener((ActionEvent e)-> log.info("Hello")) ;


        /**
         * Set Look and Feel
         * */
        //Look and Feel with UI Manager
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch(Exception e){
            e.printStackTrace();
        }
        //Look and Feel Flatlaf-method
        FlatLightLaf.setup();

        /*
        * Customize Look and Feel
        * */
        //Flatlaf-Customization with UI-Manager
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("Table.showVerticalLines", true);


        // test table to see how tables look with different look and feels
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

        // label to see the current Look and Feel
        JLabel jl = new JLabel(String.valueOf(UIManager.getLookAndFeel()), SwingConstants.CENTER);
        jl.setFont(new Font("Open Sans", Font.BOLD, 14));


        tb.add(jb1);
        f.add(tb, BorderLayout.NORTH);
        f.add(jp, BorderLayout.CENTER);
        f.add(jl, BorderLayout.SOUTH);

        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
