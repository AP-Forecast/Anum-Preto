package GUI.Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Wind extends JPanel {

    //todo: substitute this for API data
    private double windDir = 70;
    private double windSpeed = 3.2;
    private int marker = 0;

    private static final String[] DIRECTIONS = {
            "North-West",
            "North",
            "North-East",
            "West",
            "East",
            "South-West",
            "South",
            "South-East"
    };

    public Wind (File icons){

        JPanel innerPanel;
        JLabel label;
        JPanel outerPanel = new JPanel(new GridLayout(3,3));
        outerPanel.setPreferredSize(new Dimension(800,600));


        for (int row = 0; row < 3; row++){

            for (int col = 0; col < 3; col++){

                if (row == 1 && col == 1){

                    try {
                        Image image = ImageIO.read(new File(icons, "slider.png"));
                        Image resized = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);

                        innerPanel = new JPanel(new BorderLayout());
                        label = new JLabel(new ImageIcon(resized));

                        innerPanel.setBackground(Color.white);
                        innerPanel.add(label, BorderLayout.WEST);
                        outerPanel.add(innerPanel);

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage());
                    }

                } else {
                    innerPanel = new JPanel(new BorderLayout());
                    innerPanel.setBackground(Color.white);
                    label = new JLabel(DIRECTIONS[marker]);
                    innerPanel.add(label, BorderLayout.CENTER);
                    marker++;
                    outerPanel.add(innerPanel);
                }

            }

        }

        label = new JLabel("And the wind speed is: " + this.windSpeed);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setPreferredSize(new Dimension(800,600));
        add(label, BorderLayout.SOUTH);
        add(outerPanel, BorderLayout.CENTER);
    }
}
