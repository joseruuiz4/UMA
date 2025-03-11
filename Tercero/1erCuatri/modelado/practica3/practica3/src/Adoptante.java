import java.time.LocalDate;

public class Adoptante extends Socio {
    public Adoptante(LocalDate registro, Refugio refugio) {
        super(registro, refugio);
    }

    public void adoptar(Animal a, Voluntario v){
        if (a.getEstado() == EstadoAnimal.DISPONIBLE) {
            v.tramitarAdopcion(a, this);
        } else {
            System.out.println("El animal no está disponible para adopción.");
        }
    }
}
