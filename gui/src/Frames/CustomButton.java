package Frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomButton extends JButton {
    BufferedImage img;

    public CustomButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setForeground(Color.LIGHT_GRAY);
        this.setBorderPainted(false);
        this.setOpaque(false);
        try {
            img = ImageIO.read(new File("images/Button.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public CustomButton(String text,BufferedImage inImg) {
        super(text);
        this.setContentAreaFilled(false);
        this.setFocusable(false);
        this.setForeground(Color.LIGHT_GRAY);
        this.setBorderPainted(false);
        this.setOpaque(false);
        img = inImg;
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.yellow);
        g2d.drawImage(img,0,0,getWidth(),getHeight(),null);
        super.paintComponent(g2d);
    }
}
