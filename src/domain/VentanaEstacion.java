package domain;

import javax.swing.*;
import java.awt.*;

public class VentanaEstacion extends JFrame {
    private JTextField cantidadEstacionesField;
    private gestionParques gestor = new gestionParques();

    public VentanaEstacion() {
        // Inicialización VentanaInicio
        setTitle("PARQUE NACIONAL");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        JPanel panelFondo = new JPanel() {
            Image fondo = new ImageIcon(getClass().getResource("/domain/inicio.jpg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibuja la imagen a medida del panel
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelFondo.setLayout(new BoxLayout(panelFondo, BoxLayout.Y_AXIS));
        panelFondo.setOpaque(false);

        // Configuración del Panel Principal
        JPanel panel = new JPanel();

        // Configuración del Título
        JLabel titulo = new JLabel("CREA TU PARQUE NACIONAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 34)); // Ajusté el tamaño para que no se pase
        titulo.setForeground(Color.black);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFondo.add(Box.createVerticalStrut(50)); // Espaciado superior
        panelFondo.add(titulo);

 
        
        // Campo de texto para ingresar cantidad de estaciones
        cantidadEstacionesField = new JTextField();
        cantidadEstacionesField.setPreferredSize(new Dimension(500, 30));
        cantidadEstacionesField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelFondo.add(Box.createVerticalStrut(20));
        panelFondo.add(cantidadEstacionesField);

        // Inicialización de Botón
        JButton crearParqueBoton = new JButton("Crear Parque");

        crearParqueBoton.setPreferredSize(new Dimension(150, 40));
        crearParqueBoton.setRolloverEnabled(true); // Cursor ilumina botón
        JPanel botonYPreguntaPanel = new JPanel();
        botonYPreguntaPanel.setOpaque(false);
        botonYPreguntaPanel.setLayout(new FlowLayout());


        JLabel preguntaLabel = new JLabel("¿De cuántas estaciones quiere su parque?");
        preguntaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        botonYPreguntaPanel.add(crearParqueBoton);
        botonYPreguntaPanel.add(preguntaLabel);

        panelFondo.add(Box.createVerticalStrut(20));
        panelFondo.add(botonYPreguntaPanel);

        // Usar el panel personalizado como content pane
        setContentPane(panelFondo);
        

        crearParqueBoton.addActionListener(e -> {
            try {
                int cantidad = Integer.parseInt(cantidadEstacionesField.getText());
                JOptionPane.showMessageDialog(this, "Parque creado exitosamente. Cuentas con " + cantidad + " estaciones");
                new interfazVisual(cantidad); // PASAMOS LA CANTIDAD
                
                dispose(); // Cerramos esta ventana
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Agregar el título y el botón al panel
        panel.add(titulo, BorderLayout.CENTER);
        panel.add(cantidadEstacionesField, BorderLayout.CENTER);
        panel.add(crearParqueBoton, BorderLayout.SOUTH);
        panel.add(preguntaLabel,BorderLayout.NORTH);

        // Agregar el panel al frame
        add(panel);

        // Mostrar la ventana
        setVisible(true);
    }
}
