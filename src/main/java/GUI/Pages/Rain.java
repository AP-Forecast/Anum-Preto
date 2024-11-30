package GUI.Pages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Rain extends JPanel{

    private JPanel rainPanel;

    public Rain(File icons){
        rainPanel = new JPanel(new GridBagLayout());
        rainPanel.setBackground(Color.white);

        GridBagConstraints positioner = new GridBagConstraints();

        positioner.gridx = 0;



    }

}
