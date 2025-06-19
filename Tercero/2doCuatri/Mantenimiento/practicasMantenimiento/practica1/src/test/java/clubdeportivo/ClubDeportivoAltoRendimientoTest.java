/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

public class ClubDeportivoAltoRendimientoTest {
    private ClubDeportivoAltoRendimiento ClubD;
    private Grupo g1;
    private Grupo g2;
    private Grupo g3;
    @Test
    public void ClubDeportivoAltoRendimiento_valoresnegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=-1;
        double incremento=-1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimiento_maximonegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=-1;
        double incremento=1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimiento_incrementonegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=-1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimiento_maximocero_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=0;
        double incremento=1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimiento_incrementocero_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=0;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimiento_valoresAceptados_NoReturnException() throws ClubException {
        String nombre= "ClubDeportivoAltoRendimiento";
        int maximo=20;
        double incremento=2;
        assertDoesNotThrow(() -> new ClubDeportivoAltoRendimiento(nombre, maximo, incremento));
    }

    //

    @Test
    public void ClubDeportivoAltoRendimientoConTam_valoresnegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=-1;
        double incremento=-1;
        int tam = -1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimientoConTam_maximonegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=-1;
        double incremento=1;
        int tam = 1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimientoConTam_incrementonegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=-1;
        int tam = 1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimientoConTam_tamnegativos_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=1;
        int tam = -1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimientoConTam_maximocero_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=0;
        double incremento=1;
        int tam = 1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimientoConTam_incrementocero_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=0;
        int tam = 1;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }
    @Test
    public void ClubDeportivoAltoRendimientoConTam_tamcero_returnClubException() throws ClubException {
        String nombre= "ClubErroneo";
        int maximo=1;
        double incremento=1;
        int tam = 0;
        assertThrows(ClubException.class,()-> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }

    @Test
    public void ClubDeportivoAltoRendimientoConTam_valoresAceptados_NoReturnException() throws ClubException {
        String nombre= "ClubDeportivoAltoRendimiento";
        int maximo=20;
        double incremento=2;
        int tam = 1;
        assertDoesNotThrow(() -> new ClubDeportivoAltoRendimiento(nombre,tam, maximo, incremento));
    }

    //anyadirActividad
    @Test
    public void AnyadirActividadDatosRangoMenor() throws ClubException {
        ClubD = new ClubDeportivoAltoRendimiento("Club Deportivo 1", 10,2);
        String[] datosRangoMenor = {"Club AntiDeportivo", "Waterpolo", "10", "8"};
        assertThrows(ClubException.class, () -> ClubD.anyadirActividad(datosRangoMenor));
    }

    @Test
    public void AnyadirActividadDatosRangoMayor() throws ClubException {
        ClubD = new ClubDeportivoAltoRendimiento("Club Deportivo 1", 10,2);
        String[] datosRangoMayor = {"Club AntiDeportivo", "Waterpolo", "10", "8", "18.0", "RANGO MAYOR"};
        assertDoesNotThrow(()-> ClubD.anyadirActividad(datosRangoMayor)); // No importa que el tamaño del String[] sea mayor al pedido

    }

    @Test
    public void AnyadirActividadDatosPlazasMasDeLasMaximas_PlazasIgualAMaximasPersonasGrupo() throws ClubException {
        String maximo = "10";
        ClubD = new ClubDeportivoAltoRendimiento("Club Deportivo 1", 10,2);
        String plazas = "15";
        String matriculados = "5";
        int plazaslibresesperadas = Integer.parseInt(maximo)-Integer.parseInt(matriculados);
        String actividad ="Waterpolo";
        String[] datosRangoMayor = {"Club AntiDeportivo", actividad, String.valueOf(plazas), matriculados, "18.0", "RANGO MAYOR"};
        ClubD.anyadirActividad(datosRangoMayor);
        assertEquals(plazaslibresesperadas,ClubD.plazasLibres(actividad)); // No importa que el tamaño del String[] sea mayor al pedido

    }

    @Test
    public void AnyadirActividadDatosPlazasMenosDeLasMaximas_PlazasIgualAPlazasMenosMatriculadosPersonasGrupo() throws ClubException {
        String maximo = "10";
        ClubD = new ClubDeportivoAltoRendimiento("Club Deportivo 1", 10,2);
        String plazas = "8";
        String matriculados = "5";
        int plazaslibresesperadas = Integer.parseInt(plazas)-Integer.parseInt(matriculados);
        String actividad ="Waterpolo";
        String[] datosRangoMayor = {"Club AntiDeportivo", actividad, String.valueOf(plazas), matriculados, "18.0", "RANGO MAYOR"};
        ClubD.anyadirActividad(datosRangoMayor);
        assertEquals(plazaslibresesperadas,ClubD.plazasLibres(actividad)); // No importa que el tamaño del String[] sea mayor al pedido

    }

    @Test
    public void AnyadirActividadDatosFormatoNumeroIncorrecto_returnClubException() throws ClubException {
        String maximo = "10";
        ClubD = new ClubDeportivoAltoRendimiento("Club Deportivo 1", 10,2);
        String plazas = "MAL";
        String matriculados = "5";
        int plazaslibresesperadas = Integer.parseInt(maximo)-Integer.parseInt(matriculados);
        String actividad ="Waterpolo";
        String[] datosConFormatoIncorrecto = {"Club AntiDeportivo", actividad, String.valueOf(plazas), matriculados, "18.0", "RANGO MAYOR"};
        assertThrows(ClubException.class,() -> ClubD.anyadirActividad(datosConFormatoIncorrecto)); // No importa que el tamaño del String[] sea mayor al pedido

    }

    @Test
    @DisplayName("Calcular ingresos con grupos")
    public void ingresos_ConGrupos_returnCorrecto() throws ClubException{
        //Arrange: Preparamos los datos a usar
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("ClubD", 10, 2.0);
        String[] datos = {"Waterpolo", "Wa", "10", "5", "10.0"};
        double expected = 51.0;
        club.anyadirActividad(datos);
        double resultado = club.ingresos();

        assertEquals(expected,resultado);
    }

    @Test
    public void ingresos_SinGrupos_returnCero() throws ClubException{

        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("Club", 10, 2.0);
        double expected = 0.0;

        double result = club.ingresos();


        assertEquals(result,expected);
    }


}
