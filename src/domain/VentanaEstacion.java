package domain;

import javax.swing.*;
import java.awt.*;

public class VentanaEstacion extends JFrame {
    private JTextField cantidadEstacionesField;
    private gestionParques gestor = new gestionParques();

    public VentanaEstacion() {

        // Inicialización VentanaInicio
        setTitle("PARQUE NACIONAL");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del Panel Principal
        JPanel panel = new JPanel();
        

        // Configuración del Título
        JLabel titulo = new JLabel("CREA TU PARQUE NACIONAL");
        titulo.setFont(new Font("Arial", Font.BOLD, 34)); // Ajusté el tamaño para que no se pase
        titulo.setForeground(Color.black);
        titulo.setBorder(BorderFactory.createEmptyBorder(200, 20, 20, 20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.CENTER);
        JLabel preguntaLabel = new JLabel("¿De cuántas estaciones quiere su parque?");
        preguntaLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        
        
        // Campo de texto para ingresar cantidad de estaciones
        cantidadEstacionesField = new JTextField();
        cantidadEstacionesField.setPreferredSize(new Dimension(500, 30));
        
        // Inicialización de Botón
        JButton crearParqueBoton = new JButton("Crear Parque");

        crearParqueBoton.setPreferredSize(new Dimension(150, 40));
        crearParqueBoton.setRolloverEnabled(true); // Cursor ilumina botón


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

        /*// Acción del botón para abrir la interfazVisual
        crearParqueBoton.addActionListener(e -> {
            new interfazVisual(); // Abrir la otra ventana
            dispose(); // Cerrar esta ventana
        });*/

        // Mostrar la ventana
        setVisible(true);
    }
}
