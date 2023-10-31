package org.example;

import org.apache.batik.swing.svg.JSVGComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;


public class JSVGComponentGUI extends BasicGUI {

    public static void main(String[] args) {
        f.setTitle("JSVGComponent GUI Test");

        JButton jb2 = new JButton(new ImageIcon(url_bitmap));
        jb2.setPreferredSize(new Dimension(100,100));

        JSVGComponent svgComp = new JSVGComponent();
        svgComp.loadSVGDocument(String.valueOf(new File(String.valueOf(url_svg))));
        svgComp.setMySize(new Dimension(100, 100));

        jb1.add(svgComp);
        jb1.addActionListener((ActionEvent e)-> log.info("Hello")) ;

        tb.add(jb1);
        tb.add(jb2);
        f.add(tb, BorderLayout.NORTH);

        // set the size of the frame
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
