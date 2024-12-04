package GUI.Pages;

import API.database.models.Hourly;
import API.database.utils.CRUD_Operator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Rain extends JPanel {

    private CRUD_Operator doc = new CRUD_Operator();

    List<Hourly> hours = doc.readHour(78);
    private Double rainChance;
    private Double rainAmount;
    private JLabel label;
    LocalTime hourNow = LocalTime.now();
    LocalTime hourAPI;
    public Rain(File images){

        setLayout(new BorderLayout());

        for (Hourly hour : hours){
            hourAPI = LocalTime.parse(hour.getHour());
            if (hourNow.isAfter(hourAPI)){

                rainChance = hour.getRainChance().doubleValue();
                rainAmount = hour.getRainAmount();

                if (rainChance >= 0 && rainChance < 50){
                    try {
                        Image image = ImageIO.read(new File(images, "blueSky.jpg"));
                        Image resized = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                        label = new JLabel(new ImageIcon(resized));
                        add(label, BorderLayout.NORTH);

                        label = new JLabel(rainAmount.toString() + "mm");
                        label.setFont(new Font("Arial", Font.PLAIN, 24));
                        add(label, BorderLayout.SOUTH);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                } else if (rainChance >= 50 && rainChance < 80) {
                    try {
                        Image image = ImageIO.read(new File(images, "cloudySky.jpg"));
                        Image resized = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                        label = new JLabel(new ImageIcon(resized));
                        add(label, BorderLayout.NORTH);

                        label = new JLabel(rainAmount.toString() + "mm");
                        label.setFont(new Font("Arial", Font.PLAIN, 24));
                        add(label, BorderLayout.SOUTH);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                } else if (rainChance >= 80) {
                    try {
                        Image image = ImageIO.read(new File(images, "rainySky.jpg"));
                        Image resized = image.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
                        label = new JLabel(new ImageIcon(resized));
                        add(label, BorderLayout.NORTH);

                        label = new JLabel(rainAmount.toString() + "mm");
                        label.setFont(new Font("Arial", Font.PLAIN, 24));
                        add(label, BorderLayout.SOUTH);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }
                }

                break;
            }

        }



    }
}
