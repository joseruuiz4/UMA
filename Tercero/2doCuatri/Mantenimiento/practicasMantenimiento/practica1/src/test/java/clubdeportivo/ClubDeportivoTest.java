/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class ClubDeportivoTest {
    private ClubDeportivo ClubD;
    private Grupo g1;
    private Grupo g2;
    private Grupo g3;
    private Grupo g4;
    private Grupo g5;

    @BeforeEach
    public void setUp() throws ClubException {
        //Arrange
        ClubD = new ClubDeportivo("Club Deportivo 1", 4);
        g1 = new Grupo("1111", "Padel", 7, 5, 20.0);
        g2 = new Grupo("2222", "Tenis", 6, 6, 20.0);
        g3 = new Grupo("3333", "Futbol", 18, 18, 25.0);
        g4 = new Grupo("4444", "Baloncesto", 13, 11, 25.0);
        g5 = new Grupo("5555", "Padel", 10, 5, 20.0);
    }

    @Test
    public void ClubDeportivoSinTamaño() throws ClubException {
        String name = "Club Deportivo 1";
        String expected = "Club Deportivo 1 --> [  ]";
        ClubDeportivo c = new ClubDeportivo(name);
        String actual = c.toString();
        assertNotNull(c);
        assertEquals(expected, actual);
    }

    @Test
    public void ClubDeportivoConGruposNegativos() {
        int n = -1;
        String nombre = "ClubPrueba";
        assertThrows(ClubException.class,()->new ClubDeportivo(nombre, n));
    }

    @Test
    public void ClubDeportivoCon0Grupos() {
        int n = -0;
        String nombre = "ClubPrueba";
        assertThrows(ClubException.class,()->new ClubDeportivo(nombre, n));
    }

    @Test
    public void AnyadirActividadFormatoDatosErróneos() throws ClubException {
        String[] datosFormatoMalo = {"1", "4", "Club AntiDeportivo", "Ocho", "4.0"};
        assertThrows(ClubException.class, () ->ClubD.anyadirActividad(datosFormatoMalo));
    }

    @Test
    public void AnyadirActividadDatosRangoMenor() throws ClubException {
        String[] datosRangoMenor = {"Club AntiDeportivo", "Waterpolo", "10", "8"};
        assertThrows(ClubException.class, () -> ClubD.anyadirActividad(datosRangoMenor));
    }

    @Test
    public void AnyadirActividadDatosRangoMayor() throws ClubException {
        String[] datosRangoMayor = {"Club AntiDeportivo", "Waterpolo", "10", "8", "18.0", "RANGO MAYOR"};
       ClubD.anyadirActividad(datosRangoMayor); // No importa que el tamaño del String[] sea mayor al pedido
    }


    @Test
    public void AnyadirActividadNULL() throws ClubException {
        Grupo g = null;
        assertThrows(ClubException.class, () -> ClubD.anyadirActividad(g));
    }

    @Test
    public void AnyadirActividadQueNoExiste() throws ClubException {
        ClubD.anyadirActividad(g1);
        String expected = "Club Deportivo 1 --> [ (1111 - Padel - 20.0 euros - P:7 - M:5) ]";
        String actual = ClubD.toString();
        assertEquals(expected, actual);
    }


    @Test
    public void AnyadirActividadQueYaExiste() throws ClubException {
        ClubD.anyadirActividad(g1); //Anyadimos varias actividades al Club
        int plazasNuevas = 12;
        g1 = new Grupo("1111", "Padel", plazasNuevas, 5, 20.0);

        ClubD.anyadirActividad(g1); //Volvemos a anyadir la actividad de g3
        String expected = "Club Deportivo 1 --> [ (1111 - Padel - 20.0 euros - P:12 - M:5) ]";
        String actual = ClubD.toString();
        assertEquals(expected, actual);

    }

    @Test
    public void AnyadirActividadCuandoNoCabenMas() throws ClubException {
        ClubD.anyadirActividad(g1); //Anyadimos varias actividades al Club
        ClubD.anyadirActividad(g2);
        ClubD.anyadirActividad(g3);
        ClubD.anyadirActividad(g4);
        assertThrows(ClubException.class, () -> ClubD.anyadirActividad(g5));

    }

    @Test
    public void PlazasLibresActividad() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g5);
        String actividad = g1.getActividad();
        int expected = g1.getPlazas() - g1.getMatriculados() +  g5.getPlazas() - g5.getMatriculados();

        int actual = ClubD.plazasLibres(actividad);

        assertEquals(expected, actual);
    }

    @Test
    public void PlazasLibresActividadNoExiste() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g5);
        String actividad = g3.getActividad();
        int expected = 0;
        int actual = ClubD.plazasLibres(actividad);
        assertEquals(expected, actual);
    }

    @Test
    public void MatricularDeActividadInexistente() throws ClubException {
        ClubD.anyadirActividad(g1);
        String actividad = "Badminton";
        assertThrows(ClubException.class, () -> ClubD.matricular(actividad, 5));
    }

    @Test
    public void MatricularDeActividadSinPlazas() throws ClubException {
        ClubD.anyadirActividad(g1);
        String actividad = g1.getActividad();
        int npersonas = 6;
        assertThrows(ClubException.class, () -> ClubD.matricular(actividad, npersonas));
    }

    @Test
    public void MatricularDeActividadConPlazas() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g3);
        ClubD.anyadirActividad(g5);
        String actividad = g1.getActividad();
        int npersonas = 6;
        int plazasLibres1 = ClubD.plazasLibres(actividad);
        int expected = plazasLibres1 - npersonas;

        ClubD.matricular(actividad, npersonas);
        int actual = ClubD.plazasLibres(actividad);
        assertEquals(expected, actual);
    }

    @Test
    public void Matricular0PersonasDeActividadConPlazas() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g3);
        ClubD.anyadirActividad(g5);
        String actividad = g1.getActividad();
        int npersonas = 0;
        int plazasLibres1 = ClubD.plazasLibres(actividad);
        int expected = plazasLibres1 - npersonas;

        ClubD.matricular(actividad, npersonas);
        int actual = ClubD.plazasLibres(actividad);
        assertEquals(expected, actual);
    }

    @Test
    public void IngresosTest() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g3);
        ClubD.anyadirActividad(g5);
        double expected = g1.getMatriculados() * g1.getTarifa() + g3.getMatriculados() * g3.getTarifa() + g5.getMatriculados() * g5.getTarifa();
        double actual = ClubD.ingresos();
        assertEquals(expected, actual);
    }


    @Test
    public void toStringTest() throws ClubException {
        ClubD.anyadirActividad(g1);
        ClubD.anyadirActividad(g3);
        ClubD.anyadirActividad(g5);
        String expected = "Club Deportivo 1 --> [ (1111 - Padel - 20.0 euros - P:7 - M:5), (3333 - Futbol - 25.0 euros - P:18 - M:18), (5555 - Padel - 20.0 euros - P:10 - M:5) ]";
        String actual = ClubD.toString();

        assertEquals(expected, actual);

    }

}