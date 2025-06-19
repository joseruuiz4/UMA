/*
    GR Y
    Carlos Rodríguez Martin
    José Ruiz Pareja
 */

package com.uma.example.springuma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.integration.base.AbstractIntegration;
import com.uma.example.springuma.model.Medico;
import com.uma.example.springuma.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PacienteControllerMockMvcIT extends AbstractIntegration{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Medico crearMedico(Long id, String dni, String nombre, String especialidad) {
        Medico r = new Medico();
        r.setId(id);
        r.setDni(dni);
        r.setEspecialidad(especialidad);
        r.setNombre(nombre);
        return r;
    }

    private Paciente crearPaciente(Long id, String nombre, String dni, String cita, Medico medico, int edad) {
        Paciente r = new Paciente();
        r.setId(id);
        r.setDni(dni);
        r.setNombre(nombre);
        r.setCita(cita);
        r.setEdad(edad);
        r.setMedico(medico);
        return r;
    }

    @Test
    @DisplayName("Crear paciente con medico asociado y comprobar relacion")
    void guardarPacienteYVerificar() throws Exception {
        // Primero creamos el medico en la base de datos
        Medico medico = crearMedico(1L, "12345678A", "Jordi Echevarria Navarro Perez", "Ginecologia");
        //Anadimos
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Creamos el paciente con el medico anterior asociado
        Paciente paciente = new Paciente("Andrea Polonia", 88, "25/05/25", "87654321B", medico);

        this.mockMvc.perform(post("/paciente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Consultamos los pacientes asociados al medico y comprobamos que se ha creado correctamente
        this.mockMvc.perform(get("/paciente/medico/1")
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").value(paciente));
    }

    @Test
    @DisplayName("Asociar medico a paciente y verificar que se ha asignado correctamente")
    void asociarPacienteConMedico() throws Exception {
        Medico medico = new Medico();
        medico.setDni("135792468C");
        medico.setNombre("Joseliyo Elnervio");
        medico.setEspecialidad("Hombro");
        medico.setId(1);

        Paciente paciente = new Paciente();
        paciente.setNombre("Carliyo Elnervio");
        paciente.setEdad(19);
        paciente.setCita("21/05/2025");
        paciente.setDni("54322600E");
        paciente.setMedico(medico);
        paciente.setId(1);

        // Crear medico
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Crear paciente
        this.mockMvc.perform(post("/paciente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Obtener paciente por ID y verificar datos del medico asociado
        this.mockMvc.perform(get("/paciente/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").value(paciente))
                .andExpect(jsonPath("$.medico.dni").value(medico.getDni()))
                .andExpect(jsonPath("$.medico.nombre").value(medico.getNombre()))
                .andExpect(jsonPath("$.medico.especialidad").value(medico.getEspecialidad()));
    }

    @Test
    @DisplayName("Actualizar medico del paciente y comprobar cambios")
    void actualizarPacienteYMedico() throws Exception {
        Medico medico1 = crearMedico(1L, "12345678A", "Jordi Echevarria Navarro Perez", "Ginecologia");

        // Crear medico inicial
        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico1)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Crear paciente con medico inicial
        Paciente paciente = crearPaciente(1L, "Scarpati Ruiz Pareja", "112233445Q", "26/05/26", medico1, 66);

        this.mockMvc.perform(post("/paciente")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(paciente)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Crear nuevo medico
        Medico medico2 = crearMedico(2L, "887766554X", "Jose Sundblad", "Fisioterapia");

        this.mockMvc.perform(post("/medico")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(medico2)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // Actualizar paciente con nuevo medico
        paciente.setMedico(medico2);

        this.mockMvc.perform(put("/paciente")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(paciente)));

        // Consultar pacientes por ID de nuevo medico y verificar actualizacion
        this.mockMvc.perform(get("/paciente/medico/2")
                        .contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").value(paciente));
    }

}