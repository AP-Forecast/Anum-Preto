package GUI.Pages;

import API.database.models.Hourly;
import API.database.utils.CRUD_Operator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalTime;
import java.util.List;

public class UVIndex extends JPanel {

    private CRUD_Operator doc = new CRUD_Operator();

    List<Hourly> hours = doc.readHour(78);
    LocalTime hourNow = LocalTime.now();
    LocalTime hourAPI;

    private Double uvIndex;
    private int marker;
    private JPanel uvPanel;
    private static final Color[] COLORS = {
            Color.green,
            Color.yellow,
            Color.orange,
            Color.red,
            Color.magenta
    };
    private static final String[] INDEX_UV = {"1-2", "3-5", "6-7", "8-10", "11+"};

    private static final String[] INFORMATION = {
            "Não precisa usar nada",
            "Use protetor solar!",
            "Use protetor solar e roupas compridas",
            "Use protetor solar, roupas compridas e ande pela sombra",
            "Se possivel, evite sair de casa"
    };


    public UVIndex(File file){
        JPanel innerPanel;
        JLabel index;

        for (Hourly hour : hours){
            hourAPI = LocalTime.parse(hour.getHour());
            if (hourNow.isAfter(hourAPI)){

                uvIndex = hour.getUvIndex();

                if (uvIndex >= 0 && uvIndex < 3){
                    marker = 0;
                } else if (uvIndex >= 3  && uvIndex < 6) {
                    marker = 1;
                } else if (uvIndex >= 6 && uvIndex < 8) {
                    marker = 2;
                } else if (uvIndex >= 8 && uvIndex < 11) {
                    marker = 3;
                } else if (uvIndex >= 11) {
                    marker = 4;
                }
                break;
            }
        }

        uvPanel = new JPanel(new GridBagLayout());
        GridBagConstraints outerPositioner = new GridBagConstraints();

        outerPositioner.gridx = 0; //One singular column
        outerPositioner.fill = GridBagConstraints.BOTH;
        outerPositioner.weightx = 1.0; // Allow horizontal expansion
        outerPositioner.weighty = 1.0;

        // Line 1 (blank)
        outerPositioner.gridy = 0;
        innerPanel = new JPanel();
        innerPanel.setBackground(Color.white);
        uvPanel.add(innerPanel, outerPositioner);

        //Line 2 (information based on the UV index)
        outerPositioner.gridy = 1;
        outerPositioner.fill = GridBagConstraints.BOTH;
        JLabel info = new JLabel(INFORMATION[marker]);
        info.setFont(new Font("Arial", Font.PLAIN, 24));
        innerPanel = new JPanel();
        innerPanel.setBackground(Color.white);
        innerPanel.add(info);
        uvPanel.add(innerPanel, outerPositioner);

        //Line 3
        outerPositioner.gridy = 2;
        outerPositioner.fill = GridBagConstraints.BOTH;
        JPanel sub;

        innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints positioner = new GridBagConstraints();
        positioner.gridy = 0;
        positioner.fill = GridBagConstraints.BOTH;
        positioner.weightx = 1.0;
        positioner.weighty = 1.0;


        for (int j = 0; j < INDEX_UV.length; j++){
            positioner.gridx = j;
            if (j == marker){
                sub = new JPanel();
                sub.setBackground(Color.white);
                try {
                    Image image = ImageIO.read(new File(file, "slider.png"));
                    Image resized = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    index = new JLabel(new ImageIcon(resized));
                    sub.add(index);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(innerPanel, e.getMessage());
                }
                innerPanel.add(sub, positioner);
            } else {
                sub = new JPanel();
                sub.setBackground(Color.white);
                innerPanel.add(sub, positioner);
            }
        }

        positioner.gridy = 1;
        positioner.fill = GridBagConstraints.BOTH;


        for (int i = 0; i < INDEX_UV.length; i++){
            positioner.gridx = i;

            sub = new JPanel();
            sub.setBackground(COLORS[i]);

            index = new JLabel(INDEX_UV[i]);
            sub.add(index);
            innerPanel.add(sub, positioner);
        }

        uvPanel.add(innerPanel, outerPositioner);
        uvPanel.setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800, 600));
        add(uvPanel);

    }

}
