package org.example;


import com.formdev.flatlaf.extras.FlatSVGIcon;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;

public class FlatSVGIconGUI extends BasicGUI{
    public static void main(String[] args) {
        f.setTitle("FlatSVGIcon GUI Test");

        jb1 = new JButton(loadSVG(url_svg));
        JButton jb2 = new JButton(new ImageIcon(url_bitmap));

        jb1.addActionListener((ActionEvent e)-> log.info("Hello"));

        tb.add(jb1);
        tb.add(jb2);

        f.add(tb, BorderLayout.NORTH);

        // set the size of the frame
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static ImageIcon loadSVG(URL url){
        return new FlatSVGIcon(url);
    }

    public static ImageIcon loadSVG(URL url, float width) {

        try {
            Class<?> flatSvgIconClass = Class.forName("com.formdev.flatlaf.extras.FlatSVGIcon");

            Constructor<?> constructor = flatSvgIconClass.getConstructor(URL.class);
            Object flatSvgIcon = constructor.newInstance(url);

            return (ImageIcon) flatSvgIcon;
        } catch (Exception e) {
            e.printStackTrace();
            return loadSvgTranscoder(url, width);
        }
    }

    public static ImageIcon loadSvgTranscoder(URL url, float width)
    {
        if(url == null)
        {
            return null;
        }

        SvgTranscoder transcoder = new SvgTranscoder();
        transcoder.setTranscodingHints(getHints(width));
        try
        {
            TranscoderInput input = new TranscoderInput(url.openStream());
            transcoder.transcode(input, null);
        }
        catch (TranscoderException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return new ImageIcon(transcoder.getImage());
    }

    private static TranscodingHints getHints(float width)
    {
        TranscodingHints hints = new TranscodingHints();
        hints.put(ImageTranscoder.KEY_WIDTH, width);
        hints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
        hints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, SVGConstants.SVG_SVG_TAG);
        hints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, false);
        return hints;
    }

    static class SvgTranscoder extends ImageTranscoder
    {
        private BufferedImage image = null;

        @Override
        public BufferedImage createImage(int width, int height)
        {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            return image;
        }

        @Override
        public void writeImage(BufferedImage img, TranscoderOutput out)
        {
            image = img;
        }

        BufferedImage getImage()
        {
            return image;
        }
    }
}
