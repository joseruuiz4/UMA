package com.uma.example.springuma.unit.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uma.example.springuma.controller.PersonaController;
import com.uma.example.springuma.model.Persona;
import com.uma.example.springuma.model.PersonaService;

@ExtendWith(MockitoExtension.class)
class PersonaControllerTest {

    @InjectMocks
    private PersonaController personaController;
    
    @Mock
    private PersonaService personaService;
    
    private Persona persona;

    @BeforeEach
    void setUp() {
        persona = new Persona();
    }

    @Test
    @DisplayName("When creating a new persona the persona is created in the service and the necessary methods and invoked")
    void savePersona_WhenCalledController_PersonaServiceSavePersonaAndGet201Code()  {
        // arrange
        persona = new Persona();
        persona.setNombre("name"); 
        persona.setDni("122");
        persona.setEdad(16);
        persona.setId(1);
        doReturn(persona).when(personaService).addPersona(any());

        // act
        ResponseEntity<String> savedPersona = (ResponseEntity<String>) personaController.savePersona(persona);

        // asset
        verify(personaService, times(1)).addPersona(persona);
        assertEquals(HttpStatus.CREATED, savedPersona.getStatusCode());
    }
}