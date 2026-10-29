package es.taw.movies.controller;


import es.taw.movies.entity.Genres;
import es.taw.movies.entity.Movies;
import es.taw.movies.repository.GenresRepository;
import es.taw.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected GenresRepository genresRepository;



    @GetMapping("/movies/edit/{id}")
    public String doEdit(@PathVariable Integer id, Model model){
        Movies movie = null;
        if(id != null) movie = movieRepository.findById(id).orElseThrow();
        else movie = new Movies();

        model.addAttribute("movie", movie);

        List<Genres> genres = genresRepository.findAll();
        model.addAttribute("genres", genres);

        return "people_edit";
    }



    @PostMapping("movies/save")
    public String doSave(@RequestParam(value = "id") Integer id,
                         @RequestParam(value = "title") String title,
                         @RequestParam(value = "runtime") Double runtime,
                         @RequestParam(value = "revenue") BigInteger revenue,
                         @RequestParam(value = "genres", required = false) List<Integer> genres){
        Movies movie = null;
        if(id != null) movie = movieRepository.findById(id).orElseThrow();
        else movie = new Movies(); //para reutilizar la funcion si queremos crear ademas de editar

        movie.setTitle(title);
        movie.setRuntime(runtime);
        movie.setRevenue(revenue);

        if(genres != null){
            List<Genres> movieGenres = genresRepository.findAllById(genres);
            movie.setGenresList(movieGenres);
        }else{
            movie.setGenresList(null);
        }

        movieRepository.save(movie);

        return "redirect:/";
    }

}
