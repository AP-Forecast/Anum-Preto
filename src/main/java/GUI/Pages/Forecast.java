package GUI.Pages;

import javax.swing.*;
import java.awt.*;

public class Forecast  extends Panel{

    private JPanel forecastPanel;
    private JScrollPane scroller;

    public Forecast(){
        forecastPanel = new JPanel(new GridBagLayout());
        //forecastPanel.setPreferredSize(new Dimension(800, 600));
        GridBagConstraints outerPositioner = new GridBagConstraints();
        JPanel verticalDisplay;

        JLabel day;
        outerPositioner.gridy = 0;

        for (int i = 0; i < 7; i++) {
            // Defining the position of the element on the parent panel
            outerPositioner.gridx = i;
            // Creating a panel for vertical stacking
            verticalDisplay = new JPanel();
            verticalDisplay.setLayout(new BoxLayout(verticalDisplay, BoxLayout.Y_AXIS));
            // Adding content to the panel
            day = new JLabel("Data from day " + (i + 1));
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);
            // Set preferred size for each vertical panel
            verticalDisplay.setPreferredSize(new Dimension(400, 600)); // Adjust width as needed
            verticalDisplay.setMaximumSize(new Dimension(150, Short.MAX_VALUE)); // Prevent compression
            // Adding nested panel to the parent element
            forecastPanel.add(verticalDisplay, outerPositioner);
        }

        scroller = new JScrollPane(
                forecastPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

    }

}
