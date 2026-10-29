import java.util.List;

public class Refugio {
    private double liquidez;
    private List<Animal> animalesRefugiados;
    private List<Animal> animalesRegistrados;
    private List<Socio> socios;

    // dependencia
    public Refugio(Animal animal) {
        this.liquidez = 0.0; // Inicializamos la liquidez en 0
        this.animalesRefugiados = new ArrayList<>();
        this.animalesRegistrados = new ArrayList<>();
        registrar(animal); // Se registra el primer animal al crear el refugio
    }

    // agregacion
    public void registrar(Animal animal) {
        if (!animalesRegistrados.contains(animal)) {
            animalesRegistrados.add(animal);
            animalesRefugiados.add(animal);
            System.out.println("Animal registrado con éxito: " + animal.getNombre());
        } else {
            System.out.println("El animal ya está registrado.");
        }
    }
}
