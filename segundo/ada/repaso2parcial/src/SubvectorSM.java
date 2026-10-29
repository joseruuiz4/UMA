/*
 * Dado un vector V de n enteros. Escribir un algoritmo que en tiempo O(nlogn)
 * encuentre el subvector (formado por elementos consecutivos) cuya suma sea máxima
 * */
public class SubvectorSM {
    public static class Res{
        public int i,j,sum;

        public Res(int i, int j, int sum) {
            this.i = i;
            this.j = j;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Res [i=" + i + ", j=" + j + ", sum=" + sum + "]";
        }

        public Res max(Res otro) {
            Res res = null;
            if (this.sum > otro.sum) { res = this;}
            else if (this.sum < otro.sum)   { res = otro;}
            else{ //Menor el más corto
                if (this.j - this.i < otro.j - otro.i){
                    res = this;
                }else{
                    res = otro;
                }
            }
            return res;
        }
    }


    public static Res SM(Integer[] v) {
        return SMR(v,0,v.length-1);
    }

    private static Res SMR(Integer[] v, int i, int j) {
        if(i == j) return new Res(i, i, v[i]);
        else if(i == j - 1){
            if(v[i]>0 && v[j] > 0)return new Res(i, j, v[i] + v[j]);
            else if(v[i] > v[j]) return new Res(i, i, v[i]);
            else return new Res(j, j, v[j]);
        }else{
            int m = (i + j)/2;
            Res izq = SMR(v, i, m-1);
            Res der = SMR(v, m+1, j);
            Res cen = SMC(v, i, m, j);

            Res sol = izq.max(der);
            sol = sol.max(cen);
            return sol;
        }
    }


    private static Res SMC(Integer[] v, int i, int m, int j){
        int s = 0; int msi = 0; int imsi = m;

        for(int k = m-1; k>=i; k--){
            s+=v[k];
            if(s > msi){
                msi = s;
                imsi = k;
            }
        }

        s = 0; int msd = 0; int imsd = m;

        for(int k = m+1; k<=j; k++){
            s+=v[k];
            if(s > msd){
                msd = s;
                imsd = k;
            }
        }
        return new Res(imsi, imsd, msi+msd+v[m]);

    }

}//end class SubvectorSM