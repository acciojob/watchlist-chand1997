package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController{
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        if(movieService.addMovie(movie).equals("SUCCESS"))
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);

        return new ResponseEntity<>("FAILURE",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody  Director director){
        if(movieService.addDirector(director).equals("SUCCESS"))
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);

        return new ResponseEntity<>("FAILURE",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName") String movieName,
                                                       @RequestParam("directorName") String directorName){
        if(movieService.addMovieDirectorPair(movieName,directorName).equals("SUCCESS"))
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);

        return new ResponseEntity<>("FAILURE",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie movie=null;
       movie=movieService.getMovieByName(name);
       return new ResponseEntity<>(movie,HttpStatus.OK);

    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String name){
        Director director=null;
        director=movieService.getDirectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.OK);

    }

    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String director){
        List<String> l=movieService.getMoviesByDirectorName(director);
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> l=movieService.findAllMovies();
        return new ResponseEntity<>(l, HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("name") String name){
        if(movieService.deleteDirectorByName(name).equals("SUCCESS"))
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        return new ResponseEntity<>("FAILURE",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        if(movieService.deleteAllDirectors().equals("SUCCESS"))
            return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
        return new ResponseEntity<>("FAILURE",HttpStatus.BAD_REQUEST);
    }

}
