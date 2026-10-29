/*
 * Dado un número natural n, se desea determinar si n es el producto de tres
 * números naturales consecutivos. Por ejemplo, 210 = 5 · 6 · 7. Sin embargo, 12
 * no puede ser obtenido como el producto de tres números consecutivos.
 **/
public class ProductoConsecutivos {

    public static class Resultado{
        private long inicio; //valor más pequeño de los tres
        private boolean existe; //true si existe el resultado, false en caso contrario

        public Resultado(){ //No hay solución
            inicio = -1;
            existe = false;
        }

        public Resultado(long factor1){//La solución es f1*f2*f3
            inicio = factor1;
            existe = true;
        }
        public boolean existe() {
            return this.existe;
        }
        @Override
        public String toString(){
            return existe? "(" + inicio + "," + (inicio+1) +
                    "," + (inicio+2) + ")" : "NA";
        }
    }

    public static Resultado productoConsecutivos(long n) {
        //Definir un rango adecuado

        long inicio =1; //COMPLETAR
        long fin = n;   //COMPLETAR
        return productoConsecutivos_DyV(n,inicio, fin);
    }

    private static Resultado productoConsecutivos_DyV(long n, long ini, long fin) {

        //ESTA FUNCIÓN DEBE SER COMPLETADA
        if(fin < ini) return new Resultado();
        long m =  (ini+fin)/2;
        long mul = m * (m+1) * (m+2);
        if(mul == n) return new Resultado(m);
        else if(mul > n) return productoConsecutivos_DyV(n, ini, m-1);
        else return productoConsecutivos_DyV(m, m+1, fin);
    }

}//end class ProductoConsecutivos