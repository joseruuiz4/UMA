public class ElemMayoritario {
    public static class Res{
        public int elem;
        public boolean existe;

        public Res(int elem, boolean existe) {
            this.elem = elem;
            this.existe = existe;
        }

        @Override
        public String toString() {
            if (!existe) return "NA";
            return Integer.toString(elem);
        }

        @Override
        public boolean equals(Object obj) {
            boolean ok = this == obj;
            if (!ok && obj instanceof Res) {
                Res aux = (Res)obj;

                ok = (!aux.existe && !this.existe) ||
                        (aux.elem == this.elem && aux.existe && this.existe);

            }
            return ok;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(elem);
        }
    }//Fin class Ress


    public static Res mayoritario(int[] v) {
        return mayoritarioR(v,0,v.length-1);
    }

    private static Res mayoritarioR(int[] v, int i, int j){
        //Realizar por el alumno
        if(i == j) return new Res(v[i], true);
        else{
            int m = (i + j)/2;
            Res izq = mayoritarioR(v, i, m);
            Res der = mayoritarioR(v, m+1, j);

            if(izq.existe && der.existe){
                if(izq.elem == der.elem) return new Res(izq.elem, true);
                else{
                    Res sol = analizar(v, i, j, izq.elem);
                    if(!sol.existe) sol = analizar(v, i, j, der.elem);

                    return sol;
                }
            }else if(izq.existe) return analizar(v, i, j, izq.elem);
            else if(der.existe) return analizar(v, i, j, der.elem);
            else return new Res(0, false);
        }
    }

    private static Res analizar(int[]v, int i, int j, int e){
        int n = j-i+1; int cont = 0;
        int k = i;
        while(k <= j && cont <= n/2){
            if(v[k++] == e)cont++;
        }
        if(cont > n/2) return new Res(e, true);
        else return new Res(e, false);
    }

}//Fin class ElemMayoritario