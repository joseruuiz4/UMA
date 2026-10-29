package org.mps.eurocopa;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

public class EurocopaTest {
    @Test
    public void Eurocopa_ConstructorTest_Correcto(){
        Eurocopa eurocopa = new Eurocopa();
        assertThat(eurocopa).isNotNull();
    }

    @Test
    public void anyadirGrupo_FormatoErroneo_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "EsteGrupoNoVale";

        assertThatExceptionOfType(EurocopaException.class).isThrownBy(() -> eurocopa.anyadirGrupo(Grupo));
    }

    @Test
    public void anyadirGrupo_NombreGrupoEmpty_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = ":España,Suiza,Alemania,Eslovenia";

        assertThatExceptionOfType(EurocopaException.class).isThrownBy(() -> eurocopa.anyadirGrupo(Grupo));
    }

    @Test
    public void anyadirGrupo_GrupoRepetido_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Suiza,Alemania,Eslovenia";
        eurocopa.anyadirGrupo(Grupo);

        assertThatExceptionOfType(EurocopaException.class).isThrownBy(() -> eurocopa.anyadirGrupo(Grupo));
    }

    @Test
    public void anyadirGrupo_GrupoSinEquipos_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:";

        assertThatExceptionOfType(EurocopaException.class).isThrownBy(() -> eurocopa.anyadirGrupo(Grupo));
    }

    @Test
    public void anyadirGrupo_GrupoEquiposRepetidos_NoSalenRepetidos(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,España,España";

        eurocopa.anyadirGrupo(Grupo);

        String actual = eurocopa.toString();
        String expected = "GrupoA:España(0)";

        assertEquals(expected, actual);
    }


    @Test
    public void anyadirResultado_GrupoDelResultadoNull_NullPointerException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        eurocopa.anyadirGrupo(Grupo);

        Resultado resultadoMock = mock(Resultado.class);
        when(resultadoMock.getGrupo()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> eurocopa.anyadirResultado(resultadoMock));
    }

    @Test
    public void anyadirResultado_GrupoNoExiste_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        eurocopa.anyadirGrupo(Grupo);

        Resultado resultadoMock = mock(Resultado.class);
        when(resultadoMock.getGrupo()).thenReturn("GrupoB");

        assertThrows(EurocopaException.class, () -> eurocopa.anyadirResultado(resultadoMock));
    }



    @Test
    public void anyadirResultado_GanaLocal_PuntosAnyadidosCorrecto(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        String nombreGrupo = "GrupoA";
        Equipo equipoLocal = new Equipo("España");
        Equipo equipoVisitante = new Equipo("Francia");
        String expected = "GrupoA:Alemania(0)España(3)Francia(0)";

        eurocopa.anyadirGrupo(Grupo);


        Resultado resultadoMock = mock(Resultado.class);
        when(resultadoMock.getGrupo()).thenReturn(nombreGrupo);
        when(resultadoMock.getLocal()).thenReturn(equipoLocal);
        when(resultadoMock.getVisitante()).thenReturn(equipoVisitante);
        when(resultadoMock.empate()).thenReturn(false);
        when(resultadoMock.ganaLocal()).thenReturn(true);

        eurocopa.anyadirResultado(resultadoMock);

        String actual = eurocopa.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void anyadirResultado_GanaVisitante_PuntosAnyadidosCorrecto(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        String nombreGrupo = "GrupoA";
        Equipo equipoLocal = new Equipo("Francia");
        Equipo equipoVisitante = new Equipo("España");
        String expected = "GrupoA:Alemania(0)España(3)Francia(0)";

        eurocopa.anyadirGrupo(Grupo);


        Resultado resultadoMock = mock(Resultado.class);
        when(resultadoMock.getGrupo()).thenReturn(nombreGrupo);
        when(resultadoMock.getLocal()).thenReturn(equipoLocal);
        when(resultadoMock.getVisitante()).thenReturn(equipoVisitante);
        when(resultadoMock.empate()).thenReturn(false);
        when(resultadoMock.ganaLocal()).thenReturn(false);

        eurocopa.anyadirResultado(resultadoMock);

        String actual = eurocopa.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void anyadirResultado_Empate_PuntosAnyadidosCorrecto(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        String nombreGrupo = "GrupoA";
        Equipo equipoLocal = new Equipo("Alemania");
        Equipo equipoVisitante = new Equipo("España");
        String expected = "GrupoA:Alemania(1)España(1)Francia(0)";

        eurocopa.anyadirGrupo(Grupo);


        Resultado resultadoMock = mock(Resultado.class);
        when(resultadoMock.getGrupo()).thenReturn(nombreGrupo);
        when(resultadoMock.getLocal()).thenReturn(equipoLocal);
        when(resultadoMock.getVisitante()).thenReturn(equipoVisitante);
        when(resultadoMock.empate()).thenReturn(true);
        when(resultadoMock.ganaLocal()).thenReturn(false);

        eurocopa.anyadirResultado(resultadoMock);

        String actual = eurocopa.toString();
        assertEquals(expected, actual);
    }


    @Test
    public void getEquipo_GrupoNoEstaEnEurocopa_EurocopaException(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        eurocopa.anyadirGrupo(Grupo);
        String nombreGrupo = "GrupoB";
        Equipo equipo = new Equipo("España");

        assertThrows(EurocopaException.class, () -> eurocopa.getEquipo(nombreGrupo, equipo));
    }

    @Test
    public void getEquipo_EquipoNoEstaEnElGrupo_DevuelveNull(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        eurocopa.anyadirGrupo(Grupo);
        String nombreGrupo = "GrupoA";
        Equipo equipo = new Equipo("Suiza");

        Equipo actual = eurocopa.getEquipo(nombreGrupo, equipo);

        assertNull(actual);
    }

    @Test
    public void getEquipo_EquipoSIEstaEnElGrupo_EqualsCorrecto(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo = "GrupoA:España,Francia,Alemania";
        eurocopa.anyadirGrupo(Grupo);
        String nombreGrupo = "GrupoA";
        Equipo equipo = new Equipo("España");

        Equipo actual = eurocopa.getEquipo(nombreGrupo, equipo);

        assertEquals(equipo, actual);
    }

    @Test
    public void getNumeroEquipos_EurocopaVacia_Devuelve0(){
        Eurocopa eurocopa = new Eurocopa();
        int expected = 0;
        int actual = eurocopa.getNumeroEquipos();

        assertEquals(expected, actual);
    }

    @Test
    public void getNumeroEquipos_EurocopaConGrupos_DevuelveCorrecto(){
        Eurocopa eurocopa = new Eurocopa();
        String Grupo1 = "GrupoA:España,Francia,Alemania";
        String Grupo2 = "GrupoB:Suiza,Suecia,Andorra,Italia";
        eurocopa.anyadirGrupo(Grupo1);
        eurocopa.anyadirGrupo(Grupo2);
        int expected = 7;
        int actual = eurocopa.getNumeroEquipos();

        assertEquals(expected, actual);
    }








    
}
