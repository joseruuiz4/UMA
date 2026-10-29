package es.taw.junio2025.controller;


import es.taw.junio2025.entity.Marca;
import es.taw.junio2025.entity.Servicio;
import es.taw.junio2025.repository.MarcaRepository;
import es.taw.junio2025.repository.ServicioRepository;
import es.taw.junio2025.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MarcaController {

    @Autowired
    protected MarcaRepository marcaRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;


    @GetMapping("/")
    public String selecciona(@RequestParam(value = "marca", required = false) Integer marcaID,
                             Model model){
        List<Marca> marcas = marcaRepository.findAll();
        model.addAttribute("marcas", marcas);

        if(marcaID != null){
            Marca marca = marcaRepository.findById(marcaID).orElseThrow();
            model.addAttribute("vehiculos", marca.getVehiculos());
            model.addAttribute("marcaID", marcaID);
        }
        return "selecciona";
    }


    @GetMapping("/servicios")
    public String verServicios(@RequestParam(value = "marca", required = false) Integer marcaID,
                             @RequestParam(value = "vehiculos", required = false) List<String> vehiculos,
                             Model model){


            Marca marca = marcaRepository.findById(marcaID).orElseThrow();


            List<Servicio> servicios = null;
            if(vehiculos != null) {
                servicios = vehiculoRepository.serviciosPorMarcaYVehiculo(marcaID, vehiculos);
            }else{
                servicios = servicioRepository.findByVehiculo_Marca_IdMarca(marcaID);
            }
            model.addAttribute("servicios", servicios);



        return "servicios";
    }


}
