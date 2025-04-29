package domain;


import javax.swing.*;
import java.awt.*;

public class ventanaMapa extends JFrame {
    private mapaPanel mapaPanel;
    private gestionParques gestor;

    public ventanaMapa(mapaPanel mapa) {
        super("Mapa del Parque");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLayout(new BorderLayout());
        add(mapa, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    


}
