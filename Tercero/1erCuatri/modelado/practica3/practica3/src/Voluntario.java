import java.time.*;
public class Voluntario extends Socio {

    public Voluntario(LocalDate registro, Refugio refugio) {
        super(registro, refugio);
    }

    public void tramitarAdopcion(Animal a, Adoptante ad) {
        Adopcion adopcion = new Adopcion(a, ad, LocalDate.now(), this);
    }

    public void registrar(Animal a) {
        a.setEstado(EstadoAnimal.DISPONIBLE);
        a.setRefugio(this.getRefugio());
        this.getRefugio().registrar(a);
    }

}
