package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
//    Movies Database(movie name,movie object)
    HashMap<String,Movie> moviesDb=new HashMap<>();

    //    Directors Database(director name,director object)
    HashMap<String,Director>  directorsDb=new HashMap<>();

    //    Director and Movies Database(director name,list of movie names)
    HashMap<String, List<String>>  directorMoviesDb=new HashMap<>();

    public String addMovie(Movie movie){
        if(movie==null || movie.getName()==null) return "FAILURE";
        String name=movie.getName();
        moviesDb.put(name,movie);
        return "SUCCESS";
    }

    public String addDirector(Director director){
        if(director==null || director.getName()==null) return "FAILURE";
        String name=director.getName();
        directorsDb.put(name,director);
        return "SUCCESS";
    }

    public String addMovieDirectorPair(String movieName,String directorName){
        if(directorName==null || movieName==null) return "FAILURE";
        if(moviesDb.containsKey(movieName) && directorsDb.containsKey(directorName)){
            if(!directorMoviesDb.containsKey(directorName)) directorMoviesDb.put(directorName,new ArrayList<>());
            if(!directorMoviesDb.get(directorName).contains(movieName));
            directorMoviesDb.get(directorName).add(movieName);
            return "SUCCESS";
        }
        return "FAILURE";

    }

    public Movie getMovieByName(String movieName){
        if(!moviesDb.containsKey(movieName)) return null;
        return moviesDb.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        if(!directorsDb.containsKey(directorName)) return null;
        return directorsDb.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        if(directorMoviesDb.containsKey(directorName)) return directorMoviesDb.get(directorName);
        return null;
    }

    public List<String> findAllMovies(){
        List<String> l=new ArrayList<>();
        l.addAll(moviesDb.keySet());
        return l;
    }

    public String deleteDirectorByName(String directorName){
        if(directorName==null || !directorMoviesDb.containsKey(directorName)) return "FAILURE";
        delete(directorName);
        directorMoviesDb.remove(directorName);
        directorsDb.remove(directorName);
        return "SUCCESS";

    }
    public void delete(String directorName){
        for(String movieName:directorMoviesDb.get(directorName)){
            moviesDb.remove(movieName);
        }

        System.out.println(moviesDb);
    }
    public String deleteAllDirectors(){
        for(String directorName:directorMoviesDb.keySet()){
            delete(directorName);
        }
        directorMoviesDb.clear();
        directorsDb.clear();
        return "SUCCESS";
    }


}
