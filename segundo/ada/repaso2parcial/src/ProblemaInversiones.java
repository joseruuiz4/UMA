public class ProblemaInversiones {

    public static int numInversiones(int[] v) {
        return numInv(v,0,v.length-1);
    }

    private static int numInv(int[] a, int prim, int ult) {
        int suma = 0;
        //Completar la implementación de este método
        if(prim < ult){
            int m = (prim+ult)/2;
            suma+=numInv(a,m+1,ult);
            suma+=numInv(a,prim,m);
            suma+=mezcla(a,prim,m,ult);

        }
        return suma;
    }


    private static int mezcla(int[]v, int p, int m, int u){
        int suma = 0;
        int[] aux= new int[u-p+1];
        int i = p;
        int j = m +1;
        int k =0;

        while(i<=m && j <= u){
            if(v[i]<=v[j]) aux[k++]=v[i++];
            else{
                aux[k++]=v[j++];
                suma+= m-i+1;
            }
        }

        while(i<=m)aux[k++]=v[i++];
        while(j<=u)aux[k++]=v[j++];

        k=0;
        i=p;
        while(i<=u) v[i++]=aux[k++];

        return suma;
    }

}
