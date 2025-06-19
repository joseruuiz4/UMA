/*
    Carlos Rodriguez Martin
    Jose Ruiz Pareja
    Grupo Y
*/
package org.mps.selection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mps.EvolutionaryAlgorithmException;


import static org.junit.jupiter.api.Assertions.*;

public class TournamentSelectionTest {

    @Test
    @DisplayName("Tournament con tamanao menor a 0")
    public void tournamentSelection_tamanoMenor0_throwEvolutionaryAlgorithmException() {
        int tamano = -1;

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            new TournamentSelection(tamano);
        });
    }

    @Test
    @DisplayName("Tournament con tamanao mayor a 0")
    public void tournamentSelection_tamanoMayor0_True() throws EvolutionaryAlgorithmException {
        int tamano = 1;

        TournamentSelection t = new TournamentSelection(tamano);

        assertNotNull(t);
    }

    @Test
    @DisplayName("Population es Null")
    public void select_PopulationNull_ThrowEvolutionaryAlgorithmException() throws EvolutionaryAlgorithmException {
        int tamano = 1;
        int [] population = null;

        TournamentSelection t = new TournamentSelection(tamano);

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            t.select(population);
        });
    }

    @Test
    @DisplayName("Tamano  de Population Menor que Tamano")
    public void select_TamanoPopulationMenorQueTamano_ThrowEvolutionaryAlgorithmException() throws EvolutionaryAlgorithmException {
        int tamano = 10;
        int [] population = {1,2,3};

        TournamentSelection t = new TournamentSelection(tamano);

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            t.select(population);
        });
    }

    @Test
    @DisplayName("Tamano de Population Menor que 1")
    public void select_TamanoPopulationMenorQue1_ThrowEvolutionaryAlgorithmException() throws EvolutionaryAlgorithmException {
        int tamano = 1;
        int [] population = {};

        TournamentSelection t = new TournamentSelection(tamano);

        assertThrows(EvolutionaryAlgorithmException.class, () -> {
            t.select(population);
        });
    }

    @Test
    @DisplayName("Correcto")
    public void select_Correcto_Return() throws EvolutionaryAlgorithmException {
        int tamano = 5;
        int [] population = {5,4,3,2,1,6};

        TournamentSelection t = new TournamentSelection(tamano);
        int[] actual = t.select(population);

        assertEquals(population.length, actual.length);
    }


}

