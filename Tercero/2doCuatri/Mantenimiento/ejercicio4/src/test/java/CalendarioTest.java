import es.uma.informatica.mps.Calendario;
import org.junit.jupiter.api.*;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarioTest {

    @Test
    @Tag("1")@Tag("7")@Tag("13")@Tag("18")
    @DisplayName("Dia entre 1 y 4 en octubre de 1582")
    public void diaEntre1y4EnOctubre1582() {
        int dia = 4;
        int mes = 10;
        int anyo = 1582;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }


    @Test
    @Tag("1")@Tag("7")@Tag("14")@Tag("18")
    @DisplayName("Dia entre 15 y 31 en octubre de 1582")
    public void diaEntre15y31EnOctubre1582() {
        int dia = 18;
        int mes = 10;
        int anyo = 1582;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }


    @Test
    @Tag("4")@Tag("10")@Tag("18")
    @DisplayName("Dia entre 1 y 30 en un mes con 30 días en el anyo 4")
    public void diaVálidoMesCon30DiasAnyo4() {
        int dia = 22;
        int mes = 9;
        int anyo = 4;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("20")@Tag("1")@Tag("18")
    @DisplayName("Día entre 1 y 28 en febrero en un anyo bisiesto anterior al 1582")
    public void diaDeFebreroAnyoBisiestoAnteriorA1582() {
        int dia = 22;
        int mes = 2;
        int anyo = 216;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("23")@Tag("1")@Tag("18")
    @DisplayName("Dia entre 1 y 28 en febrero de un anyo NO bisiesto")
    public void diaDeFebreroAnyoNoBisiesto() {
        int dia = 28;
        int mes = 2;
        int anyo = 218;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("26")@Tag("1")@Tag("18")
    @DisplayName("Dia entre 1 y 28 de febrero en anyo bisiesto centenario, múltiplo de 400 y posterior a 1582")
    public void diaDeFebreroAnyoBisiestoCentenarioMultiplo400PosteriorA1582() {
        int dia = 23;
        int mes = 2;
        int anyo = 1600;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("29")@Tag("1")@Tag("18")
    @DisplayName("Dia entre 1 y 28 de febrero en anyo bisiesto NO centenario y posterior a 1582")
    public void diaDeFebreroAnyoBisiestoNoCentenarioPosteriorA1582() {
        int dia = 26;
        int mes = 2;
        int anyo = 1616;

        assertDoesNotThrow(() -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("2")
    @DisplayName("Numero de mes menor a 1")
    public void numeroDelMesMenorA1() {
        int dia = 8;
        int mes = 0;
        int anyo = 2025;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("3")
    @DisplayName("Numero de mes mayor a 12")
    public void numeroDelMesMayorA12() {
        int dia = 8;
        int mes = 16;
        int anyo = 2025;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("5")
    @DisplayName("Numero de mes menor a 3 en el anyo 4")
    public void numeroDelMesMenorA3EnAnyo4() {
        int dia = 8;
        int mes = 1;
        int anyo = 4;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("6")
    @DisplayName("Numero de mes mayor a 12 en el anyo 4")
    public void numeroDelMesMayorA12EnAnyo4() {
        int dia = 8;
        int mes = 15;
        int anyo = 4;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("8")
    @DisplayName("Dia del mes menor a 1 en un mes con 31 dias")
    public void diaDelMesMenorA1EnMesCon31dias() {
        int dia = 0;
        int mes = 5;
        int anyo = 2024;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("9")
    @DisplayName("Dia del mes mayor a 31 en un mes con 31 dias")
    public void diaDelMesMayorA31EnMesCon31dias() {
        int dia = 56;
        int mes = 5;
        int anyo = 2024;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("11")
    @DisplayName("Dia del mes menor a 1 en un mes con 30 dias")
    public void diaDelMesMenorA1EnMesCon30dias() {
        int dia = -7;
        int mes = 6;
        int anyo = 2024;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("12")
    @DisplayName("Dia del mes mayor a 30 en un mes con 30 dias")
    public void diaDelMesMayorA30EnMesCon30dias() {
        int dia = 56;
        int mes = 6;
        int anyo = 2024;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("15")
    @DisplayName("Dia del mes menor a 1 en octubre de 1582")
    public void diaDelMesMenorA1DeOctubreDe1582() {
        int dia = -7;
        int mes = 10;
        int anyo = 1582;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("16")
    @DisplayName("Dia del mes entre el 5 y 14 en octubre de 1582")
    public void diaDelMesEntre5y14DeOctubreDe1582() {
        int dia = 8;
        int mes = 10;
        int anyo = 1582;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("17")
    @DisplayName("Dia del mes mayor a 31 en octubre de 1582")
    public void diaDelMesMayorA31DeOctubreDe1582() {
        int dia = 56;
        int mes = 10;
        int anyo = 1582;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("19")
    @DisplayName("Numero del año menor a 4")
    public void anyoMenorA4() {
        int dia = 6;
        int mes = 10;
        int anyo = 3;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("21")
    @DisplayName("Dia del mes menor a 4 en febrero de un anyo bisiesto anterior al 1582")
    public void diaDelMesDeFebreroMenorA1EnAnyoBisiestoAnteriorA1582() {
        int dia = -6;
        int mes = 2;
        int anyo = 1200;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("22")
    @DisplayName("Dia del mes mayor a 29 en febrero de un anyo bisiesto anterior al 1582")
    public void diaDelMesDeFebreroMayorA29EnAnyoBisiestoAnteriorA1582() {
        int dia = 35;
        int mes = 2;
        int anyo = 1200;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("24")
    @DisplayName("Dia del mes menor a 1 en febrero de un anyo NO bisiesto")
    public void diaDelMesDeFebreroMenorA1EnAnyoNOBisiesto() {
        int dia = -7;
        int mes = 2;
        int anyo = 2001;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("25")
    @DisplayName("Dia del mes mayor a 28 en febrero de un anyo NO bisiesto")
    public void diaDelMesDeFebreroMayorA28EnAnyoNOBisiesto() {
        int dia = 37;
        int mes = 2;
        int anyo = 2001;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("27")
    @DisplayName("Dia del mes menor a 1 en febrero de un anyo bisiesto, centenario, multiplo de 400 y anterior al 1582")
    public void diaDelMesDeFebreroMenorA1EnAnyoBisiestoCentenarioMultiploDe400DepuesDe1582() {
        int dia = -7;
        int mes = 2;
        int anyo = 1600;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("29")
    @DisplayName("Dia del mes mayor a 29 en febrero de un anyo bisiesto, centenario, multiplo de 400 y anterior al 1582")
    public void diaDelMesDeFebreroMayorA29EnAnyoBisiestoCentenarioMultiploDe400DepuesDe1582() {
        int dia = 37;
        int mes = 2;
        int anyo = 1600;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("30")
    @DisplayName("Dia del mes menor a 1 en febrero de un anyo bisiesto, NO centenario y anterior al 1582")
    public void diaDelMesDeFebreroMenorA1EnAnyoBisiestoNOCentenarioDepuesDe1582() {
        int dia = -8;
        int mes = 2;
        int anyo = 2012;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

    @Test
    @Tag("31")
    @DisplayName("Dia del mes mayor a 29 en febrero de un anyo bisiesto, NO centenario y anterior al 1582")
    public void diaDelMesDeFebreroMayorA29EnAnyoBisiestoNOCentenarioDepuesDe1582() {
        int dia = 37;
        int mes = 2;
        int anyo = 2012;

        assertThrows(IllegalArgumentException.class, () -> Calendario.diaSemana(dia, mes, anyo));
    }

}
