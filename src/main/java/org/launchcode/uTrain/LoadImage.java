package org.launchcode.uTrain;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImage extends Component {

    BufferedImage img;

    public static void main (String[] args) {
        JFrame f = new JFrame("Load Image Sample");

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        f.add(new LoadImage());
        f.pack();
        f.setVisible(true);
    }

    public LoadImage() {
        try {
            img = ImageIO.read(new File("..resources/templates/images/IMG_1555.png"));
        } catch (IOException e) {

        }
    }
}
