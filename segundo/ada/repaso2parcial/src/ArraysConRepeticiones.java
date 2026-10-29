public class ArraysConRepeticiones {

    //Precondición: hay un elemento repetido=> v.length >= 2
    public static int encuentraElem(int [] v) {
        return encuentraElem(v,0,v.length-1);
    }

    private static int encuentraElem(int [] v, int izq, int der) {
        //Completar la implementación del método
        if(izq == der) return v[izq];

        else{
            int m = (izq+der)/2;
            if(v[m]-v[0] == m) return encuentraElem(v, m+1, der);
            else return encuentraElem(v, izq, m);
        }
    }
}
