/*
    GR Y
    Carlos Rodríguez Martin
    José Ruiz Pareja
 */

package com.uma.example.springuma.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uma.example.springuma.model.Imagen;
import com.uma.example.springuma.model.Paciente;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagenControllerWebTestClientIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ObjectMapper objectMapper;

    private WebTestClient client;

    @PostConstruct
    public void init() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:" + port)
                .responseTimeout(Duration.ofSeconds(10))
                .build();
    }


    @Test
    @DisplayName("Subir imagen correctamente con un cliente y comprobar que se ha subido")
    public void uploadImage_Correct() throws Exception {
        //CREAR PACIENTE

        Paciente paciente = new Paciente();
        paciente.setId(1);

        client.post().uri("/paciente").contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(paciente), Paciente.class).
                exchange().
                expectStatus().isCreated();

        //SUBIR IMAGEN

        ClassPathResource imagen = new ClassPathResource("healthy.png");
        File archivoSubida = imagen.getFile();

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(archivoSubida));
        builder.part("paciente", paciente);

        client.post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.response").value(resp -> {
                    assertTrue(resp.toString().contains("file uploaded successfully"));
                });

        //COMPROBAR QUE SE HA SUBIDO

        client.get()
                .uri("/imagen/paciente/{id}", paciente.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].paciente.id").isEqualTo(paciente.getId());
    }

    @Test
    @DisplayName("Realizar una prediccion sobre una imagen existente correctamente")
    public void getImagenPrediction_ValidImageID_ReturnPrediction() throws Exception {
        //CREAR PACIENTE

        Paciente paciente = new Paciente();
        paciente.setId(1);

        client.post().uri("/paciente").contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(paciente), Paciente.class).
                exchange().
                expectStatus().isCreated();

        //SUBIR IMAGEN

        ClassPathResource imagen = new ClassPathResource("healthy.png");
        File archivoSubida = imagen.getFile();

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(archivoSubida));
        builder.part("paciente", paciente);

        client.post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.response").value(resp -> {
                    assertTrue(resp.toString().contains("file uploaded successfully"));
                });

        //OBTENER IMAGEN

        Imagen img = client.get().uri("/imagen/paciente/{id}", paciente.getId())
                .exchange()
                .expectStatus().isOk()
                .returnResult(Imagen.class)
                .getResponseBody().blockFirst();

        // PREDECIR IMAGEN
        client.get()
                .uri("/imagen/predict/{id}", img.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").exists()
                .jsonPath("$.score").exists();
    }

    @Test
    @DisplayName("Obtener una imagen que no existe")
    public void downloadImage_NoImage_ReturnError(){
        client.get().uri("imagen/info/{id}",  999)
                .exchange()
                .expectStatus().is5xxServerError();
    }


    @Test
    @DisplayName("Eliminar una imagen que existe y comprobar que se ha eliminado")
    public void deleteImagen_ExistingImage_DeletesSuccessfully() throws Exception {
        //CREAR PACIENTE

        Paciente paciente = new Paciente();
        paciente.setId(1);

        client.post().uri("/paciente").contentType(MediaType.APPLICATION_JSON).
                body(Mono.just(paciente), Paciente.class).
                exchange().
                expectStatus().isCreated();


        //SUBIR IMAGEN

        ClassPathResource imagen = new ClassPathResource("healthy.png");
        File archivoSubida = imagen.getFile();

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("image", new FileSystemResource(archivoSubida));
        builder.part("paciente", paciente);

        client.post()
                .uri("/imagen")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.response").value(resp -> {
                    assertTrue(resp.toString().contains("file uploaded successfully"));
                });

        //OBTENER IMAGEN

        Imagen img = client.get().uri("/imagen/paciente/{id}", paciente.getId())
                .exchange()
                .expectStatus().isOk()
                .returnResult(Imagen.class)
                .getResponseBody().blockFirst();

        //ELIMINAR IMAGEN
        client.delete()
                .uri("/imagen/{id}", img.getId())
                .exchange()
                .expectStatus().isNoContent();

        //COMPROBAR QUE SE HA ELIMINADO
        client.get()
                .uri("/imagen/paciente/{id}", paciente.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(0);
    }




}


