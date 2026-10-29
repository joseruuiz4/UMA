/*
 * Dado un vector ordenado V de n enteros distintos,
 * escribir un algoritmo que en tiempo O(logn) encuentre
 * un número i tal que 1 ≤ i ≤ n y V[i] = i,
 * siempre que exista.
 *
 * En caso de que no exista, debera devolver -1
 * */
public class ElemEnPos {

    public static int elemEnPos(Integer[] v) {
        return elemEnPos_REC(v,0,v.length-1);
    }

    private static int elemEnPos_REC(Integer[] v, int i, int j) {
        // A REALIZAR POR EL ALUMNO
        return -5;
    }
}//end class ElemEnPos