package es.taw.movies.controller;


import es.taw.movies.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.taw.movies.repository.PeopleRepository;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class peopleController {

    @Autowired
    protected PeopleRepository peopleRepository;


    @GetMapping("/")
    public String doInit(Model model){

        List<People> people =peopleRepository.findAll();

        model.addAttribute("people", people);

        return "people";
    }

    @GetMapping("/movies/filter")
    public String doFilter(@PathParam(value = "filter") String filter,
                           @PathParam(value = "tipo") String tipo,
                           Model model){

        List<People> people = new ArrayList<>();

        if(tipo != null){
            if(tipo.equals("Reparto")){
                people = peopleRepository.filtrarPorNombreReparto(filter != null ? filter : "");

            }else if(tipo.equals("Trabajadores")){
                people = peopleRepository.filtrarPorNombreTrabajo(filter != null ? filter : "");
            }

        }else if(filter != null){
            people = peopleRepository.filtrarPorNombre(filter);

        }else{
            people = peopleRepository.findAll();
        }

        model.addAttribute("people", people);

        return "people";
    }



}
