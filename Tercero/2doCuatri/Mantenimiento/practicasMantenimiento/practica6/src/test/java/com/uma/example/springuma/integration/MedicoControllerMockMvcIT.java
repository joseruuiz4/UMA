/*
    GR Y
    Carlos Rodríguez Martin
    José Ruiz Pareja
 */

package com.uma.example.springuma.integration;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Medico;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

class MedicoControllerMockMvcIT extends AbstractIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private Medico createMedico(Long id,String dni,String nombre,String especialidad){
        Medico r = new Medico();
        r.setId(id);
        r.setDni(dni);
        r.setEspecialidad(especialidad);
        r.setNombre(nombre);
        return r;
    }




    @Test
    @DisplayName("Registrar medico y recuperar sus datos")
    void guardarMedicoYConsultar() throws Exception {
        Medico medico = this.createMedico(1L, "12345678X", "Carlos RM", "Mamografias");

        // Se realiza una solicitud para agregar un medico
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Se consulta el medico agregado para verificar su existencia

        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.dni").value("12345678X"))
                .andExpect(jsonPath("$.especialidad").value("Mamografias"))
                .andExpect(jsonPath("$.nombre").value("Carlos RM"))
                .andReturn();


    }

    @Test
    @DisplayName("Actualizar datos del medico correctamente")
    void editarDatosDeMedico() throws Exception{

        Medico medico = this.createMedico(1L, "12345678X", "Carlos RM", "Mamografias");

        // Se crea un nuevo registro para el medico
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());



        // Se modifica el nombre del medico
        medico.setNombre("Jose RP");

        // Se realiza la solicitud para actualizar el medico
        this.mockMvc.perform(put("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().is2xxSuccessful());

        // Se verifica que el cambio haya sido guardado

        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.dni").value("12345678X"))
                .andExpect(jsonPath("$.especialidad").value("Mamografias"))
                .andExpect(jsonPath("$.nombre").value("Jose RP"))
                .andReturn();

    }


    @Test
    @DisplayName("Eliminar medico y confirmar eliminacion")
    void eliminarMedicoYComprobar() throws Exception{

        Medico medico = this.createMedico(1L, "12345678X", "Carlos RM", "Mamografias");

        // Se almacena el medico en la base de datos
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Se elimina el medico previamente insertado
        this.mockMvc.perform(delete("/medico/1"))
                .andExpect(status().is2xxSuccessful());

        // Se intenta recuperar el medico eliminado, lo cual deberia fallar
        MvcResult mvcResult = this.mockMvc.perform(get("/medico/1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType("application/json"))
                .andReturn();

    }



    @Test
    @DisplayName("Obtener medico mediante DNI")
    void consultarMedicoPorDNI() throws Exception{

        Medico medico = this.createMedico(1L, "12345678X", "Carlos RM", "Mamografias");

        // Se guarda el medico en el sistema
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Se accede a los datos del medico usando su documento de identidad
        this.mockMvc.perform(get("/medico/dni/12345678X"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.dni").value("12345678X"))
                .andExpect(jsonPath("$.especialidad").value("Mamografias"))
                .andExpect(jsonPath("$.nombre").value("Carlos RM"));




    }




}
