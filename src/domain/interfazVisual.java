package domain;

import javax.swing.*;
import java.awt.*;

public class interfazVisual extends JFrame {
    private gestionParques gestor;
    private mapaPanel mapaPanel;
    

    public interfazVisual(int estaciones) {
        //Inicializacion de ventana
        super("Bienvenido al Parque Nacional");
        mapaPanel = new mapaPanel(gestor);
        gestor = new gestionParques();
        gestor.crearParque(estaciones);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLayout(new BorderLayout());
        agregarPanelCarga();
        agregarPanelVisualizacion();
        setVisible(true);
        setLocationRelativeTo(null);
        
    }

    //
    private void agregarPanelCarga() {

        //Panel donde la persona va a crear los senderos
        JPanel panelCarga = new JPanel();
        panelCarga.setLayout(new BoxLayout(panelCarga, BoxLayout.Y_AXIS));
        panelCarga.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel titulo = new JLabel("CREA NUEVOS SENDEROS");
        titulo.setFont(new Font("Arial", Font.BOLD, 35));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel panelSenderos = new JPanel();
        panelSenderos.setLayout(new GridLayout(3, 2, 10, 10));
        JLabel primerEstacion = new JLabel("Primer estacion: ");
        JTextField primerEstacionTexto = new JTextField();
        JLabel segundaEstacion = new JLabel("Segunda estacion: ");
        JTextField segundaEstacionTexto = new JTextField();
        JLabel impactoAmbiental = new JLabel("Impacto ambiental: ");
        JTextField impactoAmbientalTexto = new JTextField();

        //Botones 
        JButton agregarSenderoButton = new JButton("Agregar Sendero");
        agregarSenderoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton verMapaButton = new JButton("Ver Mapa");
        
        //Agregamos los elementos al panel
        panelSenderos.add(primerEstacion);
        panelSenderos.add(primerEstacionTexto);
        panelSenderos.add(segundaEstacion);
        panelSenderos.add(segundaEstacionTexto);
        panelSenderos.add(impactoAmbiental);
        panelSenderos.add(impactoAmbientalTexto);
        
        
        //Accion al agregar un sendero
        agregarSenderoButton.addActionListener(e -> {
                int estacionUno = Integer.parseInt(primerEstacionTexto.getText());
                int estacionDos = Integer.parseInt(segundaEstacionTexto.getText());
                int impactoAmbientalValor = Integer.parseInt(impactoAmbientalTexto.getText());
                gestor.agregarSendero(estacionUno, estacionDos, impactoAmbientalValor);
                mapaPanel.actualizarGestor(gestor);
                
                primerEstacionTexto.setText("");
                segundaEstacionTexto.setText("");
                impactoAmbientalTexto.setText(""); 
                JOptionPane.showMessageDialog(this, "Sendero generado: " + estacionUno + " y " + estacionDos);
                repaint();
                
        });
        verMapaButton.addActionListener(e -> {
            agregarPanelVisualizacion();

            
        });

        panelCarga.add(titulo);
        panelCarga.add(Box.createRigidArea(new Dimension(0, 20))); // espacio
        panelCarga.add(panelSenderos);
        panelCarga.add(Box.createRigidArea(new Dimension(0, 20))); // espacio
        panelCarga.add(agregarSenderoButton);
        panelCarga.add(verMapaButton);
        add(panelCarga, BorderLayout.WEST);
    }


    private void agregarPanelVisualizacion() {
        JPanel panelVisualizacion = new JPanel();
        add(panelVisualizacion, BorderLayout.SOUTH);
         // Agregamos el mapa generado al panel para visualizarlo
        add(mapaPanel, BorderLayout.CENTER); //
        ventanaMapa ventana = new ventanaMapa(mapaPanel);
        ventana.setVisible(true);
    }

    

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	VentanaEstacion estacionVentana = new VentanaEstacion();
                    estacionVentana.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
