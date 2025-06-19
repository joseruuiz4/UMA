/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/
package org.mps;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.mps.crossover.CrossoverOperator;
import org.mps.crossover.TwoPointCrossover;
import org.mps.mutation.GaussianMutation;
import org.mps.mutation.MutationOperator;
import org.mps.selection.TournamentSelection;

public class EvolutionaryAlgorithmTest {

    @Test
    @DisplayName("Lanza excepcion si el operador de seleccion es nulo")
    public void lanzaExcepcionCuandoOperadorSeleccionEsNulo() {
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(
                null,
                new GaussianMutation(0.5, 1),
                new TwoPointCrossover()
        ));
    }

    @Test
    @DisplayName("Lanza excepcion si el operador de cruce es nulo")
    public void lanzaExcepcionCuandoOperadorCruceEsNulo() {
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(
                new TournamentSelection(2),
                new GaussianMutation(0.5, 1),
                null
        ));
    }

    @Test
    @DisplayName("Lanza excepcion si el operador de mutacion es nulo")
    public void lanzaExcepcionCuandoOperadorMutacionEsNulo() {
        assertThrows(EvolutionaryAlgorithmException.class, () -> new EvolutionaryAlgorithm(
                new TournamentSelection(2),
                null,
                new TwoPointCrossover()
        ));
    }

    @Test
    @DisplayName("Lanza excepcion si la poblacion es nula")
    public void lanzaExcepcionCuandoPoblacionEsNula() throws EvolutionaryAlgorithmException {
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(2),
                new GaussianMutation(0.3, 1),
                new TwoPointCrossover()
        );
        assertThrows(EvolutionaryAlgorithmException.class, () -> algoritmo.optimize(null));
    }

    @Test
    @DisplayName("Lanza excepcion si la poblacion esta vacia")
    public void lanzaExcepcionCuandoPoblacionEstaVacia() throws EvolutionaryAlgorithmException {
        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(2),
                new GaussianMutation(0.3, 1),
                new TwoPointCrossover()
        );
        int[][] poblacion = {};
        assertThrows(EvolutionaryAlgorithmException.class, () -> algoritmo.optimize(poblacion));
    }

    @Test
    @DisplayName("Devuelve nueva poblacion cuando los descendientes son mejores")
    public void devuelvePoblacionOptimizadaCuandoDescendientesSonMejores() throws EvolutionaryAlgorithmException {
        int[][] poblacion = {
                {12, 12},
                {18, 18},
                {25, 25},
                {30, 30}
        };

        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(1),
                new GaussianMutation(0.9, 2),
                new TwoPointCrossover()
        );

        int[][] resultado = algoritmo.optimize(poblacion);
        assertNotNull(resultado);
        assertEquals(poblacion.length, resultado.length);
    }
    @Test
    @DisplayName("Devuelve nueva poblacion cuando los descendientes son mejores")
    public void devuelveException() throws EvolutionaryAlgorithmException {
        int[][] poblacion = {
                {},
                {}
        };

        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(1),
                new GaussianMutation(0.9, 2),
                new TwoPointCrossover()
        );

        assertThrows(EvolutionaryAlgorithmException.class, () -> algoritmo.optimize(poblacion));
    }

    @Test
    @DisplayName("Mantiene la poblacion si los descendientes no son mejores")
    public void mantienePoblacionOriginalSiDescendientesNoMejoran() throws EvolutionaryAlgorithmException {
        int[][] poblacion = {
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4}
        };

        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(1),
                new GaussianMutation(0.1, 0.1),
                new TwoPointCrossover()
        );

        int[][] resultado = algoritmo.optimize(poblacion);
        assertNotNull(resultado);
        assertEquals(poblacion.length, resultado.length);
    }

    @Test
    @DisplayName("Lanza excepcion si el tamaño de la poblacion es impar")
    public void lanzaExcepcionSiPoblacionTieneTamanoImpar() throws EvolutionaryAlgorithmException {
        int[][] poblacion = {
                {3, 3},
                {6, 6},
                {9, 9}
        };

        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(2),
                new GaussianMutation(0.5, 1),
                new TwoPointCrossover()
        );

        assertThrows(EvolutionaryAlgorithmException.class, () -> algoritmo.optimize(poblacion));
    }


    @Test
    @DisplayName("Lanza excepcion si los descendientes tienen diferente tamaño que la poblacion original")
    public void lanzaExcepcionSiDescendientesTienenDiferenteTamano() throws EvolutionaryAlgorithmException {
        int[][] poblacion = {
                {1, 2, 3},
                {4, 5}
        };

        EvolutionaryAlgorithm algoritmo = new EvolutionaryAlgorithm(
                new TournamentSelection(1),
                new GaussianMutation(0.2, 1),
                new TwoPointCrossover()
        );

        assertThrows(EvolutionaryAlgorithmException.class, () -> algoritmo.optimize(poblacion));
    }


    //Nunca se puede dar el caso que en el metodo better entre population siendo null








}
