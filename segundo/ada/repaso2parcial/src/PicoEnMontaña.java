/*
 * Dado un vector V de n enteros distintos tal que primero está ordenado
 * estrictamente de forma creciente y después estrictamente decreciente,
 * escribir un algoritmo que en tiempo O(logn) encuentre el índice del
 * elemento máximo (el punto de cambio).
 *
 * Ejemplo: V = [1, 3, 7, 12, 9, 5, 2] → debe devolver 3 (porque V[3] = 12 es el máximo)
 */
public class PicoEnMontaña {

    public static int pico(Integer[] v) {
        return pico_REC(v, 0, v.length - 1);
    }

    private static int pico_REC(Integer[] v, int i, int j) {

        if(i == j) return i;
        else{
            int m =  (i + j) / 2;

            if(m == 0) return (v[m] > v[m+1]) ? 0 : -1;

            if(v[m-1] < v[m] && v[m+1] < v[m]) return m;
            else if(v[m-1] < v[m] && v[m+1] > v[m]) return pico_REC(v, m+1, j);
            else return pico_REC(v, i, m - 1);

        }
    }
}
