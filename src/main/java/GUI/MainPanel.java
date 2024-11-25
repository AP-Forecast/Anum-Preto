package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainPanel extends JFrame {
    private JPanel contentPanel;

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
                "resource/icons/rain.png",
                "resource/icons/wind.png",
                "resource/icons/uvIndex.png",
                "resource/icons/forecast.png",
                "resource/icons/config.png"
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            //ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(iconPaths[i]));
            JButton button = new JButton(buttonLabels[i]);
            //button.setIcon(icon);
            button.addActionListener(new ButtonClickListener());
            sidebarPanel.add(button);
        }

        // Create content panel
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(new JLabel("Welcome! Please select an option from the sidebar."));

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
        JLabel label;
        switch (buttonText){
            case "Rain":
                label = new JLabel("Rain chance: " + buttonText);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
            break;

            case "Wind":
                label = new JLabel("Wind direction: " + buttonText);
                JLabel label2 = new JLabel("Wind speed: " + buttonText);
                label2.setFont(new Font("Arial", Font.PLAIN, 24));
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
                contentPanel.add(label2);
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
                label = new JLabel("Change your location: " + buttonText);
                label.setFont(new Font("Arial", Font.PLAIN, 24));
                contentPanel.add(label);
            break;
        }

        contentPanel.revalidate(); // Refresh the panel
        contentPanel.repaint(); // Repaint to show updates
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPanel main = new MainPanel();
            main.setVisible(true);
        });
    }
}
