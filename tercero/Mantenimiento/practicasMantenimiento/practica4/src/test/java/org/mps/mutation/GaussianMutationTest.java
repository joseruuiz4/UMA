/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/
package org.mps.mutation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

public class GaussianMutationTest {

    @Nested
    @DisplayName("GaussianMutation Constructor")
    class GaussianMutationConstructor {
        @Test
        @DisplayName("Constructor sin pasarle parametros funciona CORRECTO")
        public void GaussianMutation_ConstructorVacio_CORRECTO(){
            GaussianMutation gaussianMutation;

            gaussianMutation = new GaussianMutation();

            assertNotNull(gaussianMutation);
        }

        @Test
        @DisplayName("Constructor pasandole mutationRate y standardDeviation funciona CORRECTO")
        public void GaussianMutation_ConstructorConMutationRateyStandardDeviation_CORRECTO(){
            GaussianMutation gaussianMutation;
            double mr = 1.2;
            double sd = 1.4;

            gaussianMutation = new GaussianMutation(mr, sd);

            assertNotNull(gaussianMutation);
        }
    }

    @Nested
    @DisplayName("Mutation")
    class Mutation {
        @Test
        @DisplayName("Pasamos un individual NULL, lanza excepcion")
        public void mutate_mutacionNULL_EvolutionaryAlgorithmException(){
            GaussianMutation gaussianMutation = new GaussianMutation();
            int[] ind = null;

            assertThrows(EvolutionaryAlgorithmException.class, () -> gaussianMutation.mutate(ind));
        }

        @Test
        @DisplayName("Pasamos un individual con longitud 0, lanza excepcion")
        public void mutate_mutacionLongitud0_EvolutionaryAlgorithmException(){
            GaussianMutation gaussianMutation = new GaussianMutation();
            int[] ind = {};

            assertThrows(EvolutionaryAlgorithmException.class, () -> gaussianMutation.mutate(ind));
        }

        @Test
        @DisplayName("Pasamos un individual correcto y realiza correctamente la mutacion")
        public void mutate_mutacionCORRECTA() throws EvolutionaryAlgorithmException {
            GaussianMutation gaussianMutation = new GaussianMutation(1.5, 1.5);
            int[] ind = {1,5,10,15};

            int[] mutado = gaussianMutation.mutate(ind);
            boolean isMutated = false;

            for(int i = 0; i < mutado.length; i++){
                if(mutado[i] == ind[i]){
                    isMutated = true;
                }
            }

            assertTrue(isMutated);

        }

        @Test
        @DisplayName("Si mutationRate es 0, no varia individual")
        public void mutate_mutationRate0_noVariaInicial() throws EvolutionaryAlgorithmException {
            GaussianMutation gaussianMutation = new GaussianMutation(0.0, 1.5);
            int[] ind = {1,5,10,15};

            int[] mutado = gaussianMutation.mutate(ind);


            assertArrayEquals(ind, mutado);
        }
    }
}
