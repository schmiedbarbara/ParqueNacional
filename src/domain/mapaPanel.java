package domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class mapaPanel extends JPanel {
    private gestionParques gestor;
    private ArrayList<Point> posiciones = new ArrayList<>();
    private Random random = new Random();


    public mapaPanel(gestionParques gestor) {
        this.gestor = gestor;

    }

    //Inicializa gestor sin generar un parque
    public void inicializarGestor(gestionParques gestor) {
        this.gestor = gestor;
        repaint();
    }

    //Actualiza el gestor con sus parques
    public void actualizarGestor(gestionParques gestor) {
        this.gestor = gestor;
        posicionarSenderos();
        repaint();
    }

    
    private void posicionarSenderos() {
        if (gestor != null) {
            int estaciones = gestor.verVecinos(0).size() + 1;
            for (int i = 0; i < estaciones; i++) {
                int x = 50 + random.nextInt(getWidth() - 100);
                int y = 50 + random.nextInt(getHeight() - 100);
                posiciones.add(new Point(x, y));
            }
        }
    }
    public void agregarEstacion(String nombre, int x, int y) {
        posiciones.add(new Point(x, y));
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gestor == null) return;
        int tamano = posiciones.size();

        // Dibuja los senderos
        for (int i = 0; i < tamano; i++) {
            for (int j : gestor.verVecinos(i)) {
                if (i < j) { // evitar duplicar líneas
                    Point p1 = posiciones.get(i);
                    Point p2 = posiciones.get(j);
                    g.setColor(Color.red.darker());
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);

                }
            }
        }

        // Dibuja las estaciones
        for (int i = 0; i < tamano; i++) {
            Point p = posiciones.get(i);
            g.setColor(Color.BLUE.darker());
            g.fillOval(p.x - 10, p.y - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), p.x - 5, p.y - 15);
        }
    }
}
