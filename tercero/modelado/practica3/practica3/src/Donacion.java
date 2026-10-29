import java.time.LocalDate;

public class Donacion {
    private double cantidad;
    private LocalDate fecha;

    Donacion(double cantidad, LocalDate fecha) {
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
}
