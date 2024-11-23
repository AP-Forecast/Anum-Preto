package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Main extends JFrame {
    private JPanel main;
    private JPanel sidebar;
    private JButton temperature;
    private JButton rain;
    private JButton wind;
    private JButton uvIndex;
    private JButton button5;

    public Main(){
        setContentPane(main);
        setTitle("AP Forecast");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resource/images/test.jpg")));

        // Check if the icon loaded successfully
        if (icon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            System.out.println("Image not loaded properly.");
            return; // Exit if image fails to load
        }


        sidebar.setPreferredSize(new Dimension(100, 780));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.BLACK);

        sidebar.add(Box.createVerticalGlue());
        temperature.setPreferredSize(new Dimension(50,50));
        sidebar.add(temperature);

        sidebar.add(Box.createVerticalGlue());
        rain.setPreferredSize(new Dimension(50,50));
        rain.setIcon(icon);
        sidebar.add(rain);

        sidebar.add(Box.createVerticalGlue());
        wind.setPreferredSize(new Dimension(50,50));
        sidebar.add(wind);

        sidebar.add(Box.createVerticalGlue());
        uvIndex.setPreferredSize(new Dimension(50,50));
        sidebar.add(uvIndex);

        sidebar.add(Box.createVerticalGlue());
        button5.setPreferredSize(new Dimension(50,50));
        sidebar.add(button5);
        sidebar.add(Box.createVerticalGlue());

        add(sidebar, BorderLayout.WEST);


    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(Main::new);
    }
}
