package org.launchcode.uTrain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


@Controller
public class ImageController {

    private BufferedImage img;

    @PostMapping("profile")
    public void init() {
        try {
            URL url = getClass().getResource("/images/IMG_1555.png");
            Image image = new ImageIcon(url).getImage();
            img = ImageIO .read(url);
        } catch (IOException e) {

        }
    }

    public void paint(Graphics g) {
        g.drawImage(img, 50, 50, null);
    }

}
