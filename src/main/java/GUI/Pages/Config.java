package GUI.Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import API.Localizer;

public class Config extends JPanel {

    JPanel configPanel;
    JButton send;

    public Config(File icons){
        configPanel = new JPanel(new GridBagLayout());
        configPanel.setBackground(Color.white);

        GridBagConstraints positioner = new GridBagConstraints();


        //row 0, col 0
        positioner.gridx = 0;
        positioner.gridy = 0;
        positioner.anchor = GridBagConstraints.FIRST_LINE_END;
        positioner.insets = new Insets(20, 10, 20, 10); // padding element from css

        JLabel latitude = new JLabel("Latitude");
        configPanel.add(latitude, positioner);


        //row 0, col 1
        positioner.gridx = 1;
        positioner.anchor = GridBagConstraints.FIRST_LINE_START;
        positioner.insets = new Insets(15, 10, 20, 10);

        JTextField latitudeData = new JTextField();
        latitudeData.setPreferredSize(new Dimension(260, 30));
        latitudeData.setFont(new Font("Arial", Font.PLAIN, 24));


        configPanel.add(latitudeData, positioner);

        //row 1, col 0
        positioner.gridx = 0;
        positioner.gridy = 1;
        positioner.anchor = GridBagConstraints.FIRST_LINE_END;
        positioner.insets = new Insets(20, 10, 20, 10);

        JLabel longitude = new JLabel("Longitude");
        configPanel.add(longitude, positioner);

        //row 1, col 1
        positioner.gridx = 1;
        positioner.anchor = GridBagConstraints.FIRST_LINE_START;
        positioner.insets = new Insets(15, 10, 20, 10);

        JTextField longitudeData = new JTextField();
        longitudeData.setFont(new Font("Arial", Font.PLAIN, 24));
        longitudeData.setPreferredSize(new Dimension(260, 30));
        configPanel.add(longitudeData, positioner);

        //row 2, col 0 (supposed to be blank)
        positioner.gridx = 0;
        positioner.gridy = 2;

        JPanel blank = new JPanel();
        blank.setBackground(Color.white);
        configPanel.add(blank, positioner);


        //row 2, col 1 (only for the send button)
        positioner.gridx = 1;
        positioner.anchor = GridBagConstraints.CENTER;
        positioner.insets = new Insets(20, 20, 20, 20);
        try {
            Image image = ImageIO.read(new File(icons, "send-button.png"));
            Image resized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            send = new JButton("Send", new ImageIcon(resized));
            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String lat = latitudeData.getText();
                    String longi = longitudeData.getText();

                    try{
                        Localizer.setLocation(lat, longi);
                        JOptionPane.showMessageDialog(configPanel, "Dados alterados com sucesso!");
                    } catch (Exception exception){
                        JOptionPane.showMessageDialog(configPanel, "Não consegui alterar seus dados.");
                    }
                }
            });
            configPanel.add(send, positioner);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        setLayout(new BorderLayout());
        setBackground(Color.white);
        add(configPanel, BorderLayout.CENTER);
    }

}
