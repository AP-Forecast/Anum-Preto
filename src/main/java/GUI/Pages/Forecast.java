package GUI.Pages;

import API.database.models.Daily;
import API.database.utils.CRUD_Operator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Forecast  extends Panel{

    private JPanel forecastPanel;
    private JScrollPane scroller;
    private CRUD_Operator doc = new CRUD_Operator();

    public Forecast(){
        forecastPanel = new JPanel(new GridBagLayout());
        //forecastPanel.setPreferredSize(new Dimension(800, 600));
        GridBagConstraints outerPositioner = new GridBagConstraints();
        JPanel verticalDisplay;

        JLabel day;
        outerPositioner.gridy = 0;

        List<Daily> dailyList = doc.readAllDays();

        for (int i = 0; i < 7; i++) {
            // Defining the position of the element on the parent panel
            outerPositioner.gridx = i;
            // Creating a panel for vertical stacking
            verticalDisplay = new JPanel();
            verticalDisplay.setBackground(Color.white);
            verticalDisplay.setLayout(new BoxLayout(verticalDisplay, BoxLayout.Y_AXIS));
            // Adding content to the panel
            day = new JLabel("Data from day " + (i + 1));
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Sensação térmica max (ºC): " + dailyList.get(i).getApparentTempMax().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Sensação térmica min (ºC): " + dailyList.get(i).getApparentTempMin().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Temperatura real max (ºC): " + dailyList.get(i).getTemperatureMax().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Temperatura real min (ºC): " + dailyList.get(i).getTemperatureMin().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Total de chuva (mm): " + dailyList.get(i).getPreciptationSum().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Chance (%): " + dailyList.get(i).getRainSum().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Direção do vento (º): " + dailyList.get(i).getDomWindDirection().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Velocidade do vento (km/h): " + dailyList.get(i).getMaxWindSpeed().toString());
            day.setFont(new Font("Arial", Font.PLAIN, 24));
            verticalDisplay.add(day);

            day = new JLabel("Indice UV: " + dailyList.get(i).getUvIndex().toString());
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
