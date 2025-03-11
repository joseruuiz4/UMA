import java.time.LocalDate;
import java.util.List;

public class Socio {
    private LocalDate registro;
    private Refugio refugio;


    public Socio(LocalDate registro, Refugio refugio) {
        this.registro = registro;
        this.refugio = refugio;
    }

    public Refugio getRefugio() {
            return this.refugio;
        }

}

