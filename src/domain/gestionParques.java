package domain;

import java.util.Set;

public class gestionParques {
    private Parque parque;

    // Crear un nuevo parque con un número específico de vértices
    public void crearParque(int numeroEstaciones) {
        parque = new Parque(numeroEstaciones);
    }

    
    
    // agregar un sendero entre dos estaciones con un peso pasado
    public void agregarSendero(int estacion1, int estacion2, int peso) {     
        parque.agregarArista(estacion1, estacion2, peso);
    }

    // elimina un sendero entre dos estaciones
    public void eliminarSendero(int estacion1, int estacion2) { 
        parque.eliminarArista(estacion1, estacion2);
    }

    // ver peso del sendero entre dos estaciones
    public int verPesoSendero(int estacion1, int estacion2) {   
        return parque.pesoArista(estacion1, estacion2);
    }

    // obtener vecinos de una estacion (por si sirve)
    public Set<Integer> verVecinos(int estacion) {     
        return parque.vecinos(estacion);
    }

   
    // imprimir toda la matriz de adyacencia para verificar 
    public void imprimirMatrizAdyacencia() {  
        int tamano = parque.tamano();
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                System.out.print(parque.pesoArista(i, j) + " ");
            }
            System.out.println();
        }
    }
    
    public int[][] algoritmoPrim() {
        int numeroEstaciones = parque.tamano();
        boolean[] estaEnAGM = new boolean[numeroEstaciones];  // aca van los vertices que estan en el AGM
        int[][] agm = new int[numeroEstaciones][numeroEstaciones];

        for (int i = 0; i < numeroEstaciones; i++) { //se pone en cero toda la matriz adyacencia del AGM
            for (int j = 0; j < numeroEstaciones; j++) {
                agm[i][j] = 0; 
            }
        }

       
        estaEnAGM[0] = true;  //arranca del primer vertice 
        int numeroDeAristas = 0;

        while (numeroDeAristas < numeroEstaciones - 1) { //condicion de while de ppt 
            int pesoMinimo = Integer.MAX_VALUE;
            int verticeDesde = -1, verticeHasta = -1;

            for (int i = 0; i < numeroEstaciones; i++) {             // busca la arista de pesmo minimo que conecta el AGM con los demas
                if (estaEnAGM[i]) {
                    for (int vecino : parque.vecinos(i)) {
                        int peso = parque.pesoArista(i, vecino);
                        if (!estaEnAGM[vecino] && peso < pesoMinimo) {
                            pesoMinimo = peso;
                            verticeDesde = i;
                            verticeHasta = vecino;
                        }
                    }
                }
            }

            if (verticeDesde != -1 && verticeHasta != -1) {
                agm[verticeDesde][verticeHasta] = pesoMinimo;             // se pone la arista encontrada en el AGM
                agm[verticeHasta][verticeDesde] = pesoMinimo; 
                estaEnAGM[verticeHasta] = true;
                numeroDeAristas++;
            }
        }

        return agm;  //devuelve una nueva matriz de adyacencia que solo tiene el AGM, asi podemos comparar ambas 
    }
}