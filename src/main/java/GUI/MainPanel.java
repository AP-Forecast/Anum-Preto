package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import API.Localizer.*;
import GUI.Pages.*;

public class MainPanel extends JFrame {
    private JPanel contentPanel;
    private File icons = new File("src/main/java/resource/icons");

    public MainPanel() {
        // Set up the main frame
        setTitle("AP Forecast");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(5, 1)); // 5 buttons in a vertical layout

        // Create buttons with icons
        String[] buttonLabels = {"Rain", "Wind", "UV Index", "Forecast", "Config"};
        String[] iconPaths = {
                "rain.png",
                "wind.png",
                "uvIndex.png",
                "forecast.png",
                "config.png"
        };


        for (int i = 0; i < buttonLabels.length; i++) {
            try {

                Image image = ImageIO.read(new File(icons, iconPaths[i]));
                Image resized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JButton button = new JButton(buttonLabels[i],new ImageIcon(resized));
                button.addActionListener(new ButtonClickListener());
                sidebarPanel.add(button);

            } catch (Exception e){

                System.out.println(e.getMessage());

            }
        }

        // Create content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Bem vindo ao AP Forecast! Selecione uma opção do menu à esquerda."));

        // Add panels to the frame
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            String buttonText = sourceButton.getText();
            updateContent(buttonText);
        }
    }

    private void updateContent(String buttonText) {
        contentPanel.removeAll(); // Clear existing content
        GridBagConstraints mainController = new GridBagConstraints();
        JLabel label;
        JTextArea textArea;

        switch (buttonText){
            case "Rain":
                label = new JLabel("Rain chance: " + buttonText);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
            break;

            case "Wind":
                textArea = new JTextArea("Wind direction: " + buttonText + "\nWind Speed: " + buttonText);
                textArea.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(textArea);
            break;

            case "UV Index":
                label = new JLabel("UV Index" + buttonText);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
            break;

            case "Forecast":
                label = new JLabel("Forecast for the week: " + buttonText);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
            break;

            case "Config":
                Config configPanel = new Config(icons);
                configPanel.getSend().addActionListener(new ButtonClickListener());
                mainController.anchor = GridBagConstraints.CENTER;
                contentPanel.add(configPanel.getConfigPanel());


            break;
        }

        // Resetting the panel
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPanel main = new MainPanel();
            main.setVisible(true);
        });
    }
}
