package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoTest {
    private Grupo g;


    @BeforeEach
    void setUp() throws ClubException {
        g = new Grupo("1111", "Futbol", 18, 15, 25.0);
    }

    @Test
    void GrupoConPlazasNegativa(){
        assertThrows(ClubException.class, () -> new Grupo("2222", "Padel", -2, 1, 10.0));
    }

    @Test
    void GrupoConMatriculadosNegativo(){
        assertThrows(ClubException.class, () -> new Grupo("2222", "Padel", 12, -10, 10.0));
    }

    @Test
    void GrupoConTarifaNegativa(){
        assertThrows(ClubException.class, () -> new Grupo("2222", "Padel", 12, 10, -10.0));
    }

    @Test
    void GrupoConMasMatriculadosQuePlazas(){
        assertThrows(ClubException.class, () -> new Grupo("2222", "Padel", 12, 15, 20.0));
    }

    @Test
    void CrearGrupoBien() throws ClubException {
        String codigo = "1111";
        String actividad = "Futbol";
        int nplazas = 20;
        int matriculados = 18;
        double tarifa = 25.0;
        g = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        assertEquals(codigo, g.getCodigo());
        assertEquals(actividad, g.getActividad());
        assertEquals(nplazas, g.getPlazas());
        assertEquals(matriculados, g.getMatriculados());
        assertEquals(tarifa, g.getTarifa());


    }

    @Test
    void getCodigoTest() throws ClubException {
        String expected = "1111";
        Grupo g1 = new Grupo(expected, "Padel", 12, 10, 15.0);
        String actual = g1.getCodigo();
        assertEquals(expected, actual);
    }

    @Test
    void getActividadTest() throws ClubException {
        String expected = "Padel";
        Grupo g1 = new Grupo("1111", expected, 12, 10, 15.0);
        String actual = g1.getActividad();
        assertEquals(expected, actual);
    }

    @Test
    void getPlazasTest() throws ClubException {
        int expected = 12;
        Grupo g1 = new Grupo("1111", "Padel", expected, 10, 15.0);
        int actual = g1.getPlazas();
        assertEquals(expected, actual);
    }

    @Test
    void getMatriculadosTest() throws ClubException {
        int expected = 10;
        Grupo g1 = new Grupo("1111", "Padel", 12, expected, 15.0);
        int actual = g1.getMatriculados();
        assertEquals(expected, actual);
    }

    @Test
    void getTarifaTest() throws ClubException {
        double expected = 15.0;
        Grupo g1 = new Grupo("1111", "Padel", 12, 10, expected);
        double actual = g1.getTarifa();
        assertEquals(expected, actual);
    }

    @Test
    void getPlazasLibresTest() throws ClubException {
        int plazas = 18;
        int matriculados = 15;
        Grupo g1 = new Grupo("1111", "Padel", plazas, matriculados, 15.0);
        int expected = plazas - matriculados;
        int actual = g1.getPlazas() - g1.getMatriculados();
        assertEquals(expected, actual);
    }

    @Test
    void ActualizarPlazasNumeroNegativo(){
        int n = -15;
        assertThrows(ClubException.class, () -> g.actualizarPlazas(n));
    }

    @Test
    void ActualizarPlazasNumeroMenorAMatriculados(){
        int n = 12;
        assertTrue(n < g.getMatriculados());
        assertThrows(ClubException.class, () -> g.actualizarPlazas(n));
    }

    @Test
    void ActualizarPlazasCorrecto() throws ClubException {
        int expected = 16;
        g.actualizarPlazas(expected);
        int actual = g.getPlazas();
        assertEquals(expected, actual);

    }


    @Test
    void MatricularNumeroNegativo() throws ClubException {
        int n = -5;
        assertThrows(ClubException.class, () -> g.matricular(n));
    }

    @Test
    void MatricularNumeroMayorAPlazas() throws ClubException {
        int n = 10;
        assertThrows(ClubException.class, () -> g.matricular(n));
    }

    @Test
    void MatricularCorrecto() throws ClubException {
        int n = 2;
        int expected = g.getMatriculados() + n;
        g.matricular(n);
        int actual = g.getMatriculados();
        assertEquals(expected, actual);
    }

    @Test
    void toStringTest() {
        String expected = "(1111 - Futbol - 25.0 euros - P:18 - M:15)";
        String actual = g.toString();
        assertEquals(expected, actual);
    }

    @Test
    void EqualsDosObjetosDistintos() throws ClubException {
        ClubDeportivo Club = new ClubDeportivo("Club 1");
        assertFalse(g.equals(Club));
    }

    @Test
    void EqualsMismoObjeto(){
        assertTrue(g.equals(g));
    }

    @Test
    void EqualsDosGruposDistintosIguales() throws ClubException {
        Grupo g1 = new Grupo("1111", "Futbol", 18, 15, 25.0);
        assertTrue(g.equals(g1));
    }

    @Test
    void EqualsDosGruposDistintosMismoNombre() throws ClubException {
        Grupo g1 = new Grupo("1111", "Baloncesto", 20, 17, 30.0);
        assertFalse(g.equals(g1));
    }

    @Test
    void EqualsDosGruposDistintosMismaActividad() throws ClubException {
        Grupo g1 = new Grupo("2222", "Futbol", 18, 18, 27.5);
        assertFalse(g.equals(g1));
    }

    @Test
    void EqualsDosGruposDistintosCompletamente() throws ClubException {
        Grupo g1 = new Grupo("2222", "Padel", 12, 8, 20.0);
        assertFalse(g.equals(g1));
    }

    @Test
    void HashCodeTest() throws ClubException {
        Grupo g1 = new Grupo("2222", "Padel", 12, 8, 20.0);
        int hashg1 = g1.hashCode();
        int hashg = g.hashCode();
        assertNotEquals(hashg, hashg1);
    }


}
