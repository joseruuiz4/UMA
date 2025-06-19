package com.uma.example.springuma.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uma.example.springuma.model.Persona;
import com.uma.example.springuma.model.PersonaService;
import com.uma.example.springuma.model.RepositoryPersona;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @InjectMocks
    private PersonaService personaService;
    
    @Mock
    private RepositoryPersona personaRepository;

    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona();
    }

    @ParameterizedTest
    @ValueSource(strings = {"cristian", "francisco"})
    @DisplayName("Happy Path Test: save persona")
    void givenCorrectPersonaDTO_whenSavePersona_thenReturnPersonaDTO(String name) {
        
        // arrange
        persona = new Persona();
        persona.setNombre(name); 
        persona.setDni("122");
        persona.setEdad(16);
        persona.setId(1);

        doReturn(persona).when(personaRepository).saveAndFlush(any());

        // act
        Persona savePersona = personaService.addPersona(persona);

        // assert
        verify(personaRepository, times(1)).saveAndFlush(any());
        assertEquals(name, savePersona.getNombre());
    }

}