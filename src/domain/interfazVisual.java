package domain;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class interfazVisual extends JFrame {
    private gestionParques gestor;
    private mapaPanel mapaPanel;

    private JTextField primerEstacionTexto;
    private JTextField segundaEstacionTexto;
    private JTextField impactoAmbientalTexto;

    public interfazVisual(int estaciones) {
        super("Bienvenido al Parque Nacional");
        gestor = new gestionParques();
        gestor.crearParque(estaciones);
        mapaPanel = new mapaPanel(gestor);
        inicializar();
    }

    public void inicializar() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLayout(new BorderLayout());
        agregarPanelCarga();
        agregarPanelVisualizacion();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Se agregan los elementos visuales a la pantalla
    private void agregarPanelCarga() {
        JPanel panelCarga = crearPanelCarga();
        JLabel titulo = crearTitulo();
        JPanel panelSenderos = crearPanelSenderos();
        JButton agregarSenderoButton = crearBotonAgregarSendero();
        JButton verMapaButton = crearBotonVerMapa();
        JButton calcularAGMKruskalButton = crearBotonAGMKruskal();
        JButton calcularAGMPRIMButton = crearBotonAGMPrim();
        JButton limpiarAGMButton = crearBotonLimpiarAGM();
        
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        JPanel panelMasBotones = new JPanel();
        panelCarga.add(titulo);
        panelCarga.add(panelSenderos);
        panelBotones.add(agregarSenderoButton);
        panelBotones.add(verMapaButton);
        JLabel tituloAGM = new JLabel("Visualice el Algoritmo Prim y Kruskal: ");
        
        panelBotones.add(tituloAGM);
        panelMasBotones.add(calcularAGMKruskalButton);
        panelMasBotones.add(calcularAGMPRIMButton);
        panelMasBotones.add(limpiarAGMButton);

    //direccionamiento de botones 
    panelCarga.add(panelBotones);
        add(panelCarga, BorderLayout.WEST);
              panelCarga.add(panelMasBotones);
        add(panelCarga, BorderLayout.WEST);
    }

    private JPanel crearPanelCarga() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }

    private JLabel crearTitulo() {
        JLabel titulo = new JLabel("CREA NUEVOS SENDEROS");
        titulo.setFont(new Font("Arial", Font.BOLD, 40));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titulo;
    }

    private JPanel crearPanelSenderos() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        primerEstacionTexto = new JTextField();
        segundaEstacionTexto = new JTextField();
        impactoAmbientalTexto = new JTextField();

        panel.add(new JLabel("Primer estación: "));
        panel.add(primerEstacionTexto);
        panel.add(new JLabel("Segunda estación: "));
        panel.add(segundaEstacionTexto);
        panel.add(new JLabel("Impacto ambiental: "));
        panel.add(impactoAmbientalTexto);
        return panel;
    }

    private JButton crearBotonAgregarSendero() {
        JButton botonAgregarSendero = new JButton("Agregar Sendero");
        botonAgregarSendero.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAgregarSendero.addActionListener(e -> {
            try {
                int estacionUno = Integer.parseInt(primerEstacionTexto.getText());
                int estacionDos = Integer.parseInt(segundaEstacionTexto.getText());
                int impacto = Integer.parseInt(impactoAmbientalTexto.getText());
                int max = gestor.tamano();

                if (estacionUno < 0 || estacionDos < 0 || estacionUno >= max || estacionDos >= max) {
                    JOptionPane.showMessageDialog(this, "Una o ambas estaciones no existen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                gestor.agregarSendero(estacionUno, estacionDos, impacto);
                mapaPanel.actualizarGestor(gestor);
                mapaPanel.revalidate();
                mapaPanel.repaint();

                primerEstacionTexto.setText("");
                segundaEstacionTexto.setText("");
                impactoAmbientalTexto.setText("");
                JOptionPane.showMessageDialog(this, "Sendero generado: " + estacionUno + " y " + estacionDos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return botonAgregarSendero;
    }

    private JButton crearBotonVerMapa() {
        JButton botonVerMapa = new JButton("Ver Mapa");
        botonVerMapa.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMapa.addActionListener(e -> agregarPanelVisualizacion());
        return botonVerMapa;
    }

    private JButton crearBotonAGMPrim() {
        JButton botonAGMPrim = new JButton("Calcular AGM con PRIM");
        botonAGMPrim .setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAGMPrim .addActionListener(e -> {
            long inicio = System.currentTimeMillis();
            int[][] agm = gestor.algoritmoPrim();
            long fin = System.currentTimeMillis();

            mapaPanel.dibujarAGM(agm);
            mapaPanel.repaint();

            JOptionPane.showMessageDialog(this, "Tiempo de cálculo con PRIM: " + (fin - inicio) + " ms.");
        });
        return botonAGMPrim ;
    }

    private JButton crearBotonAGMKruskal() {
        JButton botonAGMKruskal = new JButton("Calcular AGM con Kruskal");
        botonAGMKruskal.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAGMKruskal.addActionListener(e -> {
            long inicio = System.currentTimeMillis();
            int[][] agm = gestor.algoritmoKruskal();
            long fin = System.currentTimeMillis();

            mapaPanel.dibujarAGM(agm);
            mapaPanel.repaint();

            JOptionPane.showMessageDialog(this, "Tiempo de cálculo con Kruskal: " + (fin - inicio) + " ms.");
        });
        return botonAGMKruskal;
    }

    private JButton crearBotonLimpiarAGM() {
        JButton botonLimpiarAGM = new JButton("Limpiar AGM");
        botonLimpiarAGM.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonLimpiarAGM.addActionListener(e -> {
            mapaPanel.limpiarAGM();
            mapaPanel.repaint();
        });
        return botonLimpiarAGM;
    }

    private void agregarPanelVisualizacion() {
        JPanel panelVisualizacion = new JPanel();
        add(panelVisualizacion, BorderLayout.SOUTH);
        add(mapaPanel, BorderLayout.CENTER);
        ventanaMapa ventana = new ventanaMapa(mapaPanel);
        ventana.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VentanaEstacion estacionVentana = new VentanaEstacion();
                estacionVentana.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
