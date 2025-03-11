import java.time.LocalDate;

public class Animal {
    private LocalDate nacimiento;
    private EstadoAnimal estado;
    private Refugio refugio;

    public Animal(LocalDate nacimiento, EstadoAnimal estado, Refugio refugio){
        this.nacimiento = nacimiento;
        this.estado = estado;
        this.refugio = refugio;

    }

    public EstadoAnimal getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoAnimal estado) {
        this.estado = estado;
    }

    public void setRefugio(Refugio refugio) {
        this.refugio = refugio;
    }

}
