package domain;

import java.util.HashSet;
import java.util.Set;

public class Parque {
    
    private int[][] A; // para representar los pesos vamos a usar una matriz de enteros, en lugar de 1 o 0 tendremos cada peso en la matriz    									
    public Parque(int vertices) {
        A = new int[vertices][vertices];							
        for (int i = 0; i < vertices; i++) { 
            for (int j = 0; j < vertices; j++) {
                A[i][j] = 0;  // inicializamos las aristas no existentes con 0
            }
        }
    }
    
    // agregado de aristas con peso
    public void agregarArista(int i, int j, int peso) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);        
        A[i][j] = peso;
        A[j][i] = peso; 
    }

    // eliminacion de aristas
    public void eliminarArista(int i, int j) {
        verificarVertice(i);
        verificarVertice(j);
        verificarDistintos(i, j);        
        A[i][j] = 0; 
        A[j][i] = 0;
    }

    public int pesoArista(int i, int j) {     // informa el peso de la arista especificada
        verificarVertice(i);
        verificarVertice(j);
        return A[i][j];
    }

    // cantidad de vertices
    public int tamano() {
        return A.length;
    }
    
    // vecinos de un vertice
    public Set<Integer> vecinos(int i) {
        verificarVertice(i);
        
        Set<Integer> ret = new HashSet<Integer>();
        for (int j = 0; j < this.tamano(); ++j) if (i != j) {
            if (A[i][j] != 0) { 
                ret.add(j);
            }
        }
        return ret;        
    }
    
    private void verificarVertice(int i) {
        if (i < 0)
            throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
        
        if (i >= A.length)
            throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
    }

    private void verificarDistintos(int i, int j) {
        if (i == j)
            throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
    }
}
