/*
 * Dado un vector V de n enteros (no necesariamente ordenados),
 * escribir un algoritmo basado en divide y vencerás que encuentre
 * el valor máximo del vector.
 *
 * Complejidad esperada: Θ(n)
 */
public class MaximoVector {

    public static int maximo(Integer[] v) {
        return maximo_REC(v, 0, v.length - 1);
    }

    private static int maximo_REC(Integer[] v, int i, int j) {

        if(i == j) return v[i];
        else{


        int m = (i + j) / 2;

        int izq = maximo_REC(v, i, m);
        int der = maximo_REC(v, m+1, j);

        return (izq > der) ? izq : der;
        }
    }


}
