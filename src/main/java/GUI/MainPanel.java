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

                JOptionPane.showMessageDialog(this, "Error loading icon: " + BUTTON_LABELS[i],
                        "Error", JOptionPane.ERROR_MESSAGE);

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
                Forecast forecast = new Forecast();
                mainController.fill = GridBagConstraints.BOTH; // Allow resizing in both directions
                mainController.weightx = 1.0; // Allow growth in width
                mainController.weighty = 1.0; // Allow growth in height
                contentPanel.add(forecast, mainController);
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
