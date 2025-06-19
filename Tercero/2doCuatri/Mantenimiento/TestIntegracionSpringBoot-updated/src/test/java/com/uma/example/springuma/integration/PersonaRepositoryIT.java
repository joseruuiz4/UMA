package com.uma.example.springuma.integration;

import com.uma.example.springuma.model.Persona;
import com.uma.example.springuma.model.RepositoryPersona;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonaRepositoryIT {

    @Autowired
    private RepositoryPersona personaRepository;

    @Test
    @DisplayName("Check persona is persited in the BBDD when created")
    void givenPersonaEntity_whenSaveUser_thenUserIsPersisted() {
        // arrange
        Persona persona = new Persona();
        persona.setNombre("Alumno"); 
        persona.setDni("122");
        persona.setEdad(16);
        persona.setId(1);

        // act
        personaRepository.save(persona);

        // assert
        Optional<Persona> retrievedPersona = personaRepository.findById(1L);
        assertTrue(retrievedPersona.isPresent());
        assertEquals("Alumno", retrievedPersona.get().getNombre());
    }
}