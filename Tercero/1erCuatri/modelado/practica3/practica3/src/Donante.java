import java.time.LocalDate;

public class Donante extends Socio {
    private List<Donacion> donaciones;

    public Donante(LocalDate registro, Refugio refugio, Donacion d) {

        donaciones = new ArrayList<>();
        donaciones.add(d);
    }
}
