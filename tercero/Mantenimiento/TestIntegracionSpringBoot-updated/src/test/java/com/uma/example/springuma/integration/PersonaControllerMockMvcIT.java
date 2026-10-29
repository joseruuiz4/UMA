package com.uma.example.springuma.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;     
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Persona;


class PersonaControllerMockMvcIT extends AbstractIntegration{

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
  
    @Test
    @DisplayName("Get correct hello string from Hello controller")
	void hello_ReturnHelloString() throws Exception {
		this.mockMvc.perform(get("/hello"))
        .andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Mantenimiento y Pruebas del Software, UMA")));
	}

	@Test
    @DisplayName("Initially the list of personas is an empty array")
	void listofPersonas_initially_IsEmpty() throws Exception {

        // obtiene el listado de personas
	    MvcResult mvcResult = this.mockMvc.perform(get("/personas"))
        .andDo(print()) // imprime el resultado
        .andExpect(status().isOk()) // comprueba que el codigo es 200
        .andExpect(content().contentType("application/json")) // comprueba que el content type es json
        .andExpect(jsonPath("$", hasSize(0))) // comprueba que el contenido sea un json array de 0 
        .andExpect(content().string("[]")) // comprueba que el contenido sea un json array de 0 
        .andReturn();
        
        String response = mvcResult.getResponse().getContentAsString();
        JsonPath.parse(response).read("$");
    }


    @Test
    @DisplayName("Create a persona and get it correctly")
	void createPersonaPost_isObtainedWithGet() throws Exception {
        Persona persona = new Persona();
        persona.setNombre("Alumno"); 
        persona.setDni("122");
        persona.setEdad(16);
        persona.setId(1);
        
        // crea una persona
        this.mockMvc.perform(post("/persona")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(persona)))
        .andExpect(status().isCreated())
        .andExpect(status().is2xxSuccessful());

        // obtiene el listado de personas
		this.mockMvc.perform(get("/personas"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andExpect(content().contentType("application/json"))
        .andExpect(jsonPath("$", hasSize(1)))  // comprueba que el tamanyo sea 1
        .andExpect(jsonPath("$[0]").value(persona)); // comprueba que el tipo resultante es igual a la persona creada
    }
}