package es.taw.movies.controller;


import es.taw.movies.entity.Movies;
import es.taw.movies.entity.ProductionCompanies;
import es.taw.movies.repository.MoviesRepository;
import es.taw.movies.repository.ProductionCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class productionCompaniesController {

    @Autowired
    protected MoviesRepository moviesRepository;

    @Autowired
    protected ProductionCompanyRepository productionCompanyRepository;


    @GetMapping("/")
    public String doInit(@RequestParam(value = "productionCompanies", required = false) List<Integer> checkedProductionCompanies, Model model){
        List<ProductionCompanies> productionCompanies = productionCompanyRepository.findAll();
        List<Movies> movies = new ArrayList<>();

        if(checkedProductionCompanies == null){
            movies = moviesRepository.findAll();

        }else{
            movies = moviesRepository.filtrarPorProductionCompany(checkedProductionCompanies);
            model.addAttribute("checkedProductionCompanies", checkedProductionCompanies);
        }


        model.addAttribute("movies", movies);
        model.addAttribute("productionCompanies", productionCompanies);

        return "productionMovies";
    }

}
