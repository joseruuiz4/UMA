/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class GrupoTest {
    private Grupo g;


    @BeforeEach
    public void setUp() throws ClubException {
        g = new Grupo("1111", "Futbol", 18, 15, 25.0);
    }

    @Test
    public void GrupoConPlazasNegativa(){
        String codigo ="2222";
        String nombre = "Padel";
        int nplazas = -2;
        int matriculados = 1;
        double tarifa = 10.0;
        assertThrows(ClubException.class, () -> new Grupo(codigo, nombre, nplazas, matriculados, tarifa));
    }

    @Test
    public void GrupoCon0Plazas(){
        String codigo ="2222";
        String nombre = "Padel";
        int nplazas = 0;
        int matriculados = 1;
        double tarifa = 10.0;
        assertThrows(ClubException.class, () -> new Grupo(codigo, nombre, nplazas, matriculados, tarifa));

    }

    @Test
    public void GrupoConMatriculadosNegativo(){
        String codigo ="2222";
        String nombre = "Padel";
        int nplazas = 12;
        int matriculados = -5;
        double tarifa = 10.0;
        assertThrows(ClubException.class, () -> new Grupo(codigo, nombre, nplazas, matriculados, tarifa));
    }


    @Test
    public void GrupoConTarifaNegativa(){
        String codigo ="2222";
        String nombre = "Padel";
        int nplazas = 12;
        int matriculados = 10;
        double tarifa = -10.0;
        assertThrows(ClubException.class, () -> new Grupo(codigo, nombre, nplazas, matriculados, tarifa));
    }

    @Test
    public void GrupoCon0Tarifa(){
        String codigo ="2222";
        String nombre = "Padel";
        int nplazas = 12;
        int matriculados = 10;
        double tarifa = 0;
        assertThrows(ClubException.class, () -> new Grupo(codigo, nombre, nplazas, matriculados, tarifa));

    }

    @Test
    public void GrupoConMasMatriculadosQuePlazas(){
        assertThrows(ClubException.class, () -> new Grupo("2222", "Padel", 12, 15, 20.0));
    }

    @Test
    public void CrearGrupoBien() throws ClubException {
        String codigo = "1111";
        String actividad = "Futbol";
        int nplazas = 20;
        int matriculados = 18;
        double tarifa = 25.0;
        g = new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        assertNotNull(g);
        assertEquals(codigo, g.getCodigo());
        assertEquals(actividad, g.getActividad());
        assertEquals(nplazas, g.getPlazas());
        assertEquals(matriculados, g.getMatriculados());
        assertEquals(tarifa, g.getTarifa());


    }

    @Test
    public void getCodigoTest() throws ClubException {
        String expected = "1111";
        Grupo g1 = new Grupo(expected, "Padel", 12, 10, 15.0);
        String actual = g1.getCodigo();
        assertEquals(expected, actual);
    }

    @Test
    public void getActividadTest() throws ClubException {
        String expected = "Padel";
        Grupo g1 = new Grupo("1111", expected, 12, 10, 15.0);

        String actual = g1.getActividad();

        assertEquals(expected, actual);
    }

    @Test
    public void getPlazasTest() throws ClubException {
        int expected = 12;
        Grupo g1 = new Grupo("1111", "Padel", expected, 10, 15.0);
        int actual = g1.getPlazas();
        assertEquals(expected, actual);
    }

    @Test
    public void getMatriculadosTest() throws ClubException {
        int expected = 10;
        Grupo g1 = new Grupo("1111", "Padel", 12, expected, 15.0);
        int actual = g1.getMatriculados();
        assertEquals(expected, actual);
    }

    @Test
    public void getTarifaTest() throws ClubException {
        double expected = 15.0;
        Grupo g1 = new Grupo("1111", "Padel", 12, 10, expected);
        double actual = g1.getTarifa();
        assertEquals(expected, actual);
    }

    @Test
    public void getPlazasLibresTest() throws ClubException {
        int plazas = 18;
        int matriculados = 15;
        Grupo g1 = new Grupo("1111", "Padel", plazas, matriculados, 15.0);
        int expected = plazas - matriculados;
        int actual = g1.getPlazas() - g1.getMatriculados();
        assertEquals(expected, actual);
    }

    @Test
    public void ActualizarPlazasNumeroNegativo(){
        int n = -15;
        assertThrows(ClubException.class, () -> g.actualizarPlazas(n));
    }

    @Test
    public void ActualizarPlazas0(){
        int n = 0;
        assertThrows(ClubException.class, () -> g.actualizarPlazas(n));
    }


    @Test
    public void ActualizarPlazasNumeroMenorAMatriculados(){
        int n = 12;
        assertTrue(n < g.getMatriculados());
        assertThrows(ClubException.class, () -> g.actualizarPlazas(n));
    }

    @Test
    public void ActualizarPlazasCorrecto() throws ClubException {
        int expected = 16;
        g.actualizarPlazas(expected);
        int actual = g.getPlazas();
        assertEquals(expected, actual);

    }


    @Test
    public void MatricularNumeroNegativo() throws ClubException {
        int n = -5;
        assertThrows(ClubException.class, () -> g.matricular(n));
    }


    @Test
    public void Matricular0Personas() throws ClubException {
        int n = 0;
        assertThrows(ClubException.class, () -> g.matricular(n));
    }


    @Test
    public void MatricularNumeroMayorAPlazas() throws ClubException {
        int n = 10;
        assertThrows(ClubException.class, () -> g.matricular(n));
    }

    @Test
    public void MatricularCorrecto() throws ClubException {
        int n = 2;
        int expected = g.getMatriculados() + n;
        g.matricular(n);

        int actual = g.getMatriculados();

        assertEquals(expected, actual);
    }

    @Test
    public void toStringTest() {
        String expected = "(1111 - Futbol - 25.0 euros - P:18 - M:15)";
        String actual = g.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void EqualsDosObjetosDistintos() throws ClubException {
        ClubDeportivo Club = new ClubDeportivo("Club 1");
        assertFalse(g.equals(Club));
    }

    @Test
    public void EqualsMismoObjeto(){
        assertTrue(g.equals(g));
    }

    @Test
    public void EqualsDosGruposDistintosIguales() throws ClubException {
        Grupo g1 = new Grupo("1111", "Futbol", 18, 15, 25.0);
        assertTrue(g.equals(g1));
    }

    @Test
    public void EqualsDosGruposDistintosMismoNombre() throws ClubException {
        Grupo g1 = new Grupo("1111", "Baloncesto", 20, 17, 30.0);
        assertFalse(g.equals(g1));
    }

    @Test
    public void EqualsDosGruposDistintosMismaActividad() throws ClubException {
        Grupo g1 = new Grupo("2222", "Futbol", 18, 18, 27.5);
        assertFalse(g.equals(g1));
    }

    @Test
    public void EqualsDosGruposDistintosCompletamente() throws ClubException {
        Grupo g1 = new Grupo("2222", "Padel", 12, 8, 20.0);
        assertFalse(g.equals(g1));
    }

    @Test
    public void HashCodeTest() throws ClubException {
        Grupo g1 = new Grupo("2222", "Padel", 12, 8, 20.0);
        int hashg1 = g1.hashCode();
        int hashg = g.hashCode();
        assertNotEquals(hashg, hashg1);
    }


}
