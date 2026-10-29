package es.taw.movies.controller;


import es.taw.movies.entity.Movies;
import es.taw.movies.entity.SpokenLanguages;
import es.taw.movies.repository.MoviesRepository;
import es.taw.movies.repository.SpokenLanguagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class moviesController {


    @Autowired
    protected MoviesRepository moviesRepository;
    @Autowired
    private SpokenLanguagesRepository spokenLanguagesRepository;

    @GetMapping("/movie/{id}")
    public String doEdit(@PathVariable Integer id, Model model){
        Movies movie = moviesRepository.findById(id).orElseThrow();


        model.addAttribute("movie", movie);
        model.addAttribute("languages", spokenLanguagesRepository.findAll());

        return "movie_edit";
    }

    @PostMapping("/movie/save")
    public String doSave(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                         @RequestParam(value = "budget") BigInteger budget,
                         @RequestParam(value = "languages") List<String> languages,
                         @RequestParam(value = "overview") String overview,
                         Model model){
        Movies movie = moviesRepository.findById(id).orElseThrow();
        movie.setTitle(title);
        movie.setReleaseDate(date);
        movie.setBudget(budget);
        if(languages != null)movie.setSpokenLanguagesList(spokenLanguagesRepository.findAllById(languages));
        else movie.setSpokenLanguagesList(null);
        movie.setOverview(overview);

        moviesRepository.save(movie);

        return "redirect:/";
    }

}
