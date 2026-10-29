package org.mps.netflix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FavoritosTest {


    @Test
    public void constructor(){
        Favoritos favoritos = new Favoritos();
        assertNotNull(favoritos);

    }

    @Test
    public void nuevoPrograma_programaNULL_RuntimeException(){
        Favoritos favoritos = new Favoritos();
        Programa programa = null;

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(()-> favoritos.nuevoPrograma(programa));
    }

    @Test
    public void nuevoPrograma_NuevoProgramaValido_AnyadePrograma(){
        Favoritos favoritos = new Favoritos();
        String expected = "Campeones";
        Programa programa = mock(Programa.class);

        when(programa.getId()).thenReturn(5);
        when(programa.getTitulo()).thenReturn(expected);

        favoritos.nuevoPrograma(programa);

        String actual = favoritos.verPrograma(5).getTitulo();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void nuevoPrograma_ProgramaValidoRepetido_ActualizaPrograma(){
        Favoritos favoritos = new Favoritos();
        String primerTitulo = "Campeones";
        String expected = "Black Mirror";
        Programa programa = mock(Programa.class);

        when(programa.getId()).thenReturn(5);
        when(programa.getTitulo()).thenReturn(primerTitulo);

        favoritos.nuevoPrograma(programa);

        when(programa.getTitulo()).thenReturn(expected);

        favoritos.nuevoPrograma(programa);

        String actual = favoritos.verPrograma(5).getTitulo();

        assertThat(actual).isEqualTo(expected);
    }

}
