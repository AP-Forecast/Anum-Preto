package GUI.Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Rain extends JPanel {

    private double rainChance = 10; //todo: substitute this for data from the API
    private double rainAmount;
    private JLabel label;

    public Rain(File images){

        setLayout(new BorderLayout());

        if (rainChance >= 0 && rainChance < 50){
            try {
                Image image = ImageIO.read(new File(images, "blueSky.jpg"));
                Image resized = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                label = new JLabel(new ImageIcon(resized));
                add(label, BorderLayout.CENTER);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (rainChance >= 50 && rainChance < 80) {
            try {
                Image image = ImageIO.read(new File(images, "cloudySky.jpg"));
                Image resized = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                label = new JLabel(new ImageIcon(resized));
                add(label, BorderLayout.CENTER);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if (rainChance >= 80) {
            try {
                Image image = ImageIO.read(new File(images, "rainySky.jpg"));
                Image resized = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                label = new JLabel(new ImageIcon(resized));
                add(label, BorderLayout.CENTER);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }

    }
}
