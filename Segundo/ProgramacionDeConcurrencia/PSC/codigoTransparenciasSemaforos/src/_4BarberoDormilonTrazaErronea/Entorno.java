package _4BarberoDormilonTrazaErronea;

public class Entorno extends Thread{
    private Barberia b;
    
    public Entorno(Barberia b){
        this.b = b;
    }
    
    public void run(){
       while (true){
           try {
        	//El entorno est� continuamente dando paso a nuevos clientes
            //La barber�a tiene capacidad para todos los clientes, nunca se llena 
			b.nuevoCliente();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
    }
}