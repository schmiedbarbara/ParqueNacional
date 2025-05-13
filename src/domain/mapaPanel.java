package domain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class mapaPanel extends JPanel {
    private gestionParques gestor;
    private ArrayList<Point> posiciones;
    private int[][] agm; // para guardar el AGM


    public mapaPanel(gestionParques gestor) {
        this.gestor = gestor;
        this.posiciones = new ArrayList<>();
    }
    
    
    
    public void dibujarAGM(int[][] agm) {
        this.agm = agm;
    }
    
    public void limpiarAGM() { // para limpiar el AGM
        this.agm = null;
    }

    //inciializa un gestor sin crear parque
    public void inicializarGestor(gestionParques gestor) {
        this.gestor = gestor;
        repaint();
    }

    //aca se actualiza el gestor con sus parques
    public void actualizarGestor(gestionParques gestor) {
        this.gestor = gestor;
        posicionarSenderos();
        repaint();
    }


    
    private void posicionarSenderos() {
        posiciones.clear();
        int estaciones = gestor.tamano();
        int radio = Math.min(getWidth(), getHeight()) / 2 - 50;
        int centroX = getWidth() / 2;
        int centroY = getHeight() / 2;

        for (int i = 0; i < estaciones; i++) {
            double angulo = 2 * Math.PI * i / estaciones;
            int x = (int) (centroX + radio * Math.cos(angulo));
            int y = (int) (centroY + radio * Math.sin(angulo));
            posiciones.add(new Point(x, y));
        }
    }

    public void agregarEstacion(String nombre, int x, int y) {
        posiciones.add(new Point(x, y));
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gestor == null || posiciones.size() < gestor.tamano()) return;
        
        int tamano = posiciones.size();

        for (int i = 0; i < tamano; i++) {
            for (int j : gestor.verVecinos(i)) {
                if (i < j) {
                    Point p1 = posiciones.get(i);
                    Point p2 = posiciones.get(j);
                    g.setColor(Color.RED);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);

                    int midX = (p1.x + p2.x) / 2;
                    int midY = (p1.y + p2.y) / 2;
                    
                    int peso = gestor.verPesoSendero(i, j);
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(peso), midX, midY);
                }
            }
        }

        // aca dibuja el AGM
        if (agm != null) {
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if (agm[i][j] > 0) { // dibuja solo si hay una conexión en el AGM
                        Point p1 = posiciones.get(i);
                        Point p2 = posiciones.get(j);
                        g.setColor(Color.green.darker()); // color para el AGM
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        }

        // dibuja las estaciones
        for (int i = 0; i < tamano; i++) {
            Point p = posiciones.get(i);
            g.setColor(Color.BLUE);
            g.fillOval(p.x - 10, p.y - 10, 20, 20);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), p.x - 5, p.y - 15);
        }
    }
}
    


