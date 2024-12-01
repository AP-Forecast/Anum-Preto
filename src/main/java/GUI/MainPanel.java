package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import GUI.Pages.*;

public class MainPanel extends JFrame {
    private JPanel contentPanel;
    private File icons = new File("src/main/java/resource/icons");
    private File images = new File("src/main/java/resource/images");

    private static final String[] BUTTON_LABELS = {"Rain", "Wind", "UV Index", "Forecast", "Config"};
    private static final String[] ICON_PATHS = {
            "rain.png",
            "wind.png",
            "uvIndex.png",
            "forecast.png",
            "config.png"
    };

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



        for (int i = 0; i < BUTTON_LABELS.length; i++) {
            try {

                Image image = ImageIO.read(new File(icons, ICON_PATHS[i]));
                Image resized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                JButton button = new JButton(BUTTON_LABELS[i],new ImageIcon(resized));
                button.addActionListener(new ButtonClickListener());
                sidebarPanel.add(button);

            } catch (Exception e){

                JOptionPane.showMessageDialog(
                        this,
                        "Error loading icon: " + BUTTON_LABELS[i],
                        "Error", JOptionPane.ERROR_MESSAGE
                );

            }
        }

        // Create content panel
        contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setPreferredSize(new Dimension(800, 600));
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
                Rain rainPanel = new Rain(images);
                mainController.fill = GridBagConstraints.BOTH; // Allow resizing in both directions
                mainController.weightx = 1.0; // Allow growth in width
                mainController.weighty = 1.0;
                contentPanel.add(rainPanel, mainController);
            break;

            case "Wind":
                Wind windPanel = new Wind(icons);
                mainController.fill = GridBagConstraints.BOTH; // Allow resizing in both directions
                mainController.weightx = 1.0; // Allow growth in width
                mainController.weighty = 1.0; // Allow growth in height
                contentPanel.add(windPanel, mainController);

            break;

            case "UV Index":
                UVIndex uvPanel = new UVIndex(icons);
                mainController.fill = GridBagConstraints.BOTH; // Allow resizing in both directions
                mainController.weightx = 1.0; // Allow growth in width
                mainController.weighty = 1.0; // Allow growth in height
                contentPanel.add(uvPanel, mainController);
            break;

            case "Forecast":
                Forecast forecast = new Forecast();
                mainController.fill = GridBagConstraints.BOTH; // Allow resizing in both directions
                mainController.weightx = 1.0; // Allow growth in width
                mainController.weighty = 1.0; // Allow growth in height
                contentPanel.add(forecast, mainController);
            break;

            case "Config":
                Config configPanel = new Config(icons);
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
