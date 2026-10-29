package es.tesaw.movies.controller;

import es.tesaw.movies.dao.GenreRepository;
import es.tesaw.movies.dao.MoviesRepository;
import es.tesaw.movies.dao.SpokenLanguageRepository;
import es.tesaw.movies.entity.GenreEntity;
import es.tesaw.movies.entity.MovieEntity;
import es.tesaw.movies.entity.SpokenLanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Column;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MoviesController {

    @Autowired
    protected MoviesRepository moviesRepository;

    @Autowired
    protected SpokenLanguageRepository spokenLanguageRepository;

    @Autowired
    protected GenreRepository genreRepository;





    @GetMapping("/movies")
    public String doFilter(
            @RequestParam(value = "filter", required = false) String filter,
            @RequestParam(value = "genres", required = false) List<Integer> markedGenres,
            Model model) {
        List<MovieEntity> lista;


        if ((filter == null || filter.isEmpty()) && markedGenres == null) {
            lista = this.moviesRepository.findAll();
        } else if (markedGenres == null) {
            lista = this.moviesRepository.filtrarPalabra(filter);
        } else {
            lista = this.moviesRepository.filtrarPalabraYGenero(filter,
                    markedGenres);
        }

        model.addAttribute("pelis", lista);

        List<GenreEntity> genresAll = genreRepository.findAll();
        model.addAttribute("genres", genresAll);

        model.addAttribute("markedGenres", markedGenres);

        return "movies";
    }

    @GetMapping("/movies/edit")
    public String doEdit (@RequestParam("id") Integer id, Model model) {
        return this.editCreate(id, model);
    }

    @PostMapping("/movies/create")
    public String doCreate(Model model){
        return this.editCreate(null, model);
    }

    protected String editCreate(Integer id, Model model){
        MovieEntity movie = null;
        if(id == null) movie = new MovieEntity();
        else movie = this.moviesRepository.findById(id).get();

        model.addAttribute("movie", movie);

        List<SpokenLanguageEntity> languages = this.spokenLanguageRepository.findAll();
        model.addAttribute("languages", languages);

        List<GenreEntity> genres = this.genreRepository.findAll();
        model.addAttribute("genres", genres);

        return "movie_edit";
    }



   @PostMapping("/movies/save")
   public String doGuardar (@RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "titulo", required = false) String titulo,
                            @RequestParam(value = "sinopsis", required = false) String sinopsis,
                            @RequestParam(value = "titulo_orig", required = false)  String originalTitle,
                            @RequestParam(value = "fecha", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate,
                            @RequestParam(value = "runtime", required = false) Float runtime,
                            @RequestParam(value = "budget", required = false) Long budget,
                            @RequestParam(value = "revenue", required = false) Long revenue,
                            @RequestParam(value = "status", required = false) String status,
                            @RequestParam(value = "tagline", required = false) String tagline,
                            @RequestParam(value = "popularity", required = false) Float popularity,
                            @RequestParam(value = "voteAverage", required = false) Float voteAverage,
                            @RequestParam(value = "voteCount", required = false) Integer voteCount,
                            @RequestParam(value = "homepage", required = false) String homepage,
                            @RequestParam(value = "genres", required = false) List<Integer> genres,
                            @RequestParam(value = "language", required = false) Integer language) {




        MovieEntity movie = null;
        if(id == null) movie = new MovieEntity();
        else movie = this.moviesRepository.findById(id).get();
        movie.setTitle(titulo);
        movie.setOriginalTitle(originalTitle);
        movie.setOverview(sinopsis);
        movie.setReleaseDate(releaseDate);
        movie.setRuntime(runtime);
        movie.setBudget(budget);
        movie.setRevenue(revenue);
        movie.setStatus(status);
        movie.setTagline(tagline);
        movie.setPopularity(popularity);
        movie.setVoteAverage(voteAverage);
        movie.setVoteCount(voteCount);
        movie.setHomepage(homepage);
        movie.setOriginalLanguage(this.spokenLanguageRepository.findById(language).get());
        if(genres != null){
            movie.setGenres(this.genreRepository.findAllById(genres));
        }



        this.moviesRepository.save(movie);
        return "redirect:/movies";
   }

   @PostMapping("/movies/remove")
    public String doRemove(@RequestParam("id") Integer id){
        MovieEntity movie = this.moviesRepository.findById(id).get();
        movie.deleteGenres();
        movie.deleteSpokenLanguages();
        movie.deleteProductionCompanies();


        this.moviesRepository.delete(movie);

        return "redirect:/movies";
   }


}
