/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/
package org.mps.crossover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class TwoPointCrossoverTest {

    @Nested
    @DisplayName("Crossover")
    class Crossover {
        @Test
        @DisplayName("Constructor TwoPointCrossover funciona correcto")
        public void TwoPointCrossover_Correcto() {
            TwoPointCrossover twoPointCrossover;

            twoPointCrossover = new TwoPointCrossover();

            assertNotNull(twoPointCrossover);
        }

        @Test
        @DisplayName("Parent1 y parent2 son NULL, devuelve una excepcion")
        public void Crossover_Parent1yParent2NULL_EvolutionaryAlgorithmException(){
            int[] p1 = null;
            int[] p2 = null;
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

            assertThrows(EvolutionaryAlgorithmException.class, () -> twoPointCrossover.crossover(p1, p2));
        }

        @Test
        @DisplayName("Parent1 es NULL, devuelve una excepcion")
        public void Crossover_Parent1NULL_EvolutionaryAlgorithmException(){
            int[] p1 = null;
            int[] p2 = {1, 2, 3};
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

            assertThrows(EvolutionaryAlgorithmException.class, () -> twoPointCrossover.crossover(p1, p2));
        }

        @Test
        @DisplayName("Parent2 es NULL, devuelve una excepcion")
        public void Crossover_Parent2NULL_EvolutionaryAlgorithmException(){
            int[] p1 = {1, 2, 3};
            int[] p2 = null;
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

            assertThrows(EvolutionaryAlgorithmException.class, () -> twoPointCrossover.crossover(p1, p2));
        }

        @Test
        @DisplayName("Parent1 tiene longitud 0, devuelve una excepcion")
        public void Crossover_Parent1Longitud0_EvolutionaryAlgorithmException(){
            int[] p1 = {};
            int[] p2 = {1, 2, 3};
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

            assertThrows(EvolutionaryAlgorithmException.class, () -> twoPointCrossover.crossover(p1, p2));
        }

        @Test
        @DisplayName("Parent1 y parent2 tienen distinta longitud, devuelve una excepcion")
        public void Crossover_Parent1Parent2DistintaLongitud_EvolutionaryAlgorithmException(){
            int[] p1 = {1, 2, 3, 4};
            int[] p2 = {1, 2, 3};
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();

            assertThrows(EvolutionaryAlgorithmException.class, () -> twoPointCrossover.crossover(p1, p2));
        }

        @Test
        @DisplayName("Parent1 y parent2 no son NULL y tienen la misma longitud, funciona CORRECTO")
        public void Crossover_CORRECTO() throws EvolutionaryAlgorithmException {
            int[] p1 = {1, 2, 3, 4, 5};
            int[] p2 = {10, 11, 12, 13, 14};
            TwoPointCrossover twoPointCrossover = new TwoPointCrossover();


            int[][] actual = twoPointCrossover.crossover(p1, p2);

            assertNotNull(actual);
            assertNotNull(actual[0]);
            assertNotNull(actual[1]);
        }
    }


}
