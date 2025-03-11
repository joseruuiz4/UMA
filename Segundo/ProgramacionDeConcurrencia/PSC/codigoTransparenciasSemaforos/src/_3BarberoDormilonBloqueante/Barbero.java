package _3BarberoDormilonBloqueante;

public class Barbero extends Thread{
    private Barberia b;
    
    public Barbero(Barberia b){
        this.b = b;
    }
    
    public void run(){
       while (true){
           try {
        	//El barbero est� continuamente pelando
        	//pero si al intentar pelar no tiene clientes se duerme
			b.pelar();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
       }
    }
}
