package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
    
    public int tamano() {
        return parque.tamano();
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
    
    public int[][] algoritmoKruskal() {
        int numeroEstaciones = parque.tamano();
        List<int[]> aristas = new ArrayList<>();

        // recopilar todas las aristas con sus pesos
        for (int i = 0; i < numeroEstaciones; i++) {
            for (int j = i + 1; j < numeroEstaciones; j++) {
                if (parque.pesoArista(i, j) > 0) {
                    aristas.add(new int[]{i, j, parque.pesoArista(i, j)});
                }
            }
        }

        // aca se ordena por peso
        aristas.sort(Comparator.comparingInt(e -> e[2]));

        int[][] agm = new int[numeroEstaciones][numeroEstaciones];
        int[] parent = new int[numeroEstaciones];

        // inciarlizardor de conjuntos
        for (int i = 0; i < numeroEstaciones; i++) {
            parent[i] = i;
        }

        for (int[] arista : aristas) {
            int origen = arista[0];
            int destino = arista[1];

            if (find(origen, parent) != find(destino, parent)) {
                union(origen, destino, parent);
                agm[origen][destino] = arista[2];
                agm[destino][origen] = arista[2];
            }
        }

        return agm;
    }

    // funcion para encontrar el conjunto al que pertenece un vertice
    private int find(int i, int[] parent) {
        while (i != parent[i]) i = parent[i];
        return i;
    }

    // funcion para unir dos conjuntos
    private void union(int i, int j, int[] parent) {
        int ri = find(i, parent);
        int rj = find(j, parent);
        if (ri != rj) {
            parent[ri] = rj;
        }
    }
    
}
