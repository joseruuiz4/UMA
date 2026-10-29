package es.taw.julio2025.controller;


import es.taw.julio2025.dao.DinosaurioRepository;
import es.taw.julio2025.dao.HabitatRepository;
import es.taw.julio2025.entity.Descubridor;
import es.taw.julio2025.entity.Dinosaurio;
import es.taw.julio2025.entity.Habitat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DinosaurioController {

    @Autowired
    protected HabitatRepository habitatRepository;

    @Autowired
    protected DinosaurioRepository dinosaurioRepository;

    @GetMapping("/duplicar/{id}")
    public String doDuplicar(@PathVariable Integer id){
        Dinosaurio dino = dinosaurioRepository.findById(id).orElseThrow();



        Dinosaurio newDino = new Dinosaurio();


        newDino.setNombre(dino.getNombre() + " (1)");
        newDino.setDieta(dino.getDieta());
        newDino.setPeriodo(dino.getPeriodo());
        newDino.setHabitats(new ArrayList<>(dino.getHabitats()));
        newDino.setDescubridores(new ArrayList<>(dino.getDescubridores()));
        newDino.setPeso_toneladas(dino.getPeso_toneladas());
        newDino.setTamaño_metros(dino.getTamaño_metros());

        dinosaurioRepository.save(newDino);

        return "redirect:/";
    }


    @GetMapping("/borrar/{id}")
    public String doBorrar(@PathVariable Integer id){
        Dinosaurio dino = dinosaurioRepository.findById(id).orElseThrow();

        dinosaurioRepository.delete(dino);

        return "redirect:/";
    }




    @GetMapping("/")
    public String doInit(@RequestParam(value = "habitat", required = false) Integer habitat,
                         @RequestParam(value = "tamaño", required = false) Float tamaño,
                         @RequestParam(value = "peso", required = false) Float peso,
                         Model model){

        List<Dinosaurio> dinosaurioList = null;

        List<Habitat> habitats = habitatRepository.findAll();
        model.addAttribute("habitats", habitats);

        if(habitat!=null&&tamaño!=null&&peso!=null){
            dinosaurioList = dinosaurioRepository.filtroHabitatTamanyoPeso(habitat, tamaño, peso);
            model.addAttribute("habitat", habitat);
            model.addAttribute("tamaño", tamaño);
            model.addAttribute("peso", peso);

        }else if(habitat != null && tamaño != null){
            dinosaurioList = dinosaurioRepository.filtroHabitatTamanyo(habitat, tamaño);
            model.addAttribute("habitat", habitat);
            model.addAttribute("tamaño", tamaño);

        }else if(habitat!=null && peso!=null){
            dinosaurioList = dinosaurioRepository.filtroHabitatPeso(habitat, peso);
            model.addAttribute("habitat", habitat);
            model.addAttribute("peso", peso);


        }else if(habitat==null&&tamaño!=null&&peso!=null){
            dinosaurioList = dinosaurioRepository.filtroTamanyoPeso(tamaño, peso);
            model.addAttribute("tamaño", tamaño);
            model.addAttribute("peso", peso);

        } else if (habitat != null) {
            dinosaurioList = dinosaurioRepository.filtroHabitat(habitat);
            model.addAttribute("habitat", habitat);

        } else if (tamaño!= null) {
            dinosaurioList = dinosaurioRepository.filtroTamanyo(tamaño);
            model.addAttribute("tamaño", tamaño);

        }else if(peso != null){
            dinosaurioList = dinosaurioRepository.filtroPeso(peso);
            model.addAttribute("peso", peso);

        } else{
            dinosaurioList = dinosaurioRepository.findAll();
        }

        model.addAttribute("dinos", dinosaurioList);





        return "dinosaurio";
    }






}
