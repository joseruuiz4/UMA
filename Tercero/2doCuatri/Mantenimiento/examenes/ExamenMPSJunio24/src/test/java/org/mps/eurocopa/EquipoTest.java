package org.mps.eurocopa;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class EquipoTest {
    
    @Test
    public void Equipo_ConstructorTest_Correcto(){
        Equipo equipo = new Equipo("España");
        assertNotNull(equipo);
    }

    @Test
    public void addPunto_PuntosNegativos_EurocopaException(){
        Equipo equipo = new Equipo("España");
        int puntos = -1;
        assertThrows(EurocopaException.class, () -> equipo.addPuntos(puntos));
    }

    @Test
    public void addPunto_PuntosGanar_Correcto(){
        Equipo equipo = new Equipo("España");
        int puntos = 3;
        equipo.addPuntos(puntos);
    }

    @Test
    public void addPunto_PuntosEmpate_Correcto(){
        Equipo equipo = new Equipo("España");
        int puntos = 1;
        equipo.addPuntos(puntos);
    }

    @Test
    public void getPuntos_Correcto(){
        Equipo equipo = new Equipo("España");
        int puntos = 1;
        equipo.addPuntos(puntos);

        int actual = equipo.getPuntos();

        assertEquals(actual, puntos);
    }



}
