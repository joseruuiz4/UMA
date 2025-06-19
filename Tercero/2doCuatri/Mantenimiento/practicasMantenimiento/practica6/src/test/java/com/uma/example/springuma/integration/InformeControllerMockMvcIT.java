/*
    GR Y
    Carlos Rodríguez Martin
    José Ruiz Pareja
 */

package com.uma.example.springuma.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Informe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class InformeControllerMockMvcIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;





    @Test
    @DisplayName("crearInforme crea correctamente un informe y comprobamos que se haya creado")
    void crearInforme_Correct() throws Exception {
        Informe informe = new Informe();
        informe.setPrediccion("prediccion");
        informe.setContenido("contenido");

        //CREAR EL INFORME
        this.mockMvc.perform(post("/informe")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        // OBTENEMOS EL INFORME Y COMPROBAMOS QUE SE HAYA CREADO
        this.mockMvc.perform(get("/informe/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.prediccion").exists())
                .andExpect(jsonPath("$.contenido").value(informe.getContenido()));

    }

    @Test
    @DisplayName("Creamos un informe, luego lo borramos y comprobamos que se haya borrado")
    public void eliminarInforme_Correct() throws Exception {
        Informe informe = new Informe();
        informe.setPrediccion("prediccion");
        informe.setContenido("contenido");

        //CREAR EL INFORME
        this.mockMvc.perform(post("/informe")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(informe)))
                .andExpect(status().isCreated())
                .andExpect(status().is2xxSuccessful());

        //BORRAR EL INFORME
        this.mockMvc.perform(delete("/informe/" + informe.getId()))
                .andExpect(status().isNoContent());

        //COMPROBAR QUE SE HA BORRADO
        this.mockMvc.perform(get("/informe/" + informe.getId()))
                .andExpect(status().isOk()) // no lanza error aunque el informe ya no exista en la bd
                .andExpect(content().string(""));
    }



}
