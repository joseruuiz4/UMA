public class KesimoElemento {

    public static int buscarKesimo(int[] a, int k) {
        return buscarKesimo(a, k, 0, a.length - 1);
    }

    private static int buscarKesimo(int[] v, int k, int ini, int fin) {
        //Completar la implementación de este método
        if(ini==fin) return v[k];
        else{
            int KPrima = partir(v, ini, fin);

            if(KPrima == k) return v[k];
            else if(KPrima < k) return buscarKesimo(v, k, KPrima+1, fin);
            else return buscarKesimo(v, k, ini, KPrima-1);
        }

    }

    private static int partir(int[] v, int ini, int fin){
        int pivote = v[ini];
        int i = ini+1;
        int j = fin;

        while(i <= j){
            while(i <= j && v[i] <= pivote) i++;
            while(i <= j && v[j] > pivote) j--;
            if(i<=j) swap(v, i, j);
        }
        swap(v, ini, j);
        return j;
    }

    private static void swap(int[] v, int i, int j){
        int aux = v[i];
        v[i]=v[j];
        v[j]=aux;
    }

}
