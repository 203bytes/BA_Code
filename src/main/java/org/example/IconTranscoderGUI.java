package org.example;

import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_WIDTH;
import static org.apache.batik.transcoder.XMLAbstractTranscoder.*;
import static org.apache.batik.util.SVGConstants.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_SVG_TAG;

public class IconTranscoderGUI extends BasicGUI{
    public static void main(String[] args) {
        f.setTitle("Transcoder GUI Test");
        jb1 = new JButton();
        jb1.setIcon(loadSvg(url_svg, 50));
        jb1.addActionListener((ActionEvent e)-> log.info("Hello"));

        JButton jb2 = new JButton(new ImageIcon(url_bitmap));

        tb.add(jb1);
        tb.add(jb2);
        f.add(tb, BorderLayout.NORTH);

        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static ImageIcon loadSvg(URL url, float width){

        if(url == null) {
            return null;
        }

        MyTranscoder transcoder = new MyTranscoder();
        transcoder.setTranscodingHints(hints(width));

        try {
            TranscoderInput input = new TranscoderInput(url.openStream());
            transcoder.transcode(input, null);
        } catch (TranscoderException e) {
            log.severe("Error parsing SVG file " + url);
            log.warning(e.toString());
            return null;
        } catch (IOException e) {
            log.severe("Error finding SVG file " + url);
            log.warning(e.toString());
            return null;
        }
        return new ImageIcon(transcoder.getImage());
    }

    private static TranscodingHints hints(float width) {
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


