package org.example;

import com.formdev.flatlaf.*;



import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Logger;

import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_WIDTH;
import static org.apache.batik.transcoder.XMLAbstractTranscoder.*;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_SVG_TAG;

public class GUI extends JFrame {
    // Logger
    static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // toolbar
    static JToolBar tb;



    static JButton jb1;
    static JLabel l1;

    // create a frame
    static JFrame f;

    public static void main(String[] args) throws URISyntaxException {

        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch(Exception e){
            e.printStackTrace();
        }


        f = new JFrame("Toolbar demo local");

        tb = new JToolBar();

        // create a panel
        JPanel p = new JPanel();

        //URI fileUri = GUI.class.getResource("/com/test/icons/key.svg").toURI();
        //String svgURI1 = fileUri.toString();

        //System.out.println(svgURI1);
        URL url = GUI.class.getResource("/org/example/icons/key.svg");



        jb1 = new JButton();

        jb1.setIcon(loadSVG(url, 100));

        //b2 = new MyButton(svgURI2);
        //b3 = new MyButton(svgURI3);

        //b1.setPreferredSize(new Dimension(100, 100));
        jb1.setPreferredSize(new Dimension(100,100));
        //b2.setPreferredSize(new Dimension(100, 100));
        //b3.setPreferredSize(new Dimension(100, 100));



/*
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("im alive");
            }
        });
*/

        //p.add(b1);
        //tb.add(b1);
        tb.add(jb1);
        //tb.add(b2);
        //tb.add(b3);

        f.add(tb, BorderLayout.NORTH);
        //f.add(p, BorderLayout.NORTH);
        //f.add(l1, BorderLayout.CENTER);


        // set the size of the frame
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static ImageIcon loadSVG(URL url, float width){

        if(url == null) {
            return null;
        }

        MyTranscoder transcoder = new MyTranscoder();
        transcoder.setTranscodingHints(getHints(width));

        try {
            TranscoderInput input = new TranscoderInput(url.openStream());
            transcoder.transcode(input, null);
        } catch (TranscoderException e) {
            log.severe("Error parsing SVG file " + url);
            log.warning(e.toString());
            return null;
        } catch (IOException e) {
            log.severe("Error parsing SVG file " + url);
            log.warning(e.toString());
            return null;
        }
        return new ImageIcon(transcoder.getImage());
    }

        private static TranscodingHints getHints(float width) {
            TranscodingHints hints = new TranscodingHints();
            hints.put(KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
            hints.put(KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVG_NAMESPACE_URI);
            hints.put(KEY_DOCUMENT_ELEMENT, SVG_SVG_TAG);
            hints.put(KEY_WIDTH, width);
            return hints;
        }

        private static class MyTranscoder extends ImageTranscoder {

            private BufferedImage image = null;

            @Override
            public BufferedImage createImage(int width, int height) {
                image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                return image;
            }

            @Override
            public void writeImage(BufferedImage img, TranscoderOutput out) {}

            BufferedImage getImage() {
                return image;
            }
        }
    }

