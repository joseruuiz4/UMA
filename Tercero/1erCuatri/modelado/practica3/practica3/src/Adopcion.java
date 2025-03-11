import java.util.Date;
import java.time.*;

public class Adopcion {
    private Animal animal;
    private Adoptante adoptante;
    private LocalDate Fecha;
    private Voluntario voluntario;

    public Adopcion(Animal animal, Adoptante adoptante, LocalDate fecha, Voluntario voluntario) {
        this.animal = animal;
        this.adoptante = adoptante;
        this.Fecha = fecha;
        this.voluntario = voluntario;
    }

}